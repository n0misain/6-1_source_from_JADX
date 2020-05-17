package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzjy extends zzed implements zzjx {
    zzjy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
    }

    public final IBinder zza(IObjectWrapper iObjectWrapper, String str, zzuq zzuq, int i) throws RemoteException {
        Parcel zzZ = zzZ();
        zzef.zza(zzZ, (IInterface) iObjectWrapper);
        zzZ.writeString(str);
        zzef.zza(zzZ, (IInterface) zzuq);
        zzZ.writeInt(11020000);
        zzZ = zza(1, zzZ);
        IBinder readStrongBinder = zzZ.readStrongBinder();
        zzZ.recycle();
        return readStrongBinder;
    }
}
