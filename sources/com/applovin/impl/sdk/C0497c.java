package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

/* renamed from: com.applovin.impl.sdk.c */
class C0497c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdDisplayListener f620a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f621b;
    /* renamed from: c */
    final /* synthetic */ C0496b f622c;

    C0497c(C0496b c0496b, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd) {
        this.f622c = c0496b;
        this.f620a = appLovinAdDisplayListener;
        this.f621b = appLovinAd;
    }

    public void run() {
        this.f620a.adDisplayed(this.f621b);
    }
}
