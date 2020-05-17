package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.widget.FrameLayout;
import com.facebook.internal.NativeProtocol;

@zzzn
public class zziz {
    private final Object mLock = new Object();
    private final zziq zzAA;
    private final zzip zzAB;
    private final zzli zzAC;
    private final zzqc zzAD;
    private final zzadh zzAE;
    private final zzww zzAF;
    private zzkh zzAz;

    @VisibleForTesting
    abstract class zza<T> {
        private /* synthetic */ zziz zzAI;

        zza(zziz zziz) {
            this.zzAI = zziz;
        }

        @Nullable
        protected abstract T zza(zzkh zzkh) throws RemoteException;

        @Nullable
        protected abstract T zzdo() throws RemoteException;

        @Nullable
        protected final T zzdp() {
            T t = null;
            zzkh zza = this.zzAI.zzdn();
            if (zza == null) {
                zzajc.zzaT("ClientApi class cannot be loaded.");
            } else {
                try {
                    t = zza(zza);
                } catch (Throwable e) {
                    zzajc.zzc("Cannot invoke local loader using ClientApi class", e);
                }
            }
            return t;
        }

        @Nullable
        protected final T zzdq() {
            try {
                return zzdo();
            } catch (Throwable e) {
                zzajc.zzc("Cannot invoke remote loader", e);
                return null;
            }
        }
    }

    public zziz(zziq zziq, zzip zzip, zzli zzli, zzqc zzqc, zzadh zzadh, zzww zzww) {
        this.zzAA = zziq;
        this.zzAB = zzip;
        this.zzAC = zzli;
        this.zzAD = zzqc;
        this.zzAE = zzadh;
        this.zzAF = zzww;
    }

    @VisibleForTesting
    static <T> T zza(Context context, boolean z, zza<T> zza) {
        boolean z2 = true;
        if (!z) {
            zzji.zzds();
            if (!zzaiy.zzX(context)) {
                zzajc.zzaC("Google Play Services is not available");
                z = true;
            }
        }
        zzji.zzds();
        int zzQ = zzaiy.zzQ(context);
        zzji.zzds();
        if (zzQ <= zzaiy.zzP(context)) {
            z2 = z;
        }
        T zzdp;
        if (z2) {
            zzdp = zza.zzdp();
            return zzdp == null ? zza.zzdq() : zzdp;
        } else {
            zzdp = zza.zzdq();
            return zzdp == null ? zza.zzdp() : zzdp;
        }
    }

    private static void zzb(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "no_ads_fallback");
        bundle.putString("flow", str);
        zzji.zzds().zza(context, null, "gmob-apps", bundle, true);
    }

    @Nullable
    private static zzkh zzdm() {
        try {
            Object newInstance = zziz.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return zzki.asInterface((IBinder) newInstance);
            }
            zzajc.zzaT("ClientApi class is not an instance of IBinder");
            return null;
        } catch (Throwable e) {
            zzajc.zzc("Failed to instantiate ClientApi class.", e);
            return null;
        }
    }

    @Nullable
    private final zzkh zzdn() {
        zzkh zzkh;
        synchronized (this.mLock) {
            if (this.zzAz == null) {
                this.zzAz = zzdm();
            }
            zzkh = this.zzAz;
        }
        return zzkh;
    }

    public final zzow zza(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return (zzow) zza(context, false, new zzjf(this, frameLayout, frameLayout2, context));
    }

    public final zzju zzb(Context context, String str, zzuq zzuq) {
        return (zzju) zza(context, false, new zzjd(this, context, str, zzuq));
    }

    @Nullable
    public final zzwx zzb(Activity activity) {
        boolean z = false;
        String str = "com.google.android.gms.ads.internal.overlay.useClientJar";
        Intent intent = activity.getIntent();
        if (intent.hasExtra(str)) {
            z = intent.getBooleanExtra(str, false);
        } else {
            zzajc.m1216e("useClientJar flag not found in activity intent extras.");
        }
        return (zzwx) zza((Context) activity, z, new zzjh(this, activity));
    }
}
