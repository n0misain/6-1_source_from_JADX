package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class av implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f537a;
    /* renamed from: b */
    final /* synthetic */ at f538b;

    av(at atVar, AppLovinAd appLovinAd) {
        this.f538b = atVar;
        this.f537a = appLovinAd;
    }

    public void run() {
        this.f538b.f530c.adHidden(this.f537a);
    }
}
