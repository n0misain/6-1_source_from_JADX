package com.twitter.sdk.android.tweetui.internal;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

public abstract class ClickableLinkSpan extends ClickableSpan implements HighlightedClickableSpan {
    private final boolean colored;
    public final int linkColor;
    private boolean selected;
    private final int selectedColor;
    private final boolean underlined;

    public ClickableLinkSpan(int selectedColor, int linkColor, boolean underlined) {
        this(selectedColor, linkColor, true, underlined);
    }

    ClickableLinkSpan(int selectedColor, int linkColor, boolean colored, boolean underlined) {
        this.selectedColor = selectedColor;
        this.linkColor = linkColor;
        this.colored = colored;
        this.underlined = underlined;
    }

    public void updateDrawState(TextPaint ds) {
        if (this.colored) {
            ds.setColor(this.linkColor);
        } else {
            ds.setColor(ds.linkColor);
        }
        if (this.selected) {
            ds.bgColor = this.selectedColor;
        } else {
            ds.bgColor = 0;
        }
        if (this.underlined) {
            ds.setUnderlineText(true);
        }
    }

    public void select(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
    }
}
