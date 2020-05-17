package com.applovin.sdk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import com.applovin.impl.sdk.fk;
import java.io.File;

public class AppLovinSdkUtils {
    public static final String TAG = "AppLovinSdkUtils";

    /* renamed from: a */
    private static boolean m1069a(Context context) {
        Bundle f = m1074f(context);
        return f != null && f.getBoolean("applovin.sdk.test_ads", false);
    }

    /* renamed from: b */
    private static boolean m1070b(Context context) {
        Bundle f = m1074f(context);
        return f != null && f.getBoolean("applovin.sdk.verbose_logging", false);
    }

    /* renamed from: c */
    private static long m1071c(Context context) {
        Bundle f = m1074f(context);
        return f != null ? (long) f.getInt("applovin.sdk.ad_refresh_seconds", -100) : -100;
    }

    /* renamed from: d */
    private static String m1072d(Context context) {
        Bundle f = m1074f(context);
        if (f != null) {
            String string = f.getString("applovin.sdk.auto_preload_ad_sizes");
            if (string != null) {
                return string;
            }
        }
        return AppLovinAdSize.INTERSTITIAL.getLabel();
    }

    public static int dpToPx(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    /* renamed from: e */
    private static String m1073e(Context context) {
        Bundle f = m1074f(context);
        if (f != null) {
            String string = f.getString("applovin.sdk.auto_preload_ad_types");
            if (string != null) {
                return string;
            }
        }
        return AppLovinAdType.REGULAR.getLabel() + "," + AppLovinAdType.INCENTIVIZED.getLabel() + "," + AppLovinAdType.NATIVE.getLabel();
    }

    /* renamed from: f */
    private static Bundle m1074f(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (Throwable e) {
            Log.e(AppLovinLogger.SDK_TAG, "Unable to retrieve application metadata", e);
            return null;
        }
    }

    public static boolean isLocalFile(Uri uri) {
        return uri != null && "file".equalsIgnoreCase(uri.getScheme());
    }

    public static boolean isValidString(String str) {
        return str != null && str.length() > 1;
    }

    public static void openUri(Context context, Uri uri, AppLovinSdk appLovinSdk) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", uri));
        } catch (Throwable th) {
            appLovinSdk.getLogger().mo2290e(TAG, "Unable to open \"" + uri + "\".", th);
        }
    }

    public static void openUrl(Context context, String str, AppLovinSdk appLovinSdk) {
        openUri(context, Uri.parse(str), appLovinSdk);
    }

    public static void recycleImageView(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable != null && (drawable instanceof BitmapDrawable)) {
                ((BitmapDrawable) drawable).getBitmap().recycle();
            }
        }
    }

    public static String retrieveSdkKey(Context context) {
        Bundle f = m1074f(context);
        if (f == null) {
            return null;
        }
        String string = f.getString("applovin.sdk.key");
        return string != null ? string : "";
    }

    public static AppLovinSdkSettings retrieveUserSettings(Context context) {
        AppLovinSdkSettings appLovinSdkSettings = new AppLovinSdkSettings();
        appLovinSdkSettings.setTestAdsEnabled(m1069a(context));
        appLovinSdkSettings.setVerboseLogging(m1070b(context));
        appLovinSdkSettings.setBannerAdRefreshSeconds(m1071c(context));
        appLovinSdkSettings.setAutoPreloadSizes(m1072d(context));
        appLovinSdkSettings.setAutoPreloadTypes(m1073e(context));
        return appLovinSdkSettings;
    }

    public static void safePopulateImageView(Context context, ImageView imageView, int i, int i2) {
        recycleImageView(imageView);
        Bitmap a = fk.m1079a(context, i, i2);
        if (a != null) {
            imageView.setImageBitmap(a);
        }
    }

    public static void safePopulateImageView(ImageView imageView, Bitmap bitmap) {
        recycleImageView(imageView);
        if (imageView != null && bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public static void safePopulateImageView(ImageView imageView, Uri uri, int i) {
        recycleImageView(imageView);
        Bitmap a = fk.m1080a(new File(uri.getPath()), i);
        if (a != null) {
            imageView.setImageBitmap(a);
        }
    }
}
