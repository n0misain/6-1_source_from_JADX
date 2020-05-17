package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.List;

@zzzn
public final class zzia extends zza {
    public static final Creator<zzia> CREATOR = new zzib();
    @Nullable
    public final String url;
    private long zzzu;
    private String zzzv;
    private String zzzw;
    private String zzzx;
    private Bundle zzzy;
    private boolean zzzz;

    zzia(@Nullable String str, long j, String str2, String str3, String str4, Bundle bundle, boolean z) {
        this.url = str;
        this.zzzu = j;
        if (str2 == null) {
            str2 = "";
        }
        this.zzzv = str2;
        if (str3 == null) {
            str3 = "";
        }
        this.zzzw = str3;
        if (str4 == null) {
            str4 = "";
        }
        this.zzzx = str4;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzzy = bundle;
        this.zzzz = z;
    }

    @Nullable
    public static zzia zzB(String str) {
        return zze(Uri.parse(str));
    }

    @Nullable
    public static zzia zze(Uri uri) {
        Throwable e;
        try {
            if (!"gcache".equals(uri.getScheme())) {
                return null;
            }
            List pathSegments = uri.getPathSegments();
            if (pathSegments.size() != 2) {
                zzajc.zzaT("Expected 2 path parts for namespace and id, found :" + pathSegments.size());
                return null;
            }
            String str = (String) pathSegments.get(0);
            String str2 = (String) pathSegments.get(1);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter("url");
            boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(uri.getQueryParameter("read_only"));
            String queryParameter2 = uri.getQueryParameter("expiration");
            long parseLong = queryParameter2 == null ? 0 : Long.parseLong(queryParameter2);
            Bundle bundle = new Bundle();
            for (String queryParameter22 : zzbs.zzbB().zzh(uri)) {
                if (queryParameter22.startsWith("tag.")) {
                    bundle.putString(queryParameter22.substring(4), uri.getQueryParameter(queryParameter22));
                }
            }
            return new zzia(queryParameter, parseLong, host, str, str2, bundle, equals);
        } catch (NullPointerException e2) {
            e = e2;
            zzajc.zzc("Unable to parse Uri into cache offering.", e);
            return null;
        } catch (NumberFormatException e3) {
            e = e3;
            zzajc.zzc("Unable to parse Uri into cache offering.", e);
            return null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzd.zze(parcel);
        zzd.zza(parcel, 2, this.url, false);
        zzd.zza(parcel, 3, this.zzzu);
        zzd.zza(parcel, 4, this.zzzv, false);
        zzd.zza(parcel, 5, this.zzzw, false);
        zzd.zza(parcel, 6, this.zzzx, false);
        zzd.zza(parcel, 7, this.zzzy, false);
        zzd.zza(parcel, 8, this.zzzz);
        zzd.zzI(parcel, zze);
    }
}
