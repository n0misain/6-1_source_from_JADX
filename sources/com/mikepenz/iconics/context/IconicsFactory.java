package com.mikepenz.iconics.context;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.v7.view.menu.ActionMenuItemView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.Iconics.IconicsBuilder;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.core.C0972R;

class IconicsFactory {
    IconicsFactory() {
    }

    public View onViewCreated(View view, Context context, AttributeSet attrs) {
        if (!(view == null || view.getTag(C0972R.id.iconics_tag_id) == Boolean.TRUE)) {
            onViewCreatedInternal(view, context, attrs);
            view.setTag(C0972R.id.iconics_tag_id, Boolean.TRUE);
        }
        return view;
    }

    void onViewCreatedInternal(View view, final Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a;
            String icon;
            if (view instanceof ActionMenuItemView) {
                a = context.obtainStyledAttributes(attrs, C0972R.styleable.Iconics);
                icon = a.getString(C0972R.styleable.Iconics_ico_icon);
                if (!TextUtils.isEmpty(icon)) {
                    ((ActionMenuItemView) view).setIcon(getDrawable(context, a, icon));
                }
                a.recycle();
            } else if (view instanceof EditText) {
                new IconicsBuilder().ctx(context).on((TextView) view).build();
            } else if (view instanceof TextView) {
                new IconicsBuilder().ctx(context).on((TextView) view).build();
                ((TextView) view).addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    public void afterTextChanged(Editable editable) {
                        Iconics.styleEditable(context, editable);
                    }
                });
            } else if (view instanceof ImageView) {
                a = context.obtainStyledAttributes(attrs, C0972R.styleable.Iconics);
                icon = a.getString(C0972R.styleable.Iconics_ico_icon);
                if (!TextUtils.isEmpty(icon)) {
                    ((ImageView) view).setImageDrawable(getDrawable(context, a, icon));
                }
                a.recycle();
            }
        }
    }

    IconicsDrawable getDrawable(Context context, TypedArray a, String icon) {
        ColorStateList colors = a.getColorStateList(C0972R.styleable.Iconics_ico_color);
        int size = a.getDimensionPixelSize(C0972R.styleable.Iconics_ico_size, -1);
        int offsetX = a.getDimensionPixelSize(C0972R.styleable.Iconics_ico_offset_x, -1);
        int offsetY = a.getDimensionPixelSize(C0972R.styleable.Iconics_ico_offset_y, -1);
        int padding = a.getDimensionPixelSize(C0972R.styleable.Iconics_ico_padding, -1);
        int contourColor = a.getColor(C0972R.styleable.Iconics_ico_contour_color, 0);
        int contourWidth = a.getDimensionPixelSize(C0972R.styleable.Iconics_ico_contour_width, -1);
        int backgroundColor = a.getColor(C0972R.styleable.Iconics_ico_background_color, 0);
        int cornerRadius = a.getDimensionPixelSize(C0972R.styleable.Iconics_ico_corner_radius, -1);
        IconicsDrawable drawable = new IconicsDrawable(context, icon);
        if (colors != null) {
            drawable.color(colors);
        }
        if (size != -1) {
            drawable.sizePx(size);
        }
        if (offsetX != -1) {
            drawable.iconOffsetXPx(offsetX);
        }
        if (offsetY != -1) {
            drawable.iconOffsetYPx(offsetY);
        }
        if (padding != -1) {
            drawable.paddingPx(padding);
        }
        if (contourColor != 0) {
            drawable.contourColor(contourColor);
        }
        if (contourWidth != -1) {
            drawable.contourWidthPx(contourWidth);
        }
        if (backgroundColor != 0) {
            drawable.backgroundColor(backgroundColor);
        }
        if (cornerRadius != -1) {
            drawable.roundedCornersPx(cornerRadius);
        }
        return drawable;
    }
}
