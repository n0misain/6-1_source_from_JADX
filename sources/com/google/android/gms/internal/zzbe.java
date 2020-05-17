package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbe extends adj<zzbe> {
    public byte[] zzcE;
    public byte[][] zzcJ;
    private Integer zzcK;
    private Integer zzcL;

    public zzbe() {
        this.zzcJ = ads.zzcsH;
        this.zzcE = null;
        this.zzcsx = -1;
    }

    public final /* synthetic */ adp zza(adg adg) throws IOException {
        while (true) {
            int zzLA = adg.zzLA();
            int zzb;
            int zzLF;
            switch (zzLA) {
                case 0:
                    break;
                case 10:
                    zzb = ads.zzb(adg, 10);
                    zzLA = this.zzcJ == null ? 0 : this.zzcJ.length;
                    Object obj = new byte[(zzb + zzLA)][];
                    if (zzLA != 0) {
                        System.arraycopy(this.zzcJ, 0, obj, 0, zzLA);
                    }
                    while (zzLA < obj.length - 1) {
                        obj[zzLA] = adg.readBytes();
                        adg.zzLA();
                        zzLA++;
                    }
                    obj[zzLA] = adg.readBytes();
                    this.zzcJ = obj;
                    continue;
                case 18:
                    this.zzcE = adg.readBytes();
                    continue;
                case 24:
                    zzb = adg.getPosition();
                    zzLF = adg.zzLF();
                    switch (zzLF) {
                        case 0:
                        case 1:
                            this.zzcK = Integer.valueOf(zzLF);
                            break;
                        default:
                            adg.zzcp(zzb);
                            zza(adg, zzLA);
                            continue;
                    }
                case 32:
                    zzb = adg.getPosition();
                    zzLF = adg.zzLF();
                    switch (zzLF) {
                        case 0:
                        case 1:
                        case 2:
                            this.zzcL = Integer.valueOf(zzLF);
                            break;
                        default:
                            adg.zzcp(zzb);
                            zza(adg, zzLA);
                            continue;
                    }
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
        if (this.zzcJ != null && this.zzcJ.length > 0) {
            for (byte[] bArr : this.zzcJ) {
                if (bArr != null) {
                    adh.zzb(1, bArr);
                }
            }
        }
        if (this.zzcE != null) {
            adh.zzb(2, this.zzcE);
        }
        if (this.zzcK != null) {
            adh.zzr(3, this.zzcK.intValue());
        }
        if (this.zzcL != null) {
            adh.zzr(4, this.zzcL.intValue());
        }
        super.zza(adh);
    }

    protected final int zzn() {
        int i = 0;
        int zzn = super.zzn();
        if (this.zzcJ == null || this.zzcJ.length <= 0) {
            i = zzn;
        } else {
            int i2 = 0;
            int i3 = 0;
            while (i < this.zzcJ.length) {
                byte[] bArr = this.zzcJ[i];
                if (bArr != null) {
                    i3++;
                    i2 += adh.zzJ(bArr);
                }
                i++;
            }
            i = (zzn + i2) + (i3 * 1);
        }
        if (this.zzcE != null) {
            i += adh.zzc(2, this.zzcE);
        }
        if (this.zzcK != null) {
            i += adh.zzs(3, this.zzcK.intValue());
        }
        return this.zzcL != null ? i + adh.zzs(4, this.zzcL.intValue()) : i;
    }
}
