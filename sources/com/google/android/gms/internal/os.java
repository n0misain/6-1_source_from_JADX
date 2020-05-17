package com.google.android.gms.internal;

import java.util.Map;

final class os implements oy {
    private /* synthetic */ pf zzcbi;

    os(op opVar, pf pfVar) {
        this.zzcbi = pfVar;
    }

    public final void zzC(Map<String, Object> map) {
        String str = null;
        String str2 = (String) map.get("s");
        if (str2.equals("ok")) {
            str2 = null;
        } else {
            str = (String) map.get("d");
        }
        if (this.zzcbi != null) {
            this.zzcbi.zzaa(str2, str);
        }
    }
}
