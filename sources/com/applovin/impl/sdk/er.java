package com.applovin.impl.sdk;

import com.applovin.impl.p000a.C0456g;
import org.json.JSONObject;

final class er extends C0456g {
    er(C0505i c0505i, JSONObject jSONObject, JSONObject jSONObject2) {
        super(c0505i, jSONObject, jSONObject2);
    }

    /* renamed from: a */
    void m1012a(fl flVar) {
        if (flVar == null) {
            throw new IllegalArgumentException("No aggregated vast response specified");
        }
        this.a.add(flVar);
    }
}
