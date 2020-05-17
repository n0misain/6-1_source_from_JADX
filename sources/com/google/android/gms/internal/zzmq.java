package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build.VERSION;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzbs;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

@zzzn
public final class zzmq {
    private Context mContext = null;
    private boolean zzGI;
    private String zzGJ;
    private Map<String, String> zzGK;
    private String zzwH = null;

    public zzmq(Context context, String str) {
        this.mContext = context;
        this.zzwH = str;
        this.zzGI = ((Boolean) zzbs.zzbL().zzd(zzmo.zzCQ)).booleanValue();
        this.zzGJ = (String) zzbs.zzbL().zzd(zzmo.zzCR);
        this.zzGK = new LinkedHashMap();
        this.zzGK.put("s", "gmob_sdk");
        this.zzGK.put("v", "3");
        this.zzGK.put("os", VERSION.RELEASE);
        this.zzGK.put("sdk", VERSION.SDK);
        zzbs.zzbz();
        this.zzGK.put("device", zzagz.zzhQ());
        this.zzGK.put(SettingsJsonConstants.APP_KEY, context.getApplicationContext() != null ? context.getApplicationContext().getPackageName() : context.getPackageName());
        Map map = this.zzGK;
        String str2 = "is_lite_sdk";
        zzbs.zzbz();
        map.put(str2, zzagz.zzO(context) ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        Future zzn = zzbs.zzbI().zzn(this.mContext);
        try {
            zzn.get();
            this.zzGK.put("network_coarse", Integer.toString(((zzacb) zzn.get()).zzVS));
            this.zzGK.put("network_fine", Integer.toString(((zzacb) zzn.get()).zzVT));
        } catch (Throwable e) {
            zzbs.zzbD().zza(e, "CsiConfiguration.CsiConfiguration");
        }
    }

    final Context getContext() {
        return this.mContext;
    }

    final String zzck() {
        return this.zzwH;
    }

    final boolean zzdL() {
        return this.zzGI;
    }

    final String zzdM() {
        return this.zzGJ;
    }

    final Map<String, String> zzdN() {
        return this.zzGK;
    }
}
