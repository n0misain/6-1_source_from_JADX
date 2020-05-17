package com.applovin.impl.sdk;

import android.app.AlertDialog;
import com.applovin.impl.adview.aj;

public class bl {
    /* renamed from: a */
    private final AppLovinSdkImpl f584a;
    /* renamed from: b */
    private final aj f585b;
    /* renamed from: c */
    private AlertDialog f586c;

    public bl(AppLovinSdkImpl appLovinSdkImpl, aj ajVar) {
        this.f584a = appLovinSdkImpl;
        this.f585b = ajVar;
    }

    /* renamed from: a */
    public void m606a() {
        this.f585b.runOnUiThread(new bm(this));
    }

    /* renamed from: b */
    public void m607b() {
        this.f585b.runOnUiThread(new bn(this));
    }

    /* renamed from: c */
    public void m608c() {
        this.f585b.runOnUiThread(new bq(this));
    }

    /* renamed from: d */
    public boolean m609d() {
        return this.f586c != null ? this.f586c.isShowing() : false;
    }
}
