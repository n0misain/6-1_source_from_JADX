package com.google.android.gms.internal;

public final class no<K, V> extends nu<K, V> {
    no(K k, V v, nq<K, V> nqVar, nq<K, V> nqVar2) {
        super(k, v, nqVar, nqVar2);
    }

    protected final int zzFD() {
        return nr.zzbZZ;
    }

    public final boolean zzFE() {
        return false;
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
        return new no(key, value, zzFG, zzFH);
    }
}
