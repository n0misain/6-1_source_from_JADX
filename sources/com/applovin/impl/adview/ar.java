package com.applovin.impl.adview;

class ar implements ab {
    /* renamed from: a */
    final /* synthetic */ aj f224a;

    ar(aj ajVar) {
        this.f224a = ajVar;
    }

    /* renamed from: a */
    public void mo2158a() {
        if (this.f224a.f19G == null) {
            return;
        }
        if (this.f224a.shouldContinueFullLengthVideoCountdown()) {
            this.f224a.f19G.setProgress((int) (((float) this.f224a.settingsProxy.am()) * (((float) this.f224a.videoView.getCurrentPosition()) / ((float) this.f224a.videoView.getDuration()))));
            return;
        }
        this.f224a.f19G.setVisibility(8);
    }

    /* renamed from: b */
    public boolean mo2159b() {
        return this.f224a.shouldContinueFullLengthVideoCountdown();
    }
}
