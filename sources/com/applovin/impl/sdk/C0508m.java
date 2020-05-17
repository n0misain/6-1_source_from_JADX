package com.applovin.impl.sdk;

/* renamed from: com.applovin.impl.sdk.m */
class C0508m implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C0507l f947a;

    C0508m(C0507l c0507l) {
        this.f947a = c0507l;
    }

    public void run() {
        if (!this.f947a.f942a.isForegroundClickInvalidated()) {
            this.f947a.f946e.m442a(this.f947a.f943b, this.f947a.f944c, this.f947a.f945d, this.f947a.f942a);
        }
    }
}
