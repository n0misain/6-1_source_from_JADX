package com.applovin.impl.sdk;

import android.content.Context;
import com.applovin.sdk.AppLovinLogger;

abstract class di implements Runnable {
    /* renamed from: c */
    final String f646c;
    /* renamed from: d */
    protected final AppLovinSdkImpl f647d;
    /* renamed from: e */
    final AppLovinLogger f648e;
    /* renamed from: f */
    final Context f649f;

    di(String str, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f647d = appLovinSdkImpl;
        if (str == null) {
            str = getClass().getSimpleName();
        }
        this.f646c = str;
        this.f648e = appLovinSdkImpl.getLogger();
        this.f649f = appLovinSdkImpl.getApplicationContext();
    }

    /* renamed from: a */
    String m676a() {
        return this.f646c;
    }

    /* renamed from: b */
    void mo2280b() {
    }
}
