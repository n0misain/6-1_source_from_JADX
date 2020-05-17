package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbs;

@zzzn
public final class zzhs {
    @Nullable
    private Context mContext;
    private final Object mLock = new Object();
    private final Runnable zzzp = new zzht(this);
    @Nullable
    private zzhz zzzq;
    @Nullable
    private zzid zzzr;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void connect() {
        /*
        r6 = this;
        r1 = r6.mLock;
        monitor-enter(r1);
        r0 = r6.mContext;	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x000b;
    L_0x0007:
        r0 = r6.zzzq;	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x002f }
    L_0x000c:
        return;
    L_0x000d:
        r0 = new com.google.android.gms.internal.zzhv;	 Catch:{ all -> 0x002f }
        r0.<init>(r6);	 Catch:{ all -> 0x002f }
        r2 = new com.google.android.gms.internal.zzhw;	 Catch:{ all -> 0x002f }
        r2.<init>(r6);	 Catch:{ all -> 0x002f }
        r3 = new com.google.android.gms.internal.zzhz;	 Catch:{ all -> 0x002f }
        r4 = r6.mContext;	 Catch:{ all -> 0x002f }
        r5 = com.google.android.gms.ads.internal.zzbs.zzbP();	 Catch:{ all -> 0x002f }
        r5 = r5.zzie();	 Catch:{ all -> 0x002f }
        r3.<init>(r4, r5, r0, r2);	 Catch:{ all -> 0x002f }
        r6.zzzq = r3;	 Catch:{ all -> 0x002f }
        r0 = r6.zzzq;	 Catch:{ all -> 0x002f }
        r0.zzrb();	 Catch:{ all -> 0x002f }
        monitor-exit(r1);	 Catch:{ all -> 0x002f }
        goto L_0x000c;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhs.connect():void");
    }

    private final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzzq == null) {
                return;
            }
            if (this.zzzq.isConnected() || this.zzzq.isConnecting()) {
                this.zzzq.disconnect();
            }
            this.zzzq = null;
            this.zzzr = null;
            Binder.flushPendingCommands();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r4) {
        /*
        r3 = this;
        if (r4 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r1 = r3.mLock;
        monitor-enter(r1);
        r0 = r3.mContext;	 Catch:{ all -> 0x000c }
        if (r0 == 0) goto L_0x000f;
    L_0x000a:
        monitor-exit(r1);	 Catch:{ all -> 0x000c }
        goto L_0x0002;
    L_0x000c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x000c }
        throw r0;
    L_0x000f:
        r0 = r4.getApplicationContext();	 Catch:{ all -> 0x000c }
        r3.mContext = r0;	 Catch:{ all -> 0x000c }
        r0 = com.google.android.gms.internal.zzmo.zzGk;	 Catch:{ all -> 0x000c }
        r2 = com.google.android.gms.ads.internal.zzbs.zzbL();	 Catch:{ all -> 0x000c }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x000c }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x000c }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x000c }
        if (r0 == 0) goto L_0x002c;
    L_0x0027:
        r3.connect();	 Catch:{ all -> 0x000c }
    L_0x002a:
        monitor-exit(r1);	 Catch:{ all -> 0x000c }
        goto L_0x0002;
    L_0x002c:
        r0 = com.google.android.gms.internal.zzmo.zzGj;	 Catch:{ all -> 0x000c }
        r2 = com.google.android.gms.ads.internal.zzbs.zzbL();	 Catch:{ all -> 0x000c }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x000c }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x000c }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x000c }
        if (r0 == 0) goto L_0x002a;
    L_0x003e:
        r0 = new com.google.android.gms.internal.zzhu;	 Catch:{ all -> 0x000c }
        r0.<init>(r3);	 Catch:{ all -> 0x000c }
        r2 = com.google.android.gms.ads.internal.zzbs.zzbC();	 Catch:{ all -> 0x000c }
        r2.zza(r0);	 Catch:{ all -> 0x000c }
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhs.initialize(android.content.Context):void");
    }

    public final zzhx zza(zzia zzia) {
        zzhx zzhx;
        synchronized (this.mLock) {
            if (this.zzzr == null) {
                zzhx = new zzhx();
            } else {
                try {
                    zzhx = this.zzzr.zza(zzia);
                } catch (Throwable e) {
                    zzajc.zzb("Unable to call into cache service.", e);
                    zzhx = new zzhx();
                }
            }
        }
        return zzhx;
    }

    public final void zzcX() {
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzGl)).booleanValue()) {
            synchronized (this.mLock) {
                connect();
                zzbs.zzbz();
                zzagz.zzZr.removeCallbacks(this.zzzp);
                zzbs.zzbz();
                zzagz.zzZr.postDelayed(this.zzzp, ((Long) zzbs.zzbL().zzd(zzmo.zzGm)).longValue());
            }
        }
    }
}
