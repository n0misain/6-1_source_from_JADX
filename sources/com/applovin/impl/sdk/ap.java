package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.util.Map;

class ap implements AppLovinAdRewardListener {
    /* renamed from: a */
    final /* synthetic */ am f521a;

    ap(am amVar) {
        this.f521a = amVar;
    }

    public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
        this.f521a.f499a.getLogger().mo2288d("IncentivizedAdController", "User declined to view");
    }

    public void userOverQuota(AppLovinAd appLovinAd, Map map) {
        this.f521a.f499a.getLogger().mo2288d("IncentivizedAdController", "User over quota: " + map);
    }

    public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
        this.f521a.f499a.getLogger().mo2288d("IncentivizedAdController", "Reward rejected: " + map);
    }

    public void userRewardVerified(AppLovinAd appLovinAd, Map map) {
        this.f521a.f499a.getLogger().mo2288d("IncentivizedAdController", "Reward validated: " + map);
    }

    public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
        this.f521a.f499a.getLogger().mo2288d("IncentivizedAdController", "Reward validation failed: " + i);
    }
}
