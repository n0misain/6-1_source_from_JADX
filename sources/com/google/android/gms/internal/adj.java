package com.google.android.gms.internal;

import java.io.IOException;

public abstract class adj<M extends adj<M>> extends adp {
    protected adl zzcso;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzLN();
    }

    public M zzLN() throws CloneNotSupportedException {
        adj adj = (adj) super.zzLO();
        adn.zza(this, adj);
        return adj;
    }

    public /* synthetic */ adp zzLO() throws CloneNotSupportedException {
        return (adj) clone();
    }

    public final <T> T zza(adk<M, T> adk) {
        if (this.zzcso == null) {
            return null;
        }
        adm zzcx = this.zzcso.zzcx(adk.tag >>> 3);
        return zzcx != null ? zzcx.zzb(adk) : null;
    }

    public void zza(adh adh) throws IOException {
        if (this.zzcso != null) {
            for (int i = 0; i < this.zzcso.size(); i++) {
                this.zzcso.zzcy(i).zza(adh);
            }
        }
    }

    protected final boolean zza(adg adg, int i) throws IOException {
        int position = adg.getPosition();
        if (!adg.zzcm(i)) {
            return false;
        }
        int i2 = i >>> 3;
        adr adr = new adr(i, adg.zzp(position, adg.getPosition() - position));
        adm adm = null;
        if (this.zzcso == null) {
            this.zzcso = new adl();
        } else {
            adm = this.zzcso.zzcx(i2);
        }
        if (adm == null) {
            adm = new adm();
            this.zzcso.zza(i2, adm);
        }
        adm.zza(adr);
        return true;
    }

    protected int zzn() {
        int i = 0;
        if (this.zzcso == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.zzcso.size()) {
            i2 += this.zzcso.zzcy(i).zzn();
            i++;
        }
        return i2;
    }
}
