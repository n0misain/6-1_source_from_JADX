package com.applovin.impl.adview;

import java.util.concurrent.TimeUnit;

class aq implements ab {
    /* renamed from: a */
    final /* synthetic */ long f222a;
    /* renamed from: b */
    final /* synthetic */ aj f223b;

    aq(aj ajVar, long j) {
        this.f223b = ajVar;
        this.f222a = j;
    }

    /* renamed from: a */
    public void mo2158a() {
        if (this.f223b.f14B != null) {
            long toSeconds = TimeUnit.MILLISECONDS.toSeconds(this.f222a - ((long) this.f223b.videoView.getCurrentPosition()));
            if (toSeconds <= 0) {
                this.f223b.f14B.setVisibility(8);
                this.f223b.f36p = true;
            } else if (this.f223b.m92s()) {
                this.f223b.f14B.m413a((int) toSeconds);
            }
        }
    }

    /* renamed from: b */
    public boolean mo2159b() {
        return this.f223b.m92s();
    }
}
