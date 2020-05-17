package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zze extends zzee implements zzd {
    public static zzd zzz(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.account.IWorkAccountService");
        return queryLocalInterface instanceof zzd ? (zzd) queryLocalInterface : new zzf(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zza zza = null;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        IBinder readStrongBinder;
        IInterface queryLocalInterface;
        switch (i) {
            case 1:
                zzO(zzef.zza(parcel));
                break;
            case 2:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.auth.account.IWorkAccountCallback");
                    zza = queryLocalInterface instanceof zza ? (zza) queryLocalInterface : new zzc(readStrongBinder);
                }
                zza(zza, parcel.readString());
                break;
            case 3:
                zza zza2;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zza2 = null;
                } else {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.auth.account.IWorkAccountCallback");
                    if (queryLocalInterface instanceof zza) {
                        zza2 = (zza) queryLocalInterface;
                    } else {
                        Object zzc = new zzc(readStrongBinder);
                    }
                }
                zza(zza2, (Account) zzef.zza(parcel, Account.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
