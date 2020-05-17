package com.google.android.gms.internal;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class zzrr {
    private final zzaka zzJH;

    public zzrr(zzaka zzaka) {
        this.zzJH = zzaka;
    }

    private static Intent zza(Intent intent, ResolveInfo resolveInfo) {
        Intent intent2 = new Intent(intent);
        intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        return intent2;
    }

    private static ResolveInfo zza(Context context, Intent intent) {
        return zza(context, intent, new ArrayList());
    }

    private static ResolveInfo zza(Context context, Intent intent, ArrayList<ResolveInfo> arrayList) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        ResolveInfo resolveInfo;
        Collection queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
        if (!(queryIntentActivities == null || resolveActivity == null)) {
            for (int i = 0; i < queryIntentActivities.size(); i++) {
                resolveInfo = (ResolveInfo) queryIntentActivities.get(i);
                if (resolveActivity != null && resolveActivity.activityInfo.name.equals(resolveInfo.activityInfo.name)) {
                    resolveInfo = resolveActivity;
                    break;
                }
            }
        }
        resolveInfo = null;
        arrayList.addAll(queryIntentActivities);
        return resolveInfo;
    }

    private static Intent zzf(Uri uri) {
        if (uri == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setData(uri);
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }

    public final Intent zza(Context context, Map<String, String> map) {
        Uri uri = null;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        String str = (String) map.get("u");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i;
        ArrayList arrayList;
        Intent zzf;
        Intent zzf2;
        ResolveInfo zza;
        List<RunningAppProcessInfo> runningAppProcesses;
        ArrayList arrayList2;
        int size;
        int i2;
        if (this.zzJH != null) {
            zzbs.zzbz();
            str = zzagz.zzb(this.zzJH, str);
        }
        Uri parse = Uri.parse(str);
        boolean parseBoolean = Boolean.parseBoolean((String) map.get("use_first_package"));
        boolean parseBoolean2 = Boolean.parseBoolean((String) map.get("use_running_process"));
        if (!Boolean.parseBoolean((String) map.get("use_custom_tabs"))) {
            if (!((Boolean) zzbs.zzbL().zzd(zzmo.zzGp)).booleanValue()) {
                i = 0;
                if ("http".equalsIgnoreCase(parse.getScheme())) {
                    uri = parse.buildUpon().scheme("https").build();
                } else if ("https".equalsIgnoreCase(parse.getScheme())) {
                    uri = parse.buildUpon().scheme("http").build();
                }
                arrayList = new ArrayList();
                zzf = zzf(parse);
                zzf2 = zzf(uri);
                if (i != 0) {
                    zzbs.zzbz();
                    zzagz.zzc(context, zzf);
                    zzbs.zzbz();
                    zzagz.zzc(context, zzf2);
                }
                zza = zza(context, zzf, arrayList);
                if (zza != null) {
                    return zza(zzf, zza);
                }
                if (zzf2 != null) {
                    zza = zza(context, zzf2);
                    if (zza != null) {
                        zzf2 = zza(zzf, zza);
                        if (zza(context, zzf2) != null) {
                            return zzf2;
                        }
                    }
                }
                if (arrayList.size() == 0) {
                    return zzf;
                }
                if (parseBoolean2 && activityManager != null) {
                    runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses != null) {
                        arrayList2 = arrayList;
                        size = arrayList2.size();
                        i2 = 0;
                        while (i2 < size) {
                            int i3 = i2 + 1;
                            zza = (ResolveInfo) arrayList2.get(i2);
                            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                                if (runningAppProcessInfo.processName.equals(zza.activityInfo.packageName)) {
                                    return zza(zzf, zza);
                                }
                            }
                            i2 = i3;
                        }
                    }
                }
                return parseBoolean ? zza(zzf, (ResolveInfo) arrayList.get(0)) : zzf;
            }
        }
        i = 1;
        if ("http".equalsIgnoreCase(parse.getScheme())) {
            uri = parse.buildUpon().scheme("https").build();
        } else if ("https".equalsIgnoreCase(parse.getScheme())) {
            uri = parse.buildUpon().scheme("http").build();
        }
        arrayList = new ArrayList();
        zzf = zzf(parse);
        zzf2 = zzf(uri);
        if (i != 0) {
            zzbs.zzbz();
            zzagz.zzc(context, zzf);
            zzbs.zzbz();
            zzagz.zzc(context, zzf2);
        }
        zza = zza(context, zzf, arrayList);
        if (zza != null) {
            return zza(zzf, zza);
        }
        if (zzf2 != null) {
            zza = zza(context, zzf2);
            if (zza != null) {
                zzf2 = zza(zzf, zza);
                if (zza(context, zzf2) != null) {
                    return zzf2;
                }
            }
        }
        if (arrayList.size() == 0) {
            return zzf;
        }
        runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null) {
            arrayList2 = arrayList;
            size = arrayList2.size();
            i2 = 0;
            while (i2 < size) {
                int i32 = i2 + 1;
                zza = (ResolveInfo) arrayList2.get(i2);
                while (r10.hasNext()) {
                    if (runningAppProcessInfo.processName.equals(zza.activityInfo.packageName)) {
                        return zza(zzf, zza);
                    }
                }
                i2 = i32;
            }
        }
        if (parseBoolean) {
        }
    }
}
