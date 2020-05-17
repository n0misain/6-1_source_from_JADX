package com.google.android.gms.internal;

final class zzvw implements Runnable {
    private /* synthetic */ zzvp zzNk;

    zzvw(zzvp zzvp) {
        this.zzNk = zzvp;
    }

    public final void run() {
        try {
            this.zzNk.zzNc.onAdOpened();
        } catch (Throwable e) {
            zzajc.zzc("Could not call onAdOpened.", e);
        }
    }
}
