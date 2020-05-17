package com.google.android.gms.internal;

final class zzvs implements Runnable {
    private /* synthetic */ zzvp zzNk;

    zzvs(zzvp zzvp) {
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
