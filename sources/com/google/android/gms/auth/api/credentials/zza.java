package com.google.android.gms.auth.api.credentials;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public final class zza implements Creator<Credential> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int zzd = zzb.zzd(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        List list = null;
        Uri uri = null;
        String str7 = null;
        String str8 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 1:
                    str8 = zzb.zzq(parcel, readInt);
                    break;
                case 2:
                    str7 = zzb.zzq(parcel, readInt);
                    break;
                case 3:
                    uri = (Uri) zzb.zza(parcel, readInt, Uri.CREATOR);
                    break;
                case 4:
                    list = zzb.zzc(parcel, readInt, IdToken.CREATOR);
                    break;
                case 5:
                    str6 = zzb.zzq(parcel, readInt);
                    break;
                case 6:
                    str5 = zzb.zzq(parcel, readInt);
                    break;
                case 7:
                    str4 = zzb.zzq(parcel, readInt);
                    break;
                case 8:
                    str3 = zzb.zzq(parcel, readInt);
                    break;
                case 9:
                    str2 = zzb.zzq(parcel, readInt);
                    break;
                case 10:
                    str = zzb.zzq(parcel, readInt);
                    break;
                case 1000:
                    i = zzb.zzg(parcel, readInt);
                    break;
                default:
                    zzb.zzb(parcel, readInt);
                    break;
            }
        }
        zzb.zzF(parcel, zzd);
        return new Credential(i, str8, str7, uri, list, str6, str5, str4, str3, str2, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Credential[i];
    }
}
