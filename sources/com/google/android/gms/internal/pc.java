package com.google.android.gms.internal;

final class pc {
    private final Long zzcbA;
    private final pf zzcbx;
    private final pa zzcby;
    private final om zzcbz;

    private pc(pf pfVar, pa paVar, Long l, om omVar) {
        this.zzcbx = pfVar;
        this.zzcby = paVar;
        this.zzcbz = omVar;
        this.zzcbA = l;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzcby.toString());
        String valueOf2 = String.valueOf(this.zzcbA);
        return new StringBuilder((String.valueOf(valueOf).length() + 8) + String.valueOf(valueOf2).length()).append(valueOf).append(" (Tag: ").append(valueOf2).append(")").toString();
    }

    public final pa zzGm() {
        return this.zzcby;
    }

    public final Long zzGn() {
        return this.zzcbA;
    }

    public final om zzGo() {
        return this.zzcbz;
    }
}
