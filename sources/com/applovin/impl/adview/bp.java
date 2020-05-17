package com.applovin.impl.adview;

import android.view.View;
import android.view.View.OnClickListener;

class bp implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ bj f275a;

    bp(bj bjVar) {
        this.f275a = bjVar;
    }

    public void onClick(View view) {
        if (this.f275a.f257g.isClickable()) {
            this.f275a.f257g.performClick();
        }
    }
}
