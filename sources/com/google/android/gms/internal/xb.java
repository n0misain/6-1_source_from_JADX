package com.google.android.gms.internal;

import java.util.Map;

public final class xb extends xh<xb> {
    private Map<Object, Object> zzcih;

    public xb(Map<Object, Object> map, xm xmVar) {
        super(xmVar);
        this.zzcih = map;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof xb)) {
            return false;
        }
        xb xbVar = (xb) obj;
        return this.zzcih.equals(xbVar.zzcih) && this.zzchS.equals(xbVar.zzchS);
    }

    public final Object getValue() {
        return this.zzcih;
    }

    public final int hashCode() {
        return this.zzcih.hashCode() + this.zzchS.hashCode();
    }

    protected final xj zzII() {
        return xj.DeferredValue;
    }

    public final String zza(xo xoVar) {
        String valueOf = String.valueOf(zzb(xoVar));
        String valueOf2 = String.valueOf(this.zzcih);
        return new StringBuilder((String.valueOf(valueOf).length() + 14) + String.valueOf(valueOf2).length()).append(valueOf).append("deferredValue:").append(valueOf2).toString();
    }

    public final /* synthetic */ xm zzf(xm xmVar) {
        return new xb(this.zzcih, xmVar);
    }
}
