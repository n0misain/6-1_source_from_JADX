package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class ay implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f543a;
    /* renamed from: b */
    final /* synthetic */ double f544b;
    /* renamed from: c */
    final /* synthetic */ boolean f545c;
    /* renamed from: d */
    final /* synthetic */ at f546d;

    ay(at atVar, AppLovinAd appLovinAd, double d, boolean z) {
        this.f546d = atVar;
        this.f543a = appLovinAd;
        this.f544b = d;
        this.f545c = z;
    }

    public void run() {
        this.f546d.f532e.videoPlaybackEnded(this.f543a, this.f544b, this.f545c);
    }
}
