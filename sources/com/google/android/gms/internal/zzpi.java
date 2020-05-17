package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

@zzzn
public final class zzpi extends NativeContentAd {
    private final VideoController zzBd = new VideoController();
    private final List<Image> zzIF = new ArrayList();
    private final zzpf zzIH;
    private final zzov zzII;

    public zzpi(zzpf zzpf) {
        zzov zzov;
        this.zzIH = zzpf;
        try {
            List images = this.zzIH.getImages();
            if (images != null) {
                for (Object next : images) {
                    zzos zzos;
                    Object next2;
                    if (next2 instanceof IBinder) {
                        IBinder iBinder = (IBinder) next2;
                        if (iBinder != null) {
                            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                            if (queryLocalInterface instanceof zzos) {
                                zzos = (zzos) queryLocalInterface;
                            } else {
                                next2 = new zzou(iBinder);
                            }
                            if (zzos != null) {
                                this.zzIF.add(new zzov(zzos));
                            }
                        }
                    }
                    zzos = null;
                    if (zzos != null) {
                        this.zzIF.add(new zzov(zzos));
                    }
                }
            }
        } catch (Throwable e) {
            zzajc.zzb("Failed to get image.", e);
        }
        try {
            zzos zzem = this.zzIH.zzem();
            if (zzem != null) {
                zzov = new zzov(zzem);
                this.zzII = zzov;
            }
        } catch (Throwable e2) {
            zzajc.zzb("Failed to get icon.", e2);
        }
        zzov = null;
        this.zzII = zzov;
    }

    private final IObjectWrapper zzei() {
        try {
            return this.zzIH.zzei();
        } catch (Throwable e) {
            zzajc.zzb("Failed to retrieve native ad engine.", e);
            return null;
        }
    }

    public final void destroy() {
        try {
            this.zzIH.destroy();
        } catch (Throwable e) {
            zzajc.zzb("Failed to destroy", e);
        }
    }

    public final CharSequence getAdvertiser() {
        try {
            return this.zzIH.getAdvertiser();
        } catch (Throwable e) {
            zzajc.zzb("Failed to get attribution.", e);
            return null;
        }
    }

    public final CharSequence getBody() {
        try {
            return this.zzIH.getBody();
        } catch (Throwable e) {
            zzajc.zzb("Failed to get body.", e);
            return null;
        }
    }

    public final CharSequence getCallToAction() {
        try {
            return this.zzIH.getCallToAction();
        } catch (Throwable e) {
            zzajc.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            return this.zzIH.getExtras();
        } catch (Throwable e) {
            zzajc.zzc("Failed to get extras", e);
            return null;
        }
    }

    public final CharSequence getHeadline() {
        try {
            return this.zzIH.getHeadline();
        } catch (Throwable e) {
            zzajc.zzb("Failed to get headline.", e);
            return null;
        }
    }

    public final List<Image> getImages() {
        return this.zzIF;
    }

    public final Image getLogo() {
        return this.zzII;
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzIH.getVideoController() != null) {
                this.zzBd.zza(this.zzIH.getVideoController());
            }
        } catch (Throwable e) {
            zzajc.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzBd;
    }

    protected final /* synthetic */ Object zzag() {
        return zzei();
    }
}
