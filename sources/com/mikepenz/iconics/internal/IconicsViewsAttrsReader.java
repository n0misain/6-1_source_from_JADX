package com.mikepenz.iconics.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.utils.Utils;
import com.mikepenz.iconics.view.C0973R;

@RestrictTo({Scope.LIBRARY_GROUP})
public class IconicsViewsAttrsReader {
    private static final int DEF_COLOR = Integer.MIN_VALUE;
    private static final int DEF_SIZE = -1;

    public static void readIconicsImageView(TypedArray a, IconicsDrawable icon) {
        String i = a.getString(C0973R.styleable.IconicsImageView_iiv_icon);
        if (i != null) {
            icon.icon(i);
        }
        int color = a.getColor(C0973R.styleable.IconicsImageView_iiv_color, Integer.MIN_VALUE);
        if (color != Integer.MIN_VALUE) {
            icon.color(color);
        }
        int size = a.getDimensionPixelSize(C0973R.styleable.IconicsImageView_iiv_size, -1);
        if (size != -1) {
            icon.sizePx(size);
        }
        int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsImageView_iiv_padding, -1);
        if (padding != -1) {
            icon.paddingPx(padding);
        }
        int contourColor = a.getColor(C0973R.styleable.IconicsImageView_iiv_contour_color, Integer.MIN_VALUE);
        if (contourColor != Integer.MIN_VALUE) {
            icon.contourColor(contourColor);
        }
        int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsImageView_iiv_contour_width, -1);
        if (contourWidth != -1) {
            icon.contourWidthPx(contourWidth);
        }
        int backgroundColor = a.getColor(C0973R.styleable.IconicsImageView_iiv_background_color, Integer.MIN_VALUE);
        if (backgroundColor != Integer.MIN_VALUE) {
            icon.backgroundColor(backgroundColor);
        }
        int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsImageView_iiv_corner_radius, -1);
        if (cornerRadius != -1) {
            icon.roundedCornersPx(cornerRadius);
        }
    }

    public static void readIconicsTextView(Context ctx, TypedArray a, CompoundIconsBundle bundle) {
        IconicsDrawable allIconBundle = readIconicsTextViewAll(a, new IconicsDrawable(ctx));
        bundle.mStartIconBundle = readIconicsTextViewStart(ctx, a, allIconBundle);
        bundle.mTopIconBundle = readIconicsTextViewTop(ctx, a, allIconBundle);
        bundle.mEndIconBundle = readIconicsTextViewEnd(ctx, a, allIconBundle);
        bundle.mBottomIconBundle = readIconicsTextViewBottom(ctx, a, allIconBundle);
    }

    public static IconicsDrawable readIconicsTextViewAll(TypedArray a, IconicsDrawable icon) {
        String i = a.getString(C0973R.styleable.IconicsTextView_iiv_all_icon);
        if (i != null) {
            icon.icon(i);
        }
        int color = a.getColor(C0973R.styleable.IconicsTextView_iiv_all_color, Integer.MIN_VALUE);
        if (color != Integer.MIN_VALUE) {
            icon.color(color);
        }
        int size = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_all_size, -1);
        if (size != -1) {
            icon.sizePx(size);
        }
        int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_all_padding, -1);
        if (padding != -1) {
            icon.paddingPx(padding);
        }
        int contourColor = a.getColor(C0973R.styleable.IconicsTextView_iiv_all_contour_color, Integer.MIN_VALUE);
        if (contourColor != Integer.MIN_VALUE) {
            icon.contourColor(contourColor);
        }
        int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_all_contour_width, -1);
        if (contourWidth != -1) {
            icon.contourWidthPx(contourWidth);
        }
        int backgroundColor = a.getColor(C0973R.styleable.IconicsTextView_iiv_all_background_color, Integer.MIN_VALUE);
        if (backgroundColor != Integer.MIN_VALUE) {
            icon.backgroundColor(backgroundColor);
        }
        int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_all_corner_radius, -1);
        if (cornerRadius != -1) {
            icon.roundedCornersPx(cornerRadius);
        }
        return icon;
    }

    public static IconicsDrawable readIconicsTextViewStart(Context ctx, TypedArray a, IconicsDrawable icon) {
        String i = a.getString(C0973R.styleable.IconicsTextView_iiv_start_icon);
        if (i != null || isFilled(icon)) {
            if (icon == null) {
                icon = new IconicsDrawable(ctx, i);
            } else {
                icon = icon.clone();
                if (i != null) {
                    icon.icon(i);
                }
            }
            int color = a.getColor(C0973R.styleable.IconicsTextView_iiv_start_color, Integer.MIN_VALUE);
            if (color != Integer.MIN_VALUE) {
                icon.color(color);
            }
            int size = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_start_size, -1);
            if (size != -1) {
                icon.sizePx(size);
            }
            int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_start_padding, -1);
            if (padding != -1) {
                icon.paddingPx(padding);
            }
            int contourColor = a.getColor(C0973R.styleable.IconicsTextView_iiv_start_contour_color, Integer.MIN_VALUE);
            if (contourColor != Integer.MIN_VALUE) {
                icon.contourColor(contourColor);
            }
            int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_start_contour_width, -1);
            if (contourWidth != -1) {
                icon.contourWidthPx(contourWidth);
            }
            int backgroundColor = a.getColor(C0973R.styleable.IconicsTextView_iiv_start_background_color, Integer.MIN_VALUE);
            if (backgroundColor != Integer.MIN_VALUE) {
                icon.backgroundColor(backgroundColor);
            }
            int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_start_corner_radius, -1);
            if (cornerRadius != -1) {
                icon.roundedCornersPx(cornerRadius);
            }
        }
        return icon;
    }

    public static IconicsDrawable readIconicsTextViewTop(Context ctx, TypedArray a, IconicsDrawable icon) {
        String i = a.getString(C0973R.styleable.IconicsTextView_iiv_top_icon);
        if (i != null || isFilled(icon)) {
            if (icon == null) {
                icon = new IconicsDrawable(ctx, i);
            } else {
                icon = icon.clone();
                if (i != null) {
                    icon.icon(i);
                }
            }
            int color = a.getColor(C0973R.styleable.IconicsTextView_iiv_top_color, Integer.MIN_VALUE);
            if (color != Integer.MIN_VALUE) {
                icon.color(color);
            }
            int size = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_top_size, -1);
            if (size != -1) {
                icon.sizePx(size);
            }
            int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_top_padding, -1);
            if (padding != -1) {
                icon.paddingPx(padding);
            }
            int contourColor = a.getColor(C0973R.styleable.IconicsTextView_iiv_top_contour_color, Integer.MIN_VALUE);
            if (contourColor != Integer.MIN_VALUE) {
                icon.contourColor(contourColor);
            }
            int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_top_contour_width, -1);
            if (contourWidth != -1) {
                icon.contourWidthPx(contourWidth);
            }
            int backgroundColor = a.getColor(C0973R.styleable.IconicsTextView_iiv_top_background_color, Integer.MIN_VALUE);
            if (backgroundColor != Integer.MIN_VALUE) {
                icon.backgroundColor(backgroundColor);
            }
            int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_top_corner_radius, -1);
            if (cornerRadius != -1) {
                icon.roundedCornersPx(cornerRadius);
            }
        }
        return icon;
    }

    public static IconicsDrawable readIconicsTextViewEnd(Context ctx, TypedArray a, IconicsDrawable icon) {
        String i = a.getString(C0973R.styleable.IconicsTextView_iiv_end_icon);
        if (i != null || isFilled(icon)) {
            if (icon == null) {
                icon = new IconicsDrawable(ctx, i);
            } else {
                icon = icon.clone();
                if (i != null) {
                    icon.icon(i);
                }
            }
            int color = a.getColor(C0973R.styleable.IconicsTextView_iiv_end_color, Integer.MIN_VALUE);
            if (color != Integer.MIN_VALUE) {
                icon.color(color);
            }
            int size = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_end_size, -1);
            if (size != -1) {
                icon.sizePx(size);
            }
            int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_end_padding, -1);
            if (padding != -1) {
                icon.paddingPx(padding);
            }
            int contourColor = a.getColor(C0973R.styleable.IconicsTextView_iiv_end_contour_color, Integer.MIN_VALUE);
            if (contourColor != Integer.MIN_VALUE) {
                icon.contourColor(contourColor);
            }
            int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_end_contour_width, -1);
            if (contourWidth != -1) {
                icon.contourWidthPx(contourWidth);
            }
            int backgroundColor = a.getColor(C0973R.styleable.IconicsTextView_iiv_end_background_color, Integer.MIN_VALUE);
            if (backgroundColor != Integer.MIN_VALUE) {
                icon.backgroundColor(backgroundColor);
            }
            int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_end_corner_radius, -1);
            if (cornerRadius != -1) {
                icon.roundedCornersPx(cornerRadius);
            }
        }
        return icon;
    }

    public static IconicsDrawable readIconicsTextViewBottom(Context ctx, TypedArray a, IconicsDrawable icon) {
        String i = a.getString(C0973R.styleable.IconicsTextView_iiv_bottom_icon);
        if (i != null || isFilled(icon)) {
            if (icon == null) {
                icon = new IconicsDrawable(ctx, i);
            } else {
                icon = icon.clone();
                if (i != null) {
                    icon.icon(i);
                }
            }
            int color = a.getColor(C0973R.styleable.IconicsTextView_iiv_bottom_color, Integer.MIN_VALUE);
            if (color != Integer.MIN_VALUE) {
                icon.color(color);
            }
            int size = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_bottom_size, -1);
            if (size != -1) {
                icon.sizePx(size);
            }
            int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_bottom_padding, -1);
            if (padding != -1) {
                icon.paddingPx(padding);
            }
            int contourColor = a.getColor(C0973R.styleable.IconicsTextView_iiv_bottom_contour_color, Integer.MIN_VALUE);
            if (contourColor != Integer.MIN_VALUE) {
                icon.contourColor(contourColor);
            }
            int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_bottom_contour_width, -1);
            if (contourWidth != -1) {
                icon.contourWidthPx(contourWidth);
            }
            int backgroundColor = a.getColor(C0973R.styleable.IconicsTextView_iiv_bottom_background_color, Integer.MIN_VALUE);
            if (backgroundColor != Integer.MIN_VALUE) {
                icon.backgroundColor(backgroundColor);
            }
            int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsTextView_iiv_bottom_corner_radius, -1);
            if (cornerRadius != -1) {
                icon.roundedCornersPx(cornerRadius);
            }
        }
        return icon;
    }

    public static void readIconicsCompoundButton(Context ctx, TypedArray a, CheckableIconBundle icon) {
        icon.mCheckedIconBundle = readIconicsCompoundButtonChecked(ctx, a, icon.mCheckedIconBundle);
        icon.mUncheckedIconBundle = readIconicsCompoundButtonUnchecked(ctx, a, icon.mUncheckedIconBundle);
    }

    public static IconicsDrawable readIconicsCompoundButtonChecked(Context ctx, TypedArray a, IconicsDrawable icon) {
        String i = a.getString(C0973R.styleable.IconicsCompoundButton_iiv_checked_icon);
        if (i != null || isFilled(icon)) {
            if (icon == null) {
                icon = new IconicsDrawable(ctx, i);
            } else {
                icon = icon.clone();
                if (i != null) {
                    icon.icon(i);
                }
            }
            int color = a.getColor(C0973R.styleable.IconicsCompoundButton_iiv_checked_color, Integer.MIN_VALUE);
            if (color != Integer.MIN_VALUE) {
                icon.color(color);
            }
            int size = a.getDimensionPixelSize(C0973R.styleable.IconicsCompoundButton_iiv_checked_size, -1);
            if (size != -1) {
                icon.sizePx(size);
            }
            int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsCompoundButton_iiv_checked_padding, -1);
            if (padding != -1) {
                icon.paddingPx(padding);
            }
            int contourColor = a.getColor(C0973R.styleable.IconicsCompoundButton_iiv_checked_contour_color, Integer.MIN_VALUE);
            if (contourColor != Integer.MIN_VALUE) {
                icon.contourColor(contourColor);
            }
            int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsCompoundButton_iiv_checked_contour_width, -1);
            if (contourWidth != -1) {
                icon.contourWidthPx(contourWidth);
            }
            int backgroundColor = a.getColor(C0973R.styleable.IconicsCompoundButton_iiv_checked_background_color, Integer.MIN_VALUE);
            if (backgroundColor != Integer.MIN_VALUE) {
                icon.backgroundColor(backgroundColor);
            }
            int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsCompoundButton_iiv_checked_corner_radius, -1);
            if (cornerRadius != -1) {
                icon.roundedCornersPx(cornerRadius);
            }
        }
        return icon;
    }

    public static IconicsDrawable readIconicsCompoundButtonUnchecked(Context ctx, TypedArray a, IconicsDrawable icon) {
        String i = a.getString(C0973R.styleable.IconicsCompoundButton_iiv_unchecked_icon);
        if (i != null || isFilled(icon)) {
            if (icon == null) {
                icon = new IconicsDrawable(ctx, i);
            } else {
                icon = icon.clone();
                if (i != null) {
                    icon.icon(i);
                }
            }
            int color = a.getColor(C0973R.styleable.IconicsCompoundButton_iiv_unchecked_color, Integer.MIN_VALUE);
            if (color != Integer.MIN_VALUE) {
                icon.color(color);
            }
            int size = a.getDimensionPixelSize(C0973R.styleable.IconicsCompoundButton_iiv_unchecked_size, -1);
            if (size != -1) {
                icon.sizePx(size);
            }
            int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsCompoundButton_iiv_unchecked_padding, -1);
            if (padding != -1) {
                icon.paddingPx(padding);
            }
            int contourColor = a.getColor(C0973R.styleable.IconicsCompoundButton_iiv_unchecked_contour_color, Integer.MIN_VALUE);
            if (contourColor != Integer.MIN_VALUE) {
                icon.contourColor(contourColor);
            }
            int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsCompoundButton_iiv_unchecked_contour_width, -1);
            if (contourWidth != -1) {
                icon.contourWidthPx(contourWidth);
            }
            int backgroundColor = a.getColor(C0973R.styleable.IconicsCompoundButton_iiv_unchecked_background_color, Integer.MIN_VALUE);
            if (backgroundColor != Integer.MIN_VALUE) {
                icon.backgroundColor(backgroundColor);
            }
            int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsCompoundButton_iiv_unchecked_corner_radius, -1);
            if (cornerRadius != -1) {
                icon.roundedCornersPx(cornerRadius);
            }
        }
        return icon;
    }

    public static void readIconicsCheckableTextView(Context ctx, TypedArray a, CompoundIconsBundle bundle) {
        IconicsDrawable allIconBundle = readIconicsCheckableTextViewAll(a, new IconicsDrawable(ctx));
        bundle.mStartIconBundle = readIconicsCheckableTextViewStart(ctx, a, allIconBundle);
        bundle.mTopIconBundle = readIconicsCheckableTextViewTop(ctx, a, allIconBundle);
        bundle.mEndIconBundle = readIconicsCheckableTextViewEnd(ctx, a, allIconBundle);
        bundle.mBottomIconBundle = readIconicsCheckableTextViewBottom(ctx, a, allIconBundle);
    }

    public static IconicsDrawable readIconicsCheckableTextViewAll(TypedArray a, IconicsDrawable icon) {
        String i = a.getString(C0973R.styleable.IconicsCheckableTextView_iiv_all_checked_icon);
        if (i != null) {
            icon.icon(i);
        }
        int color = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_all_checked_color, Integer.MIN_VALUE);
        if (color != Integer.MIN_VALUE) {
            icon.color(color);
        }
        int size = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_all_checked_size, -1);
        if (size != -1) {
            icon.sizePx(size);
        }
        int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_all_checked_padding, -1);
        if (padding != -1) {
            icon.paddingPx(padding);
        }
        int contourColor = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_all_checked_contour_color, Integer.MIN_VALUE);
        if (contourColor != Integer.MIN_VALUE) {
            icon.contourColor(contourColor);
        }
        int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_all_checked_contour_width, -1);
        if (contourWidth != -1) {
            icon.contourWidthPx(contourWidth);
        }
        int backgroundColor = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_all_checked_background_color, Integer.MIN_VALUE);
        if (backgroundColor != Integer.MIN_VALUE) {
            icon.backgroundColor(backgroundColor);
        }
        int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_all_checked_corner_radius, -1);
        if (cornerRadius != -1) {
            icon.roundedCornersPx(cornerRadius);
        }
        return icon;
    }

    public static IconicsDrawable readIconicsCheckableTextViewStart(Context ctx, TypedArray a, IconicsDrawable icon) {
        String i = Utils.getString(a, C0973R.styleable.IconicsCheckableTextView_iiv_all_checked_icon, C0973R.styleable.IconicsCheckableTextView_iiv_start_checked_icon);
        if (i != null || isFilled(icon)) {
            if (icon == null) {
                icon = new IconicsDrawable(ctx, i);
            } else {
                icon = icon.clone();
                if (i != null) {
                    icon.icon(i);
                }
            }
            int color = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_start_checked_color, Integer.MIN_VALUE);
            if (color != Integer.MIN_VALUE) {
                icon.color(color);
            }
            int size = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_start_checked_size, -1);
            if (size != -1) {
                icon.sizePx(size);
            }
            int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_start_checked_padding, -1);
            if (padding != -1) {
                icon.paddingPx(padding);
            }
            int contourColor = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_start_checked_contour_color, Integer.MIN_VALUE);
            if (contourColor != Integer.MIN_VALUE) {
                icon.contourColor(contourColor);
            }
            int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_start_checked_contour_width, -1);
            if (contourWidth != -1) {
                icon.contourWidthPx(contourWidth);
            }
            int backgroundColor = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_start_checked_background_color, Integer.MIN_VALUE);
            if (backgroundColor != Integer.MIN_VALUE) {
                icon.backgroundColor(backgroundColor);
            }
            int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_start_checked_corner_radius, -1);
            if (cornerRadius != -1) {
                icon.roundedCornersPx(cornerRadius);
            }
        }
        return icon;
    }

    public static IconicsDrawable readIconicsCheckableTextViewTop(Context ctx, TypedArray a, IconicsDrawable icon) {
        String i = Utils.getString(a, C0973R.styleable.IconicsCheckableTextView_iiv_all_checked_icon, C0973R.styleable.IconicsCheckableTextView_iiv_top_checked_icon);
        if (i != null || isFilled(icon)) {
            if (icon == null) {
                icon = new IconicsDrawable(ctx, i);
            } else {
                icon = icon.clone();
                if (i != null) {
                    icon.icon(i);
                }
            }
            int color = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_top_checked_color, Integer.MIN_VALUE);
            if (color != Integer.MIN_VALUE) {
                icon.color(color);
            }
            int size = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_top_checked_size, -1);
            if (size != -1) {
                icon.sizePx(size);
            }
            int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_top_checked_padding, -1);
            if (padding != -1) {
                icon.paddingPx(padding);
            }
            int contourColor = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_top_checked_contour_color, Integer.MIN_VALUE);
            if (contourColor != Integer.MIN_VALUE) {
                icon.contourColor(contourColor);
            }
            int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_top_checked_contour_width, -1);
            if (contourWidth != -1) {
                icon.contourWidthPx(contourWidth);
            }
            int backgroundColor = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_top_checked_background_color, Integer.MIN_VALUE);
            if (backgroundColor != Integer.MIN_VALUE) {
                icon.backgroundColor(backgroundColor);
            }
            int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_top_checked_corner_radius, -1);
            if (cornerRadius != -1) {
                icon.roundedCornersPx(cornerRadius);
            }
        }
        return icon;
    }

    public static IconicsDrawable readIconicsCheckableTextViewEnd(Context ctx, TypedArray a, IconicsDrawable icon) {
        String i = Utils.getString(a, C0973R.styleable.IconicsCheckableTextView_iiv_all_checked_icon, C0973R.styleable.IconicsCheckableTextView_iiv_end_checked_icon);
        if (i != null || isFilled(icon)) {
            if (icon == null) {
                icon = new IconicsDrawable(ctx, i);
            } else {
                icon = icon.clone();
                if (i != null) {
                    icon.icon(i);
                }
            }
            int color = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_end_checked_color, Integer.MIN_VALUE);
            if (color != Integer.MIN_VALUE) {
                icon.color(color);
            }
            int size = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_end_checked_size, -1);
            if (size != -1) {
                icon.sizePx(size);
            }
            int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_end_checked_padding, -1);
            if (padding != -1) {
                icon.paddingPx(padding);
            }
            int contourColor = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_end_checked_contour_color, Integer.MIN_VALUE);
            if (contourColor != Integer.MIN_VALUE) {
                icon.contourColor(contourColor);
            }
            int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_end_checked_contour_width, -1);
            if (contourWidth != -1) {
                icon.contourWidthPx(contourWidth);
            }
            int backgroundColor = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_end_checked_background_color, Integer.MIN_VALUE);
            if (backgroundColor != Integer.MIN_VALUE) {
                icon.backgroundColor(backgroundColor);
            }
            int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_end_checked_corner_radius, -1);
            if (cornerRadius != -1) {
                icon.roundedCornersPx(cornerRadius);
            }
        }
        return icon;
    }

    public static IconicsDrawable readIconicsCheckableTextViewBottom(Context ctx, TypedArray a, IconicsDrawable icon) {
        String i = Utils.getString(a, C0973R.styleable.IconicsCheckableTextView_iiv_all_checked_icon, C0973R.styleable.IconicsCheckableTextView_iiv_bottom_checked_icon);
        if (i != null || isFilled(icon)) {
            if (icon == null) {
                icon = new IconicsDrawable(ctx, i);
            } else {
                icon = icon.clone();
                if (i != null) {
                    icon.icon(i);
                }
            }
            int color = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_bottom_checked_color, Integer.MIN_VALUE);
            if (color != Integer.MIN_VALUE) {
                icon.color(color);
            }
            int size = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_bottom_checked_size, -1);
            if (size != -1) {
                icon.sizePx(size);
            }
            int padding = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_bottom_checked_padding, -1);
            if (padding != -1) {
                icon.paddingPx(padding);
            }
            int contourColor = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_bottom_checked_contour_color, Integer.MIN_VALUE);
            if (contourColor != Integer.MIN_VALUE) {
                icon.contourColor(contourColor);
            }
            int contourWidth = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_bottom_checked_contour_width, -1);
            if (contourWidth != -1) {
                icon.contourWidthPx(contourWidth);
            }
            int backgroundColor = a.getColor(C0973R.styleable.IconicsCheckableTextView_iiv_bottom_checked_background_color, Integer.MIN_VALUE);
            if (backgroundColor != Integer.MIN_VALUE) {
                icon.backgroundColor(backgroundColor);
            }
            int cornerRadius = a.getDimensionPixelSize(C0973R.styleable.IconicsCheckableTextView_iiv_bottom_checked_corner_radius, -1);
            if (cornerRadius != -1) {
                icon.roundedCornersPx(cornerRadius);
            }
        }
        return icon;
    }

    private static boolean isFilled(IconicsDrawable icon) {
        return (icon.getIcon() == null && TextUtils.isEmpty(icon.getPlainIcon())) ? false : true;
    }
}
