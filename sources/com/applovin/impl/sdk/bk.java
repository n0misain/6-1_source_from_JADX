package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.sdk.AppLovinAdRewardListener;

class bk {
    /* renamed from: a */
    private AppLovinSdkImpl f579a;
    /* renamed from: b */
    private am f580b;
    /* renamed from: c */
    private Activity f581c;
    /* renamed from: d */
    private AppLovinAdRewardListener f582d;
    /* renamed from: e */
    private Runnable f583e;

    private bk() {
    }

    /* renamed from: a */
    bf m596a() {
        return new bf();
    }

    /* renamed from: a */
    bk m597a(Activity activity) {
        this.f581c = activity;
        return this;
    }

    /* renamed from: a */
    bk m598a(AppLovinSdkImpl appLovinSdkImpl) {
        this.f579a = appLovinSdkImpl;
        return this;
    }

    /* renamed from: a */
    bk m599a(am amVar) {
        this.f580b = amVar;
        return this;
    }

    /* renamed from: a */
    bk m600a(AppLovinAdRewardListener appLovinAdRewardListener) {
        this.f582d = appLovinAdRewardListener;
        return this;
    }

    /* renamed from: a */
    bk m601a(Runnable runnable) {
        this.f583e = runnable;
        return this;
    }
}
