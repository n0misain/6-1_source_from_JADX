package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bp implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ bn f590a;

    bp(bn bnVar) {
        this.f590a = bnVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f590a.f588a.f585b.skipVideo();
    }
}
