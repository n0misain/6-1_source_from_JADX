package com.google.android.gms.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

final class zzchw implements Callable<String> {
    private /* synthetic */ zzchl zzbtt;

    zzchw(zzchl zzchl) {
        this.zzbtt = zzchl;
    }

    public final /* synthetic */ Object call() throws Exception {
        Object zzyH = this.zzbtt.zzwG().zzyH();
        if (zzyH == null) {
            zzyH = this.zzbtt.zzwt().zzac(120000);
            if (zzyH == null) {
                throw new TimeoutException();
            }
            this.zzbtt.zzwG().zzee(zzyH);
        }
        return zzyH;
    }
}
