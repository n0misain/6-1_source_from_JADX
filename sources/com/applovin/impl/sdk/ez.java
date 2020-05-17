package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.AccessToken;
import com.facebook.internal.NativeProtocol;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class ez extends dg {
    /* renamed from: a */
    private final ae f891a;

    public ez(ae aeVar, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskReportReward", appLovinSdkImpl);
        this.f891a = aeVar;
    }

    public void run() {
        String userIdentifier = this.d.getUserIdentifier();
        String l = this.f891a.mo2130l();
        Map hashMap = new HashMap(2);
        if (AppLovinSdkUtils.isValidString(l)) {
            hashMap.put("clcode", l);
        } else {
            hashMap.put("clcode", "NO_CLCODE");
        }
        if (userIdentifier != null) {
            hashMap.put(AccessToken.USER_ID_KEY, userIdentifier);
        }
        cy a = cy.m743a();
        l = a.m747b(this.f891a);
        if (l != null) {
            hashMap.put("result", l);
            Map a2 = a.m744a(this.f891a);
            if (a2 != null) {
                hashMap.put(NativeProtocol.WEB_DIALOG_PARAMS, a2);
            }
            m780a("cr", new JSONObject(hashMap), new fa(this));
            return;
        }
        this.e.mo2288d("TaskReportReward", "No reward result was found for ad: " + this.f891a);
    }
}
