package com.applovin.impl.adview;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

/* renamed from: com.applovin.impl.adview.b */
class C0470b implements OnDismissListener {
    /* renamed from: a */
    final /* synthetic */ C0469a f237a;

    C0470b(C0469a c0469a) {
        this.f237a = c0469a;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.f237a.f197a.contractAd();
    }
}
