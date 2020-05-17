package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public final class zzit implements Creator<zzir> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzb.zzd(parcel);
        int i = 0;
        long j = 0;
        Bundle bundle = null;
        int i2 = 0;
        List list = null;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        String str = null;
        zzlt zzlt = null;
        Location location = null;
        String str2 = null;
        Bundle bundle2 = null;
        Bundle bundle3 = null;
        List list2 = null;
        String str3 = null;
        String str4 = null;
        boolean z3 = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 1:
                    i = zzb.zzg(parcel, readInt);
                    break;
                case 2:
                    j = zzb.zzi(parcel, readInt);
                    break;
                case 3:
                    bundle = zzb.zzs(parcel, readInt);
                    break;
                case 4:
                    i2 = zzb.zzg(parcel, readInt);
                    break;
                case 5:
                    list = zzb.zzC(parcel, readInt);
                    break;
                case 6:
                    z = zzb.zzc(parcel, readInt);
                    break;
                case 7:
                    i3 = zzb.zzg(parcel, readInt);
                    break;
                case 8:
                    z2 = zzb.zzc(parcel, readInt);
                    break;
                case 9:
                    str = zzb.zzq(parcel, readInt);
                    break;
                case 10:
                    zzlt = (zzlt) zzb.zza(parcel, readInt, zzlt.CREATOR);
                    break;
                case 11:
                    location = (Location) zzb.zza(parcel, readInt, Location.CREATOR);
                    break;
                case 12:
                    str2 = zzb.zzq(parcel, readInt);
                    break;
                case 13:
                    bundle2 = zzb.zzs(parcel, readInt);
                    break;
                case 14:
                    bundle3 = zzb.zzs(parcel, readInt);
                    break;
                case 15:
                    list2 = zzb.zzC(parcel, readInt);
                    break;
                case 16:
                    str3 = zzb.zzq(parcel, readInt);
                    break;
                case 17:
                    str4 = zzb.zzq(parcel, readInt);
                    break;
                case 18:
                    z3 = zzb.zzc(parcel, readInt);
                    break;
                default:
                    zzb.zzb(parcel, readInt);
                    break;
            }
        }
        zzb.zzF(parcel, zzd);
        return new zzir(i, j, bundle, i2, list, z, i3, z2, str, zzlt, location, str2, bundle2, bundle3, list2, str3, str4, z3);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzir[i];
    }
}
