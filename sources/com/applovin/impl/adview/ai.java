package com.applovin.impl.adview;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class ai implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ ah f214a;

    ai(ah ahVar) {
        this.f214a = ahVar;
    }

    public void onAnimationEnd(Animation animation) {
        this.f214a.f213a.f210g.setClickable(true);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
