package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

final class zzjb extends zza<zzjz> {
    private /* synthetic */ zziv zzAG;
    private /* synthetic */ zziz zzAI;
    private /* synthetic */ String zztD;
    private /* synthetic */ Context zztF;

    zzjb(zziz zziz, Context context, zziv zziv, String str) {
        this.zzAI = zziz;
        this.zztF = context;
        this.zzAG = zziv;
        this.zztD = str;
        super(zziz);
    }

    public final /* synthetic */ Object zza(zzkh zzkh) throws RemoteException {
        return zzkh.createSearchAdManager(zzn.zzw(this.zztF), this.zzAG, this.zztD, 11020000);
    }

    public final /* synthetic */ Object zzdo() throws RemoteException {
        zzjz zza = this.zzAI.zzAA.zza(this.zztF, this.zzAG, this.zztD, null, 3);
        if (zza != null) {
            return zza;
        }
        zziz.zzb(this.zztF, "search");
        return new zzln();
    }
}
