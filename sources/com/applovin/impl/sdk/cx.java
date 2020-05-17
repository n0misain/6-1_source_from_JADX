package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

class cx implements AppLovinNativeAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinNativeAdLoadListener f702a;
    /* renamed from: b */
    final /* synthetic */ cq f703b;

    cx(cq cqVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.f703b = cqVar;
        this.f702a = appLovinNativeAdLoadListener;
    }

    public void onNativeAdsFailedToLoad(int i) {
        this.f703b.m736a(this.f702a, i);
    }

    public void onNativeAdsLoaded(List list) {
        this.f703b.m737a(this.f702a, list);
    }
}
