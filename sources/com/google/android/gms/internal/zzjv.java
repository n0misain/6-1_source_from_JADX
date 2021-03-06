package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

public abstract class zzjv extends zzee implements zzju {
    public zzjv() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzkk zzkk = null;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        IInterface zzaZ;
        IBinder readStrongBinder;
        switch (i) {
            case 1:
                zzaZ = zzaZ();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzaZ);
                break;
            case 2:
                zzjo zzjq;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzaZ = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    zzjq = zzaZ instanceof zzjo ? (zzjo) zzaZ : new zzjq(readStrongBinder);
                }
                zzb(zzjq);
                parcel2.writeNoException();
                break;
            case 3:
                zza(zzpo.zzl(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 4:
                zza(zzpr.zzm(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 5:
                zza(parcel.readString(), zzpx.zzo(parcel.readStrongBinder()), zzpu.zzn(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 6:
                zza((zzon) zzef.zza(parcel, zzon.CREATOR));
                parcel2.writeNoException();
                break;
            case 7:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzaZ = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    zzkk = zzaZ instanceof zzkk ? (zzkk) zzaZ : new zzkm(readStrongBinder);
                }
                zzb(zzkk);
                parcel2.writeNoException();
                break;
            case 8:
                zza(zzqa.zzp(parcel.readStrongBinder()), (zziv) zzef.zza(parcel, zziv.CREATOR));
                parcel2.writeNoException();
                break;
            case 9:
                zza((PublisherAdViewOptions) zzef.zza(parcel, PublisherAdViewOptions.CREATOR));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
