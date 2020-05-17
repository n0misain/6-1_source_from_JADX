package com.google.android.gms.internal;

import java.io.IOException;

public final class ael extends adj<ael> implements Cloneable {
    private int zzcuk;
    private int zzcul;

    public ael() {
        this.zzcuk = -1;
        this.zzcul = 0;
        this.zzcso = null;
        this.zzcsx = -1;
    }

    private ael zzMg() {
        try {
            return (ael) super.zzLN();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzMg();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ael)) {
            return false;
        }
        ael ael = (ael) obj;
        return this.zzcuk != ael.zzcuk ? false : this.zzcul != ael.zzcul ? false : (this.zzcso == null || this.zzcso.isEmpty()) ? ael.zzcso == null || ael.zzcso.isEmpty() : this.zzcso.equals(ael.zzcso);
    }

    public final int hashCode() {
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.zzcuk) * 31) + this.zzcul) * 31;
        int hashCode2 = (this.zzcso == null || this.zzcso.isEmpty()) ? 0 : this.zzcso.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ adj zzLN() throws CloneNotSupportedException {
        return (ael) clone();
    }

    public final /* synthetic */ adp zzLO() throws CloneNotSupportedException {
        return (ael) clone();
    }

    public final /* synthetic */ adp zza(adg adg) throws IOException {
        while (true) {
            int zzLA = adg.zzLA();
            int position;
            int zzLC;
            switch (zzLA) {
                case 0:
                    break;
                case 8:
                    position = adg.getPosition();
                    zzLC = adg.zzLC();
                    switch (zzLC) {
                        case -1:
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
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                            this.zzcuk = zzLC;
                            break;
                        default:
                            adg.zzcp(position);
                            zza(adg, zzLA);
                            continue;
                    }
                case 16:
                    position = adg.getPosition();
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
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 100:
                            this.zzcul = zzLC;
                            break;
                        default:
                            adg.zzcp(position);
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
        if (this.zzcuk != -1) {
            adh.zzr(1, this.zzcuk);
        }
        if (this.zzcul != 0) {
            adh.zzr(2, this.zzcul);
        }
        super.zza(adh);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.zzcuk != -1) {
            zzn += adh.zzs(1, this.zzcuk);
        }
        return this.zzcul != 0 ? zzn + adh.zzs(2, this.zzcul) : zzn;
    }
}
