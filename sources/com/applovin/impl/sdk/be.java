package com.applovin.impl.sdk;

import android.content.Intent;
import com.applovin.adview.AppLovinConfirmationActivity;

class be implements Runnable {
    /* renamed from: a */
    final /* synthetic */ bd f568a;

    be(bd bdVar) {
        this.f568a = bdVar;
    }

    public void run() {
        String str = (String) this.f568a.f565a.get(dj.f767V);
        String b = this.f568a.m582b();
        String str2 = (String) this.f568a.f565a.get(dj.aa);
        if (C0516u.m1159a(AppLovinConfirmationActivity.class, this.f568a.f567c)) {
            try {
                Intent intent = new Intent(this.f568a.f567c, AppLovinConfirmationActivity.class);
                intent.putExtra("dialog_title", str);
                intent.putExtra("dialog_body", b);
                intent.putExtra("dialog_button_text", str2);
                this.f568a.f567c.startActivity(intent);
                return;
            } catch (Throwable th) {
                this.f568a.m581a(b, th);
                return;
            }
        }
        this.f568a.m581a(b, null);
    }
}
