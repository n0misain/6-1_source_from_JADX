package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.zzbs;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

@zzzn
public final class zzzq extends zzafp implements zzzw {
    private final Context mContext;
    private zzaae zzMM;
    private zzub zzMu;
    private zzaai zzQR;
    private Runnable zzQS;
    private final Object zzQT = new Object();
    private final zzzp zzSm;
    private final zzaaf zzSn;
    private final zzij zzSo;
    zzahp zzSp;

    public zzzq(Context context, zzaaf zzaaf, zzzp zzzp, zzij zzij) {
        this.zzSm = zzzp;
        this.mContext = context;
        this.zzSn = zzaaf;
        this.zzSo = zzij;
    }

    private final zziv zzb(zzaae zzaae) throws zzzt {
        int i = 1;
        if (this.zzMM == null || this.zzMM.zzwn == null || this.zzMM.zzwn.size() <= 1) {
            i = 0;
        }
        if (i != 0 && this.zzMu != null && !this.zzMu.zzMp) {
            return null;
        }
        if (this.zzQR.zzAw) {
            for (zziv zziv : zzaae.zzvX.zzAu) {
                if (zziv.zzAw) {
                    return new zziv(zziv, zzaae.zzvX.zzAu);
                }
            }
        }
        if (this.zzQR.zzTr == null) {
            throw new zzzt("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.zzQR.zzTr.split("x");
        if (split.length != 2) {
            String str = "Invalid ad size format from the ad response: ";
            String valueOf = String.valueOf(this.zzQR.zzTr);
            throw new zzzt(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            for (zziv zziv2 : zzaae.zzvX.zzAu) {
                float f = this.mContext.getResources().getDisplayMetrics().density;
                i = zziv2.width == -1 ? (int) (((float) zziv2.widthPixels) / f) : zziv2.width;
                int i2 = zziv2.height == -2 ? (int) (((float) zziv2.heightPixels) / f) : zziv2.height;
                if (parseInt == i && parseInt2 == i2 && !zziv2.zzAw) {
                    return new zziv(zziv2, zzaae.zzvX.zzAu);
                }
            }
            str = "The ad size from the ad response was not one of the requested sizes: ";
            valueOf = String.valueOf(this.zzQR.zzTr);
            throw new zzzt(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        } catch (NumberFormatException e) {
            str = "Invalid ad size number from the ad response: ";
            valueOf = String.valueOf(this.zzQR.zzTr);
            throw new zzzt(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
    }

    private final void zzd(int i, String str) {
        if (i == 3 || i == -1) {
            zzajc.zzaS(str);
        } else {
            zzajc.zzaT(str);
        }
        if (this.zzQR == null) {
            this.zzQR = new zzaai(i);
        } else {
            this.zzQR = new zzaai(i, this.zzQR.zzMg);
        }
        this.zzSm.zza(new zzafg(this.zzMM != null ? this.zzMM : new zzaae(this.zzSn, -1, null, null, null), this.zzQR, this.zzMu, null, i, -1, this.zzQR.zzTs, null, this.zzSo));
    }

    public final void onStop() {
        synchronized (this.zzQT) {
            if (this.zzSp != null) {
                this.zzSp.cancel();
            }
        }
    }

    final zzahp zza(zzaje zzaje, zzajp<zzaae> zzajp) {
        Context context = this.mContext;
        if (new zzzv(context).zza(zzaje)) {
            zzajc.zzaC("Fetching ad response from local ad request service.");
            zzahp zzaab = new zzaab(context, zzajp, this);
            zzaab.zzgp();
            return zzaab;
        }
        zzajc.zzaC("Fetching ad response from remote ad request service.");
        zzji.zzds();
        if (zzaiy.zzX(context)) {
            return new zzaac(context, zzaje, zzajp, this);
        }
        zzajc.zzaT("Failed to connect to remote ad request service.");
        return null;
    }

    public final void zza(@NonNull zzaai zzaai) {
        String str;
        zzajc.zzaC("Received ad response.");
        this.zzQR = zzaai;
        long elapsedRealtime = zzbs.zzbF().elapsedRealtime();
        synchronized (this.zzQT) {
            this.zzSp = null;
        }
        zzbs.zzbD().zzf(this.mContext, this.zzQR.zzSV);
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzDY)).booleanValue()) {
            Context context;
            SharedPreferences sharedPreferences;
            Collection stringSet;
            Set hashSet;
            Editor edit;
            if (this.zzQR.zzTh) {
                zzbs.zzbD();
                context = this.mContext;
                str = this.zzMM.zzvR;
                sharedPreferences = context.getSharedPreferences("admob", 0);
                stringSet = sharedPreferences.getStringSet("never_pool_slots", Collections.emptySet());
                if (!stringSet.contains(str)) {
                    hashSet = new HashSet(stringSet);
                    hashSet.add(str);
                    edit = sharedPreferences.edit();
                    edit.putStringSet("never_pool_slots", hashSet);
                    edit.apply();
                }
            } else {
                zzbs.zzbD();
                context = this.mContext;
                str = this.zzMM.zzvR;
                sharedPreferences = context.getSharedPreferences("admob", 0);
                stringSet = sharedPreferences.getStringSet("never_pool_slots", Collections.emptySet());
                if (stringSet.contains(str)) {
                    hashSet = new HashSet(stringSet);
                    hashSet.remove(str);
                    edit = sharedPreferences.edit();
                    edit.putStringSet("never_pool_slots", hashSet);
                    edit.apply();
                }
            }
        }
        try {
            if (this.zzQR.errorCode == -2 || this.zzQR.errorCode == -3) {
                JSONObject jSONObject;
                if (this.zzQR.errorCode != -3) {
                    if (TextUtils.isEmpty(this.zzQR.body)) {
                        throw new zzzt("No fill from ad server.", 3);
                    }
                    zzbs.zzbD().zze(this.mContext, this.zzQR.zzSH);
                    if (this.zzQR.zzTo) {
                        this.zzMu = new zzub(this.zzQR.body);
                        zzbs.zzbD().zzz(this.zzMu.zzMe);
                    } else {
                        zzbs.zzbD().zzz(this.zzQR.zzMe);
                    }
                    if (!TextUtils.isEmpty(this.zzQR.zzSW)) {
                        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzGf)).booleanValue()) {
                            zzajc.zzaC("Received cookie from server. Setting webview cookie in CookieManager.");
                            CookieManager zzS = zzbs.zzbB().zzS(this.mContext);
                            if (zzS != null) {
                                zzS.setCookie("googleads.g.doubleclick.net", this.zzQR.zzSW);
                            }
                        }
                    }
                }
                zziv zzb = this.zzMM.zzvX.zzAu != null ? zzb(this.zzMM) : null;
                zzbs.zzbD().zzx(this.zzQR.zzTy);
                zzbs.zzbD().zzy(this.zzQR.zzTL);
                if (!TextUtils.isEmpty(this.zzQR.zzTw)) {
                    try {
                        jSONObject = new JSONObject(this.zzQR.zzTw);
                    } catch (Throwable e) {
                        zzajc.zzb("Error parsing the JSON for Active View.", e);
                    }
                    this.zzSm.zza(new zzafg(this.zzMM, this.zzQR, this.zzMu, zzb, -2, elapsedRealtime, this.zzQR.zzTs, jSONObject, this.zzSo));
                    zzagz.zzZr.removeCallbacks(this.zzQS);
                    return;
                }
                jSONObject = null;
                this.zzSm.zza(new zzafg(this.zzMM, this.zzQR, this.zzMu, zzb, -2, elapsedRealtime, this.zzQR.zzTs, jSONObject, this.zzSo));
                zzagz.zzZr.removeCallbacks(this.zzQS);
                return;
            }
            throw new zzzt("There was a problem getting an ad response. ErrorCode: " + this.zzQR.errorCode, this.zzQR.errorCode);
        } catch (Throwable e2) {
            zzajc.zzb("Could not parse mediation config.", e2);
            str = "Could not parse mediation config: ";
            String valueOf = String.valueOf(this.zzQR.body);
            throw new zzzt(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        } catch (zzzt e3) {
            zzd(e3.getErrorCode(), e3.getMessage());
            zzagz.zzZr.removeCallbacks(this.zzQS);
        }
    }

    public final void zzbd() {
        zzajc.zzaC("AdLoaderBackgroundTask started.");
        this.zzQS = new zzzr(this);
        zzagz.zzZr.postDelayed(this.zzQS, ((Long) zzbs.zzbL().zzd(zzmo.zzEK)).longValue());
        long elapsedRealtime = zzbs.zzbF().elapsedRealtime();
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzEI)).booleanValue() && this.zzSn.zzSz.extras != null) {
            String string = this.zzSn.zzSz.extras.getString("_ad");
            if (string != null) {
                this.zzMM = new zzaae(this.zzSn, elapsedRealtime, null, null, null);
                zza(zzabt.zza(this.mContext, this.zzMM, string));
                return;
            }
        }
        zzajp zzajt = new zzajt();
        zzagt.zza(new zzzs(this, zzajt));
        String zzu = zzbs.zzbY().zzu(this.mContext);
        String zzv = zzbs.zzbY().zzv(this.mContext);
        String zzw = zzbs.zzbY().zzw(this.mContext);
        zzbs.zzbY().zzh(this.mContext, zzw);
        this.zzMM = new zzaae(this.zzSn, elapsedRealtime, zzu, zzv, zzw);
        zzajt.zzf(this.zzMM);
    }
}
