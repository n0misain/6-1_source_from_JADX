package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;

class da implements AppLovinPostbackListener {
    /* renamed from: a */
    final /* synthetic */ db f717a;
    /* renamed from: b */
    final /* synthetic */ cz f718b;

    da(cz czVar, db dbVar) {
        this.f718b = czVar;
        this.f717a = dbVar;
    }

    public void onPostbackFailure(String str, int i) {
        this.f718b.f709b.mo2291i("PersistentPostbackManager", "Failed to submit postback with errorCode " + i + ". Will retry later...  Postback: " + this.f717a);
        this.f718b.m758e(this.f717a);
    }

    public void onPostbackSuccess(String str) {
        this.f718b.m757d(this.f717a);
        this.f718b.f709b.mo2288d("PersistentPostbackManager", "Successfully submitted postback: " + this.f717a);
        this.f718b.m765b();
    }
}
