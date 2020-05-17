package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbs;

final class zzabr implements Runnable {
    private /* synthetic */ zzaae zzUG;
    private /* synthetic */ zzaap zzUH;
    private /* synthetic */ zzabm zzUI;

    zzabr(zzabm zzabm, zzaae zzaae, zzaap zzaap) {
        this.zzUI = zzabm;
        this.zzUG = zzaae;
        this.zzUH = zzaap;
    }

    public final void run() {
        zzaai zzc;
        try {
            zzc = this.zzUI.zzc(this.zzUG);
        } catch (Throwable e) {
            zzbs.zzbD().zza(e, "AdRequestServiceImpl.loadAdAsync");
            zzajc.zzc("Could not fetch ad response due to an Exception.", e);
            zzc = null;
        }
        if (zzc == null) {
            zzc = new zzaai(0);
        }
        try {
            this.zzUH.zza(zzc);
        } catch (Throwable e2) {
            zzajc.zzc("Fail to forward ad response.", e2);
        }
    }
}
