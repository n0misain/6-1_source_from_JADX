package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class ar implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f524a;
    /* renamed from: b */
    final /* synthetic */ aq f525b;

    ar(aq aqVar, AppLovinAd appLovinAd) {
        this.f525b = aqVar;
        this.f524a = appLovinAd;
    }

    public void run() {
        this.f525b.f523b.adReceived(this.f524a);
    }
}
