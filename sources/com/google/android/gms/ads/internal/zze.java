package com.google.android.gms.ads.internal;

import android.webkit.CookieManager;
import com.google.android.gms.internal.zzmo;
import java.util.concurrent.Callable;

final class zze implements Callable<String> {
    private /* synthetic */ zzd zzsZ;

    zze(zzd zzd) {
        this.zzsZ = zzd;
    }

    public final /* synthetic */ Object call() throws Exception {
        String str = "";
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzGf)).booleanValue()) {
            CookieManager zzS = zzbs.zzbB().zzS(this.zzsZ.zzsP.zzqD);
            if (zzS != null) {
                return zzS.getCookie("googleads.g.doubleclick.net");
            }
        }
        return str;
    }
}
