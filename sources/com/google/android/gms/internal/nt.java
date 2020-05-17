package com.google.android.gms.internal;

public final class nt<K, V> extends nu<K, V> {
    nt(K k, V v) {
        super(k, v, np.zzFF(), np.zzFF());
    }

    nt(K k, V v, nq<K, V> nqVar, nq<K, V> nqVar2) {
        super(k, v, nqVar, nqVar2);
    }

    protected final int zzFD() {
        return nr.zzbZY;
    }

    public final boolean zzFE() {
        return true;
    }

    protected final nu<K, V> zza(K k, V v, nq<K, V> nqVar, nq<K, V> nqVar2) {
        Object key;
        Object value;
        nq zzFG;
        nq zzFH;
        if (k == null) {
            key = getKey();
        }
        if (v == null) {
            value = getValue();
        }
        if (nqVar == null) {
            zzFG = zzFG();
        }
        if (nqVar2 == null) {
            zzFH = zzFH();
        }
        return new nt(key, value, zzFG, zzFH);
    }
}
