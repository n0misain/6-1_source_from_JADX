package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public final class zzfa extends zzed implements zzey {
    zzfa(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.adshield.internal.IAdShieldClient");
    }

    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel zzZ = zzZ();
        zzef.zza(zzZ, (IInterface) iObjectWrapper);
        zzef.zza(zzZ, (IInterface) iObjectWrapper2);
        zzZ = zza(6, zzZ);
        IObjectWrapper zzM = zza.zzM(zzZ.readStrongBinder());
        zzZ.recycle();
        return zzM;
    }

    public final String zza(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        Parcel zzZ = zzZ();
        zzef.zza(zzZ, (IInterface) iObjectWrapper);
        zzZ.writeString(str);
        zzZ = zza(8, zzZ);
        String readString = zzZ.readString();
        zzZ.recycle();
        return readString;
    }

    public final String zza(IObjectWrapper iObjectWrapper, byte[] bArr) throws RemoteException {
        Parcel zzZ = zzZ();
        zzef.zza(zzZ, (IInterface) iObjectWrapper);
        zzZ.writeByteArray(bArr);
        zzZ = zza(12, zzZ);
        String readString = zzZ.readString();
        zzZ.recycle();
        return readString;
    }

    public final boolean zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzZ = zzZ();
        zzef.zza(zzZ, (IInterface) iObjectWrapper);
        zzZ = zza(3, zzZ);
        boolean zza = zzef.zza(zzZ);
        zzZ.recycle();
        return zza;
    }

    public final String zzaf() throws RemoteException {
        Parcel zza = zza(1, zzZ());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel zzZ = zzZ();
        zzef.zza(zzZ, (IInterface) iObjectWrapper);
        zzef.zza(zzZ, (IInterface) iObjectWrapper2);
        zzZ = zza(10, zzZ);
        IObjectWrapper zzM = zza.zzM(zzZ.readStrongBinder());
        zzZ.recycle();
        return zzM;
    }

    public final void zzb(String str, String str2) throws RemoteException {
        Parcel zzZ = zzZ();
        zzZ.writeString(str);
        zzZ.writeString(str2);
        zzb(2, zzZ);
    }

    public final boolean zzb(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzZ = zzZ();
        zzef.zza(zzZ, (IInterface) iObjectWrapper);
        zzZ = zza(4, zzZ);
        boolean zza = zzef.zza(zzZ);
        zzZ.recycle();
        return zza;
    }

    public final boolean zzb(String str, boolean z) throws RemoteException {
        Parcel zzZ = zzZ();
        zzZ.writeString(str);
        zzef.zza(zzZ, z);
        zzZ = zza(11, zzZ);
        boolean zza = zzef.zza(zzZ);
        zzZ.recycle();
        return zza;
    }

    public final String zzc(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzZ = zzZ();
        zzef.zza(zzZ, (IInterface) iObjectWrapper);
        zzZ = zza(7, zzZ);
        String readString = zzZ.readString();
        zzZ.recycle();
        return readString;
    }

    public final void zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzZ = zzZ();
        zzef.zza(zzZ, (IInterface) iObjectWrapper);
        zzb(9, zzZ);
    }

    public final void zzk(String str) throws RemoteException {
        Parcel zzZ = zzZ();
        zzZ.writeString(str);
        zzb(5, zzZ);
    }
}
