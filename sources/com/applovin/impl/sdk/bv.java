package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediatedAdInfo;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdk;
import java.util.Collections;
import java.util.Map;
import org.json.JSONObject;

public class bv extends C0449k {
    /* renamed from: e */
    private final boolean f601e;
    /* renamed from: f */
    private final AppLovinMediatedAdInfo f602f;

    public bv(bv bvVar, boolean z, AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        super(bvVar.a, bvVar.b, bvVar.c, bvVar.d);
        this.f601e = z;
        this.f602f = appLovinMediatedAdInfo;
    }

    bv(C0505i c0505i, JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdk appLovinSdk) {
        super(c0505i, jSONObject, jSONObject2, appLovinSdk);
        this.f601e = false;
        this.f602f = null;
    }

    /* renamed from: a */
    public boolean mo2133a() {
        return this.f601e;
    }

    /* renamed from: b */
    String m621b() {
        return bt.m615a(this.b, "class", null, this.d);
    }

    /* renamed from: c */
    String m622c() {
        return bt.m615a(this.b, "name", null, this.d);
    }

    /* renamed from: d */
    public AppLovinMediatedAdInfo m623d() {
        return this.f602f;
    }

    /* renamed from: e */
    public Map m624e() {
        if (this.b.has("config")) {
            try {
                return bt.m617a(this.b.getJSONObject("config"));
            } catch (Throwable e) {
                this.d.getLogger().mo2290e("MediatedAd", "Failed to retrieve mediation configuration", e);
            }
        }
        return Collections.emptyMap();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        bv bvVar = (bv) obj;
        return this.f601e != bvVar.f601e ? false : this.f602f != null ? this.f602f.equals(bvVar.f602f) : bvVar.f602f == null;
    }

    /* renamed from: f */
    public int m625f() {
        return bt.m613a(this.b, "timeout_sec", 5, this.d);
    }

    public /* bridge */ /* synthetic */ long getAdIdNumber() {
        return super.getAdIdNumber();
    }

    public /* bridge */ /* synthetic */ AppLovinAdSize getSize() {
        return super.getSize();
    }

    public /* bridge */ /* synthetic */ AppLovinAdType getType() {
        return super.getType();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f601e ? 1 : 0) + (super.hashCode() * 31)) * 31;
        if (this.f602f != null) {
            i = this.f602f.hashCode();
        }
        return hashCode + i;
    }

    public /* bridge */ /* synthetic */ boolean isVideoAd() {
        return super.isVideoAd();
    }

    /* renamed from: l */
    public /* bridge */ /* synthetic */ String mo2130l() {
        return super.mo2130l();
    }

    /* renamed from: m */
    public /* bridge */ /* synthetic */ C0505i mo2131m() {
        return super.mo2131m();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
