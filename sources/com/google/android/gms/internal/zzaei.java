package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbs;
import java.util.Map;

final class zzaei implements Runnable {
    private /* synthetic */ zzaeg zzXf;
    private /* synthetic */ zzajm zzXg;

    zzaei(zzaeg zzaeg, zzajm zzajm) {
        this.zzXf = zzaeg;
        this.zzXg = zzajm;
    }

    public final void run() {
        Throwable th;
        try {
            this.zzXf.zzi((Map) this.zzXg.get());
            if (this.zzXf.zzXa) {
                synchronized (this.zzXf.mLock) {
                    this.zzXf.zzWX.zzcsJ = Integer.valueOf(9);
                }
            }
            this.zzXf.send();
            return;
        } catch (Throwable e) {
            th = e;
        } catch (Throwable e2) {
            th = e2;
        } catch (Throwable e22) {
            th = e22;
        }
        String str = "Failed to get SafeBrowsing metadata";
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzGe)).booleanValue()) {
            zzajc.zza(str, th);
        }
    }
}
