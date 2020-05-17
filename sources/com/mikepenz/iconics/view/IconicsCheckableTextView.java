package com.mikepenz.iconics.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.widget.TextViewCompat;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.TextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.internal.CheckedCompoundIconicsDrawables;
import com.mikepenz.iconics.internal.CompoundIconsBundle;
import com.mikepenz.iconics.internal.IconicsViewsAttrsReader;
import com.mikepenz.iconics.utils.Utils;

public class IconicsCheckableTextView extends IconicsTextView implements Checkable, CheckedCompoundIconicsDrawables {
    private static final int[] CHECKED_STATE_SET = new int[]{16842912};
    private boolean mAnimateChanges;
    private boolean mBroadcasting;
    private boolean mChecked;
    protected CompoundIconsBundle mCheckedIconsBundle;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(IconicsCheckableTextView iconicsCheckableTextView, boolean z);
    }

    public IconicsCheckableTextView(Context context) {
        this(context, null);
    }

    public IconicsCheckableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 16842884);
    }

    public IconicsCheckableTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void initialize(Context context, AttributeSet attrs, int defStyle) {
        this.mCheckedIconsBundle = new CompoundIconsBundle();
        setFocusable(true);
        setClickable(true);
        super.applyAttr(context, attrs, defStyle);
        applyAttr(context, attrs, defStyle);
        setIcons();
    }

    @SuppressLint({"CustomViewStyleable"})
    @RestrictTo({Scope.LIBRARY_GROUP})
    public void applyAttr(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, C0973R.styleable.IconicsCheckableTextView, defStyle, 0);
        IconicsViewsAttrsReader.readIconicsCheckableTextView(context, a, this.mCheckedIconsBundle);
        a.recycle();
        a = context.obtainStyledAttributes(attrs, C0973R.styleable.IconicsAnimateChanges, defStyle, 0);
        this.mAnimateChanges = a.getBoolean(C0973R.styleable.IconicsAnimateChanges_iiv_animate_icon_changes, true);
        a.recycle();
    }

    private void setIcons() {
        TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds((TextView) this, createStatesStart(), createStatesTop(), createStatesEnd(), createStatesBottom());
    }

    private StateListDrawable createStatesStart() {
        return Utils.getCheckableIconStateList(getContext(), this.mIconsBundle.mStartIconBundle, this.mCheckedIconsBundle.mStartIconBundle, this.mAnimateChanges);
    }

    private StateListDrawable createStatesTop() {
        return Utils.getCheckableIconStateList(getContext(), this.mIconsBundle.mTopIconBundle, this.mCheckedIconsBundle.mTopIconBundle, this.mAnimateChanges);
    }

    private StateListDrawable createStatesEnd() {
        return Utils.getCheckableIconStateList(getContext(), this.mIconsBundle.mEndIconBundle, this.mCheckedIconsBundle.mEndIconBundle, this.mAnimateChanges);
    }

    private StateListDrawable createStatesBottom() {
        return Utils.getCheckableIconStateList(getContext(), this.mIconsBundle.mBottomIconBundle, this.mCheckedIconsBundle.mBottomIconBundle, this.mAnimateChanges);
    }

    public CharSequence getAccessibilityClassName() {
        return IconicsCheckableTextView.class.getName();
    }

    public void setChecked(boolean checked) {
        if (this.mChecked != checked) {
            this.mChecked = checked;
            refreshDrawableState();
            if (!this.mBroadcasting) {
                this.mBroadcasting = true;
                if (this.mOnCheckedChangeListener != null) {
                    this.mOnCheckedChangeListener.onCheckedChanged(this, this.mChecked);
                }
                this.mBroadcasting = false;
            }
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.mOnCheckedChangeListener = listener;
    }

    protected int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public void toggle() {
        setChecked(!this.mChecked);
    }

    public boolean performClick() {
        toggle();
        boolean handled = super.performClick();
        if (!handled) {
            playSoundEffect(0);
        }
        return handled;
    }

    @Nullable
    public IconicsDrawable getCheckedIconicsDrawableStart() {
        if (this.mCheckedIconsBundle.mStartIconBundle != null) {
            return this.mCheckedIconsBundle.mStartIconBundle;
        }
        return null;
    }

    @Nullable
    public IconicsDrawable getCheckedIconicsDrawableTop() {
        if (this.mCheckedIconsBundle.mTopIconBundle != null) {
            return this.mCheckedIconsBundle.mTopIconBundle;
        }
        return null;
    }

    @Nullable
    public IconicsDrawable getCheckedIconicsDrawableEnd() {
        if (this.mCheckedIconsBundle.mEndIconBundle != null) {
            return this.mCheckedIconsBundle.mEndIconBundle;
        }
        return null;
    }

    @Nullable
    public IconicsDrawable getCheckedIconicsDrawableBottom() {
        if (this.mCheckedIconsBundle.mBottomIconBundle != null) {
            return this.mCheckedIconsBundle.mBottomIconBundle;
        }
        return null;
    }

    public void setCheckedDrawableStart(@Nullable IconicsDrawable drawable) {
        this.mCheckedIconsBundle.mStartIconBundle = drawable;
        setIcons();
    }

    public void setCheckedDrawableTop(@Nullable IconicsDrawable drawable) {
        this.mCheckedIconsBundle.mTopIconBundle = drawable;
        setIcons();
    }

    public void setCheckedDrawableEnd(@Nullable IconicsDrawable drawable) {
        this.mCheckedIconsBundle.mEndIconBundle = drawable;
        setIcons();
    }

    public void setCheckedDrawableBottom(@Nullable IconicsDrawable drawable) {
        this.mCheckedIconsBundle.mBottomIconBundle = drawable;
        setIcons();
    }

    public void setCheckedDrawableForAll(@Nullable IconicsDrawable drawable) {
        this.mCheckedIconsBundle.mStartIconBundle = drawable;
        this.mCheckedIconsBundle.mTopIconBundle = drawable;
        this.mCheckedIconsBundle.mEndIconBundle = drawable;
        this.mCheckedIconsBundle.mBottomIconBundle = drawable;
        setIcons();
    }
}
