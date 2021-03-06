package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzjz;
import com.google.android.gms.internal.zzlc;

public final class PublisherAdView extends ViewGroup {
    private final zzlc zzrZ;

    public PublisherAdView(Context context) {
        super(context);
        this.zzrZ = new zzlc(this);
    }

    public PublisherAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzrZ = new zzlc(this, attributeSet, true);
        zzbo.zzb((Object) context, (Object) "Context cannot be null");
    }

    public PublisherAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzrZ = new zzlc(this, attributeSet, true);
    }

    public final void destroy() {
        this.zzrZ.destroy();
    }

    public final AdListener getAdListener() {
        return this.zzrZ.getAdListener();
    }

    public final AdSize getAdSize() {
        return this.zzrZ.getAdSize();
    }

    public final AdSize[] getAdSizes() {
        return this.zzrZ.getAdSizes();
    }

    public final String getAdUnitId() {
        return this.zzrZ.getAdUnitId();
    }

    public final AppEventListener getAppEventListener() {
        return this.zzrZ.getAppEventListener();
    }

    public final String getMediationAdapterClassName() {
        return this.zzrZ.getMediationAdapterClassName();
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzrZ.getOnCustomRenderedAdLoadedListener();
    }

    public final VideoController getVideoController() {
        return this.zzrZ.getVideoController();
    }

    public final VideoOptions getVideoOptions() {
        return this.zzrZ.getVideoOptions();
    }

    public final boolean isLoading() {
        return this.zzrZ.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(PublisherAdRequest publisherAdRequest) {
        this.zzrZ.zza(publisherAdRequest.zzab());
    }

    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = ((i3 - i) - measuredWidth) / 2;
            int i6 = ((i4 - i2) - measuredHeight) / 2;
            childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
        }
    }

    protected final void onMeasure(int i, int i2) {
        int widthInPixels;
        int i3 = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            AdSize adSize;
            AdSize adSize2 = null;
            try {
                adSize = getAdSize();
            } catch (Throwable e) {
                zzajc.zzb("Unable to retrieve ad size.", e);
                adSize = adSize2;
            }
            if (adSize != null) {
                Context context = getContext();
                widthInPixels = adSize.getWidthInPixels(context);
                i3 = adSize.getHeightInPixels(context);
            } else {
                widthInPixels = 0;
            }
        } else {
            measureChild(childAt, i, i2);
            widthInPixels = childAt.getMeasuredWidth();
            i3 = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(widthInPixels, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
    }

    public final void pause() {
        this.zzrZ.pause();
    }

    public final void recordManualImpression() {
        this.zzrZ.recordManualImpression();
    }

    public final void resume() {
        this.zzrZ.resume();
    }

    public final void setAdListener(AdListener adListener) {
        this.zzrZ.setAdListener(adListener);
    }

    public final void setAdSizes(AdSize... adSizeArr) {
        if (adSizeArr == null || adSizeArr.length <= 0) {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
        }
        this.zzrZ.zza(adSizeArr);
    }

    public final void setAdUnitId(String str) {
        this.zzrZ.setAdUnitId(str);
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        this.zzrZ.setAppEventListener(appEventListener);
    }

    public final void setCorrelator(Correlator correlator) {
        this.zzrZ.setCorrelator(correlator);
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzrZ.setManualImpressionsEnabled(z);
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzrZ.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener);
    }

    public final void setVideoOptions(VideoOptions videoOptions) {
        this.zzrZ.setVideoOptions(videoOptions);
    }

    public final boolean zza(zzjz zzjz) {
        return this.zzrZ.zza(zzjz);
    }
}
