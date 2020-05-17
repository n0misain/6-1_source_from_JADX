package com.applovin.impl.adview;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class av implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ View f230a;
    /* renamed from: b */
    final /* synthetic */ boolean f231b;
    /* renamed from: c */
    final /* synthetic */ aj f232c;

    av(aj ajVar, View view, boolean z) {
        this.f232c = ajVar;
        this.f230a = view;
        this.f231b = z;
    }

    public void onAnimationEnd(Animation animation) {
        if (!this.f231b) {
            this.f230a.setVisibility(4);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        this.f230a.setVisibility(0);
    }
}
