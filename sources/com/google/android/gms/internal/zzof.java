package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.js.zzai;
import java.lang.ref.WeakReference;
import java.util.Map;

final class zzof extends zzym implements zzrd {
    private final String zzIe;
    private final WeakReference<zznz> zztX;

    public zzof(zznz zznz, String str) {
        this.zztX = new WeakReference(zznz);
        this.zzIe = str;
    }

    public final void zza(zzaka zzaka, Map<String, String> map) {
        String str = (String) map.get("ads_id");
        if (!TextUtils.isEmpty(str) && this.zzIe.equals(str)) {
            int parseInt;
            try {
                parseInt = Integer.parseInt((String) map.get("eventType"));
            } catch (Throwable e) {
                zzajc.zzb("Parse Scion log event type error", e);
                parseInt = -1;
            }
            zznz zznz;
            if (1 == parseInt) {
                zznz = (zznz) this.zztX.get();
                if (zznz != null) {
                    zznz.zzat();
                }
            } else if (parseInt == 0) {
                zznz = (zznz) this.zztX.get();
                if (zznz != null) {
                    zznz.zzau();
                }
            }
        }
    }

    public final void zzd(zzai zzai) {
        zzai.zza("/logScionEvent", (zzrd) this);
    }
}
