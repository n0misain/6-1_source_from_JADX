package com.mikepenz.iconics.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class IconicsImageButton extends IconicsImageView {
    public IconicsImageButton(Context context) {
        this(context, null);
    }

    public IconicsImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, 16842824);
    }

    public IconicsImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFocusable(true);
    }

    protected boolean onSetAlpha(int alpha) {
        return false;
    }

    public CharSequence getAccessibilityClassName() {
        return ImageButton.class.getName();
    }
}
