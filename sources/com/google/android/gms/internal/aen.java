package com.google.android.gms.internal;

import java.io.IOException;

public final class aen extends adp {
    public long zzaLt;
    public String zzcun;
    public String zzcuo;
    public long zzcup;
    public String zzcuq;
    public long zzcur;
    public String zzcus;
    public String zzcut;
    public String zzcuu;
    public String zzcuv;
    public String zzcuw;
    public int zzcux;
    public aem[] zzcuy;

    public aen() {
        this.zzcun = "";
        this.zzcuo = "";
        this.zzcup = 0;
        this.zzcuq = "";
        this.zzcur = 0;
        this.zzaLt = 0;
        this.zzcus = "";
        this.zzcut = "";
        this.zzcuu = "";
        this.zzcuv = "";
        this.zzcuw = "";
        this.zzcux = 0;
        this.zzcuy = aem.zzMh();
        this.zzcsx = -1;
    }

    public static aen zzL(byte[] bArr) throws ado {
        return (aen) adp.zza(new aen(), bArr);
    }

    public final /* synthetic */ adp zza(adg adg) throws IOException {
        while (true) {
            int zzLA = adg.zzLA();
            switch (zzLA) {
                case 0:
                    break;
                case 10:
                    this.zzcun = adg.readString();
                    continue;
                case 18:
                    this.zzcuo = adg.readString();
                    continue;
                case 24:
                    this.zzcup = adg.zzLB();
                    continue;
                case 34:
                    this.zzcuq = adg.readString();
                    continue;
                case 40:
                    this.zzcur = adg.zzLB();
                    continue;
                case 48:
                    this.zzaLt = adg.zzLB();
                    continue;
                case 58:
                    this.zzcus = adg.readString();
                    continue;
                case 66:
                    this.zzcut = adg.readString();
                    continue;
                case 74:
                    this.zzcuu = adg.readString();
                    continue;
                case 82:
                    this.zzcuv = adg.readString();
                    continue;
                case 90:
                    this.zzcuw = adg.readString();
                    continue;
                case 96:
                    this.zzcux = adg.zzLC();
                    continue;
                case 106:
                    int zzb = ads.zzb(adg, 106);
                    zzLA = this.zzcuy == null ? 0 : this.zzcuy.length;
                    Object obj = new aem[(zzb + zzLA)];
                    if (zzLA != 0) {
                        System.arraycopy(this.zzcuy, 0, obj, 0, zzLA);
                    }
                    while (zzLA < obj.length - 1) {
                        obj[zzLA] = new aem();
                        adg.zza(obj[zzLA]);
                        adg.zzLA();
                        zzLA++;
                    }
                    obj[zzLA] = new aem();
                    adg.zza(obj[zzLA]);
                    this.zzcuy = obj;
                    continue;
                default:
                    if (!adg.zzcm(zzLA)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(adh adh) throws IOException {
        if (!(this.zzcun == null || this.zzcun.equals(""))) {
            adh.zzl(1, this.zzcun);
        }
        if (!(this.zzcuo == null || this.zzcuo.equals(""))) {
            adh.zzl(2, this.zzcuo);
        }
        if (this.zzcup != 0) {
            adh.zzb(3, this.zzcup);
        }
        if (!(this.zzcuq == null || this.zzcuq.equals(""))) {
            adh.zzl(4, this.zzcuq);
        }
        if (this.zzcur != 0) {
            adh.zzb(5, this.zzcur);
        }
        if (this.zzaLt != 0) {
            adh.zzb(6, this.zzaLt);
        }
        if (!(this.zzcus == null || this.zzcus.equals(""))) {
            adh.zzl(7, this.zzcus);
        }
        if (!(this.zzcut == null || this.zzcut.equals(""))) {
            adh.zzl(8, this.zzcut);
        }
        if (!(this.zzcuu == null || this.zzcuu.equals(""))) {
            adh.zzl(9, this.zzcuu);
        }
        if (!(this.zzcuv == null || this.zzcuv.equals(""))) {
            adh.zzl(10, this.zzcuv);
        }
        if (!(this.zzcuw == null || this.zzcuw.equals(""))) {
            adh.zzl(11, this.zzcuw);
        }
        if (this.zzcux != 0) {
            adh.zzr(12, this.zzcux);
        }
        if (this.zzcuy != null && this.zzcuy.length > 0) {
            for (adp adp : this.zzcuy) {
                if (adp != null) {
                    adh.zza(13, adp);
                }
            }
        }
        super.zza(adh);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (!(this.zzcun == null || this.zzcun.equals(""))) {
            zzn += adh.zzm(1, this.zzcun);
        }
        if (!(this.zzcuo == null || this.zzcuo.equals(""))) {
            zzn += adh.zzm(2, this.zzcuo);
        }
        if (this.zzcup != 0) {
            zzn += adh.zze(3, this.zzcup);
        }
        if (!(this.zzcuq == null || this.zzcuq.equals(""))) {
            zzn += adh.zzm(4, this.zzcuq);
        }
        if (this.zzcur != 0) {
            zzn += adh.zze(5, this.zzcur);
        }
        if (this.zzaLt != 0) {
            zzn += adh.zze(6, this.zzaLt);
        }
        if (!(this.zzcus == null || this.zzcus.equals(""))) {
            zzn += adh.zzm(7, this.zzcus);
        }
        if (!(this.zzcut == null || this.zzcut.equals(""))) {
            zzn += adh.zzm(8, this.zzcut);
        }
        if (!(this.zzcuu == null || this.zzcuu.equals(""))) {
            zzn += adh.zzm(9, this.zzcuu);
        }
        if (!(this.zzcuv == null || this.zzcuv.equals(""))) {
            zzn += adh.zzm(10, this.zzcuv);
        }
        if (!(this.zzcuw == null || this.zzcuw.equals(""))) {
            zzn += adh.zzm(11, this.zzcuw);
        }
        if (this.zzcux != 0) {
            zzn += adh.zzs(12, this.zzcux);
        }
        if (this.zzcuy == null || this.zzcuy.length <= 0) {
            return zzn;
        }
        int i = zzn;
        for (adp adp : this.zzcuy) {
            if (adp != null) {
                i += adh.zzb(13, adp);
            }
        }
        return i;
    }
}
