package com.applovin.impl.adview;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/* renamed from: com.applovin.impl.adview.s */
class C0488s implements OnTouchListener {
    /* renamed from: a */
    final /* synthetic */ C0487r f359a;

    C0488s(C0487r c0487r) {
        this.f359a = c0487r;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!view.hasFocus()) {
            view.requestFocus();
        }
        return false;
    }
}
