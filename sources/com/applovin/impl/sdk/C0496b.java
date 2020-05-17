package com.applovin.impl.sdk;

import android.os.Handler;
import android.os.Looper;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.lang.ref.WeakReference;
import java.util.Map;

/* renamed from: com.applovin.impl.sdk.b */
public class C0496b {
    /* renamed from: a */
    private static final Handler f550a = new Handler(Looper.getMainLooper());
    /* renamed from: b */
    private WeakReference f551b = new WeakReference(null);
    /* renamed from: c */
    private WeakReference f552c = new WeakReference(null);
    /* renamed from: d */
    private WeakReference f553d = new WeakReference(null);
    /* renamed from: e */
    private AppLovinAdDisplayListener f554e;
    /* renamed from: f */
    private AppLovinAdClickListener f555f;

    /* renamed from: a */
    void m568a(AppLovinAd appLovinAd) {
        AppLovinAdDisplayListener appLovinAdDisplayListener = (AppLovinAdDisplayListener) this.f551b.get();
        if (appLovinAdDisplayListener != null) {
            f550a.post(new C0497c(this, appLovinAdDisplayListener, appLovinAd));
        }
        if (this.f554e != null) {
            this.f554e.adDisplayed(appLovinAd);
        }
    }

    /* renamed from: a */
    public void m569a(AppLovinAdClickListener appLovinAdClickListener) {
        this.f552c = new WeakReference(appLovinAdClickListener);
    }

    /* renamed from: a */
    public void m570a(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.f551b = new WeakReference(appLovinAdDisplayListener);
    }

    /* renamed from: a */
    public void m571a(AppLovinAdRewardListener appLovinAdRewardListener) {
        this.f553d = new WeakReference(appLovinAdRewardListener);
    }

    /* renamed from: a */
    void m572a(Map map, bv bvVar) {
        AppLovinAdRewardListener appLovinAdRewardListener = (AppLovinAdRewardListener) this.f553d.get();
        if (appLovinAdRewardListener != null) {
            appLovinAdRewardListener.userRewardVerified(bvVar, map);
        }
    }

    /* renamed from: b */
    void m573b(AppLovinAd appLovinAd) {
        AppLovinAdDisplayListener appLovinAdDisplayListener = (AppLovinAdDisplayListener) this.f551b.get();
        if (appLovinAdDisplayListener != null) {
            f550a.post(new C0498d(this, appLovinAdDisplayListener, appLovinAd));
        }
        if (this.f554e != null) {
            this.f554e.adHidden(appLovinAd);
        }
    }

    /* renamed from: b */
    void m574b(AppLovinAdClickListener appLovinAdClickListener) {
        this.f555f = appLovinAdClickListener;
    }

    /* renamed from: b */
    void m575b(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.f554e = appLovinAdDisplayListener;
    }

    /* renamed from: b */
    void m576b(Map map, bv bvVar) {
        AppLovinAdRewardListener appLovinAdRewardListener = (AppLovinAdRewardListener) this.f553d.get();
        if (appLovinAdRewardListener != null) {
            appLovinAdRewardListener.userRewardRejected(bvVar, map);
        }
    }

    /* renamed from: c */
    void m577c(AppLovinAd appLovinAd) {
        AppLovinAdClickListener appLovinAdClickListener = (AppLovinAdClickListener) this.f552c.get();
        if (appLovinAdClickListener != null) {
            f550a.post(new C0501e(this, appLovinAdClickListener, appLovinAd));
        }
        if (this.f555f != null) {
            this.f555f.adClicked(appLovinAd);
        }
    }
}
