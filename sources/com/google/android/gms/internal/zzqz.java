package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import java.util.Map;

final class zzqz implements zzrd {
    zzqz() {
    }

    public final void zza(zzaka zzaka, Map<String, String> map) {
        zzaka.zzB(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close")));
    }
}
