package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class ax implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f541a;
    /* renamed from: b */
    final /* synthetic */ at f542b;

    ax(at atVar, AppLovinAd appLovinAd) {
        this.f542b = atVar;
        this.f541a = appLovinAd;
    }

    public void run() {
        this.f542b.f532e.videoPlaybackBegan(this.f541a);
    }
}
