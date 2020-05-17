package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.zzh;
import java.util.ArrayList;
import java.util.List;

final class rc implements pf {
    private /* synthetic */ qr zzccH;
    private /* synthetic */ qu zzcdA;
    private /* synthetic */ List zzcdH;
    private /* synthetic */ qu zzcdI;

    rc(qu quVar, qr qrVar, List list, qu quVar2) {
        this.zzcdA = quVar;
        this.zzccH = qrVar;
        this.zzcdH = list;
        this.zzcdI = quVar2;
    }

    public final void zzaa(String str, String str2) {
        int i = 0;
        DatabaseError zzac = qu.zzab(str, str2);
        this.zzcdA.zza("Transaction", this.zzccH, zzac);
        List arrayList = new ArrayList();
        if (zzac == null) {
            List arrayList2 = new ArrayList();
            for (rv rvVar : this.zzcdH) {
                rvVar.zzceb = rw.zzcem;
                arrayList.addAll(this.zzcdA.zzcdw.zza(rvVar.zzcef, false, false, this.zzcdA.zzcdj));
                arrayList2.add(new rd(this, rvVar, zzh.zza(zzh.zza(this.zzcdI, rvVar.zzbZf), xf.zzj(rvVar.zzcei))));
                this.zzcdA.zze(new to(this.zzcdA, rvVar.zzcea, vt.zzM(rvVar.zzbZf)));
            }
            this.zzcdA.zzb(this.zzcdA.zzcdm.zzK(this.zzccH));
            this.zzcdA.zzHl();
            this.zzcdI.zzT(arrayList);
            while (i < arrayList2.size()) {
                this.zzcdA.zzo((Runnable) arrayList2.get(i));
                i++;
            }
            return;
        }
        if (zzac.getCode() == -1) {
            for (rv rvVar2 : this.zzcdH) {
                if (rvVar2.zzceb == rw.zzcen) {
                    rvVar2.zzceb = rw.zzceo;
                } else {
                    rvVar2.zzceb = rw.zzcek;
                }
            }
        } else {
            for (rv rvVar22 : this.zzcdH) {
                rvVar22.zzceb = rw.zzceo;
                rvVar22.zzcee = zzac;
            }
        }
        this.zzcdA.zzn(this.zzccH);
    }
}
