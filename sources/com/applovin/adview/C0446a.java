package com.applovin.adview;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.applovin.adview.a */
class C0446a implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinConfirmationActivity f55a;

    C0446a(AppLovinConfirmationActivity appLovinConfirmationActivity) {
        this.f55a = appLovinConfirmationActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f55a.finish();
    }
}
