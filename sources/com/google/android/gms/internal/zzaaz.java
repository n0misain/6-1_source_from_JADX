package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.js.zza;
import com.google.android.gms.ads.internal.js.zzl;
import com.google.android.gms.ads.internal.js.zzy;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzaaz extends zzafp {
    private static zzl zzRl = null;
    private static long zzTW = TimeUnit.SECONDS.toMillis(10);
    private static boolean zzTX = false;
    private static zzre zzTY = null;
    private static zzrn zzTZ = null;
    private static zzrd zzUa = null;
    private static final Object zzuF = new Object();
    private final Context mContext;
    private final Object zzQT = new Object();
    private final zzzp zzSm;
    private final zzaaf zzSn;
    private zzij zzSo;
    private zzy zzUb;

    public zzaaz(Context context, zzaaf zzaaf, zzzp zzzp, zzij zzij) {
        super(true);
        this.zzSm = zzzp;
        this.mContext = context;
        this.zzSn = zzaaf;
        this.zzSo = zzij;
        synchronized (zzuF) {
            if (!zzTX) {
                zzTZ = new zzrn();
                zzTY = new zzre(context.getApplicationContext(), zzaaf.zzvT);
                zzUa = new zzabh();
                zzRl = new zzl(this.mContext.getApplicationContext(), this.zzSn.zzvT, (String) zzbs.zzbL().zzd(zzmo.zzBX), new zzabg(), new zzabf());
                zzTX = true;
            }
        }
    }

    private final JSONObject zza(zzaae zzaae, String str) {
        Throwable e;
        Object obj;
        Info advertisingIdInfo;
        Map hashMap;
        JSONObject jSONObject = null;
        Bundle bundle = zzaae.zzSz.extras.getBundle("sdk_less_server_data");
        if (bundle != null) {
            zzacb zzacb;
            try {
                zzacb = (zzacb) zzbs.zzbI().zzn(this.mContext).get();
            } catch (Throwable e2) {
                zzajc.zzc("Error grabbing device info: ", e2);
                obj = jSONObject;
            }
            Context context = this.mContext;
            zzabk zzabk = new zzabk();
            zzabk.zzUj = zzaae;
            zzabk.zzUk = zzacb;
            JSONObject zza = zzabt.zza(context, zzabk);
            if (zza != null) {
                try {
                    advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
                } catch (IOException e3) {
                    e2 = e3;
                    zzajc.zzc("Cannot get advertising id info", e2);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("request_param", zza);
                    hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzbs.zzbz().zzj(hashMap);
                    return jSONObject;
                } catch (IllegalStateException e4) {
                    e2 = e4;
                    zzajc.zzc("Cannot get advertising id info", e2);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("request_param", zza);
                    hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzbs.zzbz().zzj(hashMap);
                    return jSONObject;
                } catch (GooglePlayServicesNotAvailableException e5) {
                    e2 = e5;
                    zzajc.zzc("Cannot get advertising id info", e2);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("request_param", zza);
                    hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzbs.zzbz().zzj(hashMap);
                    return jSONObject;
                } catch (GooglePlayServicesRepairableException e6) {
                    e2 = e6;
                    zzajc.zzc("Cannot get advertising id info", e2);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("request_param", zza);
                    hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzbs.zzbz().zzj(hashMap);
                    return jSONObject;
                }
                hashMap = new HashMap();
                hashMap.put("request_id", str);
                hashMap.put("request_param", zza);
                hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bundle);
                if (advertisingIdInfo != null) {
                    hashMap.put("adid", advertisingIdInfo.getId());
                    if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                    }
                    hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                }
                try {
                    jSONObject = zzbs.zzbz().zzj(hashMap);
                } catch (JSONException e7) {
                }
            }
        }
        return jSONObject;
    }

    protected static void zza(zza zza) {
        zza.zza("/loadAd", zzTZ);
        zza.zza("/fetchHttpRequest", zzTY);
        zza.zza("/invalidRequest", zzUa);
    }

    protected static void zzb(zza zza) {
        zza.zzb("/loadAd", zzTZ);
        zza.zzb("/fetchHttpRequest", zzTY);
        zza.zzb("/invalidRequest", zzUa);
    }

    private final zzaai zzd(zzaae zzaae) {
        zzbs.zzbz();
        String zzhO = zzagz.zzhO();
        JSONObject zza = zza(zzaae, zzhO);
        if (zza == null) {
            return new zzaai(0);
        }
        long elapsedRealtime = zzbs.zzbF().elapsedRealtime();
        Future zzS = zzTZ.zzS(zzhO);
        zzaiy.zzaaH.post(new zzabb(this, zza, zzhO));
        try {
            JSONObject jSONObject = (JSONObject) zzS.get(zzTW - (zzbs.zzbF().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new zzaai(-1);
            }
            zzaai zza2 = zzabt.zza(this.mContext, zzaae, jSONObject.toString());
            return (zza2.errorCode == -3 || !TextUtils.isEmpty(zza2.body)) ? zza2 : new zzaai(3);
        } catch (CancellationException e) {
            return new zzaai(-1);
        } catch (InterruptedException e2) {
            return new zzaai(-1);
        } catch (TimeoutException e3) {
            return new zzaai(2);
        } catch (ExecutionException e4) {
            return new zzaai(0);
        }
    }

    public final void onStop() {
        synchronized (this.zzQT) {
            zzaiy.zzaaH.post(new zzabe(this));
        }
    }

    public final void zzbd() {
        zzajc.zzaC("SdkLessAdLoaderBackgroundTask started.");
        String zzw = zzbs.zzbY().zzw(this.mContext);
        zzaae zzaae = new zzaae(this.zzSn, -1, zzbs.zzbY().zzu(this.mContext), zzbs.zzbY().zzv(this.mContext), zzw);
        zzbs.zzbY().zzh(this.mContext, zzw);
        zzaai zzd = zzd(zzaae);
        zzaae zzaae2 = zzaae;
        zzub zzub = null;
        zziv zziv = null;
        zzaiy.zzaaH.post(new zzaba(this, new zzafg(zzaae2, zzd, zzub, zziv, zzd.errorCode, zzbs.zzbF().elapsedRealtime(), zzd.zzTs, null, this.zzSo)));
    }
}
