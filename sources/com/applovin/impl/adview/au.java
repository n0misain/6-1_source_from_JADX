package com.applovin.impl.adview;

class au implements ch {
    /* renamed from: a */
    final /* synthetic */ aj f229a;

    au(aj ajVar) {
        this.f229a = ajVar;
    }

    /* renamed from: a */
    public void mo2160a(cf cfVar) {
        this.f229a.logger.mo2288d("InterActivity", "Clicking through from video button...");
        this.f229a.clickThroughFromVideo();
    }

    /* renamed from: b */
    public void mo2161b(cf cfVar) {
        this.f229a.logger.mo2288d("InterActivity", "Closing ad from video button...");
        this.f229a.dismiss();
    }

    /* renamed from: c */
    public void mo2162c(cf cfVar) {
        this.f229a.logger.mo2288d("InterActivity", "Skipping video from video button...");
        this.f229a.skipVideo();
    }
}
