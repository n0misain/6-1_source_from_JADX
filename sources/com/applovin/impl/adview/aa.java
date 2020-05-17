package com.applovin.impl.adview;

class aa implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ac f198a;
    /* renamed from: b */
    final /* synthetic */ int f199b;
    /* renamed from: c */
    final /* synthetic */ C0494z f200c;

    aa(C0494z c0494z, ac acVar, int i) {
        this.f200c = c0494z;
        this.f198a = acVar;
        this.f199b = i;
    }

    public void run() {
        ab b = this.f198a.m293c();
        if (!b.mo2159b()) {
            this.f200c.f394a.mo2288d("CountdownManager", "Ending countdown for " + this.f198a.m288a());
        } else if (this.f200c.f397d.get() == this.f199b) {
            try {
                b.mo2158a();
            } catch (Throwable th) {
                this.f200c.f394a.mo2290e("CountdownManager", "Encountered error on countdown step for: " + this.f198a.m288a(), th);
            }
            this.f200c.m434a(this.f198a, this.f199b);
        } else {
            this.f200c.f394a.mo2294w("CountdownManager", "Killing duplicate countdown from previous generation: " + this.f198a.m288a());
        }
    }
}
