package com.google.android.gms.internal;

import java.io.IOException;

public final class adw extends adj<adw> {
    private byte[] body;
    private adx zzcta;
    public adv[] zzctb;
    private byte[] zzctc;
    private Integer zzctd;

    public adw() {
        this.zzcta = null;
        this.zzctb = adv.zzLW();
        this.body = null;
        this.zzctc = null;
        this.zzctd = null;
        this.zzcso = null;
        this.zzcsx = -1;
    }

    public final /* synthetic */ adp zza(adg adg) throws IOException {
        while (true) {
            int zzLA = adg.zzLA();
            switch (zzLA) {
                case 0:
                    break;
                case 10:
                    if (this.zzcta == null) {
                        this.zzcta = new adx();
                    }
                    adg.zza(this.zzcta);
                    continue;
                case 18:
                    int zzb = ads.zzb(adg, 18);
                    zzLA = this.zzctb == null ? 0 : this.zzctb.length;
                    Object obj = new adv[(zzb + zzLA)];
                    if (zzLA != 0) {
                        System.arraycopy(this.zzctb, 0, obj, 0, zzLA);
                    }
                    while (zzLA < obj.length - 1) {
                        obj[zzLA] = new adv();
                        adg.zza(obj[zzLA]);
                        adg.zzLA();
                        zzLA++;
                    }
                    obj[zzLA] = new adv();
                    adg.zza(obj[zzLA]);
                    this.zzctb = obj;
                    continue;
                case 26:
                    this.body = adg.readBytes();
                    continue;
                case 34:
                    this.zzctc = adg.readBytes();
                    continue;
                case 40:
                    this.zzctd = Integer.valueOf(adg.zzLC());
                    continue;
                default:
                    if (!super.zza(adg, zzLA)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(adh adh) throws IOException {
        if (this.zzcta != null) {
            adh.zza(1, this.zzcta);
        }
        if (this.zzctb != null && this.zzctb.length > 0) {
            for (adp adp : this.zzctb) {
                if (adp != null) {
                    adh.zza(2, adp);
                }
            }
        }
        if (this.body != null) {
            adh.zzb(3, this.body);
        }
        if (this.zzctc != null) {
            adh.zzb(4, this.zzctc);
        }
        if (this.zzctd != null) {
            adh.zzr(5, this.zzctd.intValue());
        }
        super.zza(adh);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.zzcta != null) {
            zzn += adh.zzb(1, this.zzcta);
        }
        if (this.zzctb != null && this.zzctb.length > 0) {
            int i = zzn;
            for (adp adp : this.zzctb) {
                if (adp != null) {
                    i += adh.zzb(2, adp);
                }
            }
            zzn = i;
        }
        if (this.body != null) {
            zzn += adh.zzc(3, this.body);
        }
        if (this.zzctc != null) {
            zzn += adh.zzc(4, this.zzctc);
        }
        return this.zzctd != null ? zzn + adh.zzs(5, this.zzctd.intValue()) : zzn;
    }
}
