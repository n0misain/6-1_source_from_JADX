package com.applovin.impl.p000a;

import com.applovin.impl.sdk.C0505i;
import com.applovin.impl.sdk.bt;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.a.g */
public class C0456g {
    /* renamed from: b */
    private static final List f100b = Arrays.asList(new String[]{"video/mp4", "video/webm", "video/3gpp", "video/x-matroska"});
    /* renamed from: a */
    protected List f101a;
    /* renamed from: c */
    private final C0505i f102c;
    /* renamed from: d */
    private final JSONObject f103d;
    /* renamed from: e */
    private final JSONObject f104e;

    public C0456g(C0505i c0505i, JSONObject jSONObject, JSONObject jSONObject2) {
        if (c0505i == null) {
            throw new IllegalArgumentException("Unable to create VastContext. No ad spec response specified.");
        } else if (jSONObject == null) {
            throw new IllegalArgumentException("Unable to create VastContext. No sdk vast response specified.");
        } else {
            this.f102c = c0505i;
            this.f103d = jSONObject;
            this.f104e = jSONObject2;
            this.f101a = new ArrayList();
        }
    }

    /* renamed from: a */
    public int m205a() {
        return this.f101a.size();
    }

    /* renamed from: b */
    public List m206b() {
        return this.f101a;
    }

    /* renamed from: c */
    public JSONObject m207c() {
        return this.f103d;
    }

    /* renamed from: d */
    public JSONObject m208d() {
        return this.f104e;
    }

    /* renamed from: e */
    public C0505i m209e() {
        return this.f102c;
    }

    /* renamed from: f */
    public List m210f() {
        String a = bt.m615a(this.f103d, "vast_preferred_video_types", null, null);
        return AppLovinSdkUtils.isValidString(a) ? Arrays.asList(a.split(",")) : f100b;
    }
}
