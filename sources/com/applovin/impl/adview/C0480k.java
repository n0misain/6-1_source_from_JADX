package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

/* renamed from: com.applovin.impl.adview.k */
class C0480k implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f346a;
    /* renamed from: b */
    private final AppLovinAd f347b;

    public C0480k(AdViewControllerImpl adViewControllerImpl, AppLovinAd appLovinAd) {
        this.f346a = adViewControllerImpl;
        this.f347b = appLovinAd;
    }

    public void run() {
        AppLovinAdDisplayListener j = this.f346a.f186y;
        if (j != null && this.f347b != null) {
            try {
                j.adHidden(this.f347b);
            } catch (Throwable th) {
                this.f346a.f166e.userError("AppLovinAdView", "Exception while notifying ad display listener", th);
            }
        }
    }
}
