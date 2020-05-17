package com.mikepenz.iconics.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.TextView.BufferType;
import com.mikepenz.iconics.Iconics.IconicsBuilder;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.internal.CheckableIconBundle;
import com.mikepenz.iconics.internal.IconicsView;
import com.mikepenz.iconics.internal.IconicsViewsAttrsReader;

public class IconicsCompoundButton extends CompoundButton implements IconicsView {
    private final CheckableIconBundle mIconsBundle;

    public IconicsCompoundButton(Context context) {
        super(context);
        this.mIconsBundle = new CheckableIconBundle();
    }

    public IconicsCompoundButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconicsCompoundButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mIconsBundle = new CheckableIconBundle();
        if (!isInEditMode()) {
            initialize(context, attrs, defStyle);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void initialize(Context context, AttributeSet attrs, int defStyle) {
        this.mIconsBundle.createIcons(context);
        applyAttr(context, attrs, defStyle);
        setButtonDrawable(this.mIconsBundle.createStates(context));
    }

    @SuppressLint({"CustomViewStyleable"})
    @RestrictTo({Scope.LIBRARY_GROUP})
    public void applyAttr(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, C0973R.styleable.IconicsCompoundButton, defStyle, 0);
        IconicsViewsAttrsReader.readIconicsCompoundButton(context, a, this.mIconsBundle);
        a.recycle();
        a = context.obtainStyledAttributes(attrs, C0973R.styleable.IconicsAnimateChanges, defStyle, 0);
        this.mIconsBundle.mAnimateChanges = a.getBoolean(C0973R.styleable.IconicsAnimateChanges_iiv_animate_icon_changes, true);
        a.recycle();
    }

    public void setCheckedIcon(@Nullable IconicsDrawable icon) {
        this.mIconsBundle.mCheckedIconBundle = icon;
        setButtonDrawable(this.mIconsBundle.createStates(getContext()));
    }

    public void setUncheckedIcon(@Nullable IconicsDrawable icon) {
        this.mIconsBundle.mUncheckedIconBundle = icon;
        setButtonDrawable(this.mIconsBundle.createStates(getContext()));
    }

    @Nullable
    public IconicsDrawable getCheckedIcon() {
        if (this.mIconsBundle.mCheckedIconBundle != null) {
            return this.mIconsBundle.mCheckedIconBundle;
        }
        return null;
    }

    @Nullable
    public IconicsDrawable getUncheckedIcon() {
        if (this.mIconsBundle.mUncheckedIconBundle != null) {
            return this.mIconsBundle.mUncheckedIconBundle;
        }
        return null;
    }

    public void setText(CharSequence text, BufferType type) {
        if (isInEditMode()) {
            super.setText(text, type);
        } else {
            super.setText(new IconicsBuilder().ctx(getContext()).on(text).build(), type);
        }
    }

    public CharSequence getAccessibilityClassName() {
        return IconicsCompoundButton.class.getName();
    }
}
