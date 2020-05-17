package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;

class bw implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f301a;
    /* renamed from: b */
    final /* synthetic */ bt f302b;

    bw(bt btVar, AppLovinAd appLovinAd) {
        this.f302b = btVar;
        this.f301a = appLovinAd;
    }

    public void run() {
        if (this.f302b.f287h != null) {
            this.f302b.f287h.adReceived(this.f301a);
        }
    }
}
