package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

@zzzn
public final class zzpe extends NativeAppInstallAd {
    private final VideoController zzBd = new VideoController();
    private final zzpb zzIE;
    private final List<Image> zzIF = new ArrayList();
    private final zzov zzIG;

    public zzpe(zzpb zzpb) {
        zzov zzov;
        this.zzIE = zzpb;
        try {
            List images = this.zzIE.getImages();
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
            zzos zzeh = this.zzIE.zzeh();
            if (zzeh != null) {
                zzov = new zzov(zzeh);
                this.zzIG = zzov;
            }
        } catch (Throwable e2) {
            zzajc.zzb("Failed to get icon.", e2);
        }
        zzov = null;
        this.zzIG = zzov;
    }

    private final IObjectWrapper zzei() {
        try {
            return this.zzIE.zzei();
        } catch (Throwable e) {
            zzajc.zzb("Failed to retrieve native ad engine.", e);
            return null;
        }
    }

    public final void destroy() {
        try {
            this.zzIE.destroy();
        } catch (Throwable e) {
            zzajc.zzb("Failed to destroy", e);
        }
    }

    public final CharSequence getBody() {
        try {
            return this.zzIE.getBody();
        } catch (Throwable e) {
            zzajc.zzb("Failed to get body.", e);
            return null;
        }
    }

    public final CharSequence getCallToAction() {
        try {
            return this.zzIE.getCallToAction();
        } catch (Throwable e) {
            zzajc.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            return this.zzIE.getExtras();
        } catch (Throwable e) {
            zzajc.zzb("Failed to get extras", e);
            return null;
        }
    }

    public final CharSequence getHeadline() {
        try {
            return this.zzIE.getHeadline();
        } catch (Throwable e) {
            zzajc.zzb("Failed to get headline.", e);
            return null;
        }
    }

    public final Image getIcon() {
        return this.zzIG;
    }

    public final List<Image> getImages() {
        return this.zzIF;
    }

    public final CharSequence getPrice() {
        try {
            return this.zzIE.getPrice();
        } catch (Throwable e) {
            zzajc.zzb("Failed to get price.", e);
            return null;
        }
    }

    public final Double getStarRating() {
        Double d = null;
        try {
            double starRating = this.zzIE.getStarRating();
            if (starRating != -1.0d) {
                d = Double.valueOf(starRating);
            }
        } catch (Throwable e) {
            zzajc.zzb("Failed to get star rating.", e);
        }
        return d;
    }

    public final CharSequence getStore() {
        try {
            return this.zzIE.getStore();
        } catch (Throwable e) {
            zzajc.zzb("Failed to get store", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzIE.getVideoController() != null) {
                this.zzBd.zza(this.zzIE.getVideoController());
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
