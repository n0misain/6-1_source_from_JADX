package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

final class sr implements Callable<List<? extends vk>> {
    private /* synthetic */ so zzceR;
    private /* synthetic */ qi zzceU;

    sr(so soVar, qi qiVar) {
        this.zzceR = soVar;
        this.zzceU = qiVar;
    }

    public final /* synthetic */ Object call() throws Exception {
        sn snVar;
        xm xmVar;
        sn snVar2;
        vg vgVar;
        vt zzGH = this.zzceU.zzGH();
        qr zzFq = zzGH.zzFq();
        xm xmVar2 = null;
        qr qrVar = zzFq;
        uv zzd = this.zzceR.zzceG;
        boolean z = false;
        while (!zzd.isEmpty()) {
            boolean z2;
            xm xmVar3;
            snVar = (sn) zzd.getValue();
            if (snVar != null) {
                if (xmVar2 == null) {
                    xmVar2 = snVar.zzr(qrVar);
                }
                z2 = z || snVar.zzHo();
                xmVar3 = xmVar2;
            } else {
                z2 = z;
                xmVar3 = xmVar2;
            }
            zzd = zzd.zze(qrVar.isEmpty() ? wp.zzgT("") : qrVar.zzHc());
            qrVar = qrVar.zzHd();
            xmVar2 = xmVar3;
            z = z2;
        }
        snVar = (sn) this.zzceR.zzceG.zzJ(zzFq);
        sn snVar3;
        boolean z3;
        if (snVar == null) {
            snVar = new sn(this.zzceR.zzceF);
            this.zzceR.zzceG = this.zzceR.zzceG.zzb(zzFq, (Object) snVar);
            snVar3 = snVar;
            xmVar = xmVar2;
            z3 = z;
            snVar2 = snVar3;
        } else {
            z = z || snVar.zzHo();
            if (xmVar2 == null) {
                xmVar2 = snVar.zzr(qr.zzGZ());
            }
            snVar3 = snVar;
            xmVar = xmVar2;
            z3 = z;
            snVar2 = snVar3;
        }
        this.zzceR.zzceF.zzg(zzGH);
        if (xmVar != null) {
            vgVar = new vg(xf.zza(xmVar, zzGH.zzIm()), true, false);
        } else {
            vg zzf = this.zzceR.zzceF.zzf(zzGH);
            if (zzf.zzHU()) {
                vgVar = zzf;
            } else {
                xm zzJb = xd.zzJb();
                Iterator it = this.zzceR.zzceG.zzH(zzFq).zzHS().iterator();
                while (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    sn snVar4 = (sn) ((uv) entry.getValue()).getValue();
                    if (snVar4 != null) {
                        xm zzr = snVar4.zzr(qr.zzGZ());
                        if (zzr != null) {
                            xmVar = zzJb.zze((wp) entry.getKey(), zzr);
                            zzJb = xmVar;
                        }
                    }
                    xmVar = zzJb;
                    zzJb = xmVar;
                }
                for (xl xlVar : zzf.zzFn()) {
                    if (!zzJb.zzk(xlVar.zzJk())) {
                        zzJb = zzJb.zze(xlVar.zzJk(), xlVar.zzFn());
                    }
                }
                vgVar = new vg(xf.zza(zzJb, zzGH.zzIm()), false, false);
            }
        }
        boolean zzc = snVar2.zzc(zzGH);
        if (!(zzc || zzGH.zzIq())) {
            th zzf2 = this.zzceR.zzHr();
            this.zzceR.zzceJ.put(zzGH, zzf2);
            this.zzceR.zzceI.put(zzf2, zzGH);
        }
        List zza = snVar2.zza(this.zzceU, this.zzceR.zzceH.zzt(zzFq), vgVar);
        if (!(zzc || r4)) {
            this.zzceR.zza(zzGH, snVar2.zzb(zzGH));
        }
        return zza;
    }
}
