package com.mikepenz.iconics.utils;

import android.content.Context;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class GenericsUtil {
    public static String[] getFields(Context ctx) {
        Class rStringClass = resolveRClass(ctx.getPackageName());
        if (rStringClass != null) {
            return getDefinedFonts(ctx, rStringClass.getFields());
        }
        return new String[0];
    }

    private static Class resolveRClass(String packageName) {
        do {
            try {
                return Class.forName(packageName + ".R$string");
            } catch (ClassNotFoundException e) {
                packageName = packageName.contains(".") ? packageName.substring(0, packageName.lastIndexOf(46)) : "";
                if (TextUtils.isEmpty(packageName)) {
                    return null;
                }
            }
        } while (TextUtils.isEmpty(packageName));
        return null;
    }

    private static String[] getDefinedFonts(Context ctx, Field[] fields) {
        ArrayList<String> fieldArray = new ArrayList();
        for (Field field : fields) {
            if (field.getName().contains("define_font_")) {
                fieldArray.add(getStringResourceByName(ctx, field.getName()));
            }
        }
        return (String[]) fieldArray.toArray(new String[fieldArray.size()]);
    }

    private static String getStringResourceByName(Context ctx, String resourceName) {
        int resId = ctx.getResources().getIdentifier(resourceName, "string", ctx.getPackageName());
        if (resId == 0) {
            return "";
        }
        return ctx.getString(resId);
    }
}
