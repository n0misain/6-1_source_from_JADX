package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.List;

abstract class dv extends di {
    /* renamed from: a */
    protected AppLovinNativeAdLoadListener f817a;
    /* renamed from: b */
    protected AppLovinNativeAdPrecacheListener f818b;
    /* renamed from: g */
    private List f819g;
    /* renamed from: h */
    private int f820h = 0;

    dv(String str, AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super(str, appLovinSdkImpl);
        this.f817a = appLovinNativeAdLoadListener;
        this.f819g = list;
    }

    dv(String str, AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super(str, appLovinSdkImpl);
        if (list == null) {
            throw new IllegalArgumentException("Slots cannot be null");
        }
        this.f819g = list;
        this.f818b = appLovinNativeAdPrecacheListener;
    }

    /* renamed from: a */
    private void m912a(int i) {
        if (this.f817a != null) {
            this.f817a.onNativeAdsFailedToLoad(i);
        }
    }

    /* renamed from: a */
    private void m913a(List list) {
        if (this.f817a != null) {
            this.f817a.onNativeAdsLoaded(list);
        }
    }

    /* renamed from: a */
    protected String m914a(String str, al alVar) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            this.d.getLogger().mo2288d(m676a(), "Asked to cache file with null/empty URL, nothing to do.");
            return null;
        } else if (fk.m1089a(this.d, str)) {
            try {
                String a = alVar.m520a(this.f, str, true, null, true);
                if (a != null) {
                    return a;
                }
                this.e.mo2294w(m676a(), "Unable to cache icon resource " + str);
                return null;
            } catch (Throwable e) {
                this.e.mo2295w(m676a(), "Unable to cache icon resource " + str, e);
                return null;
            }
        } else {
            this.d.getLogger().mo2288d(m676a(), "Domain is not whitelisted, skipping precache for URL " + str);
            return null;
        }
    }

    /* renamed from: a */
    protected abstract void mo2276a(NativeAdImpl nativeAdImpl);

    /* renamed from: a */
    protected abstract void mo2277a(NativeAdImpl nativeAdImpl, int i);

    /* renamed from: a */
    protected abstract boolean mo2278a(NativeAdImpl nativeAdImpl, al alVar);

    public void run() {
        for (NativeAdImpl nativeAdImpl : this.f819g) {
            al fileManager = this.d.getFileManager();
            this.d.getLogger().mo2288d(m676a(), "Beginning resource caching phase...");
            if (mo2278a(nativeAdImpl, fileManager)) {
                this.f820h++;
                mo2276a(nativeAdImpl);
            } else {
                this.d.getLogger().mo2289e(m676a(), "Unable to cache resources");
            }
        }
        try {
            if (this.f820h == this.f819g.size()) {
                m913a(this.f819g);
            } else if (((Boolean) this.d.get(dj.aY)).booleanValue()) {
                this.d.getLogger().mo2289e(m676a(), "Mismatch between successful populations and requested size");
                m912a(-6);
            } else {
                m913a(this.f819g);
            }
        } catch (Throwable th) {
            this.d.getLogger().userError(m676a(), "Encountered exception while notifying publisher code", th);
        }
    }
}
