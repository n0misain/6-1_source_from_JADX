package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/* renamed from: com.applovin.impl.sdk.i */
public class C0505i {
    /* renamed from: a */
    public static final C0505i f926a = C0505i.m1131a(AppLovinAdType.REGULAR, C0506j.DIRECT, AppLovinAdSize.BANNER);
    /* renamed from: b */
    public static final C0505i f927b = C0505i.m1131a(AppLovinAdType.REGULAR, C0506j.DIRECT, AppLovinAdSize.MREC);
    /* renamed from: c */
    public static final C0505i f928c = C0505i.m1131a(AppLovinAdType.REGULAR, C0506j.DIRECT, AppLovinAdSize.LEADER);
    /* renamed from: d */
    public static final C0505i f929d = C0505i.m1131a(AppLovinAdType.REGULAR, C0506j.DIRECT, AppLovinAdSize.INTERSTITIAL);
    /* renamed from: e */
    public static final C0505i f930e = C0505i.m1131a(AppLovinAdType.REGULAR, C0506j.INDIRECT, AppLovinAdSize.INTERSTITIAL);
    /* renamed from: f */
    public static final C0505i f931f = C0505i.m1131a(AppLovinAdType.INCENTIVIZED, C0506j.DIRECT, AppLovinAdSize.INTERSTITIAL);
    /* renamed from: g */
    public static final C0505i f932g = C0505i.m1131a(AppLovinAdType.INCENTIVIZED, C0506j.INDIRECT, AppLovinAdSize.INTERSTITIAL);
    /* renamed from: h */
    public static final C0505i f933h = C0505i.m1131a(AppLovinAdType.NATIVE, C0506j.DIRECT, AppLovinAdSize.NATIVE);
    /* renamed from: i */
    private static final Collection f934i = new HashSet();
    /* renamed from: j */
    private final AppLovinAdSize f935j;
    /* renamed from: k */
    private final AppLovinAdType f936k;
    /* renamed from: l */
    private final C0506j f937l;

    public C0505i(AppLovinAdType appLovinAdType, C0506j c0506j, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        } else if (appLovinAdType == null) {
            throw new IllegalArgumentException("No ad type specified");
        } else if (c0506j == null) {
            throw new IllegalArgumentException("No ad mediation type specified");
        } else {
            this.f935j = appLovinAdSize;
            this.f936k = appLovinAdType;
            this.f937l = c0506j;
        }
    }

    /* renamed from: a */
    private static C0505i m1131a(AppLovinAdType appLovinAdType, C0506j c0506j, AppLovinAdSize appLovinAdSize) {
        C0505i c0505i = new C0505i(appLovinAdType, c0506j, appLovinAdSize);
        f934i.add(c0505i);
        return c0505i;
    }

    /* renamed from: d */
    public static Collection m1132d() {
        return Collections.unmodifiableCollection(f934i);
    }

    /* renamed from: a */
    public AppLovinAdSize m1133a() {
        return this.f935j;
    }

    /* renamed from: b */
    public AppLovinAdType m1134b() {
        return this.f936k;
    }

    /* renamed from: c */
    public C0506j m1135c() {
        return this.f937l;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0505i c0505i = (C0505i) obj;
        if (this.f935j != null) {
            if (!this.f935j.equals(c0505i.f935j)) {
                return false;
            }
        } else if (c0505i.f935j != null) {
            return false;
        }
        if (this.f936k != null) {
            if (!this.f936k.equals(c0505i.f936k)) {
                return false;
            }
        } else if (c0505i.f936k != null) {
            return false;
        }
        if (this.f937l != c0505i.f937l) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f936k != null ? this.f936k.hashCode() : 0) + ((this.f935j != null ? this.f935j.hashCode() : 0) * 31)) * 31;
        if (this.f937l != null) {
            i = this.f937l.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "[" + this.f936k.getLabel() + " " + this.f937l + " " + this.f935j.getLabel() + "]";
    }
}
