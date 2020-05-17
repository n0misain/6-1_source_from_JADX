package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bj implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ bg f578a;

    bj(bg bgVar) {
        this.f578a = bgVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f578a.f575a.f570b.m556a(this.f578a.f575a.f573e);
    }
}
