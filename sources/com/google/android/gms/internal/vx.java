package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class vx {
    private static wg zzchu = new vy();
    private final wf zzcht;

    public vx(wf wfVar) {
        this.zzcht = wfVar;
    }

    private final vw zza(vw vwVar, qr qrVar, pz pzVar, ts tsVar, xm xmVar, boolean z, wc wcVar) {
        if (vwVar.zzIA().zzFn().isEmpty() && !vwVar.zzIA().zzHU()) {
            return vwVar;
        }
        if (!qrVar.isEmpty()) {
            pzVar = pz.zzGI().zzb(qrVar, pzVar);
        }
        xm zzFn = vwVar.zzIA().zzFn();
        Map zzGL = pzVar.zzGL();
        vw vwVar2 = vwVar;
        for (Entry entry : zzGL.entrySet()) {
            wp wpVar = (wp) entry.getKey();
            if (zzFn.zzk(wpVar)) {
                vwVar2 = zza(vwVar2, new qr(wpVar), ((pz) entry.getValue()).zzb(zzFn.zzm(wpVar)), tsVar, xmVar, z, wcVar);
            }
        }
        for (Entry entry2 : zzGL.entrySet()) {
            wpVar = (wp) entry2.getKey();
            Object obj = (vwVar.zzIA().zzf(wpVar) || ((pz) entry2.getValue()).zzGJ() != null) ? null : 1;
            if (!zzFn.zzk(wpVar) && obj == null) {
                vwVar2 = zza(vwVar2, new qr(wpVar), ((pz) entry2.getValue()).zzb(zzFn.zzm(wpVar)), tsVar, xmVar, z, wcVar);
            }
        }
        return vwVar2;
    }

    private final vw zza(vw vwVar, qr qrVar, ts tsVar, wg wgVar, wc wcVar) {
        vg zzIy = vwVar.zzIy();
        if (tsVar.zzu(qrVar) != null) {
            return vwVar;
        }
        xf zza;
        xm zzIB;
        if (qrVar.isEmpty()) {
            if (vwVar.zzIA().zzHV()) {
                zzIB = vwVar.zzIB();
                if (!(zzIB instanceof wr)) {
                    zzIB = xd.zzJb();
                }
                zzIB = tsVar.zzd(zzIB);
            } else {
                zzIB = tsVar.zzc(vwVar.zzIB());
            }
            zza = this.zzcht.zza(vwVar.zzIy().zzHW(), xf.zza(zzIB, this.zzcht.zzIm()), wcVar);
        } else {
            wp zzHc = qrVar.zzHc();
            if (zzHc.zzIN()) {
                zzIB = tsVar.zza(qrVar, zzIy.zzFn(), vwVar.zzIA().zzFn());
                zza = zzIB != null ? this.zzcht.zza(zzIy.zzHW(), zzIB) : zzIy.zzHW();
            } else {
                xm zzl;
                qr zzHd = qrVar.zzHd();
                if (zzIy.zzf(zzHc)) {
                    zzIB = tsVar.zza(qrVar, zzIy.zzFn(), vwVar.zzIA().zzFn());
                    zzl = zzIB != null ? zzIy.zzFn().zzm(zzHc).zzl(zzHd, zzIB) : zzIy.zzFn().zzm(zzHc);
                } else {
                    zzl = tsVar.zza(zzHc, vwVar.zzIA());
                }
                zza = zzl != null ? this.zzcht.zza(zzIy.zzHW(), zzHc, zzl, zzHd, wgVar, wcVar) : zzIy.zzHW();
            }
        }
        boolean z = zzIy.zzHU() || qrVar.isEmpty();
        return vwVar.zza(zza, z, this.zzcht.zzIE());
    }

    private final vw zza(vw vwVar, qr qrVar, xm xmVar, ts tsVar, xm xmVar2, wc wcVar) {
        vg zzIy = vwVar.zzIy();
        wg wbVar = new wb(tsVar, vwVar, xmVar2);
        if (qrVar.isEmpty()) {
            return vwVar.zza(this.zzcht.zza(vwVar.zzIy().zzHW(), xf.zza(xmVar, this.zzcht.zzIm()), wcVar), true, this.zzcht.zzIE());
        }
        wp zzHc = qrVar.zzHc();
        if (zzHc.zzIN()) {
            return vwVar.zza(this.zzcht.zza(vwVar.zzIy().zzHW(), xmVar), zzIy.zzHU(), zzIy.zzHV());
        }
        xm xmVar3;
        qr zzHd = qrVar.zzHd();
        xm zzm = zzIy.zzFn().zzm(zzHc);
        if (zzHd.isEmpty()) {
            xmVar3 = xmVar;
        } else {
            xmVar3 = wbVar.zzh(zzHc);
            if (xmVar3 == null) {
                xmVar3 = xd.zzJb();
            } else if (!(zzHd.zzHf().zzIN() && xmVar3.zzN(zzHd.zzHe()).isEmpty())) {
                xmVar3 = xmVar3.zzl(zzHd, xmVar);
            }
        }
        return !zzm.equals(xmVar3) ? vwVar.zza(this.zzcht.zza(zzIy.zzHW(), zzHc, xmVar3, zzHd, wbVar, wcVar), zzIy.zzHU(), this.zzcht.zzIE()) : vwVar;
    }

    private final vw zza(vw vwVar, qr qrVar, xm xmVar, ts tsVar, xm xmVar2, boolean z, wc wcVar) {
        xf zza;
        vg zzIA = vwVar.zzIA();
        wf zzID = z ? this.zzcht : this.zzcht.zzID();
        if (qrVar.isEmpty()) {
            zza = zzID.zza(zzIA.zzHW(), xf.zza(xmVar, zzID.zzIm()), null);
        } else if (!zzID.zzIE() || zzIA.zzHV()) {
            wp zzHc = qrVar.zzHc();
            if (!zzIA.zzL(qrVar) && qrVar.size() > 1) {
                return vwVar;
            }
            qr zzHd = qrVar.zzHd();
            xm zzl = zzIA.zzFn().zzm(zzHc).zzl(zzHd, xmVar);
            zza = zzHc.zzIN() ? zzID.zza(zzIA.zzHW(), zzl) : zzID.zza(zzIA.zzHW(), zzHc, zzl, zzHd, zzchu, null);
        } else {
            wp zzHc2 = qrVar.zzHc();
            zza = zzID.zza(zzIA.zzHW(), zzIA.zzHW().zzg(zzHc2, zzIA.zzFn().zzm(zzHc2).zzl(qrVar.zzHd(), xmVar)), null);
        }
        boolean z2 = zzIA.zzHU() || qrVar.isEmpty();
        vw zzb = vwVar.zzb(zza, z2, zzID.zzIE());
        return zza(zzb, qrVar, tsVar, new wb(tsVar, zzb, xmVar2), wcVar);
    }

    private static boolean zza(vw vwVar, wp wpVar) {
        return vwVar.zzIy().zzf(wpVar);
    }

    public final wa zza(vw vwVar, tx txVar, ts tsVar, xm xmVar) {
        vw zza;
        wc wcVar = new wc();
        boolean z;
        vg zzIA;
        qr zzh;
        boolean z2;
        switch (vz.zzchv[txVar.zzHF().ordinal()]) {
            case 1:
                ub ubVar = (ub) txVar;
                if (!ubVar.zzHE().zzHG()) {
                    z = ubVar.zzHE().zzHH() || (vwVar.zzIA().zzHV() && !ubVar.zzFq().isEmpty());
                    zza = zza(vwVar, ubVar.zzFq(), ubVar.zzHJ(), tsVar, xmVar, z, wcVar);
                    break;
                }
                zza = zza(vwVar, ubVar.zzFq(), ubVar.zzHJ(), tsVar, xmVar, wcVar);
                break;
                break;
            case 2:
                tw twVar = (tw) txVar;
                if (!twVar.zzHE().zzHG()) {
                    z = twVar.zzHE().zzHH() || vwVar.zzIA().zzHV();
                    zza = zza(vwVar, twVar.zzFq(), twVar.zzHD(), tsVar, xmVar, z, wcVar);
                    break;
                }
                Entry entry;
                qr zzh2;
                qr zzFq = twVar.zzFq();
                pz zzHD = twVar.zzHD();
                Iterator it = zzHD.iterator();
                vw vwVar2 = vwVar;
                while (it.hasNext()) {
                    entry = (Entry) it.next();
                    zzh2 = zzFq.zzh((qr) entry.getKey());
                    if (zza(vwVar, zzh2.zzHc())) {
                        vwVar2 = zza(vwVar2, zzh2, (xm) entry.getValue(), tsVar, xmVar, wcVar);
                    }
                }
                Iterator it2 = zzHD.iterator();
                while (it2.hasNext()) {
                    entry = (Entry) it2.next();
                    zzh2 = zzFq.zzh((qr) entry.getKey());
                    if (!zza(vwVar, zzh2.zzHc())) {
                        vwVar2 = zza(vwVar2, zzh2, (xm) entry.getValue(), tsVar, xmVar, wcVar);
                    }
                }
                zza = vwVar2;
                break;
                break;
            case 3:
                tu tuVar = (tu) txVar;
                if (!tuVar.zzHC()) {
                    qr zzFq2 = tuVar.zzFq();
                    uv zzHB = tuVar.zzHB();
                    if (tsVar.zzu(zzFq2) == null) {
                        z = vwVar.zzIA().zzHV();
                        zzIA = vwVar.zzIA();
                        pz zzGI;
                        if (zzHB.getValue() != null) {
                            if ((!zzFq2.isEmpty() || !zzIA.zzHU()) && !zzIA.zzL(zzFq2)) {
                                if (!zzFq2.isEmpty()) {
                                    zza = vwVar;
                                    break;
                                }
                                zzGI = pz.zzGI();
                                for (xl xlVar : zzIA.zzFn()) {
                                    zzGI = zzGI.zza(xlVar.zzJk(), xlVar.zzFn());
                                }
                                zza = zza(vwVar, zzFq2, zzGI, tsVar, xmVar, z, wcVar);
                                break;
                            }
                            zza = zza(vwVar, zzFq2, zzIA.zzFn().zzN(zzFq2), tsVar, xmVar, z, wcVar);
                            break;
                        }
                        zzGI = pz.zzGI();
                        Iterator it3 = zzHB.iterator();
                        while (it3.hasNext()) {
                            qr qrVar = (qr) ((Entry) it3.next()).getKey();
                            zzh = zzFq2.zzh(qrVar);
                            if (zzIA.zzL(zzh)) {
                                zzGI = zzGI.zze(qrVar, zzIA.zzFn().zzN(zzh));
                            }
                        }
                        zza = zza(vwVar, zzFq2, zzGI, tsVar, xmVar, z, wcVar);
                        break;
                    }
                    zza = vwVar;
                    break;
                }
                qr zzFq3 = tuVar.zzFq();
                if (tsVar.zzu(zzFq3) != null) {
                    zza = vwVar;
                    break;
                }
                wg wbVar = new wb(tsVar, vwVar, xmVar);
                xf zzHW = vwVar.zzIy().zzHW();
                if (zzFq3.isEmpty() || zzFq3.zzHc().zzIN()) {
                    zzHW = this.zzcht.zza(zzHW, xf.zza(vwVar.zzIA().zzHU() ? tsVar.zzc(vwVar.zzIB()) : tsVar.zzd(vwVar.zzIA().zzFn()), this.zzcht.zzIm()), wcVar);
                } else {
                    wp zzHc = zzFq3.zzHc();
                    xm zza2 = tsVar.zza(zzHc, vwVar.zzIA());
                    if (zza2 == null && vwVar.zzIA().zzf(zzHc)) {
                        zza2 = zzHW.zzFn().zzm(zzHc);
                    }
                    if (zza2 != null) {
                        zzHW = this.zzcht.zza(zzHW, zzHc, zza2, zzFq3.zzHd(), wbVar, wcVar);
                    } else if (zza2 == null && vwVar.zzIy().zzFn().zzk(zzHc)) {
                        zzHW = this.zzcht.zza(zzHW, zzHc, xd.zzJb(), zzFq3.zzHd(), wbVar, wcVar);
                    }
                    if (zzHW.zzFn().isEmpty() && vwVar.zzIA().zzHU()) {
                        xm zzc = tsVar.zzc(vwVar.zzIB());
                        if (zzc.zzIQ()) {
                            zzHW = this.zzcht.zza(zzHW, xf.zza(zzc, this.zzcht.zzIm()), wcVar);
                        }
                    }
                }
                if (!vwVar.zzIA().zzHU()) {
                    if (tsVar.zzu(qr.zzGZ()) == null) {
                        z2 = false;
                        zza = vwVar.zza(zzHW, z2, this.zzcht.zzIE());
                        break;
                    }
                }
                z2 = true;
                zza = vwVar.zza(zzHW, z2, this.zzcht.zzIE());
                break;
            case 4:
                zzh = txVar.zzFq();
                zzIA = vwVar.zzIA();
                xf zzHW2 = zzIA.zzHW();
                z2 = zzIA.zzHU() || zzh.isEmpty();
                zza = zza(vwVar.zzb(zzHW2, z2, zzIA.zzHV()), zzh, tsVar, zzchu, wcVar);
                break;
            default:
                String valueOf = String.valueOf(txVar.zzHF());
                throw new AssertionError(new StringBuilder(String.valueOf(valueOf).length() + 19).append("Unknown operation: ").append(valueOf).toString());
        }
        List arrayList = new ArrayList(wcVar.zzIC());
        vg zzIy = zza.zzIy();
        if (zzIy.zzHU()) {
            Object obj = (zzIy.zzFn().zzIQ() || zzIy.zzFn().isEmpty()) ? 1 : null;
            if (!(arrayList.isEmpty() && vwVar.zzIy().zzHU() && ((obj == null || zzIy.zzFn().equals(vwVar.zzIz())) && zzIy.zzFn().zzIR().equals(vwVar.zzIz().zzIR())))) {
                arrayList.add(vi.zza(zzIy.zzHW()));
            }
        }
        return new wa(zza, arrayList);
    }
}
