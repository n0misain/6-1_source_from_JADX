package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

final class si {
    private Map<wp, si> zzceA = null;
    private xm zzcez = null;

    public final void zza(qr qrVar, sm smVar) {
        if (this.zzcez != null) {
            smVar.zzf(qrVar, this.zzcez);
            return;
        }
        sl skVar = new sk(this, qrVar, smVar);
        if (this.zzceA != null) {
            for (Entry entry : this.zzceA.entrySet()) {
                skVar.zza((wp) entry.getKey(), (si) entry.getValue());
            }
        }
    }

    public final void zzh(qr qrVar, xm xmVar) {
        while (!qrVar.isEmpty()) {
            if (this.zzcez != null) {
                this.zzcez = this.zzcez.zzl(qrVar, xmVar);
                return;
            }
            if (this.zzceA == null) {
                this.zzceA = new HashMap();
            }
            wp zzHc = qrVar.zzHc();
            if (!this.zzceA.containsKey(zzHc)) {
                this.zzceA.put(zzHc, new si());
            }
            si siVar = (si) this.zzceA.get(zzHc);
            qrVar = qrVar.zzHd();
            this = siVar;
        }
        this.zzcez = xmVar;
        this.zzceA = null;
    }

    public final boolean zzq(qr qrVar) {
        while (!qrVar.isEmpty()) {
            if (this.zzcez != null) {
                if (this.zzcez.zzIQ()) {
                    return false;
                }
                wr wrVar = (wr) this.zzcez;
                this.zzcez = null;
                wrVar.zza(new sj(this, qrVar), false);
            } else if (this.zzceA == null) {
                return true;
            } else {
                wp zzHc = qrVar.zzHc();
                qr zzHd = qrVar.zzHd();
                if (this.zzceA.containsKey(zzHc) && ((si) this.zzceA.get(zzHc)).zzq(zzHd)) {
                    this.zzceA.remove(zzHc);
                }
                if (!this.zzceA.isEmpty()) {
                    return false;
                }
                this.zzceA = null;
                return true;
            }
        }
        this.zzcez = null;
        this.zzceA = null;
        return true;
    }
}
