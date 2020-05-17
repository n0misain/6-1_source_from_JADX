package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

class ce implements Runnable {
    /* renamed from: a */
    final /* synthetic */ bv f635a;
    /* renamed from: b */
    final /* synthetic */ ca f636b;

    ce(ca caVar, bv bvVar) {
        this.f636b = caVar;
        this.f635a = bvVar;
    }

    public void run() {
        this.f636b.m659b(this.f635a);
        if (this.f635a.getType() == AppLovinAdType.REGULAR) {
            if (this.f635a.getSize() == AppLovinAdSize.INTERSTITIAL) {
                this.f636b.f624b.prepareInterstitialAd(this.f636b.f627e, this.f636b.f625c.getApplicationContext());
            }
        } else if (this.f635a.getType() == AppLovinAdType.INCENTIVIZED) {
            this.f636b.f624b.prepareIncentivizedAd(this.f636b.f627e, this.f636b.f625c.getApplicationContext());
        }
    }
}
