package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcka extends adj<zzcka> {
    public long[] zzbwe;
    public long[] zzbwf;

    public zzcka() {
        this.zzbwe = ads.zzcsD;
        this.zzbwf = ads.zzcsD;
        this.zzcso = null;
        this.zzcsx = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcka)) {
            return false;
        }
        zzcka zzcka = (zzcka) obj;
        return !adn.equals(this.zzbwe, zzcka.zzbwe) ? false : !adn.equals(this.zzbwf, zzcka.zzbwf) ? false : (this.zzcso == null || this.zzcso.isEmpty()) ? zzcka.zzcso == null || zzcka.zzcso.isEmpty() : this.zzcso.equals(zzcka.zzcso);
    }

    public final int hashCode() {
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + adn.hashCode(this.zzbwe)) * 31) + adn.hashCode(this.zzbwf)) * 31;
        int hashCode2 = (this.zzcso == null || this.zzcso.isEmpty()) ? 0 : this.zzcso.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ adp zza(adg adg) throws IOException {
        while (true) {
            int zzLA = adg.zzLA();
            int zzb;
            Object obj;
            int zzcn;
            Object obj2;
            switch (zzLA) {
                case 0:
                    break;
                case 8:
                    zzb = ads.zzb(adg, 8);
                    zzLA = this.zzbwe == null ? 0 : this.zzbwe.length;
                    obj = new long[(zzb + zzLA)];
                    if (zzLA != 0) {
                        System.arraycopy(this.zzbwe, 0, obj, 0, zzLA);
                    }
                    while (zzLA < obj.length - 1) {
                        obj[zzLA] = adg.zzLG();
                        adg.zzLA();
                        zzLA++;
                    }
                    obj[zzLA] = adg.zzLG();
                    this.zzbwe = obj;
                    continue;
                case 10:
                    zzcn = adg.zzcn(adg.zzLF());
                    zzb = adg.getPosition();
                    zzLA = 0;
                    while (adg.zzLK() > 0) {
                        adg.zzLG();
                        zzLA++;
                    }
                    adg.zzcp(zzb);
                    zzb = this.zzbwe == null ? 0 : this.zzbwe.length;
                    obj2 = new long[(zzLA + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzbwe, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = adg.zzLG();
                        zzb++;
                    }
                    this.zzbwe = obj2;
                    adg.zzco(zzcn);
                    continue;
                case 16:
                    zzb = ads.zzb(adg, 16);
                    zzLA = this.zzbwf == null ? 0 : this.zzbwf.length;
                    obj = new long[(zzb + zzLA)];
                    if (zzLA != 0) {
                        System.arraycopy(this.zzbwf, 0, obj, 0, zzLA);
                    }
                    while (zzLA < obj.length - 1) {
                        obj[zzLA] = adg.zzLG();
                        adg.zzLA();
                        zzLA++;
                    }
                    obj[zzLA] = adg.zzLG();
                    this.zzbwf = obj;
                    continue;
                case 18:
                    zzcn = adg.zzcn(adg.zzLF());
                    zzb = adg.getPosition();
                    zzLA = 0;
                    while (adg.zzLK() > 0) {
                        adg.zzLG();
                        zzLA++;
                    }
                    adg.zzcp(zzb);
                    zzb = this.zzbwf == null ? 0 : this.zzbwf.length;
                    obj2 = new long[(zzLA + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzbwf, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = adg.zzLG();
                        zzb++;
                    }
                    this.zzbwf = obj2;
                    adg.zzco(zzcn);
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
        int i = 0;
        if (this.zzbwe != null && this.zzbwe.length > 0) {
            for (long zza : this.zzbwe) {
                adh.zza(1, zza);
            }
        }
        if (this.zzbwf != null && this.zzbwf.length > 0) {
            while (i < this.zzbwf.length) {
                adh.zza(2, this.zzbwf[i]);
                i++;
            }
        }
        super.zza(adh);
    }

    protected final int zzn() {
        int i;
        int i2;
        int i3 = 0;
        int zzn = super.zzn();
        if (this.zzbwe == null || this.zzbwe.length <= 0) {
            i = zzn;
        } else {
            i2 = 0;
            for (long zzaP : this.zzbwe) {
                i2 += adh.zzaP(zzaP);
            }
            i = (zzn + i2) + (this.zzbwe.length * 1);
        }
        if (this.zzbwf == null || this.zzbwf.length <= 0) {
            return i;
        }
        i2 = 0;
        while (i3 < this.zzbwf.length) {
            i2 += adh.zzaP(this.zzbwf[i3]);
            i3++;
        }
        return (i + i2) + (this.zzbwf.length * 1);
    }
}
