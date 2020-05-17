package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zzuu extends zzee implements zzut {
    public zzuu() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzuw zzuw = null;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        IObjectWrapper zzM;
        zziv zziv;
        zzir zzir;
        String readString;
        IBinder readStrongBinder;
        zzuw zzuw2;
        IInterface queryLocalInterface;
        String readString2;
        zzir zzir2;
        boolean isInitialized;
        Parcelable zzfs;
        switch (i) {
            case 1:
                zzM = zza.zzM(parcel.readStrongBinder());
                zziv = (zziv) zzef.zza(parcel, zziv.CREATOR);
                zzir = (zzir) zzef.zza(parcel, zzir.CREATOR);
                readString = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzuw2 = null;
                } else {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzuw2 = queryLocalInterface instanceof zzuw ? (zzuw) queryLocalInterface : new zzuy(readStrongBinder);
                }
                zza(zzM, zziv, zzir, readString, zzuw2);
                parcel2.writeNoException();
                break;
            case 2:
                queryLocalInterface = getView();
                parcel2.writeNoException();
                zzef.zza(parcel2, queryLocalInterface);
                break;
            case 3:
                IObjectWrapper zzM2 = zza.zzM(parcel.readStrongBinder());
                zzir zzir3 = (zzir) zzef.zza(parcel, zzir.CREATOR);
                readString2 = parcel.readString();
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzuw = queryLocalInterface2 instanceof zzuw ? (zzuw) queryLocalInterface2 : new zzuy(readStrongBinder2);
                }
                zza(zzM2, zzir3, readString2, zzuw);
                parcel2.writeNoException();
                break;
            case 4:
                showInterstitial();
                parcel2.writeNoException();
                break;
            case 5:
                destroy();
                parcel2.writeNoException();
                break;
            case 6:
                zzM = zza.zzM(parcel.readStrongBinder());
                zziv = (zziv) zzef.zza(parcel, zziv.CREATOR);
                zzir = (zzir) zzef.zza(parcel, zzir.CREATOR);
                readString = parcel.readString();
                String readString3 = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzuw = queryLocalInterface instanceof zzuw ? (zzuw) queryLocalInterface : new zzuy(readStrongBinder);
                }
                zza(zzM, zziv, zzir, readString, readString3, zzuw);
                parcel2.writeNoException();
                break;
            case 7:
                zzM = zza.zzM(parcel.readStrongBinder());
                zzir2 = (zzir) zzef.zza(parcel, zzir.CREATOR);
                readString2 = parcel.readString();
                readString = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzuw2 = null;
                } else {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzuw2 = queryLocalInterface instanceof zzuw ? (zzuw) queryLocalInterface : new zzuy(readStrongBinder);
                }
                zza(zzM, zzir2, readString2, readString, zzuw2);
                parcel2.writeNoException();
                break;
            case 8:
                pause();
                parcel2.writeNoException();
                break;
            case 9:
                resume();
                parcel2.writeNoException();
                break;
            case 10:
                zza(zza.zzM(parcel.readStrongBinder()), (zzir) zzef.zza(parcel, zzir.CREATOR), parcel.readString(), zzaeb.zzx(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                zzc((zzir) zzef.zza(parcel, zzir.CREATOR), parcel.readString());
                parcel2.writeNoException();
                break;
            case 12:
                showVideo();
                parcel2.writeNoException();
                break;
            case 13:
                isInitialized = isInitialized();
                parcel2.writeNoException();
                zzef.zza(parcel2, isInitialized);
                break;
            case 14:
                zzM = zza.zzM(parcel.readStrongBinder());
                zzir2 = (zzir) zzef.zza(parcel, zzir.CREATOR);
                readString2 = parcel.readString();
                readString = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzuw2 = null;
                } else {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzuw2 = queryLocalInterface instanceof zzuw ? (zzuw) queryLocalInterface : new zzuy(readStrongBinder);
                }
                zza(zzM, zzir2, readString2, readString, zzuw2, (zzon) zzef.zza(parcel, zzon.CREATOR), parcel.createStringArrayList());
                parcel2.writeNoException();
                break;
            case 15:
                queryLocalInterface = zzfq();
                parcel2.writeNoException();
                zzef.zza(parcel2, queryLocalInterface);
                break;
            case 16:
                queryLocalInterface = zzfr();
                parcel2.writeNoException();
                zzef.zza(parcel2, queryLocalInterface);
                break;
            case 17:
                zzfs = zzfs();
                parcel2.writeNoException();
                zzef.zzb(parcel2, zzfs);
                break;
            case 18:
                zzfs = getInterstitialAdapterInfo();
                parcel2.writeNoException();
                zzef.zzb(parcel2, zzfs);
                break;
            case 19:
                zzfs = zzft();
                parcel2.writeNoException();
                zzef.zzb(parcel2, zzfs);
                break;
            case 20:
                zza((zzir) zzef.zza(parcel, zzir.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 21:
                zzk(zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 22:
                isInitialized = zzfu();
                parcel2.writeNoException();
                zzef.zza(parcel2, isInitialized);
                break;
            case 23:
                zza(zza.zzM(parcel.readStrongBinder()), zzaeb.zzx(parcel.readStrongBinder()), parcel.createStringArrayList());
                parcel2.writeNoException();
                break;
            case 24:
                queryLocalInterface = zzfv();
                parcel2.writeNoException();
                zzef.zza(parcel2, queryLocalInterface);
                break;
            case 25:
                setImmersiveMode(zzef.zza(parcel));
                parcel2.writeNoException();
                break;
            case 26:
                queryLocalInterface = getVideoController();
                parcel2.writeNoException();
                zzef.zza(parcel2, queryLocalInterface);
                break;
            default:
                return false;
        }
        return true;
    }
}
