package com.applovin.impl.adview;

import com.applovin.impl.sdk.C0503g;

/* renamed from: com.applovin.impl.adview.a */
class C0469a implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f197a;

    C0469a(AdViewControllerImpl adViewControllerImpl) {
        this.f197a = adViewControllerImpl;
    }

    public void run() {
        if (this.f197a.f179r == null && (this.f197a.f176o instanceof C0503g)) {
            if (this.f197a.f163b != null) {
                this.f197a.f163b.removeView(this.f197a.f171j);
            }
            this.f197a.f179r = new ae((C0503g) this.f197a.f176o, this.f197a.f171j, this.f197a.f162a, this.f197a.f164c);
            this.f197a.f179r.setOnDismissListener(new C0470b(this));
            this.f197a.f179r.show();
        }
    }
}
