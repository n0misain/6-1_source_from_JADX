package com.google.android.gms.internal;

final class st implements uy<sn, Void> {
    private /* synthetic */ so zzceR;

    st(so soVar) {
        this.zzceR = soVar;
    }

    public final /* synthetic */ Object zza(qr qrVar, Object obj, Object obj2) {
        sn snVar = (sn) obj;
        vt zzIv;
        if (qrVar.isEmpty() || !snVar.zzHo()) {
            for (vu zzIv2 : snVar.zzHn()) {
                zzIv = zzIv2.zzIv();
                this.zzceR.zzceL.zza(so.zzd(zzIv), this.zzceR.zze(zzIv));
            }
        } else {
            zzIv = snVar.zzHp().zzIv();
            this.zzceR.zzceL.zza(so.zzd(zzIv), this.zzceR.zze(zzIv));
        }
        return null;
    }
}
