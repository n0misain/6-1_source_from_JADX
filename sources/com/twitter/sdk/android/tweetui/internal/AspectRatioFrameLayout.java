package com.twitter.sdk.android.tweetui.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import com.twitter.sdk.android.tweetui.C1043R;

public class AspectRatioFrameLayout extends FrameLayout {
    static final int ADJUST_DIMENSION_HEIGHT = 0;
    static final int ADJUST_DIMENSION_WIDTH = 1;
    private static final int DEFAULT_ADJUST_DIMENSION = 0;
    private static final float DEFAULT_ASPECT_RATIO = 1.0f;
    protected double aspectRatio;
    private int dimensionToAdjust;

    public AspectRatioFrameLayout(Context context) {
        this(context, null);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttributes(defStyle);
    }

    private void initAttributes(int styleResId) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(styleResId, C1043R.styleable.AspectRatioFrameLayout);
        try {
            this.aspectRatio = (double) a.getFloat(C1043R.styleable.AspectRatioFrameLayout_tw__frame_layout_aspect_ratio, DEFAULT_ASPECT_RATIO);
            this.dimensionToAdjust = a.getInt(C1043R.styleable.AspectRatioFrameLayout_tw__frame_layout_dimension_to_adjust, 0);
        } finally {
            a.recycle();
        }
    }

    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        int horizontalPadding = getPaddingLeft() + getPaddingRight();
        int verticalPadding = getPaddingBottom() + getPaddingTop();
        if (this.dimensionToAdjust == 0) {
            if (MeasureSpec.getMode(widthMeasureSpec) == 1073741824) {
                width = MeasureSpec.getSize(widthMeasureSpec) - horizontalPadding;
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                width = getMeasuredWidth() - horizontalPadding;
            }
            height = (int) (((double) width) / this.aspectRatio);
        } else {
            if (MeasureSpec.getMode(heightMeasureSpec) == 1073741824) {
                height = MeasureSpec.getSize(heightMeasureSpec) - verticalPadding;
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                height = getMeasuredHeight() - verticalPadding;
            }
            width = (int) (((double) height) * this.aspectRatio);
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(width + horizontalPadding, 1073741824), MeasureSpec.makeMeasureSpec(height + verticalPadding, 1073741824));
    }
}
