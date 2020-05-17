package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.zzn;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@zzzn
public final class zzacs extends zzd implements zzadt {
    private static zzacs zzWr;
    private static final zzup zzWs = new zzup();
    private final Map<String, zzadz> zzWt = new HashMap();
    private boolean zzWu;
    private boolean zzuj;

    public zzacs(Context context, zzv zzv, zziv zziv, zzuq zzuq, zzaje zzaje) {
        super(context, zziv, null, zzuq, zzaje, zzv);
        zzWr = this;
    }

    private static zzafg zzc(zzafg zzafg) {
        zzafr.m1217v("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            String jSONObject = zzabt.zzb(zzafg.zzXY).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zzafg.zzUj.zzvR);
            zzua zzua = new zzua(jSONObject, null, Arrays.asList(new String[]{"com.google.ads.mediation.admob.AdMobAdapter"}), null, null, Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null);
            return new zzafg(zzafg.zzUj, zzafg.zzXY, new zzub(Arrays.asList(new zzua[]{zzua}), ((Long) zzbs.zzbL().zzd(zzmo.zzEL)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1, 0, 1, null, 0, -1, -1, false), zzafg.zzvX, zzafg.errorCode, zzafg.zzXR, zzafg.zzXS, zzafg.zzXL, zzafg.zzXX);
        } catch (Throwable e) {
            zzajc.zzb("Unable to generate ad state for non-mediated rewarded video.", e);
            return new zzafg(zzafg.zzUj, zzafg.zzXY, null, zzafg.zzvX, 0, zzafg.zzXR, zzafg.zzXS, zzafg.zzXL, zzafg.zzXX);
        }
    }

    public static zzacs zzgO() {
        return zzWr;
    }

    public final void destroy() {
        String valueOf;
        zzbo.zzcz("destroy must be called on the main UI thread.");
        for (String valueOf2 : this.zzWt.keySet()) {
            try {
                zzadz zzadz = (zzadz) this.zzWt.get(valueOf2);
                if (!(zzadz == null || zzadz.zzgW() == null)) {
                    zzadz.zzgW().destroy();
                }
            } catch (RemoteException e) {
                String str = "Fail to destroy adapter: ";
                valueOf2 = String.valueOf(valueOf2);
                zzajc.zzaT(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
            }
        }
    }

    public final boolean isLoaded() {
        zzbo.zzcz("isLoaded must be called on the main UI thread.");
        return this.zzsP.zzvV == null && this.zzsP.zzvW == null && this.zzsP.zzvY != null && !this.zzWu;
    }

    public final void onContextChanged(@NonNull Context context) {
        for (zzadz zzgW : this.zzWt.values()) {
            try {
                zzgW.zzgW().zzk(zzn.zzw(context));
            } catch (Throwable e) {
                zzajc.zzb("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public final void onRewardedVideoAdClosed() {
        zzap();
    }

    public final void onRewardedVideoAdLeftApplication() {
        zzaq();
    }

    public final void onRewardedVideoAdOpened() {
        zza(this.zzsP.zzvY, false);
        zzar();
    }

    public final void onRewardedVideoStarted() {
        if (!(this.zzsP.zzvY == null || this.zzsP.zzvY.zzMG == null)) {
            zzbs.zzbS();
            zzuj.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzsP.zzvY, this.zzsP.zzvR, false, this.zzsP.zzvY.zzMG.zzLQ);
        }
        zzav();
    }

    public final void pause() {
        String valueOf;
        zzbo.zzcz("pause must be called on the main UI thread.");
        for (String valueOf2 : this.zzWt.keySet()) {
            try {
                zzadz zzadz = (zzadz) this.zzWt.get(valueOf2);
                if (!(zzadz == null || zzadz.zzgW() == null)) {
                    zzadz.zzgW().pause();
                }
            } catch (RemoteException e) {
                String str = "Fail to pause adapter: ";
                valueOf2 = String.valueOf(valueOf2);
                zzajc.zzaT(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
            }
        }
    }

    public final void resume() {
        String valueOf;
        zzbo.zzcz("resume must be called on the main UI thread.");
        for (String valueOf2 : this.zzWt.keySet()) {
            try {
                zzadz zzadz = (zzadz) this.zzWt.get(valueOf2);
                if (!(zzadz == null || zzadz.zzgW() == null)) {
                    zzadz.zzgW().resume();
                }
            } catch (RemoteException e) {
                String str = "Fail to resume adapter: ";
                valueOf2 = String.valueOf(valueOf2);
                zzajc.zzaT(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
            }
        }
    }

    public final void setImmersiveMode(boolean z) {
        zzbo.zzcz("setImmersiveMode must be called on the main UI thread.");
        this.zzuj = z;
    }

    public final void zza(zzadj zzadj) {
        zzbo.zzcz("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty(zzadj.zzvR)) {
            zzajc.zzaT("Invalid ad unit id. Aborting.");
            zzagz.zzZr.post(new zzact(this));
            return;
        }
        this.zzWu = false;
        this.zzsP.zzvR = zzadj.zzvR;
        super.zza(zzadj.zzSz);
    }

    public final void zza(zzafg zzafg, zznb zznb) {
        if (zzafg.errorCode != -2) {
            zzagz.zzZr.post(new zzacu(this, zzafg));
            return;
        }
        this.zzsP.zzvZ = zzafg;
        if (zzafg.zzXN == null) {
            this.zzsP.zzvZ = zzc(zzafg);
        }
        this.zzsP.zzwt = 0;
        zzbt zzbt = this.zzsP;
        zzbs.zzby();
        zzahp zzadw = new zzadw(this.zzsP.zzqD, this.zzsP.zzvZ, this);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(zzadw.getClass().getName());
        zzajc.zzaC(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        zzadw.zzgp();
        zzbt.zzvW = zzadw;
    }

    public final boolean zza(zzaff zzaff, zzaff zzaff2) {
        return true;
    }

    protected final boolean zza(zzir zzir, zzaff zzaff, boolean z) {
        return false;
    }

    protected final void zzap() {
        this.zzsP.zzvY = null;
        super.zzap();
    }

    @Nullable
    public final zzadz zzav(String str) {
        zzadz zzadz;
        Throwable th;
        String str2;
        String valueOf;
        zzadz zzadz2 = (zzadz) this.zzWt.get(str);
        if (zzadz2 != null) {
            return zzadz2;
        }
        try {
            zzadz = new zzadz(("com.google.ads.mediation.admob.AdMobAdapter".equals(str) ? zzWs : this.zzsX).zzah(str), this);
            try {
                this.zzWt.put(str, zzadz);
                return zzadz;
            } catch (Throwable e) {
                th = e;
                str2 = "Fail to instantiate adapter ";
                valueOf = String.valueOf(str);
                zzajc.zzc(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf), th);
                return zzadz;
            }
        } catch (Throwable e2) {
            th = e2;
            zzadz = zzadz2;
            str2 = "Fail to instantiate adapter ";
            valueOf = String.valueOf(str);
            if (valueOf.length() == 0) {
            }
            zzajc.zzc(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf), th);
            return zzadz;
        }
    }

    public final void zzc(@Nullable zzaee zzaee) {
        if (!(this.zzsP.zzvY == null || this.zzsP.zzvY.zzMG == null)) {
            zzbs.zzbS();
            zzuj.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzsP.zzvY, this.zzsP.zzvR, false, this.zzsP.zzvY.zzMG.zzLR);
        }
        if (!(this.zzsP.zzvY == null || this.zzsP.zzvY.zzXN == null || TextUtils.isEmpty(this.zzsP.zzvY.zzXN.zzMh))) {
            zzaee = new zzaee(this.zzsP.zzvY.zzXN.zzMh, this.zzsP.zzvY.zzXN.zzMi);
        }
        zza(zzaee);
    }

    public final void zzgP() {
        zzbo.zzcz("showAd must be called on the main UI thread.");
        if (isLoaded()) {
            this.zzWu = true;
            zzadz zzav = zzav(this.zzsP.zzvY.zzMI);
            if (zzav != null && zzav.zzgW() != null) {
                try {
                    zzav.zzgW().setImmersiveMode(this.zzuj);
                    zzav.zzgW().showVideo();
                    return;
                } catch (Throwable e) {
                    zzajc.zzc("Could not call showVideo.", e);
                    return;
                }
            }
            return;
        }
        zzajc.zzaT("The reward video has not loaded.");
    }

    public final void zzgQ() {
        onAdClicked();
    }
}
