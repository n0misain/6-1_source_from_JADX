package com.applovin.impl.sdk;

/* renamed from: com.applovin.impl.sdk.q */
class C0512q extends di {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdServiceImpl f958a;
    /* renamed from: b */
    private final C0505i f959b;

    public C0512q(AppLovinAdServiceImpl appLovinAdServiceImpl, C0505i c0505i) {
        this.f958a = appLovinAdServiceImpl;
        super("UpdateAdTask", appLovinAdServiceImpl.f398a);
        this.f959b = c0505i;
    }

    public void run() {
        Object obj = 1;
        C0511p c0511p = (C0511p) this.f958a.f401d.get(this.f959b);
        synchronized (c0511p.f952b) {
            boolean a = this.f958a.m452a(this.f959b.m1133a());
            boolean e = this.f958a.m448a();
            Object obj2 = !c0511p.f956f.isEmpty() ? 1 : null;
            if (System.currentTimeMillis() <= c0511p.f954d) {
                obj = null;
            }
            if (!(!a || obj2 == null || r1 == null || !e || c0511p.f955e)) {
                c0511p.f955e = true;
                this.f958a.m457b(this.f959b, new C0510o(this.f958a, c0511p));
            }
        }
    }
}
