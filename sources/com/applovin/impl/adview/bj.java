package com.applovin.impl.adview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.sdk.dn;
import com.applovin.impl.sdk.dp;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.lang.ref.WeakReference;

class bj extends Dialog implements ad {
    /* renamed from: a */
    private final Activity f251a;
    /* renamed from: b */
    private final AppLovinSdk f252b;
    /* renamed from: c */
    private final AppLovinLogger f253c;
    /* renamed from: d */
    private RelativeLayout f254d;
    /* renamed from: e */
    private AppLovinAdView f255e;
    /* renamed from: f */
    private Runnable f256f;
    /* renamed from: g */
    private C0471x f257g;
    /* renamed from: h */
    private Handler f258h;
    /* renamed from: i */
    private bt f259i = null;
    /* renamed from: j */
    private dp f260j;
    /* renamed from: k */
    private long f261k;
    /* renamed from: l */
    private long f262l;
    /* renamed from: m */
    private volatile boolean f263m = false;
    /* renamed from: n */
    private volatile boolean f264n = false;

    bj(AppLovinSdk appLovinSdk, Activity activity) {
        super(activity, 16973840);
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else {
            this.f252b = appLovinSdk;
            this.f253c = appLovinSdk.getLogger();
            this.f251a = activity;
            this.f256f = new bs();
            this.f260j = new dp(appLovinSdk);
            this.f258h = new Handler();
            this.f255e = new AppLovinAdView(appLovinSdk, AppLovinAdSize.INTERSTITIAL, activity);
            this.f255e.setAutoDestroy(false);
            ((AdViewControllerImpl) this.f255e.getAdViewController()).setParentDialog(new WeakReference(this));
            requestWindowFeature(1);
            try {
                getWindow().setFlags(activity.getWindow().getAttributes().flags, activity.getWindow().getAttributes().flags);
            } catch (Throwable e) {
                this.f253c.mo2290e("InterstitialAdDialog", "Setting window flags failed.", e);
            }
        }
    }

    /* renamed from: a */
    private int m314a(int i) {
        return AppLovinSdkUtils.dpToPx(this.f251a, i);
    }

    /* renamed from: a */
    private void m317a() {
        this.f251a.runOnUiThread(new br(this));
    }

    /* renamed from: a */
    private void m318a(long j) {
        this.f258h.postDelayed(new bq(this), j);
    }

    /* renamed from: a */
    private void m320a(C0493y c0493y) {
        int i = 9;
        this.f257g = C0471x.m365a(this.f252b, getContext(), c0493y);
        this.f257g.setVisibility(8);
        this.f257g.setOnClickListener(new bo(this));
        this.f257g.setClickable(false);
        dn dnVar = new dn(this.f252b);
        int a = m314a(dnVar.m861k());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(a, a);
        layoutParams.addRule(10);
        layoutParams.addRule(dnVar.m874x() ? 9 : 11);
        this.f257g.mo2181a(a);
        int a2 = m314a(dnVar.m863m());
        int a3 = m314a(dnVar.m865o());
        layoutParams.setMargins(a3, a2, a3, a2);
        this.f255e.addView(this.f257g, layoutParams);
        this.f257g.bringToFront();
        int a4 = m314a(dnVar.m867q());
        View view = new View(this.f251a);
        view.setBackgroundColor(0);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a + a4, a + a4);
        layoutParams2.addRule(10);
        if (!dnVar.m873w()) {
            i = 11;
        }
        layoutParams2.addRule(i);
        layoutParams2.setMargins(0, a2 - m314a(5), a3 - m314a(5), 0);
        view.setOnClickListener(new bp(this));
        this.f255e.addView(view, layoutParams2);
        view.bringToFront();
    }

    /* renamed from: b */
    private void m323b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.f255e.setLayoutParams(layoutParams);
        this.f254d = new RelativeLayout(this.f251a);
        this.f254d.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.f254d.setBackgroundColor(-1157627904);
        this.f254d.addView(this.f255e);
        setContentView(this.f254d);
    }

    /* renamed from: a */
    public void m337a(bt btVar) {
        this.f255e.setAdDisplayListener(new bk(this, btVar));
        this.f255e.setAdClickListener(new bl(this, btVar));
        this.f255e.setAdVideoPlaybackListener(new bm(this, btVar));
        this.f259i = btVar;
        btVar.m357a(true);
    }

    /* renamed from: a */
    public void m338a(AppLovinAd appLovinAd, String str) {
        this.f251a.runOnUiThread(new bn(this, appLovinAd, str));
    }

    public void dismiss() {
        long currentTimeMillis = System.currentTimeMillis() - this.f261k;
        this.f260j.m887a(currentTimeMillis);
        this.f253c.mo2291i("InterstitialAdDialog", "Dismissing ad after " + currentTimeMillis + " milliseconds elapsed");
        if (this.f259i != null) {
            this.f259i.m364h();
        }
        if (this.f255e != null) {
            this.f255e.destroy();
        }
        this.f259i = null;
        this.f255e = null;
        super.dismiss();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m323b();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.f253c.mo2288d("InterstitialAdDialog", "Window focus changed. hasFocus = " + z);
        dn dnVar = new dn(this.f252b);
        if (z && this.f262l == 0 && dnVar.m841Q()) {
            this.f255e.setVisibility(4);
            this.f255e.setVisibility(0);
        }
        if (!z || this.f262l <= 0) {
            this.f262l = System.currentTimeMillis();
        } else {
            this.f260j.m891c(System.currentTimeMillis() - this.f262l);
        }
    }
}
