package com.github.paolorotolo.appintro.util;

import android.content.Context;
import android.graphics.Typeface;
import java.util.Hashtable;

public class CustomFontCache {
    private static final String TAG = LogHelper.makeLogTag(CustomFontCache.class);
    private static final Hashtable<String, Typeface> fCache = new Hashtable();

    public static Typeface get(String tfn, Context ctx) {
        Typeface tf = (Typeface) fCache.get(tfn);
        if (tf != null) {
            return tf;
        }
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), tfn);
            if (tf != null) {
                fCache.put(tfn, tf);
            }
            return tf;
        } catch (Exception e) {
            if ("".equals(tfn)) {
                LogHelper.m1214w(TAG, e, "Empty path");
            } else {
                LogHelper.m1214w(TAG, e, tfn);
            }
            return null;
        }
    }
}
