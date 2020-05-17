package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class zzaan extends zzee implements zzaam {
    public zzaan() {
        attachInterface(this, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzaas zzaas = null;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        IBinder readStrongBinder;
        IInterface queryLocalInterface;
        switch (i) {
            case 1:
                Parcelable zzc = zzc((zzaae) zzef.zza(parcel, zzaae.CREATOR));
                parcel2.writeNoException();
                zzef.zzb(parcel2, zzc);
                break;
            case 2:
                zzaap zzaar;
                zzaae zzaae = (zzaae) zzef.zza(parcel, zzaae.CREATOR);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdResponseListener");
                    zzaar = queryLocalInterface instanceof zzaap ? (zzaap) queryLocalInterface : new zzaar(readStrongBinder);
                }
                zza(zzaae, zzaar);
                parcel2.writeNoException();
                break;
            case 3:
                zzaax zzaax = (zzaax) zzef.zza(parcel, zzaax.CREATOR);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonResponseListener");
                    zzaas = queryLocalInterface instanceof zzaas ? (zzaas) queryLocalInterface : new zzaat(readStrongBinder);
                }
                zza(zzaax, zzaas);
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
