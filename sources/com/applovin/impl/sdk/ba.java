package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import java.util.Map;

class ba implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f556a;
    /* renamed from: b */
    final /* synthetic */ Map f557b;
    /* renamed from: c */
    final /* synthetic */ at f558c;

    ba(at atVar, AppLovinAd appLovinAd, Map map) {
        this.f558c = atVar;
        this.f556a = appLovinAd;
        this.f557b = map;
    }

    public void run() {
        this.f558c.f533f.userOverQuota(this.f556a, this.f557b);
    }
}
