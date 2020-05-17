package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bo implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ bn f589a;

    bo(bn bnVar) {
        this.f589a = bnVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f589a.f588a.f585b.continueVideo();
    }
}
