package com.applovin.impl.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

class ao implements Runnable {
    /* renamed from: a */
    final /* synthetic */ aj f219a;

    ao(aj ajVar) {
        this.f219a = ajVar;
    }

    public void run() {
        try {
            if (!this.f219a.f32l && this.f219a.f46z != null) {
                this.f219a.f32l = true;
                this.f219a.f46z.setVisibility(0);
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration((long) this.f219a.settingsProxy.m854d());
                alphaAnimation.setRepeatCount(0);
                this.f219a.f46z.startAnimation(alphaAnimation);
                if (this.f219a.m83o() && this.f219a.f13A != null) {
                    this.f219a.f13A.setVisibility(0);
                    this.f219a.f13A.bringToFront();
                }
            }
        } catch (Throwable th) {
            this.f219a.logger.mo2294w("InterActivity", "Unable to show skip button: " + th);
        }
    }
}
