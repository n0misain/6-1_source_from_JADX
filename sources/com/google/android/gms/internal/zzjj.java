package com.google.android.gms.internal;

import java.util.Random;

@zzzn
public final class zzjj extends zzkl {
    private Object mLock = new Object();
    private final Random zzAO = new Random();
    private long zzAP;

    public zzjj() {
        zzdu();
    }

    public final long getValue() {
        return this.zzAP;
    }

    public final void zzdu() {
        synchronized (this.mLock) {
            int i = 3;
            long j = 0;
            while (true) {
                i--;
                if (i <= 0) {
                    break;
                }
                j = ((long) this.zzAO.nextInt()) + 2147483648L;
                if (j != this.zzAP && j != 0) {
                    break;
                }
            }
            this.zzAP = j;
        }
    }
}
