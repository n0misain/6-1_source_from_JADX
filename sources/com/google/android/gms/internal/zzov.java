package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zzov extends Image {
    private final Drawable mDrawable;
    private final Uri mUri;
    private final double zzHA;
    private final zzos zzID;

    public zzov(zzos zzos) {
        Drawable drawable;
        double d;
        Uri uri = null;
        this.zzID = zzos;
        try {
            IObjectWrapper zzeg = this.zzID.zzeg();
            if (zzeg != null) {
                drawable = (Drawable) zzn.zzE(zzeg);
                this.mDrawable = drawable;
                uri = this.zzID.getUri();
                this.mUri = uri;
                d = 1.0d;
                d = this.zzID.getScale();
                this.zzHA = d;
            }
        } catch (Throwable e) {
            zzajc.zzb("Failed to get drawable.", e);
        }
        Object obj = uri;
        this.mDrawable = drawable;
        try {
            uri = this.zzID.getUri();
        } catch (Throwable e2) {
            zzajc.zzb("Failed to get uri.", e2);
        }
        this.mUri = uri;
        d = 1.0d;
        try {
            d = this.zzID.getScale();
        } catch (Throwable e3) {
            zzajc.zzb("Failed to get scale.", e3);
        }
        this.zzHA = d;
    }

    public final Drawable getDrawable() {
        return this.mDrawable;
    }

    public final double getScale() {
        return this.zzHA;
    }

    public final Uri getUri() {
        return this.mUri;
    }
}
