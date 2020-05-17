package com.applovin.impl.sdk;

import android.app.Activity;
import android.net.Uri;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinMediationService;
import com.applovin.sdk.AppLovinMediationService.AppLovinMediationAdapterInfo;
import com.applovin.sdk.AppLovinMediationService.AppLovinMediationAdapterStats;
import com.applovin.sdk.AppLovinMediationService.AppLovinMediationAdapterStatus;
import java.util.ArrayList;
import java.util.Collection;

public class MediationServiceImpl implements AppLovinMediationService {
    public static final String TAG = "MediationServiceImpl";
    /* renamed from: a */
    private final AppLovinSdkImpl f432a;
    /* renamed from: b */
    private final AppLovinLogger f433b;
    /* renamed from: c */
    private final by f434c;
    /* renamed from: d */
    private final Object f435d = new Object();
    /* renamed from: e */
    private long f436e = 0;
    /* renamed from: f */
    private String f437f = null;

    MediationServiceImpl(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f432a = appLovinSdkImpl;
        this.f433b = appLovinSdkImpl.getLogger();
        this.f434c = new by(appLovinSdkImpl);
    }

    /* renamed from: a */
    private void m490a(int i, bv bvVar) {
        if (((Boolean) this.f432a.get(dj.cB)).booleanValue()) {
            m497a("err", i, bvVar);
        }
    }

    /* renamed from: a */
    private void m494a(bv bvVar, int i, C0496b c0496b) {
        c0496b.m573b((AppLovinAd) bvVar);
    }

    /* renamed from: a */
    private void m495a(bv bvVar, int i, AppLovinAdLoadListener appLovinAdLoadListener) {
        m490a(i, bvVar);
        if (appLovinAdLoadListener == null) {
            return;
        }
        if (appLovinAdLoadListener instanceof aj) {
            ((aj) appLovinAdLoadListener).mo2254a(bvVar.mo2131m(), i);
        } else {
            appLovinAdLoadListener.failedToReceiveAd(i);
        }
    }

    /* renamed from: a */
    private void m496a(AppLovinAd appLovinAd, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (appLovinAdLoadListener != null) {
            appLovinAdLoadListener.adReceived(appLovinAd);
        }
    }

    /* renamed from: a */
    private void m497a(String str, int i, bv bvVar) {
        try {
            this.f432a.getPostbackService().dispatchPostbackAsync(Uri.parse((String) this.f432a.get(dj.f783l)).buildUpon().appendQueryParameter("event", str).appendQueryParameter("ec", String.valueOf(i)).appendQueryParameter("clcode", bvVar.mo2130l()).appendQueryParameter("an", bvVar.m622c()).appendQueryParameter("ac", bvVar.m621b()).build().toString(), null);
        } catch (Throwable th) {
            this.f433b.mo2290e(TAG, "Unable to create post-back URL for mediated '" + str + "'", th);
        }
    }

    /* renamed from: b */
    private void m500b(bv bvVar) {
        if (((Boolean) this.f432a.get(dj.cz)).booleanValue()) {
            m497a("imp", 0, bvVar);
        }
    }

    /* renamed from: c */
    private void m502c(bv bvVar) {
        if (((Boolean) this.f432a.get(dj.cA)).booleanValue()) {
            m497a("clk", 0, bvVar);
        }
    }

    /* renamed from: a */
    void m503a() {
        this.f434c.m641a();
    }

    /* renamed from: a */
    void m504a(bv bvVar) {
        if (bvVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        this.f433b.mo2288d(TAG, "Loading " + bvVar + "...");
        ca a = this.f434c.m640a(bvVar.m622c(), bvVar.m621b(), bvVar.m624e());
        if (a != null) {
            a.m663a(bvVar);
        } else {
            this.f433b.mo2294w(TAG, "Failed to prepare" + bvVar + ": adapter not loaded");
        }
    }

    /* renamed from: a */
    void m505a(bv bvVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (bvVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        this.f433b.mo2288d(TAG, "Loading " + bvVar + "...");
        ca a = this.f434c.m640a(bvVar.m622c(), bvVar.m621b(), bvVar.m624e());
        if (a != null) {
            a.m664a(bvVar, new cj(this, a, System.currentTimeMillis(), appLovinAdLoadListener, bvVar));
            return;
        }
        this.f433b.mo2294w(TAG, "Failed to load " + bvVar + ": adapter not loaded");
        m495a(bvVar, (int) AppLovinErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED, appLovinAdLoadListener);
    }

    public Collection getAdapterInfo() {
        Collection b = this.f434c.m643b();
        Collection<ca> c = this.f434c.m644c();
        Collection arrayList = new ArrayList(c.size());
        for (ca caVar : c) {
            String a = caVar.m662a();
            String e = caVar.m671e();
            if (b.contains(e)) {
                arrayList.add(new AppLovinMediationAdapterInfo(a, e, AppLovinMediationAdapterStatus.ERROR_LOAD));
            } else if (!caVar.m668b()) {
                arrayList.add(new AppLovinMediationAdapterInfo(a, e, AppLovinMediationAdapterStatus.ERROR_LOAD));
            } else if (caVar.m669c()) {
                arrayList.add(new AppLovinMediationAdapterInfo(a, e, AppLovinMediationAdapterStatus.READY, caVar.m670d(), caVar.m672f()));
            } else {
                arrayList.add(new AppLovinMediationAdapterInfo(a, e, AppLovinMediationAdapterStatus.ERROR_NOT_READY));
            }
        }
        return arrayList;
    }

    public AppLovinMediationAdapterStats getLastAdapterStats() {
        synchronized (this.f435d) {
            if (this.f437f != null) {
                AppLovinMediationAdapterStats appLovinMediationAdapterStats = new AppLovinMediationAdapterStats(this.f437f, this.f436e);
                return appLovinMediationAdapterStats;
            }
            return null;
        }
    }

    public void showAd(bv bvVar, String str, Activity activity, C0496b c0496b) {
        if (bvVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else if (c0496b == null) {
            throw new IllegalArgumentException("No listeners specified");
        } else if (bvVar.mo2133a()) {
            ca a = this.f434c.m640a(bvVar.m622c(), bvVar.m621b(), bvVar.m624e());
            if (a != null) {
                c0496b.m575b(new ck(this, bvVar));
                c0496b.m574b(new cl(this, bvVar));
                a.m665a(bvVar, str, activity, c0496b);
                return;
            }
            m494a(bvVar, (int) AppLovinErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED_ON_RENDER, c0496b);
            this.f433b.mo2294w(TAG, "Failed to show " + bvVar + ": adapter not loaded");
            this.f433b.userError(TAG, "There may be an integration problem with the mediated '" + bvVar.m622c() + "'. Please check if you have a supported version of that SDK integrated into your project.");
        } else {
            m494a(bvVar, (int) AppLovinErrorCodes.MEDIATION_ADAPTER_RENDER_NOT_READY_AD, c0496b);
            this.f433b.mo2289e(TAG, "Ad " + bvVar + " was not ready when provided requestsed to show.");
        }
    }
}
