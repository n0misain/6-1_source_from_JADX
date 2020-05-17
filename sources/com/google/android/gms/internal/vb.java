package com.google.android.gms.internal;

import java.util.Map.Entry;

public final class vb<T> {
    private vf<T> zzcgA;
    private wp zzcgy;
    private vb<T> zzcgz;

    public vb() {
        this(null, null, new vf());
    }

    private vb(wp wpVar, vb<T> vbVar, vf<T> vfVar) {
        this.zzcgy = wpVar;
        this.zzcgz = vbVar;
        this.zzcgA = vfVar;
    }

    private final void zzHT() {
        while (this.zzcgz != null) {
            vb vbVar = this.zzcgz;
            wp wpVar = this.zzcgy;
            Object obj = (this.zzcgA.value == null && this.zzcgA.zzceA.isEmpty()) ? 1 : null;
            boolean containsKey = vbVar.zzcgA.zzceA.containsKey(wpVar);
            if (obj != null && containsKey) {
                vbVar.zzcgA.zzceA.remove(wpVar);
                this = vbVar;
            } else if (obj == null && !containsKey) {
                vbVar.zzcgA.zzceA.put(wpVar, this.zzcgA);
                this = vbVar;
            } else {
                return;
            }
        }
    }

    public final T getValue() {
        return this.zzcgA.value;
    }

    public final boolean hasChildren() {
        return !this.zzcgA.zzceA.isEmpty();
    }

    public final void setValue(T t) {
        this.zzcgA.value = t;
        zzHT();
    }

    public final String toString() {
        String str = "";
        String asString = this.zzcgy == null ? "<anon>" : this.zzcgy.asString();
        String valueOf = String.valueOf(this.zzcgA.toString(String.valueOf(str).concat("\t")));
        return new StringBuilder(((String.valueOf(str).length() + 1) + String.valueOf(asString).length()) + String.valueOf(valueOf).length()).append(str).append(asString).append("\n").append(valueOf).toString();
    }

    public final qr zzFq() {
        if (this.zzcgz != null) {
            return this.zzcgz.zzFq().zza(this.zzcgy);
        }
        if (this.zzcgy == null) {
            return qr.zzGZ();
        }
        return new qr(this.zzcgy);
    }

    public final vb<T> zzK(qr qrVar) {
        vb<T> vbVar;
        wp zzHc = qrVar.zzHc();
        while (zzHc != null) {
            vb<T> vbVar2 = new vb(zzHc, vbVar, vbVar.zzcgA.zzceA.containsKey(zzHc) ? (vf) vbVar.zzcgA.zzceA.get(zzHc) : new vf());
            qrVar = qrVar.zzHd();
            zzHc = qrVar.zzHc();
            vbVar = vbVar2;
        }
        return vbVar;
    }

    public final void zza(ve<T> veVar) {
        Object[] toArray = this.zzcgA.zzceA.entrySet().toArray();
        for (Object obj : toArray) {
            Entry entry = (Entry) obj;
            veVar.zzd(new vb((wp) entry.getKey(), this, (vf) entry.getValue()));
        }
    }

    public final void zza(ve<T> veVar, boolean z, boolean z2) {
        if (z && !z2) {
            veVar.zzd(this);
        }
        zza(new vc(this, veVar, z2));
        if (z && z2) {
            veVar.zzd(this);
        }
    }

    public final boolean zza(vd<T> vdVar, boolean z) {
        for (vb vbVar = this.zzcgz; vbVar != null; vbVar = vbVar.zzcgz) {
            vdVar.zze(vbVar);
        }
        return false;
    }
}
