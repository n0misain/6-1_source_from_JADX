package com.applovin.impl.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.applovin.impl.sdk.dn;

class br implements Runnable {
    /* renamed from: a */
    final /* synthetic */ bj f277a;

    br(bj bjVar) {
        this.f277a = bjVar;
    }

    public void run() {
        try {
            this.f277a.f257g.setVisibility(0);
            this.f277a.f257g.bringToFront();
            dn dnVar = new dn(this.f277a.f252b);
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration((long) dnVar.m854d());
            alphaAnimation.setRepeatCount(0);
            this.f277a.f257g.startAnimation(alphaAnimation);
        } catch (Throwable th) {
            this.f277a.dismiss();
        }
    }
}
