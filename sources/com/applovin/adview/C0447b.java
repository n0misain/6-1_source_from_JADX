package com.applovin.adview;

import android.app.Activity;
import com.applovin.impl.adview.InterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinSdk;

/* renamed from: com.applovin.adview.b */
final class C0447b implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinSdk f56a;
    /* renamed from: b */
    final /* synthetic */ Activity f57b;
    /* renamed from: c */
    final /* synthetic */ String f58c;

    C0447b(AppLovinSdk appLovinSdk, Activity activity, String str) {
        this.f56a = appLovinSdk;
        this.f57b = activity;
        this.f58c = str;
    }

    public void run() {
        new InterstitialAdDialogCreatorImpl().createInterstitialAdDialog(this.f56a, this.f57b).show(this.f58c);
    }
}
