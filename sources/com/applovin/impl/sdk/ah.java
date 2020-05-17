package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

public final class ah implements AppLovinAd {
    /* renamed from: a */
    public String m513a() {
        return "<html><head></head><body></body></html>";
    }

    public long getAdIdNumber() {
        return 0;
    }

    public AppLovinAdSize getSize() {
        return AppLovinAdSize.BANNER;
    }

    public AppLovinAdType getType() {
        return AppLovinAdType.REGULAR;
    }

    public boolean isVideoAd() {
        return false;
    }
}
