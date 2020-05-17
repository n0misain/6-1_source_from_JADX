package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zznf extends zzee implements zzne {
    public zznf() {
        attachInterface(this, "com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        String zzdX;
        switch (i) {
            case 1:
                zzdX = zzdX();
                parcel2.writeNoException();
                parcel2.writeString(zzdX);
                return true;
            case 2:
                zzdX = getContent();
                parcel2.writeNoException();
                parcel2.writeString(zzdX);
                return true;
            case 3:
                zzi(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 4:
                recordClick();
                parcel2.writeNoException();
                return true;
            case 5:
                recordImpression();
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
