package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zzez extends zzee implements zzey {
    public zzez() {
        attachInterface(this, "com.google.android.gms.ads.adshield.internal.IAdShieldClient");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        String zzaf;
        boolean zza;
        IInterface zza2;
        switch (i) {
            case 1:
                zzaf = zzaf();
                parcel2.writeNoException();
                parcel2.writeString(zzaf);
                return true;
            case 2:
                zzb(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 3:
                zza = zza(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzef.zza(parcel2, zza);
                return true;
            case 4:
                zza = zzb(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzef.zza(parcel2, zza);
                return true;
            case 5:
                zzk(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                zza2 = zza(zza.zzM(parcel.readStrongBinder()), zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzef.zza(parcel2, zza2);
                return true;
            case 7:
                zzaf = zzc(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeString(zzaf);
                return true;
            case 8:
                zzaf = zza(zza.zzM(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(zzaf);
                return true;
            case 9:
                zzd(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 10:
                zza2 = zzb(zza.zzM(parcel.readStrongBinder()), zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzef.zza(parcel2, zza2);
                return true;
            case 11:
                zza = zzb(parcel.readString(), zzef.zza(parcel));
                parcel2.writeNoException();
                zzef.zza(parcel2, zza);
                return true;
            case 12:
                zzaf = zza(zza.zzM(parcel.readStrongBinder()), parcel.createByteArray());
                parcel2.writeNoException();
                parcel2.writeString(zzaf);
                return true;
            default:
                return false;
        }
    }
}
