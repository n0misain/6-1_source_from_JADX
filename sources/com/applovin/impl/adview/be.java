package com.applovin.impl.adview;

class be implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f244a;
    /* renamed from: b */
    final /* synthetic */ int f245b;
    /* renamed from: c */
    final /* synthetic */ bd f246c;

    be(bd bdVar, int i, int i2) {
        this.f246c = bdVar;
        this.f244a = i;
        this.f245b = i2;
    }

    public void run() {
        this.f246c.f243a.logger.mo2289e("InterActivity", "Video view error (" + this.f244a + "," + this.f245b + ").");
        this.f246c.f243a.handleMediaError();
    }
}
