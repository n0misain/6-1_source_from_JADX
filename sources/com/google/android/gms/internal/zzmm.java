package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;

@zzzn
public final class zzmm {
    private final Object mLock = new Object();
    private final ConditionVariable zzBS = new ConditionVariable();
    @Nullable
    private SharedPreferences zzBT = null;
    private volatile boolean zzuH = false;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r4) {
        /*
        r3 = this;
        r0 = r3.zzuH;
        if (r0 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r1 = r3.mLock;
        monitor-enter(r1);
        r0 = r3.zzuH;	 Catch:{ all -> 0x000e }
        if (r0 == 0) goto L_0x0011;
    L_0x000c:
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        goto L_0x0004;
    L_0x000e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        throw r0;
    L_0x0011:
        r0 = com.google.android.gms.common.zzo.getRemoteContext(r4);	 Catch:{ all -> 0x0040 }
        if (r0 != 0) goto L_0x0047;
    L_0x0017:
        if (r4 == 0) goto L_0x0047;
    L_0x0019:
        r0 = r4.getApplicationContext();	 Catch:{ all -> 0x0040 }
        if (r0 != 0) goto L_0x0028;
    L_0x001f:
        if (r4 != 0) goto L_0x002a;
    L_0x0021:
        r0 = r3.zzBS;	 Catch:{ all -> 0x000e }
        r0.open();	 Catch:{ all -> 0x000e }
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        goto L_0x0004;
    L_0x0028:
        r4 = r0;
        goto L_0x001f;
    L_0x002a:
        com.google.android.gms.ads.internal.zzbs.zzbJ();	 Catch:{ all -> 0x0040 }
        r0 = "google_ads_flags";
        r2 = 0;
        r0 = r4.getSharedPreferences(r0, r2);	 Catch:{ all -> 0x0040 }
        r3.zzBT = r0;	 Catch:{ all -> 0x0040 }
        r0 = 1;
        r3.zzuH = r0;	 Catch:{ all -> 0x0040 }
        r0 = r3.zzBS;	 Catch:{ all -> 0x000e }
        r0.open();	 Catch:{ all -> 0x000e }
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        goto L_0x0004;
    L_0x0040:
        r0 = move-exception;
        r2 = r3.zzBS;	 Catch:{ all -> 0x000e }
        r2.open();	 Catch:{ all -> 0x000e }
        throw r0;	 Catch:{ all -> 0x000e }
    L_0x0047:
        r4 = r0;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzmm.initialize(android.content.Context):void");
    }

    public final <T> T zzd(zzme<T> zzme) {
        if (this.zzBS.block(5000)) {
            if (!this.zzuH || this.zzBT == null) {
                synchronized (this.mLock) {
                    if (!this.zzuH || this.zzBT == null) {
                        T zzdI = zzme.zzdI();
                        return zzdI;
                    }
                }
            }
            return zzait.zzb(new zzmn(this, zzme));
        }
        throw new IllegalStateException("Flags.initialize() was not called!");
    }
}
