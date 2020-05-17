package com.google.android.gms.internal;

import java.util.concurrent.ScheduledExecutorService;

public final class ps {
    private final wl zzbZE;
    private long zzccm = 1000;
    private double zzcco = 0.5d;
    private double zzccp = 1.3d;
    private final ScheduledExecutorService zzccu;
    private long zzccv = 30000;

    public ps(ScheduledExecutorService scheduledExecutorService, wm wmVar, String str) {
        this.zzccu = scheduledExecutorService;
        this.zzbZE = new wl(wmVar, str);
    }

    public final pq zzGC() {
        return new pq(this.zzccu, this.zzbZE, this.zzccm, this.zzccv, this.zzccp, this.zzcco);
    }

    public final ps zzas(long j) {
        this.zzccm = 1000;
        return this;
    }

    public final ps zzat(long j) {
        this.zzccv = 30000;
        return this;
    }

    public final ps zzh(double d) {
        this.zzccp = 1.3d;
        return this;
    }

    public final ps zzi(double d) {
        this.zzcco = 0.7d;
        return this;
    }
}
