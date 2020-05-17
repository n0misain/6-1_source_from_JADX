package com.google.android.gms.internal;

public final class uj {
    private static final uz<Boolean> zzcgd = new uk();
    private static final uz<Boolean> zzcge = new ul();
    private static final uv<Boolean> zzcgf = new uv(Boolean.valueOf(true));
    private static final uv<Boolean> zzcgg = new uv(Boolean.valueOf(false));
    private final uv<Boolean> zzcgc;

    public uj() {
        this.zzcgc = uv.zzHR();
    }

    private uj(uv<Boolean> uvVar) {
        this.zzcgc = uvVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof uj)) {
            return false;
        }
        return this.zzcgc.equals(((uj) obj).zzcgc);
    }

    public final int hashCode() {
        return this.zzcgc.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzcgc.toString());
        return new StringBuilder(String.valueOf(valueOf).length() + 14).append("{PruneForest:").append(valueOf).append("}").toString();
    }

    public final boolean zzHN() {
        return this.zzcgc.zzb(zzcge);
    }

    public final <T> T zza(T t, uy<Void, T> uyVar) {
        return this.zzcgc.zzb((Object) t, new um(this, uyVar));
    }

    public final uj zzd(wp wpVar) {
        uv zze = this.zzcgc.zze(wpVar);
        uv uvVar = zze == null ? new uv((Boolean) this.zzcgc.getValue()) : (zze.getValue() != null || this.zzcgc.getValue() == null) ? zze : zze.zzb(qr.zzGZ(), (Boolean) this.zzcgc.getValue());
        return new uj(uvVar);
    }

    public final boolean zzv(qr qrVar) {
        Boolean bool = (Boolean) this.zzcgc.zzG(qrVar);
        return bool != null && bool.booleanValue();
    }

    public final boolean zzw(qr qrVar) {
        Boolean bool = (Boolean) this.zzcgc.zzG(qrVar);
        return (bool == null || bool.booleanValue()) ? false : true;
    }

    public final uj zzx(qr qrVar) {
        if (this.zzcgc.zzb(qrVar, zzcgd) == null) {
            return this.zzcgc.zzb(qrVar, zzcge) != null ? this : new uj(this.zzcgc.zza(qrVar, zzcgf));
        } else {
            throw new IllegalArgumentException("Can't prune path that was kept previously!");
        }
    }

    public final uj zzy(qr qrVar) {
        return this.zzcgc.zzb(qrVar, zzcgd) != null ? this : new uj(this.zzcgc.zza(qrVar, zzcgg));
    }
}
