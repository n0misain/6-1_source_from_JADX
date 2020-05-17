package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.AccessToken;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class fg extends dg {
    /* renamed from: a */
    private final ae f902a;
    /* renamed from: b */
    private final AppLovinAdRewardListener f903b;
    /* renamed from: g */
    private final Object f904g = new Object();
    /* renamed from: h */
    private volatile boolean f905h = false;

    public fg(ae aeVar, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskValidateReward", appLovinSdkImpl);
        this.f902a = aeVar;
        this.f903b = appLovinAdRewardListener;
    }

    /* renamed from: a */
    private void m1057a(int i) {
        if (!m1063c()) {
            String str = "network_timeout";
            if (i < 400 || i > 500) {
                this.f903b.validationRequestFailed(this.f902a, i);
            } else {
                this.f903b.userRewardRejected(this.f902a, new HashMap(0));
                str = "rejected";
            }
            cy.m743a().m745a(this.f902a, str);
        }
    }

    /* renamed from: a */
    private void m1060a(String str, Map map) {
        if (!m1063c()) {
            cy a = cy.m743a();
            a.m745a(this.f902a, str);
            a.m746a(this.f902a, map);
            if (str.equals("accepted")) {
                this.f903b.userRewardVerified(this.f902a, map);
            } else if (str.equals("quota_exceeded")) {
                this.f903b.userOverQuota(this.f902a, map);
            } else if (str.equals("rejected")) {
                this.f903b.userRewardRejected(this.f902a, map);
            } else {
                this.f903b.validationRequestFailed(this.f902a, AppLovinErrorCodes.INCENTIVIZED_UNKNOWN_SERVER_ERROR);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    private void m1061a(org.json.JSONObject r5) {
        /*
        r4 = this;
        r0 = r4.m1063c();
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r2 = com.applovin.impl.sdk.C0518x.m1180a(r5);	 Catch:{ JSONException -> 0x0027 }
        r0 = r4.d;	 Catch:{ JSONException -> 0x0027 }
        com.applovin.impl.sdk.C0518x.m1182a(r2, r0);	 Catch:{ JSONException -> 0x0027 }
        r0 = "params";
        r0 = r2.get(r0);	 Catch:{ Throwable -> 0x0032 }
        r0 = (org.json.JSONObject) r0;	 Catch:{ Throwable -> 0x0032 }
        r0 = com.applovin.impl.sdk.bt.m617a(r0);	 Catch:{ Throwable -> 0x0032 }
        r1 = r0;
    L_0x001d:
        r0 = "result";
        r0 = r2.getString(r0);	 Catch:{ Throwable -> 0x003b }
    L_0x0023:
        r4.m1060a(r0, r1);	 Catch:{ JSONException -> 0x0027 }
        goto L_0x0006;
    L_0x0027:
        r0 = move-exception;
        r1 = r4.e;
        r2 = r4.c;
        r3 = "Unable to parse API response";
        r1.mo2290e(r2, r3, r0);
        goto L_0x0006;
    L_0x0032:
        r0 = move-exception;
        r0 = new java.util.HashMap;	 Catch:{ JSONException -> 0x0027 }
        r1 = 0;
        r0.<init>(r1);	 Catch:{ JSONException -> 0x0027 }
        r1 = r0;
        goto L_0x001d;
    L_0x003b:
        r0 = move-exception;
        r0 = "network_timeout";
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.fg.a(org.json.JSONObject):void");
    }

    /* renamed from: a */
    public void m1062a(boolean z) {
        synchronized (this.f904g) {
            this.f905h = z;
        }
    }

    /* renamed from: c */
    boolean m1063c() {
        boolean z;
        synchronized (this.f904g) {
            z = this.f905h;
        }
        return z;
    }

    public void run() {
        String userIdentifier = this.d.getUserIdentifier();
        String l = this.f902a.mo2130l();
        Map hashMap = new HashMap(2);
        if (AppLovinSdkUtils.isValidString(l)) {
            hashMap.put("clcode", l);
        } else {
            hashMap.put("clcode", "NO_CLCODE");
        }
        if (userIdentifier != null) {
            hashMap.put(AccessToken.USER_ID_KEY, userIdentifier);
        }
        m780a("vr", new JSONObject(hashMap), new fh(this));
    }
}
