package com.google.android.gms.internal;

import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzbs;
import java.util.Map;

@zzzn
final class zzrx implements zzrd {
    zzrx() {
    }

    public final void zza(zzaka zzaka, Map<String, String> map) {
        Throwable e;
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzEC)).booleanValue()) {
            zzaks zzaks;
            zzaks zziH = zzaka.zziH();
            if (zziH == null) {
                try {
                    zziH = new zzaks(zzaka, Float.parseFloat((String) map.get("duration")), AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("customControlsAllowed")));
                    zzaka.zza(zziH);
                    zzaks = zziH;
                } catch (NullPointerException e2) {
                    e = e2;
                    zzajc.zzb("Unable to parse videoMeta message.", e);
                    zzbs.zzbD().zza(e, "VideoMetaGmsgHandler.onGmsg");
                    return;
                } catch (NumberFormatException e3) {
                    e = e3;
                    zzajc.zzb("Unable to parse videoMeta message.", e);
                    zzbs.zzbD().zza(e, "VideoMetaGmsgHandler.onGmsg");
                    return;
                }
            }
            zzaks = zziH;
            boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("muted"));
            float parseFloat = Float.parseFloat((String) map.get("currentTime"));
            int parseInt = Integer.parseInt((String) map.get("playbackState"));
            int i = (parseInt < 0 || 3 < parseInt) ? 0 : parseInt;
            String str = (String) map.get("aspectRatio");
            float parseFloat2 = TextUtils.isEmpty(str) ? 0.0f : Float.parseFloat(str);
            if (zzajc.zzz(3)) {
                zzajc.zzaC(new StringBuilder(String.valueOf(str).length() + 79).append("Video Meta GMSG: isMuted : ").append(equals).append(" , playbackState : ").append(i).append(" , aspectRatio : ").append(str).toString());
            }
            zzaks.zza(parseFloat, i, equals, parseFloat2);
        }
    }
}
