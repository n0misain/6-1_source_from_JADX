package com.applovin.impl.adview;

/* renamed from: com.applovin.impl.adview.e */
class C0474e implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f338a;
    /* renamed from: b */
    final /* synthetic */ AdViewControllerImpl f339b;

    C0474e(AdViewControllerImpl adViewControllerImpl, int i) {
        this.f339b = adViewControllerImpl;
        this.f338a = i;
    }

    public void run() {
        try {
            if (this.f339b.f185x != null) {
                this.f339b.f185x.failedToReceiveAd(this.f338a);
            }
        } catch (Throwable th) {
            this.f339b.f166e.userError("AppLovinAdView", "Exception while running app load  callback", th);
        }
    }
}
