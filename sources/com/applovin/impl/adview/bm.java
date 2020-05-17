package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

class bm implements AppLovinAdVideoPlaybackListener {
    /* renamed from: a */
    final /* synthetic */ bt f269a;
    /* renamed from: b */
    final /* synthetic */ bj f270b;

    bm(bj bjVar, bt btVar) {
        this.f270b = bjVar;
        this.f269a = btVar;
    }

    public void videoPlaybackBegan(AppLovinAd appLovinAd) {
        AppLovinAdVideoPlaybackListener c = this.f269a.m359c();
        if (c != null) {
            c.videoPlaybackBegan(appLovinAd);
        }
    }

    public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
        AppLovinAdVideoPlaybackListener c = this.f269a.m359c();
        if (c != null) {
            c.videoPlaybackEnded(appLovinAd, d, z);
        }
    }
}
