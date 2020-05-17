package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinSdk;

public class dp {
    /* renamed from: a */
    private final C0500do f809a;

    public dp(AppLovinSdkImpl appLovinSdkImpl) {
        this.f809a = appLovinSdkImpl.m468b();
    }

    public dp(AppLovinSdk appLovinSdk) {
        this.f809a = ((AppLovinSdkImpl) appLovinSdk).m468b();
    }

    /* renamed from: a */
    public void m886a() {
        this.f809a.m878a("ad_imp");
        this.f809a.m878a("ad_imp_session");
    }

    /* renamed from: a */
    public void m887a(long j) {
        this.f809a.m882b("ad_dismiss_duration", j);
    }

    /* renamed from: a */
    public void m888a(AppLovinAd appLovinAd) {
        this.f809a.m882b("last_displayed_ad_id_number", appLovinAd.getAdIdNumber());
    }

    /* renamed from: b */
    public long m889b() {
        return this.f809a.m880b("ad_time_to_click_through");
    }

    /* renamed from: b */
    public void m890b(long j) {
        this.f809a.m882b("ad_time_to_click_through", j);
    }

    /* renamed from: c */
    public void m891c(long j) {
        this.f809a.m882b("ad_paused_duration", j);
    }
}
