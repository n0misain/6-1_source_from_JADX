package com.google.android.gms.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

@zzzn
public class zziv extends zza {
    public static final Creator<zziv> CREATOR = new zziw();
    public final int height;
    public final int heightPixels;
    public final int width;
    public final int widthPixels;
    public final String zzAs;
    public final boolean zzAt;
    public final zziv[] zzAu;
    public final boolean zzAv;
    public final boolean zzAw;
    public boolean zzAx;

    public zziv() {
        this("interstitial_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    public zziv(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    public zziv(Context context, AdSize[] adSizeArr) {
        int i;
        int i2;
        AdSize adSize = adSizeArr[0];
        this.zzAt = false;
        this.zzAw = adSize.isFluid();
        if (this.zzAw) {
            this.width = AdSize.BANNER.getWidth();
            this.height = AdSize.BANNER.getHeight();
        } else {
            this.width = adSize.getWidth();
            this.height = adSize.getHeight();
        }
        boolean z = this.width == -1;
        boolean z2 = this.height == -2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (z) {
            double d;
            zzji.zzds();
            if (zzaiy.zzZ(context)) {
                zzji.zzds();
                if (zzaiy.zzaa(context)) {
                    i = displayMetrics.widthPixels;
                    zzji.zzds();
                    this.widthPixels = i - zzaiy.zzab(context);
                    d = (double) (((float) this.widthPixels) / displayMetrics.density);
                    i = (int) d;
                    if (d - ((double) ((int) d)) >= 0.01d) {
                        i++;
                    }
                    i2 = i;
                }
            }
            this.widthPixels = displayMetrics.widthPixels;
            d = (double) (((float) this.widthPixels) / displayMetrics.density);
            i = (int) d;
            if (d - ((double) ((int) d)) >= 0.01d) {
                i++;
            }
            i2 = i;
        } else {
            i = this.width;
            zzji.zzds();
            this.widthPixels = zzaiy.zza(displayMetrics, this.width);
            i2 = i;
        }
        i = z2 ? zzc(displayMetrics) : this.height;
        zzji.zzds();
        this.heightPixels = zzaiy.zza(displayMetrics, i);
        if (z || z2) {
            this.zzAs = i2 + "x" + i + "_as";
        } else if (this.zzAw) {
            this.zzAs = "320x50_mb";
        } else {
            this.zzAs = adSize.toString();
        }
        if (adSizeArr.length > 1) {
            this.zzAu = new zziv[adSizeArr.length];
            for (int i3 = 0; i3 < adSizeArr.length; i3++) {
                this.zzAu[i3] = new zziv(context, adSizeArr[i3]);
            }
        } else {
            this.zzAu = null;
        }
        this.zzAv = false;
        this.zzAx = false;
    }

    public zziv(zziv zziv, zziv[] zzivArr) {
        this(zziv.zzAs, zziv.height, zziv.heightPixels, zziv.zzAt, zziv.width, zziv.widthPixels, zzivArr, zziv.zzAv, zziv.zzAw, zziv.zzAx);
    }

    zziv(String str, int i, int i2, boolean z, int i3, int i4, zziv[] zzivArr, boolean z2, boolean z3, boolean z4) {
        this.zzAs = str;
        this.height = i;
        this.heightPixels = i2;
        this.zzAt = z;
        this.width = i3;
        this.widthPixels = i4;
        this.zzAu = zzivArr;
        this.zzAv = z2;
        this.zzAw = z3;
        this.zzAx = z4;
    }

    public static int zza(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int zzb(DisplayMetrics displayMetrics) {
        return (int) (((float) zzc(displayMetrics)) * displayMetrics.density);
    }

    private static int zzc(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        return i <= 400 ? 32 : i <= 720 ? 50 : 90;
    }

    public static zziv zzdk() {
        return new zziv("reward_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    public static zziv zzg(Context context) {
        return new zziv("320x50_mb", 0, 0, false, 0, 0, null, true, false, false);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzd.zze(parcel);
        zzd.zza(parcel, 2, this.zzAs, false);
        zzd.zzc(parcel, 3, this.height);
        zzd.zzc(parcel, 4, this.heightPixels);
        zzd.zza(parcel, 5, this.zzAt);
        zzd.zzc(parcel, 6, this.width);
        zzd.zzc(parcel, 7, this.widthPixels);
        zzd.zza(parcel, 8, this.zzAu, i, false);
        zzd.zza(parcel, 9, this.zzAv);
        zzd.zza(parcel, 10, this.zzAw);
        zzd.zza(parcel, 11, this.zzAx);
        zzd.zzI(parcel, zze);
    }

    public final AdSize zzdl() {
        return zzb.zza(this.width, this.height, this.zzAs);
    }
}
