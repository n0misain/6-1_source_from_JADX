package com.google.android.gms.internal;

final class zzvx implements Runnable {
    private /* synthetic */ zzvp zzNk;

    zzvx(zzvp zzvp) {
        this.zzNk = zzvp;
    }

    public final void run() {
        try {
            this.zzNk.zzNc.onAdLoaded();
        } catch (Throwable e) {
            zzajc.zzc("Could not call onAdLoaded.", e);
        }
    }
}
