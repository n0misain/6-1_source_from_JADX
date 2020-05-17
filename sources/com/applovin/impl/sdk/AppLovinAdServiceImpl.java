package com.applovin.impl.sdk;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AppLovinAdServiceImpl implements AppLovinAdService {
    public static String URI_NO_OP = "/adservice/no_op";
    public static String URI_SKIP_AD = "/adservice/skip";
    public static String URI_TRACK_CLICK_IMMEDIATELY = "/adservice/track_click_now";
    /* renamed from: a */
    private final AppLovinSdkImpl f398a;
    /* renamed from: b */
    private final AppLovinLogger f399b;
    /* renamed from: c */
    private Handler f400c;
    /* renamed from: d */
    private final Map f401d;

    AppLovinAdServiceImpl(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f398a = appLovinSdkImpl;
        this.f399b = appLovinSdkImpl.getLogger();
        this.f400c = new Handler(Looper.getMainLooper());
        this.f401d = new HashMap(5);
        this.f401d.put(C0505i.f926a, new C0511p(C0505i.f926a));
        this.f401d.put(C0505i.f927b, new C0511p(C0505i.f927b));
        this.f401d.put(C0505i.f928c, new C0511p(C0505i.f928c));
        this.f401d.put(C0505i.f929d, new C0511p(C0505i.f929d));
        this.f401d.put(C0505i.f930e, new C0511p(C0505i.f930e));
        this.f401d.put(C0505i.f931f, new C0511p(C0505i.f931f));
        this.f401d.put(C0505i.f932g, new C0511p(C0505i.f932g));
    }

    /* renamed from: a */
    private void m442a(Uri uri, ae aeVar, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl) {
        adViewControllerImpl.removeClickTrackingOverlay();
        expireAdLoadState(aeVar);
        AppLovinSdkUtils.openUri(appLovinAdView.getContext(), uri, this.f398a);
        adViewControllerImpl.dismissInterstitialIfRequired();
    }

    /* renamed from: a */
    private void m446a(ae aeVar, String str) {
        String b = aeVar.m139b(str);
        if (AppLovinSdkUtils.isValidString(b)) {
            this.f398a.getPersistentPostbackManager().m762a(b, null);
        }
    }

    /* renamed from: a */
    private void m447a(C0505i c0505i, AppLovinAdLoadListener appLovinAdLoadListener) {
        Object obj = 1;
        if (c0505i == null) {
            throw new IllegalArgumentException("No ad spec specified");
        } else if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("No callback specified");
        } else if (C0518x.m1184a(this.f398a.getApplicationContext()) || ((Boolean) this.f398a.get(dj.ca)).booleanValue()) {
            this.f398a.getLogger().mo2288d("AppLovinAdService", "Loading next ad " + c0505i + "...");
            C0511p c0511p = (C0511p) this.f401d.get(c0505i);
            if (c0511p == null) {
                throw new IllegalArgumentException("Ad not supported: " + c0505i);
            }
            AppLovinAd appLovinAd;
            synchronized (c0511p.f952b) {
                if (System.currentTimeMillis() <= c0511p.f954d) {
                    obj = null;
                }
                if (c0511p.f953c == null || r2 != null) {
                    c0511p.f957g.add(appLovinAdLoadListener);
                    if (c0511p.f955e) {
                        this.f399b.mo2288d("AppLovinAdService", "Already waiting on an ad load...");
                        appLovinAd = null;
                    } else {
                        this.f399b.mo2288d("AppLovinAdService", "Loading next ad...");
                        c0511p.f955e = true;
                        AppLovinAdLoadListener c0510o = new C0510o(this, c0511p, null);
                        if (!m451a(c0505i)) {
                            this.f399b.mo2288d("AppLovinAdService", "Task merge not necessary.");
                            m457b(c0505i, c0510o);
                        } else if (this.f398a.m469c().mo2257a(c0505i, (Object) c0510o)) {
                            this.f399b.mo2288d("AppLovinAdService", "Attaching load listener to initial preload task...");
                        } else {
                            this.f399b.mo2288d("AppLovinAdService", "Skipped attach of initial preload callback.");
                            m457b(c0505i, c0510o);
                        }
                        appLovinAd = null;
                    }
                } else {
                    appLovinAd = c0511p.f953c;
                }
            }
            if (appLovinAd != null) {
                appLovinAdLoadListener.adReceived(appLovinAd);
            }
        } else {
            this.f399b.userError("AppLovinAdService", "Failing ad load due to no internet connection.");
            appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.NO_NETWORK);
        }
    }

    /* renamed from: a */
    private boolean m448a() {
        return ((PowerManager) this.f398a.getApplicationContext().getSystemService("power")).isScreenOn();
    }

    /* renamed from: a */
    private boolean m450a(dl dlVar, AppLovinAdSize appLovinAdSize) {
        return ((String) this.f398a.get(dlVar)).toUpperCase(Locale.ENGLISH).contains(appLovinAdSize.getLabel());
    }

    /* renamed from: a */
    private boolean m451a(C0505i c0505i) {
        return !((Boolean) this.f398a.get(dj.f751F)).booleanValue() ? false : !m459c(c0505i) ? false : c0505i.m1135c() == C0506j.DIRECT ? c0505i.m1134b().equals(AppLovinAdType.INCENTIVIZED) ? ((Boolean) this.f398a.get(dj.aM)).booleanValue() : c0505i.m1133a().equals(AppLovinAdSize.INTERSTITIAL) ? ((Boolean) this.f398a.get(dj.aN)).booleanValue() : false : c0505i.m1135c() == C0506j.INDIRECT ? c0505i.m1134b().equals(AppLovinAdType.INCENTIVIZED) ? ((Boolean) this.f398a.get(dj.aO)).booleanValue() : c0505i.m1133a().equals(AppLovinAdSize.INTERSTITIAL) ? ((Boolean) this.f398a.get(dj.aP)).booleanValue() : false : false;
    }

    /* renamed from: a */
    private boolean m452a(AppLovinAdSize appLovinAdSize) {
        return appLovinAdSize == AppLovinAdSize.BANNER ? ((Boolean) this.f398a.get(dj.f796y)).booleanValue() : appLovinAdSize == AppLovinAdSize.MREC ? ((Boolean) this.f398a.get(dj.f746A)).booleanValue() : appLovinAdSize == AppLovinAdSize.LEADER ? ((Boolean) this.f398a.get(dj.f748C)).booleanValue() : false;
    }

    /* renamed from: b */
    private long m454b(AppLovinAdSize appLovinAdSize) {
        return appLovinAdSize == AppLovinAdSize.BANNER ? ((Long) this.f398a.get(dj.f797z)).longValue() : appLovinAdSize == AppLovinAdSize.MREC ? ((Long) this.f398a.get(dj.f747B)).longValue() : appLovinAdSize == AppLovinAdSize.LEADER ? ((Long) this.f398a.get(dj.f749D)).longValue() : 0;
    }

    /* renamed from: b */
    private void m456b(C0505i c0505i) {
        long b = m454b(c0505i.m1133a());
        if (b > 0) {
            this.f398a.m466a().m993a(new C0512q(this, c0505i), ej.MAIN, (b + 2) * 1000);
        }
    }

    /* renamed from: b */
    private void m457b(C0505i c0505i, AppLovinAdLoadListener appLovinAdLoadListener) {
        AppLovinAd appLovinAd = (AppLovinAd) this.f398a.m469c().mo2258b(c0505i);
        if (appLovinAd != null) {
            this.f399b.mo2288d("AppLovinAdService", "Using pre-loaded ad: " + appLovinAd + " for " + c0505i);
            appLovinAdLoadListener.adReceived(appLovinAd);
        } else {
            this.f398a.m466a().m992a(new ed(c0505i, appLovinAdLoadListener, this.f398a), ej.MAIN);
        }
        this.f398a.m469c().mo2263f(c0505i);
    }

    /* renamed from: c */
    private boolean m459c(C0505i c0505i) {
        try {
            return c0505i.m1135c() == C0506j.DIRECT ? c0505i.m1134b().equals(AppLovinAdType.INCENTIVIZED) ? ((Boolean) this.f398a.get(dj.f757L)).booleanValue() : m450a(dj.f755J, c0505i.m1133a()) : c0505i.m1135c() == C0506j.INDIRECT ? c0505i.m1134b().equals(AppLovinAdType.INCENTIVIZED) ? ((Boolean) this.f398a.get(dj.f758M)).booleanValue() : m450a(dj.f756K, c0505i.m1133a()) : false;
        } catch (Throwable e) {
            this.f398a.getLogger().mo2290e("AppLovinAdService", "Unable to safely test preload merge capability", e);
            return false;
        }
    }

    /* renamed from: a */
    void m462a(AppLovinAdLoadListener appLovinAdLoadListener) {
        m447a(C0505i.f931f, appLovinAdLoadListener);
    }

    public void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener) {
        addAdUpdateListener(appLovinAdUpdateListener, AppLovinAdSize.BANNER);
    }

    public void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdUpdateListener == null) {
            throw new IllegalArgumentException("No ad listener specified");
        }
        Object obj;
        C0505i c0505i = new C0505i(AppLovinAdType.REGULAR, C0506j.DIRECT, appLovinAdSize);
        C0511p c0511p = (C0511p) this.f401d.get(c0505i);
        synchronized (c0511p.f952b) {
            if (c0511p.f956f.contains(appLovinAdUpdateListener)) {
                obj = null;
            } else {
                c0511p.f956f.add(appLovinAdUpdateListener);
                obj = 1;
                this.f399b.mo2288d("AppLovinAdService", "Added update listener: " + appLovinAdUpdateListener);
            }
        }
        if (obj != null) {
            this.f398a.m466a().m992a(new C0512q(this, c0505i), ej.MAIN);
        }
    }

    /* renamed from: b */
    void m463b(AppLovinAdLoadListener appLovinAdLoadListener) {
        m447a(C0505i.f932g, appLovinAdLoadListener);
    }

    public void expireAdLoadState(AppLovinAd appLovinAd) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (appLovinAd instanceof C0449k) {
            C0511p c0511p = (C0511p) this.f401d.get(((C0449k) appLovinAd).mo2131m());
            synchronized (c0511p.f952b) {
                c0511p.f953c = null;
                c0511p.f954d = 0;
            }
        } else {
            throw new IllegalArgumentException("Unknown ad type specified: " + appLovinAd.getClass().getName());
        }
    }

    public boolean hasPreloadedAd(AppLovinAdSize appLovinAdSize) {
        return this.f398a.m469c().mo2262e(new C0505i(AppLovinAdType.REGULAR, C0506j.DIRECT, appLovinAdSize));
    }

    public void loadNextAd(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener) {
        m447a(new C0505i(AppLovinAdType.REGULAR, C0506j.DIRECT, appLovinAdSize), appLovinAdLoadListener);
    }

    public void loadNextMediatedAd(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener) {
        m447a(new C0505i(AppLovinAdType.REGULAR, C0506j.INDIRECT, appLovinAdSize), appLovinAdLoadListener);
    }

    public void preloadAd(AppLovinAdSize appLovinAdSize) {
        this.f398a.m469c().mo2263f(new C0505i(AppLovinAdType.REGULAR, C0506j.DIRECT, appLovinAdSize));
    }

    public void removeAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdUpdateListener != null) {
            C0511p c0511p = (C0511p) this.f401d.get(new C0505i(AppLovinAdType.REGULAR, C0506j.DIRECT, appLovinAdSize));
            synchronized (c0511p.f952b) {
                c0511p.f956f.remove(appLovinAdUpdateListener);
            }
            this.f399b.mo2288d("AppLovinAdService", "Removed update listener: " + appLovinAdUpdateListener);
        }
    }

    public void trackAndLaunchClick(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        ae aeVar = (ae) appLovinAd;
        m446a(aeVar, str);
        m442a(uri, aeVar, appLovinAdView, adViewControllerImpl);
    }

    public void trackAndLaunchForegroundClick(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        ae aeVar = (ae) appLovinAd;
        this.f399b.mo2288d("AppLovinAdService", "Tracking foreground click on an ad...");
        int intValue = ((Integer) this.f398a.get(dj.bB)).intValue();
        int intValue2 = ((Integer) this.f398a.get(dj.bC)).intValue();
        int intValue3 = ((Integer) this.f398a.get(dj.bD)).intValue();
        this.f398a.getPostbackService().dispatchPostbackAsync(aeVar.m139b(str), null, null, intValue, (long) intValue2, intValue3, new C0507l(this, adViewControllerImpl, uri, aeVar, appLovinAdView));
    }

    public void trackAndLaunchVideoClick(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, Uri uri) {
        m446a((ae) appLovinAd, str);
        AppLovinSdkUtils.openUri(appLovinAdView.getContext(), uri, this.f398a);
    }
}
