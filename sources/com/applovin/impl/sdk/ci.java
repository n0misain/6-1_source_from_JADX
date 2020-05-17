package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediationAdapter;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinErrorCodes;

class ci extends di {
    /* renamed from: a */
    final /* synthetic */ ca f650a;
    /* renamed from: b */
    private final ch f651b;

    private ci(ca caVar, ch chVar) {
        this.f650a = caVar;
        super("TaskTimeoutMediatedAd", caVar.f625c);
        if (chVar == null) {
            throw new IllegalArgumentException("No loadState specified");
        }
        this.f651b = chVar;
    }

    public void run() {
        this.e.mo2294w(this.c, "Timing out " + this.f651b.f643a + "...");
        AppLovinMediationAdapter d = this.f650a.m670d();
        if (this.f651b.f643a.getType().equals(AppLovinAdType.INCENTIVIZED)) {
            d.processIncentivizedAdLoadTimeout();
        } else {
            d.processInterstitialAdLoadTimeout();
        }
        this.f650a.m652a((int) AppLovinErrorCodes.MEDIATION_ADAPTER_TIMEOUT, this.f651b);
    }
}
