package com.applovin.impl.sdk;

import android.util.Log;
import com.applovin.sdk.AppLovinLogger;

/* renamed from: com.applovin.impl.sdk.r */
class C0513r implements AppLovinLogger {
    /* renamed from: a */
    private dm f960a;
    /* renamed from: b */
    private C0514s f961b;

    C0513r() {
    }

    /* renamed from: a */
    void m1144a(dm dmVar) {
        this.f960a = dmVar;
    }

    /* renamed from: a */
    void m1145a(C0514s c0514s) {
        this.f961b = c0514s;
    }

    /* renamed from: a */
    boolean m1146a() {
        return this.f960a != null ? ((Boolean) this.f960a.m818a(dj.f780i)).booleanValue() : false;
    }

    /* renamed from: d */
    public void mo2288d(String str, String str2) {
        if (m1146a()) {
            Log.d(AppLovinLogger.SDK_TAG, "[" + str + "] " + str2);
        }
        if (this.f961b != null) {
            this.f961b.m1153a("DEBUG  [" + str + "] " + str2);
        }
    }

    /* renamed from: e */
    public void mo2289e(String str, String str2) {
        mo2290e(str, str2, null);
    }

    /* renamed from: e */
    public void mo2290e(String str, String str2, Throwable th) {
        if (m1146a()) {
            Log.e(AppLovinLogger.SDK_TAG, "[" + str + "] " + str2, th);
        }
        if (this.f961b != null) {
            this.f961b.m1153a("ERROR  [" + str + "] " + str2 + (th != null ? ": " + th.getMessage() : ""));
        }
    }

    /* renamed from: i */
    public void mo2291i(String str, String str2) {
        if (m1146a()) {
            Log.i(AppLovinLogger.SDK_TAG, "[" + str + "] " + str2);
        }
        if (this.f961b != null) {
            this.f961b.m1153a("INFO  [" + str + "] " + str2);
        }
    }

    public void userError(String str, String str2) {
        userError(str, str2, null);
    }

    public void userError(String str, String str2, Throwable th) {
        Log.e(AppLovinLogger.SDK_TAG, "[" + str + "] " + str2, th);
        if (this.f961b != null) {
            this.f961b.m1153a("USER  [" + str + "] " + str2 + (th != null ? ": " + th.getMessage() : ""));
        }
    }

    /* renamed from: w */
    public void mo2294w(String str, String str2) {
        mo2295w(str, str2, null);
    }

    /* renamed from: w */
    public void mo2295w(String str, String str2, Throwable th) {
        if (m1146a()) {
            Log.w(AppLovinLogger.SDK_TAG, "[" + str + "] " + str2, th);
        }
        if (this.f961b != null) {
            this.f961b.m1153a("WARN  [" + str + "] " + str2);
        }
    }
}
