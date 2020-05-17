package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@zzzn
public final class zzuk implements zztz {
    private final Context mContext;
    private final Object mLock = new Object();
    private final long mStartTime;
    private final zzaae zzMM;
    private final long zzMN;
    private final int zzMO;
    private boolean zzMP = false;
    private final Map<zzajm<zzuh>, zzue> zzMQ = new HashMap();
    private final String zzMR;
    private List<zzuh> zzMS = new ArrayList();
    private final zzub zzMu;
    private final boolean zzMy;
    private final zzuq zzsX;
    private final boolean zzwJ;

    public zzuk(Context context, zzaae zzaae, zzuq zzuq, zzub zzub, boolean z, boolean z2, String str, long j, long j2, int i) {
        this.mContext = context;
        this.zzMM = zzaae;
        this.zzsX = zzuq;
        this.zzMu = zzub;
        this.zzwJ = z;
        this.zzMy = z2;
        this.zzMR = str;
        this.mStartTime = j;
        this.zzMN = j2;
        this.zzMO = 2;
    }

    private final void zza(zzajm<zzuh> zzajm) {
        zzagz.zzZr.post(new zzum(this, zzajm));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.zzuh zzg(java.util.List<com.google.android.gms.internal.zzajm<com.google.android.gms.internal.zzuh>> r5) {
        /*
        r4 = this;
        r2 = r4.mLock;
        monitor-enter(r2);
        r0 = r4.zzMP;	 Catch:{ all -> 0x003c }
        if (r0 == 0) goto L_0x000f;
    L_0x0007:
        r1 = new com.google.android.gms.internal.zzuh;	 Catch:{ all -> 0x003c }
        r0 = -1;
        r1.<init>(r0);	 Catch:{ all -> 0x003c }
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
    L_0x000e:
        return r1;
    L_0x000f:
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
        r2 = r5.iterator();
    L_0x0014:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x003f;
    L_0x001a:
        r0 = r2.next();
        r0 = (com.google.android.gms.internal.zzajm) r0;
        r1 = r0.get();	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        r1 = (com.google.android.gms.internal.zzuh) r1;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        r3 = r4.zzMS;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        r3.add(r1);	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        if (r1 == 0) goto L_0x0014;
    L_0x002d:
        r3 = r1.zzMF;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        if (r3 != 0) goto L_0x0014;
    L_0x0031:
        r4.zza(r0);	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        goto L_0x000e;
    L_0x0035:
        r0 = move-exception;
    L_0x0036:
        r1 = "Exception while processing an adapter; continuing with other adapters";
        com.google.android.gms.internal.zzajc.zzc(r1, r0);
        goto L_0x0014;
    L_0x003c:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
        throw r0;
    L_0x003f:
        r0 = 0;
        r4.zza(r0);
        r1 = new com.google.android.gms.internal.zzuh;
        r0 = 1;
        r1.<init>(r0);
        goto L_0x000e;
    L_0x004a:
        r0 = move-exception;
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzuk.zzg(java.util.List):com.google.android.gms.internal.zzuh");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.zzuh zzh(java.util.List<com.google.android.gms.internal.zzajm<com.google.android.gms.internal.zzuh>> r16) {
        /*
        r15 = this;
        r1 = r15.mLock;
        monitor-enter(r1);
        r0 = r15.zzMP;	 Catch:{ all -> 0x007e }
        if (r0 == 0) goto L_0x000f;
    L_0x0007:
        r2 = new com.google.android.gms.internal.zzuh;	 Catch:{ all -> 0x007e }
        r0 = -1;
        r2.<init>(r0);	 Catch:{ all -> 0x007e }
        monitor-exit(r1);	 Catch:{ all -> 0x007e }
    L_0x000e:
        return r2;
    L_0x000f:
        monitor-exit(r1);	 Catch:{ all -> 0x007e }
        r4 = -1;
        r3 = 0;
        r2 = 0;
        r0 = r15.zzMu;
        r0 = r0.zzMk;
        r6 = -1;
        r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r0 == 0) goto L_0x0081;
    L_0x001d:
        r0 = r15.zzMu;
        r0 = r0.zzMk;
    L_0x0021:
        r8 = r16.iterator();
        r6 = r0;
    L_0x0026:
        r0 = r8.hasNext();
        if (r0 == 0) goto L_0x00b8;
    L_0x002c:
        r0 = r8.next();
        r0 = (com.google.android.gms.internal.zzajm) r0;
        r1 = com.google.android.gms.ads.internal.zzbs.zzbF();
        r10 = r1.currentTimeMillis();
        r12 = 0;
        r1 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r1 != 0) goto L_0x0084;
    L_0x0040:
        r1 = r0.isDone();	 Catch:{ InterruptedException -> 0x00c5, ExecutionException -> 0x00c7, RemoteException -> 0x008d, TimeoutException -> 0x00c9 }
        if (r1 == 0) goto L_0x0084;
    L_0x0046:
        r1 = r0.get();	 Catch:{ InterruptedException -> 0x00c5, ExecutionException -> 0x00c7, RemoteException -> 0x008d, TimeoutException -> 0x00c9 }
        r1 = (com.google.android.gms.internal.zzuh) r1;	 Catch:{ InterruptedException -> 0x00c5, ExecutionException -> 0x00c7, RemoteException -> 0x008d, TimeoutException -> 0x00c9 }
    L_0x004c:
        r5 = r15.zzMS;	 Catch:{ InterruptedException -> 0x00c5, ExecutionException -> 0x00c7, RemoteException -> 0x008d, TimeoutException -> 0x00c9 }
        r5.add(r1);	 Catch:{ InterruptedException -> 0x00c5, ExecutionException -> 0x00c7, RemoteException -> 0x008d, TimeoutException -> 0x00c9 }
        if (r1 == 0) goto L_0x00cb;
    L_0x0053:
        r5 = r1.zzMF;	 Catch:{ InterruptedException -> 0x00c5, ExecutionException -> 0x00c7, RemoteException -> 0x008d, TimeoutException -> 0x00c9 }
        if (r5 != 0) goto L_0x00cb;
    L_0x0057:
        r5 = r1.zzMK;	 Catch:{ InterruptedException -> 0x00c5, ExecutionException -> 0x00c7, RemoteException -> 0x008d, TimeoutException -> 0x00c9 }
        if (r5 == 0) goto L_0x00cb;
    L_0x005b:
        r9 = r5.zzfo();	 Catch:{ InterruptedException -> 0x00c5, ExecutionException -> 0x00c7, RemoteException -> 0x008d, TimeoutException -> 0x00c9 }
        if (r9 <= r4) goto L_0x00cb;
    L_0x0061:
        r2 = r5.zzfo();	 Catch:{ InterruptedException -> 0x00c5, ExecutionException -> 0x00c7, RemoteException -> 0x008d, TimeoutException -> 0x00c9 }
        r14 = r1;
        r1 = r0;
        r0 = r14;
    L_0x0068:
        r3 = com.google.android.gms.ads.internal.zzbs.zzbF();
        r4 = r3.currentTimeMillis();
        r4 = r4 - r10;
        r4 = r6 - r4;
        r6 = 0;
        r4 = java.lang.Math.max(r4, r6);
        r6 = r4;
        r3 = r1;
        r4 = r2;
        r2 = r0;
        goto L_0x0026;
    L_0x007e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x007e }
        throw r0;
    L_0x0081:
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        goto L_0x0021;
    L_0x0084:
        r1 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException -> 0x00c5, ExecutionException -> 0x00c7, RemoteException -> 0x008d, TimeoutException -> 0x00c9 }
        r1 = r0.get(r6, r1);	 Catch:{ InterruptedException -> 0x00c5, ExecutionException -> 0x00c7, RemoteException -> 0x008d, TimeoutException -> 0x00c9 }
        r1 = (com.google.android.gms.internal.zzuh) r1;	 Catch:{ InterruptedException -> 0x00c5, ExecutionException -> 0x00c7, RemoteException -> 0x008d, TimeoutException -> 0x00c9 }
        goto L_0x004c;
    L_0x008d:
        r0 = move-exception;
    L_0x008e:
        r1 = "Exception while processing an adapter; continuing with other adapters";
        com.google.android.gms.internal.zzajc.zzc(r1, r0);	 Catch:{ all -> 0x00a6 }
        r0 = com.google.android.gms.ads.internal.zzbs.zzbF();
        r0 = r0.currentTimeMillis();
        r0 = r0 - r10;
        r0 = r6 - r0;
        r6 = 0;
        r0 = java.lang.Math.max(r0, r6);
        r6 = r0;
        goto L_0x0026;
    L_0x00a6:
        r0 = move-exception;
        r1 = com.google.android.gms.ads.internal.zzbs.zzbF();
        r2 = r1.currentTimeMillis();
        r2 = r2 - r10;
        r2 = r6 - r2;
        r4 = 0;
        java.lang.Math.max(r2, r4);
        throw r0;
    L_0x00b8:
        r15.zza(r3);
        if (r2 != 0) goto L_0x000e;
    L_0x00bd:
        r2 = new com.google.android.gms.internal.zzuh;
        r0 = 1;
        r2.<init>(r0);
        goto L_0x000e;
    L_0x00c5:
        r0 = move-exception;
        goto L_0x008e;
    L_0x00c7:
        r0 = move-exception;
        goto L_0x008e;
    L_0x00c9:
        r0 = move-exception;
        goto L_0x008e;
    L_0x00cb:
        r0 = r2;
        r1 = r3;
        r2 = r4;
        goto L_0x0068;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzuk.zzh(java.util.List):com.google.android.gms.internal.zzuh");
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzMP = true;
            for (zzue cancel : this.zzMQ.values()) {
                cancel.cancel();
            }
        }
    }

    public final zzuh zzf(List<zzua> list) {
        zzajc.zzaC("Starting mediation.");
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList arrayList = new ArrayList();
        zziv zziv = this.zzMM.zzvX;
        int[] iArr = new int[2];
        if (zziv.zzAu != null) {
            zzbs.zzbS();
            if (zzuj.zza(this.zzMR, iArr)) {
                int i = iArr[0];
                int i2 = iArr[1];
                for (zziv zziv2 : zziv.zzAu) {
                    if (i == zziv2.width && i2 == zziv2.height) {
                        break;
                    }
                }
            }
        }
        zziv zziv22 = zziv;
        for (zzua zzua : list) {
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(zzua.zzLI);
            zzajc.zzaS(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            for (String zzue : zzua.zzLJ) {
                zzue zzue2 = new zzue(this.mContext, zzue, this.zzsX, this.zzMu, zzua, this.zzMM.zzSz, zziv22, this.zzMM.zzvT, this.zzwJ, this.zzMy, this.zzMM.zzwj, this.zzMM.zzwq, this.zzMM.zzSO, this.zzMM.zzTj);
                zzajm zza = zzagt.zza(newCachedThreadPool, new zzul(this, zzue2));
                this.zzMQ.put(zza, zzue2);
                arrayList.add(zza);
            }
        }
        switch (this.zzMO) {
            case 2:
                return zzh(arrayList);
            default:
                return zzg(arrayList);
        }
    }

    public final List<zzuh> zzfg() {
        return this.zzMS;
    }
}
