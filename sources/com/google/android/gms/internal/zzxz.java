package com.google.android.gms.internal;

import android.content.Context;
import io.fabric.sdk.android.services.network.HttpRequest;

@zzzn
public class zzxz extends zzxr implements zzakf {
    zzxz(Context context, zzafg zzafg, zzaka zzaka, zzxy zzxy) {
        super(context, zzafg, zzaka, zzxy);
    }

    protected final void zzgo() {
        if (this.zzQR.errorCode == -2) {
            this.zzJH.zziw().zza((zzakf) this);
            zzgq();
            zzajc.zzaC("Loading HTML in WebView.");
            this.zzJH.loadDataWithBaseURL(this.zzQR.zzPi, this.zzQR.body, "text/html", HttpRequest.CHARSET_UTF8, null);
        }
    }

    protected void zzgq() {
    }
}
