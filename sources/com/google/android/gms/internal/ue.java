package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;

public final class ue implements uh {
    private final wl zzbZE;
    private final ui zzcfX;
    private final uo zzcfY;
    private final uc zzcfZ;
    private long zzcga;

    public ue(qd qdVar, ui uiVar, uc ucVar) {
        this(qdVar, uiVar, ucVar, new yt());
    }

    private ue(qd qdVar, ui uiVar, uc ucVar, ys ysVar) {
        this.zzcga = 0;
        this.zzcfX = uiVar;
        this.zzbZE = qdVar.zzgP("Persistence");
        this.zzcfY = new uo(this.zzcfX, this.zzbZE, ysVar);
        this.zzcfZ = ucVar;
    }

    private final void zzHM() {
        this.zzcga++;
        if (this.zzcfZ.zzax(this.zzcga)) {
            if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb("Reached prune check threshold.", null, new Object[0]);
            }
            this.zzcga = 0;
            int i = 1;
            long zzFt = this.zzcfX.zzFt();
            if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb("Cache size: " + zzFt, null, new Object[0]);
            }
            while (i != 0 && this.zzcfZ.zzi(r2, this.zzcfY.zzHP())) {
                uj zza = this.zzcfY.zza(this.zzcfZ);
                if (zza.zzHN()) {
                    this.zzcfX.zza(qr.zzGZ(), zza);
                } else {
                    i = 0;
                }
                zzFt = this.zzcfX.zzFt();
                if (this.zzbZE.zzIH()) {
                    this.zzbZE.zzb("Cache size after prune: " + zzFt, null, new Object[0]);
                }
            }
        }
    }

    public final List<tm> zzFs() {
        return this.zzcfX.zzFs();
    }

    public final void zzFv() {
        this.zzcfX.zzFv();
    }

    public final void zza(qr qrVar, pz pzVar, long j) {
        this.zzcfX.zza(qrVar, pzVar, j);
    }

    public final void zza(qr qrVar, xm xmVar, long j) {
        this.zzcfX.zza(qrVar, xmVar, j);
    }

    public final void zza(vt vtVar, xm xmVar) {
        if (vtVar.zzIq()) {
            this.zzcfX.zza(vtVar.zzFq(), xmVar);
        } else {
            this.zzcfX.zzb(vtVar.zzFq(), xmVar);
        }
        zzi(vtVar);
        zzHM();
    }

    public final void zza(vt vtVar, Set<wp> set) {
        this.zzcfX.zza(this.zzcfY.zzk(vtVar).id, (Set) set);
    }

    public final void zza(vt vtVar, Set<wp> set, Set<wp> set2) {
        this.zzcfX.zza(this.zzcfY.zzk(vtVar).id, (Set) set, (Set) set2);
    }

    public final void zzal(long j) {
        this.zzcfX.zzal(j);
    }

    public final void zzc(qr qrVar, pz pzVar) {
        Iterator it = pzVar.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzk(qrVar.zzh((qr) entry.getKey()), (xm) entry.getValue());
        }
    }

    public final void zzd(qr qrVar, pz pzVar) {
        this.zzcfX.zza(qrVar, pzVar);
        zzHM();
    }

    public final vg zzf(vt vtVar) {
        boolean z;
        Set set;
        if (this.zzcfY.zzm(vtVar)) {
            un zzk = this.zzcfY.zzk(vtVar);
            if (vtVar.zzIq() || zzk == null || !zzk.complete) {
                set = null;
                z = true;
            } else {
                set = this.zzcfX.zzao(zzk.id);
                z = true;
            }
        } else {
            set = this.zzcfY.zzA(vtVar.zzFq());
            z = false;
        }
        xm zza = this.zzcfX.zza(vtVar.zzFq());
        if (r3 == null) {
            return new vg(xf.zza(zza, vtVar.zzIm()), true, false);
        }
        xd zzJb = xd.zzJb();
        xm xmVar = zzJb;
        for (wp wpVar : r3) {
            xmVar = xmVar.zze(wpVar, zza.zzm(wpVar));
        }
        return new vg(xf.zza(xmVar, vtVar.zzIm()), z, true);
    }

    public final <T> T zzg(Callable<T> callable) {
        this.zzcfX.beginTransaction();
        try {
            T call = callable.call();
            this.zzcfX.setTransactionSuccessful();
            this.zzcfX.endTransaction();
            return call;
        } catch (Throwable th) {
            this.zzcfX.endTransaction();
        }
    }

    public final void zzg(vt vtVar) {
        this.zzcfY.zzg(vtVar);
    }

    public final void zzh(vt vtVar) {
        this.zzcfY.zzh(vtVar);
    }

    public final void zzi(vt vtVar) {
        if (vtVar.zzIq()) {
            this.zzcfY.zzz(vtVar.zzFq());
        } else {
            this.zzcfY.zzl(vtVar);
        }
    }

    public final void zzk(qr qrVar, xm xmVar) {
        if (!this.zzcfY.zzC(qrVar)) {
            this.zzcfX.zza(qrVar, xmVar);
            this.zzcfY.zzB(qrVar);
        }
    }
}
