package com.google.android.gms.internal;

final class zzvr implements Runnable {
    private /* synthetic */ zzvp zzNk;

    zzvr(zzvp zzvp) {
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
