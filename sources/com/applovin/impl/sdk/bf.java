package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.util.Timer;

class bf {
    /* renamed from: a */
    private final AppLovinSdkImpl f569a;
    /* renamed from: b */
    private final am f570b;
    /* renamed from: c */
    private final Activity f571c;
    /* renamed from: d */
    private final Runnable f572d;
    /* renamed from: e */
    private final AppLovinAdRewardListener f573e;
    /* renamed from: f */
    private final Timer f574f;

    private bf(bk bkVar) {
        this.f569a = bkVar.f579a;
        this.f570b = bkVar.f580b;
        this.f571c = bkVar.f581c;
        this.f572d = bkVar.f583e;
        this.f573e = bkVar.f582d;
        this.f574f = new Timer("IncentivizedAdLauncher");
    }

    /* renamed from: b */
    static bk m585b() {
        return new bk();
    }

    /* renamed from: a */
    void m590a() {
        this.f571c.runOnUiThread(new bg(this));
    }
}
