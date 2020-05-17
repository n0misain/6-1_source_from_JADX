package com.google.android.gms.internal;

import java.io.IOException;

public final class zzba extends adj<zzba> {
    private int[] zzcA;
    private Long zzcB;
    private Long zzcx;
    private Integer zzcy;
    private Boolean zzcz;

    public zzba() {
        this.zzcx = null;
        this.zzcy = null;
        this.zzcz = null;
        this.zzcA = ads.zzcsC;
        this.zzcB = null;
        this.zzcsx = -1;
    }

    public final /* synthetic */ adp zza(adg adg) throws IOException {
        while (true) {
            int zzLA = adg.zzLA();
            int zzb;
            switch (zzLA) {
                case 0:
                    break;
                case 8:
                    this.zzcx = Long.valueOf(adg.zzLG());
                    continue;
                case 16:
                    this.zzcy = Integer.valueOf(adg.zzLF());
                    continue;
                case 24:
                    this.zzcz = Boolean.valueOf(adg.zzLD());
                    continue;
                case 32:
                    zzb = ads.zzb(adg, 32);
                    zzLA = this.zzcA == null ? 0 : this.zzcA.length;
                    Object obj = new int[(zzb + zzLA)];
                    if (zzLA != 0) {
                        System.arraycopy(this.zzcA, 0, obj, 0, zzLA);
                    }
                    while (zzLA < obj.length - 1) {
                        obj[zzLA] = adg.zzLF();
                        adg.zzLA();
                        zzLA++;
                    }
                    obj[zzLA] = adg.zzLF();
                    this.zzcA = obj;
                    continue;
                case 34:
                    int zzcn = adg.zzcn(adg.zzLF());
                    zzb = adg.getPosition();
                    zzLA = 0;
                    while (adg.zzLK() > 0) {
                        adg.zzLF();
                        zzLA++;
                    }
                    adg.zzcp(zzb);
                    zzb = this.zzcA == null ? 0 : this.zzcA.length;
                    Object obj2 = new int[(zzLA + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzcA, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = adg.zzLF();
                        zzb++;
                    }
                    this.zzcA = obj2;
                    adg.zzco(zzcn);
                    continue;
                case 40:
                    this.zzcB = Long.valueOf(adg.zzLG());
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
        if (this.zzcx != null) {
            adh.zzb(1, this.zzcx.longValue());
        }
        if (this.zzcy != null) {
            adh.zzr(2, this.zzcy.intValue());
        }
        if (this.zzcz != null) {
            adh.zzk(3, this.zzcz.booleanValue());
        }
        if (this.zzcA != null && this.zzcA.length > 0) {
            for (int zzr : this.zzcA) {
                adh.zzr(4, zzr);
            }
        }
        if (this.zzcB != null) {
            adh.zza(5, this.zzcB.longValue());
        }
        super.zza(adh);
    }

    protected final int zzn() {
        int i = 0;
        int zzn = super.zzn();
        if (this.zzcx != null) {
            zzn += adh.zze(1, this.zzcx.longValue());
        }
        if (this.zzcy != null) {
            zzn += adh.zzs(2, this.zzcy.intValue());
        }
        if (this.zzcz != null) {
            this.zzcz.booleanValue();
            zzn += adh.zzct(3) + 1;
        }
        if (this.zzcA != null && this.zzcA.length > 0) {
            int i2 = 0;
            while (i < this.zzcA.length) {
                i2 += adh.zzcr(this.zzcA[i]);
                i++;
            }
            zzn = (zzn + i2) + (this.zzcA.length * 1);
        }
        if (this.zzcB == null) {
            return zzn;
        }
        return zzn + (adh.zzct(5) + adh.zzaP(this.zzcB.longValue()));
    }
}
