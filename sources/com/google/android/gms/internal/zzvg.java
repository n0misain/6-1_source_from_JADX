package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import java.util.List;

public abstract class zzvg extends zzee implements zzvf {
    public zzvg() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        String headline;
        IInterface zzem;
        boolean overrideImpressionRecording;
        switch (i) {
            case 2:
                headline = getHeadline();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                return true;
            case 3:
                List images = getImages();
                parcel2.writeNoException();
                parcel2.writeList(images);
                return true;
            case 4:
                headline = getBody();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                return true;
            case 5:
                zzem = zzem();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzem);
                return true;
            case 6:
                headline = getCallToAction();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                return true;
            case 7:
                headline = getAdvertiser();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                return true;
            case 8:
                recordImpression();
                parcel2.writeNoException();
                return true;
            case 9:
                zzl(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 10:
                zzm(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 11:
                overrideImpressionRecording = getOverrideImpressionRecording();
                parcel2.writeNoException();
                zzef.zza(parcel2, overrideImpressionRecording);
                return true;
            case 12:
                overrideImpressionRecording = getOverrideClickHandling();
                parcel2.writeNoException();
                zzef.zza(parcel2, overrideImpressionRecording);
                return true;
            case 13:
                Parcelable extras = getExtras();
                parcel2.writeNoException();
                zzef.zzb(parcel2, extras);
                return true;
            case 14:
                zzn(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 15:
                zzem = zzfw();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzem);
                return true;
            case 16:
                zzem = getVideoController();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzem);
                return true;
            default:
                return false;
        }
    }
}
