package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class cl implements AppLovinAdClickListener {
    /* renamed from: a */
    final /* synthetic */ bv f659a;
    /* renamed from: b */
    final /* synthetic */ MediationServiceImpl f660b;

    cl(MediationServiceImpl mediationServiceImpl, bv bvVar) {
        this.f660b = mediationServiceImpl;
        this.f659a = bvVar;
    }

    public void adClicked(AppLovinAd appLovinAd) {
        this.f660b.m502c(this.f659a);
    }
}
