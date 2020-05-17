package com.applovin.mediation;

import java.util.Map;

public class AppLovinMediatedAdInfo {
    /* renamed from: a */
    private final Map f979a;

    public AppLovinMediatedAdInfo(Map map) {
        this.f979a = map;
    }

    public boolean containsKey(String str) {
        return this.f979a != null ? this.f979a.containsKey(str) : false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppLovinMediatedAdInfo)) {
            return false;
        }
        AppLovinMediatedAdInfo appLovinMediatedAdInfo = (AppLovinMediatedAdInfo) obj;
        return this.f979a != null ? this.f979a.equals(appLovinMediatedAdInfo.f979a) : appLovinMediatedAdInfo.f979a == null;
    }

    public Object get(String str) {
        return this.f979a != null ? this.f979a.get(str) : null;
    }

    public int hashCode() {
        return this.f979a != null ? this.f979a.hashCode() : 0;
    }

    public String toString() {
        return "AppLovinMediatedAdInfo{adInfo=" + this.f979a + "}";
    }
}
