package com.google.android.gms.internal;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinEventParameters;
import com.facebook.internal.NativeProtocol;
import java.util.Map;

@zzzn
public final class zzru implements zzrd {
    private final zzrv zzJJ;

    public zzru(zzrv zzrv) {
        this.zzJJ = zzrv;
    }

    public final void zza(zzaka zzaka, Map<String, String> map) {
        String str = (String) map.get(NativeProtocol.WEB_DIALOG_ACTION);
        if ("grant".equals(str)) {
            zzaee zzaee;
            try {
                int parseInt = Integer.parseInt((String) map.get(AppLovinEventParameters.REVENUE_AMOUNT));
                str = (String) map.get("type");
                if (!TextUtils.isEmpty(str)) {
                    zzaee = new zzaee(str, parseInt);
                    this.zzJJ.zzb(zzaee);
                }
            } catch (Throwable e) {
                zzajc.zzc("Unable to parse reward amount.", e);
            }
            zzaee = null;
            this.zzJJ.zzb(zzaee);
        } else if ("video_start".equals(str)) {
            this.zzJJ.zzbc();
        }
    }
}
