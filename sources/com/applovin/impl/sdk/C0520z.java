package com.applovin.impl.sdk;

import android.webkit.WebView;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.applovin.impl.sdk.z */
class C0520z implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AtomicReference f976a;
    /* renamed from: b */
    final /* synthetic */ CountDownLatch f977b;
    /* renamed from: c */
    final /* synthetic */ C0519y f978c;

    C0520z(C0519y c0519y, AtomicReference atomicReference, CountDownLatch countDownLatch) {
        this.f978c = c0519y;
        this.f976a = atomicReference;
        this.f977b = countDownLatch;
    }

    public void run() {
        try {
            this.f976a.set(new WebView(this.f978c.f974c).getSettings().getUserAgentString());
        } catch (Throwable th) {
            this.f978c.f973b.mo2290e("DataCollector", "Unable to collect user agent string", th);
        } finally {
            this.f977b.countDown();
        }
    }
}
