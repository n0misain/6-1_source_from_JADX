package com.google.android.gms.internal;

import android.content.Context;

@zzzn
public final class zzaev implements zzgi {
    private final Context mContext;
    private final Object mLock = new Object();
    private boolean zzXy = false;
    private final String zztV;

    public zzaev(Context context, String str) {
        this.mContext = context;
        this.zztV = str;
    }

    public final void zza(zzgh zzgh) {
        zzu(zzgh.zzxS);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzu(boolean r5) {
        /*
        r4 = this;
        r0 = com.google.android.gms.ads.internal.zzbs.zzbY();
        r1 = r4.mContext;
        r0 = r0.zzp(r1);
        if (r0 != 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r1 = r4.mLock;
        monitor-enter(r1);
        r0 = r4.zzXy;	 Catch:{ all -> 0x0016 }
        if (r0 != r5) goto L_0x0019;
    L_0x0014:
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
        goto L_0x000c;
    L_0x0016:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
        throw r0;
    L_0x0019:
        r4.zzXy = r5;	 Catch:{ all -> 0x0016 }
        r0 = r4.zzXy;	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x002c;
    L_0x001f:
        r0 = com.google.android.gms.ads.internal.zzbs.zzbY();	 Catch:{ all -> 0x0016 }
        r2 = r4.mContext;	 Catch:{ all -> 0x0016 }
        r3 = r4.zztV;	 Catch:{ all -> 0x0016 }
        r0.zzc(r2, r3);	 Catch:{ all -> 0x0016 }
    L_0x002a:
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
        goto L_0x000c;
    L_0x002c:
        r0 = com.google.android.gms.ads.internal.zzbs.zzbY();	 Catch:{ all -> 0x0016 }
        r2 = r4.mContext;	 Catch:{ all -> 0x0016 }
        r3 = r4.zztV;	 Catch:{ all -> 0x0016 }
        r0.zzd(r2, r3);	 Catch:{ all -> 0x0016 }
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaev.zzu(boolean):void");
    }
}
