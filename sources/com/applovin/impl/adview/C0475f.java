package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

/* renamed from: com.applovin.impl.adview.f */
class C0475f implements AppLovinAdClickListener {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f340a;

    C0475f(AdViewControllerImpl adViewControllerImpl) {
        this.f340a = adViewControllerImpl;
    }

    public void adClicked(AppLovinAd appLovinAd) {
        if (this.f340a.f160A != null) {
            this.f340a.f160A.adClicked(appLovinAd);
        }
    }
}
