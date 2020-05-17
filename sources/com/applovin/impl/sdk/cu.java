package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

class cu implements AppLovinNativeAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ List f695a;
    /* renamed from: b */
    final /* synthetic */ AppLovinNativeAdLoadListener f696b;
    /* renamed from: c */
    final /* synthetic */ List f697c;
    /* renamed from: d */
    final /* synthetic */ cq f698d;

    cu(cq cqVar, List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, List list2) {
        this.f698d = cqVar;
        this.f695a = list;
        this.f696b = appLovinNativeAdLoadListener;
        this.f697c = list2;
    }

    public void onNativeAdsFailedToLoad(int i) {
        if (this.f696b != null) {
            this.f696b.onNativeAdsFailedToLoad(i);
        }
    }

    public void onNativeAdsLoaded(List list) {
        this.f698d.m741c(this.f695a, new cv(this));
    }
}
