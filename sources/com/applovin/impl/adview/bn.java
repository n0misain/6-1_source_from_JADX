package com.applovin.impl.adview;

import android.view.Window;
import com.applovin.impl.sdk.C0503g;
import com.applovin.impl.sdk.ae;
import com.applovin.impl.sdk.fk;
import com.applovin.sdk.AppLovinAd;

class bn implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f271a;
    /* renamed from: b */
    final /* synthetic */ String f272b;
    /* renamed from: c */
    final /* synthetic */ bj f273c;

    bn(bj bjVar, AppLovinAd appLovinAd, String str) {
        this.f273c = bjVar;
        this.f271a = appLovinAd;
        this.f272b = str;
    }

    public void run() {
        this.f273c.f261k = System.currentTimeMillis();
        this.f273c.f260j.m888a(this.f273c.f259i.m358b());
        this.f273c.f260j.m890b(-1);
        this.f273c.f255e.renderAd(this.f271a, this.f272b);
        ae aeVar = (ae) this.f271a;
        if (aeVar.m158y()) {
            Window window = this.f273c.getWindow();
            if (window != null) {
                window.setFlags(16777216, 16777216);
            }
        }
        if (!(aeVar instanceof C0503g)) {
            return;
        }
        if (((C0503g) aeVar).m1127h()) {
            this.f273c.f253c.mo2288d("InterstitialAdDialog", "Skip showing of close button");
            return;
        }
        this.f273c.m320a(aeVar.m150q());
        float p = aeVar.m149p();
        if (p > 0.0f) {
            this.f273c.m318a(fk.m1092c(p));
            return;
        }
        this.f273c.f257g.setVisibility(0);
        this.f273c.f257g.setClickable(true);
    }
}
