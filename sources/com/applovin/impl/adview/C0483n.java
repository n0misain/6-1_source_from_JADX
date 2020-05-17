package com.applovin.impl.adview;

/* renamed from: com.applovin.impl.adview.n */
class C0483n implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f350a;

    private C0483n(AdViewControllerImpl adViewControllerImpl) {
        this.f350a = adViewControllerImpl;
    }

    public void run() {
        if (this.f350a.f176o != null) {
            this.f350a.f166e.mo2288d("AppLovinAdView", "Rendering advertisement ad for #" + this.f350a.f176o.getAdIdNumber() + " over placement: \"" + this.f350a.f168g + "\"...");
            AdViewControllerImpl.m271b(this.f350a.f171j, this.f350a.f176o.getSize());
            this.f350a.f171j.m402a(this.f350a.f176o, this.f350a.f168g);
        }
    }
}
