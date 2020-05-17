package com.applovin.impl.sdk;

import java.util.concurrent.atomic.AtomicReference;

class dy implements C0499w {
    /* renamed from: a */
    final /* synthetic */ AtomicReference f822a;
    /* renamed from: b */
    final /* synthetic */ String f823b;
    /* renamed from: c */
    final /* synthetic */ dx f824c;

    dy(dx dxVar, AtomicReference atomicReference, String str) {
        this.f824c = dxVar;
        this.f822a = atomicReference;
        this.f823b = str;
    }

    /* renamed from: a */
    public void mo2274a(int i) {
        this.f824c.e.mo2289e(this.f824c.c, "Failed to load resource from '" + this.f823b + "'");
    }

    /* renamed from: a */
    public void m931a(String str, int i) {
        this.f822a.set(str);
    }
}
