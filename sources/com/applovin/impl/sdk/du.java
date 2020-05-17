package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.List;

public class du extends dv {
    public du(AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdImages", appLovinSdkImpl, list, appLovinNativeAdLoadListener);
    }

    public du(AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdImages", appLovinSdkImpl, list, appLovinNativeAdPrecacheListener);
    }

    /* renamed from: b */
    private boolean m918b(NativeAdImpl nativeAdImpl) {
        this.e.mo2294w("TaskCacheNativeAdImages", "Unable to cache image resource");
        mo2277a(nativeAdImpl, !C0518x.m1184a(this.f) ? AppLovinErrorCodes.NO_NETWORK : AppLovinErrorCodes.UNABLE_TO_PRECACHE_IMAGE_RESOURCES);
        return false;
    }

    /* renamed from: a */
    protected void mo2276a(NativeAdImpl nativeAdImpl) {
        if (this.b != null) {
            this.b.onNativeAdImagesPrecached(nativeAdImpl);
        }
    }

    /* renamed from: a */
    protected void mo2277a(NativeAdImpl nativeAdImpl, int i) {
        if (this.b != null) {
            this.b.onNativeAdImagePrecachingFailed(nativeAdImpl, i);
        }
    }

    /* renamed from: a */
    protected boolean mo2278a(NativeAdImpl nativeAdImpl, al alVar) {
        this.d.getLogger().mo2288d("TaskCacheNativeAdImages", "Beginning slot image caching for ad " + nativeAdImpl.getAdId());
        if (((Boolean) this.d.get(dj.f752G)).booleanValue()) {
            String a = m914a(nativeAdImpl.getSourceIconUrl(), alVar);
            if (a == null) {
                return m918b(nativeAdImpl);
            }
            nativeAdImpl.setIconUrl(a);
            a = m914a(nativeAdImpl.getSourceImageUrl(), alVar);
            if (a == null) {
                return m918b(nativeAdImpl);
            }
            nativeAdImpl.setImageUrl(a);
        } else {
            this.d.getLogger().mo2288d("TaskCacheNativeAdImages", "Resource caching is disabled, skipping...");
        }
        return true;
    }

    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }
}
