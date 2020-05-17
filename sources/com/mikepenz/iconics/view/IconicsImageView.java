package com.mikepenz.iconics.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView.ScaleType;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.internal.IconicsView;
import com.mikepenz.iconics.internal.IconicsViewsAttrsReader;
import com.mikepenz.iconics.typeface.IIcon;

public class IconicsImageView extends AppCompatImageView implements IconicsView {
    private IconicsDrawable mIcon;

    public IconicsImageView(Context context) {
        this(context, null);
    }

    public IconicsImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconicsImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            initialize(context, attrs, defStyle);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void initialize(Context context, AttributeSet attrs, int defStyle) {
        this.mIcon = new IconicsDrawable(context);
        applyAttr(context, attrs, defStyle);
        setScaleType(ScaleType.CENTER_INSIDE);
        setImageDrawable(this.mIcon);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void applyAttr(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, C0973R.styleable.IconicsImageView, defStyle, 0);
        IconicsViewsAttrsReader.readIconicsImageView(a, this.mIcon);
        a.recycle();
    }

    public void setIcon(@Nullable IconicsDrawable icon) {
        this.mIcon = icon;
        setImageDrawable(icon);
    }

    @Deprecated
    public void setIcon(Character icon) {
        if (this.mIcon != null) {
            this.mIcon.icon(icon);
        } else {
            setIcon(new IconicsDrawable(getContext(), icon));
        }
    }

    @Deprecated
    public void setIcon(String icon) {
        if (this.mIcon != null) {
            this.mIcon.icon(icon);
        } else {
            setIcon(new IconicsDrawable(getContext(), icon));
        }
    }

    @Deprecated
    public void setIcon(IIcon icon) {
        if (this.mIcon != null) {
            this.mIcon.icon(icon);
        } else {
            setIcon(new IconicsDrawable(getContext(), icon));
        }
    }

    @Deprecated
    public void setIconText(String iconText) {
        setIcon(new IconicsDrawable(getContext()).iconText(iconText));
    }

    @Deprecated
    public void setColor(@ColorInt int color) {
        this.mIcon.color(color);
    }

    @Deprecated
    public void setColorRes(@ColorRes int colorRes) {
        this.mIcon.colorRes(colorRes);
    }

    @Deprecated
    public void setPaddingPx(int padding) {
        this.mIcon.paddingPx(padding);
    }

    @Deprecated
    public void setPaddingDp(int paddingDp) {
        this.mIcon.paddingDp(paddingDp);
    }

    @Deprecated
    public void setPaddingRes(@DimenRes int paddingRes) {
        this.mIcon.paddingRes(paddingRes);
    }

    @Deprecated
    public void setContourColor(@ColorInt int color) {
        this.mIcon.contourColor(color);
    }

    @Deprecated
    public void setContourColorRes(@ColorRes int colorRes) {
        this.mIcon.contourColorRes(colorRes);
    }

    @Deprecated
    public void setContourWidthPx(int contourWidth) {
        this.mIcon.contourWidthPx(contourWidth);
    }

    @Deprecated
    public void setContourWidthDp(int contourWidthDp) {
        this.mIcon.contourWidthDp(contourWidthDp);
    }

    @Deprecated
    public void setContourWidthRes(@DimenRes int contourWidthRes) {
        this.mIcon.contourWidthRes(contourWidthRes);
    }

    @Deprecated
    public void setBackgroundColor(@ColorInt int color) {
        this.mIcon.backgroundColor(color);
    }

    @Deprecated
    public void setBackgroundColorRes(@ColorRes int colorRes) {
        this.mIcon.backgroundColorRes(colorRes);
    }

    @Deprecated
    public void setRoundedCornersPx(int cornerRadius) {
        this.mIcon.roundedCornersPx(cornerRadius);
    }

    @Deprecated
    public void setRoundedCornersDp(int cornerRadiusDp) {
        this.mIcon.roundedCornersDp(cornerRadiusDp);
    }

    @Deprecated
    public void setRoundedCornersRes(@DimenRes int cornerRadiusRes) {
        this.mIcon.roundedCornersRes(cornerRadiusRes);
    }

    @Nullable
    public IconicsDrawable getIcon() {
        if (getDrawable() instanceof IconicsDrawable) {
            return (IconicsDrawable) getDrawable();
        }
        return null;
    }
}
