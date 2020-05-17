package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzaay implements Creator<zzaax> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzb.zzd(parcel);
        ApplicationInfo applicationInfo = null;
        PackageInfo packageInfo = null;
        zzaje zzaje = null;
        Bundle bundle = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 1:
                    bundle = zzb.zzs(parcel, readInt);
                    break;
                case 2:
                    zzaje = (zzaje) zzb.zza(parcel, readInt, zzaje.CREATOR);
                    break;
                case 3:
                    packageInfo = (PackageInfo) zzb.zza(parcel, readInt, PackageInfo.CREATOR);
                    break;
                case 4:
                    applicationInfo = (ApplicationInfo) zzb.zza(parcel, readInt, ApplicationInfo.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, readInt);
                    break;
            }
        }
        zzb.zzF(parcel, zzd);
        return new zzaax(bundle, zzaje, packageInfo, applicationInfo);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzaax[i];
    }
}
