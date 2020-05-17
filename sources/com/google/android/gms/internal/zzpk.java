package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import java.util.List;

public abstract class zzpk extends zzee implements zzpj {
    public zzpk() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }

    public static zzpj zzk(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        return queryLocalInterface instanceof zzpj ? (zzpj) queryLocalInterface : new zzpl(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        String zzP;
        IInterface zzQ;
        switch (i) {
            case 1:
                zzP = zzP(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(zzP);
                return true;
            case 2:
                zzQ = zzQ(parcel.readString());
                parcel2.writeNoException();
                zzef.zza(parcel2, zzQ);
                return true;
            case 3:
                List availableAssetNames = getAvailableAssetNames();
                parcel2.writeNoException();
                parcel2.writeStringList(availableAssetNames);
                return true;
            case 4:
                zzP = getCustomTemplateId();
                parcel2.writeNoException();
                parcel2.writeString(zzP);
                return true;
            case 5:
                performClick(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                recordImpression();
                parcel2.writeNoException();
                return true;
            case 7:
                zzQ = getVideoController();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzQ);
                return true;
            case 8:
                destroy();
                parcel2.writeNoException();
                return true;
            case 9:
                zzQ = zzen();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzQ);
                return true;
            case 10:
                boolean zzj = zzj(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzef.zza(parcel2, zzj);
                return true;
            case 11:
                zzQ = zzei();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzQ);
                return true;
            default:
                return false;
        }
    }
}
