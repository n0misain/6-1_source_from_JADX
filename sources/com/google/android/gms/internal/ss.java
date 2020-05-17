package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

final class ss implements Callable<List<vk>> {
    private /* synthetic */ vt zzcdP;
    private /* synthetic */ so zzceR;
    private /* synthetic */ qi zzceU;
    private /* synthetic */ DatabaseError zzceV;

    ss(so soVar, vt vtVar, qi qiVar, DatabaseError databaseError) {
        this.zzceR = soVar;
        this.zzcdP = vtVar;
        this.zzceU = qiVar;
        this.zzceV = databaseError;
    }

    public final /* synthetic */ Object call() throws Exception {
        qr zzFq = this.zzcdP.zzFq();
        sn snVar = (sn) this.zzceR.zzceG.zzJ(zzFq);
        Object arrayList = new ArrayList();
        if (snVar != null && (this.zzcdP.isDefault() || snVar.zzc(this.zzcdP))) {
            Object obj;
            za zza = snVar.zza(this.zzcdP, this.zzceU, this.zzceV);
            if (snVar.isEmpty()) {
                this.zzceR.zzceG = this.zzceR.zzceG.zzI(zzFq);
            }
            List<vt> list = (List) zza.getFirst();
            arrayList = (List) zza.zzJG();
            Object obj2 = null;
            for (vt vtVar : list) {
                this.zzceR.zzceF.zzh(this.zzcdP);
                obj = (obj2 != null || vtVar.zzIq()) ? 1 : null;
                obj2 = obj;
            }
            uv zzd = this.zzceR.zzceG;
            obj = (zzd.getValue() == null || !((sn) zzd.getValue()).zzHo()) ? null : 1;
            Iterator it = zzFq.iterator();
            uv uvVar = zzd;
            Object obj3 = obj;
            while (it.hasNext()) {
                uvVar = uvVar.zze((wp) it.next());
                obj = (obj3 != null || (uvVar.getValue() != null && ((sn) uvVar.getValue()).zzHo())) ? 1 : null;
                if (obj != null || uvVar.isEmpty()) {
                    obj3 = obj;
                    break;
                }
                obj3 = obj;
            }
            if (obj2 != null && obj3 == null) {
                uv zzH = this.zzceR.zzceG.zzH(zzFq);
                if (!zzH.isEmpty()) {
                    for (vu vuVar : this.zzceR.zza(zzH)) {
                        Object tfVar = new tf(this.zzceR, vuVar);
                        this.zzceR.zzceL.zza(so.zzd(vuVar.zzIv()), tfVar.zzcff, tfVar, tfVar);
                    }
                }
            }
            if (obj3 == null && !list.isEmpty() && this.zzceV == null) {
                if (obj2 != null) {
                    this.zzceR.zzceL.zza(so.zzd(this.zzcdP), null);
                } else {
                    for (vt vtVar2 : list) {
                        this.zzceR.zzceL.zza(so.zzd(vtVar2), this.zzceR.zze(vtVar2));
                    }
                }
            }
            this.zzceR.zzU(list);
        }
        return arrayList;
    }
}
