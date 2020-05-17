package com.mikepenz.iconics.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.StyleableRes;
import android.util.TypedValue;

public class Utils {
    public static int convertDpToPx(Context context, float dp) {
        return (int) TypedValue.applyDimension(1, dp, context.getResources().getDisplayMetrics());
    }

    public static String getString(TypedArray a, @StyleableRes int index, @StyleableRes int defIndex) {
        if (a.hasValue(index)) {
            return a.getString(index);
        }
        return a.getString(defIndex);
    }

    public static StateListDrawable getCheckableIconStateList(Context ctx, Drawable icon, Drawable checkedIcon) {
        return getCheckableIconStateList(ctx, icon, checkedIcon, true);
    }

    public static StateListDrawable getCheckableIconStateList(Context ctx, Drawable icon, Drawable checkedIcon, boolean animate) {
        StateListDrawable iconStateListDrawable = new StateListDrawable();
        iconStateListDrawable.addState(new int[]{16842912}, checkedIcon);
        iconStateListDrawable.addState(new int[]{-16842912}, icon);
        if (animate) {
            int duration = ctx.getResources().getInteger(17694720);
            iconStateListDrawable.setEnterFadeDuration(duration);
            iconStateListDrawable.setExitFadeDuration(duration);
        }
        return iconStateListDrawable;
    }
}
