package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class kw implements Creator<kv> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int zzd = zzb.zzd(parcel);
        boolean z = false;
        String str2 = null;
        lb lbVar = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 2:
                    str6 = zzb.zzq(parcel, readInt);
                    break;
                case 3:
                    str5 = zzb.zzq(parcel, readInt);
                    break;
                case 4:
                    z = zzb.zzc(parcel, readInt);
                    break;
                case 5:
                    str4 = zzb.zzq(parcel, readInt);
                    break;
                case 6:
                    str3 = zzb.zzq(parcel, readInt);
                    break;
                case 7:
                    lbVar = (lb) zzb.zza(parcel, readInt, lb.CREATOR);
                    break;
                case 8:
                    str2 = zzb.zzq(parcel, readInt);
                    break;
                case 9:
                    str = zzb.zzq(parcel, readInt);
                    break;
                default:
                    zzb.zzb(parcel, readInt);
                    break;
            }
        }
        zzb.zzF(parcel, zzd);
        return new kv(str6, str5, z, str4, str3, lbVar, str2, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new kv[i];
    }
}
