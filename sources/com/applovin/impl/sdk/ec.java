package com.applovin.impl.sdk;

import org.json.JSONObject;

class ec extends ex {
    /* renamed from: a */
    final /* synthetic */ eb f836a;

    ec(eb ebVar, String str, JSONObject jSONObject, String str2, AppLovinSdkImpl appLovinSdkImpl) {
        this.f836a = ebVar;
        super(str, jSONObject, str2, appLovinSdkImpl);
    }

    /* renamed from: a */
    public void mo2274a(int i) {
        this.f836a.f831g.onPostbackFailure(this.f836a.f829a, i);
    }

    /* renamed from: a */
    public void m940a(JSONObject jSONObject, int i) {
        this.f836a.f831g.onPostbackSuccess(this.f836a.f829a);
    }
}
