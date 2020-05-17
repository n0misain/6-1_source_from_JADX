package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

/* renamed from: com.applovin.impl.sdk.d */
class C0498d implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdDisplayListener f714a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f715b;
    /* renamed from: c */
    final /* synthetic */ C0496b f716c;

    C0498d(C0496b c0496b, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd) {
        this.f716c = c0496b;
        this.f714a = appLovinAdDisplayListener;
        this.f715b = appLovinAd;
    }

    public void run() {
        this.f714a.adHidden(this.f715b);
    }
}
