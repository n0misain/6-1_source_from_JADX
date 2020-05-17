package com.applovin.impl.sdk;

import android.app.AlertDialog.Builder;

class bn implements Runnable {
    /* renamed from: a */
    final /* synthetic */ bl f588a;

    bn(bl blVar) {
        this.f588a = blVar;
    }

    public void run() {
        Builder builder = new Builder(this.f588a.f585b);
        builder.setTitle((CharSequence) this.f588a.f584a.get(dj.ac));
        builder.setMessage((CharSequence) this.f588a.f584a.get(dj.ad));
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence) this.f588a.f584a.get(dj.af), new bo(this));
        builder.setNegativeButton((CharSequence) this.f588a.f584a.get(dj.ae), new bp(this));
        this.f588a.f586c = builder.show();
    }
}
