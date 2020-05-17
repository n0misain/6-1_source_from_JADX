package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinAd;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class co extends dd {
    public co(AppLovinSdkImpl appLovinSdkImpl) {
        super(appLovinSdkImpl);
    }

    /* renamed from: a */
    di mo2251a(C0505i c0505i) {
        di efVar = new ef(this.a, 1, this);
        efVar.m961a(true);
        return efVar;
    }

    /* renamed from: a */
    C0505i mo2252a(bu buVar) {
        return C0505i.f933h;
    }

    /* renamed from: a */
    Map mo2253a() {
        Map hashMap = new HashMap(1);
        hashMap.put(C0505i.f933h, new de(((Integer) this.a.get(dj.bc)).intValue()));
        return hashMap;
    }

    /* renamed from: a */
    public void mo2254a(C0505i c0505i, int i) {
    }

    /* renamed from: a */
    void mo2255a(Object obj, bu buVar) {
        AppLovinNativeAdLoadListener appLovinNativeAdLoadListener = (AppLovinNativeAdLoadListener) obj;
        appLovinNativeAdLoadListener.onNativeAdsLoaded(Arrays.asList(new AppLovinNativeAd[]{(AppLovinNativeAd) buVar}));
    }

    /* renamed from: a */
    void mo2256a(Object obj, C0505i c0505i, int i) {
        ((AppLovinNativeAdLoadListener) obj).onNativeAdsFailedToLoad(i);
    }

    public void adReceived(AppLovinAd appLovinAd) {
    }

    /* renamed from: b */
    public /* bridge */ /* synthetic */ bu mo2258b(C0505i c0505i) {
        return super.mo2258b(c0505i);
    }

    /* renamed from: b */
    public /* bridge */ /* synthetic */ void mo2259b(C0505i c0505i, Object obj) {
        super.mo2259b(c0505i, obj);
    }

    /* renamed from: c */
    public /* bridge */ /* synthetic */ boolean mo2260c(C0505i c0505i) {
        return super.mo2260c(c0505i);
    }

    /* renamed from: d */
    public /* bridge */ /* synthetic */ void mo2261d(C0505i c0505i) {
        super.mo2261d(c0505i);
    }

    /* renamed from: e */
    public /* bridge */ /* synthetic */ boolean mo2262e(C0505i c0505i) {
        return super.mo2262e(c0505i);
    }

    /* renamed from: f */
    public /* bridge */ /* synthetic */ void mo2263f(C0505i c0505i) {
        super.mo2263f(c0505i);
    }

    public void failedToReceiveAd(int i) {
    }

    public void onNativeAdsFailedToLoad(int i) {
        m707b(C0505i.f933h, i);
    }

    public void onNativeAdsLoaded(List list) {
        AppLovinNativeAd appLovinNativeAd = (AppLovinNativeAd) list.get(0);
        if (((Boolean) this.a.get(dj.bI)).booleanValue()) {
            this.a.getNativeAdService().precacheResources(appLovinNativeAd, new cp(this));
        } else {
            m709c((bu) appLovinNativeAd);
        }
    }
}
