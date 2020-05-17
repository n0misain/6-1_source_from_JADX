package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class ToggleImageButton extends ImageButton {
    private static final int[] STATE_TOGGLED_ON = new int[]{C1043R.attr.state_toggled_on};
    String contentDescriptionOff;
    String contentDescriptionOn;
    boolean isToggledOn;
    final boolean toggleOnClick;

    public ToggleImageButton(Context context) {
        this(context, null);
    }

    public ToggleImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToggleImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = null;
        try {
            String str;
            a = context.getTheme().obtainStyledAttributes(attrs, C1043R.styleable.ToggleImageButton, defStyle, 0);
            String contentDescriptionOn = a.getString(C1043R.styleable.ToggleImageButton_contentDescriptionOn);
            String contentDescriptionOff = a.getString(C1043R.styleable.ToggleImageButton_contentDescriptionOff);
            this.contentDescriptionOn = contentDescriptionOn == null ? (String) getContentDescription() : contentDescriptionOn;
            if (contentDescriptionOff == null) {
                str = (String) getContentDescription();
            } else {
                str = contentDescriptionOff;
            }
            this.contentDescriptionOff = str;
            this.toggleOnClick = a.getBoolean(C1043R.styleable.ToggleImageButton_toggleOnClick, true);
            setToggledOn(false);
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
    }

    public int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 2);
        if (this.isToggledOn) {
            mergeDrawableStates(drawableState, STATE_TOGGLED_ON);
        }
        return drawableState;
    }

    public boolean performClick() {
        if (this.toggleOnClick) {
            toggle();
        }
        return super.performClick();
    }

    public void setToggledOn(boolean isToggledOn) {
        this.isToggledOn = isToggledOn;
        setContentDescription(isToggledOn ? this.contentDescriptionOn : this.contentDescriptionOff);
        refreshDrawableState();
    }

    public void toggle() {
        setToggledOn(!this.isToggledOn);
    }

    public boolean isToggledOn() {
        return this.isToggledOn;
    }
}
