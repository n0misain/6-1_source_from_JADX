package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public final class xt {
    private final qr zzciC;
    private final qr zzciD;
    private final xm zzciE;

    public xt(pe peVar) {
        qr qrVar = null;
        List zzGs = peVar.zzGs();
        this.zzciC = zzGs != null ? new qr(zzGs) : null;
        List zzGt = peVar.zzGt();
        if (zzGt != null) {
            qrVar = new qr(zzGt);
        }
        this.zzciD = qrVar;
        this.zzciE = xp.zza(peVar.zzGu(), xd.zzJb());
    }

    private final xm zzb(qr qrVar, xm xmVar, xm xmVar2) {
        int i = 1;
        int i2 = 0;
        int zzj = this.zzciC == null ? 1 : qrVar.zzj(this.zzciC);
        int zzj2 = this.zzciD == null ? -1 : qrVar.zzj(this.zzciD);
        int i3 = (this.zzciC == null || !qrVar.zzi(this.zzciC)) ? 0 : 1;
        if (this.zzciD == null || !qrVar.zzi(this.zzciD)) {
            i = 0;
        }
        if (zzj > 0 && zzj2 < 0 && r1 == 0) {
            return xmVar2;
        }
        if (zzj > 0 && r1 != 0 && xmVar2.zzIQ()) {
            return xmVar2;
        }
        if (zzj > 0 && zzj2 == 0) {
            return xmVar.zzIQ() ? xd.zzJb() : xmVar;
        } else {
            if (i3 == 0 && r1 == 0) {
                return xmVar;
            }
            Collection hashSet = new HashSet();
            for (xl zzJk : xmVar) {
                hashSet.add(zzJk.zzJk());
            }
            for (xl zzJk2 : xmVar2) {
                hashSet.add(zzJk2.zzJk());
            }
            List arrayList = new ArrayList(hashSet.size() + 1);
            arrayList.addAll(hashSet);
            if (!(xmVar2.zzIR().isEmpty() && xmVar.zzIR().isEmpty())) {
                arrayList.add(wp.zzIL());
            }
            ArrayList arrayList2 = (ArrayList) arrayList;
            i3 = arrayList2.size();
            xm xmVar3 = xmVar;
            while (i2 < i3) {
                Object obj = arrayList2.get(i2);
                i2++;
                wp wpVar = (wp) obj;
                xm zzm = xmVar.zzm(wpVar);
                xm zzb = zzb(qrVar.zza(wpVar), xmVar.zzm(wpVar), xmVar2.zzm(wpVar));
                xmVar3 = zzb != zzm ? xmVar3.zze(wpVar, zzb) : xmVar3;
            }
            return xmVar3;
        }
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzciC);
        String valueOf2 = String.valueOf(this.zzciD);
        String valueOf3 = String.valueOf(this.zzciE);
        return new StringBuilder(((String.valueOf(valueOf).length() + 55) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()).append("RangeMerge{optExclusiveStart=").append(valueOf).append(", optInclusiveEnd=").append(valueOf2).append(", snap=").append(valueOf3).append("}").toString();
    }

    public final xm zzm(xm xmVar) {
        return zzb(qr.zzGZ(), xmVar, this.zzciE);
    }
}
