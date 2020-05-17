package com.applovin.impl.sdk;

import io.fabric.sdk.android.services.network.HttpRequest;
import org.json.JSONObject;

abstract class dg extends di {
    protected dg(String str, AppLovinSdkImpl appLovinSdkImpl) {
        super(str, appLovinSdkImpl);
    }

    /* renamed from: a */
    protected void m780a(String str, JSONObject jSONObject, C0499w c0499w) {
        ex dhVar = new dh(this, HttpRequest.METHOD_POST, new JSONObject(), "Repeat" + this.c, this.d, c0499w);
        dhVar.m797a(C0518x.m1179a(str, null, this.d));
        dhVar.m802b(C0518x.m1189c(str, null, this.d));
        dhVar.m798a(jSONObject);
        dhVar.m803c(((Integer) this.d.get(dj.f778g)).intValue());
        dhVar.m795a(dj.f781j);
        dhVar.m801b(dj.f785n);
        dhVar.run();
    }
}
