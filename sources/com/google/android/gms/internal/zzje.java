package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

final class zzje extends zza<zzkn> {
    private /* synthetic */ zziz zzAI;
    private /* synthetic */ Context zztF;

    zzje(zziz zziz, Context context) {
        this.zzAI = zziz;
        this.zztF = context;
        super(zziz);
    }

    public final /* synthetic */ Object zza(zzkh zzkh) throws RemoteException {
        return zzkh.getMobileAdsSettingsManagerWithClientJarVersion(zzn.zzw(this.zztF), 11020000);
    }

    public final /* synthetic */ Object zzdo() throws RemoteException {
        zzkn zzh = this.zzAI.zzAC.zzh(this.zztF);
        if (zzh != null) {
            return zzh;
        }
        zziz.zzb(this.zztF, "mobile_ads_settings");
        return new zzlp();
    }
}
