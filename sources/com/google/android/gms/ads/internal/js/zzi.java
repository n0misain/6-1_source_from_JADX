package com.google.android.gms.ads.internal.js;

import io.fabric.sdk.android.services.network.HttpRequest;

final class zzi implements Runnable {
    private /* synthetic */ zze zzKW;
    private /* synthetic */ String zzKY;

    zzi(zze zze, String str) {
        this.zzKW = zze;
        this.zzKY = str;
    }

    public final void run() {
        this.zzKW.zzJH.loadData(this.zzKY, "text/html", HttpRequest.CHARSET_UTF8);
    }
}
