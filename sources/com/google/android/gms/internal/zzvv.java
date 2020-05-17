package com.google.android.gms.internal;

final class zzvv implements Runnable {
    private /* synthetic */ zzvp zzNk;

    zzvv(zzvp zzvp) {
        this.zzNk = zzvp;
    }

    public final void run() {
        try {
            this.zzNk.zzNc.onAdLeftApplication();
        } catch (Throwable e) {
            zzajc.zzc("Could not call onAdLeftApplication.", e);
        }
    }
}
