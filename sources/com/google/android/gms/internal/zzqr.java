package com.google.android.gms.internal;

import com.facebook.internal.NativeProtocol;
import java.util.Map;

final class zzqr implements zzrd {
    zzqr() {
    }

    public final void zza(zzaka zzaka, Map<String, String> map) {
        String str = (String) map.get(NativeProtocol.WEB_DIALOG_ACTION);
        if ("pause".equals(str)) {
            zzaka.zzaJ();
        } else if ("resume".equals(str)) {
            zzaka.zzaK();
        }
    }
}
