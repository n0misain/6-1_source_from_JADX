package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

/* renamed from: com.applovin.impl.adview.g */
class C0476g implements AppLovinAdDisplayListener {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f341a;

    C0476g(AdViewControllerImpl adViewControllerImpl) {
        this.f341a = adViewControllerImpl;
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
    }

    public void adHidden(AppLovinAd appLovinAd) {
        if (this.f341a.f186y != null) {
            this.f341a.f186y.adHidden(appLovinAd);
        }
    }
}
