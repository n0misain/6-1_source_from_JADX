package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public final class tt implements qj {
    private static tt zzcfC = new tt();
    private HashMap<qi, List<qi>> zzcfB = new HashMap();

    private tt() {
    }

    public static tt zzHA() {
        return zzcfC;
    }

    public final void zzd(qi qiVar) {
        int i = 0;
        synchronized (this.zzcfB) {
            List list = (List) this.zzcfB.get(qiVar);
            if (list != null) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (list.get(i2) == qiVar) {
                        list.remove(i2);
                        break;
                    }
                }
                if (list.isEmpty()) {
                    this.zzcfB.remove(qiVar);
                }
            }
            if (!qiVar.zzGH().isDefault()) {
                qi zza = qiVar.zza(vt.zzM(qiVar.zzGH().zzFq()));
                list = (List) this.zzcfB.get(zza);
                if (list != null) {
                    while (i < list.size()) {
                        if (list.get(i) == qiVar) {
                            list.remove(i);
                            break;
                        }
                        i++;
                    }
                    if (list.isEmpty()) {
                        this.zzcfB.remove(zza);
                    }
                }
            }
        }
    }

    public final void zzi(qi qiVar) {
        synchronized (this.zzcfB) {
            List list = (List) this.zzcfB.get(qiVar);
            if (list == null) {
                list = new ArrayList();
                this.zzcfB.put(qiVar, list);
            }
            list.add(qiVar);
            if (!qiVar.zzGH().isDefault()) {
                qi zza = qiVar.zza(vt.zzM(qiVar.zzGH().zzFq()));
                list = (List) this.zzcfB.get(zza);
                if (list == null) {
                    list = new ArrayList();
                    this.zzcfB.put(zza, list);
                }
                list.add(qiVar);
            }
            qiVar.zzaE(true);
            qiVar.zza((qj) this);
        }
    }

    public final void zzj(qi qiVar) {
        synchronized (this.zzcfB) {
            List list = (List) this.zzcfB.get(qiVar);
            if (!(list == null || list.isEmpty())) {
                if (qiVar.zzGH().isDefault()) {
                    HashSet hashSet = new HashSet();
                    for (int size = list.size() - 1; size >= 0; size--) {
                        qi qiVar2 = (qi) list.get(size);
                        if (!hashSet.contains(qiVar2.zzGH())) {
                            hashSet.add(qiVar2.zzGH());
                            qiVar2.zzGV();
                        }
                    }
                } else {
                    ((qi) list.get(0)).zzGV();
                }
            }
        }
    }
}
