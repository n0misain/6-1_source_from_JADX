package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.dynamic.zzn;
import java.util.List;

@zzzn
public final class zzpm implements NativeCustomTemplateAd {
    private final VideoController zzBd = new VideoController();
    private final zzpj zzIJ;
    private final MediaView zzIK;

    public zzpm(zzpj zzpj) {
        Context context;
        Throwable e;
        MediaView mediaView;
        MediaView mediaView2 = null;
        this.zzIJ = zzpj;
        try {
            context = (Context) zzn.zzE(zzpj.zzen());
        } catch (NullPointerException e2) {
            e = e2;
            zzajc.zzb("Unable to inflate MediaView.", e);
            context = null;
            if (context != null) {
                mediaView = new MediaView(context);
                try {
                    if (!this.zzIJ.zzj(zzn.zzw(mediaView))) {
                        mediaView = null;
                    }
                    mediaView2 = mediaView;
                } catch (Throwable e3) {
                    zzajc.zzb("Unable to render video in MediaView.", e3);
                }
            }
            this.zzIK = mediaView2;
        } catch (RemoteException e4) {
            e3 = e4;
            zzajc.zzb("Unable to inflate MediaView.", e3);
            context = null;
            if (context != null) {
                mediaView = new MediaView(context);
                if (this.zzIJ.zzj(zzn.zzw(mediaView))) {
                    mediaView = null;
                }
                mediaView2 = mediaView;
            }
            this.zzIK = mediaView2;
        }
        if (context != null) {
            mediaView = new MediaView(context);
            if (this.zzIJ.zzj(zzn.zzw(mediaView))) {
                mediaView = null;
            }
            mediaView2 = mediaView;
        }
        this.zzIK = mediaView2;
    }

    public final void destroy() {
        try {
            this.zzIJ.destroy();
        } catch (Throwable e) {
            zzajc.zzb("Failed to destroy ad.", e);
        }
    }

    public final List<String> getAvailableAssetNames() {
        try {
            return this.zzIJ.getAvailableAssetNames();
        } catch (Throwable e) {
            zzajc.zzb("Failed to get available asset names.", e);
            return null;
        }
    }

    public final String getCustomTemplateId() {
        try {
            return this.zzIJ.getCustomTemplateId();
        } catch (Throwable e) {
            zzajc.zzb("Failed to get custom template id.", e);
            return null;
        }
    }

    public final Image getImage(String str) {
        try {
            zzos zzQ = this.zzIJ.zzQ(str);
            if (zzQ != null) {
                return new zzov(zzQ);
            }
        } catch (Throwable e) {
            zzajc.zzb("Failed to get image.", e);
        }
        return null;
    }

    public final CharSequence getText(String str) {
        try {
            return this.zzIJ.zzP(str);
        } catch (Throwable e) {
            zzajc.zzb("Failed to get string.", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            zzks videoController = this.zzIJ.getVideoController();
            if (videoController != null) {
                this.zzBd.zza(videoController);
            }
        } catch (Throwable e) {
            zzajc.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzBd;
    }

    public final MediaView getVideoMediaView() {
        return this.zzIK;
    }

    public final void performClick(String str) {
        try {
            this.zzIJ.performClick(str);
        } catch (Throwable e) {
            zzajc.zzb("Failed to perform click.", e);
        }
    }

    public final void recordImpression() {
        try {
            this.zzIJ.recordImpression();
        } catch (Throwable e) {
            zzajc.zzb("Failed to record impression.", e);
        }
    }

    public final zzpj zzex() {
        return this.zzIJ;
    }
}
