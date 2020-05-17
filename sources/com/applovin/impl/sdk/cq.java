package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.nativeAds.AppLovinNativeAdService;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class cq implements AppLovinNativeAdService {
    /* renamed from: a */
    private final AppLovinSdkImpl f687a;
    /* renamed from: b */
    private final Object f688b = new Object();

    cq(AppLovinSdkImpl appLovinSdkImpl) {
        this.f687a = appLovinSdkImpl;
    }

    /* renamed from: a */
    private List m728a(AppLovinNativeAd appLovinNativeAd) {
        List arrayList = new ArrayList(1);
        arrayList.add((NativeAdImpl) appLovinNativeAd);
        return arrayList;
    }

    /* renamed from: a */
    private void m735a(AppLovinNativeAd appLovinNativeAd, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        if (appLovinNativeAd.isVideoPrecached()) {
            appLovinNativeAdPrecacheListener.onNativeAdVideoPreceached(appLovinNativeAd);
            return;
        }
        this.f687a.m466a().m992a(new dw(this.f687a, m728a(appLovinNativeAd), new ct(this, appLovinNativeAdPrecacheListener)), ej.MAIN);
    }

    /* renamed from: a */
    private void m736a(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, int i) {
        if (appLovinNativeAdLoadListener != null) {
            try {
                appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(i);
            } catch (Throwable e) {
                this.f687a.getLogger().userError("WidgetServiceImpl", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    /* renamed from: a */
    private void m737a(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, List list) {
        if (appLovinNativeAdLoadListener != null) {
            try {
                appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
            } catch (Throwable e) {
                this.f687a.getLogger().userError("WidgetServiceImpl", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    /* renamed from: a */
    private void m738a(AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, AppLovinNativeAd appLovinNativeAd, int i, boolean z) {
        if (appLovinNativeAdPrecacheListener == null) {
            return;
        }
        if (z) {
            try {
                appLovinNativeAdPrecacheListener.onNativeAdVideoPrecachingFailed(appLovinNativeAd, i);
                return;
            } catch (Throwable e) {
                this.f687a.getLogger().userError("WidgetServiceImpl", "Encountered exception whilst notifying user callback", e);
                return;
            }
        }
        appLovinNativeAdPrecacheListener.onNativeAdImagePrecachingFailed(appLovinNativeAd, i);
    }

    /* renamed from: a */
    private void m739a(AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, AppLovinNativeAd appLovinNativeAd, boolean z) {
        if (appLovinNativeAdPrecacheListener == null) {
            return;
        }
        if (z) {
            try {
                appLovinNativeAdPrecacheListener.onNativeAdVideoPreceached(appLovinNativeAd);
                return;
            } catch (Throwable e) {
                this.f687a.getLogger().userError("WidgetServiceImpl", "Encountered exception whilst notifying user callback", e);
                return;
            }
        }
        appLovinNativeAdPrecacheListener.onNativeAdImagesPrecached(appLovinNativeAd);
    }

    /* renamed from: b */
    private void m740b(List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.f687a.m466a().m992a(new du(this.f687a, list, new cw(this, appLovinNativeAdLoadListener)), ej.MAIN);
    }

    /* renamed from: c */
    private void m741c(List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.f687a.m466a().m992a(new dw(this.f687a, list, new cx(this, appLovinNativeAdLoadListener)), ej.MAIN);
    }

    /* renamed from: a */
    public void m742a(List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        int intValue = ((Integer) this.f687a.get(dj.bj)).intValue();
        if (intValue > 0) {
            list = list;
            int size = list.size();
            if (size != 0) {
                intValue = Math.min(intValue, size);
                List subList = list.subList(0, intValue);
                m740b(subList, new cu(this, subList, appLovinNativeAdLoadListener, list.subList(intValue, size)));
            } else if (appLovinNativeAdLoadListener != null) {
                appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD);
            }
        } else if (appLovinNativeAdLoadListener != null) {
            appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
        }
    }

    public void loadNativeAds(int i, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        AppLovinNativeAd appLovinNativeAd = null;
        synchronized (this.f688b) {
            if (i == 1) {
                if (this.f687a.m470d().mo2262e(C0505i.f933h)) {
                    appLovinNativeAd = (AppLovinNativeAd) this.f687a.m470d().mo2258b(C0505i.f933h);
                }
            }
        }
        if (appLovinNativeAd != null) {
            m737a(appLovinNativeAdLoadListener, Arrays.asList(new AppLovinNativeAd[]{appLovinNativeAd}));
            return;
        }
        this.f687a.m466a().m992a(new ef(this.f687a, i, new cr(this, appLovinNativeAdLoadListener)), ej.MAIN);
    }

    public void precacheResources(AppLovinNativeAd appLovinNativeAd, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        if (appLovinNativeAd.isImagePrecached()) {
            appLovinNativeAdPrecacheListener.onNativeAdImagesPrecached(appLovinNativeAd);
            m735a(appLovinNativeAd, appLovinNativeAdPrecacheListener);
            return;
        }
        this.f687a.m466a().m992a(new du(this.f687a, m728a(appLovinNativeAd), new cs(this, appLovinNativeAdPrecacheListener)), ej.MAIN);
    }
}
