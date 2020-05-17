package com.applovin.impl.adview;

import android.app.Activity;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;

class ca extends bt {
    ca(AppLovinSdk appLovinSdk, Activity activity) {
        super(appLovinSdk, activity);
    }

    /* renamed from: a */
    protected void mo2182a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.b.getAdService().loadNextMediatedAd(AppLovinAdSize.INTERSTITIAL, appLovinAdLoadListener);
    }
}
