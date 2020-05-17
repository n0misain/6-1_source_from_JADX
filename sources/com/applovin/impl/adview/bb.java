package com.applovin.impl.adview;

class bb implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f239a;
    /* renamed from: b */
    final /* synthetic */ int f240b;
    /* renamed from: c */
    final /* synthetic */ ba f241c;

    bb(ba baVar, int i, int i2) {
        this.f241c = baVar;
        this.f239a = i;
        this.f240b = i2;
    }

    public void run() {
        this.f241c.f238a.f236a.logger.mo2289e("InterActivity", "Media player error (" + this.f239a + "," + this.f240b + ").");
        this.f241c.f238a.f236a.handleMediaError();
    }
}
