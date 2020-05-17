package com.google.android.gms.ads.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzacs;
import com.google.android.gms.internal.zzadz;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzua;
import com.google.android.gms.internal.zzub;
import com.google.android.gms.internal.zzut;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzaz implements Runnable {
    private /* synthetic */ zzay zzuN;

    zzaz(zzay zzay) {
        this.zzuN = zzay;
    }

    public final void run() {
        Context zza = this.zzuN.zzuM.mContext;
        Runnable runnable = this.zzuN.zzuL;
        zzbo.zzcz("Adapters must be initialized on the main thread.");
        Map zzhm = zzbs.zzbD().zzhD().zzhm();
        if (zzhm != null && !zzhm.isEmpty()) {
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    zzajc.zzc("Could not initialize rewarded ads.", th);
                    return;
                }
            }
            zzacs zzgO = zzacs.zzgO();
            if (zzgO != null) {
                String valueOf;
                Collection<zzub> values = zzhm.values();
                zzhm = new HashMap();
                IObjectWrapper zzw = zzn.zzw(zza);
                for (zzub zzub : values) {
                    for (zzua zzua : zzub.zzLY) {
                        String str = zzua.zzLP;
                        for (String valueOf2 : zzua.zzLJ) {
                            if (!zzhm.containsKey(valueOf2)) {
                                zzhm.put(valueOf2, new ArrayList());
                            }
                            if (str != null) {
                                ((Collection) zzhm.get(valueOf2)).add(str);
                            }
                        }
                    }
                }
                for (Entry entry : zzhm.entrySet()) {
                    String str2 = (String) entry.getKey();
                    try {
                        zzadz zzav = zzgO.zzav(str2);
                        if (zzav != null) {
                            zzut zzgW = zzav.zzgW();
                            if (!zzgW.isInitialized() && zzgW.zzfu()) {
                                zzgW.zza(zzw, zzav.zzgX(), (List) entry.getValue());
                                String str3 = "Initialized rewarded video mediation adapter ";
                                valueOf2 = String.valueOf(str2);
                                zzajc.zzaC(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
                            }
                        }
                    } catch (Throwable th2) {
                        zzajc.zzc(new StringBuilder(String.valueOf(str2).length() + 56).append("Failed to initialize rewarded video mediation adapter \"").append(str2).append("\"").toString(), th2);
                    }
                }
            }
        }
    }
}
