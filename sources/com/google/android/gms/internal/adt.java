package com.google.android.gms.internal;

import java.io.IOException;

public final class adt extends adj<adt> {
    public String url;
    public Integer zzcsJ;
    private Integer zzcsK;
    public String zzcsL;
    private String zzcsM;
    public adu zzcsN;
    public aeb[] zzcsO;
    public String zzcsP;
    public aea zzcsQ;
    private Boolean zzcsR;
    private String[] zzcsS;
    private String zzcsT;
    private Boolean zzcsU;
    private Boolean zzcsV;
    private byte[] zzcsW;
    public aec zzcsX;

    public adt() {
        this.zzcsJ = null;
        this.zzcsK = null;
        this.url = null;
        this.zzcsL = null;
        this.zzcsM = null;
        this.zzcsN = null;
        this.zzcsO = aeb.zzLX();
        this.zzcsP = null;
        this.zzcsQ = null;
        this.zzcsR = null;
        this.zzcsS = ads.EMPTY_STRING_ARRAY;
        this.zzcsT = null;
        this.zzcsU = null;
        this.zzcsV = null;
        this.zzcsW = null;
        this.zzcsX = null;
        this.zzcso = null;
        this.zzcsx = -1;
    }

    public final /* synthetic */ adp zza(adg adg) throws IOException {
        while (true) {
            int zzLA = adg.zzLA();
            int zzb;
            Object obj;
            int zzLC;
            switch (zzLA) {
                case 0:
                    break;
                case 10:
                    this.url = adg.readString();
                    continue;
                case 18:
                    this.zzcsL = adg.readString();
                    continue;
                case 26:
                    this.zzcsM = adg.readString();
                    continue;
                case 34:
                    zzb = ads.zzb(adg, 34);
                    zzLA = this.zzcsO == null ? 0 : this.zzcsO.length;
                    obj = new aeb[(zzb + zzLA)];
                    if (zzLA != 0) {
                        System.arraycopy(this.zzcsO, 0, obj, 0, zzLA);
                    }
                    while (zzLA < obj.length - 1) {
                        obj[zzLA] = new aeb();
                        adg.zza(obj[zzLA]);
                        adg.zzLA();
                        zzLA++;
                    }
                    obj[zzLA] = new aeb();
                    adg.zza(obj[zzLA]);
                    this.zzcsO = obj;
                    continue;
                case 40:
                    this.zzcsR = Boolean.valueOf(adg.zzLD());
                    continue;
                case 50:
                    zzb = ads.zzb(adg, 50);
                    zzLA = this.zzcsS == null ? 0 : this.zzcsS.length;
                    obj = new String[(zzb + zzLA)];
                    if (zzLA != 0) {
                        System.arraycopy(this.zzcsS, 0, obj, 0, zzLA);
                    }
                    while (zzLA < obj.length - 1) {
                        obj[zzLA] = adg.readString();
                        adg.zzLA();
                        zzLA++;
                    }
                    obj[zzLA] = adg.readString();
                    this.zzcsS = obj;
                    continue;
                case 58:
                    this.zzcsT = adg.readString();
                    continue;
                case 64:
                    this.zzcsU = Boolean.valueOf(adg.zzLD());
                    continue;
                case 72:
                    this.zzcsV = Boolean.valueOf(adg.zzLD());
                    continue;
                case 80:
                    zzb = adg.getPosition();
                    zzLC = adg.zzLC();
                    switch (zzLC) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                            this.zzcsJ = Integer.valueOf(zzLC);
                            break;
                        default:
                            adg.zzcp(zzb);
                            zza(adg, zzLA);
                            continue;
                    }
                case 88:
                    zzb = adg.getPosition();
                    zzLC = adg.zzLC();
                    switch (zzLC) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            this.zzcsK = Integer.valueOf(zzLC);
                            break;
                        default:
                            adg.zzcp(zzb);
                            zza(adg, zzLA);
                            continue;
                    }
                case 98:
                    if (this.zzcsN == null) {
                        this.zzcsN = new adu();
                    }
                    adg.zza(this.zzcsN);
                    continue;
                case 106:
                    this.zzcsP = adg.readString();
                    continue;
                case 114:
                    if (this.zzcsQ == null) {
                        this.zzcsQ = new aea();
                    }
                    adg.zza(this.zzcsQ);
                    continue;
                case 122:
                    this.zzcsW = adg.readBytes();
                    continue;
                case 138:
                    if (this.zzcsX == null) {
                        this.zzcsX = new aec();
                    }
                    adg.zza(this.zzcsX);
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
        if (this.url != null) {
            adh.zzl(1, this.url);
        }
        if (this.zzcsL != null) {
            adh.zzl(2, this.zzcsL);
        }
        if (this.zzcsM != null) {
            adh.zzl(3, this.zzcsM);
        }
        if (this.zzcsO != null && this.zzcsO.length > 0) {
            for (adp adp : this.zzcsO) {
                if (adp != null) {
                    adh.zza(4, adp);
                }
            }
        }
        if (this.zzcsR != null) {
            adh.zzk(5, this.zzcsR.booleanValue());
        }
        if (this.zzcsS != null && this.zzcsS.length > 0) {
            while (i < this.zzcsS.length) {
                String str = this.zzcsS[i];
                if (str != null) {
                    adh.zzl(6, str);
                }
                i++;
            }
        }
        if (this.zzcsT != null) {
            adh.zzl(7, this.zzcsT);
        }
        if (this.zzcsU != null) {
            adh.zzk(8, this.zzcsU.booleanValue());
        }
        if (this.zzcsV != null) {
            adh.zzk(9, this.zzcsV.booleanValue());
        }
        if (this.zzcsJ != null) {
            adh.zzr(10, this.zzcsJ.intValue());
        }
        if (this.zzcsK != null) {
            adh.zzr(11, this.zzcsK.intValue());
        }
        if (this.zzcsN != null) {
            adh.zza(12, this.zzcsN);
        }
        if (this.zzcsP != null) {
            adh.zzl(13, this.zzcsP);
        }
        if (this.zzcsQ != null) {
            adh.zza(14, this.zzcsQ);
        }
        if (this.zzcsW != null) {
            adh.zzb(15, this.zzcsW);
        }
        if (this.zzcsX != null) {
            adh.zza(17, this.zzcsX);
        }
        super.zza(adh);
    }

    protected final int zzn() {
        int i;
        int i2 = 0;
        int zzn = super.zzn();
        if (this.url != null) {
            zzn += adh.zzm(1, this.url);
        }
        if (this.zzcsL != null) {
            zzn += adh.zzm(2, this.zzcsL);
        }
        if (this.zzcsM != null) {
            zzn += adh.zzm(3, this.zzcsM);
        }
        if (this.zzcsO != null && this.zzcsO.length > 0) {
            i = zzn;
            for (adp adp : this.zzcsO) {
                if (adp != null) {
                    i += adh.zzb(4, adp);
                }
            }
            zzn = i;
        }
        if (this.zzcsR != null) {
            this.zzcsR.booleanValue();
            zzn += adh.zzct(5) + 1;
        }
        if (this.zzcsS != null && this.zzcsS.length > 0) {
            i = 0;
            int i3 = 0;
            while (i2 < this.zzcsS.length) {
                String str = this.zzcsS[i2];
                if (str != null) {
                    i3++;
                    i += adh.zzhQ(str);
                }
                i2++;
            }
            zzn = (zzn + i) + (i3 * 1);
        }
        if (this.zzcsT != null) {
            zzn += adh.zzm(7, this.zzcsT);
        }
        if (this.zzcsU != null) {
            this.zzcsU.booleanValue();
            zzn += adh.zzct(8) + 1;
        }
        if (this.zzcsV != null) {
            this.zzcsV.booleanValue();
            zzn += adh.zzct(9) + 1;
        }
        if (this.zzcsJ != null) {
            zzn += adh.zzs(10, this.zzcsJ.intValue());
        }
        if (this.zzcsK != null) {
            zzn += adh.zzs(11, this.zzcsK.intValue());
        }
        if (this.zzcsN != null) {
            zzn += adh.zzb(12, this.zzcsN);
        }
        if (this.zzcsP != null) {
            zzn += adh.zzm(13, this.zzcsP);
        }
        if (this.zzcsQ != null) {
            zzn += adh.zzb(14, this.zzcsQ);
        }
        if (this.zzcsW != null) {
            zzn += adh.zzc(15, this.zzcsW);
        }
        return this.zzcsX != null ? zzn + adh.zzb(17, this.zzcsX) : zzn;
    }
}
