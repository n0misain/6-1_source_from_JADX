package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public final class zzaaj implements Creator<zzaai> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzb.zzd(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        List list = null;
        int i2 = 0;
        List list2 = null;
        long j = 0;
        boolean z = false;
        long j2 = 0;
        List list3 = null;
        long j3 = 0;
        int i3 = 0;
        String str3 = null;
        long j4 = 0;
        String str4 = null;
        boolean z2 = false;
        String str5 = null;
        String str6 = null;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        zzaau zzaau = null;
        String str7 = null;
        String str8 = null;
        boolean z8 = false;
        boolean z9 = false;
        zzaee zzaee = null;
        List list4 = null;
        List list5 = null;
        boolean z10 = false;
        zzaak zzaak = null;
        boolean z11 = false;
        String str9 = null;
        List list6 = null;
        boolean z12 = false;
        String str10 = null;
        zzaeq zzaeq = null;
        String str11 = null;
        boolean z13 = false;
        boolean z14 = false;
        Bundle bundle = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 1:
                    i = zzb.zzg(parcel, readInt);
                    break;
                case 2:
                    str = zzb.zzq(parcel, readInt);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel, readInt);
                    break;
                case 4:
                    list = zzb.zzC(parcel, readInt);
                    break;
                case 5:
                    i2 = zzb.zzg(parcel, readInt);
                    break;
                case 6:
                    list2 = zzb.zzC(parcel, readInt);
                    break;
                case 7:
                    j = zzb.zzi(parcel, readInt);
                    break;
                case 8:
                    z = zzb.zzc(parcel, readInt);
                    break;
                case 9:
                    j2 = zzb.zzi(parcel, readInt);
                    break;
                case 10:
                    list3 = zzb.zzC(parcel, readInt);
                    break;
                case 11:
                    j3 = zzb.zzi(parcel, readInt);
                    break;
                case 12:
                    i3 = zzb.zzg(parcel, readInt);
                    break;
                case 13:
                    str3 = zzb.zzq(parcel, readInt);
                    break;
                case 14:
                    j4 = zzb.zzi(parcel, readInt);
                    break;
                case 15:
                    str4 = zzb.zzq(parcel, readInt);
                    break;
                case 18:
                    z2 = zzb.zzc(parcel, readInt);
                    break;
                case 19:
                    str5 = zzb.zzq(parcel, readInt);
                    break;
                case 21:
                    str6 = zzb.zzq(parcel, readInt);
                    break;
                case 22:
                    z3 = zzb.zzc(parcel, readInt);
                    break;
                case 23:
                    z4 = zzb.zzc(parcel, readInt);
                    break;
                case 24:
                    z5 = zzb.zzc(parcel, readInt);
                    break;
                case 25:
                    z6 = zzb.zzc(parcel, readInt);
                    break;
                case 26:
                    z7 = zzb.zzc(parcel, readInt);
                    break;
                case 28:
                    zzaau = (zzaau) zzb.zza(parcel, readInt, zzaau.CREATOR);
                    break;
                case 29:
                    str7 = zzb.zzq(parcel, readInt);
                    break;
                case 30:
                    str8 = zzb.zzq(parcel, readInt);
                    break;
                case 31:
                    z8 = zzb.zzc(parcel, readInt);
                    break;
                case 32:
                    z9 = zzb.zzc(parcel, readInt);
                    break;
                case 33:
                    zzaee = (zzaee) zzb.zza(parcel, readInt, zzaee.CREATOR);
                    break;
                case 34:
                    list4 = zzb.zzC(parcel, readInt);
                    break;
                case 35:
                    list5 = zzb.zzC(parcel, readInt);
                    break;
                case 36:
                    z10 = zzb.zzc(parcel, readInt);
                    break;
                case 37:
                    zzaak = (zzaak) zzb.zza(parcel, readInt, zzaak.CREATOR);
                    break;
                case 38:
                    z11 = zzb.zzc(parcel, readInt);
                    break;
                case 39:
                    str9 = zzb.zzq(parcel, readInt);
                    break;
                case 40:
                    list6 = zzb.zzC(parcel, readInt);
                    break;
                case 42:
                    z12 = zzb.zzc(parcel, readInt);
                    break;
                case 43:
                    str10 = zzb.zzq(parcel, readInt);
                    break;
                case 44:
                    zzaeq = (zzaeq) zzb.zza(parcel, readInt, zzaeq.CREATOR);
                    break;
                case 45:
                    str11 = zzb.zzq(parcel, readInt);
                    break;
                case 46:
                    z13 = zzb.zzc(parcel, readInt);
                    break;
                case 47:
                    z14 = zzb.zzc(parcel, readInt);
                    break;
                case 48:
                    bundle = zzb.zzs(parcel, readInt);
                    break;
                default:
                    zzb.zzb(parcel, readInt);
                    break;
            }
        }
        zzb.zzF(parcel, zzd);
        return new zzaai(i, str, str2, list, i2, list2, j, z, j2, list3, j3, i3, str3, j4, str4, z2, str5, str6, z3, z4, z5, z6, z7, zzaau, str7, str8, z8, z9, zzaee, list4, list5, z10, zzaak, z11, str9, list6, z12, str10, zzaeq, str11, z13, z14, bundle);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzaai[i];
    }
}
