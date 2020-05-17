package com.mikepenz.iconics.internal;

import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.widget.TextViewCompat;
import android.widget.TextView;
import com.mikepenz.iconics.IconicsDrawable;

@RestrictTo({Scope.LIBRARY_GROUP})
public class CompoundIconsBundle {
    public IconicsDrawable mBottomIconBundle;
    public IconicsDrawable mEndIconBundle;
    public IconicsDrawable mStartIconBundle;
    public IconicsDrawable mTopIconBundle;

    public void setIcons(TextView textView) {
        Drawable[] drawables = TextViewCompat.getCompoundDrawablesRelative(textView);
        TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, this.mStartIconBundle != null ? this.mStartIconBundle : drawables[0], this.mTopIconBundle != null ? this.mTopIconBundle : drawables[1], this.mEndIconBundle != null ? this.mEndIconBundle : drawables[2], this.mBottomIconBundle != null ? this.mBottomIconBundle : drawables[3]);
    }
}
