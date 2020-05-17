package com.applovin.impl.adview;

import com.applovin.impl.sdk.ae;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class aw implements AppLovinAdDisplayListener {
    /* renamed from: a */
    final /* synthetic */ aj f233a;

    aw(aj ajVar) {
        this.f233a = ajVar;
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        this.f233a.currentAd = (ae) appLovinAd;
        if (!this.f233a.f26f) {
            this.f233a.m44a(appLovinAd);
        }
    }

    public void adHidden(AppLovinAd appLovinAd) {
        this.f233a.m52b(appLovinAd);
    }
}
