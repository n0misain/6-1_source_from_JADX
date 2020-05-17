package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzfc extends zzed implements zzfb {
    zzfc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.adshield.internal.IAdShieldCreator");
    }

    public final IBinder zza(String str, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzZ = zzZ();
        zzZ.writeString(str);
        zzef.zza(zzZ, (IInterface) iObjectWrapper);
        zzZ = zza(1, zzZ);
        IBinder readStrongBinder = zzZ.readStrongBinder();
        zzZ.recycle();
        return readStrongBinder;
    }

    public final IBinder zzb(String str, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzZ = zzZ();
        zzZ.writeString(str);
        zzef.zza(zzZ, (IInterface) iObjectWrapper);
        zzZ = zza(2, zzZ);
        IBinder readStrongBinder = zzZ.readStrongBinder();
        zzZ.recycle();
        return readStrongBinder;
    }
}
