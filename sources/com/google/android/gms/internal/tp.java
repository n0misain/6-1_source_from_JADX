package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public final class tp {
    private static final uz<tm> zzcfv = new tr();
    private pz zzcfs = pz.zzGI();
    private List<tm> zzcft = new ArrayList();
    private Long zzcfu = Long.valueOf(-1);

    private static pz zza(List<tm> list, uz<tm> uzVar, qr qrVar) {
        pz zzGI = pz.zzGI();
        pz pzVar = zzGI;
        for (tm tmVar : list) {
            if (uzVar.zzaj(tmVar)) {
                qr zzFq = tmVar.zzFq();
                if (tmVar.zzHw()) {
                    if (qrVar.zzi(zzFq)) {
                        pzVar = pzVar.zze(qr.zza(qrVar, zzFq), tmVar.zzHu());
                    } else if (zzFq.zzi(qrVar)) {
                        pzVar = pzVar.zze(qr.zzGZ(), tmVar.zzHu().zzN(qr.zza(zzFq, qrVar)));
                    }
                } else if (qrVar.zzi(zzFq)) {
                    pzVar = pzVar.zzb(qr.zza(qrVar, zzFq), tmVar.zzHv());
                } else if (zzFq.zzi(qrVar)) {
                    zzFq = qr.zza(zzFq, qrVar);
                    if (zzFq.isEmpty()) {
                        pzVar = pzVar.zzb(qr.zzGZ(), tmVar.zzHv());
                    } else {
                        xm zzf = tmVar.zzHv().zzf(zzFq);
                        if (zzf != null) {
                            zzGI = pzVar.zze(qr.zzGZ(), zzf);
                            pzVar = zzGI;
                        }
                    }
                }
            }
            zzGI = pzVar;
            pzVar = zzGI;
        }
        return pzVar;
    }

    public final List<tm> zzHz() {
        List arrayList = new ArrayList(this.zzcft);
        this.zzcfs = pz.zzGI();
        this.zzcft = new ArrayList();
        return arrayList;
    }

    public final xl zza(qr qrVar, xm xmVar, xl xlVar, boolean z, xe xeVar) {
        xl xlVar2 = null;
        pz zzg = this.zzcfs.zzg(qrVar);
        xm zzf = zzg.zzf(qr.zzGZ());
        if (zzf == null) {
            if (xmVar != null) {
                zzf = zzg.zzb(xmVar);
            }
            return xlVar2;
        }
        for (xl xlVar3 : r0) {
            xl xlVar32;
            if (xeVar.zza(xlVar32, xlVar, z) <= 0 || (xlVar2 != null && xeVar.zza(xlVar32, xlVar2, z) >= 0)) {
                xlVar32 = xlVar2;
            }
            xlVar2 = xlVar32;
        }
        return xlVar2;
    }

    public final xm zza(qr qrVar, qr qrVar2, xm xmVar, xm xmVar2) {
        qr zzh = qrVar.zzh(qrVar2);
        if (this.zzcfs.zze(zzh)) {
            return null;
        }
        pz zzg = this.zzcfs.zzg(zzh);
        return zzg.isEmpty() ? xmVar2.zzN(qrVar2) : zzg.zzb(xmVar2.zzN(qrVar2));
    }

    public final xm zza(qr qrVar, wp wpVar, vg vgVar) {
        qr zza = qrVar.zza(wpVar);
        xm zzf = this.zzcfs.zzf(zza);
        return zzf != null ? zzf : vgVar.zzf(wpVar) ? this.zzcfs.zzg(zza).zzb(vgVar.zzFn().zzm(wpVar)) : null;
    }

    public final xm zza(qr qrVar, xm xmVar, List<Long> list, boolean z) {
        pz zzg;
        if (!list.isEmpty() || z) {
            zzg = this.zzcfs.zzg(qrVar);
            if (!z && zzg.isEmpty()) {
                return xmVar;
            }
            if (!z && xmVar == null && !zzg.zze(qr.zzGZ())) {
                return null;
            }
            zzg = zza(this.zzcft, new tq(this, z, list, qrVar), qrVar);
            if (xmVar == null) {
                xmVar = xd.zzJb();
            }
            return zzg.zzb(xmVar);
        }
        xm zzf = this.zzcfs.zzf(qrVar);
        if (zzf != null) {
            return zzf;
        }
        zzg = this.zzcfs.zzg(qrVar);
        if (zzg.isEmpty()) {
            return xmVar;
        }
        if (xmVar == null && !zzg.zze(qr.zzGZ())) {
            return null;
        }
        if (xmVar == null) {
            xmVar = xd.zzJb();
        }
        return zzg.zzb(xmVar);
    }

    public final void zza(qr qrVar, pz pzVar, Long l) {
        this.zzcft.add(new tm(l.longValue(), qrVar, pzVar));
        this.zzcfs = this.zzcfs.zzb(qrVar, pzVar);
        this.zzcfu = l;
    }

    public final void zza(qr qrVar, xm xmVar, Long l, boolean z) {
        this.zzcft.add(new tm(l.longValue(), qrVar, xmVar, z));
        if (z) {
            this.zzcfs = this.zzcfs.zze(qrVar, xmVar);
        }
        this.zzcfu = l;
    }

    public final tm zzav(long j) {
        for (tm tmVar : this.zzcft) {
            if (tmVar.zzHt() == j) {
                return tmVar;
            }
        }
        return null;
    }

    public final boolean zzaw(long j) {
        tm tmVar;
        tm tmVar2;
        int i = 0;
        for (tm tmVar3 : this.zzcft) {
            if (tmVar3.zzHt() == j) {
                tmVar2 = tmVar3;
                break;
            }
            i++;
        }
        tmVar2 = null;
        this.zzcft.remove(tmVar2);
        boolean isVisible = tmVar2.isVisible();
        int size = this.zzcft.size() - 1;
        boolean z = false;
        while (isVisible && size >= 0) {
            boolean zzi;
            boolean z2;
            tmVar3 = (tm) this.zzcft.get(size);
            if (tmVar3.isVisible()) {
                if (size >= i) {
                    qr zzFq = tmVar2.zzFq();
                    if (tmVar3.zzHw()) {
                        zzi = tmVar3.zzFq().zzi(zzFq);
                    } else {
                        Iterator it = tmVar3.zzHv().iterator();
                        while (it.hasNext()) {
                            if (tmVar3.zzFq().zzh((qr) ((Entry) it.next()).getKey()).zzi(zzFq)) {
                                zzi = true;
                                break;
                            }
                        }
                        zzi = false;
                    }
                    if (zzi) {
                        z2 = z;
                        zzi = false;
                        size--;
                        isVisible = zzi;
                        z = z2;
                    }
                }
                if (tmVar2.zzFq().zzi(tmVar3.zzFq())) {
                    z2 = true;
                    zzi = isVisible;
                    size--;
                    isVisible = zzi;
                    z = z2;
                }
            }
            z2 = z;
            zzi = isVisible;
            size--;
            isVisible = zzi;
            z = z2;
        }
        if (!isVisible) {
            return false;
        }
        if (z) {
            this.zzcfs = zza(this.zzcft, zzcfv, qr.zzGZ());
            if (this.zzcft.size() > 0) {
                this.zzcfu = Long.valueOf(((tm) this.zzcft.get(this.zzcft.size() - 1)).zzHt());
                return true;
            }
            this.zzcfu = Long.valueOf(-1);
            return true;
        } else if (tmVar2.zzHw()) {
            this.zzcfs = this.zzcfs.zzd(tmVar2.zzFq());
            return true;
        } else {
            Iterator it2 = tmVar2.zzHv().iterator();
            while (it2.hasNext()) {
                this.zzcfs = this.zzcfs.zzd(tmVar2.zzFq().zzh((qr) ((Entry) it2.next()).getKey()));
            }
            return true;
        }
    }

    public final xm zzj(qr qrVar, xm xmVar) {
        xd zzJb = xd.zzJb();
        xm<xl> zzf = this.zzcfs.zzf(qrVar);
        xm xmVar2;
        if (zzf == null) {
            pz zzg = this.zzcfs.zzg(qrVar);
            xmVar2 = zzJb;
            for (xl xlVar : xmVar) {
                xmVar2 = xmVar2.zze(xlVar.zzJk(), zzg.zzg(new qr(xlVar.zzJk())).zzb(xlVar.zzFn()));
            }
            for (xl xlVar2 : zzg.zzGK()) {
                xmVar2 = xmVar2.zze(xlVar2.zzJk(), xlVar2.zzFn());
            }
            return xmVar2;
        } else if (zzf.zzIQ()) {
            return zzJb;
        } else {
            xmVar2 = zzJb;
            for (xl xlVar22 : zzf) {
                xmVar2 = xmVar2.zze(xlVar22.zzJk(), xlVar22.zzFn());
            }
            return xmVar2;
        }
    }

    public final ts zzt(qr qrVar) {
        return new ts(qrVar, this);
    }

    public final xm zzu(qr qrVar) {
        return this.zzcfs.zzf(qrVar);
    }
}
