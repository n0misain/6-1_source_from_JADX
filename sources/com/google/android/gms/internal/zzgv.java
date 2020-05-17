package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbs;

@zzzn
public final class zzgv {
    private final Object zzyn = new Object();
    private zzgw zzyo = null;
    private boolean zzyp = false;

    @Nullable
    public final Activity getActivity() {
        Activity activity;
        synchronized (this.zzyn) {
            if (this.zzyo != null) {
                activity = this.zzyo.getActivity();
            } else {
                activity = null;
            }
        }
        return activity;
    }

    @Nullable
    public final Context getContext() {
        Context context;
        synchronized (this.zzyn) {
            if (this.zzyo != null) {
                context = this.zzyo.getContext();
            } else {
                context = null;
            }
        }
        return context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r5) {
        /*
        r4 = this;
        r2 = r4.zzyn;
        monitor-enter(r2);
        r0 = r4.zzyp;	 Catch:{ all -> 0x0032 }
        if (r0 != 0) goto L_0x0048;
    L_0x0007:
        r0 = com.google.android.gms.internal.zzmo.zzDJ;	 Catch:{ all -> 0x0032 }
        r1 = com.google.android.gms.ads.internal.zzbs.zzbL();	 Catch:{ all -> 0x0032 }
        r0 = r1.zzd(r0);	 Catch:{ all -> 0x0032 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x0032 }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x0032 }
        if (r0 != 0) goto L_0x001b;
    L_0x0019:
        monitor-exit(r2);	 Catch:{ all -> 0x0032 }
    L_0x001a:
        return;
    L_0x001b:
        r1 = 0;
        r0 = r5.getApplicationContext();	 Catch:{ all -> 0x0032 }
        if (r0 != 0) goto L_0x0023;
    L_0x0022:
        r0 = r5;
    L_0x0023:
        r3 = r0 instanceof android.app.Application;	 Catch:{ all -> 0x0032 }
        if (r3 == 0) goto L_0x004a;
    L_0x0027:
        r0 = (android.app.Application) r0;	 Catch:{ all -> 0x0032 }
    L_0x0029:
        if (r0 != 0) goto L_0x0035;
    L_0x002b:
        r0 = "Can not cast Context to Application";
        com.google.android.gms.internal.zzajc.zzaT(r0);	 Catch:{ all -> 0x0032 }
        monitor-exit(r2);	 Catch:{ all -> 0x0032 }
        goto L_0x001a;
    L_0x0032:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0032 }
        throw r0;
    L_0x0035:
        r1 = r4.zzyo;	 Catch:{ all -> 0x0032 }
        if (r1 != 0) goto L_0x0040;
    L_0x0039:
        r1 = new com.google.android.gms.internal.zzgw;	 Catch:{ all -> 0x0032 }
        r1.<init>();	 Catch:{ all -> 0x0032 }
        r4.zzyo = r1;	 Catch:{ all -> 0x0032 }
    L_0x0040:
        r1 = r4.zzyo;	 Catch:{ all -> 0x0032 }
        r1.zza(r0, r5);	 Catch:{ all -> 0x0032 }
        r0 = 1;
        r4.zzyp = r0;	 Catch:{ all -> 0x0032 }
    L_0x0048:
        monitor-exit(r2);	 Catch:{ all -> 0x0032 }
        goto L_0x001a;
    L_0x004a:
        r0 = r1;
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgv.initialize(android.content.Context):void");
    }

    public final void zza(zzgy zzgy) {
        synchronized (this.zzyn) {
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzDJ)).booleanValue()) {
                if (this.zzyo == null) {
                    this.zzyo = new zzgw();
                }
                this.zzyo.zza(zzgy);
                return;
            }
        }
    }
}
