package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

class cj implements AppLovinAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ ca f652a;
    /* renamed from: b */
    final /* synthetic */ long f653b;
    /* renamed from: c */
    final /* synthetic */ AppLovinAdLoadListener f654c;
    /* renamed from: d */
    final /* synthetic */ bv f655d;
    /* renamed from: e */
    final /* synthetic */ MediationServiceImpl f656e;

    cj(MediationServiceImpl mediationServiceImpl, ca caVar, long j, AppLovinAdLoadListener appLovinAdLoadListener, bv bvVar) {
        this.f656e = mediationServiceImpl;
        this.f652a = caVar;
        this.f653b = j;
        this.f654c = appLovinAdLoadListener;
        this.f655d = bvVar;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        synchronized (this.f656e.f435d) {
            this.f656e.f437f = this.f652a.m662a();
            this.f656e.f436e = System.currentTimeMillis() - this.f653b;
        }
        if (((Boolean) this.f656e.f432a.get(dj.cx)).booleanValue()) {
            this.f656e.f434c.m642a(this.f652a);
        }
        this.f656e.m496a(appLovinAd, this.f654c);
    }

    public void failedToReceiveAd(int i) {
        this.f656e.m495a(this.f655d, i, this.f654c);
    }
}
