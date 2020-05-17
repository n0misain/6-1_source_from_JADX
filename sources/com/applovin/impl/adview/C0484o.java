package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

/* renamed from: com.applovin.impl.adview.o */
class C0484o implements AppLovinAdLoadListener, AppLovinAdUpdateListener {
    /* renamed from: a */
    private final WeakReference f351a;
    /* renamed from: b */
    private final AppLovinAdService f352b;
    /* renamed from: c */
    private final AppLovinLogger f353c;

    C0484o(AdViewControllerImpl adViewControllerImpl, AppLovinSdk appLovinSdk) {
        if (adViewControllerImpl == null) {
            throw new IllegalArgumentException("No view specified");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            this.f351a = new WeakReference(adViewControllerImpl);
            this.f353c = appLovinSdk.getLogger();
            this.f352b = appLovinSdk.getAdService();
        }
    }

    public void adReceived(AppLovinAd appLovinAd) {
        AdViewControllerImpl adViewControllerImpl = (AdViewControllerImpl) this.f351a.get();
        if (adViewControllerImpl != null) {
            adViewControllerImpl.m284a(appLovinAd);
        } else {
            this.f353c.userError("AppLovinAdView", "Ad view has been garbage collected by the time an ad was recieved");
        }
    }

    public void adUpdated(AppLovinAd appLovinAd) {
        AdViewControllerImpl adViewControllerImpl = (AdViewControllerImpl) this.f351a.get();
        if (adViewControllerImpl != null) {
            adViewControllerImpl.m284a(appLovinAd);
            return;
        }
        this.f352b.removeAdUpdateListener(this, appLovinAd.getSize());
        this.f353c.userError("AppLovinAdView", "Ad view has been garbage collected by the time an ad was updated");
    }

    public void failedToReceiveAd(int i) {
        AdViewControllerImpl adViewControllerImpl = (AdViewControllerImpl) this.f351a.get();
        if (adViewControllerImpl != null) {
            adViewControllerImpl.m283a(i);
        }
    }

    public String toString() {
        return "[AdViewController listener: " + hashCode() + "]";
    }
}
