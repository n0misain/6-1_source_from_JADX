package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.mediation.AppLovinMediationDisplayListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

class cf implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C0496b f637a;
    /* renamed from: b */
    final /* synthetic */ bv f638b;
    /* renamed from: c */
    final /* synthetic */ String f639c;
    /* renamed from: d */
    final /* synthetic */ Activity f640d;
    /* renamed from: e */
    final /* synthetic */ ca f641e;

    cf(ca caVar, C0496b c0496b, bv bvVar, String str, Activity activity) {
        this.f641e = caVar;
        this.f637a = c0496b;
        this.f638b = bvVar;
        this.f639c = str;
        this.f640d = activity;
    }

    public void run() {
        AppLovinMediationDisplayListener cgVar = new cg(this);
        if (this.f638b.getType() == AppLovinAdType.REGULAR) {
            if (this.f638b.getSize() == AppLovinAdSize.INTERSTITIAL) {
                this.f641e.f624b.showInterstitialAd(this.f638b.m623d(), this.f641e.f627e, this.f639c, this.f640d, cgVar);
            } else {
                this.f641e.f626d.mo2289e("MediationAdapterWrapper", "Failed to display " + this.f638b + ": " + this.f638b.getSize() + " is not a supported ad size");
                throw new IllegalArgumentException("Unsupported ad size");
            }
        } else if (this.f638b.getType() == AppLovinAdType.INCENTIVIZED) {
            this.f641e.f624b.showIncentivizedAd(this.f638b.m623d(), this.f641e.f627e, this.f639c, this.f640d, cgVar);
        } else {
            this.f641e.f626d.mo2289e("MediationAdapterWrapper", "Failed to display " + this.f638b + ": " + this.f638b.getType() + " is not a supported ad type");
            throw new IllegalArgumentException("Unsupported ad type");
        }
    }
}
