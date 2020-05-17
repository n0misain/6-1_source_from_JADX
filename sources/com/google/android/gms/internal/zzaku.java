package com.google.android.gms.internal;

final class zzaku implements Runnable {
    private /* synthetic */ zzaks zzacC;
    private /* synthetic */ int zzacD;
    private /* synthetic */ int zzacE;
    private /* synthetic */ boolean zzacF;
    private /* synthetic */ boolean zzacG;

    zzaku(zzaks zzaks, int i, int i2, boolean z, boolean z2) {
        this.zzacC = zzaks;
        this.zzacD = i;
        this.zzacE = i2;
        this.zzacF = z;
        this.zzacG = z2;
    }

    public final void run() {
        boolean z = false;
        synchronized (this.zzacC.mLock) {
            boolean z2 = this.zzacD != this.zzacE;
            boolean z3 = !this.zzacC.zzacx && this.zzacE == 1;
            boolean z4 = z2 && this.zzacE == 1;
            boolean z5 = z2 && this.zzacE == 2;
            boolean z6 = z2 && this.zzacE == 3;
            z2 = this.zzacF != this.zzacG;
            zzaks zzaks = this.zzacC;
            if (this.zzacC.zzacx || z3) {
                z = true;
            }
            zzaks.zzacx = z;
            if (this.zzacC.zzacw == null) {
                return;
            }
            if (z3) {
                try {
                    this.zzacC.zzacw.onVideoStart();
                } catch (Throwable e) {
                    zzajc.zzc("Unable to call onVideoStart()", e);
                }
            }
            if (z4) {
                try {
                    this.zzacC.zzacw.onVideoPlay();
                } catch (Throwable e2) {
                    zzajc.zzc("Unable to call onVideoPlay()", e2);
                }
            }
            if (z5) {
                try {
                    this.zzacC.zzacw.onVideoPause();
                } catch (Throwable e22) {
                    zzajc.zzc("Unable to call onVideoPause()", e22);
                }
            }
            if (z6) {
                try {
                    this.zzacC.zzacw.onVideoEnd();
                } catch (Throwable e222) {
                    zzajc.zzc("Unable to call onVideoEnd()", e222);
                }
            }
            if (z2) {
                try {
                    this.zzacC.zzacw.onVideoMute(this.zzacG);
                } catch (Throwable e2222) {
                    zzajc.zzc("Unable to call onVideoMute()", e2222);
                }
            }
        }
    }
}
