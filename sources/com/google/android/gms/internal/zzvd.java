package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import java.util.List;

public abstract class zzvd extends zzee implements zzvc {
    public zzvd() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        String headline;
        IInterface zzeh;
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
                zzeh = zzeh();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzeh);
                return true;
            case 6:
                headline = getCallToAction();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                return true;
            case 7:
                double starRating = getStarRating();
                parcel2.writeNoException();
                parcel2.writeDouble(starRating);
                return true;
            case 8:
                headline = getStore();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                return true;
            case 9:
                headline = getPrice();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                return true;
            case 10:
                recordImpression();
                parcel2.writeNoException();
                return true;
            case 11:
                zzl(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 12:
                zzm(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 13:
                overrideImpressionRecording = getOverrideImpressionRecording();
                parcel2.writeNoException();
                zzef.zza(parcel2, overrideImpressionRecording);
                return true;
            case 14:
                overrideImpressionRecording = getOverrideClickHandling();
                parcel2.writeNoException();
                zzef.zza(parcel2, overrideImpressionRecording);
                return true;
            case 15:
                Parcelable extras = getExtras();
                parcel2.writeNoException();
                zzef.zzb(parcel2, extras);
                return true;
            case 16:
                zzn(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 17:
                zzeh = getVideoController();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzeh);
                return true;
            case 18:
                zzeh = zzfw();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzeh);
                return true;
            default:
                return false;
        }
    }
}
