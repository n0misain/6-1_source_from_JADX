package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediationErrorCode;
import com.applovin.mediation.AppLovinMediationLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.concurrent.TimeUnit;

class cc implements Runnable {
    /* renamed from: a */
    final /* synthetic */ bv f631a;
    /* renamed from: b */
    final /* synthetic */ ch f632b;
    /* renamed from: c */
    final /* synthetic */ ca f633c;

    cc(ca caVar, bv bvVar, ch chVar) {
        this.f633c = caVar;
        this.f631a = bvVar;
        this.f632b = chVar;
    }

    public void run() {
        this.f633c.m659b(this.f631a);
        AppLovinMediationLoadListener cdVar = new cd(this);
        if (this.f631a.getType() == AppLovinAdType.REGULAR) {
            if (this.f631a.getSize() == AppLovinAdSize.INTERSTITIAL) {
                this.f633c.f624b.loadInterstitialAd(this.f633c.f627e, this.f633c.f625c.getApplicationContext(), cdVar);
            } else {
                this.f633c.f626d.mo2289e("MediationAdapterWrapper", "Failed to load " + this.f631a + ": " + this.f631a.getSize() + "> is not a supported ad size");
                this.f633c.m652a(AppLovinMediationErrorCode.INTERNAL_AD_SIZE_NOT_SUPPORTED.getErrorCode(), this.f632b);
            }
        } else if (this.f631a.getType() == AppLovinAdType.INCENTIVIZED) {
            this.f633c.f624b.loadIncentivizedAd(this.f633c.f627e, this.f633c.f625c.getApplicationContext(), cdVar);
        } else {
            this.f633c.f626d.mo2289e("MediationAdapterWrapper", "Failed to load " + this.f631a + ": " + this.f631a.getType() + " is not a supported ad type");
            this.f633c.m652a(AppLovinMediationErrorCode.INTERNAL_AD_TYPE_NOT_SUPPORTED.getErrorCode(), this.f632b);
        }
        if (!this.f632b.f645c.get()) {
            if (this.f631a.m625f() == 0) {
                this.f633c.f626d.mo2288d("MediationAdapterWrapper", "Failing ad " + this.f631a + " since it has 0 timeout");
                this.f633c.m652a((int) AppLovinErrorCodes.MEDIATION_ADAPTER_IMMEDIATE_TIMEOUT, this.f632b);
            } else if (this.f631a.m625f() > 0) {
                this.f633c.f626d.mo2288d("MediationAdapterWrapper", "Setting timeout " + this.f631a.m625f() + " sec. for " + this.f631a);
                this.f633c.f625c.m466a().m993a(new ci(this.f633c, this.f632b, null), ej.MAIN, TimeUnit.SECONDS.toMillis((long) this.f631a.m625f()));
            } else {
                this.f633c.f626d.mo2288d("MediationAdapterWrapper", "Negative timeout set for " + this.f631a + ", not scheduling a timeout");
            }
        }
    }
}
