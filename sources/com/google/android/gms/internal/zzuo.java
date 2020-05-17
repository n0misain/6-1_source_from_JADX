package com.google.android.gms.internal;

final class zzuo implements Runnable {
    private /* synthetic */ zzuh zzMX;

    zzuo(zzun zzun, zzuh zzuh) {
        this.zzMX = zzuh;
    }

    public final void run() {
        try {
            this.zzMX.zzMH.destroy();
        } catch (Throwable e) {
            zzajc.zzc("Could not destroy mediation adapter.", e);
        }
    }
}
