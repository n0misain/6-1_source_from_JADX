package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class au implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f534a;
    /* renamed from: b */
    final /* synthetic */ int f535b;
    /* renamed from: c */
    final /* synthetic */ at f536c;

    au(at atVar, AppLovinAd appLovinAd, int i) {
        this.f536c = atVar;
        this.f534a = appLovinAd;
        this.f535b = i;
    }

    public void run() {
        this.f536c.f533f.validationRequestFailed(this.f534a, this.f535b);
    }
}
