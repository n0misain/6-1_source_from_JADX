package com.applovin.impl.sdk;

import org.json.JSONObject;

public class en extends di implements fi {
    /* renamed from: a */
    private final C0505i f867a;
    /* renamed from: b */
    private final JSONObject f868b;
    /* renamed from: g */
    private final JSONObject f869g;

    en(C0505i c0505i, JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskLoadAdapterAd", appLovinSdkImpl);
        if (c0505i == null) {
            throw new IllegalArgumentException("No ad spec specified");
        } else if (jSONObject == null) {
            throw new IllegalArgumentException("No ad object specified");
        } else if (jSONObject2 == null) {
            throw new IllegalArgumentException("No full ad response specified");
        } else {
            this.f867a = c0505i;
            this.f868b = jSONObject;
            this.f869g = jSONObject2;
        }
    }

    /* renamed from: c */
    public String mo2281c() {
        return "tPAA";
    }

    public void run() {
        try {
            this.d.getMediationService().m504a(new bv(this.f867a, this.f868b, this.f869g, this.d));
        } catch (Throwable th) {
            this.e.mo2290e(this.c, "Unable to prepare adapter ad", th);
        }
    }
}
