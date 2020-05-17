package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.impl.p000a.C0450a;
import com.applovin.impl.p000a.C0467r;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.C0496b;
import com.applovin.impl.sdk.C0503g;
import com.applovin.impl.sdk.C0504h;
import com.applovin.impl.sdk.C0516u;
import com.applovin.impl.sdk.C0518x;
import com.applovin.impl.sdk.ae;
import com.applovin.impl.sdk.ag;
import com.applovin.impl.sdk.bv;
import com.applovin.impl.sdk.dn;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class bt implements AppLovinInterstitialAdDialog {
    /* renamed from: d */
    public static volatile boolean f279d = false;
    /* renamed from: e */
    public static volatile boolean f280e = false;
    /* renamed from: f */
    private static final Map f281f = Collections.synchronizedMap(new HashMap());
    /* renamed from: p */
    private static volatile boolean f282p;
    /* renamed from: a */
    protected final String f283a;
    /* renamed from: b */
    protected final AppLovinSdkImpl f284b;
    /* renamed from: c */
    protected final WeakReference f285c;
    /* renamed from: g */
    private final C0496b f286g;
    /* renamed from: h */
    private volatile AppLovinAdLoadListener f287h;
    /* renamed from: i */
    private volatile AppLovinAdDisplayListener f288i;
    /* renamed from: j */
    private volatile AppLovinAdVideoPlaybackListener f289j;
    /* renamed from: k */
    private volatile AppLovinAdClickListener f290k;
    /* renamed from: l */
    private volatile ae f291l;
    /* renamed from: m */
    private volatile ag f292m;
    /* renamed from: n */
    private volatile ad f293n;
    /* renamed from: o */
    private volatile String f294o;

    bt(AppLovinSdk appLovinSdk, Activity activity) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else {
            this.f284b = (AppLovinSdkImpl) appLovinSdk;
            this.f283a = UUID.randomUUID().toString();
            this.f286g = new C0496b();
            this.f285c = new WeakReference(activity);
            f279d = true;
            f280e = false;
        }
    }

    /* renamed from: a */
    public static bt m339a(String str) {
        return (bt) f281f.get(str);
    }

    /* renamed from: a */
    private void m341a(int i) {
        Activity i2 = m353i();
        if (i2 != null) {
            i2.runOnUiThread(new bx(this, i));
        } else {
            this.f284b.getLogger().userError("InterstitialAdDialogWrapper", "Failed to notify load listener: activity reference is stale");
        }
    }

    /* renamed from: a */
    private void m342a(Activity activity) {
        Object bjVar = new bj(this.f284b, activity);
        bjVar.m337a(this);
        this.f293n = bjVar;
        bjVar.m338a(this.f291l, this.f294o);
    }

    /* renamed from: a */
    private void m343a(Activity activity, boolean z, boolean z2) {
        if (z && z2) {
            m351b(activity);
        } else {
            m342a(activity);
        }
    }

    /* renamed from: a */
    private void m347a(ae aeVar, String str, Activity activity) {
        boolean z = false;
        dn dnVar = new dn(this.f284b);
        if (C0518x.m1184a(this.f284b.getApplicationContext()) || dnVar.m848X()) {
            f281f.put(this.f283a, this);
            this.f291l = aeVar;
            this.f294o = str;
            this.f292m = this.f291l != null ? this.f291l.m147n() : ag.DEFAULT;
            if (!(this.f291l.mo2134b() || this.f291l.mo2135d() == null || this.f284b.getFileManager().m527a(this.f291l.mo2135d().getLastPathSegment(), (Context) activity))) {
                if (this.f291l instanceof C0450a) {
                    C0467r c = ((C0450a) this.f291l).m172c();
                    if (c != null) {
                        this.f284b.getLogger().mo2289e("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for VAST ad. Setting videoUri to source: " + c.m256a());
                        c.m257a(c.m256a());
                    } else {
                        this.f284b.getLogger().mo2289e("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for VAST ad and source uri not found. Failing ad show.");
                        m349a((AppLovinAd) aeVar);
                        return;
                    }
                }
                this.f284b.getLogger().mo2289e("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for internal ad. Failing ad show.");
                m349a((AppLovinAd) aeVar);
                return;
            }
            boolean a = C0516u.m1159a(AppLovinInterstitialActivity.class, (Context) activity);
            boolean z2 = (this.f291l instanceof C0503g) && ((C0503g) this.f291l).m1128i() == C0504h.ACTIVITY;
            boolean z3 = this.f292m == ag.ACTIVITY_LANDSCAPE || this.f292m == ag.ACTIVITY_PORTRAIT;
            boolean z4 = this.f291l instanceof C0450a;
            if (z2 || z3 || z4) {
                z = true;
            }
            long max = Math.max(0, dnVar.m842R());
            Handler handler = new Handler(activity.getMainLooper());
            this.f284b.getLogger().mo2288d("InterstitialAdDialogWrapper", "Presenting ad with delay of " + max);
            handler.postDelayed(new bv(this, activity, a, z), max);
            return;
        }
        this.f284b.getLogger().userError("InterstitialAdDialogWrapper", "Failing ad display due to no internet connection.");
        m349a((AppLovinAd) aeVar);
    }

    /* renamed from: a */
    private void m348a(bv bvVar, String str, Activity activity) {
        this.f284b.getMediationService().showAd(bvVar, str, activity, this.f286g);
    }

    /* renamed from: a */
    private void m349a(AppLovinAd appLovinAd) {
        if (this.f288i != null) {
            this.f288i.adHidden(appLovinAd);
        }
    }

    /* renamed from: b */
    private void m351b(Activity activity) {
        Intent intent = new Intent(activity, AppLovinInterstitialActivity.class);
        intent.putExtra(aj.KEY_WRAPPER_ID, this.f283a);
        AppLovinInterstitialActivity.lastKnownWrapper = this;
        activity.startActivity(intent);
        try {
            activity.overridePendingTransition(0, 0);
        } catch (Throwable th) {
            this.f284b.getLogger().mo2290e("InterstitialAdDialogWrapper", "Unable to remove pending transition animations", th);
        }
        m357a(true);
    }

    /* renamed from: b */
    private void m352b(AppLovinAd appLovinAd) {
        Activity i = m353i();
        if (i != null) {
            i.runOnUiThread(new bw(this, appLovinAd));
        } else {
            this.f284b.getLogger().userError("InterstitialAdDialogWrapper", "Failed to notify load listener: activity reference is stale");
        }
    }

    /* renamed from: i */
    private Activity m353i() {
        return this.f285c != null ? (Activity) this.f285c.get() : null;
    }

    /* renamed from: a */
    public AppLovinSdk m354a() {
        return this.f284b;
    }

    /* renamed from: a */
    public void m355a(ad adVar) {
        this.f293n = adVar;
    }

    /* renamed from: a */
    protected void mo2182a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.f284b.getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, appLovinAdLoadListener);
    }

    /* renamed from: a */
    public void m357a(boolean z) {
        f282p = z;
    }

    /* renamed from: b */
    public AppLovinAd m358b() {
        return this.f291l;
    }

    /* renamed from: c */
    public AppLovinAdVideoPlaybackListener m359c() {
        return this.f289j;
    }

    /* renamed from: d */
    public AppLovinAdDisplayListener m360d() {
        return this.f288i;
    }

    public void dismiss() {
        Activity i = m353i();
        if (i == null) {
            this.f284b.getLogger().userError("InterstitialAdDialogWrapper", "Failed to notify load listener: activity reference is stale");
        } else if (this.f293n != null) {
            i.runOnUiThread(new by(this));
        }
    }

    /* renamed from: e */
    public AppLovinAdClickListener m361e() {
        return this.f290k;
    }

    /* renamed from: f */
    public ag m362f() {
        return this.f292m;
    }

    /* renamed from: g */
    public String m363g() {
        return this.f294o;
    }

    /* renamed from: h */
    public void m364h() {
        f279d = false;
        f280e = true;
        f281f.remove(this.f283a);
    }

    public boolean isAdReadyToDisplay() {
        return this.f284b.getAdService().hasPreloadedAd(AppLovinAdSize.INTERSTITIAL);
    }

    public boolean isShowing() {
        return f282p;
    }

    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        this.f290k = appLovinAdClickListener;
        this.f286g.m569a(appLovinAdClickListener);
    }

    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.f288i = appLovinAdDisplayListener;
        this.f286g.m570a(appLovinAdDisplayListener);
    }

    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.f287h = appLovinAdLoadListener;
    }

    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.f289j = appLovinAdVideoPlaybackListener;
    }

    public void show() {
        show(null);
    }

    public void show(String str) {
        mo2182a(new bu(this, str));
    }

    public void showAndRender(AppLovinAd appLovinAd) {
        showAndRender(appLovinAd, null);
    }

    public void showAndRender(AppLovinAd appLovinAd, String str) {
        Activity i = m353i();
        if (isShowing()) {
            this.f284b.getLogger().userError("AppLovinInterstitialAdDialog", "Attempted to show an interstitial while one is already displayed; ignoring.");
        } else if (i == null) {
            this.f284b.getLogger().mo2289e("InterstitialAdDialogWrapper", "Failed to show interstitial: stale activity reference provided");
            m349a(appLovinAd);
        } else if (appLovinAd instanceof ae) {
            m347a((ae) appLovinAd, str, i);
        } else if (appLovinAd instanceof bv) {
            m348a((bv) appLovinAd, str, i);
        } else {
            this.f284b.getLogger().mo2289e("InterstitialAdDialogWrapper", "Failed to show interstitial: unknown ad type provided: '" + appLovinAd + "'");
            m349a(appLovinAd);
        }
    }
}
