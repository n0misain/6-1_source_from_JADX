package com.applovin.impl.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.applovin.impl.sdk.dn;

class ah implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ae f213a;

    ah(ae aeVar) {
        this.f213a = aeVar;
    }

    public void run() {
        try {
            if (this.f213a.f210g == null) {
                this.f213a.m300b();
            }
            this.f213a.f210g.setVisibility(0);
            this.f213a.f210g.bringToFront();
            dn dnVar = new dn(this.f213a.f205b);
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(dnVar.m849Y());
            alphaAnimation.setAnimationListener(new ai(this));
            this.f213a.f210g.startAnimation(alphaAnimation);
        } catch (Throwable th) {
            this.f213a.f206c.mo2290e("ExpandedAdDialog", "Unable to fade in close button", th);
            this.f213a.m300b();
        }
    }
}
