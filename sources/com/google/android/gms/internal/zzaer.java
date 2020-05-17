package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public final class zzaer implements Creator<zzaeq> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        List list = null;
        boolean z = false;
        int zzd = zzb.zzd(parcel);
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 2:
                    str2 = zzb.zzq(parcel, readInt);
                    break;
                case 3:
                    str = zzb.zzq(parcel, readInt);
                    break;
                case 4:
                    z4 = zzb.zzc(parcel, readInt);
                    break;
                case 5:
                    z3 = zzb.zzc(parcel, readInt);
                    break;
                case 6:
                    list = zzb.zzC(parcel, readInt);
                    break;
                case 7:
                    z2 = zzb.zzc(parcel, readInt);
                    break;
                case 8:
                    z = zzb.zzc(parcel, readInt);
                    break;
                default:
                    zzb.zzb(parcel, readInt);
                    break;
            }
        }
        zzb.zzF(parcel, zzd);
        return new zzaeq(str2, str, z4, z3, list, z2, z);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzaeq[i];
    }
}
