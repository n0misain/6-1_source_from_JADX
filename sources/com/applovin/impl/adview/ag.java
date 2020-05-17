package com.applovin.impl.adview;

import android.view.View;
import android.view.View.OnClickListener;

class ag implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ae f212a;

    ag(ae aeVar) {
        this.f212a = aeVar;
    }

    public void onClick(View view) {
        if (this.f212a.f210g.isClickable()) {
            this.f212a.f210g.performClick();
        }
    }
}
