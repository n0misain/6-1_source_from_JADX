package com.applovin.impl.sdk;

import com.applovin.impl.p000a.C0457h;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdkUtils;
import org.json.JSONObject;

final class es extends eq {
    /* renamed from: a */
    private final JSONObject f878a;

    es(C0505i c0505i, JSONObject jSONObject, JSONObject jSONObject2, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super(new er(c0505i, jSONObject, jSONObject2), appLovinAdLoadListener, appLovinSdkImpl);
        if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("Unable to create TaskProcessSdkVastResponse. No callback specified.");
        }
        this.f878a = jSONObject;
    }

    public void run() {
        this.e.mo2288d(this.c, "Processing SDK JSON response...");
        String a = bt.m615a(this.f878a, "xml", null, this.d);
        if (!AppLovinSdkUtils.isValidString(a)) {
            this.e.mo2289e(this.c, "No VAST response received.");
            m1009a(C0457h.NO_WRAPPER_RESPONSE);
        } else if (a.length() < ((Integer) this.d.get(dj.cp)).intValue()) {
            try {
                m1010a(fm.m1104a(a, this.d));
            } catch (Throwable th) {
                this.e.mo2290e(this.c, "Unable to parse VAST response", th);
                m1009a(C0457h.XML_PARSING);
            }
        } else {
            this.e.mo2289e(this.c, "VAST response is over max length");
            m1009a(C0457h.XML_PARSING);
        }
    }
}
