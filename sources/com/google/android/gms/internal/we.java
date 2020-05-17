package com.google.android.gms.internal;

import java.util.Iterator;

public final class we implements wf {
    private final int limit;
    private final xe zzcgV;
    private final boolean zzchA;
    private final wh zzchz;

    public we(vq vqVar) {
        this.zzchz = new wh(vqVar);
        this.zzcgV = vqVar.zzIm();
        this.limit = vqVar.getLimit();
        this.zzchA = !vqVar.zzIo();
    }

    public final wf zzID() {
        return this.zzchz.zzID();
    }

    public final boolean zzIE() {
        return true;
    }

    public final xe zzIm() {
        return this.zzcgV;
    }

    public final xf zza(xf xfVar, wp wpVar, xm xmVar, qr qrVar, wg wgVar, wc wcVar) {
        xm zzJb = !this.zzchz.zza(new xl(wpVar, xmVar)) ? xd.zzJb() : xmVar;
        if (xfVar.zzFn().zzm(wpVar).equals(zzJb)) {
            return xfVar;
        }
        if (xfVar.zzFn().getChildCount() < this.limit) {
            return this.zzchz.zzID().zza(xfVar, wpVar, zzJb, qrVar, wgVar, wcVar);
        }
        xl xlVar = new xl(wpVar, zzJb);
        xl zzJf = this.zzchA ? xfVar.zzJf() : xfVar.zzJg();
        boolean zza = this.zzchz.zza(xlVar);
        if (xfVar.zzFn().zzk(wpVar)) {
            xm zzm = xfVar.zzFn().zzm(wpVar);
            xl zza2 = wgVar.zza(this.zzcgV, zzJf, this.zzchA);
            while (zza2 != null && (zza2.zzJk().equals(wpVar) || xfVar.zzFn().zzk(zza2.zzJk()))) {
                zza2 = wgVar.zza(this.zzcgV, zza2, this.zzchA);
            }
            Object obj = (!zza || zzJb.isEmpty() || (zza2 == null ? 1 : this.zzcgV.zza(zza2, xlVar, this.zzchA)) < 0) ? null : 1;
            if (obj != null) {
                if (wcVar != null) {
                    wcVar.zza(vi.zza(wpVar, zzJb, zzm));
                }
                return xfVar.zzg(wpVar, zzJb);
            }
            if (wcVar != null) {
                wcVar.zza(vi.zzd(wpVar, zzm));
            }
            xfVar = xfVar.zzg(wpVar, xd.zzJb());
            obj = (zza2 == null || !this.zzchz.zza(zza2)) ? null : 1;
            if (obj == null) {
                return xfVar;
            }
            if (wcVar != null) {
                wcVar.zza(vi.zzc(zza2.zzJk(), zza2.zzFn()));
            }
            return xfVar.zzg(zza2.zzJk(), zza2.zzFn());
        } else if (zzJb.isEmpty() || !zza || this.zzcgV.zza(zzJf, xlVar, this.zzchA) < 0) {
            return xfVar;
        } else {
            if (wcVar != null) {
                wcVar.zza(vi.zzd(zzJf.zzJk(), zzJf.zzFn()));
                wcVar.zza(vi.zzc(wpVar, zzJb));
            }
            return xfVar.zzg(wpVar, zzJb).zzg(zzJf.zzJk(), xd.zzJb());
        }
    }

    public final xf zza(xf xfVar, xf xfVar2, wc wcVar) {
        xf zza;
        if (xfVar2.zzFn().zzIQ() || xfVar2.zzFn().isEmpty()) {
            zza = xf.zza(xd.zzJb(), this.zzcgV);
        } else {
            Object zzIF;
            Iterator it;
            int i;
            xf zzk = xfVar2.zzk(xd.zzJb());
            if (this.zzchA) {
                Iterator zzFz = xfVar2.zzFz();
                Object zzIG = this.zzchz.zzIG();
                zzIF = this.zzchz.zzIF();
                it = zzFz;
                i = -1;
            } else {
                Iterator it2 = xfVar2.iterator();
                xl zzIF2 = this.zzchz.zzIF();
                xl zzIG2 = this.zzchz.zzIG();
                xl xlVar = zzIF2;
                i = 1;
                it = it2;
            }
            int i2 = 0;
            zza = zzk;
            Object obj = null;
            while (it.hasNext()) {
                xl xlVar2 = (xl) it.next();
                if (obj == null && this.zzcgV.compare(r5, xlVar2) * i <= 0) {
                    obj = 1;
                }
                Object obj2 = (obj == null || i2 >= this.limit || this.zzcgV.compare(xlVar2, zzIF) * i > 0) ? null : 1;
                if (obj2 != null) {
                    i2++;
                } else {
                    zza = zza.zzg(xlVar2.zzJk(), xd.zzJb());
                }
            }
        }
        return this.zzchz.zzID().zza(xfVar, zza, wcVar);
    }

    public final xf zza(xf xfVar, xm xmVar) {
        return xfVar;
    }
}
