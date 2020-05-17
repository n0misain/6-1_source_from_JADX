package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

class bu implements AppLovinAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ String f295a;
    /* renamed from: b */
    final /* synthetic */ bt f296b;

    bu(bt btVar, String str) {
        this.f296b = btVar;
        this.f295a = str;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        this.f296b.m352b(appLovinAd);
        this.f296b.showAndRender(appLovinAd, this.f295a);
    }

    public void failedToReceiveAd(int i) {
        this.f296b.m341a(i);
    }
}
