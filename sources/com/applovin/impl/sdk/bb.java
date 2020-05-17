package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import java.util.Map;

class bb implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f559a;
    /* renamed from: b */
    final /* synthetic */ Map f560b;
    /* renamed from: c */
    final /* synthetic */ at f561c;

    bb(at atVar, AppLovinAd appLovinAd, Map map) {
        this.f561c = atVar;
        this.f559a = appLovinAd;
        this.f560b = map;
    }

    public void run() {
        this.f561c.f533f.userRewardRejected(this.f559a, this.f560b);
    }
}
