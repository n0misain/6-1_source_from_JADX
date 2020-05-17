package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.Map;
import org.json.JSONObject;

class ef extends ed {
    /* renamed from: a */
    private int f841a;
    /* renamed from: b */
    private final AppLovinNativeAdLoadListener f842b;

    public ef(AppLovinSdkImpl appLovinSdkImpl, int i, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super(C0505i.f933h, null, appLovinSdkImpl);
        this.f842b = appLovinNativeAdLoadListener;
        this.f841a = i;
    }

    /* renamed from: a */
    protected di mo2282a(JSONObject jSONObject) {
        return new ev(jSONObject, this.d, this.f842b);
    }

    /* renamed from: a */
    protected String mo2283a(Map map) {
        return C0518x.m1186b("nad", map, this.d);
    }

    /* renamed from: a */
    protected void mo2284a(int i) {
        if (this.f842b != null) {
            this.f842b.onNativeAdsFailedToLoad(i);
        }
    }

    /* renamed from: b */
    protected String mo2285b(Map map) {
        return C0518x.m1190d("nad", map, this.d);
    }

    /* renamed from: c */
    public String mo2281c() {
        return "tFNW";
    }

    /* renamed from: d */
    protected void mo2286d(Map map) {
        map.put("slot_count", Integer.toString(this.f841a));
    }

    /* renamed from: e */
    protected void mo2287e(Map map) {
        ff a = fd.m1052a().m1053a("tFNW");
        if (a != null) {
            map.put("etfw", Long.toString(a.m1056b()));
            map.put("ntfw", a.m1055a());
        }
    }
}
