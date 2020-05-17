package com.applovin.impl.sdk;

import android.net.Uri;
import com.applovin.sdk.AppLovinAdLoadListener;

public class dt extends ds {
    /* renamed from: a */
    private final C0503g f815a;
    /* renamed from: b */
    private boolean f816b;

    public dt(C0503g c0503g, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskCacheAppLovinAd", c0503g, appLovinAdLoadListener, appLovinSdkImpl);
        this.f815a = c0503g;
    }

    /* renamed from: e */
    private void m909e() {
        this.e.mo2288d(this.c, "Caching HTML resources...");
        this.f815a.m1120a(m906c(this.f815a.mo2133a()));
        this.e.mo2288d(this.c, "Finish caching non-video resources for ad #" + this.f815a.getAdIdNumber());
        this.e.mo2288d(this.c, "Ad updated with cachedHTML = " + this.f815a.mo2133a());
    }

    /* renamed from: f */
    private void m910f() {
        Uri a = m902a(this.f815a.m1124e());
        if (a != null) {
            this.f815a.m1122c();
            this.f815a.m1119a(a);
        }
    }

    /* renamed from: a */
    public void m911a(boolean z) {
        this.f816b = z;
    }

    public void run() {
        if (this.f815a.mo2134b()) {
            this.e.mo2288d(this.c, "Begin caching for streaming ad #" + this.f815a.getAdIdNumber() + "...");
            m907c();
            if (this.f816b) {
                this.e.mo2288d(this.c, "Calling back ad load immediately");
                m908d();
            }
            m909e();
            if (!this.f816b) {
                this.e.mo2288d(this.c, "Calling back ad load AFTER caching endcard");
                m908d();
            }
            m910f();
            return;
        }
        this.e.mo2288d(this.c, "Begin processing for non-streaming ad #" + this.f815a.getAdIdNumber() + "...");
        m907c();
        m909e();
        m910f();
        this.e.mo2288d(this.c, "Caching finished. Calling back ad load success...");
        m908d();
    }
}
