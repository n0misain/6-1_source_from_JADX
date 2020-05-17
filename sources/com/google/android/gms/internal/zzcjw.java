package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcjw extends adj<zzcjw> {
    private static volatile zzcjw[] zzbvv;
    public Integer count;
    public String name;
    public zzcjx[] zzbvw;
    public Long zzbvx;
    public Long zzbvy;

    public zzcjw() {
        this.zzbvw = zzcjx.zzzC();
        this.name = null;
        this.zzbvx = null;
        this.zzbvy = null;
        this.count = null;
        this.zzcso = null;
        this.zzcsx = -1;
    }

    public static zzcjw[] zzzB() {
        if (zzbvv == null) {
            synchronized (adn.zzcsw) {
                if (zzbvv == null) {
                    zzbvv = new zzcjw[0];
                }
            }
        }
        return zzbvv;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcjw)) {
            return false;
        }
        zzcjw zzcjw = (zzcjw) obj;
        if (!adn.equals(this.zzbvw, zzcjw.zzbvw)) {
            return false;
        }
        if (this.name == null) {
            if (zzcjw.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzcjw.name)) {
            return false;
        }
        if (this.zzbvx == null) {
            if (zzcjw.zzbvx != null) {
                return false;
            }
        } else if (!this.zzbvx.equals(zzcjw.zzbvx)) {
            return false;
        }
        if (this.zzbvy == null) {
            if (zzcjw.zzbvy != null) {
                return false;
            }
        } else if (!this.zzbvy.equals(zzcjw.zzbvy)) {
            return false;
        }
        if (this.count == null) {
            if (zzcjw.count != null) {
                return false;
            }
        } else if (!this.count.equals(zzcjw.count)) {
            return false;
        }
        return (this.zzcso == null || this.zzcso.isEmpty()) ? zzcjw.zzcso == null || zzcjw.zzcso.isEmpty() : this.zzcso.equals(zzcjw.zzcso);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.count == null ? 0 : this.count.hashCode()) + (((this.zzbvy == null ? 0 : this.zzbvy.hashCode()) + (((this.zzbvx == null ? 0 : this.zzbvx.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + adn.hashCode(this.zzbvw)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.zzcso == null || this.zzcso.isEmpty())) {
            i = this.zzcso.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ adp zza(adg adg) throws IOException {
        while (true) {
            int zzLA = adg.zzLA();
            switch (zzLA) {
                case 0:
                    break;
                case 10:
                    int zzb = ads.zzb(adg, 10);
                    zzLA = this.zzbvw == null ? 0 : this.zzbvw.length;
                    Object obj = new zzcjx[(zzb + zzLA)];
                    if (zzLA != 0) {
                        System.arraycopy(this.zzbvw, 0, obj, 0, zzLA);
                    }
                    while (zzLA < obj.length - 1) {
                        obj[zzLA] = new zzcjx();
                        adg.zza(obj[zzLA]);
                        adg.zzLA();
                        zzLA++;
                    }
                    obj[zzLA] = new zzcjx();
                    adg.zza(obj[zzLA]);
                    this.zzbvw = obj;
                    continue;
                case 18:
                    this.name = adg.readString();
                    continue;
                case 24:
                    this.zzbvx = Long.valueOf(adg.zzLG());
                    continue;
                case 32:
                    this.zzbvy = Long.valueOf(adg.zzLG());
                    continue;
                case 40:
                    this.count = Integer.valueOf(adg.zzLF());
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
        if (this.zzbvw != null && this.zzbvw.length > 0) {
            for (adp adp : this.zzbvw) {
                if (adp != null) {
                    adh.zza(1, adp);
                }
            }
        }
        if (this.name != null) {
            adh.zzl(2, this.name);
        }
        if (this.zzbvx != null) {
            adh.zzb(3, this.zzbvx.longValue());
        }
        if (this.zzbvy != null) {
            adh.zzb(4, this.zzbvy.longValue());
        }
        if (this.count != null) {
            adh.zzr(5, this.count.intValue());
        }
        super.zza(adh);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.zzbvw != null && this.zzbvw.length > 0) {
            for (adp adp : this.zzbvw) {
                if (adp != null) {
                    zzn += adh.zzb(1, adp);
                }
            }
        }
        if (this.name != null) {
            zzn += adh.zzm(2, this.name);
        }
        if (this.zzbvx != null) {
            zzn += adh.zze(3, this.zzbvx.longValue());
        }
        if (this.zzbvy != null) {
            zzn += adh.zze(4, this.zzbvy.longValue());
        }
        return this.count != null ? zzn + adh.zzs(5, this.count.intValue()) : zzn;
    }
}
