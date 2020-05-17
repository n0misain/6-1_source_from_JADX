package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zziw implements Creator<zziv> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zziv[] zzivArr = null;
        boolean z = false;
        int zzd = zzb.zzd(parcel);
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        int i2 = 0;
        boolean z4 = false;
        int i3 = 0;
        int i4 = 0;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 2:
                    str = zzb.zzq(parcel, readInt);
                    break;
                case 3:
                    i4 = zzb.zzg(parcel, readInt);
                    break;
                case 4:
                    i3 = zzb.zzg(parcel, readInt);
                    break;
                case 5:
                    z4 = zzb.zzc(parcel, readInt);
                    break;
                case 6:
                    i2 = zzb.zzg(parcel, readInt);
                    break;
                case 7:
                    i = zzb.zzg(parcel, readInt);
                    break;
                case 8:
                    zzivArr = (zziv[]) zzb.zzb(parcel, readInt, zziv.CREATOR);
                    break;
                case 9:
                    z3 = zzb.zzc(parcel, readInt);
                    break;
                case 10:
                    z2 = zzb.zzc(parcel, readInt);
                    break;
                case 11:
                    z = zzb.zzc(parcel, readInt);
                    break;
                default:
                    zzb.zzb(parcel, readInt);
                    break;
            }
        }
        zzb.zzF(parcel, zzd);
        return new zziv(str, i4, i3, z4, i2, i, zzivArr, z3, z2, z);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zziv[i];
    }
}
