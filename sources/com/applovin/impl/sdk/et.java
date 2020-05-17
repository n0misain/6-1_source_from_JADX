package com.applovin.impl.sdk;

import com.applovin.impl.p000a.C0456g;
import com.applovin.sdk.AppLovinAdLoadListener;

final class et extends eq {
    /* renamed from: a */
    private final fl f879a;

    et(fl flVar, C0456g c0456g, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super(c0456g, appLovinAdLoadListener, appLovinSdkImpl);
        if (flVar == null) {
            throw new IllegalArgumentException("Unable to create TaskProcessVastWrapperResponse. No vast wrapper response specified.");
        } else if (c0456g == null) {
            throw new IllegalArgumentException("Unable to create TaskProcessVastWrapperResponse. No context specified.");
        } else if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("Unable to create TaskProcessVastWrapperResponse. No callback specified.");
        } else {
            this.f879a = flVar;
        }
    }

    public void run() {
        this.e.mo2288d(this.c, "Processing VAST Wrapper response...");
        m1010a(this.f879a);
    }
}
