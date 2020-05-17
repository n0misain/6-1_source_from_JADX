package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.mediation.AppLovinMediatedAdInfo;
import com.applovin.mediation.AppLovinMediationAdapter;
import com.applovin.mediation.AppLovinMediationAdapterConfig;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinLogger;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

class ca {
    /* renamed from: a */
    private final String f623a;
    /* renamed from: b */
    private final AppLovinMediationAdapter f624b;
    /* renamed from: c */
    private final AppLovinSdkImpl f625c;
    /* renamed from: d */
    private final AppLovinLogger f626d;
    /* renamed from: e */
    private bx f627e;
    /* renamed from: f */
    private final AtomicBoolean f628f;

    ca(String str, AppLovinMediationAdapter appLovinMediationAdapter, AppLovinSdkImpl appLovinSdkImpl) {
        if (str == null) {
            throw new IllegalArgumentException("No implementation name specified");
        } else if (appLovinMediationAdapter == null) {
            throw new IllegalArgumentException("No implementation specified");
        } else if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            this.f623a = str;
            this.f624b = appLovinMediationAdapter;
            this.f625c = appLovinSdkImpl;
            this.f626d = appLovinSdkImpl.getLogger();
            this.f627e = new bx(str, appLovinSdkImpl);
            this.f628f = new AtomicBoolean(true);
        }
    }

    /* renamed from: a */
    private void m652a(int i, ch chVar) {
        if (chVar.f645c.compareAndSet(false, true) && chVar.f644b != null) {
            chVar.f644b.failedToReceiveAd(i);
        }
    }

    /* renamed from: a */
    private void m656a(AppLovinMediatedAdInfo appLovinMediatedAdInfo, ch chVar) {
        if (chVar.f645c.compareAndSet(false, true) && chVar.f644b != null) {
            chVar.f644b.adReceived(new bv(chVar.f643a, true, appLovinMediatedAdInfo));
        }
    }

    /* renamed from: a */
    private void m657a(String str, Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("No operation specified");
        }
        try {
            this.f626d.mo2288d("MediationAdapterWrapper", "Running implementation operation '" + str + "'...");
            runnable.run();
        } catch (Throwable th) {
            this.f626d.mo2290e("MediationAdapterWrapper", "Unable to implementation operation run " + str + ", marking " + this + " as disabled", th);
            m666a("fail_" + str);
        }
    }

    /* renamed from: b */
    private void m659b(bv bvVar) {
        Map e = bvVar.m624e();
        if (e != null) {
            this.f627e.m631b(e);
        }
    }

    /* renamed from: a */
    public String m662a() {
        return this.f623a;
    }

    /* renamed from: a */
    void m663a(bv bvVar) {
        if (bvVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (!this.f628f.get()) {
            this.f626d.mo2294w("MediationAdapterWrapper", "Mediation implementation '" + m671e() + "' was disabled due to earlier failures. Preparing ads with this implementation is disabled.");
        } else if (this.f624b.isReady()) {
            m657a("ad_prepare", new ce(this, bvVar));
        } else {
            this.f626d.userError("MediationAdapterWrapper", "Mediation implementation '" + m671e() + "' is not ready.");
        }
    }

    /* renamed from: a */
    void m664a(bv bvVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (bvVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (!this.f628f.get()) {
            this.f626d.userError("MediationAdapterWrapper", "Mediation implementation '" + m671e() + "' was disabled due to earlier failures. Loading ads with this implementation is disabled.");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.MEDIATION_ADAPTER_DISABLED);
            }
        } else if (this.f624b.isReady()) {
            m657a("ad_load", new cc(this, bvVar, new ch(bvVar, appLovinAdLoadListener)));
        } else {
            this.f626d.userError("MediationAdapterWrapper", "Mediation implementation '" + m671e() + "' is not ready.");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.MEDIATION_ADAPTER_READY_AD);
            }
        }
    }

    /* renamed from: a */
    void m665a(bv bvVar, String str, Activity activity, C0496b c0496b) {
        if (bvVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (!bvVar.mo2133a()) {
            throw new IllegalArgumentException("Mediated ad is not ready");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else if (c0496b == null) {
            throw new IllegalArgumentException("No listeners specified");
        } else {
            m657a("ad_render", new cf(this, c0496b, bvVar, str, activity));
        }
    }

    /* renamed from: a */
    void m666a(String str) {
        this.f626d.mo2291i("MediationAdapterWrapper", "Marking " + m671e() + " as disabled due to: " + str);
        this.f628f.set(false);
    }

    /* renamed from: a */
    void m667a(Map map) {
        m657a("init", new cb(this, map));
    }

    /* renamed from: b */
    boolean m668b() {
        return this.f628f.get();
    }

    /* renamed from: c */
    boolean m669c() {
        return m668b() && this.f624b.isReady();
    }

    /* renamed from: d */
    AppLovinMediationAdapter m670d() {
        return this.f624b;
    }

    /* renamed from: e */
    String m671e() {
        return this.f624b.getClass().getName();
    }

    /* renamed from: f */
    AppLovinMediationAdapterConfig m672f() {
        return this.f627e;
    }

    public String toString() {
        return "[MediationAdapterWrapper implementation: " + m671e() + "]";
    }
}
