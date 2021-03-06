package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class ku implements Creator<kt> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        lh lhVar = null;
        int zzd = zzb.zzd(parcel);
        String str = null;
        boolean z2 = false;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 2:
                    str2 = zzb.zzq(parcel, readInt);
                    break;
                case 3:
                    z2 = zzb.zzc(parcel, readInt);
                    break;
                case 4:
                    str = zzb.zzq(parcel, readInt);
                    break;
                case 5:
                    z = zzb.zzc(parcel, readInt);
                    break;
                case 6:
                    lhVar = (lh) zzb.zza(parcel, readInt, lh.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, readInt);
                    break;
            }
        }
        zzb.zzF(parcel, zzd);
        return new kt(str2, z2, str, z, lhVar);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new kt[i];
    }
}
