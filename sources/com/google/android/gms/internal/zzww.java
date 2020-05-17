package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;

@zzzn
public final class zzww extends zzp<zzxa> {
    public zzww() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    protected final /* synthetic */ Object zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlayCreator");
        return queryLocalInterface instanceof zzxa ? (zzxa) queryLocalInterface : new zzxb(iBinder);
    }

    public final zzwx zze(Activity activity) {
        try {
            IBinder zzp = ((zzxa) zzaS(activity)).zzp(zzn.zzw(activity));
            if (zzp == null) {
                return null;
            }
            IInterface queryLocalInterface = zzp.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
            return queryLocalInterface instanceof zzwx ? (zzwx) queryLocalInterface : new zzwz(zzp);
        } catch (Throwable e) {
            zzajc.zzc("Could not create remote AdOverlay.", e);
            return null;
        } catch (Throwable e2) {
            zzajc.zzc("Could not create remote AdOverlay.", e2);
            return null;
        }
    }
}
