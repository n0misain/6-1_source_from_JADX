package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

@zzzn
public final class zzaje extends zza {
    public static final Creator<zzaje> CREATOR = new zzajf();
    public String zzaP;
    public int zzaaO;
    public int zzaaP;
    public boolean zzaaQ;
    public boolean zzaaR;

    public zzaje(int i, int i2, boolean z) {
        this(i, i2, z, false, false);
    }

    public zzaje(int i, int i2, boolean z, boolean z2) {
        this(11020000, i2, true, false, z2);
    }

    private zzaje(int i, int i2, boolean z, boolean z2, boolean z3) {
        String valueOf = String.valueOf("afma-sdk-a-v");
        String str = z ? AppEventsConstants.EVENT_PARAM_VALUE_NO : AppEventsConstants.EVENT_PARAM_VALUE_YES;
        this(new StringBuilder((String.valueOf(valueOf).length() + 24) + String.valueOf(str).length()).append(valueOf).append(i).append(".").append(i2).append(".").append(str).toString(), i, i2, z, z3);
    }

    zzaje(String str, int i, int i2, boolean z, boolean z2) {
        this.zzaP = str;
        this.zzaaO = i;
        this.zzaaP = i2;
        this.zzaaQ = z;
        this.zzaaR = z2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzd.zze(parcel);
        zzd.zza(parcel, 2, this.zzaP, false);
        zzd.zzc(parcel, 3, this.zzaaO);
        zzd.zzc(parcel, 4, this.zzaaP);
        zzd.zza(parcel, 5, this.zzaaQ);
        zzd.zza(parcel, 6, this.zzaaR);
        zzd.zzI(parcel, zze);
    }
}
