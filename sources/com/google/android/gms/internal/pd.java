package com.google.android.gms.internal;

import java.util.Map;

final class pd {
    private Map<String, Object> zzcbB;
    private boolean zzcbC;
    private String zzcbv;
    private pf zzcbw;

    private pd(String str, Map<String, Object> map, pf pfVar) {
        this.zzcbv = str;
        this.zzcbB = map;
        this.zzcbw = pfVar;
    }

    public final String getAction() {
        return this.zzcbv;
    }

    public final pf zzGl() {
        return this.zzcbw;
    }

    public final Map<String, Object> zzGp() {
        return this.zzcbB;
    }

    public final void zzGq() {
        this.zzcbC = true;
    }

    public final boolean zzGr() {
        return this.zzcbC;
    }
}
