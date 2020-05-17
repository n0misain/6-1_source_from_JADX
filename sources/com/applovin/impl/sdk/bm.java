package com.applovin.impl.sdk;

class bm implements Runnable {
    /* renamed from: a */
    final /* synthetic */ bl f587a;

    bm(bl blVar) {
        this.f587a = blVar;
    }

    public void run() {
        if (this.f587a.f586c != null) {
            this.f587a.f586c.dismiss();
        }
    }
}
