package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;

public final class zzb implements Creator<ProxyResponse> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        byte[] bArr = null;
        int i = 0;
        int zzd = com.google.android.gms.common.internal.safeparcel.zzb.zzd(parcel);
        Bundle bundle = null;
        PendingIntent pendingIntent = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 1:
                    i2 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, readInt);
                    break;
                case 2:
                    pendingIntent = (PendingIntent) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, readInt, PendingIntent.CREATOR);
                    break;
                case 3:
                    i = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, readInt);
                    break;
                case 4:
                    bundle = com.google.android.gms.common.internal.safeparcel.zzb.zzs(parcel, readInt);
                    break;
                case 5:
                    bArr = com.google.android.gms.common.internal.safeparcel.zzb.zzt(parcel, readInt);
                    break;
                case 1000:
                    i3 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, readInt);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, readInt);
                    break;
            }
        }
        com.google.android.gms.common.internal.safeparcel.zzb.zzF(parcel, zzd);
        return new ProxyResponse(i3, i2, pendingIntent, i, bundle, bArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ProxyResponse[i];
    }
}
