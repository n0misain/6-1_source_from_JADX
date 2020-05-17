package com.applovin.impl.sdk;

import android.app.AlertDialog.Builder;

class bg implements Runnable {
    /* renamed from: a */
    final /* synthetic */ bf f575a;

    bg(bf bfVar) {
        this.f575a = bfVar;
    }

    public void run() {
        Builder builder = new Builder(this.f575a.f571c);
        builder.setTitle((CharSequence) this.f575a.f569a.get(dj.f763R));
        builder.setMessage((CharSequence) this.f575a.f569a.get(dj.f764S));
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence) this.f575a.f569a.get(dj.f765T), new bh(this));
        builder.setNegativeButton((CharSequence) this.f575a.f569a.get(dj.f766U), new bj(this));
        builder.show();
    }
}
