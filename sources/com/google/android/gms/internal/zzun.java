package com.google.android.gms.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

@zzzn
public final class zzun implements zztz {
    private final Context mContext;
    private final Object mLock = new Object();
    private final long mStartTime;
    private final zzaae zzMM;
    private final long zzMN;
    private boolean zzMP = false;
    private final String zzMR;
    private List<zzuh> zzMS = new ArrayList();
    private zzue zzMW;
    private final zzub zzMu;
    private final boolean zzMy;
    private final zznb zzsK;
    private final zzuq zzsX;
    private final boolean zzwJ;

    public zzun(Context context, zzaae zzaae, zzuq zzuq, zzub zzub, boolean z, boolean z2, String str, long j, long j2, zznb zznb) {
        this.mContext = context;
        this.zzMM = zzaae;
        this.zzsX = zzuq;
        this.zzMu = zzub;
        this.zzwJ = z;
        this.zzMy = z2;
        this.zzMR = str;
        this.mStartTime = j;
        this.zzMN = j2;
        this.zzsK = zznb;
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzMP = true;
            if (this.zzMW != null) {
                this.zzMW.cancel();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzuh zzf(java.util.List<com.google.android.gms.internal.zzua> r24) {
        /*
        r23 = this;
        r2 = "Starting mediation.";
        com.google.android.gms.internal.zzajc.zzaC(r2);
        r17 = new java.util.ArrayList;
        r17.<init>();
        r0 = r23;
        r2 = r0.zzsK;
        r18 = r2.zzdS();
        r0 = r23;
        r2 = r0.zzMM;
        r2 = r2.zzvX;
        r3 = 2;
        r3 = new int[r3];
        r4 = r2.zzAu;
        if (r4 == 0) goto L_0x0196;
    L_0x001f:
        com.google.android.gms.ads.internal.zzbs.zzbS();
        r0 = r23;
        r4 = r0.zzMR;
        r4 = com.google.android.gms.internal.zzuj.zza(r4, r3);
        if (r4 == 0) goto L_0x0196;
    L_0x002c:
        r4 = 0;
        r4 = r3[r4];
        r5 = 1;
        r5 = r3[r5];
        r6 = r2.zzAu;
        r7 = r6.length;
        r3 = 0;
    L_0x0036:
        if (r3 >= r7) goto L_0x0196;
    L_0x0038:
        r9 = r6[r3];
        r8 = r9.width;
        if (r4 != r8) goto L_0x0096;
    L_0x003e:
        r8 = r9.height;
        if (r5 != r8) goto L_0x0096;
    L_0x0042:
        r19 = r24.iterator();
    L_0x0046:
        r2 = r19.hasNext();
        if (r2 == 0) goto L_0x0177;
    L_0x004c:
        r7 = r19.next();
        r7 = (com.google.android.gms.internal.zzua) r7;
        r3 = "Trying mediation network: ";
        r2 = r7.zzLI;
        r2 = java.lang.String.valueOf(r2);
        r4 = r2.length();
        if (r4 == 0) goto L_0x0099;
    L_0x0060:
        r2 = r3.concat(r2);
    L_0x0064:
        com.google.android.gms.internal.zzajc.zzaS(r2);
        r2 = r7.zzLJ;
        r20 = r2.iterator();
    L_0x006d:
        r2 = r20.hasNext();
        if (r2 == 0) goto L_0x0046;
    L_0x0073:
        r4 = r20.next();
        r4 = (java.lang.String) r4;
        r0 = r23;
        r2 = r0.zzsK;
        r21 = r2.zzdS();
        r0 = r23;
        r0 = r0.mLock;
        r22 = r0;
        monitor-enter(r22);
        r0 = r23;
        r2 = r0.zzMP;	 Catch:{ all -> 0x014c }
        if (r2 == 0) goto L_0x009f;
    L_0x008e:
        r2 = new com.google.android.gms.internal.zzuh;	 Catch:{ all -> 0x014c }
        r3 = -1;
        r2.<init>(r3);	 Catch:{ all -> 0x014c }
        monitor-exit(r22);	 Catch:{ all -> 0x014c }
    L_0x0095:
        return r2;
    L_0x0096:
        r3 = r3 + 1;
        goto L_0x0036;
    L_0x0099:
        r2 = new java.lang.String;
        r2.<init>(r3);
        goto L_0x0064;
    L_0x009f:
        r2 = new com.google.android.gms.internal.zzue;	 Catch:{ all -> 0x014c }
        r0 = r23;
        r3 = r0.mContext;	 Catch:{ all -> 0x014c }
        r0 = r23;
        r5 = r0.zzsX;	 Catch:{ all -> 0x014c }
        r0 = r23;
        r6 = r0.zzMu;	 Catch:{ all -> 0x014c }
        r0 = r23;
        r8 = r0.zzMM;	 Catch:{ all -> 0x014c }
        r8 = r8.zzSz;	 Catch:{ all -> 0x014c }
        r0 = r23;
        r10 = r0.zzMM;	 Catch:{ all -> 0x014c }
        r10 = r10.zzvT;	 Catch:{ all -> 0x014c }
        r0 = r23;
        r11 = r0.zzwJ;	 Catch:{ all -> 0x014c }
        r0 = r23;
        r12 = r0.zzMy;	 Catch:{ all -> 0x014c }
        r0 = r23;
        r13 = r0.zzMM;	 Catch:{ all -> 0x014c }
        r13 = r13.zzwj;	 Catch:{ all -> 0x014c }
        r0 = r23;
        r14 = r0.zzMM;	 Catch:{ all -> 0x014c }
        r14 = r14.zzwq;	 Catch:{ all -> 0x014c }
        r0 = r23;
        r15 = r0.zzMM;	 Catch:{ all -> 0x014c }
        r15 = r15.zzSO;	 Catch:{ all -> 0x014c }
        r0 = r23;
        r0 = r0.zzMM;	 Catch:{ all -> 0x014c }
        r16 = r0;
        r0 = r16;
        r0 = r0.zzTj;	 Catch:{ all -> 0x014c }
        r16 = r0;
        r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ all -> 0x014c }
        r0 = r23;
        r0.zzMW = r2;	 Catch:{ all -> 0x014c }
        monitor-exit(r22);	 Catch:{ all -> 0x014c }
        r0 = r23;
        r2 = r0.zzMW;
        r0 = r23;
        r10 = r0.mStartTime;
        r0 = r23;
        r12 = r0.zzMN;
        r2 = r2.zza(r10, r12);
        r0 = r23;
        r3 = r0.zzMS;
        r3.add(r2);
        r3 = r2.zzMF;
        if (r3 != 0) goto L_0x014f;
    L_0x0102:
        r3 = "Adapter succeeded.";
        com.google.android.gms.internal.zzajc.zzaC(r3);
        r0 = r23;
        r3 = r0.zzsK;
        r5 = "mediation_network_succeed";
        r3.zzh(r5, r4);
        r3 = r17.isEmpty();
        if (r3 != 0) goto L_0x0127;
    L_0x0116:
        r0 = r23;
        r3 = r0.zzsK;
        r4 = "mediation_networks_fail";
        r5 = ",";
        r0 = r17;
        r5 = android.text.TextUtils.join(r5, r0);
        r3.zzh(r4, r5);
    L_0x0127:
        r0 = r23;
        r3 = r0.zzsK;
        r4 = 1;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r6 = "mls";
        r4[r5] = r6;
        r0 = r21;
        r3.zza(r0, r4);
        r0 = r23;
        r3 = r0.zzsK;
        r4 = 1;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r6 = "ttm";
        r4[r5] = r6;
        r0 = r18;
        r3.zza(r0, r4);
        goto L_0x0095;
    L_0x014c:
        r2 = move-exception;
        monitor-exit(r22);	 Catch:{ all -> 0x014c }
        throw r2;
    L_0x014f:
        r0 = r17;
        r0.add(r4);
        r0 = r23;
        r3 = r0.zzsK;
        r4 = 1;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r6 = "mlf";
        r4[r5] = r6;
        r0 = r21;
        r3.zza(r0, r4);
        r3 = r2.zzMH;
        if (r3 == 0) goto L_0x006d;
    L_0x0169:
        r3 = com.google.android.gms.internal.zzagz.zzZr;
        r4 = new com.google.android.gms.internal.zzuo;
        r0 = r23;
        r4.<init>(r0, r2);
        r3.post(r4);
        goto L_0x006d;
    L_0x0177:
        r2 = r17.isEmpty();
        if (r2 != 0) goto L_0x018e;
    L_0x017d:
        r0 = r23;
        r2 = r0.zzsK;
        r3 = "mediation_networks_fail";
        r4 = ",";
        r0 = r17;
        r4 = android.text.TextUtils.join(r4, r0);
        r2.zzh(r3, r4);
    L_0x018e:
        r2 = new com.google.android.gms.internal.zzuh;
        r3 = 1;
        r2.<init>(r3);
        goto L_0x0095;
    L_0x0196:
        r9 = r2;
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzun.zzf(java.util.List):com.google.android.gms.internal.zzuh");
    }

    public final List<zzuh> zzfg() {
        return this.zzMS;
    }
}
