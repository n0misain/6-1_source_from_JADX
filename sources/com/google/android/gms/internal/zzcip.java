package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzcip implements Runnable {
    private /* synthetic */ boolean zzbtw;
    private /* synthetic */ zzcid zzbua;
    private /* synthetic */ AtomicReference zzbub;

    zzcip(zzcid zzcid, AtomicReference atomicReference, boolean z) {
        this.zzbua = zzcid;
        this.zzbub = atomicReference;
        this.zzbtw = z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r5 = this;
        r1 = r5.zzbub;
        monitor-enter(r1);
        r0 = r5.zzbua;	 Catch:{ RemoteException -> 0x0046 }
        r0 = r0.zzbtU;	 Catch:{ RemoteException -> 0x0046 }
        if (r0 != 0) goto L_0x0021;
    L_0x000b:
        r0 = r5.zzbua;	 Catch:{ RemoteException -> 0x0046 }
        r0 = r0.zzwF();	 Catch:{ RemoteException -> 0x0046 }
        r0 = r0.zzyx();	 Catch:{ RemoteException -> 0x0046 }
        r2 = "Failed to get user properties";
        r0.log(r2);	 Catch:{ RemoteException -> 0x0046 }
        r0 = r5.zzbub;	 Catch:{ all -> 0x0043 }
        r0.notify();	 Catch:{ all -> 0x0043 }
        monitor-exit(r1);	 Catch:{ all -> 0x0043 }
    L_0x0020:
        return;
    L_0x0021:
        r2 = r5.zzbub;	 Catch:{ RemoteException -> 0x0046 }
        r3 = r5.zzbua;	 Catch:{ RemoteException -> 0x0046 }
        r3 = r3.zzwu();	 Catch:{ RemoteException -> 0x0046 }
        r4 = 0;
        r3 = r3.zzdV(r4);	 Catch:{ RemoteException -> 0x0046 }
        r4 = r5.zzbtw;	 Catch:{ RemoteException -> 0x0046 }
        r0 = r0.zza(r3, r4);	 Catch:{ RemoteException -> 0x0046 }
        r2.set(r0);	 Catch:{ RemoteException -> 0x0046 }
        r0 = r5.zzbua;	 Catch:{ RemoteException -> 0x0046 }
        r0.zzkP();	 Catch:{ RemoteException -> 0x0046 }
        r0 = r5.zzbub;	 Catch:{ all -> 0x0043 }
        r0.notify();	 Catch:{ all -> 0x0043 }
    L_0x0041:
        monitor-exit(r1);	 Catch:{ all -> 0x0043 }
        goto L_0x0020;
    L_0x0043:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0043 }
        throw r0;
    L_0x0046:
        r0 = move-exception;
        r2 = r5.zzbua;	 Catch:{ all -> 0x005c }
        r2 = r2.zzwF();	 Catch:{ all -> 0x005c }
        r2 = r2.zzyx();	 Catch:{ all -> 0x005c }
        r3 = "Failed to get user properties";
        r2.zzj(r3, r0);	 Catch:{ all -> 0x005c }
        r0 = r5.zzbub;	 Catch:{ all -> 0x0043 }
        r0.notify();	 Catch:{ all -> 0x0043 }
        goto L_0x0041;
    L_0x005c:
        r0 = move-exception;
        r2 = r5.zzbub;	 Catch:{ all -> 0x0043 }
        r2.notify();	 Catch:{ all -> 0x0043 }
        throw r0;	 Catch:{ all -> 0x0043 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcip.run():void");
    }
}
