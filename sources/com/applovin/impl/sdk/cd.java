package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediatedAdInfo;
import com.applovin.mediation.AppLovinMediationErrorCode;
import com.applovin.mediation.AppLovinMediationLoadListener;

class cd implements AppLovinMediationLoadListener {
    /* renamed from: a */
    final /* synthetic */ cc f634a;

    cd(cc ccVar) {
        this.f634a = ccVar;
    }

    public void adLoaded(AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        this.f634a.f633c.f626d.mo2288d("MediationAdapterWrapper", "Successfully loaded " + this.f634a.f631a);
        this.f634a.f633c.m656a(appLovinMediatedAdInfo, this.f634a.f632b);
    }

    public void failedToLoadAd(AppLovinMediationErrorCode appLovinMediationErrorCode) {
        if (appLovinMediationErrorCode == null) {
            appLovinMediationErrorCode = AppLovinMediationErrorCode.NETWORK_UNSPECIFIED;
        }
        this.f634a.f633c.f626d.mo2289e("MediationAdapterWrapper", "Failed to load " + this.f634a.f631a + ": " + appLovinMediationErrorCode);
        this.f634a.f633c.m652a(appLovinMediationErrorCode.getErrorCode(), this.f634a.f632b);
    }
}
