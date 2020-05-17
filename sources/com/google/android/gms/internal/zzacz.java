package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zzacz extends zzee implements zzacy {
    public zzacz() {
        attachInterface(this, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    public static zzacy zzv(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        return queryLocalInterface instanceof zzacy ? (zzacy) queryLocalInterface : new zzada(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza((zzadj) zzef.zza(parcel, zzadj.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                show();
                parcel2.writeNoException();
                break;
            case 3:
                zzadd zzadd;
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzadd = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    zzadd = queryLocalInterface instanceof zzadd ? (zzadd) queryLocalInterface : new zzadf(readStrongBinder);
                }
                zza(zzadd);
                parcel2.writeNoException();
                break;
            case 4:
                setUserId(parcel.readString());
                parcel2.writeNoException();
                break;
            case 5:
                boolean isLoaded = isLoaded();
                parcel2.writeNoException();
                zzef.zza(parcel2, isLoaded);
                break;
            case 6:
                pause();
                parcel2.writeNoException();
                break;
            case 7:
                resume();
                parcel2.writeNoException();
                break;
            case 8:
                destroy();
                parcel2.writeNoException();
                break;
            case 9:
                zzf(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 10:
                zzg(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 11:
                zzh(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 12:
                String mediationAdapterClassName = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 34:
                setImmersiveMode(zzef.zza(parcel));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
