package com.applovin.impl.adview;

import android.app.Activity;

class bv implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Activity f297a;
    /* renamed from: b */
    final /* synthetic */ boolean f298b;
    /* renamed from: c */
    final /* synthetic */ boolean f299c;
    /* renamed from: d */
    final /* synthetic */ bt f300d;

    bv(bt btVar, Activity activity, boolean z, boolean z2) {
        this.f300d = btVar;
        this.f297a = activity;
        this.f298b = z;
        this.f299c = z2;
    }

    public void run() {
        this.f300d.m343a(this.f297a, this.f298b, this.f299c);
    }
}
