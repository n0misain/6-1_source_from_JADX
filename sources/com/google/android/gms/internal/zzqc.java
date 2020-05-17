package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

@zzzn
public final class zzqc extends zzp<zzoz> {
    public zzqc() {
        super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
    }

    public final zzow zzb(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        Throwable e;
        try {
            IBinder zza = ((zzoz) zzaS(context)).zza(zzn.zzw(context), zzn.zzw(frameLayout), zzn.zzw(frameLayout2), 11020000);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
            return queryLocalInterface instanceof zzow ? (zzow) queryLocalInterface : new zzoy(zza);
        } catch (RemoteException e2) {
            e = e2;
            zzajc.zzc("Could not create remote NativeAdViewDelegate.", e);
            return null;
        } catch (zzq e3) {
            e = e3;
            zzajc.zzc("Could not create remote NativeAdViewDelegate.", e);
            return null;
        }
    }

    protected final /* synthetic */ Object zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
        return queryLocalInterface instanceof zzoz ? (zzoz) queryLocalInterface : new zzpa(iBinder);
    }
}
