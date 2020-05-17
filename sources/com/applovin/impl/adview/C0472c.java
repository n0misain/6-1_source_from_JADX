package com.applovin.impl.adview;

/* renamed from: com.applovin.impl.adview.c */
class C0472c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f310a;

    C0472c(AdViewControllerImpl adViewControllerImpl) {
        this.f310a = adViewControllerImpl;
    }

    public void run() {
        if (this.f310a.f179r != null) {
            this.f310a.f179r.dismiss();
            this.f310a.f179r = null;
            if (this.f310a.f163b != null && this.f310a.f171j != null) {
                this.f310a.f163b.addView(this.f310a.f171j);
                AdViewControllerImpl.m271b(this.f310a.f171j, this.f310a.f176o.getSize());
            }
        }
    }
}
