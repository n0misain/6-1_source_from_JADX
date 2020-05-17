package com.google.android.gms.internal;

import java.util.concurrent.Callable;

public final class zzdq implements Callable {
    private final zzdb zzpJ;
    private final zzax zzro;

    public zzdq(zzdb zzdb, zzax zzax) {
        this.zzpJ = zzdb;
        this.zzro = zzax;
    }

    private final Void zzV() throws Exception {
        if (this.zzpJ.zzL() != null) {
            this.zzpJ.zzL().get();
        }
        adp zzK = this.zzpJ.zzK();
        if (zzK != null) {
            try {
                synchronized (this.zzro) {
                    adp.zza(this.zzro, adp.zzc(zzK));
                }
            } catch (ado e) {
            }
        }
        return null;
    }

    public final /* synthetic */ Object call() throws Exception {
        return zzV();
    }
}
