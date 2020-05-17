package com.google.android.gms.internal;

import java.util.Comparator;

public abstract class nu<K, V> implements nq<K, V> {
    private final V value;
    private final K zzcab;
    private nq<K, V> zzcac;
    private final nq<K, V> zzcad;

    nu(K k, V v, nq<K, V> nqVar, nq<K, V> nqVar2) {
        nq zzFF;
        nq zzFF2;
        this.zzcab = k;
        this.value = v;
        if (nqVar == null) {
            zzFF = np.zzFF();
        }
        this.zzcac = zzFF;
        if (nqVar2 == null) {
            zzFF2 = np.zzFF();
        }
        this.zzcad = zzFF2;
    }

    private final nq<K, V> zzFL() {
        if (this.zzcac.isEmpty()) {
            return np.zzFF();
        }
        if (!(this.zzcac.zzFE() || this.zzcac.zzFG().zzFE())) {
            this = zzFM();
        }
        return zza(null, null, ((nu) this.zzcac).zzFL(), null).zzFN();
    }

    private final nu<K, V> zzFM() {
        nu<K, V> zzFQ = zzFQ();
        return zzFQ.zzcad.zzFG().zzFE() ? zzFQ.zza(null, null, null, ((nu) zzFQ.zzcad).zzFP()).zzFO().zzFQ() : zzFQ;
    }

    private final nu<K, V> zzFN() {
        nu<K, V> zzFO;
        if (this.zzcad.zzFE() && !this.zzcac.zzFE()) {
            zzFO = zzFO();
        }
        if (zzFO.zzcac.zzFE() && ((nu) zzFO.zzcac).zzcac.zzFE()) {
            zzFO = zzFO.zzFP();
        }
        return (zzFO.zzcac.zzFE() && zzFO.zzcad.zzFE()) ? zzFO.zzFQ() : zzFO;
    }

    private final nu<K, V> zzFO() {
        return (nu) this.zzcad.zza(null, null, zzFD(), zzb(null, null, nr.zzbZY, null, ((nu) this.zzcad).zzcac), null);
    }

    private final nu<K, V> zzFP() {
        return (nu) this.zzcac.zza(null, null, zzFD(), null, zzb(null, null, nr.zzbZY, ((nu) this.zzcac).zzcad, null));
    }

    private final nu<K, V> zzFQ() {
        return zzb(null, null, zza((nq) this), this.zzcac.zza(null, null, zza(this.zzcac), null, null), this.zzcad.zza(null, null, zza(this.zzcad), null, null));
    }

    private static int zza(nq nqVar) {
        return nqVar.zzFE() ? nr.zzbZZ : nr.zzbZY;
    }

    private final nu<K, V> zzb(K k, V v, Integer num, nq<K, V> nqVar, nq<K, V> nqVar2) {
        nq nqVar3;
        nq nqVar4;
        Object obj = this.zzcab;
        Object obj2 = this.value;
        if (nqVar == null) {
            nqVar3 = this.zzcac;
        }
        if (nqVar2 == null) {
            nqVar4 = this.zzcad;
        }
        return num == nr.zzbZY ? new nt(obj, obj2, nqVar3, nqVar4) : new no(obj, obj2, nqVar3, nqVar4);
    }

    public final K getKey() {
        return this.zzcab;
    }

    public final V getValue() {
        return this.value;
    }

    public final boolean isEmpty() {
        return false;
    }

    protected abstract int zzFD();

    public final nq<K, V> zzFG() {
        return this.zzcac;
    }

    public final nq<K, V> zzFH() {
        return this.zzcad;
    }

    public final nq<K, V> zzFI() {
        return this.zzcac.isEmpty() ? this : this.zzcac.zzFI();
    }

    public final nq<K, V> zzFJ() {
        return this.zzcad.isEmpty() ? this : this.zzcad.zzFJ();
    }

    public final int zzFK() {
        return (this.zzcac.zzFK() + 1) + this.zzcad.zzFK();
    }

    public final /* synthetic */ nq zza(Object obj, Object obj2, int i, nq nqVar, nq nqVar2) {
        return zzb(null, null, i, nqVar, nqVar2);
    }

    public final nq<K, V> zza(K k, V v, Comparator<K> comparator) {
        int compare = comparator.compare(k, this.zzcab);
        nu zza = compare < 0 ? zza(null, null, this.zzcac.zza(k, v, comparator), null) : compare == 0 ? zza(k, v, null, null) : zza(null, null, null, this.zzcad.zza(k, v, comparator));
        return zza.zzFN();
    }

    public final nq<K, V> zza(K k, Comparator<K> comparator) {
        nu zza;
        if (comparator.compare(k, this.zzcab) < 0) {
            if (!(this.zzcac.isEmpty() || this.zzcac.zzFE() || ((nu) this.zzcac).zzcac.zzFE())) {
                this = zzFM();
            }
            zza = zza(null, null, this.zzcac.zza(k, comparator), null);
        } else {
            if (this.zzcac.zzFE()) {
                this = zzFP();
            }
            if (!(this.zzcad.isEmpty() || this.zzcad.zzFE() || ((nu) this.zzcad).zzcac.zzFE())) {
                zza = zzFQ();
                if (zza.zzcac.zzFG().zzFE()) {
                    zza = zza.zzFP().zzFQ();
                }
                this = zza;
            }
            if (comparator.compare(k, this.zzcab) == 0) {
                if (this.zzcad.isEmpty()) {
                    return np.zzFF();
                }
                nq zzFI = this.zzcad.zzFI();
                this = zza(zzFI.getKey(), zzFI.getValue(), null, ((nu) this.zzcad).zzFL());
            }
            zza = zza(null, null, null, this.zzcad.zza(k, comparator));
        }
        return zza.zzFN();
    }

    protected abstract nu<K, V> zza(K k, V v, nq<K, V> nqVar, nq<K, V> nqVar2);

    public final void zza(ns<K, V> nsVar) {
        this.zzcac.zza(nsVar);
        nsVar.zzh(this.zzcab, this.value);
        this.zzcad.zza(nsVar);
    }

    final void zzb(nq<K, V> nqVar) {
        this.zzcac = nqVar;
    }
}
