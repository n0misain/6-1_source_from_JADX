package com.applovin.impl.adview;

import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;

/* renamed from: com.applovin.impl.adview.t */
class C0489t implements OnLongClickListener {
    /* renamed from: a */
    final /* synthetic */ C0487r f360a;

    C0489t(C0487r c0487r) {
        this.f360a = c0487r;
    }

    public boolean onLongClick(View view) {
        Log.d("AdWebView", "Received a LongClick event.");
        return true;
    }
}
