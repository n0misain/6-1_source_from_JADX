package com.applovin.impl.sdk;

import android.app.AlertDialog.Builder;

class bq implements Runnable {
    /* renamed from: a */
    final /* synthetic */ bl f591a;

    bq(bl blVar) {
        this.f591a = blVar;
    }

    public void run() {
        Builder builder = new Builder(this.f591a.f585b);
        builder.setTitle((CharSequence) this.f591a.f584a.get(dj.ah));
        builder.setMessage((CharSequence) this.f591a.f584a.get(dj.ai));
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence) this.f591a.f584a.get(dj.ak), null);
        builder.setNegativeButton((CharSequence) this.f591a.f584a.get(dj.aj), new br(this));
        this.f591a.f586c = builder.show();
    }
}
