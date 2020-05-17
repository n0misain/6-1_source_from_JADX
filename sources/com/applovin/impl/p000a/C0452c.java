package com.applovin.impl.p000a;

import com.applovin.impl.sdk.C0505i;
import com.applovin.sdk.AppLovinSdk;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.a.c */
public class C0452c {
    /* renamed from: a */
    private C0505i f73a;
    /* renamed from: b */
    private JSONObject f74b;
    /* renamed from: c */
    private JSONObject f75c;
    /* renamed from: d */
    private AppLovinSdk f76d;
    /* renamed from: e */
    private String f77e;
    /* renamed from: f */
    private String f78f;
    /* renamed from: g */
    private C0460k f79g;
    /* renamed from: h */
    private C0464o f80h;
    /* renamed from: i */
    private C0455f f81i;
    /* renamed from: j */
    private Set f82j;
    /* renamed from: k */
    private Set f83k;

    private C0452c() {
    }

    /* renamed from: a */
    public C0450a m188a() {
        return new C0450a();
    }

    /* renamed from: a */
    public C0452c m189a(C0455f c0455f) {
        this.f81i = c0455f;
        return this;
    }

    /* renamed from: a */
    public C0452c m190a(C0460k c0460k) {
        this.f79g = c0460k;
        return this;
    }

    /* renamed from: a */
    public C0452c m191a(C0464o c0464o) {
        this.f80h = c0464o;
        return this;
    }

    /* renamed from: a */
    public C0452c m192a(C0505i c0505i) {
        if (c0505i == null) {
            throw new IllegalArgumentException("No ad spec specified.");
        }
        this.f73a = c0505i;
        return this;
    }

    /* renamed from: a */
    public C0452c m193a(AppLovinSdk appLovinSdk) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        this.f76d = appLovinSdk;
        return this;
    }

    /* renamed from: a */
    public C0452c m194a(String str) {
        this.f77e = str;
        return this;
    }

    /* renamed from: a */
    public C0452c m195a(Set set) {
        this.f82j = set;
        return this;
    }

    /* renamed from: a */
    public C0452c m196a(JSONObject jSONObject) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No ad object specified.");
        }
        this.f74b = jSONObject;
        return this;
    }

    /* renamed from: b */
    public C0452c m197b(String str) {
        this.f78f = str;
        return this;
    }

    /* renamed from: b */
    public C0452c m198b(Set set) {
        this.f83k = set;
        return this;
    }

    /* renamed from: b */
    public C0452c m199b(JSONObject jSONObject) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No full ad response specified.");
        }
        this.f75c = jSONObject;
        return this;
    }
}
