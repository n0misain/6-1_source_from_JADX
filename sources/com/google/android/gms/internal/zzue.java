package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzue implements zzui {
    private final Context mContext;
    private final Object mLock = new Object();
    private int zzMA = -2;
    private zzuz zzMB;
    private final String zzMs;
    private final long zzMt;
    private final zzub zzMu;
    private final zzua zzMv;
    private final List<String> zzMw;
    private final List<String> zzMx;
    private final boolean zzMy;
    private zzut zzMz;
    private final zzuq zzsX;
    private final zzon zztS;
    private final List<String> zztT;
    private final zzaje zztW;
    private zzir zzuT;
    private final zziv zzuZ;
    private final boolean zzwJ;

    public zzue(Context context, String str, zzuq zzuq, zzub zzub, zzua zzua, zzir zzir, zziv zziv, zzaje zzaje, boolean z, boolean z2, zzon zzon, List<String> list, List<String> list2, List<String> list3) {
        this.mContext = context;
        this.zzsX = zzuq;
        this.zzMv = zzua;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            this.zzMs = zzfj();
        } else {
            this.zzMs = str;
        }
        this.zzMu = zzub;
        this.zzMt = zzub.zzLZ != -1 ? zzub.zzLZ : 10000;
        this.zzuT = zzir;
        this.zzuZ = zziv;
        this.zztW = zzaje;
        this.zzwJ = z;
        this.zzMy = z2;
        this.zztS = zzon;
        this.zztT = list;
        this.zzMw = list2;
        this.zzMx = list3;
    }

    private static zzut zza(MediationAdapter mediationAdapter) {
        return new zzvj(mediationAdapter);
    }

    private final void zza(zzud zzud) {
        String zzaf = zzaf(this.zzMv.zzLP);
        try {
            if (this.zztW.zzaaP < 4100000) {
                if (this.zzuZ.zzAt) {
                    this.zzMz.zza(zzn.zzw(this.mContext), this.zzuT, zzaf, zzud);
                } else {
                    this.zzMz.zza(zzn.zzw(this.mContext), this.zzuZ, this.zzuT, zzaf, (zzuw) zzud);
                }
            } else if (this.zzwJ || this.zzMv.zzfi()) {
                List arrayList = new ArrayList(this.zztT);
                if (this.zzMw != null) {
                    for (String str : this.zzMw) {
                        String str2 = ":false";
                        if (this.zzMx != null && this.zzMx.contains(str)) {
                            str2 = ":true";
                        }
                        arrayList.add(new StringBuilder((String.valueOf(str).length() + 7) + String.valueOf(str2).length()).append("custom:").append(str).append(str2).toString());
                    }
                }
                this.zzMz.zza(zzn.zzw(this.mContext), this.zzuT, zzaf, this.zzMv.zzLH, zzud, this.zztS, arrayList);
            } else if (this.zzuZ.zzAt) {
                this.zzMz.zza(zzn.zzw(this.mContext), this.zzuT, zzaf, this.zzMv.zzLH, (zzuw) zzud);
            } else if (!this.zzMy) {
                this.zzMz.zza(zzn.zzw(this.mContext), this.zzuZ, this.zzuT, zzaf, this.zzMv.zzLH, zzud);
            } else if (this.zzMv.zzLS != null) {
                this.zzMz.zza(zzn.zzw(this.mContext), this.zzuT, zzaf, this.zzMv.zzLH, zzud, new zzon(zzag(this.zzMv.zzLW)), this.zzMv.zzLV);
            } else {
                this.zzMz.zza(zzn.zzw(this.mContext), this.zzuZ, this.zzuT, zzaf, this.zzMv.zzLH, zzud);
            }
        } catch (Throwable e) {
            zzajc.zzc("Could not request ad from mediation adapter.", e);
            zzo(5);
        }
    }

    private final String zzaf(String str) {
        if (!(str == null || !zzfm() || zzp(2))) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                jSONObject.remove("cpm_floor_cents");
                str = jSONObject.toString();
            } catch (JSONException e) {
                zzajc.zzaT("Could not remove field. Returning the original value");
            }
        }
        return str;
    }

    private static NativeAdOptions zzag(String str) {
        int i = 0;
        Builder builder = new Builder();
        if (str == null) {
            return builder.build();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            builder.setRequestMultipleImages(jSONObject.optBoolean("multiple_images", false));
            builder.setReturnUrlsForImageAssets(jSONObject.optBoolean("only_urls", false));
            String optString = jSONObject.optString("native_image_orientation", "any");
            if ("landscape".equals(optString)) {
                i = 2;
            } else if ("portrait".equals(optString)) {
                i = 1;
            } else if (!"any".equals(optString)) {
                i = -1;
            }
            builder.setImageOrientation(i);
        } catch (Throwable e) {
            zzajc.zzc("Exception occurred when creating native ad options", e);
        }
        return builder.build();
    }

    private final String zzfj() {
        try {
            if (!TextUtils.isEmpty(this.zzMv.zzLL)) {
                return this.zzsX.zzai(this.zzMv.zzLL) ? "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter" : "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        } catch (RemoteException e) {
            zzajc.zzaT("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    private final zzuz zzfk() {
        if (this.zzMA != 0 || !zzfm()) {
            return null;
        }
        try {
            if (!(!zzp(4) || this.zzMB == null || this.zzMB.zzfo() == 0)) {
                return this.zzMB;
            }
        } catch (RemoteException e) {
            zzajc.zzaT("Could not get cpm value from MediationResponseMetadata");
        }
        return new zzug(zzfn());
    }

    private final zzut zzfl() {
        String str = "Instantiating mediation adapter: ";
        String valueOf = String.valueOf(this.zzMs);
        zzajc.zzaS(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        if (!(this.zzwJ || this.zzMv.zzfi())) {
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzEG)).booleanValue() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzMs)) {
                return zza(new AdMobAdapter());
            }
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzEH)).booleanValue() && "com.google.ads.mediation.AdUrlAdapter".equals(this.zzMs)) {
                return zza(new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzMs)) {
                return new zzvj(new zzwd());
            }
        }
        try {
            return this.zzsX.zzah(this.zzMs);
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = "Could not instantiate mediation adapter: ";
            valueOf = String.valueOf(this.zzMs);
            zzajc.zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            return null;
        }
    }

    private final boolean zzfm() {
        return this.zzMu.zzMj != -1;
    }

    private final int zzfn() {
        if (this.zzMv.zzLP == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.zzMv.zzLP);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzMs)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int optInt = zzp(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            return optInt == 0 ? jSONObject.optInt("penalized_average_cpm_cents", 0) : optInt;
        } catch (JSONException e) {
            zzajc.zzaT("Could not convert to json. Returning 0");
            return 0;
        }
    }

    private final boolean zzp(int i) {
        try {
            Bundle zzft = this.zzwJ ? this.zzMz.zzft() : this.zzuZ.zzAt ? this.zzMz.getInterstitialAdapterInfo() : this.zzMz.zzfs();
            return zzft != null && (zzft.getInt("capabilities", 0) & i) == i;
        } catch (RemoteException e) {
            zzajc.zzaT("Could not get adapter info. Returning false");
            return false;
        }
    }

    public final void cancel() {
        synchronized (this.mLock) {
            try {
                if (this.zzMz != null) {
                    this.zzMz.destroy();
                }
            } catch (Throwable e) {
                zzajc.zzc("Could not destroy mediation adapter.", e);
            }
            this.zzMA = -1;
            this.mLock.notify();
        }
    }

    public final zzuh zza(long j, long j2) {
        zzuh zzuh;
        synchronized (this.mLock) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            zzud zzud = new zzud();
            zzagz.zzZr.post(new zzuf(this, zzud));
            long j3 = this.zzMt;
            while (this.zzMA == -2) {
                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                long j4 = j3 - (elapsedRealtime2 - elapsedRealtime);
                elapsedRealtime2 = j2 - (elapsedRealtime2 - j);
                if (j4 <= 0 || elapsedRealtime2 <= 0) {
                    zzajc.zzaS("Timed out waiting for adapter.");
                    this.zzMA = 3;
                } else {
                    try {
                        this.mLock.wait(Math.min(j4, elapsedRealtime2));
                    } catch (InterruptedException e) {
                        this.zzMA = -1;
                    }
                }
            }
            zzuh = new zzuh(this.zzMv, this.zzMz, this.zzMs, zzud, this.zzMA, zzfk(), zzbs.zzbF().elapsedRealtime() - elapsedRealtime);
        }
        return zzuh;
    }

    public final void zza(int i, zzuz zzuz) {
        synchronized (this.mLock) {
            this.zzMA = 0;
            this.zzMB = zzuz;
            this.mLock.notify();
        }
    }

    public final void zzo(int i) {
        synchronized (this.mLock) {
            this.zzMA = i;
            this.mLock.notify();
        }
    }
}
