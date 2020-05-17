package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

/* renamed from: com.applovin.impl.adview.h */
class C0477h implements AppLovinAdVideoPlaybackListener {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f342a;

    C0477h(AdViewControllerImpl adViewControllerImpl) {
        this.f342a = adViewControllerImpl;
    }

    public void videoPlaybackBegan(AppLovinAd appLovinAd) {
        if (this.f342a.f187z != null) {
            this.f342a.f187z.videoPlaybackBegan(appLovinAd);
        }
    }

    public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
        if (this.f342a.f187z != null) {
            this.f342a.f187z.videoPlaybackEnded(appLovinAd, d, z);
        }
    }
}
