package com.applovin.impl.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.view.Display;
import android.view.WindowManager;
import com.applovin.adview.AppLovinInterstitialActivity;

/* renamed from: com.applovin.impl.sdk.u */
public class C0516u {
    /* renamed from: a */
    static void m1157a() {
        try {
            if (VERSION.SDK_INT >= 9) {
                StrictMode.setThreadPolicy(new Builder().permitAll().build());
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static boolean m1158a(Context context) {
        boolean z = false;
        try {
            z = fk.m1088a(context.getPackageManager().getActivityInfo(new ComponentName(context, AppLovinInterstitialActivity.class.getCanonicalName()), 0).configChanges, 1024);
        } catch (Throwable th) {
        }
        return z;
    }

    /* renamed from: a */
    public static boolean m1159a(Class cls, Context context) {
        return context.getPackageManager().resolveActivity(new Intent(context, cls), 0) != null;
    }

    /* renamed from: a */
    public static boolean m1160a(String str, Context context) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    /* renamed from: b */
    public static boolean m1161b() {
        return VERSION.SDK_INT >= 15;
    }

    /* renamed from: b */
    public static boolean m1162b(Context context) {
        boolean z = false;
        try {
            z = fk.m1088a(context.getPackageManager().getActivityInfo(new ComponentName(context, AppLovinInterstitialActivity.class.getCanonicalName()), 0).configChanges, 128);
        } catch (Throwable th) {
        }
        return z;
    }

    /* renamed from: c */
    public static Point m1163c(Context context) {
        Point point = new Point();
        point.x = 480;
        point.y = 320;
        try {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            if (VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
            } else {
                point.x = defaultDisplay.getWidth();
                point.y = defaultDisplay.getHeight();
            }
        } catch (Throwable th) {
        }
        return point;
    }

    /* renamed from: c */
    public static boolean m1164c() {
        return VERSION.SDK_INT >= 17;
    }

    /* renamed from: d */
    public static boolean m1165d() {
        return VERSION.SDK_INT >= 19;
    }

    /* renamed from: e */
    public static boolean m1166e() {
        return VERSION.SDK_INT >= 21;
    }
}
