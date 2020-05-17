package com.applovin.sdk;

public class AppLovinSdkSettings {
    /* renamed from: a */
    private boolean f593a = false;
    /* renamed from: b */
    private boolean f594b = false;
    /* renamed from: c */
    private long f595c = -1;
    /* renamed from: d */
    private String f596d = AppLovinAdSize.INTERSTITIAL.getLabel();
    /* renamed from: e */
    private String f597e = (AppLovinAdType.INCENTIVIZED.getLabel() + "," + AppLovinAdType.REGULAR.getLabel() + "," + AppLovinAdType.NATIVE.getLabel());
    /* renamed from: f */
    private boolean f598f = false;

    public String getAutoPreloadSizes() {
        return this.f596d;
    }

    public String getAutoPreloadTypes() {
        return this.f597e;
    }

    public long getBannerAdRefreshSeconds() {
        return this.f595c;
    }

    public boolean isMuted() {
        return this.f598f;
    }

    public boolean isTestAdsEnabled() {
        return this.f593a;
    }

    public boolean isVerboseLoggingEnabled() {
        return this.f594b;
    }

    public void setAutoPreloadSizes(String str) {
        this.f596d = str;
    }

    public void setAutoPreloadTypes(String str) {
        this.f597e = str;
    }

    public void setBannerAdRefreshSeconds(long j) {
        this.f595c = j;
    }

    public void setMuted(boolean z) {
        this.f598f = z;
    }

    public void setTestAdsEnabled(boolean z) {
        this.f593a = z;
    }

    public void setVerboseLogging(boolean z) {
        this.f594b = z;
    }
}
