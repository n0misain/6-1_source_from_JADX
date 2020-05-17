package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

@zzzn
public final class zzadh extends zzp<zzadb> {
    public zzadh() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }

    public final zzacy zza(Context context, zzuq zzuq) {
        Throwable e;
        try {
            IBinder zza = ((zzadb) zzaS(context)).zza(zzn.zzw(context), zzuq, 11020000);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
            return queryLocalInterface instanceof zzacy ? (zzacy) queryLocalInterface : new zzada(zza);
        } catch (RemoteException e2) {
            e = e2;
            zzajc.zzc("Could not get remote RewardedVideoAd.", e);
            return null;
        } catch (zzq e3) {
            e = e3;
            zzajc.zzc("Could not get remote RewardedVideoAd.", e);
            return null;
        }
    }

    protected final /* synthetic */ Object zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
        return queryLocalInterface instanceof zzadb ? (zzadb) queryLocalInterface : new zzadc(iBinder);
    }
}
