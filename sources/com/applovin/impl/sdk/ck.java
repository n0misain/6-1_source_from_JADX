package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class ck implements AppLovinAdDisplayListener {
    /* renamed from: a */
    final /* synthetic */ bv f657a;
    /* renamed from: b */
    final /* synthetic */ MediationServiceImpl f658b;

    ck(MediationServiceImpl mediationServiceImpl, bv bvVar) {
        this.f658b = mediationServiceImpl;
        this.f657a = bvVar;
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        this.f658b.m500b(this.f657a);
    }

    public void adHidden(AppLovinAd appLovinAd) {
    }
}
