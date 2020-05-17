package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

/* renamed from: com.applovin.impl.adview.j */
class C0479j implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f344a;
    /* renamed from: b */
    private final AppLovinAd f345b;

    public C0479j(AdViewControllerImpl adViewControllerImpl, AppLovinAd appLovinAd) {
        this.f344a = adViewControllerImpl;
        this.f345b = appLovinAd;
    }

    public void run() {
        AppLovinAdClickListener k = this.f344a.f160A;
        if (k != null && this.f345b != null) {
            try {
                k.adClicked(this.f345b);
            } catch (Throwable th) {
                this.f344a.f166e.userError("AppLovinAdView", "Exception while notifying ad click listener", th);
            }
        }
    }
}
