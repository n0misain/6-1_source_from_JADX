package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class zza {
    private static boolean zza(Context context, Intent intent, zzag zzag) {
        try {
            String str = "Launching an intent: ";
            String valueOf = String.valueOf(intent.toURI());
            zzafr.m1217v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            zzbs.zzbz();
            zzagz.zzb(context, intent);
            if (zzag != null) {
                zzag.zzan();
            }
            return true;
        } catch (ActivityNotFoundException e) {
            zzajc.zzaT(e.getMessage());
            return false;
        }
    }

    public static boolean zza(Context context, zzc zzc, zzag zzag) {
        if (zzc == null) {
            zzajc.zzaT("No intent data for launcher overlay.");
            return false;
        }
        zzmo.initialize(context);
        if (zzc.intent != null) {
            return zza(context, zzc.intent, zzag);
        }
        Intent intent = new Intent();
        if (TextUtils.isEmpty(zzc.url)) {
            zzajc.zzaT("Open GMSG did not contain a URL.");
            return false;
        }
        if (TextUtils.isEmpty(zzc.mimeType)) {
            intent.setData(Uri.parse(zzc.url));
        } else {
            intent.setDataAndType(Uri.parse(zzc.url), zzc.mimeType);
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty(zzc.packageName)) {
            intent.setPackage(zzc.packageName);
        }
        if (!TextUtils.isEmpty(zzc.zzOh)) {
            String[] split = zzc.zzOh.split("/", 2);
            if (split.length < 2) {
                String str = "Could not parse component name from open GMSG: ";
                String valueOf = String.valueOf(zzc.zzOh);
                zzajc.zzaT(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                return false;
            }
            intent.setClassName(split[0], split[1]);
        }
        Object obj = zzc.zzOi;
        if (!TextUtils.isEmpty(obj)) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(obj);
            } catch (NumberFormatException e) {
                zzajc.zzaT("Could not parse intent flags.");
                parseInt = 0;
            }
            intent.addFlags(parseInt);
        }
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzGq)).booleanValue()) {
            intent.addFlags(268435456);
            intent.putExtra("android.support.customtabs.extra.user_opt_out", true);
        } else {
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzGp)).booleanValue()) {
                zzbs.zzbz();
                zzagz.zzc(context, intent);
            }
        }
        return zza(context, intent, zzag);
    }
}
