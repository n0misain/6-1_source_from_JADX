package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;

class cs implements AppLovinNativeAdPrecacheListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinNativeAdPrecacheListener f691a;
    /* renamed from: b */
    final /* synthetic */ cq f692b;

    cs(cq cqVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        this.f692b = cqVar;
        this.f691a = appLovinNativeAdPrecacheListener;
    }

    public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        this.f692b.m738a(this.f691a, appLovinNativeAd, i, false);
    }

    public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
        this.f692b.m739a(this.f691a, appLovinNativeAd, false);
        this.f692b.m735a(appLovinNativeAd, this.f691a);
    }

    public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
    }

    public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
    }
}
