package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

class cw implements AppLovinNativeAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinNativeAdLoadListener f700a;
    /* renamed from: b */
    final /* synthetic */ cq f701b;

    cw(cq cqVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.f701b = cqVar;
        this.f700a = appLovinNativeAdLoadListener;
    }

    public void onNativeAdsFailedToLoad(int i) {
        if (this.f700a != null) {
            this.f700a.onNativeAdsFailedToLoad(i);
        }
    }

    public void onNativeAdsLoaded(List list) {
        if (this.f700a != null) {
            this.f700a.onNativeAdsLoaded(list);
        }
    }
}
