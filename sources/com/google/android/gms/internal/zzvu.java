package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;

final class zzvu implements Runnable {
    private /* synthetic */ zzvp zzNk;
    private /* synthetic */ ErrorCode zzNl;

    zzvu(zzvp zzvp, ErrorCode errorCode) {
        this.zzNk = zzvp;
        this.zzNl = errorCode;
    }

    public final void run() {
        try {
            this.zzNk.zzNc.onAdFailedToLoad(zzwb.zza(this.zzNl));
        } catch (Throwable e) {
            zzajc.zzc("Could not call onAdFailedToLoad.", e);
        }
    }
}
