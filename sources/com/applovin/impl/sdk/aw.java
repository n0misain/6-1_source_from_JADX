package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class aw implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f539a;
    /* renamed from: b */
    final /* synthetic */ at f540b;

    aw(at atVar, AppLovinAd appLovinAd) {
        this.f540b = atVar;
        this.f539a = appLovinAd;
    }

    public void run() {
        this.f540b.f531d.adClicked(this.f539a);
    }
}
