package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public final class uv<T> implements Iterable<Entry<qr, T>> {
    private static final nh zzcgt = ni.zza(ob.zze(wp.class));
    private static final uv zzcgu = new uv(null, zzcgt);
    private final T value;
    private final nh<wp, uv<T>> zzcgs;

    public uv(T t) {
        this(t, zzcgt);
    }

    private uv(T t, nh<wp, uv<T>> nhVar) {
        this.value = t;
        this.zzcgs = nhVar;
    }

    public static <V> uv<V> zzHR() {
        return zzcgu;
    }

    private final <R> R zza(qr qrVar, uy<? super T, R> uyVar, R r) {
        Iterator it = this.zzcgs.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            r = ((uv) entry.getValue()).zza(qrVar.zza((wp) entry.getKey()), uyVar, r);
        }
        return this.value != null ? uyVar.zza(qrVar, this.value, r) : r;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        uv uvVar = (uv) obj;
        if (this.zzcgs == null ? uvVar.zzcgs != null : !this.zzcgs.equals(uvVar.zzcgs)) {
            return false;
        }
        if (this.value != null) {
            if (this.value.equals(uvVar.value)) {
                return true;
            }
        } else if (uvVar.value == null) {
            return true;
        }
        return false;
    }

    public final T getValue() {
        return this.value;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.value != null ? this.value.hashCode() : 0) * 31;
        if (this.zzcgs != null) {
            i = this.zzcgs.hashCode();
        }
        return hashCode + i;
    }

    public final boolean isEmpty() {
        return this.value == null && this.zzcgs.isEmpty();
    }

    public final Iterator<Entry<qr, T>> iterator() {
        List arrayList = new ArrayList();
        zza(new ux(this, arrayList));
        return arrayList.iterator();
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ImmutableTree { value=");
        stringBuilder.append(this.value);
        stringBuilder.append(", children={");
        Iterator it = this.zzcgs.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            stringBuilder.append(((wp) entry.getKey()).asString());
            stringBuilder.append("=");
            stringBuilder.append(entry.getValue());
        }
        stringBuilder.append("} }");
        return stringBuilder.toString();
    }

    public final Collection<T> values() {
        Collection arrayList = new ArrayList();
        zza(new uw(this, arrayList));
        return arrayList;
    }

    public final qr zzF(qr qrVar) {
        return zza(qrVar, uz.zzcgx);
    }

    public final T zzG(qr qrVar) {
        uz uzVar = uz.zzcgx;
        T t = (this.value == null || !uzVar.zzaj(this.value)) ? null : this.value;
        Iterator it = qrVar.iterator();
        T t2 = t;
        while (it.hasNext()) {
            uv uvVar = (uv) this.zzcgs.get((wp) it.next());
            if (uvVar == null) {
                break;
            }
            if (uvVar.value != null && uzVar.zzaj(uvVar.value)) {
                t2 = uvVar.value;
            }
            this = uvVar;
        }
        return t2;
    }

    public final uv<T> zzH(qr qrVar) {
        uv<T> uvVar;
        while (!qrVar.isEmpty()) {
            uv<T> uvVar2 = (uv) uvVar.zzcgs.get(qrVar.zzHc());
            if (uvVar2 == null) {
                return zzcgu;
            }
            qrVar = qrVar.zzHd();
            uvVar = uvVar2;
        }
        return uvVar;
    }

    public final nh<wp, uv<T>> zzHS() {
        return this.zzcgs;
    }

    public final uv<T> zzI(qr qrVar) {
        if (qrVar.isEmpty()) {
            return this.zzcgs.isEmpty() ? zzcgu : new uv(null, this.zzcgs);
        } else {
            wp zzHc = qrVar.zzHc();
            uv uvVar = (uv) this.zzcgs.get(zzHc);
            if (uvVar == null) {
                return this;
            }
            uvVar = uvVar.zzI(qrVar.zzHd());
            nh zzX = uvVar.isEmpty() ? this.zzcgs.zzX(zzHc) : this.zzcgs.zzg(zzHc, uvVar);
            return (this.value == null && zzX.isEmpty()) ? zzcgu : new uv(this.value, zzX);
        }
    }

    public final T zzJ(qr qrVar) {
        while (!qrVar.isEmpty()) {
            uv uvVar = (uv) this.zzcgs.get(qrVar.zzHc());
            if (uvVar == null) {
                return null;
            }
            qrVar = qrVar.zzHd();
            this = uvVar;
        }
        return this.value;
    }

    public final qr zza(qr qrVar, uz<? super T> uzVar) {
        if (this.value != null && uzVar.zzaj(this.value)) {
            return qr.zzGZ();
        }
        if (qrVar.isEmpty()) {
            return null;
        }
        uv uvVar = (uv) this.zzcgs.get(qrVar.zzHc());
        if (uvVar == null) {
            return null;
        }
        qr zza = uvVar.zza(qrVar.zzHd(), (uz) uzVar);
        if (zza == null) {
            return null;
        }
        return new qr(r2).zzh(zza);
    }

    public final uv<T> zza(qr qrVar, uv<T> uvVar) {
        if (qrVar.isEmpty()) {
            return uvVar;
        }
        wp zzHc = qrVar.zzHc();
        uv uvVar2 = (uv) this.zzcgs.get(zzHc);
        if (uvVar2 == null) {
            uvVar2 = zzcgu;
        }
        uvVar2 = uvVar2.zza(qrVar.zzHd(), (uv) uvVar);
        return new uv(this.value, uvVar2.isEmpty() ? this.zzcgs.zzX(zzHc) : this.zzcgs.zzg(zzHc, uvVar2));
    }

    public final void zza(uy<T, Void> uyVar) {
        zza(qr.zzGZ(), uyVar, null);
    }

    public final uv<T> zzb(qr qrVar, T t) {
        if (qrVar.isEmpty()) {
            return new uv(t, this.zzcgs);
        }
        wp zzHc = qrVar.zzHc();
        uv uvVar = (uv) this.zzcgs.get(zzHc);
        if (uvVar == null) {
            uvVar = zzcgu;
        }
        return new uv(this.value, this.zzcgs.zzg(zzHc, uvVar.zzb(qrVar.zzHd(), (Object) t)));
    }

    public final T zzb(qr qrVar, uz<? super T> uzVar) {
        if (this.value != null && uzVar.zzaj(this.value)) {
            return this.value;
        }
        Iterator it = qrVar.iterator();
        while (it.hasNext()) {
            uv uvVar = (uv) this.zzcgs.get((wp) it.next());
            if (uvVar == null) {
                return null;
            }
            if (uvVar.value != null && uzVar.zzaj(uvVar.value)) {
                return uvVar.value;
            }
            this = uvVar;
        }
        return null;
    }

    public final <R> R zzb(R r, uy<? super T, R> uyVar) {
        return zza(qr.zzGZ(), uyVar, r);
    }

    public final boolean zzb(uz<? super T> uzVar) {
        if (this.value != null && uzVar.zzaj(this.value)) {
            return true;
        }
        Iterator it = this.zzcgs.iterator();
        while (it.hasNext()) {
            if (((uv) ((Entry) it.next()).getValue()).zzb(uzVar)) {
                return true;
            }
        }
        return false;
    }

    public final uv<T> zze(wp wpVar) {
        uv<T> uvVar = (uv) this.zzcgs.get(wpVar);
        return uvVar != null ? uvVar : zzcgu;
    }
}
