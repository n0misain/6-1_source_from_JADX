package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bh implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ bg f576a;

    bh(bg bgVar) {
        this.f576a = bgVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f576a.f575a.f574f.schedule(new bi(this), 200);
    }
}
