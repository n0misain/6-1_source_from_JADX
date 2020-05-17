package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinSdkUtils;

class cp implements AppLovinNativeAdPrecacheListener {
    /* renamed from: a */
    final /* synthetic */ co f686a;

    cp(co coVar) {
        this.f686a = coVar;
    }

    public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        this.f686a.m707b(C0505i.f933h, i);
    }

    public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
        if (!AppLovinSdkUtils.isValidString(appLovinNativeAd.getVideoUrl())) {
            this.f686a.m709c((bu) appLovinNativeAd);
        }
    }

    public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        this.f686a.b.mo2294w("NativeAdPreloadManager", "Video failed to cache during native ad preload. " + i);
        this.f686a.m709c((bu) appLovinNativeAd);
    }

    public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
        this.f686a.m709c((bu) appLovinNativeAd);
    }
}
