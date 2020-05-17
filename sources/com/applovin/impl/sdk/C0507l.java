package com.applovin.impl.sdk;

import android.net.Uri;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.sdk.AppLovinPostbackListener;

/* renamed from: com.applovin.impl.sdk.l */
class C0507l implements AppLovinPostbackListener {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f942a;
    /* renamed from: b */
    final /* synthetic */ Uri f943b;
    /* renamed from: c */
    final /* synthetic */ ae f944c;
    /* renamed from: d */
    final /* synthetic */ AppLovinAdView f945d;
    /* renamed from: e */
    final /* synthetic */ AppLovinAdServiceImpl f946e;

    C0507l(AppLovinAdServiceImpl appLovinAdServiceImpl, AdViewControllerImpl adViewControllerImpl, Uri uri, ae aeVar, AppLovinAdView appLovinAdView) {
        this.f946e = appLovinAdServiceImpl;
        this.f942a = adViewControllerImpl;
        this.f943b = uri;
        this.f944c = aeVar;
        this.f945d = appLovinAdView;
    }

    public void onPostbackFailure(String str, int i) {
        this.f946e.f400c.post(new C0509n(this));
    }

    public void onPostbackSuccess(String str) {
        this.f946e.f400c.post(new C0508m(this));
    }
}
