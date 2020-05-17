package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

class ao implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdVideoPlaybackListener f517a;
    /* renamed from: b */
    final /* synthetic */ ae f518b;
    /* renamed from: c */
    final /* synthetic */ AppLovinAdDisplayListener f519c;
    /* renamed from: d */
    final /* synthetic */ am f520d;

    ao(am amVar, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, ae aeVar, AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.f520d = amVar;
        this.f517a = appLovinAdVideoPlaybackListener;
        this.f518b = aeVar;
        this.f519c = appLovinAdDisplayListener;
    }

    public void run() {
        if (this.f517a != null) {
            this.f517a.videoPlaybackEnded(this.f518b, 0.0d, false);
        }
        if (this.f519c != null) {
            this.f519c.adHidden(this.f518b);
        }
    }
}
