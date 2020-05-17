package com.applovin.impl.sdk;

import com.applovin.impl.p000a.C0456g;
import com.applovin.impl.p000a.C0457h;
import com.applovin.impl.p000a.C0463n;
import com.applovin.sdk.AppLovinAdLoadListener;
import org.json.JSONObject;

abstract class eq extends di implements fi {
    /* renamed from: a */
    private final AppLovinAdLoadListener f876a;
    /* renamed from: b */
    private final er f877b;

    eq(C0456g c0456g, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskProcessVastResponse", appLovinSdkImpl);
        if (c0456g == null) {
            throw new IllegalArgumentException("Unable to create TaskProcessVastResponse. No context specified.");
        }
        this.f876a = appLovinAdLoadListener;
        this.f877b = (er) c0456g;
    }

    /* renamed from: a */
    public static eq m1007a(fl flVar, C0456g c0456g, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        return new et(flVar, c0456g, appLovinAdLoadListener, appLovinSdkImpl);
    }

    /* renamed from: a */
    public static eq m1008a(C0505i c0505i, JSONObject jSONObject, JSONObject jSONObject2, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        return new es(c0505i, jSONObject, jSONObject2, appLovinAdLoadListener, appLovinSdkImpl);
    }

    /* renamed from: a */
    void m1009a(C0457h c0457h) {
        this.e.mo2289e(this.c, "Failed to process VAST response due to VAST error code " + c0457h);
        C0463n.m233a(this.f877b, this.f877b.m209e(), this.f876a, c0457h, -6, this.d);
    }

    /* renamed from: a */
    protected void m1010a(fl flVar) {
        int a = this.f877b.m205a();
        this.e.mo2288d(this.c, "Finished parsing XML at depth " + a);
        this.f877b.m1012a(flVar);
        if (C0463n.m239a(flVar)) {
            int intValue = ((Integer) this.d.get(dj.cm)).intValue();
            if (a < intValue) {
                this.e.mo2288d(this.c, "VAST response is wrapper. Resolving...");
                this.d.m466a().m991a(new fb(this.f877b, this.f876a, this.d));
                return;
            }
            this.e.mo2289e(this.c, "Reached beyond max wrapper depth of " + intValue);
            m1009a(C0457h.WRAPPER_LIMIT_REACHED);
        } else if (C0463n.m242b(flVar)) {
            this.e.mo2288d(this.c, "VAST response is inline. Rendering ad...");
            this.d.m466a().m991a(new ew(this.f877b, this.f876a, this.d));
        } else {
            this.e.mo2289e(this.c, "VAST response is an error");
            m1009a(C0457h.NO_WRAPPER_RESPONSE);
        }
    }

    /* renamed from: c */
    public String mo2281c() {
        return "tPVR";
    }
}
