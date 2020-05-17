package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.zze;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.StringTokenizer;

@zzzn
public final class zzaiy {
    public static final Handler zzaaH = new Handler(Looper.getMainLooper());
    private static final String zzaaI = AdView.class.getName();
    private static final String zzaaJ = InterstitialAd.class.getName();
    private static final String zzaaK = PublisherAdView.class.getName();
    private static final String zzaaL = PublisherInterstitialAd.class.getName();
    private static final String zzaaM = SearchAdView.class.getName();
    private static final String zzaaN = AdLoader.class.getName();

    public static int zzP(Context context) {
        return DynamiteModule.zzF(context, ModuleDescriptor.MODULE_ID);
    }

    public static int zzQ(Context context) {
        return DynamiteModule.zzE(context, ModuleDescriptor.MODULE_ID);
    }

    public static String zzV(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String string = contentResolver == null ? null : Secure.getString(contentResolver, "android_id");
        if (string == null || zzik()) {
            string = "emulator";
        }
        return zzaR(string);
    }

    @Nullable
    public static String zzW(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        return contentResolver == null ? null : Secure.getString(contentResolver, "android_id");
    }

    public static boolean zzX(Context context) {
        return zze.zzoW().isGooglePlayServicesAvailable(context) == 0;
    }

    public static boolean zzY(Context context) {
        int isGooglePlayServicesAvailable = zze.zzoW().isGooglePlayServicesAvailable(context);
        return isGooglePlayServicesAvailable == 0 || isGooglePlayServicesAvailable == 2;
    }

    public static boolean zzZ(Context context) {
        if (context.getResources().getConfiguration().orientation != 2) {
            return false;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return ((int) (((float) displayMetrics.heightPixels) / displayMetrics.density)) < 600;
    }

    public static int zza(DisplayMetrics displayMetrics, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, displayMetrics);
    }

    @Nullable
    public static String zza(StackTraceElement[] stackTraceElementArr, String str) {
        String className;
        for (int i = 0; i + 1 < stackTraceElementArr.length; i++) {
            StackTraceElement stackTraceElement = stackTraceElementArr[i];
            String className2 = stackTraceElement.getClassName();
            if ("loadAd".equalsIgnoreCase(stackTraceElement.getMethodName()) && (zzaaI.equalsIgnoreCase(className2) || zzaaJ.equalsIgnoreCase(className2) || zzaaK.equalsIgnoreCase(className2) || zzaaL.equalsIgnoreCase(className2) || zzaaM.equalsIgnoreCase(className2) || zzaaN.equalsIgnoreCase(className2))) {
                className = stackTraceElementArr[i + 1].getClassName();
                break;
            }
        }
        className = null;
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
            StringBuilder stringBuilder = new StringBuilder();
            int i2 = 2;
            if (stringTokenizer.hasMoreElements()) {
                CharSequence stringBuilder2;
                stringBuilder.append(stringTokenizer.nextToken());
                while (true) {
                    int i3 = i2 - 1;
                    if (i2 <= 0 || !stringTokenizer.hasMoreElements()) {
                        stringBuilder2 = stringBuilder.toString();
                    } else {
                        stringBuilder.append(".").append(stringTokenizer.nextToken());
                        i2 = i3;
                    }
                }
                stringBuilder2 = stringBuilder.toString();
            }
            if (!(className == null || className.contains(r8))) {
                return className;
            }
        }
        return null;
    }

    public static void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z, zzajb zzajb) {
        if (z) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
            bundle.putString("os", VERSION.RELEASE);
            bundle.putString("api", String.valueOf(VERSION.SDK_INT));
            bundle.putString("appid", applicationContext.getPackageName());
            if (str == null) {
                zze.zzoW();
                str = zze.zzau(context) + ".11020000";
            }
            bundle.putString("js", str);
        }
        Builder appendQueryParameter = new Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", str2);
        for (String str3 : bundle.keySet()) {
            appendQueryParameter.appendQueryParameter(str3, bundle.getString(str3));
        }
        zzajb.zzaN(appendQueryParameter.toString());
    }

    private final void zza(ViewGroup viewGroup, zziv zziv, String str, int i, int i2) {
        if (viewGroup.getChildCount() == 0) {
            Context context = viewGroup.getContext();
            View textView = new TextView(context);
            textView.setGravity(17);
            textView.setText(str);
            textView.setTextColor(i);
            textView.setBackgroundColor(i2);
            View frameLayout = new FrameLayout(context);
            frameLayout.setBackgroundColor(i);
            int zzc = zzc(context, 3);
            frameLayout.addView(textView, new LayoutParams(zziv.widthPixels - zzc, zziv.heightPixels - zzc, 17));
            viewGroup.addView(frameLayout, zziv.widthPixels, zziv.heightPixels);
        }
    }

    public static void zza(boolean z, HttpURLConnection httpURLConnection, @Nullable String str) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setReadTimeout(60000);
        if (str != null) {
            httpURLConnection.setRequestProperty("User-Agent", str);
        }
        httpURLConnection.setUseCaches(false);
    }

    public static String zzaR(String str) {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest.getInstance(CommonUtils.MD5_INSTANCE).update(str.getBytes());
                return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, r1.digest())});
            } catch (NoSuchAlgorithmException e) {
                i++;
            }
        }
        return null;
    }

    @TargetApi(17)
    public static boolean zzaa(Context context) {
        int i;
        int i2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (zzq.zzsa()) {
            defaultDisplay.getRealMetrics(displayMetrics);
            i = displayMetrics.heightPixels;
            i2 = displayMetrics.widthPixels;
        } else {
            try {
                i = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception e) {
                return false;
            }
        }
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.heightPixels == i && displayMetrics.widthPixels == i2;
    }

    public static int zzab(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_width", "dimen", "android");
        return identifier > 0 ? context.getResources().getDimensionPixelSize(identifier) : 0;
    }

    public static int zzb(DisplayMetrics displayMetrics, int i) {
        return Math.round(((float) i) / displayMetrics.density);
    }

    public static int zzc(Context context, int i) {
        return zza(context.getResources().getDisplayMetrics(), i);
    }

    public static int zzd(Context context, int i) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return zzb(displayMetrics, i);
    }

    public static boolean zzik() {
        return Build.DEVICE.startsWith("generic");
    }

    public static boolean zzil() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public final void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        zza(context, null, str2, bundle, true, new zzaiz(this));
    }

    public final void zza(ViewGroup viewGroup, zziv zziv, String str) {
        zza(viewGroup, zziv, str, -16777216, -1);
    }

    public final void zza(ViewGroup viewGroup, zziv zziv, String str, String str2) {
        zzajc.zzaT(str2);
        zza(viewGroup, zziv, str, (int) SupportMenu.CATEGORY_MASK, -16777216);
    }
}
