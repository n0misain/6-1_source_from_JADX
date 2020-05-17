package com.applovin.impl.adview;

import android.app.Activity;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.adview.InterstitialAdDialogCreator;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

public class InterstitialAdDialogCreatorImpl implements InterstitialAdDialogCreator {
    /* renamed from: a */
    private static final Object f191a = new Object();
    /* renamed from: b */
    private static WeakReference f192b = new WeakReference(null);
    /* renamed from: c */
    private static WeakReference f193c = new WeakReference(null);

    public AppLovinInterstitialAdDialog createInterstitialAdDialog(AppLovinSdk appLovinSdk, Activity activity) {
        AppLovinInterstitialAdDialog appLovinInterstitialAdDialog;
        if (appLovinSdk == null) {
            appLovinSdk = AppLovinSdk.getInstance(activity);
        }
        synchronized (f191a) {
            appLovinInterstitialAdDialog = (bt) f192b.get();
            if (appLovinInterstitialAdDialog != null && appLovinInterstitialAdDialog.isShowing() && f193c.get() == activity) {
                appLovinSdk.getLogger().mo2294w("InterstitialAdDialogCreator", "An interstitial dialog is already showing, returning it");
            } else {
                appLovinInterstitialAdDialog = new bt(appLovinSdk, activity);
                f192b = new WeakReference(appLovinInterstitialAdDialog);
                f193c = new WeakReference(activity);
            }
        }
        return appLovinInterstitialAdDialog;
    }
}
