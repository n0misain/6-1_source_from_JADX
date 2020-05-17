package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;

class ct implements AppLovinNativeAdPrecacheListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinNativeAdPrecacheListener f693a;
    /* renamed from: b */
    final /* synthetic */ cq f694b;

    ct(cq cqVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        this.f694b = cqVar;
        this.f693a = appLovinNativeAdPrecacheListener;
    }

    public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
    }

    public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
    }

    public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        this.f694b.m738a(this.f693a, appLovinNativeAd, i, true);
    }

    public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
        this.f694b.m739a(this.f693a, appLovinNativeAd, true);
    }
}
