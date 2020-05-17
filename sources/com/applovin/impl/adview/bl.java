package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class bl implements AppLovinAdClickListener {
    /* renamed from: a */
    final /* synthetic */ bt f267a;
    /* renamed from: b */
    final /* synthetic */ bj f268b;

    bl(bj bjVar, bt btVar) {
        this.f268b = bjVar;
        this.f267a = btVar;
    }

    public void adClicked(AppLovinAd appLovinAd) {
        if (this.f268b.f260j.m889b() == -1) {
            this.f268b.f260j.m890b(System.currentTimeMillis() - this.f268b.f261k);
        }
        AppLovinAdClickListener e = this.f267a.m361e();
        if (e != null) {
            e.adClicked(appLovinAd);
        }
    }
}
