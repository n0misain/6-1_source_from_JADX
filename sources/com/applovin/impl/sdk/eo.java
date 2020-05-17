package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import org.json.JSONArray;
import org.json.JSONObject;

class eo extends di implements fi {
    /* renamed from: a */
    private final JSONObject f870a;
    /* renamed from: b */
    private final C0505i f871b;
    /* renamed from: g */
    private final AppLovinAdLoadListener f872g;

    eo(JSONObject jSONObject, C0505i c0505i, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskProcessAdWaterfall", appLovinSdkImpl);
        if (jSONObject == null) {
            throw new IllegalArgumentException("No response specified");
        } else if (c0505i == null) {
            throw new IllegalArgumentException("No ad spec specified");
        } else {
            this.f870a = jSONObject;
            this.f871b = c0505i;
            this.f872g = appLovinAdLoadListener;
        }
    }

    /* renamed from: a */
    private void m996a(int i) {
        fk.m1087a(this.f872g, this.f871b, i, this.d);
    }

    /* renamed from: a */
    private void m999a(AppLovinAd appLovinAd) {
        try {
            if (this.f872g != null) {
                this.f872g.adReceived(appLovinAd);
            }
        } catch (Throwable th) {
            this.e.mo2290e(this.c, "Unable process a ad received notification", th);
        }
    }

    /* renamed from: d */
    private void m1002d() {
        m996a(-6);
    }

    /* renamed from: c */
    public String mo2281c() {
        return "tPAW";
    }

    public void run() {
        try {
            this.e.mo2288d(this.c, "Processing ad response...");
            JSONArray jSONArray = this.f870a.getJSONArray("ads");
            int length = jSONArray.length();
            if (length > 0) {
                this.e.mo2288d(this.c, "Loading the first out of " + length + " ads...");
                this.d.m466a().m991a(new ep(this, 0, jSONArray));
                return;
            }
            this.e.mo2294w(this.c, "No ads were returned from the server");
            m996a((int) AppLovinErrorCodes.NO_FILL);
        } catch (Throwable th) {
            this.e.mo2290e(this.c, "Encountered error while processing ad response", th);
            m1002d();
        }
    }
}
