package com.google.android.gms.internal;

import java.util.HashMap;

public final class zzda extends zzbs<Integer, Long> {
    public Long zzqB;
    public Long zzqC;

    public zzda(String str) {
        zzi(str);
    }

    protected final void zzi(String str) {
        HashMap zzj = zzbs.zzj(str);
        if (zzj != null) {
            this.zzqB = (Long) zzj.get(Integer.valueOf(0));
            this.zzqC = (Long) zzj.get(Integer.valueOf(1));
        }
    }

    protected final HashMap<Integer, Long> zzv() {
        HashMap<Integer, Long> hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), this.zzqB);
        hashMap.put(Integer.valueOf(1), this.zzqC);
        return hashMap;
    }
}
