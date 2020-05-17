package com.google.android.gms.internal;

import java.util.Iterator;

public final class wh implements wf {
    private final xe zzcgV;
    private final wd zzchB;
    private final xl zzchC;
    private final xl zzchD;

    public wh(vq vqVar) {
        xl zzf;
        this.zzchB = new wd(vqVar.zzIm());
        this.zzcgV = vqVar.zzIm();
        if (vqVar.zzIe()) {
            zzf = vqVar.zzIm().zzf(vqVar.zzIg(), vqVar.zzIf());
        } else {
            vqVar.zzIm();
            zzf = xl.zzJi();
        }
        this.zzchC = zzf;
        if (vqVar.zzIh()) {
            zzf = vqVar.zzIm().zzf(vqVar.zzIj(), vqVar.zzIi());
        } else {
            zzf = vqVar.zzIm().zzJc();
        }
        this.zzchD = zzf;
    }

    public final wf zzID() {
        return this.zzchB;
    }

    public final boolean zzIE() {
        return true;
    }

    public final xl zzIF() {
        return this.zzchC;
    }

    public final xl zzIG() {
        return this.zzchD;
    }

    public final xe zzIm() {
        return this.zzcgV;
    }

    public final xf zza(xf xfVar, wp wpVar, xm xmVar, qr qrVar, wg wgVar, wc wcVar) {
        return this.zzchB.zza(xfVar, wpVar, !zza(new xl(wpVar, xmVar)) ? xd.zzJb() : xmVar, qrVar, wgVar, wcVar);
    }

    public final xf zza(xf xfVar, xf xfVar2, wc wcVar) {
        xf zza;
        if (xfVar2.zzFn().zzIQ()) {
            zza = xf.zza(xd.zzJb(), this.zzcgV);
        } else {
            xf zzk = xfVar2.zzk(xd.zzJb());
            Iterator it = xfVar2.iterator();
            zza = zzk;
            while (it.hasNext()) {
                xl xlVar = (xl) it.next();
                zza = !zza(xlVar) ? zza.zzg(xlVar.zzJk(), xd.zzJb()) : zza;
            }
        }
        return this.zzchB.zza(xfVar, zza, wcVar);
    }

    public final xf zza(xf xfVar, xm xmVar) {
        return xfVar;
    }

    public final boolean zza(xl xlVar) {
        return this.zzcgV.compare(this.zzchC, xlVar) <= 0 && this.zzcgV.compare(xlVar, this.zzchD) <= 0;
    }
}
