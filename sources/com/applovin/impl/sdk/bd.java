package com.applovin.impl.sdk;

import android.app.Activity;
import android.widget.Toast;

public class bd {
    /* renamed from: a */
    private final AppLovinSdkImpl f565a;
    /* renamed from: b */
    private final String f566b;
    /* renamed from: c */
    private final Activity f567c;

    public bd(AppLovinSdkImpl appLovinSdkImpl, Activity activity, String str) {
        this.f565a = appLovinSdkImpl;
        this.f566b = str;
        this.f567c = activity;
    }

    /* renamed from: a */
    void m580a() {
        this.f567c.runOnUiThread(new be(this));
    }

    /* renamed from: a */
    void m581a(String str, Throwable th) {
        this.f565a.getLogger().userError("IncentivizedConfirmationManager", "Unable to show incentivized ad reward dialog. Have you defined com.applovin.adview.AppLovinConfirmationActivity in your manifest?", th);
        Toast.makeText(this.f567c, str, 1).show();
    }

    /* renamed from: b */
    String m582b() {
        return this.f566b.equals("accepted") ? (String) this.f565a.get(dj.f768W) : this.f566b.equals("quota_exceeded") ? (String) this.f565a.get(dj.f769X) : this.f566b.equals("rejected") ? (String) this.f565a.get(dj.f770Y) : (String) this.f565a.get(dj.f771Z);
    }
}
