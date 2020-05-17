package com.mikepenz.iconics.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView.BufferType;
import com.mikepenz.iconics.Iconics.IconicsBuilder;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.internal.CompoundIconicsDrawables;
import com.mikepenz.iconics.internal.CompoundIconsBundle;
import com.mikepenz.iconics.internal.IconicsView;
import com.mikepenz.iconics.internal.IconicsViewsAttrsReader;

public class IconicsTextView extends AppCompatTextView implements CompoundIconicsDrawables, IconicsView {
    protected final CompoundIconsBundle mIconsBundle;

    public IconicsTextView(Context context) {
        this(context, null);
    }

    public IconicsTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 16842884);
    }

    public IconicsTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mIconsBundle = new CompoundIconsBundle();
        if (!isInEditMode()) {
            initialize(context, attrs, defStyle);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void initialize(Context context, AttributeSet attrs, int defStyle) {
        applyAttr(context, attrs, defStyle);
        setIcons();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void applyAttr(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, C0973R.styleable.IconicsTextView, defStyle, 0);
        IconicsViewsAttrsReader.readIconicsTextView(context, a, this.mIconsBundle);
        a.recycle();
    }

    private void setIcons() {
        this.mIconsBundle.setIcons(this);
    }

    @Nullable
    public IconicsDrawable getIconicsDrawableStart() {
        if (this.mIconsBundle.mStartIconBundle != null) {
            return this.mIconsBundle.mStartIconBundle;
        }
        return null;
    }

    @Nullable
    public IconicsDrawable getIconicsDrawableTop() {
        if (this.mIconsBundle.mTopIconBundle != null) {
            return this.mIconsBundle.mTopIconBundle;
        }
        return null;
    }

    @Nullable
    public IconicsDrawable getIconicsDrawableEnd() {
        if (this.mIconsBundle.mEndIconBundle != null) {
            return this.mIconsBundle.mEndIconBundle;
        }
        return null;
    }

    @Nullable
    public IconicsDrawable getIconicsDrawableBottom() {
        if (this.mIconsBundle.mBottomIconBundle != null) {
            return this.mIconsBundle.mBottomIconBundle;
        }
        return null;
    }

    public void setDrawableStart(@Nullable IconicsDrawable drawable) {
        this.mIconsBundle.mStartIconBundle = drawable;
        setIcons();
    }

    public void setDrawableTop(@Nullable IconicsDrawable drawable) {
        this.mIconsBundle.mTopIconBundle = drawable;
        setIcons();
    }

    public void setDrawableEnd(@Nullable IconicsDrawable drawable) {
        this.mIconsBundle.mEndIconBundle = drawable;
        setIcons();
    }

    public void setDrawableBottom(@Nullable IconicsDrawable drawable) {
        this.mIconsBundle.mBottomIconBundle = drawable;
        setIcons();
    }

    public void setDrawableForAll(@Nullable IconicsDrawable drawable) {
        this.mIconsBundle.mStartIconBundle = drawable;
        this.mIconsBundle.mTopIconBundle = drawable;
        this.mIconsBundle.mEndIconBundle = drawable;
        this.mIconsBundle.mBottomIconBundle = drawable;
        setIcons();
    }

    public void setText(CharSequence text, BufferType type) {
        if (isInEditMode()) {
            super.setText(text, type);
        } else {
            super.setText(new IconicsBuilder().ctx(getContext()).on(text).build(), type);
        }
    }
}
