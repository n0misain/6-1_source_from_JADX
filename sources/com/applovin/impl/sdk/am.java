package com.applovin.impl.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.SoftReference;

public class am {
    /* renamed from: a */
    protected final AppLovinSdkImpl f499a;
    /* renamed from: b */
    protected final AppLovinAdServiceImpl f500b;
    /* renamed from: c */
    private AppLovinAd f501c;
    /* renamed from: d */
    private String f502d;
    /* renamed from: e */
    private SoftReference f503e;
    /* renamed from: f */
    private final Handler f504f;
    /* renamed from: g */
    private final Object f505g = new Object();
    /* renamed from: h */
    private volatile String f506h;
    /* renamed from: i */
    private fg f507i;
    /* renamed from: j */
    private volatile boolean f508j = false;
    /* renamed from: k */
    private SoftReference f509k;

    public am(AppLovinSdk appLovinSdk) {
        this.f499a = (AppLovinSdkImpl) appLovinSdk;
        this.f500b = (AppLovinAdServiceImpl) appLovinSdk.getAdService();
        this.f504f = new Handler(Looper.getMainLooper());
    }

    /* renamed from: a */
    private void m540a(Activity activity, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        if (!m559a()) {
            this.f499a.getLogger().userError("IncentivizedAdController", "Skipping incentivized video playback: user attempted to play an incentivized video before one was preloaded.");
            m550d();
        } else if (this.f501c instanceof ae) {
            m541a((ae) this.f501c, activity, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
        } else if (this.f501c instanceof bv) {
            m545a((bv) this.f501c, activity, appLovinAdRewardListener, appLovinAdDisplayListener, appLovinAdClickListener);
        } else {
            this.f499a.getLogger().userError("IncentivizedAdController", "Skipping incentivized video playback: an unknwon ad was pre-loaded: '" + this.f501c + "'");
            m550d();
        }
    }

    /* renamed from: a */
    private void m541a(ae aeVar, Activity activity, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        if (!aeVar.getType().equals(AppLovinAdType.INCENTIVIZED)) {
            this.f499a.getLogger().mo2289e("IncentivizedAdController", "Failed  to render an ad of type " + aeVar.getType() + " in an Incentivized Ad interstitial.");
            m543a(aeVar, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
        } else if (aeVar.mo2134b() || aeVar.mo2135d() == null || this.f499a.getFileManager().m527a(aeVar.mo2135d().getLastPathSegment(), (Context) activity)) {
            Runnable anVar = new an(this, activity, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener, aeVar);
            if (((Boolean) this.f499a.get(dj.al)).booleanValue()) {
                bf.m585b().m598a(this.f499a).m597a(activity).m599a(this).m600a(appLovinAdRewardListener).m601a(anVar).m596a().m590a();
            } else {
                anVar.run();
            }
        } else {
            this.f499a.getLogger().mo2289e("IncentivizedAdController", "Failed to render an ad: no invalid video URL provided");
            m543a(aeVar, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
        }
    }

    /* renamed from: a */
    private void m542a(ae aeVar, AppLovinAdRewardListener appLovinAdRewardListener) {
        this.f507i = new fg(aeVar, appLovinAdRewardListener, this.f499a);
        this.f499a.m466a().m992a(this.f507i, ej.BACKGROUND);
    }

    /* renamed from: a */
    private void m543a(ae aeVar, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.f504f.post(new ao(this, appLovinAdVideoPlaybackListener, aeVar, appLovinAdDisplayListener));
    }

    /* renamed from: a */
    private void m545a(bv bvVar, Activity activity, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        C0496b c0496b = new C0496b();
        c0496b.m569a(appLovinAdClickListener);
        c0496b.m570a(appLovinAdDisplayListener);
        c0496b.m571a(appLovinAdRewardListener);
        this.f499a.getMediationService().showAd(bvVar, this.f502d, activity, c0496b);
        m551e();
    }

    /* renamed from: d */
    private void m550d() {
        if (this.f503e != null) {
            AppLovinAdLoadListener appLovinAdLoadListener = (AppLovinAdLoadListener) this.f503e.get();
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.INCENTIVIZED_NO_AD_PRELOADED);
            }
        }
    }

    /* renamed from: e */
    private void m551e() {
        this.f501c = null;
        this.f502d = null;
    }

    /* renamed from: f */
    private AppLovinAdRewardListener m553f() {
        return new ap(this);
    }

    /* renamed from: a */
    public void m554a(Activity activity, String str, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        AppLovinAdRewardListener f = appLovinAdRewardListener == null ? m553f() : appLovinAdRewardListener;
        this.f502d = str;
        m540a(activity, f, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }

    /* renamed from: a */
    public void m555a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.f499a.getLogger().mo2288d("IncentivizedAdController", "User requested preload of incentivized ad...");
        this.f503e = new SoftReference(appLovinAdLoadListener);
        if (m559a()) {
            this.f499a.getLogger().userError("IncentivizedAdController", "Attempted to call preloadAndNotify: while an ad was already loaded or currently being played. Do not call preloadAndNotify: again until the last ad has been closed (adHidden).");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.adReceived(this.f501c);
                return;
            }
            return;
        }
        mo2237b(new aq(this, appLovinAdLoadListener));
    }

    /* renamed from: a */
    void m556a(AppLovinAdRewardListener appLovinAdRewardListener) {
        appLovinAdRewardListener.userDeclinedToViewAd(this.f501c);
    }

    /* renamed from: a */
    void m557a(String str) {
        synchronized (this.f505g) {
            this.f506h = str;
        }
    }

    /* renamed from: a */
    void m558a(String str, Activity activity) {
        if (str != null && ((Boolean) this.f499a.get(dj.am)).booleanValue()) {
            new bd(this.f499a, activity, str).m580a();
        }
    }

    /* renamed from: a */
    public boolean m559a() {
        return this.f501c != null;
    }

    /* renamed from: b */
    String m560b() {
        String str;
        synchronized (this.f505g) {
            str = this.f506h;
        }
        return str;
    }

    /* renamed from: b */
    void mo2237b(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.f500b.m462a(appLovinAdLoadListener);
    }

    /* renamed from: c */
    public void m562c() {
        if (this.f509k != null) {
            AppLovinInterstitialAdDialog appLovinInterstitialAdDialog = (AppLovinInterstitialAdDialog) this.f509k.get();
            if (appLovinInterstitialAdDialog != null) {
                appLovinInterstitialAdDialog.dismiss();
            }
        }
    }
}
