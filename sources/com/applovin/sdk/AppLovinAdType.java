package com.applovin.sdk;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AppLovinAdType {
    public static final AppLovinAdType INCENTIVIZED = new AppLovinAdType("VIDEOA");
    public static final AppLovinAdType NATIVE = new AppLovinAdType("NATIVE");
    public static final AppLovinAdType REGULAR = new AppLovinAdType("REGULAR");
    /* renamed from: a */
    private final String f985a;

    public AppLovinAdType(String str) {
        this.f985a = str;
    }

    public static Set allTypes() {
        Set hashSet = new HashSet(2);
        hashSet.add(REGULAR);
        hashSet.add(INCENTIVIZED);
        return hashSet;
    }

    public static AppLovinAdType fromString(String str) {
        return str.toUpperCase(Locale.ENGLISH).equals(INCENTIVIZED.getLabel()) ? INCENTIVIZED : REGULAR;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppLovinAdType appLovinAdType = (AppLovinAdType) obj;
        if (this.f985a != null) {
            if (this.f985a.equals(appLovinAdType.f985a)) {
                return true;
            }
        } else if (appLovinAdType.f985a == null) {
            return true;
        }
        return false;
    }

    public String getLabel() {
        return this.f985a.toUpperCase(Locale.ENGLISH);
    }

    public int hashCode() {
        return this.f985a != null ? this.f985a.hashCode() : 0;
    }

    public String toString() {
        return getLabel();
    }
}
