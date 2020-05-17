package com.applovin.impl.sdk;

class fc extends ex {
    /* renamed from: a */
    final /* synthetic */ fb f895a;

    fc(fb fbVar, String str, fl flVar, String str2, AppLovinSdkImpl appLovinSdkImpl) {
        this.f895a = fbVar;
        super(str, flVar, str2, appLovinSdkImpl);
    }

    /* renamed from: a */
    public void mo2274a(int i) {
        this.e.mo2289e(this.c, "Unable to resolve VAST wrapper. Server returned " + i);
        this.f895a.m1045a(i);
    }

    /* renamed from: a */
    public void m1050a(fl flVar, int i) {
        this.d.m466a().m991a(eq.m1007a(flVar, this.f895a.f893a, this.f895a.f894b, this.d));
    }
}
