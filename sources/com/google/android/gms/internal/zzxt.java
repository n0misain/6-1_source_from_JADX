package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;

@zzzn
public abstract class zzxt extends zzafp {
    protected final Context mContext;
    protected final Object mLock = new Object();
    protected final zzxy zzQP;
    protected final zzafg zzQQ;
    protected zzaai zzQR;
    protected final Object zzQT = new Object();

    protected zzxt(Context context, zzafg zzafg, zzxy zzxy) {
        super(true);
        this.mContext = context;
        this.zzQQ = zzafg;
        this.zzQR = zzafg.zzXY;
        this.zzQP = zzxy;
    }

    public void onStop() {
    }

    public final void zzbd() {
        int errorCode;
        synchronized (this.mLock) {
            zzajc.zzaC("AdRendererBackgroundTask started.");
            int i = this.zzQQ.errorCode;
            try {
                zzd(SystemClock.elapsedRealtime());
            } catch (zzxw e) {
                errorCode = e.getErrorCode();
                if (errorCode == 3 || errorCode == -1) {
                    zzajc.zzaS(e.getMessage());
                } else {
                    zzajc.zzaT(e.getMessage());
                }
                if (this.zzQR == null) {
                    this.zzQR = new zzaai(errorCode);
                } else {
                    this.zzQR = new zzaai(errorCode, this.zzQR.zzMg);
                }
                zzagz.zzZr.post(new zzxu(this));
                i = errorCode;
            }
            zzagz.zzZr.post(new zzxv(this, zzs(i)));
        }
    }

    protected abstract void zzd(long j) throws zzxw;

    protected abstract zzaff zzs(int i);
}
