package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdkUtils;
import org.json.JSONException;
import org.json.JSONObject;

class eu extends di implements fi {
    /* renamed from: a */
    private final C0505i f880a;
    /* renamed from: b */
    private final JSONObject f881b;
    /* renamed from: g */
    private final JSONObject f882g;
    /* renamed from: h */
    private final AppLovinAdLoadListener f883h;

    eu(C0505i c0505i, JSONObject jSONObject, JSONObject jSONObject2, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskRenderAppLovinAd", appLovinSdkImpl);
        this.f880a = c0505i;
        this.f881b = jSONObject;
        this.f882g = jSONObject2;
        this.f883h = appLovinAdLoadListener;
    }

    /* renamed from: a */
    private ej m1013a(String str) {
        return "main".equalsIgnoreCase(str) ? ej.MAIN : ej.BACKGROUND;
    }

    /* renamed from: d */
    private void m1014d() throws JSONException, IllegalArgumentException {
        if (AppLovinSdkUtils.isValidString(bt.m615a(this.f881b, "html", null, this.d))) {
            C0503g c0503g = new C0503g(this.f880a, this.f881b, this.f882g, this.d);
            boolean a = bt.m619a(this.f881b, "vs_cache_immediately", false, this.d);
            boolean a2 = bt.m619a(this.f881b, "vs_load_immediately", true, this.d);
            String a3 = bt.m615a(this.f881b, "vs_ad_cache_priority", "background", this.d);
            this.e.mo2288d(this.c, "Creating cache task...");
            di dtVar = new dt(c0503g, this.f883h, this.d);
            if (!c0503g.mo2134b() || a) {
                this.d.m466a().m991a(dtVar);
                return;
            }
            ej a4 = m1013a(a3);
            dtVar.m911a(a2);
            this.d.m466a().m992a(dtVar, a4);
            return;
        }
        this.e.mo2289e(this.c, "No HTML received for requested ad");
        fk.m1087a(this.f883h, this.f880a, -6, this.d);
    }

    /* renamed from: c */
    public String mo2281c() {
        return "tRA";
    }

    public void run() {
        try {
            m1014d();
        } catch (Throwable th) {
            if (th instanceof JSONException) {
                this.e.mo2290e(this.c, "Unable to parse ad service response", th);
            } else if (th instanceof IllegalArgumentException) {
                this.e.mo2290e(this.c, "Ad response is not valid", th);
            } else {
                this.e.mo2290e(this.c, "Unable to render ad", th);
            }
            fk.m1087a(this.f883h, this.f880a, -6, this.d);
        }
    }
}
