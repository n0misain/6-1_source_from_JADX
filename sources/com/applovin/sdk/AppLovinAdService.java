package com.applovin.sdk;

public interface AppLovinAdService {
    public static final String URI_AD_SERVICE = "/adservice";
    @Deprecated
    public static final String URI_API_SERVICE = "/api";
    public static final String URI_CLOSE_AD = "/adservice/close_ad";
    public static final String URI_CONTRACT_AD = "/adservice/contract_ad";
    public static final String URI_EXPAND_AD = "/adservice/expand_ad";
    @Deprecated
    public static final String URI_LAUNCH_APP = "/launch";
    @Deprecated
    public static final String URI_NEXT_AD = "/adservice/next_ad";

    @Deprecated
    void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener);

    void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize);

    boolean hasPreloadedAd(AppLovinAdSize appLovinAdSize);

    void loadNextAd(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener);

    void loadNextMediatedAd(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener);

    void preloadAd(AppLovinAdSize appLovinAdSize);

    void removeAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize);
}
