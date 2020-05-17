package com.google.android.gms.ads.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.overlay.zzag;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzacq;
import com.google.android.gms.internal.zzadd;
import com.google.android.gms.internal.zzaee;
import com.google.android.gms.internal.zzaez;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafg;
import com.google.android.gms.internal.zzafh;
import com.google.android.gms.internal.zzafm;
import com.google.android.gms.internal.zzafn;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzaiy;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzfh;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzis;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzjl;
import com.google.android.gms.internal.zzjo;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzks;
import com.google.android.gms.internal.zzky;
import com.google.android.gms.internal.zzlv;
import com.google.android.gms.internal.zzlx;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzmz;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zznh;
import com.google.android.gms.internal.zzqk;
import com.google.android.gms.internal.zzxg;
import com.google.android.gms.internal.zzxo;
import com.google.android.gms.internal.zzxy;
import com.google.android.gms.internal.zzzn;
import com.google.android.gms.internal.zzzp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;

@zzzn
public abstract class zza extends zzka implements zzag, zzafm, zzim, zzqk, zzxy, zzzp {
    protected zznb zzsK;
    private zzmz zzsL;
    private zzmz zzsM;
    protected boolean zzsN = false;
    protected final zzbi zzsO;
    protected final zzbt zzsP;
    @Nullable
    protected transient zzir zzsQ;
    protected final zzfh zzsR;
    protected final zzv zzsS;

    zza(zzbt zzbt, @Nullable zzbi zzbi, zzv zzv) {
        this.zzsP = zzbt;
        this.zzsO = new zzbi(this);
        this.zzsS = zzv;
        zzbs.zzbz().zzE(this.zzsP.zzqD);
        zzbs.zzbD().zzd(this.zzsP.zzqD, this.zzsP.zzvT);
        zzbs.zzbE().initialize(this.zzsP.zzqD);
        this.zzsR = zzbs.zzbD().zzhG();
        zzbs.zzbC().initialize(this.zzsP.zzqD);
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzFE)).booleanValue()) {
            Timer timer = new Timer();
            timer.schedule(new zzb(this, new CountDownLatch(((Integer) zzbs.zzbL().zzd(zzmo.zzFG)).intValue()), timer), 0, ((Long) zzbs.zzbL().zzd(zzmo.zzFF)).longValue());
        }
    }

    private static long zzr(String str) {
        int indexOf = str.indexOf("ufe");
        int indexOf2 = str.indexOf(44, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        try {
            return Long.parseLong(str.substring(indexOf + 4, indexOf2));
        } catch (IndexOutOfBoundsException e) {
            zzajc.zzaT("Invalid index for Url fetch time in CSI latency info.");
            return -1;
        } catch (NumberFormatException e2) {
            zzajc.zzaT("Cannot find valid format of Url fetch time in CSI latency info.");
            return -1;
        }
    }

    public void destroy() {
        zzbo.zzcz("destroy must be called on the main UI thread.");
        this.zzsO.cancel();
        this.zzsR.zzh(this.zzsP.zzvY);
        zzbt zzbt = this.zzsP;
        if (zzbt.zzvU != null) {
            zzbt.zzvU.zzcg();
        }
        zzbt.zzwc = null;
        zzbt.zzwd = null;
        zzbt.zzwo = null;
        zzbt.zzwe = null;
        zzbt.zze(false);
        if (zzbt.zzvU != null) {
            zzbt.zzvU.removeAllViews();
        }
        zzbt.zzca();
        zzbt.zzcb();
        zzbt.zzvY = null;
    }

    public String getAdUnitId() {
        return this.zzsP.zzvR;
    }

    public zzks getVideoController() {
        return null;
    }

    public final boolean isLoading() {
        return this.zzsN;
    }

    public final boolean isReady() {
        zzbo.zzcz("isLoaded must be called on the main UI thread.");
        return this.zzsP.zzvV == null && this.zzsP.zzvW == null && this.zzsP.zzvY != null;
    }

    public void onAdClicked() {
        if (this.zzsP.zzvY == null) {
            zzajc.zzaT("Ad state was null when trying to ping click URLs.");
            return;
        }
        zzajc.zzaC("Pinging click URLs.");
        if (this.zzsP.zzwa != null) {
            this.zzsP.zzwa.zzhd();
        }
        if (this.zzsP.zzvY.zzMa != null) {
            zzbs.zzbz();
            zzagz.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, zzb(this.zzsP.zzvY.zzMa));
        }
        if (this.zzsP.zzwb != null) {
            try {
                this.zzsP.zzwb.onAdClicked();
            } catch (Throwable e) {
                zzajc.zzc("Could not notify onAdClicked event.", e);
            }
        }
    }

    public final void onAppEvent(String str, @Nullable String str2) {
        if (this.zzsP.zzwd != null) {
            try {
                this.zzsP.zzwd.onAppEvent(str, str2);
            } catch (Throwable e) {
                zzajc.zzc("Could not call the AppEventListener.", e);
            }
        }
    }

    public void pause() {
        zzbo.zzcz("pause must be called on the main UI thread.");
    }

    public void resume() {
        zzbo.zzcz("resume must be called on the main UI thread.");
    }

    public void setImmersiveMode(boolean z) {
        throw new IllegalStateException("onImmersiveModeUpdated is not supported for current ad type");
    }

    public void setManualImpressionsEnabled(boolean z) {
        throw new UnsupportedOperationException("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
    }

    public final void setUserId(String str) {
        zzajc.zzaT("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }

    public final void stopLoading() {
        zzbo.zzcz("stopLoading must be called on the main UI thread.");
        this.zzsN = false;
        this.zzsP.zze(true);
    }

    public final void zza(zzadd zzadd) {
        zzbo.zzcz("setRewardedVideoAdListener can only be called from the UI thread.");
        this.zzsP.zzwp = zzadd;
    }

    protected final void zza(@Nullable zzaee zzaee) {
        if (this.zzsP.zzwp != null) {
            try {
                String str = "";
                int i = 1;
                if (zzaee != null) {
                    str = zzaee.type;
                    i = zzaee.zzWW;
                }
                this.zzsP.zzwp.zza(new zzacq(str, i));
            } catch (Throwable e) {
                zzajc.zzc("Could not call RewardedVideoAdListener.onRewarded().", e);
            }
        }
    }

    public final void zza(zzafg zzafg) {
        if (!(zzafg.zzXY.zzTs == -1 || TextUtils.isEmpty(zzafg.zzXY.zzTB))) {
            long zzr = zzr(zzafg.zzXY.zzTB);
            if (zzr != -1) {
                zzmz zzc = this.zzsK.zzc(zzr + zzafg.zzXY.zzTs);
                this.zzsK.zza(zzc, "stc");
            }
        }
        this.zzsK.zzO(zzafg.zzXY.zzTB);
        this.zzsK.zza(this.zzsL, "arf");
        this.zzsM = this.zzsK.zzdS();
        this.zzsK.zzh("gqi", zzafg.zzXY.zzTC);
        this.zzsP.zzvV = null;
        this.zzsP.zzvZ = zzafg;
        zzafg.zzXX.zza(new zzc(this, zzafg));
        zzafg.zzXX.zzdf();
        zza(zzafg, this.zzsK);
    }

    protected abstract void zza(zzafg zzafg, zznb zznb);

    public final void zza(zziv zziv) {
        zzbo.zzcz("setAdSize must be called on the main UI thread.");
        this.zzsP.zzvX = zziv;
        if (!(this.zzsP.zzvY == null || this.zzsP.zzvY.zzPg == null || this.zzsP.zzwt != 0)) {
            this.zzsP.zzvY.zzPg.zza(zziv);
        }
        if (this.zzsP.zzvU != null) {
            if (this.zzsP.zzvU.getChildCount() > 1) {
                this.zzsP.zzvU.removeView(this.zzsP.zzvU.getNextView());
            }
            this.zzsP.zzvU.setMinimumWidth(zziv.widthPixels);
            this.zzsP.zzvU.setMinimumHeight(zziv.heightPixels);
            this.zzsP.zzvU.requestLayout();
        }
    }

    public final void zza(zzjl zzjl) {
        zzbo.zzcz("setAdListener must be called on the main UI thread.");
        this.zzsP.zzwb = zzjl;
    }

    public final void zza(zzjo zzjo) {
        zzbo.zzcz("setAdListener must be called on the main UI thread.");
        this.zzsP.zzwc = zzjo;
    }

    public final void zza(zzke zzke) {
        zzbo.zzcz("setAppEventListener must be called on the main UI thread.");
        this.zzsP.zzwd = zzke;
    }

    public final void zza(zzkk zzkk) {
        zzbo.zzcz("setCorrelationIdProvider must be called on the main UI thread");
        this.zzsP.zzwe = zzkk;
    }

    public final void zza(@Nullable zzky zzky) {
        zzbo.zzcz("setIconAdOptions must be called on the main UI thread.");
        this.zzsP.zzwl = zzky;
    }

    public final void zza(@Nullable zzlx zzlx) {
        zzbo.zzcz("setVideoOptions must be called on the main UI thread.");
        this.zzsP.zzwk = zzlx;
    }

    public void zza(zznh zznh) {
        throw new IllegalStateException("setOnCustomRenderedAdLoadedListener is not supported for current ad type");
    }

    public void zza(zzxg zzxg) {
        zzajc.zzaT("setInAppPurchaseListener is deprecated and should not be called.");
    }

    public final void zza(zzxo zzxo, String str) {
        zzajc.zzaT("setPlayStorePurchaseParams is deprecated and should not be called.");
    }

    public final void zza(HashSet<zzafh> hashSet) {
        this.zzsP.zza(hashSet);
    }

    boolean zza(zzaff zzaff) {
        return false;
    }

    protected abstract boolean zza(@Nullable zzaff zzaff, zzaff zzaff2);

    public boolean zza(zzir zzir) {
        zzbo.zzcz("loadAd must be called on the main UI thread.");
        zzbs.zzbE().zzcX();
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzDR)).booleanValue()) {
            zzir.zzh(zzir);
        }
        if (zzj.zzaI(this.zzsP.zzqD) && zzir.zzzV != null) {
            zzir = new zzis(zzir).zza(null).zzdj();
        }
        if (this.zzsP.zzvV == null && this.zzsP.zzvW == null) {
            zzajc.zzaS("Starting ad request.");
            this.zzsK = new zznb(((Boolean) zzbs.zzbL().zzd(zzmo.zzCQ)).booleanValue(), "load_ad", this.zzsP.zzvX.zzAs);
            this.zzsL = new zzmz(-1, null, null);
            this.zzsM = new zzmz(-1, null, null);
            this.zzsL = this.zzsK.zzdS();
            if (zzir.zzzQ) {
                zzajc.zzaS("This request is sent from a test device.");
            } else {
                zzji.zzds();
                String valueOf = String.valueOf(zzaiy.zzV(this.zzsP.zzqD));
                zzajc.zzaS(new StringBuilder(String.valueOf(valueOf).length() + 71).append("Use AdRequest.Builder.addTestDevice(\"").append(valueOf).append("\") to get test ads on this device.").toString());
            }
            this.zzsO.zzf(zzir);
            this.zzsN = zza(zzir, this.zzsK);
            return this.zzsN;
        }
        if (this.zzsQ != null) {
            zzajc.zzaT("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
        } else {
            zzajc.zzaT("Loading already in progress, saving this object for future refreshes.");
        }
        this.zzsQ = zzir;
        return false;
    }

    protected abstract boolean zza(zzir zzir, zznb zznb);

    public final zzv zzak() {
        return this.zzsS;
    }

    public final IObjectWrapper zzal() {
        zzbo.zzcz("getAdFrame must be called on the main UI thread.");
        return zzn.zzw(this.zzsP.zzvU);
    }

    @Nullable
    public final zziv zzam() {
        zzbo.zzcz("getAdSize must be called on the main UI thread.");
        return this.zzsP.zzvX == null ? null : new zzlv(this.zzsP.zzvX);
    }

    public final void zzan() {
        zzaq();
    }

    public final void zzao() {
        zzbo.zzcz("recordManualImpression must be called on the main UI thread.");
        if (this.zzsP.zzvY == null) {
            zzajc.zzaT("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        zzajc.zzaC("Pinging manual tracking URLs.");
        if (this.zzsP.zzvY.zzTq != null && !this.zzsP.zzvY.zzXV) {
            zzbs.zzbz();
            zzagz.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzsP.zzvY.zzTq);
            this.zzsP.zzvY.zzXV = true;
        }
    }

    protected void zzap() {
        zzajc.zzaS("Ad closing.");
        if (this.zzsP.zzwc != null) {
            try {
                this.zzsP.zzwc.onAdClosed();
            } catch (Throwable e) {
                zzajc.zzc("Could not call AdListener.onAdClosed().", e);
            }
        }
        if (this.zzsP.zzwp != null) {
            try {
                this.zzsP.zzwp.onRewardedVideoAdClosed();
            } catch (Throwable e2) {
                zzajc.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdClosed().", e2);
            }
        }
    }

    protected final void zzaq() {
        zzajc.zzaS("Ad leaving application.");
        if (this.zzsP.zzwc != null) {
            try {
                this.zzsP.zzwc.onAdLeftApplication();
            } catch (Throwable e) {
                zzajc.zzc("Could not call AdListener.onAdLeftApplication().", e);
            }
        }
        if (this.zzsP.zzwp != null) {
            try {
                this.zzsP.zzwp.onRewardedVideoAdLeftApplication();
            } catch (Throwable e2) {
                zzajc.zzc("Could not call  RewardedVideoAdListener.onRewardedVideoAdLeftApplication().", e2);
            }
        }
    }

    protected final void zzar() {
        zzajc.zzaS("Ad opening.");
        if (this.zzsP.zzwc != null) {
            try {
                this.zzsP.zzwc.onAdOpened();
            } catch (Throwable e) {
                zzajc.zzc("Could not call AdListener.onAdOpened().", e);
            }
        }
        if (this.zzsP.zzwp != null) {
            try {
                this.zzsP.zzwp.onRewardedVideoAdOpened();
            } catch (Throwable e2) {
                zzajc.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdOpened().", e2);
            }
        }
    }

    protected void zzas() {
        zzajc.zzaS("Ad finished loading.");
        this.zzsN = false;
        if (this.zzsP.zzwc != null) {
            try {
                this.zzsP.zzwc.onAdLoaded();
            } catch (Throwable e) {
                zzajc.zzc("Could not call AdListener.onAdLoaded().", e);
            }
        }
        if (this.zzsP.zzwp != null) {
            try {
                this.zzsP.zzwp.onRewardedVideoAdLoaded();
            } catch (Throwable e2) {
                zzajc.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdLoaded().", e2);
            }
        }
    }

    public final void zzat() {
        zzajc.zzaS("Ad impression.");
        if (this.zzsP.zzwc != null) {
            try {
                this.zzsP.zzwc.onAdImpression();
            } catch (Throwable e) {
                zzajc.zzc("Could not call AdListener.onAdImpression().", e);
            }
        }
    }

    public final void zzau() {
        zzajc.zzaS("Ad clicked.");
        if (this.zzsP.zzwc != null) {
            try {
                this.zzsP.zzwc.onAdClicked();
            } catch (Throwable e) {
                zzajc.zzc("Could not call AdListener.onAdClicked().", e);
            }
        }
    }

    protected final void zzav() {
        if (this.zzsP.zzwp != null) {
            try {
                this.zzsP.zzwp.onRewardedVideoStarted();
            } catch (Throwable e) {
                zzajc.zzc("Could not call RewardedVideoAdListener.onVideoStarted().", e);
            }
        }
    }

    public final void zzaw() {
        zzaff zzaff = this.zzsP.zzvY;
        if (zzaff != null && !TextUtils.isEmpty(zzaff.zzTK) && !zzaff.zzXW && zzbs.zzbH().zzic()) {
            zzajc.zzaC("Sending troubleshooting signals to the server.");
            zzbs.zzbH().zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, zzaff.zzTK, this.zzsP.zzvR);
            zzaff.zzXW = true;
        }
    }

    public final zzke zzax() {
        return this.zzsP.zzwd;
    }

    public final zzjo zzay() {
        return this.zzsP.zzwc;
    }

    protected final List<String> zzb(List<String> list) {
        List arrayList = new ArrayList(list.size());
        for (String zzb : list) {
            arrayList.add(zzaez.zzb(zzb, this.zzsP.zzqD));
        }
        return arrayList;
    }

    protected final void zzb(View view) {
        zzbu zzbu = this.zzsP.zzvU;
        if (zzbu != null) {
            zzbu.addView(view, zzbs.zzbB().zzhW());
        }
    }

    public void zzb(zzaff zzaff) {
        this.zzsK.zza(this.zzsM, "awr");
        this.zzsP.zzvW = null;
        if (!(zzaff.errorCode == -2 || zzaff.errorCode == 3)) {
            zzbs.zzbD().zzb(this.zzsP.zzbZ());
        }
        if (zzaff.errorCode == -1) {
            this.zzsN = false;
            return;
        }
        if (zza(zzaff)) {
            zzajc.zzaC("Ad refresh scheduled.");
        }
        if (zzaff.errorCode != -2) {
            zze(zzaff.errorCode);
            return;
        }
        if (this.zzsP.zzwr == null) {
            this.zzsP.zzwr = new zzafn(this.zzsP.zzvR);
        }
        this.zzsR.zzg(this.zzsP.zzvY);
        if (zza(this.zzsP.zzvY, zzaff)) {
            this.zzsP.zzvY = zzaff;
            zzbt zzbt = this.zzsP;
            if (zzbt.zzwa != null) {
                if (zzbt.zzvY != null) {
                    zzbt.zzwa.zzh(zzbt.zzvY.zzXR);
                    zzbt.zzwa.zzi(zzbt.zzvY.zzXS);
                    zzbt.zzwa.zzw(zzbt.zzvY.zzTo);
                }
                zzbt.zzwa.zzv(zzbt.zzvX.zzAt);
            }
            this.zzsK.zzh("is_mraid", this.zzsP.zzvY.zzcn() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            this.zzsK.zzh("is_mediation", this.zzsP.zzvY.zzTo ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            if (!(this.zzsP.zzvY.zzPg == null || this.zzsP.zzvY.zzPg.zziw() == null)) {
                this.zzsK.zzh("is_delay_pl", this.zzsP.zzvY.zzPg.zziw().zziS() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            this.zzsK.zza(this.zzsL, "ttc");
            if (zzbs.zzbD().zzhr() != null) {
                zzbs.zzbD().zzhr().zza(this.zzsK);
            }
            zzaw();
            if (this.zzsP.zzcc()) {
                zzas();
            }
        }
        if (zzaff.zzMd != null) {
            zzbs.zzbz().zza(this.zzsP.zzqD, zzaff.zzMd);
        }
    }

    protected boolean zzb(zzir zzir) {
        if (this.zzsP.zzvU == null) {
            return false;
        }
        ViewParent parent = this.zzsP.zzvU.getParent();
        if (!(parent instanceof View)) {
            return false;
        }
        View view = (View) parent;
        return zzbs.zzbz().zza(view, view.getContext());
    }

    protected void zze(int i) {
        zzajc.zzaT("Failed to load ad: " + i);
        this.zzsN = false;
        if (this.zzsP.zzwc != null) {
            try {
                this.zzsP.zzwc.onAdFailedToLoad(i);
            } catch (Throwable e) {
                zzajc.zzc("Could not call AdListener.onAdFailedToLoad().", e);
            }
        }
        if (this.zzsP.zzwp != null) {
            try {
                this.zzsP.zzwp.onRewardedVideoAdFailedToLoad(i);
            } catch (Throwable e2) {
                zzajc.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdFailedToLoad().", e2);
            }
        }
    }
}
