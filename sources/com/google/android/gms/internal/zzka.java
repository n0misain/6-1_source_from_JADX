package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class zzka extends zzee implements zzjz {
    public zzka() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IAdManager");
    }

    public static zzjz zze(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        return queryLocalInterface instanceof zzjz ? (zzjz) queryLocalInterface : new zzkb(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzkk zzkk = null;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        IInterface zzal;
        boolean isReady;
        IBinder readStrongBinder;
        String mediationAdapterClassName;
        switch (i) {
            case 1:
                zzal = zzal();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzal);
                break;
            case 2:
                destroy();
                parcel2.writeNoException();
                break;
            case 3:
                isReady = isReady();
                parcel2.writeNoException();
                zzef.zza(parcel2, isReady);
                break;
            case 4:
                isReady = zza((zzir) zzef.zza(parcel, zzir.CREATOR));
                parcel2.writeNoException();
                zzef.zza(parcel2, isReady);
                break;
            case 5:
                pause();
                parcel2.writeNoException();
                break;
            case 6:
                resume();
                parcel2.writeNoException();
                break;
            case 7:
                zzjo zzjq;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzal = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    zzjq = zzal instanceof zzjo ? (zzjo) zzal : new zzjq(readStrongBinder);
                }
                zza(zzjq);
                parcel2.writeNoException();
                break;
            case 8:
                zzke zzkg;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzal = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
                    zzkg = zzal instanceof zzke ? (zzke) zzal : new zzkg(readStrongBinder);
                }
                zza(zzkg);
                parcel2.writeNoException();
                break;
            case 9:
                showInterstitial();
                parcel2.writeNoException();
                break;
            case 10:
                stopLoading();
                parcel2.writeNoException();
                break;
            case 11:
                zzao();
                parcel2.writeNoException();
                break;
            case 12:
                Parcelable zzam = zzam();
                parcel2.writeNoException();
                zzef.zzb(parcel2, zzam);
                break;
            case 13:
                zza((zziv) zzef.zza(parcel, zziv.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                zza(zzxh.zzs(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 15:
                zza(zzxp.zzu(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                break;
            case 18:
                mediationAdapterClassName = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 19:
                zza(zzni.zzh(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 20:
                zzjl zzjn;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzal = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdClickListener");
                    zzjn = zzal instanceof zzjl ? (zzjl) zzal : new zzjn(readStrongBinder);
                }
                zza(zzjn);
                parcel2.writeNoException();
                break;
            case 21:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzal = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    zzkk = zzal instanceof zzkk ? (zzkk) zzal : new zzkm(readStrongBinder);
                }
                zza(zzkk);
                parcel2.writeNoException();
                break;
            case 22:
                setManualImpressionsEnabled(zzef.zza(parcel));
                parcel2.writeNoException();
                break;
            case 23:
                isReady = isLoading();
                parcel2.writeNoException();
                zzef.zza(parcel2, isReady);
                break;
            case 24:
                zza(zzade.zzw(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 25:
                setUserId(parcel.readString());
                parcel2.writeNoException();
                break;
            case 26:
                zzal = getVideoController();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzal);
                break;
            case 29:
                zza((zzlx) zzef.zza(parcel, zzlx.CREATOR));
                parcel2.writeNoException();
                break;
            case 30:
                zza((zzky) zzef.zza(parcel, zzky.CREATOR));
                parcel2.writeNoException();
                break;
            case 31:
                mediationAdapterClassName = getAdUnitId();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 32:
                zzal = zzax();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzal);
                break;
            case 33:
                zzal = zzay();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzal);
                break;
            case 34:
                setImmersiveMode(zzef.zza(parcel));
                parcel2.writeNoException();
                break;
            case 35:
                mediationAdapterClassName = zzaI();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            default:
                return false;
        }
        return true;
    }
}
