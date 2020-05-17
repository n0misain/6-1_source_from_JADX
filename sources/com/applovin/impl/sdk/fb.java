package com.applovin.impl.sdk;

import com.applovin.impl.p000a.C0456g;
import com.applovin.impl.p000a.C0457h;
import com.applovin.impl.p000a.C0463n;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import io.fabric.sdk.android.services.network.HttpRequest;

class fb extends di implements fi {
    /* renamed from: a */
    private C0456g f893a;
    /* renamed from: b */
    private final AppLovinAdLoadListener f894b;

    fb(C0456g c0456g, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskResolveVastWrapper", appLovinSdkImpl);
        this.f894b = appLovinAdLoadListener;
        this.f893a = c0456g;
    }

    /* renamed from: a */
    private void m1045a(int i) {
        this.e.mo2289e(this.c, "Failed to resolve VAST wrapper due to error code " + i);
        if (i == AppLovinErrorCodes.NO_NETWORK) {
            fk.m1087a(this.f894b, this.f893a.m209e(), i, this.d);
        } else {
            C0463n.m233a(this.f893a, this.f893a.m209e(), this.f894b, i == AppLovinErrorCodes.FETCH_AD_TIMEOUT ? C0457h.TIMED_OUT : C0457h.GENERAL_WRAPPER_ERROR, i, this.d);
        }
    }

    /* renamed from: c */
    public String mo2281c() {
        return "tRVW";
    }

    public void run() {
        String a = C0463n.m229a(this.f893a);
        if (AppLovinSdkUtils.isValidString(a)) {
            this.e.mo2288d(this.c, "Resolving VAST ad with depth " + this.f893a.m205a() + " at " + a);
            try {
                di fcVar = new fc(this, HttpRequest.METHOD_GET, fl.f910a, "RepeatResolveVastWrapper", this.d);
                fcVar.m797a(a);
                fcVar.m800b(((Integer) this.d.get(dj.co)).intValue());
                fcVar.m803c(((Integer) this.d.get(dj.cn)).intValue());
                this.d.m466a().m991a(fcVar);
                return;
            } catch (Throwable th) {
                this.e.mo2290e(this.c, "Unable to resolve VAST wrapper", th);
                m1045a(-1);
                return;
            }
        }
        this.e.mo2289e(this.c, "Resolving VAST failed. Could not find resolution URL");
        m1045a(-1);
    }
}
