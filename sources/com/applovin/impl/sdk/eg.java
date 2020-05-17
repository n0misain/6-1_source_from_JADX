package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.internal.AnalyticsEvents;

class eg implements Runnable {
    /* renamed from: a */
    private final AppLovinSdkImpl f843a;
    /* renamed from: b */
    private final AppLovinLogger f844b;
    /* renamed from: c */
    private final Context f845c;

    eg(AppLovinSdkImpl appLovinSdkImpl) {
        this.f843a = appLovinSdkImpl;
        this.f845c = appLovinSdkImpl.getApplicationContext();
        this.f844b = appLovinSdkImpl.getLogger();
    }

    /* renamed from: a */
    private void m978a(dl dlVar, C0506j c0506j) {
        String str = (String) this.f843a.get(dlVar);
        if (str.length() > 0) {
            for (String fromString : str.split(",")) {
                AppLovinAdSize fromString2 = AppLovinAdSize.fromString(fromString);
                if (fromString2 != null) {
                    this.f843a.m469c().mo2261d(new C0505i(AppLovinAdType.REGULAR, c0506j, fromString2));
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m979a() {
        if (C0519y.m1193a("android.permission.INTERNET", this.f845c)) {
            return true;
        }
        this.f844b.userError("TaskInitializeSdk", "Unable to enable AppLovin SDK: no android.permission.INTERNET");
        return false;
    }

    /* renamed from: b */
    private void m980b() {
        this.f843a.m466a().m993a(new dq(this.f843a), ej.MAIN, 500);
    }

    /* renamed from: b */
    private void m981b(dl dlVar, C0506j c0506j) {
        if (((Boolean) this.f843a.get(dlVar)).booleanValue()) {
            this.f843a.m469c().mo2261d(new C0505i(AppLovinAdType.INCENTIVIZED, c0506j, AppLovinAdSize.INTERSTITIAL));
        }
    }

    /* renamed from: c */
    private void m982c() {
        m978a(dj.f755J, C0506j.DIRECT);
        m978a(dj.f756K, C0506j.INDIRECT);
        m981b(dj.f757L, C0506j.DIRECT);
        m981b(dj.f758M, C0506j.INDIRECT);
        m983d();
    }

    /* renamed from: d */
    private void m983d() {
        if (((Boolean) this.f843a.get(dj.aX)).booleanValue()) {
            this.f843a.m470d().mo2261d(C0505i.f933h);
        }
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f844b.mo2288d("TaskInitializeSdk", "Initializing AppLovin SDK 7.0.0...");
        try {
            if (m979a()) {
                C0500do b = this.f843a.m468b();
                b.m883c();
                b.m884c("ad_imp_session");
                C0495a.m510b(this.f843a);
                this.f843a.getFileManager().m534e(this.f845c);
                this.f843a.getFileManager().m533d(this.f845c);
                this.f843a.getMediationService().m503a();
                m982c();
                m980b();
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f845c);
                if (!AppLovinSdkUtils.isValidString(defaultSharedPreferences.getString("com.applovin.sdk.impl.isFirstRun", null))) {
                    defaultSharedPreferences.edit().putString("com.applovin.sdk.impl.isFirstRun", Boolean.toString(true)).commit();
                }
                this.f843a.getPersistentPostbackManager().m760a();
                this.f843a.getEventService().trackEvent("landing");
                this.f843a.m467a(true);
            } else {
                this.f843a.m467a(false);
            }
            this.f844b.mo2288d("TaskInitializeSdk", "AppLovin SDK 7.0.0 initialization " + (this.f843a.isEnabled() ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : "failed") + " in " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        } catch (Throwable th) {
            Throwable th2 = th;
            this.f844b.mo2288d("TaskInitializeSdk", "AppLovin SDK 7.0.0 initialization " + (this.f843a.isEnabled() ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : "failed") + " in " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        }
    }
}
