package com.applovin.impl.adview;

import android.app.Activity;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.adview.InterstitialAdDialogCreator;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

public class MediatedInterstitialAdDialogCreatorImpl implements InterstitialAdDialogCreator {
    /* renamed from: a */
    private static final Object f194a = new Object();
    /* renamed from: b */
    private static WeakReference f195b = new WeakReference(null);
    /* renamed from: c */
    private static WeakReference f196c = new WeakReference(null);

    public AppLovinInterstitialAdDialog createInterstitialAdDialog(AppLovinSdk appLovinSdk, Activity activity) {
        AppLovinInterstitialAdDialog appLovinInterstitialAdDialog;
        if (appLovinSdk == null) {
            appLovinSdk = AppLovinSdk.getInstance(activity);
        }
        synchronized (f194a) {
            appLovinInterstitialAdDialog = (ca) f195b.get();
            if (appLovinInterstitialAdDialog != null && appLovinInterstitialAdDialog.isShowing() && f196c.get() == activity) {
                appLovinSdk.getLogger().mo2294w("InterstitialAdDialogCreator", "An interstitial dialog is already showing, returning it");
            } else {
                appLovinInterstitialAdDialog = new ca(appLovinSdk, activity);
                f195b = new WeakReference(appLovinInterstitialAdDialog);
                f196c = new WeakReference(activity);
            }
        }
        return appLovinInterstitialAdDialog;
    }
}
