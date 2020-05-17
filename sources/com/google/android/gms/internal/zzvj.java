package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzzn
public final class zzvj extends zzuu {
    private final MediationAdapter zzNa;
    private zzvk zzNb;

    public zzvj(MediationAdapter mediationAdapter) {
        this.zzNa = mediationAdapter;
    }

    private final Bundle zza(String str, zzir zzir, String str2) throws RemoteException {
        String str3 = "Server parameters: ";
        String valueOf = String.valueOf(str);
        zzajc.zzaT(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    valueOf = (String) keys.next();
                    bundle2.putString(valueOf, jSONObject.getString(valueOf));
                }
                bundle = bundle2;
            }
            if (this.zzNa instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                if (zzir != null) {
                    bundle.putInt("tagForChildDirectedTreatment", zzir.zzzR);
                }
            }
            return bundle;
        } catch (Throwable th) {
            zzajc.zzc("Could not get Server Parameters Bundle.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void destroy() throws RemoteException {
        try {
            this.zzNa.onDestroy();
        } catch (Throwable th) {
            zzajc.zzc("Could not destroy adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final Bundle getInterstitialAdapterInfo() {
        if (this.zzNa instanceof zzalj) {
            return ((zzalj) this.zzNa).getInterstitialAdapterInfo();
        }
        String str = "MediationAdapter is not a v2 MediationInterstitialAdapter: ";
        String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
        zzajc.zzaT(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public final zzks getVideoController() {
        if (!(this.zzNa instanceof zza)) {
            return null;
        }
        try {
            return ((zza) this.zzNa).getVideoController();
        } catch (Throwable th) {
            zzajc.zzc("Could not get video controller.", th);
            return null;
        }
    }

    public final IObjectWrapper getView() throws RemoteException {
        if (this.zzNa instanceof MediationBannerAdapter) {
            try {
                return zzn.zzw(((MediationBannerAdapter) this.zzNa).getBannerView());
            } catch (Throwable th) {
                zzajc.zzc("Could not get banner view from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
            zzajc.zzaT(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final boolean isInitialized() throws RemoteException {
        if (this.zzNa instanceof MediationRewardedVideoAdAdapter) {
            zzajc.zzaC("Check if adapter is initialized.");
            try {
                return ((MediationRewardedVideoAdAdapter) this.zzNa).isInitialized();
            } catch (Throwable th) {
                zzajc.zzc("Could not check if adapter is initialized.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
            zzajc.zzaT(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final void pause() throws RemoteException {
        try {
            this.zzNa.onPause();
        } catch (Throwable th) {
            zzajc.zzc("Could not pause adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void resume() throws RemoteException {
        try {
            this.zzNa.onResume();
        } catch (Throwable th) {
            zzajc.zzc("Could not resume adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
        if (this.zzNa instanceof OnImmersiveModeUpdatedListener) {
            try {
                ((OnImmersiveModeUpdatedListener) this.zzNa).onImmersiveModeUpdated(z);
                return;
            } catch (Throwable th) {
                zzajc.zzc("Could not set immersive mode.", th);
                return;
            }
        }
        String str = "MediationAdapter is not an OnImmersiveModeUpdatedListener: ";
        String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
        zzajc.zzaS(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public final void showInterstitial() throws RemoteException {
        if (this.zzNa instanceof MediationInterstitialAdapter) {
            zzajc.zzaC("Showing interstitial from adapter.");
            try {
                ((MediationInterstitialAdapter) this.zzNa).showInterstitial();
            } catch (Throwable th) {
                zzajc.zzc("Could not show interstitial from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
            zzajc.zzaT(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final void showVideo() throws RemoteException {
        if (this.zzNa instanceof MediationRewardedVideoAdAdapter) {
            zzajc.zzaC("Show rewarded video ad from adapter.");
            try {
                ((MediationRewardedVideoAdAdapter) this.zzNa).showVideo();
            } catch (Throwable th) {
                zzajc.zzc("Could not show rewarded video ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
            zzajc.zzaT(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzaea zzaea, List<String> list) throws RemoteException {
        String str;
        if (this.zzNa instanceof InitializableMediationRewardedVideoAdAdapter) {
            zzajc.zzaC("Initialize rewarded video adapter.");
            try {
                InitializableMediationRewardedVideoAdAdapter initializableMediationRewardedVideoAdAdapter = (InitializableMediationRewardedVideoAdAdapter) this.zzNa;
                List arrayList = new ArrayList();
                for (String str2 : list) {
                    arrayList.add(zza(str2, null, null));
                }
                initializableMediationRewardedVideoAdAdapter.initialize((Context) zzn.zzE(iObjectWrapper), new zzaed(zzaea), arrayList);
            } catch (Throwable th) {
                zzajc.zzc("Could not initialize rewarded video adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            str2 = "MediationAdapter is not an InitializableMediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
            zzajc.zzaT(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzir zzir, String str, zzaea zzaea, String str2) throws RemoteException {
        if (this.zzNa instanceof MediationRewardedVideoAdAdapter) {
            zzajc.zzaC("Initialize rewarded video adapter.");
            try {
                Bundle bundle;
                MediationAdRequest mediationAdRequest;
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzNa;
                Bundle zza = zza(str2, zzir, null);
                if (zzir != null) {
                    zzvi zzvi = new zzvi(zzir.zzzN == -1 ? null : new Date(zzir.zzzN), zzir.zzzO, zzir.zzzP != null ? new HashSet(zzir.zzzP) : null, zzir.zzzV, zzir.zzzQ, zzir.zzzR, zzir.zzAc);
                    if (zzir.zzzX != null) {
                        bundle = zzir.zzzX.getBundle(mediationRewardedVideoAdAdapter.getClass().getName());
                        mediationAdRequest = zzvi;
                    } else {
                        bundle = null;
                        Object obj = zzvi;
                    }
                } else {
                    bundle = null;
                    mediationAdRequest = null;
                }
                mediationRewardedVideoAdAdapter.initialize((Context) zzn.zzE(iObjectWrapper), mediationAdRequest, str, new zzaed(zzaea), zza, bundle);
            } catch (Throwable th) {
                zzajc.zzc("Could not initialize rewarded video adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
            zzajc.zzaT(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzir zzir, String str, zzuw zzuw) throws RemoteException {
        zza(iObjectWrapper, zzir, str, null, zzuw);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzir zzir, String str, String str2, zzuw zzuw) throws RemoteException {
        if (this.zzNa instanceof MediationInterstitialAdapter) {
            zzajc.zzaC("Requesting interstitial ad from adapter.");
            try {
                MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zzNa;
                mediationInterstitialAdapter.requestInterstitialAd((Context) zzn.zzE(iObjectWrapper), new zzvk(zzuw), zza(str, zzir, str2), new zzvi(zzir.zzzN == -1 ? null : new Date(zzir.zzzN), zzir.zzzO, zzir.zzzP != null ? new HashSet(zzir.zzzP) : null, zzir.zzzV, zzir.zzzQ, zzir.zzzR, zzir.zzAc), zzir.zzzX != null ? zzir.zzzX.getBundle(mediationInterstitialAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzajc.zzc("Could not request interstitial ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
            zzajc.zzaT(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzir zzir, String str, String str2, zzuw zzuw, zzon zzon, List<String> list) throws RemoteException {
        if (this.zzNa instanceof MediationNativeAdapter) {
            try {
                MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) this.zzNa;
                zzvn zzvn = new zzvn(zzir.zzzN == -1 ? null : new Date(zzir.zzzN), zzir.zzzO, zzir.zzzP != null ? new HashSet(zzir.zzzP) : null, zzir.zzzV, zzir.zzzQ, zzir.zzzR, zzon, list, zzir.zzAc);
                Bundle bundle = zzir.zzzX != null ? zzir.zzzX.getBundle(mediationNativeAdapter.getClass().getName()) : null;
                this.zzNb = new zzvk(zzuw);
                mediationNativeAdapter.requestNativeAd((Context) zzn.zzE(iObjectWrapper), this.zzNb, zza(str, zzir, str2), zzvn, bundle);
            } catch (Throwable th) {
                zzajc.zzc("Could not request native ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationNativeAdapter: ";
            String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
            zzajc.zzaT(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zziv zziv, zzir zzir, String str, zzuw zzuw) throws RemoteException {
        zza(iObjectWrapper, zziv, zzir, str, null, zzuw);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zziv zziv, zzir zzir, String str, String str2, zzuw zzuw) throws RemoteException {
        if (this.zzNa instanceof MediationBannerAdapter) {
            zzajc.zzaC("Requesting banner ad from adapter.");
            try {
                MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzNa;
                mediationBannerAdapter.requestBannerAd((Context) zzn.zzE(iObjectWrapper), new zzvk(zzuw), zza(str, zzir, str2), zzb.zza(zziv.width, zziv.height, zziv.zzAs), new zzvi(zzir.zzzN == -1 ? null : new Date(zzir.zzzN), zzir.zzzO, zzir.zzzP != null ? new HashSet(zzir.zzzP) : null, zzir.zzzV, zzir.zzzQ, zzir.zzzR, zzir.zzAc), zzir.zzzX != null ? zzir.zzzX.getBundle(mediationBannerAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzajc.zzc("Could not request banner ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
            zzajc.zzaT(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zza(zzir zzir, String str, String str2) throws RemoteException {
        if (this.zzNa instanceof MediationRewardedVideoAdAdapter) {
            zzajc.zzaC("Requesting rewarded video ad from adapter.");
            try {
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzNa;
                mediationRewardedVideoAdAdapter.loadAd(new zzvi(zzir.zzzN == -1 ? null : new Date(zzir.zzzN), zzir.zzzO, zzir.zzzP != null ? new HashSet(zzir.zzzP) : null, zzir.zzzV, zzir.zzzQ, zzir.zzzR, zzir.zzAc), zza(str, zzir, str2), zzir.zzzX != null ? zzir.zzzX.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzajc.zzc("Could not load rewarded video ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
            zzajc.zzaT(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zzc(zzir zzir, String str) throws RemoteException {
        zza(zzir, str, null);
    }

    public final zzvc zzfq() {
        NativeAdMapper zzfx = this.zzNb.zzfx();
        return zzfx instanceof NativeAppInstallAdMapper ? new zzvl((NativeAppInstallAdMapper) zzfx) : null;
    }

    public final zzvf zzfr() {
        NativeAdMapper zzfx = this.zzNb.zzfx();
        return zzfx instanceof NativeContentAdMapper ? new zzvm((NativeContentAdMapper) zzfx) : null;
    }

    public final Bundle zzfs() {
        if (this.zzNa instanceof zzali) {
            return ((zzali) this.zzNa).zzfs();
        }
        String str = "MediationAdapter is not a v2 MediationBannerAdapter: ";
        String valueOf = String.valueOf(this.zzNa.getClass().getCanonicalName());
        zzajc.zzaT(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public final Bundle zzft() {
        return new Bundle();
    }

    public final boolean zzfu() {
        return this.zzNa instanceof InitializableMediationRewardedVideoAdAdapter;
    }

    public final zzpj zzfv() {
        NativeCustomTemplateAd zzfy = this.zzNb.zzfy();
        return zzfy instanceof zzpm ? ((zzpm) zzfy).zzex() : null;
    }

    public final void zzk(IObjectWrapper iObjectWrapper) throws RemoteException {
        try {
            ((OnContextChangedListener) this.zzNa).onContextChanged((Context) zzn.zzE(iObjectWrapper));
        } catch (Throwable th) {
            zzajc.zza("Could not inform adapter of changed context", th);
        }
    }
}
