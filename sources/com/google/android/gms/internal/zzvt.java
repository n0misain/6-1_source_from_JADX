package com.google.android.gms.internal;

final class zzvt implements Runnable {
    private /* synthetic */ zzvp zzNk;

    zzvt(zzvp zzvp) {
        this.zzNk = zzvp;
    }

    public final void run() {
        try {
            this.zzNk.zzNc.onAdClosed();
        } catch (Throwable e) {
            zzajc.zzc("Could not call onAdClosed.", e);
        }
    }
}
