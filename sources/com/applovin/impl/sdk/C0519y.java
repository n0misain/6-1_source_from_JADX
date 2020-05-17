package com.applovin.impl.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.applovin.impl.sdk.y */
class C0519y {
    /* renamed from: e */
    private static String f971e;
    /* renamed from: a */
    private final AppLovinSdkImpl f972a;
    /* renamed from: b */
    private final AppLovinLogger f973b;
    /* renamed from: c */
    private final Context f974c;
    /* renamed from: d */
    private final Map f975d;

    C0519y(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f972a = appLovinSdkImpl;
        this.f973b = appLovinSdkImpl.getLogger();
        this.f974c = appLovinSdkImpl.getApplicationContext();
        this.f975d = Collections.synchronizedMap(new HashMap());
    }

    /* renamed from: a */
    private ad m1192a(ad adVar) {
        if (adVar == null) {
            adVar = new ad();
        }
        if (((Boolean) this.f972a.get(dj.bO)).booleanValue()) {
            adVar.f482o = m1197f();
        } else {
            adVar.f482o = null;
        }
        if (((Boolean) this.f972a.get(dj.bN)).booleanValue()) {
            adVar.f481n = m1199h();
        }
        try {
            AudioManager audioManager = (AudioManager) this.f974c.getSystemService("audio");
            if (audioManager != null) {
                adVar.f483p = (int) (((float) audioManager.getStreamVolume(3)) * ((Float) this.f972a.get(dj.bQ)).floatValue());
            }
        } catch (Throwable th) {
            this.f973b.mo2290e("DataCollector", "Unable to collect volume", th);
        }
        if (((Boolean) this.f972a.get(dj.bT)).booleanValue()) {
            if (f971e == null) {
                String k = m1202k();
                if (AppLovinSdkUtils.isValidString(k)) {
                    f971e = k;
                } else {
                    f971e = "";
                }
            }
            if (AppLovinSdkUtils.isValidString(f971e)) {
                adVar.f484q = f971e;
            }
        }
        return adVar;
    }

    /* renamed from: a */
    static boolean m1193a(String str, Context context) {
        if (str == null) {
            throw new IllegalArgumentException("No permission name specified");
        } else if (context != null) {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } else {
            throw new IllegalArgumentException("No context specified");
        }
    }

    /* renamed from: b */
    private String m1195b(String str) {
        int length = str.length();
        int[] iArr = new int[]{11, 12, 10, 3, 2, 1, 15, 10, 15, 14};
        int length2 = iArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = str.charAt(i);
            for (int i2 = length2 - 1; i2 >= 0; i2--) {
                cArr[i] = (char) (cArr[i] ^ iArr[i2]);
            }
        }
        String str2 = new String(cArr);
        return str2 != null ? str2 : "";
    }

    /* renamed from: e */
    private String m1196e() {
        String str = "none";
        try {
            int a = fk.m1077a(this.f974c);
            return a == 1 ? "portrait" : a == 2 ? "landscape" : str;
        } catch (Throwable th) {
            this.f972a.getLogger().mo2290e("DataCollector", "Encountered error while attempting to collect application orientation", th);
            return str;
        }
    }

    /* renamed from: f */
    private ac m1197f() {
        int i = -1;
        try {
            ac acVar = new ac();
            Intent registerReceiver = this.f974c.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver != null ? registerReceiver.getIntExtra("level", -1) : -1;
            int intExtra2 = registerReceiver != null ? registerReceiver.getIntExtra("scale", -1) : -1;
            if (intExtra <= 0 || intExtra2 <= 0) {
                acVar.f467b = -1;
            } else {
                acVar.f467b = (int) ((((float) intExtra) / ((float) intExtra2)) * 100.0f);
            }
            if (registerReceiver != null) {
                i = registerReceiver.getIntExtra("status", -1);
            }
            acVar.f466a = i;
            return acVar;
        } catch (Throwable th) {
            this.f973b.mo2290e("DataCollector", "Unable to collect battery info", th);
            return null;
        }
    }

    /* renamed from: g */
    private double m1198g() {
        return ((double) Math.round((((double) TimeZone.getDefault().getOffset(new Date().getTime())) * 10.0d) / 3600000.0d)) / 10.0d;
    }

    /* renamed from: h */
    private boolean m1199h() {
        try {
            return m1200i() || m1201j();
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: i */
    private boolean m1200i() {
        String str = "lz}$blpz";
        str = Build.TAGS;
        return str != null && str.contains(m1195b("lz}$blpz"));
    }

    /* renamed from: j */
    private boolean m1201j() {
        String[] strArr = new String[]{"&zpz}ld&hyy&Z|yl{|zl{'hyb", "&zk`g&z|", "&zpz}ld&k`g&z|", "&zpz}ld&qk`g&z|", "&mh}h&efjhe&qk`g&z|", "&mh}h&efjhe&k`g&z|", "&zpz}ld&zm&qk`g&z|", "&zpz}ld&k`g&oh`ezhol&z|", "&mh}h&efjhe&z|"};
        for (String b : strArr) {
            if (new File(m1195b(b)).exists()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: k */
    private String m1202k() {
        AtomicReference atomicReference = new AtomicReference();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Handler(this.f974c.getMainLooper()).post(new C0520z(this, atomicReference, countDownLatch));
        try {
            countDownLatch.await(((Long) this.f972a.get(dj.bU)).longValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
        }
        return (String) atomicReference.get();
    }

    /* renamed from: a */
    ad m1203a() {
        Object obj = this.f975d.get(ad.class);
        if (obj != null) {
            return m1192a((ad) obj);
        }
        ad adVar = new ad();
        adVar.f475h = Locale.getDefault();
        adVar.f468a = Build.MODEL;
        adVar.f469b = VERSION.RELEASE;
        adVar.f470c = Build.MANUFACTURER;
        adVar.f472e = VERSION.SDK_INT;
        adVar.f471d = Build.DEVICE;
        adVar.f476i = m1196e();
        adVar.f479l = m1198g();
        if (m1204a("android.permission.READ_PHONE_STATE")) {
            TelephonyManager telephonyManager = (TelephonyManager) this.f974c.getSystemService("phone");
            if (telephonyManager != null) {
                adVar.f473f = telephonyManager.getSimCountryIso().toUpperCase(Locale.ENGLISH);
                String networkOperatorName = telephonyManager.getNetworkOperatorName();
                try {
                    adVar.f474g = URLEncoder.encode(networkOperatorName, HttpRequest.CHARSET_UTF8);
                } catch (UnsupportedEncodingException e) {
                    adVar.f474g = networkOperatorName;
                }
            }
        }
        try {
            adVar.f480m = this.f974c.getPackageManager().getPackageInfo((String) this.f972a.getSettingsManager().m818a(dj.bM), 0).versionCode;
        } catch (Throwable th) {
        }
        try {
            DisplayMetrics displayMetrics = this.f974c.getResources().getDisplayMetrics();
            adVar.f477j = displayMetrics.density;
            adVar.f478k = displayMetrics.densityDpi;
        } catch (Throwable th2) {
        }
        this.f975d.put(ad.class, adVar);
        return adVar;
    }

    /* renamed from: a */
    boolean m1204a(String str) {
        return C0519y.m1193a(str, this.f974c);
    }

    /* renamed from: b */
    ad m1205b() {
        return m1192a(null);
    }

    /* renamed from: c */
    ab m1206c() {
        Object obj = this.f975d.get(ab.class);
        if (obj != null) {
            return (ab) obj;
        }
        ApplicationInfo applicationInfo = this.f974c.getApplicationInfo();
        long lastModified = new File(applicationInfo.sourceDir).lastModified();
        PackageManager packageManager = this.f974c.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(this.f974c.getPackageName(), 0);
        } catch (NameNotFoundException e) {
        }
        ab abVar = new ab();
        abVar.f464c = applicationInfo.packageName;
        abVar.f465d = lastModified;
        abVar.f462a = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
        abVar.f463b = packageInfo != null ? packageInfo.versionName : "";
        this.f975d.put(ab.class, abVar);
        return abVar;
    }

    /* renamed from: d */
    aa m1207d() {
        try {
            Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            if (cls != null) {
                Object invoke = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.f974c});
                if (invoke != null) {
                    Class cls2 = invoke.getClass();
                    Object invoke2 = cls2.getMethod("isLimitAdTrackingEnabled", (Class[]) null).invoke(invoke, (Object[]) null);
                    Object invoke3 = cls2.getMethod("getId", (Class[]) null).invoke(invoke, (Object[]) null);
                    aa aaVar = new aa();
                    String str = (String) invoke3;
                    String str2 = str == null ? "" : str;
                    aaVar.f460a = ((Boolean) invoke2).booleanValue();
                    aaVar.f461b = str2;
                    return aaVar;
                }
            }
        } catch (Throwable e) {
            this.f973b.userError("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", e);
        } catch (Throwable e2) {
            this.f973b.mo2290e("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", e2);
        }
        aa aaVar2 = new aa();
        aaVar2.f461b = "";
        aaVar2.f460a = false;
        return aaVar2;
    }
}
