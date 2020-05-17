package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import org.json.JSONObject;

public class eh extends di implements fi {
    /* renamed from: a */
    private final C0505i f846a;
    /* renamed from: b */
    private final JSONObject f847b;
    /* renamed from: g */
    private final JSONObject f848g;
    /* renamed from: h */
    private final AppLovinAdLoadListener f849h;

    eh(C0505i c0505i, JSONObject jSONObject, JSONObject jSONObject2, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskLoadAdapterAd", appLovinSdkImpl);
        if (c0505i == null) {
            throw new IllegalArgumentException("No ad spec specified");
        } else if (jSONObject == null) {
            throw new IllegalArgumentException("No ad object specified");
        } else if (jSONObject2 == null) {
            throw new IllegalArgumentException("No full ad response specified");
        } else {
            this.f846a = c0505i;
            this.f847b = jSONObject;
            this.f848g = jSONObject2;
            this.f849h = appLovinAdLoadListener;
        }
    }

    /* renamed from: c */
    public String mo2281c() {
        return "tPAA";
    }

    public void run() {
        try {
            this.d.getMediationService().m505a(new bv(this.f846a, this.f847b, this.f848g, this.d), this.f849h);
        } catch (Throwable th) {
            this.e.mo2290e(this.c, "Unable to process adapter ad", th);
            if (this.f849h != null) {
                this.f849h.failedToReceiveAd(AppLovinErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED);
            }
        }
    }
}
