package com.applovin.sdk;

public interface AppLovinLogger {
    public static final String SDK_TAG = "AppLovinSdk";

    /* renamed from: d */
    void mo2288d(String str, String str2);

    /* renamed from: e */
    void mo2289e(String str, String str2);

    /* renamed from: e */
    void mo2290e(String str, String str2, Throwable th);

    /* renamed from: i */
    void mo2291i(String str, String str2);

    void userError(String str, String str2);

    void userError(String str, String str2, Throwable th);

    /* renamed from: w */
    void mo2294w(String str, String str2);

    /* renamed from: w */
    void mo2295w(String str, String str2, Throwable th);
}
