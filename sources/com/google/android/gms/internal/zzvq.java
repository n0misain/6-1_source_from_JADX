package com.google.android.gms.internal;

final class zzvq implements Runnable {
    private /* synthetic */ zzvp zzNk;

    zzvq(zzvp zzvp) {
        this.zzNk = zzvp;
    }

    public final void run() {
        try {
            this.zzNk.zzNc.onAdClicked();
        } catch (Throwable e) {
            zzajc.zzc("Could not call onAdClicked.", e);
        }
    }
}
