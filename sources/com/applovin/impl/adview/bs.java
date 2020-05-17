package com.applovin.impl.adview;

class bs implements Runnable {
    /* renamed from: a */
    final /* synthetic */ bj f278a;

    private bs(bj bjVar) {
        this.f278a = bjVar;
    }

    public void run() {
        try {
            this.f278a.dismiss();
        } catch (Throwable th) {
            if (this.f278a.f253c != null) {
                this.f278a.f253c.mo2290e("InterstitialAdDialog", "dismiss() threw exception", th);
            }
        }
    }
}
