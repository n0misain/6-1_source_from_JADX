package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzlu implements Creator<zzlt> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzb.zzd(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        String str = null;
        int i9 = 0;
        String str2 = null;
        int i10 = 0;
        int i11 = 0;
        String str3 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 2:
                    i = zzb.zzg(parcel, readInt);
                    break;
                case 3:
                    i2 = zzb.zzg(parcel, readInt);
                    break;
                case 4:
                    i3 = zzb.zzg(parcel, readInt);
                    break;
                case 5:
                    i4 = zzb.zzg(parcel, readInt);
                    break;
                case 6:
                    i5 = zzb.zzg(parcel, readInt);
                    break;
                case 7:
                    i6 = zzb.zzg(parcel, readInt);
                    break;
                case 8:
                    i7 = zzb.zzg(parcel, readInt);
                    break;
                case 9:
                    i8 = zzb.zzg(parcel, readInt);
                    break;
                case 10:
                    str = zzb.zzq(parcel, readInt);
                    break;
                case 11:
                    i9 = zzb.zzg(parcel, readInt);
                    break;
                case 12:
                    str2 = zzb.zzq(parcel, readInt);
                    break;
                case 13:
                    i10 = zzb.zzg(parcel, readInt);
                    break;
                case 14:
                    i11 = zzb.zzg(parcel, readInt);
                    break;
                case 15:
                    str3 = zzb.zzq(parcel, readInt);
                    break;
                default:
                    zzb.zzb(parcel, readInt);
                    break;
            }
        }
        zzb.zzF(parcel, zzd);
        return new zzlt(i, i2, i3, i4, i5, i6, i7, i8, str, i9, str2, i10, i11, str3);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzlt[i];
    }
}
