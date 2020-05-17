package com.applovin.impl.p000a;

import com.applovin.impl.sdk.fl;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.internal.ServerProtocol;

/* renamed from: com.applovin.impl.a.k */
public class C0460k {
    /* renamed from: a */
    private String f128a;
    /* renamed from: b */
    private String f129b;

    private C0460k() {
    }

    /* renamed from: a */
    public static C0460k m219a(fl flVar, C0460k c0460k, AppLovinSdk appLovinSdk) {
        if (flVar == null) {
            throw new IllegalArgumentException("Unable to create system info. No node specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to create system info. No sdk specified.");
        } else {
            C0460k c0460k2 = c0460k != null ? c0460k : new C0460k();
            try {
                String c;
                if (!AppLovinSdkUtils.isValidString(c0460k2.f128a)) {
                    c = flVar.m1101c();
                    if (AppLovinSdkUtils.isValidString(c)) {
                        c0460k2.f128a = c;
                    }
                }
                if (!AppLovinSdkUtils.isValidString(c0460k2.f129b)) {
                    c = (String) flVar.m1099b().get(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
                    if (AppLovinSdkUtils.isValidString(c)) {
                        c0460k2.f129b = c;
                    }
                }
                return c0460k2;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo2290e("VastSystemInfo", "Error occurred while initializing", th);
                return null;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0460k)) {
            return false;
        }
        C0460k c0460k = (C0460k) obj;
        return (this.f128a == null ? c0460k.f128a != null : !this.f128a.equals(c0460k.f128a)) ? false : this.f129b != null ? this.f129b.equals(c0460k.f129b) : c0460k.f129b == null;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f128a != null ? this.f128a.hashCode() : 0) * 31;
        if (this.f129b != null) {
            i = this.f129b.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "VastSystemInfo{name='" + this.f128a + '\'' + ", version='" + this.f129b + '\'' + '}';
    }
}
