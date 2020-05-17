package com.google.firebase.database.connection.idl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import java.util.List;

public abstract class zzu extends zzee implements zzt {
    public zzu() {
        attachInterface(this, "com.google.firebase.database.connection.idl.IPersistentConnection");
    }

    public static zzt asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        return queryLocalInterface instanceof zzt ? (zzt) queryLocalInterface : new zzv(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzah zzah = null;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        IBinder readStrongBinder;
        IBinder readStrongBinder2;
        List createStringArrayList;
        IObjectWrapper zzM;
        IInterface queryLocalInterface;
        IBinder readStrongBinder3;
        switch (i) {
            case 1:
                zzk zzk;
                IInterface queryLocalInterface2;
                zzw zzy;
                zzc zzc = (zzc) zzef.zza(parcel, zzc.CREATOR);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzk = null;
                } else {
                    queryLocalInterface2 = readStrongBinder.queryLocalInterface("com.google.firebase.database.connection.idl.IConnectionAuthTokenProvider");
                    if (queryLocalInterface2 instanceof zzk) {
                        zzk = (zzk) queryLocalInterface2;
                    } else {
                        Object zzm = new zzm(readStrongBinder);
                    }
                }
                IObjectWrapper zzM2 = zza.zzM(parcel.readStrongBinder());
                readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    zzy = queryLocalInterface2 instanceof zzw ? (zzw) queryLocalInterface2 : new zzy(readStrongBinder2);
                }
                setup(zzc, zzk, zzM2, zzy);
                parcel2.writeNoException();
                break;
            case 2:
                initialize();
                parcel2.writeNoException();
                break;
            case 3:
                shutdown();
                parcel2.writeNoException();
                break;
            case 4:
                refreshAuthToken();
                parcel2.writeNoException();
                break;
            case 5:
                zzq zzq;
                createStringArrayList = parcel.createStringArrayList();
                zzM = zza.zzM(parcel.readStrongBinder());
                readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 == null) {
                    zzq = null;
                } else {
                    queryLocalInterface = readStrongBinder2.queryLocalInterface("com.google.firebase.database.connection.idl.IListenHashProvider");
                    zzq = queryLocalInterface instanceof zzq ? (zzq) queryLocalInterface : new zzs(readStrongBinder2);
                }
                long readLong = parcel.readLong();
                IBinder readStrongBinder4 = parcel.readStrongBinder();
                if (readStrongBinder4 != null) {
                    queryLocalInterface = readStrongBinder4.queryLocalInterface("com.google.firebase.database.connection.idl.IRequestResultCallback");
                    zzah = queryLocalInterface instanceof zzah ? (zzah) queryLocalInterface : new zzaj(readStrongBinder4);
                }
                listen(createStringArrayList, zzM, zzq, readLong, zzah);
                parcel2.writeNoException();
                break;
            case 6:
                unlisten(parcel.createStringArrayList(), zza.zzM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 7:
                purgeOutstandingWrites();
                parcel2.writeNoException();
                break;
            case 8:
                createStringArrayList = parcel.createStringArrayList();
                zzM = zza.zzM(parcel.readStrongBinder());
                readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    queryLocalInterface = readStrongBinder3.queryLocalInterface("com.google.firebase.database.connection.idl.IRequestResultCallback");
                    zzah = queryLocalInterface instanceof zzah ? (zzah) queryLocalInterface : new zzaj(readStrongBinder3);
                }
                put(createStringArrayList, zzM, zzah);
                parcel2.writeNoException();
                break;
            case 9:
                createStringArrayList = parcel.createStringArrayList();
                zzM = zza.zzM(parcel.readStrongBinder());
                String readString = parcel.readString();
                readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    queryLocalInterface = readStrongBinder2.queryLocalInterface("com.google.firebase.database.connection.idl.IRequestResultCallback");
                    zzah = queryLocalInterface instanceof zzah ? (zzah) queryLocalInterface : new zzaj(readStrongBinder2);
                }
                compareAndPut(createStringArrayList, zzM, readString, zzah);
                parcel2.writeNoException();
                break;
            case 10:
                createStringArrayList = parcel.createStringArrayList();
                zzM = zza.zzM(parcel.readStrongBinder());
                readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    queryLocalInterface = readStrongBinder3.queryLocalInterface("com.google.firebase.database.connection.idl.IRequestResultCallback");
                    zzah = queryLocalInterface instanceof zzah ? (zzah) queryLocalInterface : new zzaj(readStrongBinder3);
                }
                merge(createStringArrayList, zzM, zzah);
                parcel2.writeNoException();
                break;
            case 11:
                createStringArrayList = parcel.createStringArrayList();
                zzM = zza.zzM(parcel.readStrongBinder());
                readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    queryLocalInterface = readStrongBinder3.queryLocalInterface("com.google.firebase.database.connection.idl.IRequestResultCallback");
                    zzah = queryLocalInterface instanceof zzah ? (zzah) queryLocalInterface : new zzaj(readStrongBinder3);
                }
                onDisconnectPut(createStringArrayList, zzM, zzah);
                parcel2.writeNoException();
                break;
            case 12:
                createStringArrayList = parcel.createStringArrayList();
                zzM = zza.zzM(parcel.readStrongBinder());
                readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    queryLocalInterface = readStrongBinder3.queryLocalInterface("com.google.firebase.database.connection.idl.IRequestResultCallback");
                    zzah = queryLocalInterface instanceof zzah ? (zzah) queryLocalInterface : new zzaj(readStrongBinder3);
                }
                onDisconnectMerge(createStringArrayList, zzM, zzah);
                parcel2.writeNoException();
                break;
            case 13:
                createStringArrayList = parcel.createStringArrayList();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.firebase.database.connection.idl.IRequestResultCallback");
                    zzah = queryLocalInterface instanceof zzah ? (zzah) queryLocalInterface : new zzaj(readStrongBinder);
                }
                onDisconnectCancel(createStringArrayList, zzah);
                parcel2.writeNoException();
                break;
            case 14:
                interrupt(parcel.readString());
                parcel2.writeNoException();
                break;
            case 15:
                resume(parcel.readString());
                parcel2.writeNoException();
                break;
            case 16:
                boolean isInterrupted = isInterrupted(parcel.readString());
                parcel2.writeNoException();
                zzef.zza(parcel2, isInterrupted);
                break;
            case 17:
                refreshAuthToken2(parcel.readString());
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
