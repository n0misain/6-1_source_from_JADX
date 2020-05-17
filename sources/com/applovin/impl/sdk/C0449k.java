package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Arrays;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.k */
abstract class C0449k implements bu, AppLovinAd {
    /* renamed from: a */
    protected final C0505i f62a;
    /* renamed from: b */
    protected final JSONObject f63b;
    /* renamed from: c */
    protected final JSONObject f64c;
    /* renamed from: d */
    protected final AppLovinSdk f65d;

    C0449k(C0505i c0505i, JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdk appLovinSdk) {
        if (c0505i == null) {
            throw new IllegalArgumentException("No ad spec specified");
        } else if (jSONObject == null) {
            throw new IllegalArgumentException("No ad object specified");
        } else if (jSONObject2 == null) {
            throw new IllegalArgumentException("No full response object specified");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            this.f62a = c0505i;
            this.f63b = jSONObject;
            this.f64c = jSONObject2;
            this.f65d = appLovinSdk;
        }
    }

    /* renamed from: a */
    private String mo2133a() {
        char[] toCharArray = this.f63b.toString().toCharArray();
        Arrays.sort(toCharArray);
        return new String(toCharArray) + getType() + getSize() + mo2130l();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0449k c0449k = (C0449k) obj;
        if (this.f62a != null) {
            if (!this.f62a.equals(c0449k.f62a)) {
                return false;
            }
        } else if (c0449k.f62a != null) {
            return false;
        }
        return mo2133a().equals(c0449k.mo2133a());
    }

    public long getAdIdNumber() {
        return (long) bt.m613a(this.f63b, "ad_id", -1, this.f65d);
    }

    public AppLovinAdSize getSize() {
        return this.f62a.m1133a();
    }

    public AppLovinAdType getType() {
        return this.f62a.m1134b();
    }

    public int hashCode() {
        return this.f62a.hashCode() + mo2133a().hashCode();
    }

    public boolean isVideoAd() {
        this.f65d.getLogger().mo2289e("AppLovinAdBase", "Attempting to invoke isVideoAd() from base ad class");
        return false;
    }

    /* renamed from: l */
    public String mo2130l() {
        String a = bt.m615a(this.f63b, "clcode", "", this.f65d);
        return AppLovinSdkUtils.isValidString(a) ? a : bt.m615a(this.f64c, "clcode", "", this.f65d);
    }

    /* renamed from: m */
    public C0505i mo2131m() {
        return this.f62a;
    }

    public String toString() {
        return "[" + getClass().getSimpleName() + " #" + getAdIdNumber() + " adType=" + getType() + ", adSize=" + getSize() + ", adObject=" + this.f63b + "]";
    }
}
