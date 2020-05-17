package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzkt extends zzee implements zzks {
    public zzkt() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IVideoController");
    }

    public static zzks zzg(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
        return queryLocalInterface instanceof zzks ? (zzks) queryLocalInterface : new zzku(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        boolean isMuted;
        float zzdv;
        switch (i) {
            case 1:
                play();
                parcel2.writeNoException();
                break;
            case 2:
                pause();
                parcel2.writeNoException();
                break;
            case 3:
                mute(zzef.zza(parcel));
                parcel2.writeNoException();
                break;
            case 4:
                isMuted = isMuted();
                parcel2.writeNoException();
                zzef.zza(parcel2, isMuted);
                break;
            case 5:
                int playbackState = getPlaybackState();
                parcel2.writeNoException();
                parcel2.writeInt(playbackState);
                break;
            case 6:
                zzdv = zzdv();
                parcel2.writeNoException();
                parcel2.writeFloat(zzdv);
                break;
            case 7:
                zzdv = zzdw();
                parcel2.writeNoException();
                parcel2.writeFloat(zzdv);
                break;
            case 8:
                zzkv zzkv;
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzkv = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
                    zzkv = queryLocalInterface instanceof zzkv ? (zzkv) queryLocalInterface : new zzkx(readStrongBinder);
                }
                zza(zzkv);
                parcel2.writeNoException();
                break;
            case 9:
                zzdv = getAspectRatio();
                parcel2.writeNoException();
                parcel2.writeFloat(zzdv);
                break;
            case 10:
                isMuted = isCustomControlsEnabled();
                parcel2.writeNoException();
                zzef.zza(parcel2, isMuted);
                break;
            default:
                return false;
        }
        return true;
    }
}
