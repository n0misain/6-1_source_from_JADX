package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

class aq implements AppLovinAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ am f522a;
    /* renamed from: b */
    private final AppLovinAdLoadListener f523b;

    aq(am amVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        this.f522a = amVar;
        this.f523b = appLovinAdLoadListener;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        this.f522a.f501c = appLovinAd;
        if (this.f523b != null) {
            this.f522a.f504f.post(new ar(this, appLovinAd));
        }
    }

    public void failedToReceiveAd(int i) {
        if (this.f523b != null) {
            this.f522a.f504f.post(new as(this, i));
        }
    }
}
