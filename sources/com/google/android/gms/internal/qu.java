package com.google.android.gms.internal;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.Transaction.Handler;
import com.google.firebase.database.Transaction.Result;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.zzh;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class qu implements oo {
    private final rx zzbYW;
    private final on zzccd;
    private final yz zzcdj = new yz(new yt(), 0);
    private sh zzcdk;
    private si zzcdl;
    private vb<List<rv>> zzcdm;
    private boolean zzcdn = false;
    private final vo zzcdo;
    private final qd zzcdp;
    private final wl zzcdq;
    private final wl zzcdr;
    private final wl zzcds;
    private long zzcdt = 0;
    private long zzcdu = 1;
    private so zzcdv;
    private so zzcdw;
    private FirebaseDatabase zzcdx;
    private boolean zzcdy = false;
    private long zzcdz = 0;

    qu(rx rxVar, qd qdVar, FirebaseDatabase firebaseDatabase) {
        this.zzbYW = rxVar;
        this.zzcdp = qdVar;
        this.zzcdx = firebaseDatabase;
        this.zzcdq = this.zzcdp.zzgP("RepoOperation");
        this.zzcdr = this.zzcdp.zzgP("Transaction");
        this.zzcds = this.zzcdp.zzgP("DataOperation");
        this.zzcdo = new vo(this.zzcdp);
        this.zzccd = qdVar.zza(new ol(rxVar.host, rxVar.zzbxU, rxVar.secure), this);
        zzq(new qv(this));
    }

    private final void zzHg() {
        this.zzcdp.zzccP.zza(new rh(this));
        this.zzccd.initialize();
        uh zzgQ = this.zzcdp.zzgQ(this.zzbYW.host);
        this.zzcdk = new sh();
        this.zzcdl = new si();
        this.zzcdm = new vb();
        this.zzcdv = new so(this.zzcdp, new ug(), new rm(this));
        this.zzcdw = new so(this.zzcdp, zzgQ, new ro(this));
        List<tm> zzFs = zzgQ.zzFs();
        Map zza = se.zza(this.zzcdj);
        long j = Long.MIN_VALUE;
        for (tm tmVar : zzFs) {
            pf rqVar = new rq(this, tmVar);
            if (j >= tmVar.zzHt()) {
                throw new IllegalStateException("Write ids were not in order.");
            }
            long zzHt = tmVar.zzHt();
            this.zzcdu = tmVar.zzHt() + 1;
            if (tmVar.zzHw()) {
                if (this.zzcdq.zzIH()) {
                    this.zzcdq.zzb("Restoring overwrite with id " + tmVar.zzHt(), null, new Object[0]);
                }
                this.zzccd.zza(tmVar.zzFq().zzHb(), tmVar.zzHu().getValue(true), rqVar);
                this.zzcdw.zza(tmVar.zzFq(), tmVar.zzHu(), se.zza(tmVar.zzHu(), zza), tmVar.zzHt(), true, false);
                j = zzHt;
            } else {
                if (this.zzcdq.zzIH()) {
                    this.zzcdq.zzb("Restoring merge with id " + tmVar.zzHt(), null, new Object[0]);
                }
                this.zzccd.zza(tmVar.zzFq().zzHb(), tmVar.zzHv().zzaD(true), rqVar);
                this.zzcdw.zza(tmVar.zzFq(), tmVar.zzHv(), se.zza(tmVar.zzHv(), zza), tmVar.zzHt(), false);
                j = zzHt;
            }
        }
        zza(qc.zzccM, Boolean.valueOf(false));
        zza(qc.zzccN, Boolean.valueOf(false));
    }

    private final long zzHk() {
        long j = this.zzcdu;
        this.zzcdu = 1 + j;
        return j;
    }

    private final void zzHl() {
        vb vbVar = this.zzcdm;
        zzb(vbVar);
        zza(vbVar);
    }

    private final void zzT(List<? extends vk> list) {
        if (!list.isEmpty()) {
            this.zzcdo.zzV(list);
        }
    }

    private final xm zza(qr qrVar, List<Long> list) {
        xm zzc = this.zzcdw.zzc(qrVar, list);
        return zzc == null ? xd.zzJb() : zzc;
    }

    private final void zza(long j, qr qrVar, DatabaseError databaseError) {
        if (databaseError == null || databaseError.getCode() != -25) {
            List zza = this.zzcdw.zza(j, !(databaseError == null), true, this.zzcdj);
            if (zza.size() > 0) {
                zzn(qrVar);
            }
            zzT(zza);
        }
    }

    private final void zza(vb<List<rv>> vbVar) {
        if (((List) vbVar.getValue()) != null) {
            Boolean valueOf;
            List<rv> zzc = zzc((vb) vbVar);
            Boolean valueOf2 = Boolean.valueOf(true);
            for (rv zzc2 : zzc) {
                if (zzc2.zzceb != rw.zzcek) {
                    valueOf = Boolean.valueOf(false);
                    break;
                }
            }
            valueOf = valueOf2;
            if (valueOf.booleanValue()) {
                qr zzFq = vbVar.zzFq();
                List arrayList = new ArrayList();
                for (rv zzc22 : zzc) {
                    arrayList.add(Long.valueOf(zzc22.zzcef));
                }
                xm zza = zza(zzFq, arrayList);
                String zzIP = zza.zzIP();
                xm xmVar = zza;
                for (rv zzc222 : zzc) {
                    zzc222.zzceb = rw.zzcel;
                    zzc222.retryCount = zzc222.retryCount + 1;
                    xmVar = xmVar.zzl(qr.zza(zzFq, zzc222.zzbZf), zzc222.zzceh);
                }
                this.zzccd.zza(zzFq.zzHb(), xmVar.getValue(true), zzIP, new rc(this, zzFq, zzc, this));
            }
        } else if (vbVar.hasChildren()) {
            vbVar.zza(new rb(this));
        }
    }

    private final void zza(vb<List<rv>> vbVar, int i) {
        List list = (List) vbVar.getValue();
        List arrayList = new ArrayList();
        if (list != null) {
            DatabaseError zzgA;
            int i2;
            List arrayList2 = new ArrayList();
            if (i == -9) {
                zzgA = DatabaseError.zzgA("overriddenBySet");
            } else {
                zd.zzb(i == -25, "Unknown transaction abort reason: " + i);
                zzgA = DatabaseError.zzbU(-25);
            }
            int i3 = 0;
            int i4 = -1;
            while (i3 < list.size()) {
                rv rvVar = (rv) list.get(i3);
                if (rvVar.zzceb != rw.zzcen) {
                    if (rvVar.zzceb == rw.zzcel) {
                        rvVar.zzceb = rw.zzcen;
                        rvVar.zzcee = zzgA;
                        i2 = i3;
                        i3++;
                        i4 = i2;
                    } else {
                        zze(new to(this, rvVar.zzcea, vt.zzM(rvVar.zzbZf)));
                        if (i == -9) {
                            arrayList.addAll(this.zzcdw.zza(rvVar.zzcef, true, false, this.zzcdj));
                        } else {
                            zd.zzb(i == -25, "Unknown transaction abort reason: " + i);
                        }
                        arrayList2.add(new rl(this, rvVar, zzgA));
                    }
                }
                i2 = i4;
                i3++;
                i4 = i2;
            }
            if (i4 == -1) {
                vbVar.setValue(null);
            } else {
                vbVar.setValue(list.subList(0, i4 + 1));
            }
            zzT(arrayList);
            ArrayList arrayList3 = (ArrayList) arrayList2;
            int size = arrayList3.size();
            i2 = 0;
            while (i2 < size) {
                Object obj = arrayList3.get(i2);
                i2++;
                zzo((Runnable) obj);
            }
        }
    }

    private final void zza(wp wpVar, Object obj) {
        if (wpVar.equals(qc.zzccL)) {
            this.zzcdj.zzay(((Long) obj).longValue());
        }
        qr qrVar = new qr(qc.zzccK, wpVar);
        try {
            xm zza = xp.zza(obj, xd.zzJb());
            this.zzcdk.zzg(qrVar, zza);
            zzT(this.zzcdv.zzi(qrVar, zza));
        } catch (Throwable e) {
            this.zzcdq.zzd("Failed to parse info update", e);
        }
    }

    private final void zza(String str, qr qrVar, DatabaseError databaseError) {
        if (databaseError != null && databaseError.getCode() != -1 && databaseError.getCode() != -25) {
            wl wlVar = this.zzcdq;
            String valueOf = String.valueOf(qrVar.toString());
            String valueOf2 = String.valueOf(databaseError.toString());
            wlVar.zze(new StringBuilder(((String.valueOf(str).length() + 13) + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length()).append(str).append(" at ").append(valueOf).append(" failed: ").append(valueOf2).toString(), null);
        }
    }

    private final void zza(List<rv> list, vb<List<rv>> vbVar) {
        List list2 = (List) vbVar.getValue();
        if (list2 != null) {
            list.addAll(list2);
        }
        vbVar.zza(new ri(this, list));
    }

    private static DatabaseError zzab(String str, String str2) {
        return str != null ? DatabaseError.zzZ(str, str2) : null;
    }

    private final qr zzb(qr qrVar, int i) {
        qr zzFq = zzo(qrVar).zzFq();
        if (this.zzcdr.zzIH()) {
            wl wlVar = this.zzcdq;
            String valueOf = String.valueOf(qrVar);
            String valueOf2 = String.valueOf(zzFq);
            wlVar.zzb(new StringBuilder((String.valueOf(valueOf).length() + 44) + String.valueOf(valueOf2).length()).append("Aborting transactions for path: ").append(valueOf).append(". Affected: ").append(valueOf2).toString(), null, new Object[0]);
        }
        vb zzK = this.zzcdm.zzK(qrVar);
        zzK.zza(new rj(this, i), false);
        zza(zzK, i);
        zzK.zza(new rk(this, i), false, false);
        return zzFq;
    }

    private final void zzb(vb<List<rv>> vbVar) {
        List list = (List) vbVar.getValue();
        if (list != null) {
            int i = 0;
            while (i < list.size()) {
                if (((rv) list.get(i)).zzceb == rw.zzcem) {
                    list.remove(i);
                } else {
                    i++;
                }
            }
            if (list.size() > 0) {
                vbVar.setValue(list);
            } else {
                vbVar.setValue(null);
            }
        }
        vbVar.zza(new re(this));
    }

    private final List<rv> zzc(vb<List<rv>> vbVar) {
        List arrayList = new ArrayList();
        zza(arrayList, (vb) vbVar);
        Collections.sort(arrayList);
        return arrayList;
    }

    private final qr zzn(qr qrVar) {
        vb zzo = zzo(qrVar);
        qr zzFq = zzo.zzFq();
        List<rv> zzc = zzc(zzo);
        if (!zzc.isEmpty()) {
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            for (rv zzb : zzc) {
                arrayList2.add(Long.valueOf(zzb.zzcef));
            }
            for (rv rvVar : zzc) {
                Object obj;
                DatabaseError zzj;
                qr.zza(zzFq, rvVar.zzbZf);
                ArrayList arrayList3 = new ArrayList();
                if (rvVar.zzceb == rw.zzceo) {
                    obj = 1;
                    zzj = rvVar.zzcee;
                    if (zzj.getCode() != -25) {
                        arrayList3.addAll(this.zzcdw.zza(rvVar.zzcef, true, false, this.zzcdj));
                    }
                } else if (rvVar.zzceb != rw.zzcek) {
                    zzj = null;
                    obj = null;
                } else if (rvVar.retryCount >= 25) {
                    obj = 1;
                    zzj = DatabaseError.zzgA("maxretries");
                    arrayList3.addAll(this.zzcdw.zza(rvVar.zzcef, true, false, this.zzcdj));
                } else {
                    Result doTransaction;
                    xm zza = zza(rvVar.zzbZf, arrayList2);
                    rvVar.zzceg = zza;
                    try {
                        doTransaction = rvVar.zzcdZ.doTransaction(zzh.zza(zza));
                        zzj = null;
                    } catch (Throwable th) {
                        DatabaseError fromException = DatabaseError.fromException(th);
                        doTransaction = Transaction.abort();
                        zzj = fromException;
                    }
                    if (doTransaction.isSuccess()) {
                        Long valueOf = Long.valueOf(rvVar.zzcef);
                        Map zza2 = se.zza(this.zzcdj);
                        xm zzFn = doTransaction.zzFn();
                        xm zza3 = se.zza(zzFn, zza2);
                        rvVar.zzceh = zzFn;
                        rvVar.zzcei = zza3;
                        rvVar.zzcef = zzHk();
                        arrayList2.remove(valueOf);
                        arrayList3.addAll(this.zzcdw.zza(rvVar.zzbZf, zzFn, zza3, rvVar.zzcef, rvVar.zzced, false));
                        arrayList3.addAll(this.zzcdw.zza(valueOf.longValue(), true, false, this.zzcdj));
                        zzj = null;
                        obj = null;
                    } else {
                        obj = 1;
                        arrayList3.addAll(this.zzcdw.zza(rvVar.zzcef, true, false, this.zzcdj));
                    }
                }
                zzT(arrayList3);
                if (obj != null) {
                    rvVar.zzceb = rw.zzcem;
                    DataSnapshot zza4 = zzh.zza(zzh.zza(this, rvVar.zzbZf), xf.zzj(rvVar.zzceg));
                    zzq(new rf(this, rvVar));
                    arrayList.add(new rg(this, rvVar, zzj, zza4));
                }
            }
            zzb(this.zzcdm);
            for (int i = 0; i < arrayList.size(); i++) {
                zzo((Runnable) arrayList.get(i));
            }
            zzHl();
        }
        return zzFq;
    }

    private final vb<List<rv>> zzo(qr qrVar) {
        vb<List<rv>> vbVar = this.zzcdm;
        while (!qrVar.isEmpty() && vbVar.getValue() == null) {
            vbVar = vbVar.zzK(new qr(qrVar.zzHc()));
            qrVar = qrVar.zzHd();
        }
        return vbVar;
    }

    public final FirebaseDatabase getDatabase() {
        return this.zzcdx;
    }

    final void interrupt() {
        this.zzccd.interrupt("repo_interrupt");
    }

    public final void onDisconnect() {
        zza(qc.zzccN, Boolean.valueOf(false));
        Map zza = se.zza(this.zzcdj);
        si siVar = this.zzcdl;
        si siVar2 = new si();
        siVar.zza(new qr(""), new sf(siVar2, zza));
        List arrayList = new ArrayList();
        siVar2.zza(qr.zzGZ(), new qy(this, arrayList));
        this.zzcdl = new si();
        zzT(arrayList);
    }

    public final void purgeOutstandingWrites() {
        if (this.zzcdq.zzIH()) {
            this.zzcdq.zzb("Purging writes", null, new Object[0]);
        }
        zzT(this.zzcdw.zzHq());
        zzb(qr.zzGZ(), -25);
        this.zzccd.purgeOutstandingWrites();
    }

    final void resume() {
        this.zzccd.resume("repo_interrupt");
    }

    public final String toString() {
        return this.zzbYW.toString();
    }

    public final void zzB(Map<String, Object> map) {
        for (Entry entry : map.entrySet()) {
            zza(wp.zzgT((String) entry.getKey()), entry.getValue());
        }
    }

    public final void zzGb() {
        zza(qc.zzccN, Boolean.valueOf(true));
    }

    public final rx zzHh() {
        return this.zzbYW;
    }

    public final long zzHi() {
        return this.zzcdj.zzJF();
    }

    final boolean zzHj() {
        return (this.zzcdv.isEmpty() && this.zzcdw.isEmpty()) ? false : true;
    }

    public final void zza(qr qrVar, pz pzVar, CompletionListener completionListener, Map<String, Object> map) {
        if (this.zzcdq.zzIH()) {
            wl wlVar = this.zzcdq;
            String valueOf = String.valueOf(qrVar);
            wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 8).append("update: ").append(valueOf).toString(), null, new Object[0]);
        }
        if (this.zzcds.zzIH()) {
            wlVar = this.zzcds;
            valueOf = String.valueOf(qrVar);
            String valueOf2 = String.valueOf(map);
            wlVar.zzb(new StringBuilder((String.valueOf(valueOf).length() + 9) + String.valueOf(valueOf2).length()).append("update: ").append(valueOf).append(" ").append(valueOf2).toString(), null, new Object[0]);
        }
        if (pzVar.isEmpty()) {
            if (this.zzcdq.zzIH()) {
                this.zzcdq.zzb("update called with no changes. No-op", null, new Object[0]);
            }
            zza(completionListener, null, qrVar);
            return;
        }
        pz zza = se.zza(pzVar, se.zza(this.zzcdj));
        long zzHk = zzHk();
        zzT(this.zzcdw.zza(qrVar, pzVar, zza, zzHk, true));
        this.zzccd.zza(qrVar.zzHb(), (Map) map, new rt(this, qrVar, zzHk, completionListener));
        Iterator it = pzVar.iterator();
        while (it.hasNext()) {
            zzn(zzb(qrVar.zzh((qr) ((Entry) it.next()).getKey()), -9));
        }
    }

    public final void zza(qr qrVar, xm xmVar, CompletionListener completionListener) {
        if (this.zzcdq.zzIH()) {
            wl wlVar = this.zzcdq;
            String valueOf = String.valueOf(qrVar);
            wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 5).append("set: ").append(valueOf).toString(), null, new Object[0]);
        }
        if (this.zzcds.zzIH()) {
            wlVar = this.zzcds;
            valueOf = String.valueOf(qrVar);
            String valueOf2 = String.valueOf(xmVar);
            wlVar.zzb(new StringBuilder((String.valueOf(valueOf).length() + 6) + String.valueOf(valueOf2).length()).append("set: ").append(valueOf).append(" ").append(valueOf2).toString(), null, new Object[0]);
        }
        xm zza = se.zza(xmVar, se.zza(this.zzcdj));
        long zzHk = zzHk();
        zzT(this.zzcdw.zza(qrVar, xmVar, zza, zzHk, true, true));
        this.zzccd.zza(qrVar.zzHb(), xmVar.getValue(true), new rs(this, qrVar, zzHk, completionListener));
        zzn(zzb(qrVar, -9));
    }

    public final void zza(qr qrVar, CompletionListener completionListener) {
        this.zzccd.zza(qrVar.zzHb(), new qx(this, qrVar, completionListener));
    }

    public final void zza(qr qrVar, Handler handler, boolean z) {
        if (this.zzcdq.zzIH()) {
            wl wlVar = this.zzcdq;
            String valueOf = String.valueOf(qrVar);
            wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 13).append("transaction: ").append(valueOf).toString(), null, new Object[0]);
        }
        if (this.zzcds.zzIH()) {
            wlVar = this.zzcdq;
            valueOf = String.valueOf(qrVar);
            wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 13).append("transaction: ").append(valueOf).toString(), null, new Object[0]);
        }
        if (this.zzcdp.zzcaE && !this.zzcdy) {
            this.zzcdy = true;
            this.zzcdr.info("runTransaction() usage detected while persistence is enabled. Please be aware that transactions *will not* be persisted across database restarts.  See https://www.firebase.com/docs/android/guide/offline-capabilities.html#section-handling-transactions-offline for more details.");
        }
        DatabaseReference zza = zzh.zza(this, qrVar);
        ValueEventListener qzVar = new qz(this);
        zzf(new to(this, qzVar, zza.zzFr()));
        int i = rw.zzcej;
        long j = this.zzcdz;
        this.zzcdz = 1 + j;
        rv rvVar = new rv(qrVar, handler, qzVar, i, z, j);
        xm zza2 = zza(qrVar, new ArrayList());
        rvVar.zzceg = zza2;
        DatabaseError databaseError;
        Result result;
        try {
            Result doTransaction = handler.doTransaction(zzh.zza(zza2));
            if (doTransaction == null) {
                throw new NullPointerException("Transaction returned null as result");
            }
            Result result2 = doTransaction;
            databaseError = null;
            result = result2;
            if (result.isSuccess()) {
                rvVar.zzceb = rw.zzcek;
                vb zzK = this.zzcdm.zzK(qrVar);
                List list = (List) zzK.getValue();
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(rvVar);
                zzK.setValue(list);
                Map zza3 = se.zza(this.zzcdj);
                xm zzFn = result.zzFn();
                xm zza4 = se.zza(zzFn, zza3);
                rvVar.zzceh = zzFn;
                rvVar.zzcei = zza4;
                rvVar.zzcef = zzHk();
                zzT(this.zzcdw.zza(qrVar, zzFn, zza4, rvVar.zzcef, z, false));
                zzHl();
                return;
            }
            rvVar.zzceh = null;
            rvVar.zzcei = null;
            zzo(new ra(this, handler, databaseError, zzh.zza(zza, xf.zzj(rvVar.zzceg))));
        } catch (Throwable th) {
            DatabaseError fromException = DatabaseError.fromException(th);
            databaseError = fromException;
            result = Transaction.abort();
        }
    }

    public final void zza(qr qrVar, Map<qr, xm> map, CompletionListener completionListener, Map<String, Object> map2) {
        this.zzccd.zzb(qrVar.zzHb(), (Map) map2, new qw(this, qrVar, map, completionListener));
    }

    public final void zza(vt vtVar, boolean z) {
        this.zzcdw.zza(vtVar, z);
    }

    final void zza(CompletionListener completionListener, DatabaseError databaseError, qr qrVar) {
        if (completionListener != null) {
            wp zzHf = qrVar.zzHf();
            DatabaseReference zza = (zzHf == null || !zzHf.zzIN()) ? zzh.zza(this, qrVar) : zzh.zza(this, qrVar.zzHe());
            zzo(new rr(this, completionListener, databaseError, zza));
        }
    }

    public final void zza(List<String> list, Object obj, boolean z, Long l) {
        List zza;
        qr qrVar = new qr((List) list);
        if (this.zzcdq.zzIH()) {
            wl wlVar = this.zzcdq;
            String valueOf = String.valueOf(qrVar);
            wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 14).append("onDataUpdate: ").append(valueOf).toString(), null, new Object[0]);
        }
        if (this.zzcds.zzIH()) {
            wlVar = this.zzcdq;
            valueOf = String.valueOf(qrVar);
            String valueOf2 = String.valueOf(obj);
            wlVar.zzb(new StringBuilder((String.valueOf(valueOf).length() + 15) + String.valueOf(valueOf2).length()).append("onDataUpdate: ").append(valueOf).append(" ").append(valueOf2).toString(), null, new Object[0]);
        }
        this.zzcdt++;
        if (l != null) {
            try {
                th thVar = new th(l.longValue());
                if (z) {
                    Map hashMap = new HashMap();
                    for (Entry entry : ((Map) obj).entrySet()) {
                        hashMap.put(new qr((String) entry.getKey()), xp.zza(entry.getValue(), xd.zzJb()));
                    }
                    zza = this.zzcdw.zza(qrVar, hashMap, thVar);
                } else {
                    zza = this.zzcdw.zza(qrVar, xp.zza(obj, xd.zzJb()), thVar);
                }
            } catch (Throwable e) {
                this.zzcdq.zzd("FIREBASE INTERNAL ERROR", e);
                return;
            }
        } else if (z) {
            Map hashMap2 = new HashMap();
            for (Entry entry2 : ((Map) obj).entrySet()) {
                hashMap2.put(new qr((String) entry2.getKey()), xp.zza(entry2.getValue(), xd.zzJb()));
            }
            zza = this.zzcdw.zza(qrVar, hashMap2);
        } else {
            zza = this.zzcdw.zzi(qrVar, xp.zza(obj, xd.zzJb()));
        }
        if (zza.size() > 0) {
            zzn(qrVar);
        }
        zzT(zza);
    }

    public final void zza(List<String> list, List<pe> list2, Long l) {
        qr qrVar = new qr((List) list);
        if (this.zzcdq.zzIH()) {
            wl wlVar = this.zzcdq;
            String valueOf = String.valueOf(qrVar);
            wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 20).append("onRangeMergeUpdate: ").append(valueOf).toString(), null, new Object[0]);
        }
        if (this.zzcds.zzIH()) {
            wlVar = this.zzcdq;
            valueOf = String.valueOf(qrVar);
            String valueOf2 = String.valueOf(list2);
            wlVar.zzb(new StringBuilder((String.valueOf(valueOf).length() + 21) + String.valueOf(valueOf2).length()).append("onRangeMergeUpdate: ").append(valueOf).append(" ").append(valueOf2).toString(), null, new Object[0]);
        }
        this.zzcdt++;
        List arrayList = new ArrayList(list2.size());
        for (pe xtVar : list2) {
            arrayList.add(new xt(xtVar));
        }
        List zza = l != null ? this.zzcdw.zza(qrVar, arrayList, new th(l.longValue())) : this.zzcdw.zzb(qrVar, arrayList);
        if (zza.size() > 0) {
            zzn(qrVar);
        }
        zzT(zza);
    }

    public final void zzaB(boolean z) {
        zza(qc.zzccM, Boolean.valueOf(z));
    }

    public final void zzb(qr qrVar, xm xmVar, CompletionListener completionListener) {
        this.zzccd.zzb(qrVar.zzHb(), xmVar.getValue(true), new ru(this, qrVar, xmVar, completionListener));
    }

    public final void zze(qi qiVar) {
        zzT(qc.zzccK.equals(qiVar.zzGH().zzFq().zzHc()) ? this.zzcdv.zzh(qiVar) : this.zzcdw.zzh(qiVar));
    }

    public final void zzf(qi qiVar) {
        wp zzHc = qiVar.zzGH().zzFq().zzHc();
        List zzg = (zzHc == null || !zzHc.equals(qc.zzccK)) ? this.zzcdw.zzg(qiVar) : this.zzcdv.zzg(qiVar);
        zzT(zzg);
    }

    public final void zzo(Runnable runnable) {
        this.zzcdp.zzGO();
        this.zzcdp.zzccO.zzo(runnable);
    }

    public final void zzq(Runnable runnable) {
        this.zzcdp.zzGO();
        this.zzcdp.zzccQ.zzq(runnable);
    }
}
