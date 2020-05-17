package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class bk implements AppLovinAdDisplayListener {
    /* renamed from: a */
    final /* synthetic */ bt f265a;
    /* renamed from: b */
    final /* synthetic */ bj f266b;

    bk(bj bjVar, bt btVar) {
        this.f266b = bjVar;
        this.f265a = btVar;
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        super.show();
        if (!this.f266b.f263m) {
            AppLovinAdDisplayListener d = this.f265a.m360d();
            if (d != null) {
                d.adDisplayed(appLovinAd);
            }
            this.f266b.f263m = true;
        }
    }

    public void adHidden(AppLovinAd appLovinAd) {
        this.f266b.f251a.runOnUiThread(this.f266b.f256f);
        AppLovinAdDisplayListener d = this.f265a.m360d();
        if (!(this.f266b.f264n || d == null)) {
            d.adHidden(appLovinAd);
            this.f266b.f264n = true;
        }
        this.f265a.m357a(false);
    }
}
