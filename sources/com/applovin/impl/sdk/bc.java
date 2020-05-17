package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class bc implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f562a;
    /* renamed from: b */
    final /* synthetic */ int f563b;
    /* renamed from: c */
    final /* synthetic */ at f564c;

    bc(at atVar, AppLovinAd appLovinAd, int i) {
        this.f564c = atVar;
        this.f562a = appLovinAd;
        this.f563b = i;
    }

    public void run() {
        this.f564c.f533f.validationRequestFailed(this.f562a, this.f563b);
    }
}
