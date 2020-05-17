package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbb;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzzn
public final class zzyf extends zzafp {
    private final Object mLock;
    private final zzxy zzQP;
    private final zzafg zzQQ;
    private final zzaai zzQR;
    private final zzyn zzRg;
    private Future<zzaff> zzRh;

    public zzyf(Context context, zzbb zzbb, zzafg zzafg, zzcu zzcu, zzxy zzxy, zznb zznb) {
        this(zzafg, zzxy, new zzyn(context, zzbb, new zzaie(context), zzcu, zzafg, zznb));
    }

    private zzyf(zzafg zzafg, zzxy zzxy, zzyn zzyn) {
        this.mLock = new Object();
        this.zzQQ = zzafg;
        this.zzQR = zzafg.zzXY;
        this.zzQP = zzxy;
        this.zzRg = zzyn;
    }

    public final void onStop() {
        synchronized (this.mLock) {
            if (this.zzRh != null) {
                this.zzRh.cancel(true);
            }
        }
    }

    public final void zzbd() {
        zzaff zzaff;
        int i = -2;
        try {
            synchronized (this.mLock) {
                this.zzRh = zzagt.zza(this.zzRg);
            }
            zzaff = (zzaff) this.zzRh.get(60000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            zzajc.zzaT("Timed out waiting for native ad.");
            i = 2;
            this.zzRh.cancel(true);
            zzaff = null;
        } catch (ExecutionException e2) {
            i = 0;
            zzaff = null;
        } catch (InterruptedException e3) {
            i = 0;
            zzaff = null;
        } catch (CancellationException e4) {
            i = 0;
            zzaff = null;
        }
        if (zzaff == null) {
            zzaff = new zzaff(this.zzQQ.zzUj.zzSz, null, null, i, null, null, this.zzQR.orientation, this.zzQR.zzMg, this.zzQQ.zzUj.zzSC, false, null, null, null, null, null, this.zzQR.zzTp, this.zzQQ.zzvX, this.zzQR.zzTn, this.zzQQ.zzXR, this.zzQR.zzTs, this.zzQR.zzTt, this.zzQQ.zzXL, null, null, null, null, this.zzQQ.zzXY.zzTG, this.zzQQ.zzXY.zzTH, null, null, this.zzQR.zzTK, this.zzQQ.zzXX);
        }
        zzagz.zzZr.post(new zzyg(this, zzaff));
    }
}
