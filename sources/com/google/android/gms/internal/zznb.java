package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.zzbs;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zzzn
public final class zznb {
    private final Object mLock = new Object();
    private boolean zzGI;
    private final List<zzmz> zzGZ = new LinkedList();
    private final Map<String, String> zzHa = new LinkedHashMap();
    private String zzHb;
    private zzmz zzHc;
    @Nullable
    private zznb zzHd;

    public zznb(boolean z, String str, String str2) {
        this.zzGI = z;
        this.zzHa.put(NativeProtocol.WEB_DIALOG_ACTION, str);
        this.zzHa.put("ad_format", str2);
    }

    public final void zzO(String str) {
        if (this.zzGI) {
            synchronized (this.mLock) {
                this.zzHb = str;
            }
        }
    }

    public final boolean zza(zzmz zzmz, long j, String... strArr) {
        synchronized (this.mLock) {
            for (String zzmz2 : strArr) {
                this.zzGZ.add(new zzmz(j, zzmz2, zzmz));
            }
        }
        return true;
    }

    public final boolean zza(@Nullable zzmz zzmz, String... strArr) {
        return (!this.zzGI || zzmz == null) ? false : zza(zzmz, zzbs.zzbF().elapsedRealtime(), strArr);
    }

    @Nullable
    public final zzmz zzc(long j) {
        return !this.zzGI ? null : new zzmz(j, null, null);
    }

    public final void zzc(@Nullable zznb zznb) {
        synchronized (this.mLock) {
            this.zzHd = zznb;
        }
    }

    public final zzmz zzdS() {
        return zzc(zzbs.zzbF().elapsedRealtime());
    }

    public final void zzdT() {
        synchronized (this.mLock) {
            this.zzHc = zzdS();
        }
    }

    public final String zzdU() {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        synchronized (this.mLock) {
            for (zzmz zzmz : this.zzGZ) {
                long time = zzmz.getTime();
                String zzdP = zzmz.zzdP();
                zzmz zzmz2 = zzmz2.zzdQ();
                if (zzmz2 != null && time > 0) {
                    stringBuilder2.append(zzdP).append('.').append(time - zzmz2.getTime()).append(',');
                }
            }
            this.zzGZ.clear();
            if (!TextUtils.isEmpty(this.zzHb)) {
                stringBuilder2.append(this.zzHb);
            } else if (stringBuilder2.length() > 0) {
                stringBuilder2.setLength(stringBuilder2.length() - 1);
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }

    final Map<String, String> zzdV() {
        Map<String, String> map;
        synchronized (this.mLock) {
            zzmr zzhr = zzbs.zzbD().zzhr();
            if (zzhr == null || this.zzHd == null) {
                map = this.zzHa;
            } else {
                map = zzhr.zza(this.zzHa, this.zzHd.zzdV());
            }
        }
        return map;
    }

    public final zzmz zzdW() {
        zzmz zzmz;
        synchronized (this.mLock) {
            zzmz = this.zzHc;
        }
        return zzmz;
    }

    public final void zzh(String str, String str2) {
        if (this.zzGI && !TextUtils.isEmpty(str2)) {
            zzmr zzhr = zzbs.zzbD().zzhr();
            if (zzhr != null) {
                synchronized (this.mLock) {
                    zzmv zzM = zzhr.zzM(str);
                    Map map = this.zzHa;
                    map.put(str, zzM.zzg((String) map.get(str), str2));
                }
            }
        }
    }
}
