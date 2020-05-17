package com.applovin.adview;

import android.app.Activity;
import com.applovin.impl.adview.MediatedInterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinSdk;

/* renamed from: com.applovin.adview.c */
final class C0448c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinSdk f59a;
    /* renamed from: b */
    final /* synthetic */ Activity f60b;
    /* renamed from: c */
    final /* synthetic */ String f61c;

    C0448c(AppLovinSdk appLovinSdk, Activity activity, String str) {
        this.f59a = appLovinSdk;
        this.f60b = activity;
        this.f61c = str;
    }

    public void run() {
        new MediatedInterstitialAdDialogCreatorImpl().createInterstitialAdDialog(this.f59a, this.f60b).show(this.f61c);
    }
}
