package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdUpdateListener;
import java.util.Collection;
import java.util.HashSet;

/* renamed from: com.applovin.impl.sdk.o */
class C0510o implements AppLovinAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdServiceImpl f949a;
    /* renamed from: b */
    private final C0511p f950b;

    private C0510o(AppLovinAdServiceImpl appLovinAdServiceImpl, C0511p c0511p) {
        this.f949a = appLovinAdServiceImpl;
        this.f950b = c0511p;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        synchronized (this.f950b.f952b) {
            if (this.f949a.m452a(this.f950b.f951a.m1133a())) {
                long b = this.f949a.m454b(this.f950b.f951a.m1133a());
                if (b > 0) {
                    this.f950b.f954d = (b * 1000) + System.currentTimeMillis();
                } else if (b == 0) {
                    this.f950b.f954d = Long.MAX_VALUE;
                }
                this.f950b.f953c = appLovinAd;
            } else {
                this.f950b.f953c = null;
                this.f950b.f954d = 0;
            }
            Collection<AppLovinAdLoadListener> hashSet = new HashSet(this.f950b.f957g);
            this.f950b.f957g.clear();
            Collection<AppLovinAdUpdateListener> hashSet2 = new HashSet(this.f950b.f956f);
            this.f950b.f955e = false;
        }
        this.f949a.m456b(this.f950b.f951a);
        for (AppLovinAdLoadListener adReceived : hashSet) {
            try {
                adReceived.adReceived(appLovinAd);
            } catch (Throwable th) {
                this.f949a.f399b.mo2290e("AppLovinAdService", "Unable to notify listener about a newly loaded ad", th);
            }
        }
        for (AppLovinAdUpdateListener adUpdated : hashSet2) {
            try {
                adUpdated.adUpdated(appLovinAd);
            } catch (Throwable th2) {
                this.f949a.f399b.mo2290e("AppLovinAdService", "Unable to notify listener about an updated loaded ad", th2);
            }
        }
    }

    public void failedToReceiveAd(int i) {
        synchronized (this.f950b.f952b) {
            Collection<AppLovinAdLoadListener> hashSet = new HashSet(this.f950b.f957g);
            this.f950b.f957g.clear();
            this.f950b.f955e = false;
        }
        for (AppLovinAdLoadListener failedToReceiveAd : hashSet) {
            try {
                failedToReceiveAd.failedToReceiveAd(i);
            } catch (Throwable th) {
                this.f949a.f399b.mo2290e("AppLovinAdService", "Unable to notify listener about ad load failure", th);
            }
        }
    }
}
