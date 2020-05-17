package com.applovin.impl.adview;

import com.applovin.adview.AppLovinInterstitialAdDialog;

/* renamed from: com.applovin.impl.adview.l */
class C0481l implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f348a;

    private C0481l(AdViewControllerImpl adViewControllerImpl) {
        this.f348a = adViewControllerImpl;
    }

    public void run() {
        if (this.f348a.f176o != null) {
            try {
                this.f348a.f166e.mo2288d("AppLovinAdView", "Rendering interstitial ad for #" + this.f348a.f176o.getAdIdNumber() + " over placement: \"" + this.f348a.f168g + "\"...");
                AppLovinInterstitialAdDialog createInterstitialAdDialog = new InterstitialAdDialogCreatorImpl().createInterstitialAdDialog(this.f348a.f164c, this.f348a.f162a);
                createInterstitialAdDialog.setAdDisplayListener(new C0476g(this.f348a));
                createInterstitialAdDialog.setAdVideoPlaybackListener(new C0477h(this.f348a));
                createInterstitialAdDialog.setAdClickListener(new C0475f(this.f348a));
                createInterstitialAdDialog.showAndRender(this.f348a.f176o, this.f348a.f168g);
            } catch (Throwable th) {
            }
        }
    }
}
