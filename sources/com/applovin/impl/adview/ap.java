package com.applovin.impl.adview;

class ap implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C0471x f220a;
    /* renamed from: b */
    final /* synthetic */ aj f221b;

    ap(aj ajVar, C0471x c0471x) {
        this.f221b = ajVar;
        this.f220a = c0471x;
    }

    public void run() {
        if (this.f220a.equals(this.f221b.f44x)) {
            this.f221b.m82n();
        } else if (this.f220a.equals(this.f221b.f46z)) {
            this.f221b.m85p();
        }
    }
}
