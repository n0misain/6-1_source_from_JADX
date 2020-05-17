package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

/* renamed from: com.applovin.impl.sdk.e */
class C0501e implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdClickListener f826a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f827b;
    /* renamed from: c */
    final /* synthetic */ C0496b f828c;

    C0501e(C0496b c0496b, AppLovinAdClickListener appLovinAdClickListener, AppLovinAd appLovinAd) {
        this.f828c = c0496b;
        this.f826a = appLovinAdClickListener;
        this.f827b = appLovinAd;
    }

    public void run() {
        this.f826a.adClicked(this.f827b);
    }
}
