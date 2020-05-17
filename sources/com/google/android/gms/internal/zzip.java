package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;

@zzzn
public final class zzip extends zzp<zzjx> {
    public zzip() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    public final zzju zza(Context context, String str, zzuq zzuq) {
        try {
            IBinder zza = ((zzjx) zzaS(context)).zza(zzn.zzw(context), str, zzuq, 11020000);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            return queryLocalInterface instanceof zzju ? (zzju) queryLocalInterface : new zzjw(zza);
        } catch (Throwable e) {
            zzajc.zzc("Could not create remote builder for AdLoader.", e);
            return null;
        } catch (Throwable e2) {
            zzajc.zzc("Could not create remote builder for AdLoader.", e2);
            return null;
        }
    }

    protected final /* synthetic */ Object zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
        return queryLocalInterface instanceof zzjx ? (zzjx) queryLocalInterface : new zzjy(iBinder);
    }
}
