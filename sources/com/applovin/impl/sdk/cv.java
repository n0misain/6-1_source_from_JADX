package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.ArrayList;
import java.util.List;

class cv implements AppLovinNativeAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ cu f699a;

    cv(cu cuVar) {
        this.f699a = cuVar;
    }

    public void onNativeAdsFailedToLoad(int i) {
        if (this.f699a.f696b != null) {
            this.f699a.f696b.onNativeAdsFailedToLoad(i);
        }
    }

    public void onNativeAdsLoaded(List list) {
        if (this.f699a.f696b != null) {
            List arrayList = new ArrayList();
            arrayList.addAll(this.f699a.f695a);
            arrayList.addAll(this.f699a.f697c);
            this.f699a.f696b.onNativeAdsLoaded(arrayList);
        }
    }
}
