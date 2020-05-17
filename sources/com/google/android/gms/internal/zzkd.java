package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzkd extends zzed implements zzkc {
    zzkd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdManagerCreator");
    }

    public final IBinder zza(IObjectWrapper iObjectWrapper, zziv zziv, String str, zzuq zzuq, int i, int i2) throws RemoteException {
        Parcel zzZ = zzZ();
        zzef.zza(zzZ, (IInterface) iObjectWrapper);
        zzef.zza(zzZ, (Parcelable) zziv);
        zzZ.writeString(str);
        zzef.zza(zzZ, (IInterface) zzuq);
        zzZ.writeInt(11020000);
        zzZ.writeInt(i2);
        zzZ = zza(2, zzZ);
        IBinder readStrongBinder = zzZ.readStrongBinder();
        zzZ.recycle();
        return readStrongBinder;
    }
}
