package com.applovin.impl.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

class an implements Runnable {
    /* renamed from: a */
    final /* synthetic */ aj f218a;

    an(aj ajVar) {
        this.f218a = ajVar;
    }

    public void run() {
        try {
            if (this.f218a.f31k) {
                this.f218a.f44x.setVisibility(0);
                return;
            }
            this.f218a.f31k = true;
            if (this.f218a.m83o() && this.f218a.f45y != null) {
                this.f218a.f45y.setVisibility(0);
                this.f218a.f45y.bringToFront();
            }
            this.f218a.f44x.setVisibility(0);
            this.f218a.f44x.bringToFront();
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration((long) this.f218a.settingsProxy.m854d());
            alphaAnimation.setRepeatCount(0);
            this.f218a.f44x.startAnimation(alphaAnimation);
        } catch (Throwable th) {
            this.f218a.dismiss();
        }
    }
}
