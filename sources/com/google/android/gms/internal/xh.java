package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class xh<T extends xh> implements xm {
    protected final xm zzchS;
    private String zzchT;

    xh(xm xmVar) {
        this.zzchS = xmVar;
    }

    private static int zza(xk xkVar, xc xcVar) {
        return Double.valueOf((double) ((Long) xkVar.getValue()).longValue()).compareTo((Double) xcVar.getValue());
    }

    public /* synthetic */ int compareTo(Object obj) {
        xm xmVar = (xm) obj;
        if (xmVar.isEmpty()) {
            return 1;
        }
        if (xmVar instanceof wr) {
            return -1;
        }
        if ((this instanceof xk) && (xmVar instanceof xc)) {
            return zza((xk) this, (xc) xmVar);
        }
        if ((this instanceof xc) && (xmVar instanceof xk)) {
            return zza((xk) xmVar, (xc) this) * -1;
        }
        xh xhVar = (xh) xmVar;
        xj zzII = zzII();
        Enum zzII2 = xhVar.zzII();
        return zzII.equals(zzII2) ? zza(xhVar) : zzII.compareTo(zzII2);
    }

    public final int getChildCount() {
        return 0;
    }

    public final Object getValue(boolean z) {
        if (!z || this.zzchS.isEmpty()) {
            return getValue();
        }
        Map hashMap = new HashMap();
        hashMap.put(".value", getValue());
        hashMap.put(".priority", this.zzchS.getValue());
        return hashMap;
    }

    public final boolean isEmpty() {
        return false;
    }

    public Iterator<xl> iterator() {
        return Collections.emptyList().iterator();
    }

    public String toString() {
        String obj = getValue(true).toString();
        return obj.length() <= 100 ? obj : String.valueOf(obj.substring(0, 100)).concat("...");
    }

    public final Iterator<xl> zzFz() {
        return Collections.emptyList().iterator();
    }

    protected abstract xj zzII();

    public final String zzIP() {
        if (this.zzchT == null) {
            this.zzchT = zd.zzgY(zza(xo.V1));
        }
        return this.zzchT;
    }

    public final boolean zzIQ() {
        return true;
    }

    public final xm zzIR() {
        return this.zzchS;
    }

    public final xm zzN(qr qrVar) {
        return qrVar.isEmpty() ? this : qrVar.zzHc().zzIN() ? this.zzchS : xd.zzJb();
    }

    protected abstract int zza(T t);

    protected final String zzb(xo xoVar) {
        switch (xi.zzcio[xoVar.ordinal()]) {
            case 1:
            case 2:
                if (this.zzchS.isEmpty()) {
                    return "";
                }
                String valueOf = String.valueOf(this.zzchS.zza(xoVar));
                return new StringBuilder(String.valueOf(valueOf).length() + 10).append("priority:").append(valueOf).append(":").toString();
            default:
                String valueOf2 = String.valueOf(xoVar);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf2).length() + 22).append("Unknown hash version: ").append(valueOf2).toString());
        }
    }

    public final xm zze(wp wpVar, xm xmVar) {
        return wpVar.zzIN() ? zzf(xmVar) : !xmVar.isEmpty() ? xd.zzJb().zze(wpVar, xmVar).zzf(this.zzchS) : this;
    }

    public final boolean zzk(wp wpVar) {
        return false;
    }

    public final wp zzl(wp wpVar) {
        return null;
    }

    public final xm zzl(qr qrVar, xm xmVar) {
        wp zzHc = qrVar.zzHc();
        return zzHc == null ? xmVar : (!xmVar.isEmpty() || zzHc.zzIN()) ? zze(zzHc, xd.zzJb().zzl(qrVar.zzHd(), xmVar)) : this;
    }

    public final xm zzm(wp wpVar) {
        return wpVar.zzIN() ? this.zzchS : xd.zzJb();
    }
}
