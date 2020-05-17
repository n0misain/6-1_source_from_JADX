package com.google.android.gms.internal;

final class zzlm implements Runnable {
    private /* synthetic */ zzll zzBs;

    zzlm(zzll zzll) {
        this.zzBs = zzll;
    }

    public final void run() {
        if (this.zzBs.zzBr.zztK != null) {
            try {
                this.zzBs.zzBr.zztK.onAdFailedToLoad(1);
            } catch (Throwable e) {
                zzajc.zzc("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
