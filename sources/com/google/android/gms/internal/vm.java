package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class vm {
    private final vt zzcgU;
    private final xe zzcgV;

    public vm(vt vtVar) {
        this.zzcgU = vtVar;
        this.zzcgV = vtVar.zzIm();
    }

    private final void zza(List<vj> list, vl vlVar, List<vi> list2, List<qi> list3, xf xfVar) {
        List arrayList = new ArrayList();
        for (vi viVar : list2) {
            if (viVar.zzHZ().equals(vlVar)) {
                arrayList.add(viVar);
            }
        }
        Collections.sort(arrayList, new vn(this));
        ArrayList arrayList2 = (ArrayList) arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            vi viVar2 = (vi) arrayList2.get(i);
            for (qi qiVar : list3) {
                if (qiVar.zza(vlVar)) {
                    vi zzg = (viVar2.zzHZ().equals(vl.VALUE) || viVar2.zzHZ().equals(vl.CHILD_REMOVED)) ? viVar2 : viVar2.zzg(xfVar.zza(viVar2.zzHY(), viVar2.zzHW().zzFn(), this.zzcgV));
                    list.add(qiVar.zza(zzg, this.zzcgU));
                }
            }
            i = i2;
        }
    }

    public final List<vj> zza(List<vi> list, xf xfVar, List<qi> list2) {
        List<vj> arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        for (vi viVar : list) {
            if (viVar.zzHZ().equals(vl.CHILD_CHANGED)) {
                if ((this.zzcgV.compare(new xl(wp.zzIJ(), viVar.zzIb().zzFn()), new xl(wp.zzIJ(), viVar.zzHW().zzFn())) != 0 ? 1 : null) != null) {
                    arrayList2.add(vi.zzc(viVar.zzHY(), viVar.zzHW()));
                }
            }
        }
        zza(arrayList, vl.CHILD_REMOVED, list, list2, xfVar);
        zza(arrayList, vl.CHILD_ADDED, list, list2, xfVar);
        zza(arrayList, vl.CHILD_MOVED, arrayList2, list2, xfVar);
        zza(arrayList, vl.CHILD_CHANGED, list, list2, xfVar);
        zza(arrayList, vl.VALUE, list, list2, xfVar);
        return arrayList;
    }
}
