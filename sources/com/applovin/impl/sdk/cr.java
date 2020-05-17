package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

class cr implements AppLovinNativeAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinNativeAdLoadListener f689a;
    /* renamed from: b */
    final /* synthetic */ cq f690b;

    cr(cq cqVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.f690b = cqVar;
        this.f689a = appLovinNativeAdLoadListener;
    }

    public void onNativeAdsFailedToLoad(int i) {
        this.f690b.m736a(this.f689a, i);
    }

    public void onNativeAdsLoaded(List list) {
        this.f690b.m742a(list, this.f689a);
    }
}
