package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbs;

@zzzn
public final class zzadm extends zzafp implements zzads, zzadv {
    private final Context mContext;
    private int mErrorCode = 3;
    private final Object mLock;
    private final String zzMs;
    private final zzafg zzQQ;
    private final String zzWA;
    private final zzua zzWB;
    private final long zzWC;
    private int zzWD = 0;
    private zzadp zzWE;
    private final zzadz zzWy;
    private final zzadv zzWz;

    public zzadm(Context context, String str, String str2, zzua zzua, zzafg zzafg, zzadz zzadz, zzadv zzadv, long j) {
        this.mContext = context;
        this.zzMs = str;
        this.zzWA = str2;
        this.zzWB = zzua;
        this.zzQQ = zzafg;
        this.zzWy = zzadz;
        this.mLock = new Object();
        this.zzWz = zzadv;
        this.zzWC = j;
    }

    private final void zza(zzir zzir, zzut zzut) {
        this.zzWy.zzgX().zza((zzadv) this);
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzMs)) {
                zzut.zza(zzir, this.zzWA, this.zzWB.zzLH);
            } else {
                zzut.zzc(zzir, this.zzWA);
            }
        } catch (Throwable e) {
            zzajc.zzc("Fail to load ad from adapter.", e);
            zza(this.zzMs, 0);
        }
    }

    private final boolean zzf(long j) {
        long elapsedRealtime = this.zzWC - (zzbs.zzbF().elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            this.mErrorCode = 4;
            return false;
        }
        try {
            this.mLock.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.mErrorCode = 5;
            return false;
        }
    }

    public final void onStop() {
    }

    public final void zza(String str, int i) {
        synchronized (this.mLock) {
            this.zzWD = 2;
            this.mErrorCode = i;
            this.mLock.notify();
        }
    }

    public final void zzaw(String str) {
        synchronized (this.mLock) {
            this.zzWD = 1;
            this.mLock.notify();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzbd() {
        /*
        r10 = this;
        r9 = 1;
        r8 = 0;
        r0 = r10.zzWy;
        if (r0 == 0) goto L_0x0016;
    L_0x0006:
        r0 = r10.zzWy;
        r0 = r0.zzgX();
        if (r0 == 0) goto L_0x0016;
    L_0x000e:
        r0 = r10.zzWy;
        r0 = r0.zzgW();
        if (r0 != 0) goto L_0x0017;
    L_0x0016:
        return;
    L_0x0017:
        r0 = r10.zzWy;
        r1 = r0.zzgX();
        r1.zza(r8);
        r1.zza(r10);
        r0 = r10.zzQQ;
        r0 = r0.zzUj;
        r0 = r0.zzSz;
        r2 = r10.zzWy;
        r2 = r2.zzgW();
        r3 = r2.isInitialized();	 Catch:{ RemoteException -> 0x009c }
        if (r3 == 0) goto L_0x0091;
    L_0x0035:
        r3 = com.google.android.gms.internal.zzaiy.zzaaH;	 Catch:{ RemoteException -> 0x009c }
        r4 = new com.google.android.gms.internal.zzadn;	 Catch:{ RemoteException -> 0x009c }
        r4.<init>(r10, r0, r2);	 Catch:{ RemoteException -> 0x009c }
        r3.post(r4);	 Catch:{ RemoteException -> 0x009c }
    L_0x003f:
        r0 = com.google.android.gms.ads.internal.zzbs.zzbF();
        r2 = r0.elapsedRealtime();
    L_0x0047:
        r4 = r10.mLock;
        monitor-enter(r4);
        r0 = r10.zzWD;	 Catch:{ all -> 0x00e1 }
        if (r0 == 0) goto L_0x00ac;
    L_0x004e:
        r0 = new com.google.android.gms.internal.zzadr;	 Catch:{ all -> 0x00e1 }
        r0.<init>();	 Catch:{ all -> 0x00e1 }
        r5 = com.google.android.gms.ads.internal.zzbs.zzbF();	 Catch:{ all -> 0x00e1 }
        r6 = r5.elapsedRealtime();	 Catch:{ all -> 0x00e1 }
        r2 = r6 - r2;
        r2 = r0.zzg(r2);	 Catch:{ all -> 0x00e1 }
        r0 = r10.zzWD;	 Catch:{ all -> 0x00e1 }
        if (r9 != r0) goto L_0x00a9;
    L_0x0065:
        r0 = 6;
    L_0x0066:
        r0 = r2.zzw(r0);	 Catch:{ all -> 0x00e1 }
        r2 = r10.zzMs;	 Catch:{ all -> 0x00e1 }
        r0 = r0.zzax(r2);	 Catch:{ all -> 0x00e1 }
        r2 = r10.zzWB;	 Catch:{ all -> 0x00e1 }
        r2 = r2.zzLK;	 Catch:{ all -> 0x00e1 }
        r0 = r0.zzay(r2);	 Catch:{ all -> 0x00e1 }
        r0 = r0.zzgU();	 Catch:{ all -> 0x00e1 }
        r10.zzWE = r0;	 Catch:{ all -> 0x00e1 }
        monitor-exit(r4);	 Catch:{ all -> 0x00e1 }
    L_0x007f:
        r1.zza(r8);
        r1.zza(r8);
        r0 = r10.zzWD;
        if (r0 != r9) goto L_0x00e7;
    L_0x0089:
        r0 = r10.zzWz;
        r1 = r10.zzMs;
        r0.zzaw(r1);
        goto L_0x0016;
    L_0x0091:
        r3 = com.google.android.gms.internal.zzaiy.zzaaH;	 Catch:{ RemoteException -> 0x009c }
        r4 = new com.google.android.gms.internal.zzado;	 Catch:{ RemoteException -> 0x009c }
        r4.<init>(r10, r2, r0, r1);	 Catch:{ RemoteException -> 0x009c }
        r3.post(r4);	 Catch:{ RemoteException -> 0x009c }
        goto L_0x003f;
    L_0x009c:
        r0 = move-exception;
        r2 = "Fail to check if adapter is initialized.";
        com.google.android.gms.internal.zzajc.zzc(r2, r0);
        r0 = r10.zzMs;
        r2 = 0;
        r10.zza(r0, r2);
        goto L_0x003f;
    L_0x00a9:
        r0 = r10.mErrorCode;	 Catch:{ all -> 0x00e1 }
        goto L_0x0066;
    L_0x00ac:
        r0 = r10.zzf(r2);	 Catch:{ all -> 0x00e1 }
        if (r0 != 0) goto L_0x00e4;
    L_0x00b2:
        r0 = new com.google.android.gms.internal.zzadr;	 Catch:{ all -> 0x00e1 }
        r0.<init>();	 Catch:{ all -> 0x00e1 }
        r5 = r10.mErrorCode;	 Catch:{ all -> 0x00e1 }
        r0 = r0.zzw(r5);	 Catch:{ all -> 0x00e1 }
        r5 = com.google.android.gms.ads.internal.zzbs.zzbF();	 Catch:{ all -> 0x00e1 }
        r6 = r5.elapsedRealtime();	 Catch:{ all -> 0x00e1 }
        r2 = r6 - r2;
        r0 = r0.zzg(r2);	 Catch:{ all -> 0x00e1 }
        r2 = r10.zzMs;	 Catch:{ all -> 0x00e1 }
        r0 = r0.zzax(r2);	 Catch:{ all -> 0x00e1 }
        r2 = r10.zzWB;	 Catch:{ all -> 0x00e1 }
        r2 = r2.zzLK;	 Catch:{ all -> 0x00e1 }
        r0 = r0.zzay(r2);	 Catch:{ all -> 0x00e1 }
        r0 = r0.zzgU();	 Catch:{ all -> 0x00e1 }
        r10.zzWE = r0;	 Catch:{ all -> 0x00e1 }
        monitor-exit(r4);	 Catch:{ all -> 0x00e1 }
        goto L_0x007f;
    L_0x00e1:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x00e1 }
        throw r0;
    L_0x00e4:
        monitor-exit(r4);	 Catch:{ all -> 0x00e1 }
        goto L_0x0047;
    L_0x00e7:
        r0 = r10.zzWz;
        r1 = r10.zzMs;
        r2 = r10.mErrorCode;
        r0.zza(r1, r2);
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzadm.zzbd():void");
    }

    public final zzadp zzgR() {
        zzadp zzadp;
        synchronized (this.mLock) {
            zzadp = this.zzWE;
        }
        return zzadp;
    }

    public final zzua zzgS() {
        return this.zzWB;
    }

    public final void zzgT() {
        zza(this.zzQQ.zzUj.zzSz, this.zzWy.zzgW());
    }

    public final void zzv(int i) {
        zza(this.zzMs, 0);
    }
}
