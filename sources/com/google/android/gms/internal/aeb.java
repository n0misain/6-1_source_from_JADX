package com.google.android.gms.internal;

import java.io.IOException;

public final class aeb extends adj<aeb> {
    private static volatile aeb[] zzctm;
    public String url;
    public Integer zzbuM;
    public adw zzctn;
    private ady zzcto;
    private Integer zzctp;
    private int[] zzctq;
    private String zzctr;
    public Integer zzcts;
    public String[] zzctt;

    public aeb() {
        this.zzbuM = null;
        this.url = null;
        this.zzctn = null;
        this.zzcto = null;
        this.zzctp = null;
        this.zzctq = ads.zzcsC;
        this.zzctr = null;
        this.zzcts = null;
        this.zzctt = ads.EMPTY_STRING_ARRAY;
        this.zzcso = null;
        this.zzcsx = -1;
    }

    public static aeb[] zzLX() {
        if (zzctm == null) {
            synchronized (adn.zzcsw) {
                if (zzctm == null) {
                    zzctm = new aeb[0];
                }
            }
        }
        return zzctm;
    }

    public final /* synthetic */ adp zza(adg adg) throws IOException {
        while (true) {
            int zzLA = adg.zzLA();
            int zzb;
            Object obj;
            int zzcn;
            switch (zzLA) {
                case 0:
                    break;
                case 8:
                    this.zzbuM = Integer.valueOf(adg.zzLC());
                    continue;
                case 18:
                    this.url = adg.readString();
                    continue;
                case 26:
                    if (this.zzctn == null) {
                        this.zzctn = new adw();
                    }
                    adg.zza(this.zzctn);
                    continue;
                case 34:
                    if (this.zzcto == null) {
                        this.zzcto = new ady();
                    }
                    adg.zza(this.zzcto);
                    continue;
                case 40:
                    this.zzctp = Integer.valueOf(adg.zzLC());
                    continue;
                case 48:
                    zzb = ads.zzb(adg, 48);
                    zzLA = this.zzctq == null ? 0 : this.zzctq.length;
                    obj = new int[(zzb + zzLA)];
                    if (zzLA != 0) {
                        System.arraycopy(this.zzctq, 0, obj, 0, zzLA);
                    }
                    while (zzLA < obj.length - 1) {
                        obj[zzLA] = adg.zzLC();
                        adg.zzLA();
                        zzLA++;
                    }
                    obj[zzLA] = adg.zzLC();
                    this.zzctq = obj;
                    continue;
                case 50:
                    zzcn = adg.zzcn(adg.zzLF());
                    zzb = adg.getPosition();
                    zzLA = 0;
                    while (adg.zzLK() > 0) {
                        adg.zzLC();
                        zzLA++;
                    }
                    adg.zzcp(zzb);
                    zzb = this.zzctq == null ? 0 : this.zzctq.length;
                    Object obj2 = new int[(zzLA + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzctq, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = adg.zzLC();
                        zzb++;
                    }
                    this.zzctq = obj2;
                    adg.zzco(zzcn);
                    continue;
                case 58:
                    this.zzctr = adg.readString();
                    continue;
                case 64:
                    zzb = adg.getPosition();
                    zzcn = adg.zzLC();
                    switch (zzcn) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            this.zzcts = Integer.valueOf(zzcn);
                            break;
                        default:
                            adg.zzcp(zzb);
                            zza(adg, zzLA);
                            continue;
                    }
                case 74:
                    zzb = ads.zzb(adg, 74);
                    zzLA = this.zzctt == null ? 0 : this.zzctt.length;
                    obj = new String[(zzb + zzLA)];
                    if (zzLA != 0) {
                        System.arraycopy(this.zzctt, 0, obj, 0, zzLA);
                    }
                    while (zzLA < obj.length - 1) {
                        obj[zzLA] = adg.readString();
                        adg.zzLA();
                        zzLA++;
                    }
                    obj[zzLA] = adg.readString();
                    this.zzctt = obj;
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
        adh.zzr(1, this.zzbuM.intValue());
        if (this.url != null) {
            adh.zzl(2, this.url);
        }
        if (this.zzctn != null) {
            adh.zza(3, this.zzctn);
        }
        if (this.zzcto != null) {
            adh.zza(4, this.zzcto);
        }
        if (this.zzctp != null) {
            adh.zzr(5, this.zzctp.intValue());
        }
        if (this.zzctq != null && this.zzctq.length > 0) {
            for (int zzr : this.zzctq) {
                adh.zzr(6, zzr);
            }
        }
        if (this.zzctr != null) {
            adh.zzl(7, this.zzctr);
        }
        if (this.zzcts != null) {
            adh.zzr(8, this.zzcts.intValue());
        }
        if (this.zzctt != null && this.zzctt.length > 0) {
            while (i < this.zzctt.length) {
                String str = this.zzctt[i];
                if (str != null) {
                    adh.zzl(9, str);
                }
                i++;
            }
        }
        super.zza(adh);
    }

    protected final int zzn() {
        int i;
        int i2;
        int i3 = 0;
        int zzn = super.zzn() + adh.zzs(1, this.zzbuM.intValue());
        if (this.url != null) {
            zzn += adh.zzm(2, this.url);
        }
        if (this.zzctn != null) {
            zzn += adh.zzb(3, this.zzctn);
        }
        if (this.zzcto != null) {
            zzn += adh.zzb(4, this.zzcto);
        }
        if (this.zzctp != null) {
            zzn += adh.zzs(5, this.zzctp.intValue());
        }
        if (this.zzctq != null && this.zzctq.length > 0) {
            i = 0;
            for (int zzcr : this.zzctq) {
                i += adh.zzcr(zzcr);
            }
            zzn = (zzn + i) + (this.zzctq.length * 1);
        }
        if (this.zzctr != null) {
            zzn += adh.zzm(7, this.zzctr);
        }
        if (this.zzcts != null) {
            zzn += adh.zzs(8, this.zzcts.intValue());
        }
        if (this.zzctt == null || this.zzctt.length <= 0) {
            return zzn;
        }
        i2 = 0;
        i = 0;
        while (i3 < this.zzctt.length) {
            String str = this.zzctt[i3];
            if (str != null) {
                i++;
                i2 += adh.zzhQ(str);
            }
            i3++;
        }
        return (zzn + i2) + (i * 1);
    }
}
