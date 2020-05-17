package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.List;

public class dw extends dv {
    public dw(AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdVideos", appLovinSdkImpl, list, appLovinNativeAdLoadListener);
    }

    public dw(AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdVideos", appLovinSdkImpl, list, appLovinNativeAdPrecacheListener);
    }

    /* renamed from: b */
    private boolean m922b(NativeAdImpl nativeAdImpl) {
        this.e.mo2294w("TaskCacheNativeAdVideos", "Unable to cache video resource " + nativeAdImpl.getSourceVideoUrl());
        mo2277a(nativeAdImpl, !C0518x.m1184a(this.f) ? AppLovinErrorCodes.NO_NETWORK : AppLovinErrorCodes.UNABLE_TO_PRECACHE_VIDEO_RESOURCES);
        return false;
    }

    /* renamed from: a */
    protected void mo2276a(NativeAdImpl nativeAdImpl) {
        if (this.b != null) {
            this.b.onNativeAdVideoPreceached(nativeAdImpl);
        }
    }

    /* renamed from: a */
    protected void mo2277a(NativeAdImpl nativeAdImpl, int i) {
        if (this.b != null) {
            this.b.onNativeAdVideoPrecachingFailed(nativeAdImpl, i);
        }
    }

    /* renamed from: a */
    protected boolean mo2278a(NativeAdImpl nativeAdImpl, al alVar) {
        if (AppLovinSdkUtils.isValidString(nativeAdImpl.getSourceVideoUrl())) {
            this.d.getLogger().mo2288d("TaskCacheNativeAdVideos", "Beginning slot video caching for ad " + nativeAdImpl.getAdId());
            if (((Boolean) this.d.get(dj.f752G)).booleanValue()) {
                String a = m914a(nativeAdImpl.getSourceVideoUrl(), alVar);
                if (a == null) {
                    return m922b(nativeAdImpl);
                }
                nativeAdImpl.setVideoUrl(a);
            } else {
                this.d.getLogger().mo2288d("TaskCacheNativeAdVideos", "Resource caching is disabled, skipping...");
            }
            return true;
        }
        this.d.getLogger().mo2288d("TaskCacheNativeAdVideos", "No video attached to ad, nothing to cache...");
        return true;
    }

    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }
}
