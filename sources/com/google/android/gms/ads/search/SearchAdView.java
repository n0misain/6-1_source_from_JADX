package com.google.android.gms.ads.search;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class SearchAdView extends ViewGroup {
    private final zzlc zzrZ;

    public SearchAdView(Context context) {
        super(context);
        this.zzrZ = new zzlc(this);
    }

    public SearchAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzrZ = new zzlc(this, attributeSet, false);
    }

    public SearchAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzrZ = new zzlc(this, attributeSet, false);
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

    public final String getAdUnitId() {
        return this.zzrZ.getAdUnitId();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(DynamicHeightSearchAdRequest dynamicHeightSearchAdRequest) {
        if (AdSize.SEARCH.equals(getAdSize())) {
            this.zzrZ.zza(dynamicHeightSearchAdRequest.zzab());
            return;
        }
        throw new IllegalStateException("You must use AdSize.SEARCH for a DynamicHeightSearchAdRequest");
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(SearchAdRequest searchAdRequest) {
        this.zzrZ.zza(searchAdRequest.zzab());
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

    public final void resume() {
        this.zzrZ.resume();
    }

    public final void setAdListener(AdListener adListener) {
        this.zzrZ.setAdListener(adListener);
    }

    public final void setAdSize(AdSize adSize) {
        this.zzrZ.setAdSizes(adSize);
    }

    public final void setAdUnitId(String str) {
        this.zzrZ.setAdUnitId(str);
    }
}
