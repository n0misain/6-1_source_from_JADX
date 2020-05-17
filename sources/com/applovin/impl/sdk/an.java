package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import java.lang.ref.SoftReference;

class an implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Activity f510a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAdRewardListener f511b;
    /* renamed from: c */
    final /* synthetic */ AppLovinAdVideoPlaybackListener f512c;
    /* renamed from: d */
    final /* synthetic */ AppLovinAdDisplayListener f513d;
    /* renamed from: e */
    final /* synthetic */ AppLovinAdClickListener f514e;
    /* renamed from: f */
    final /* synthetic */ ae f515f;
    /* renamed from: g */
    final /* synthetic */ am f516g;

    an(am amVar, Activity activity, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener, ae aeVar) {
        this.f516g = amVar;
        this.f510a = activity;
        this.f511b = appLovinAdRewardListener;
        this.f512c = appLovinAdVideoPlaybackListener;
        this.f513d = appLovinAdDisplayListener;
        this.f514e = appLovinAdClickListener;
        this.f515f = aeVar;
    }

    public void run() {
        AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(this.f516g.f499a, this.f510a);
        AppLovinAdRewardListener atVar = new at(this.f516g, this.f510a, this.f511b, this.f512c, this.f513d, this.f514e, null);
        create.setAdDisplayListener(atVar);
        create.setAdVideoPlaybackListener(atVar);
        create.setAdClickListener(atVar);
        create.showAndRender(this.f515f, this.f516g.f502d);
        this.f516g.f509k = new SoftReference(create);
        this.f516g.m542a(this.f515f, atVar);
    }
}
