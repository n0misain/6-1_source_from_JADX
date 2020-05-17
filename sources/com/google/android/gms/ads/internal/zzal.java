package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import com.facebook.internal.NativeProtocol;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.js.zzai;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzu;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzaai;
import com.google.android.gms.internal.zzabt;
import com.google.android.gms.internal.zzaee;
import com.google.android.gms.internal.zzaet;
import com.google.android.gms.internal.zzaev;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafg;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzakb;
import com.google.android.gms.internal.zzakm;
import com.google.android.gms.internal.zzge;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zzrm;
import com.google.android.gms.internal.zzru;
import com.google.android.gms.internal.zzrv;
import com.google.android.gms.internal.zzua;
import com.google.android.gms.internal.zzub;
import com.google.android.gms.internal.zzuq;
import com.google.android.gms.internal.zzzn;
import java.util.Collections;
import org.json.JSONObject;

@zzzn
public final class zzal extends zzi implements zzrm, zzrv {
    private transient boolean zzuf = false;
    private int zzug = -1;
    private boolean zzuh;
    private float zzui;
    private boolean zzuj;
    private final zzaev zzuk;
    private String zzul;
    private final String zzum;

    public zzal(Context context, zziv zziv, String str, zzuq zzuq, zzaje zzaje, zzv zzv) {
        super(context, zziv, str, zzuq, zzaje, zzv);
        this.zzuk = zzbs.zzbY().zzs(context) ? new zzaev(context, str) : null;
        String str2 = (zziv == null || !"reward_mb".equals(zziv.zzAs)) ? "/Interstitial" : "/Rewarded";
        this.zzum = str2;
    }

    private static zzafg zzb(zzafg zzafg) {
        try {
            String jSONObject = zzabt.zzb(zzafg.zzXY).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zzafg.zzUj.zzvR);
            zzua zzua = new zzua(jSONObject, null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null);
            zzaai zzaai = zzafg.zzXY;
            zzub zzub = new zzub(Collections.singletonList(zzua), ((Long) zzbs.zzbL().zzd(zzmo.zzEL)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), zzaai.zzMd, zzaai.zzMe, "", -1, 0, 1, null, 0, -1, -1, false);
            return new zzafg(zzafg.zzUj, new zzaai(zzafg.zzUj, zzaai.zzPi, zzaai.body, Collections.emptyList(), Collections.emptyList(), zzaai.zzTn, true, zzaai.zzTp, Collections.emptyList(), zzaai.zzMg, zzaai.orientation, zzaai.zzTr, zzaai.zzTs, zzaai.zzTt, zzaai.zzTu, zzaai.zzTv, null, zzaai.zzTx, zzaai.zzAv, zzaai.zzSH, zzaai.zzTy, zzaai.zzTz, zzaai.zzTC, zzaai.zzAw, zzaai.zzAx, null, Collections.emptyList(), Collections.emptyList(), zzaai.zzTG, zzaai.zzTH, zzaai.zzSV, zzaai.zzSW, zzaai.zzMd, zzaai.zzMe, zzaai.zzTI, null, zzaai.zzTK, zzaai.zzTL, zzaai.zzTh), zzub, zzafg.zzvX, zzafg.errorCode, zzafg.zzXR, zzafg.zzXS, null, zzafg.zzXX);
        } catch (Throwable e) {
            zzajc.zzb("Unable to generate ad state for an interstitial ad with pooling.", e);
            return zzafg;
        }
    }

    private final void zzb(Bundle bundle) {
        zzbs.zzbz().zzb(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, "gmob-apps", bundle, false);
    }

    public final void setImmersiveMode(boolean z) {
        zzbo.zzcz("setImmersiveMode must be called on the main UI thread.");
        this.zzuj = z;
    }

    public final void showInterstitial() {
        zzbo.zzcz("showInterstitial must be called on the main UI thread.");
        if (zzbs.zzbY().zzs(this.zzsP.zzqD)) {
            this.zzul = zzbs.zzbY().zzt(this.zzsP.zzqD);
            String valueOf = String.valueOf(this.zzul);
            String valueOf2 = String.valueOf(this.zzum);
            this.zzul = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        if (this.zzsP.zzvY == null) {
            zzajc.zzaT("The interstitial has not loaded.");
            return;
        }
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzEv)).booleanValue()) {
            Bundle bundle;
            valueOf2 = this.zzsP.zzqD.getApplicationContext() != null ? this.zzsP.zzqD.getApplicationContext().getPackageName() : this.zzsP.zzqD.getPackageName();
            if (!this.zzuf) {
                zzajc.zzaT("It is not recommended to show an interstitial before onAdLoaded completes.");
                bundle = new Bundle();
                bundle.putString("appid", valueOf2);
                bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "show_interstitial_before_load_finish");
                zzb(bundle);
            }
            zzbs.zzbz();
            if (!zzagz.zzJ(this.zzsP.zzqD)) {
                zzajc.zzaT("It is not recommended to show an interstitial when app is not in foreground.");
                bundle = new Bundle();
                bundle.putString("appid", valueOf2);
                bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "show_interstitial_app_not_in_foreground");
                zzb(bundle);
            }
        }
        if (!this.zzsP.zzcd()) {
            if (this.zzsP.zzvY.zzTo && this.zzsP.zzvY.zzMH != null) {
                try {
                    if (((Boolean) zzbs.zzbL().zzd(zzmo.zzDT)).booleanValue()) {
                        this.zzsP.zzvY.zzMH.setImmersiveMode(this.zzuj);
                    }
                    this.zzsP.zzvY.zzMH.showInterstitial();
                } catch (Throwable e) {
                    zzajc.zzc("Could not show interstitial.", e);
                    zzbb();
                }
            } else if (this.zzsP.zzvY.zzPg == null) {
                zzajc.zzaT("The interstitial failed to load.");
            } else if (this.zzsP.zzvY.zzPg.zziA()) {
                zzajc.zzaT("The interstitial is already showing.");
            } else {
                Bitmap zzK;
                this.zzsP.zzvY.zzPg.zzA(true);
                if (this.zzsP.zzvY.zzXL != null) {
                    this.zzsR.zza(this.zzsP.zzvX, this.zzsP.zzvY);
                }
                zzaff zzaff = this.zzsP.zzvY;
                if (zzaff.zzcn()) {
                    new zzge(this.zzsP.zzqD, zzaff.zzPg.getView()).zza(zzaff.zzPg);
                } else {
                    zzaff.zzPg.zziw().zza(new zzam(this, zzaff));
                }
                if (this.zzsP.zzur) {
                    zzbs.zzbz();
                    zzK = zzagz.zzK(this.zzsP.zzqD);
                } else {
                    zzK = null;
                }
                this.zzug = zzbs.zzbU().zzb(zzK);
                if (!((Boolean) zzbs.zzbL().zzd(zzmo.zzFh)).booleanValue() || zzK == null) {
                    zzap zzap = new zzap(this.zzsP.zzur, zzba(), false, 0.0f, -1, this.zzuj);
                    int requestedOrientation = this.zzsP.zzvY.zzPg.getRequestedOrientation();
                    if (requestedOrientation == -1) {
                        requestedOrientation = this.zzsP.zzvY.orientation;
                    }
                    AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(this, this, this, this.zzsP.zzvY.zzPg, requestedOrientation, this.zzsP.zzvT, this.zzsP.zzvY.zzTt, zzap);
                    zzbs.zzbx();
                    zzu.zza(this.zzsP.zzqD, adOverlayInfoParcel, true);
                    return;
                }
                new zzan(this, this.zzug).zzhL();
            }
        }
    }

    protected final zzaka zza(zzafg zzafg, @Nullable zzw zzw, @Nullable zzaet zzaet) throws zzakm {
        zzaka zza = zzbs.zzbA().zza(this.zzsP.zzqD, this.zzsP.zzvX, false, false, this.zzsP.zzvS, this.zzsP.zzvT, this.zzsK, this, this.zzsS, zzafg.zzXX);
        zza.zziw().zza(this, null, this, this, ((Boolean) zzbs.zzbL().zzd(zzmo.zzDn)).booleanValue(), this, zzw, null, zzaet);
        zza((zzai) zza);
        zza.zzaV(zzafg.zzUj.zzSM);
        zza.zziw().zza("/reward", new zzru(this));
        return zza;
    }

    public final void zza(zzafg zzafg, zznb zznb) {
        Object obj = 1;
        if (!((Boolean) zzbs.zzbL().zzd(zzmo.zzDY)).booleanValue()) {
            super.zza(zzafg, zznb);
        } else if (zzafg.errorCode != -2) {
            super.zza(zzafg, zznb);
        } else {
            Bundle bundle = zzafg.zzUj.zzSz.zzzX.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
            Object obj2 = (bundle == null || !bundle.containsKey("gw")) ? 1 : null;
            if (zzafg.zzXY.zzTo) {
                obj = null;
            }
            if (!(obj2 == null || r2 == null)) {
                this.zzsP.zzvZ = zzb(zzafg);
            }
            super.zza(this.zzsP.zzvZ, zznb);
        }
    }

    public final void zza(boolean z, float f) {
        this.zzuh = z;
        this.zzui = f;
    }

    public final boolean zza(@Nullable zzaff zzaff, zzaff zzaff2) {
        if (!super.zza(zzaff, zzaff2)) {
            return false;
        }
        if (!(this.zzsP.zzcc() || this.zzsP.zzws == null || zzaff2.zzXL == null)) {
            this.zzsR.zza(this.zzsP.zzvX, zzaff2, this.zzsP.zzws);
        }
        return true;
    }

    protected final boolean zza(zzir zzir, zzaff zzaff, boolean z) {
        if (this.zzsP.zzcc() && zzaff.zzPg != null) {
            zzbs.zzbB();
            zzahe.zzk(zzaff.zzPg);
        }
        return this.zzsO.zzbo();
    }

    public final boolean zza(zzir zzir, zznb zznb) {
        if (this.zzsP.zzvY == null) {
            return super.zza(zzir, zznb);
        }
        zzajc.zzaT("An interstitial is already loading. Aborting.");
        return false;
    }

    public final void zzaA() {
        super.zzaA();
        this.zzsR.zzg(this.zzsP.zzvY);
        if (zzbs.zzbY().zzs(this.zzsP.zzqD)) {
            this.zzuk.zzu(false);
        }
    }

    public final void zzaB() {
        recordImpression();
        super.zzaB();
        if (!(this.zzsP.zzvY == null || this.zzsP.zzvY.zzPg == null)) {
            zzakb zziw = this.zzsP.zzvY.zzPg.zziw();
            if (zziw != null) {
                zziw.zziV();
            }
        }
        if (zzbs.zzbY().zzs(this.zzsP.zzqD)) {
            if (!(this.zzsP.zzvY == null || this.zzsP.zzvY.zzPg == null)) {
                zzbs.zzbY().zze(this.zzsP.zzvY.zzPg.getContext(), this.zzul);
            }
            this.zzuk.zzu(true);
        }
    }

    protected final void zzap() {
        zzbb();
        super.zzap();
    }

    protected final void zzas() {
        super.zzas();
        this.zzuf = true;
    }

    public final void zzb(zzaee zzaee) {
        if (this.zzsP.zzvY != null) {
            if (this.zzsP.zzvY.zzTF != null) {
                zzbs.zzbz();
                zzagz.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzsP.zzvY.zzTF);
            }
            if (this.zzsP.zzvY.zzTD != null) {
                zzaee = this.zzsP.zzvY.zzTD;
            }
        }
        zza(zzaee);
    }

    protected final boolean zzba() {
        if (!(this.zzsP.zzqD instanceof Activity)) {
            return false;
        }
        Window window = ((Activity) this.zzsP.zzqD).getWindow();
        if (window == null || window.getDecorView() == null) {
            return false;
        }
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        window.getDecorView().getGlobalVisibleRect(rect, null);
        window.getDecorView().getWindowVisibleDisplayFrame(rect2);
        return (rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top) ? false : true;
    }

    public final void zzbb() {
        zzbs.zzbU().zzb(Integer.valueOf(this.zzug));
        if (this.zzsP.zzcc()) {
            this.zzsP.zzca();
            this.zzsP.zzvY = null;
            this.zzsP.zzur = false;
            this.zzuf = false;
        }
    }

    public final void zzbc() {
        if (!(this.zzsP.zzvY == null || this.zzsP.zzvY.zzXQ == null)) {
            zzbs.zzbz();
            zzagz.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzsP.zzvY.zzXQ);
        }
        zzav();
    }

    public final void zzc(boolean z) {
        this.zzsP.zzur = z;
    }
}
