package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import java.util.Map;

class az implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f547a;
    /* renamed from: b */
    final /* synthetic */ Map f548b;
    /* renamed from: c */
    final /* synthetic */ at f549c;

    az(at atVar, AppLovinAd appLovinAd, Map map) {
        this.f549c = atVar;
        this.f547a = appLovinAd;
        this.f548b = map;
    }

    public void run() {
        this.f549c.f533f.userRewardVerified(this.f547a, this.f548b);
    }
}
