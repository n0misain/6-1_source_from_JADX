package com.google.android.gms.internal;

public final class xu extends xh<xu> {
    private final String value;

    public xu(String str, xm xmVar) {
        super(xmVar);
        this.value = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof xu)) {
            return false;
        }
        xu xuVar = (xu) obj;
        return this.value.equals(xuVar.value) && this.zzchS.equals(xuVar.zzchS);
    }

    public final Object getValue() {
        return this.value;
    }

    public final int hashCode() {
        return this.value.hashCode() + this.zzchS.hashCode();
    }

    protected final xj zzII() {
        return xj.String;
    }

    protected final /* synthetic */ int zza(xh xhVar) {
        return this.value.compareTo(((xu) xhVar).value);
    }

    public final String zza(xo xoVar) {
        String valueOf;
        String str;
        switch (xv.zzcio[xoVar.ordinal()]) {
            case 1:
                valueOf = String.valueOf(zzb(xoVar));
                str = this.value;
                return new StringBuilder((String.valueOf(valueOf).length() + 7) + String.valueOf(str).length()).append(valueOf).append("string:").append(str).toString();
            case 2:
                valueOf = String.valueOf(zzb(xoVar));
                str = String.valueOf(zd.zzgZ(this.value));
                return new StringBuilder((String.valueOf(valueOf).length() + 7) + String.valueOf(str).length()).append(valueOf).append("string:").append(str).toString();
            default:
                str = String.valueOf(xoVar);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 38).append("Invalid hash version for string node: ").append(str).toString());
        }
    }

    public final /* synthetic */ xm zzf(xm xmVar) {
        return new xu(this.value, xmVar);
    }
}
