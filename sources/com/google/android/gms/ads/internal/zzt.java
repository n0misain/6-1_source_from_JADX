package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzns;

final class zzt implements Runnable {
    private /* synthetic */ zzq zzti;
    private /* synthetic */ zzns zztk;

    zzt(zzq zzq, zzns zzns) {
        this.zzti = zzq;
        this.zztk = zzns;
    }

    public final void run() {
        try {
            if (this.zzti.zzsP.zzwg != null) {
                this.zzti.zzsP.zzwg.zza(this.zztk);
            }
        } catch (Throwable e) {
            zzajc.zzc("Could not call OnContentAdLoadedListener.onContentAdLoaded().", e);
        }
    }
}
