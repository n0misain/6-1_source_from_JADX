package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinLogger;

/* renamed from: com.applovin.impl.adview.d */
class C0473d implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f336a;
    /* renamed from: b */
    final /* synthetic */ AdViewControllerImpl f337b;

    C0473d(AdViewControllerImpl adViewControllerImpl, AppLovinAd appLovinAd) {
        this.f337b = adViewControllerImpl;
        this.f336a = appLovinAd;
    }

    public void run() {
        try {
            if (this.f337b.f185x != null) {
                this.f337b.f185x.adReceived(this.f336a);
            }
        } catch (Throwable th) {
            this.f337b.f166e.userError(AppLovinLogger.SDK_TAG, "Exception while running app load callback: " + th.getMessage());
        }
    }
}
