package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zznd implements CustomRenderedAd {
    private final zzne zzHh;

    public zznd(zzne zzne) {
        this.zzHh = zzne;
    }

    public final String getBaseUrl() {
        try {
            return this.zzHh.zzdX();
        } catch (Throwable e) {
            zzajc.zzc("Could not delegate getBaseURL to CustomRenderedAd", e);
            return null;
        }
    }

    public final String getContent() {
        try {
            return this.zzHh.getContent();
        } catch (Throwable e) {
            zzajc.zzc("Could not delegate getContent to CustomRenderedAd", e);
            return null;
        }
    }

    public final void onAdRendered(View view) {
        try {
            this.zzHh.zzi(view != null ? zzn.zzw(view) : null);
        } catch (Throwable e) {
            zzajc.zzc("Could not delegate onAdRendered to CustomRenderedAd", e);
        }
    }

    public final void recordClick() {
        try {
            this.zzHh.recordClick();
        } catch (Throwable e) {
            zzajc.zzc("Could not delegate recordClick to CustomRenderedAd", e);
        }
    }

    public final void recordImpression() {
        try {
            this.zzHh.recordImpression();
        } catch (Throwable e) {
            zzajc.zzc("Could not delegate recordImpression to CustomRenderedAd", e);
        }
    }
}
