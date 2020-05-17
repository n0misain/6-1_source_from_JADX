package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class ax implements AppLovinAdClickListener {
    /* renamed from: a */
    final /* synthetic */ aj f234a;

    ax(aj ajVar) {
        this.f234a = ajVar;
    }

    public void adClicked(AppLovinAd appLovinAd) {
        this.f234a.m63e();
        AppLovinAdClickListener e = this.f234a.f22b.m361e();
        if (e != null) {
            e.adClicked(appLovinAd);
        }
    }
}
