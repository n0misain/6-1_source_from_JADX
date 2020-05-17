package com.twitter.sdk.android.tweetui.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class OverlayImageView extends ImageView {
    Overlay overlay = new Overlay(null);

    protected static class Overlay {
        final Drawable drawable;

        Overlay(Drawable drawable) {
            this.drawable = drawable;
        }

        protected void cleanupDrawable(ImageView imageView) {
            if (this.drawable != null) {
                this.drawable.setCallback(null);
                imageView.unscheduleDrawable(this.drawable);
            }
        }

        protected void setDrawableBounds(int width, int height) {
            if (this.drawable != null) {
                this.drawable.setBounds(0, 0, width, height);
            }
        }

        protected void setDrawableState(int[] state) {
            if (this.drawable != null && this.drawable.isStateful()) {
                this.drawable.setState(state);
            }
        }

        protected void draw(Canvas canvas) {
            if (this.drawable != null) {
                this.drawable.draw(canvas);
            }
        }
    }

    public OverlayImageView(Context context) {
        super(context);
    }

    public OverlayImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.overlay.draw(canvas);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.overlay.setDrawableState(getDrawableState());
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.overlay.setDrawableBounds(getMeasuredWidth(), getMeasuredHeight());
    }

    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        this.overlay.setDrawableBounds(width, height);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (drawable == this.overlay.drawable) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public void setOverlayDrawable(Drawable drawable) {
        if (drawable != this.overlay.drawable) {
            this.overlay.cleanupDrawable(this);
            if (drawable != null) {
                drawable.setCallback(this);
            }
            this.overlay = new Overlay(drawable);
            this.overlay.setDrawableState(getDrawableState());
            requestLayout();
        }
    }
}
