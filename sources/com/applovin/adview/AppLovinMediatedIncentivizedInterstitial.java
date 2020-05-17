package com.applovin.adview;

import android.content.Context;
import com.applovin.impl.sdk.am;
import com.applovin.impl.sdk.bw;
import com.applovin.sdk.AppLovinSdk;

public class AppLovinMediatedIncentivizedInterstitial extends AppLovinIncentivizedInterstitial {
    public AppLovinMediatedIncentivizedInterstitial(Context context) {
        super(context);
    }

    public AppLovinMediatedIncentivizedInterstitial(AppLovinSdk appLovinSdk) {
        super(appLovinSdk);
    }

    public static AppLovinMediatedIncentivizedInterstitial create(Context context) {
        return create(AppLovinSdk.getInstance(context));
    }

    public static AppLovinMediatedIncentivizedInterstitial create(AppLovinSdk appLovinSdk) {
        return new AppLovinMediatedIncentivizedInterstitial(appLovinSdk);
    }

    protected am createIncentivizedAdController(AppLovinSdk appLovinSdk) {
        return new bw(appLovinSdk);
    }
}
