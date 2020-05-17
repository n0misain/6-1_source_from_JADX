package com.applovin.impl.adview;

class bx implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f303a;
    /* renamed from: b */
    final /* synthetic */ bt f304b;

    bx(bt btVar, int i) {
        this.f304b = btVar;
        this.f303a = i;
    }

    public void run() {
        if (this.f304b.f287h != null) {
            this.f304b.f287h.failedToReceiveAd(this.f303a);
        }
    }
}
