package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import java.util.Map;

@zzzn
public final class zzrl implements zzrd {
    private final zzrm zzJC;

    public zzrl(zzrm zzrm) {
        this.zzJC = zzrm;
    }

    public final void zza(zzaka zzaka, Map<String, String> map) {
        float parseFloat;
        boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("transparentBackground"));
        boolean equals2 = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("blur"));
        try {
            if (map.get("blurRadius") != null) {
                parseFloat = Float.parseFloat((String) map.get("blurRadius"));
                this.zzJC.zzc(equals);
                this.zzJC.zza(equals2, parseFloat);
            }
        } catch (Throwable e) {
            zzajc.zzb("Fail to parse float", e);
        }
        parseFloat = 0.0f;
        this.zzJC.zzc(equals);
        this.zzJC.zza(equals2, parseFloat);
    }
}
