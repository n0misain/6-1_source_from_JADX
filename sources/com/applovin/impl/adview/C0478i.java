package com.applovin.impl.adview;

/* renamed from: com.applovin.impl.adview.i */
class C0478i implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f343a;

    private C0478i(AdViewControllerImpl adViewControllerImpl) {
        this.f343a = adViewControllerImpl;
    }

    public void run() {
        if (this.f343a.f171j != null) {
            this.f343a.f171j.setVisibility(8);
        }
    }
}
