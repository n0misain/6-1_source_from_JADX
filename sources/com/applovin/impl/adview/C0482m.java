package com.applovin.impl.adview;

/* renamed from: com.applovin.impl.adview.m */
class C0482m implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f349a;

    private C0482m(AdViewControllerImpl adViewControllerImpl) {
        this.f349a = adViewControllerImpl;
    }

    public void run() {
        try {
            this.f349a.f171j.loadDataWithBaseURL("/", "<html></html>", "text/html", null, "");
        } catch (Exception e) {
        }
    }
}
