package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.overlay.zzaa;
import com.google.android.gms.ads.internal.overlay.zzaq;
import com.google.android.gms.ads.internal.zzbs;
import com.twitter.sdk.android.core.internal.VineCardUtils;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@zzzn
public final class zzrw implements zzrd {
    private boolean zzJK;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = (String) map.get(str);
        if (str2 != null) {
            try {
                zzji.zzds();
                i = zzaiy.zzc(context, Integer.parseInt(str2));
            } catch (NumberFormatException e) {
                zzajc.zzaT(new StringBuilder((String.valueOf(str).length() + 34) + String.valueOf(str2).length()).append("Could not parse ").append(str).append(" in a video GMSG: ").append(str2).toString());
            }
        }
        return i;
    }

    public final void zza(zzaka zzaka, Map<String, String> map) {
        int i = 0;
        String str = (String) map.get(NativeProtocol.WEB_DIALOG_ACTION);
        if (str == null) {
            zzajc.zzaT("Action missing from video GMSG.");
            return;
        }
        if (zzajc.zzz(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String valueOf = String.valueOf(jSONObject.toString());
            zzajc.zzaC(new StringBuilder((String.valueOf(str).length() + 13) + String.valueOf(valueOf).length()).append("Video GMSG: ").append(str).append(" ").append(valueOf).toString());
        }
        if ("background".equals(str)) {
            valueOf = (String) map.get("color");
            if (TextUtils.isEmpty(valueOf)) {
                zzajc.zzaT("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                zzaka.setBackgroundColor(Color.parseColor(valueOf));
            } catch (IllegalArgumentException e) {
                zzajc.zzaT("Invalid color parameter in video GMSG.");
            }
        } else if ("decoderProps".equals(str)) {
            valueOf = (String) map.get("mimeTypes");
            if (valueOf == null) {
                zzajc.zzaT("No MIME types specified for decoder properties inspection.");
                zzaa.zza(zzaka, "missingMimeTypes");
            } else if (VERSION.SDK_INT < 16) {
                zzajc.zzaT("Video decoder properties available on API versions >= 16.");
                zzaa.zza(zzaka, "deficientApiVersion");
            } else {
                Map hashMap = new HashMap();
                String[] split = valueOf.split(",");
                r2 = split.length;
                while (i < r2) {
                    String str2 = split[i];
                    hashMap.put(str2, zzaiw.zzaQ(str2.trim()));
                    i++;
                }
                zzaa.zzc(zzaka, hashMap);
            }
        } else {
            zzajz zziE = zzaka.zziE();
            if (zziE == null) {
                zzajc.zzaT("Could not get underlay container for a video GMSG.");
                return;
            }
            boolean equals = AppSettingsData.STATUS_NEW.equals(str);
            boolean equals2 = "position".equals(str);
            int min;
            if (equals || equals2) {
                Context context = zzaka.getContext();
                int zza = zza(context, map, "x", 0);
                r2 = zza(context, map, "y", 0);
                int zza2 = zza(context, map, "w", -1);
                int zza3 = zza(context, map, "h", -1);
                if (((Boolean) zzbs.zzbL().zzd(zzmo.zzFB)).booleanValue()) {
                    min = Math.min(zza2, zzaka.getMeasuredWidth() - zza);
                    zza3 = Math.min(zza3, zzaka.getMeasuredHeight() - r2);
                } else {
                    min = zza2;
                }
                try {
                    zza2 = Integer.parseInt((String) map.get(VineCardUtils.PLAYER_CARD));
                } catch (NumberFormatException e2) {
                    zza2 = 0;
                }
                boolean parseBoolean = Boolean.parseBoolean((String) map.get("spherical"));
                if (equals && zziE.zzip() == null) {
                    zziE.zza(zza, r2, min, zza3, zza2, parseBoolean, new zzaq((String) map.get("flags")));
                    return;
                } else {
                    zziE.zze(zza, r2, min, zza3);
                    return;
                }
            }
            zzaa zzip = zziE.zzip();
            if (zzip == null) {
                zzaa.zzh(zzaka);
            } else if ("click".equals(str)) {
                r0 = zzaka.getContext();
                r2 = zza(r0, map, "x", 0);
                min = zza(r0, map, "y", 0);
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) r2, (float) min, 0);
                zzip.zze(obtain);
                obtain.recycle();
            } else if ("currentTime".equals(str)) {
                valueOf = (String) map.get("time");
                if (valueOf == null) {
                    zzajc.zzaT("Time parameter missing from currentTime video GMSG.");
                    return;
                }
                try {
                    zzip.seekTo((int) (Float.parseFloat(valueOf) * 1000.0f));
                } catch (NumberFormatException e3) {
                    str = "Could not parse time parameter from currentTime video GMSG: ";
                    valueOf = String.valueOf(valueOf);
                    zzajc.zzaT(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
            } else if ("hide".equals(str)) {
                zzip.setVisibility(4);
            } else if ("load".equals(str)) {
                zzip.zzfY();
            } else if ("muted".equals(str)) {
                if (Boolean.parseBoolean((String) map.get("muted"))) {
                    zzip.zzfZ();
                } else {
                    zzip.zzga();
                }
            } else if ("pause".equals(str)) {
                zzip.pause();
            } else if ("play".equals(str)) {
                zzip.play();
            } else if ("show".equals(str)) {
                zzip.setVisibility(0);
            } else if ("src".equals(str)) {
                zzip.zzaq((String) map.get("src"));
            } else if ("touchMove".equals(str)) {
                r0 = zzaka.getContext();
                zzip.zza((float) zza(r0, map, "dx", 0), (float) zza(r0, map, "dy", 0));
                if (!this.zzJK) {
                    zzaka.zziu().zzfQ();
                    this.zzJK = true;
                }
            } else if (MediaRouteProviderProtocol.CLIENT_DATA_VOLUME.equals(str)) {
                valueOf = (String) map.get(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME);
                if (valueOf == null) {
                    zzajc.zzaT("Level parameter missing from volume video GMSG.");
                    return;
                }
                try {
                    zzip.zzb(Float.parseFloat(valueOf));
                } catch (NumberFormatException e4) {
                    str = "Could not parse volume parameter from volume video GMSG: ";
                    valueOf = String.valueOf(valueOf);
                    zzajc.zzaT(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
            } else if ("watermark".equals(str)) {
                zzip.zzgb();
            } else {
                String str3 = "Unknown video action: ";
                valueOf = String.valueOf(str);
                zzajc.zzaT(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            }
        }
    }
}
