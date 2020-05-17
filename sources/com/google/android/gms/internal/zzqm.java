package com.google.android.gms.internal;

import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;

@zzzn
public final class zzqm implements zzrd {
    public final void zza(zzaka zzaka, Map<String, String> map) {
        String str = (String) map.get(NativeProtocol.WEB_DIALOG_ACTION);
        String str2;
        if ("tick".equals(str)) {
            str = (String) map.get("label");
            str2 = (String) map.get("start_label");
            String str3 = (String) map.get(Param.TIMESTAMP);
            if (TextUtils.isEmpty(str)) {
                zzajc.zzaT("No label given for CSI tick.");
            } else if (TextUtils.isEmpty(str3)) {
                zzajc.zzaT("No timestamp given for CSI tick.");
            } else {
                try {
                    long parseLong = (Long.parseLong(str3) - zzbs.zzbF().currentTimeMillis()) + zzbs.zzbF().elapsedRealtime();
                    if (TextUtils.isEmpty(str2)) {
                        str2 = "native:view_load";
                    }
                    zzaka.zziG().zza(str, str2, parseLong);
                } catch (Throwable e) {
                    zzajc.zzc("Malformed timestamp for CSI tick.", e);
                }
            }
        } else if ("experiment".equals(str)) {
            str = (String) map.get(FirebaseAnalytics.Param.VALUE);
            if (TextUtils.isEmpty(str)) {
                zzajc.zzaT("No value given for CSI experiment.");
                return;
            }
            zznb zzdR = zzaka.zziG().zzdR();
            if (zzdR == null) {
                zzajc.zzaT("No ticker for WebView, dropping experiment ID.");
            } else {
                zzdR.zzh("e", str);
            }
        } else if ("extra".equals(str)) {
            str = (String) map.get("name");
            str2 = (String) map.get(FirebaseAnalytics.Param.VALUE);
            if (TextUtils.isEmpty(str2)) {
                zzajc.zzaT("No value given for CSI extra.");
            } else if (TextUtils.isEmpty(str)) {
                zzajc.zzaT("No name given for CSI extra.");
            } else {
                zznb zzdR2 = zzaka.zziG().zzdR();
                if (zzdR2 == null) {
                    zzajc.zzaT("No ticker for WebView, dropping extra parameter.");
                } else {
                    zzdR2.zzh(str, str2);
                }
            }
        }
    }
}
