package com.twitter.sdk.android.tweetui.internal;

import android.annotation.SuppressLint;
import android.text.Layout;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class SpanClickHandler {
    private HighlightedClickableSpan highlightedClickableSpan;
    private Layout layout;
    private float left;
    private float top;
    private final View view;

    public static void enableClicksOnSpans(TextView textView) {
        final SpanClickHandler helper = new SpanClickHandler(textView, null);
        textView.setOnTouchListener(new OnTouchListener() {
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent event) {
                TextView textView = (TextView) view;
                Layout layout = textView.getLayout();
                if (layout == null) {
                    return false;
                }
                helper.layout = layout;
                helper.left = (float) (textView.getTotalPaddingLeft() + textView.getScrollX());
                helper.top = (float) (textView.getTotalPaddingTop() + textView.getScrollY());
                return helper.handleTouchEvent(event);
            }
        });
    }

    public SpanClickHandler(View view, Layout layout) {
        this.view = view;
        this.layout = layout;
    }

    public boolean handleTouchEvent(MotionEvent event) {
        CharSequence text = this.layout.getText();
        Spanned spannedText = text instanceof Spanned ? (Spanned) text : null;
        if (spannedText == null) {
            return false;
        }
        int action = event.getAction() & 255;
        int x = (int) (event.getX() - this.left);
        int y = (int) (event.getY() - this.top);
        if (x < 0 || x >= this.layout.getWidth() || y < 0 || y >= this.layout.getHeight()) {
            deselectSpan();
            return false;
        }
        int line = this.layout.getLineForVertical(y);
        if (((float) x) < this.layout.getLineLeft(line) || ((float) x) > this.layout.getLineRight(line)) {
            deselectSpan();
            return false;
        } else if (action == 0) {
            int offset = this.layout.getOffsetForHorizontal(line, (float) x);
            HighlightedClickableSpan[] span = (HighlightedClickableSpan[]) spannedText.getSpans(offset, offset, HighlightedClickableSpan.class);
            if (span.length <= 0) {
                return false;
            }
            selectSpan(span[0]);
            return true;
        } else if (action != 1) {
            return false;
        } else {
            HighlightedClickableSpan selectedSpan = this.highlightedClickableSpan;
            if (selectedSpan == null) {
                return false;
            }
            selectedSpan.onClick(this.view);
            deselectSpan();
            return true;
        }
    }

    private void selectSpan(HighlightedClickableSpan span) {
        span.select(true);
        this.highlightedClickableSpan = span;
        invalidate();
    }

    private void deselectSpan() {
        HighlightedClickableSpan selectedSpan = this.highlightedClickableSpan;
        if (selectedSpan != null && selectedSpan.isSelected()) {
            selectedSpan.select(false);
            this.highlightedClickableSpan = null;
            invalidate();
        }
    }

    private void invalidate() {
        this.view.invalidate((int) this.left, (int) this.top, ((int) this.left) + this.layout.getWidth(), ((int) this.top) + this.layout.getHeight());
    }
}
