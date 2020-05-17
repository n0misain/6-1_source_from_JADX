package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import android.widget.RelativeLayout.LayoutParams;
import com.applovin.adview.AdViewController;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.adview.ClickTrackingOverlayView;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.ae;
import com.applovin.impl.sdk.af;
import com.applovin.impl.sdk.ah;
import com.applovin.impl.sdk.dn;
import com.applovin.impl.sdk.dp;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;

public class AdViewControllerImpl implements AdViewController {
    /* renamed from: A */
    private volatile AppLovinAdClickListener f160A;
    /* renamed from: B */
    private volatile boolean f161B;
    /* renamed from: a */
    private Activity f162a;
    /* renamed from: b */
    private ViewGroup f163b;
    /* renamed from: c */
    private AppLovinSdk f164c;
    /* renamed from: d */
    private AppLovinAdService f165d;
    /* renamed from: e */
    private AppLovinLogger f166e;
    /* renamed from: f */
    private AppLovinAdSize f167f;
    /* renamed from: g */
    private String f168g;
    /* renamed from: h */
    private C0490u f169h;
    /* renamed from: i */
    private C0484o f170i;
    /* renamed from: j */
    private C0487r f171j;
    /* renamed from: k */
    private AppLovinAd f172k;
    /* renamed from: l */
    private Runnable f173l;
    /* renamed from: m */
    private Runnable f174m;
    /* renamed from: n */
    private Runnable f175n;
    /* renamed from: o */
    private volatile AppLovinAd f176o = null;
    /* renamed from: p */
    private ClickTrackingOverlayView f177p = null;
    /* renamed from: q */
    private WeakReference f178q = null;
    /* renamed from: r */
    private ae f179r = null;
    /* renamed from: s */
    private final AtomicReference f180s = new AtomicReference();
    /* renamed from: t */
    private volatile boolean f181t = false;
    /* renamed from: u */
    private volatile boolean f182u = true;
    /* renamed from: v */
    private volatile boolean f183v = false;
    /* renamed from: w */
    private volatile boolean f184w = false;
    /* renamed from: x */
    private volatile AppLovinAdLoadListener f185x;
    /* renamed from: y */
    private volatile AppLovinAdDisplayListener f186y;
    /* renamed from: z */
    private volatile AppLovinAdVideoPlaybackListener f187z;

    /* renamed from: a */
    private void m265a(ViewGroup viewGroup, AppLovinSdk appLovinSdk, AppLovinAdSize appLovinAdSize, Context context) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("No parent view specified");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        } else if (context instanceof Activity) {
            this.f164c = appLovinSdk;
            this.f165d = appLovinSdk.getAdService();
            this.f166e = appLovinSdk.getLogger();
            this.f167f = appLovinAdSize;
            this.f162a = (Activity) context;
            this.f163b = viewGroup;
            this.f172k = new ah();
            this.f169h = new C0490u(this, appLovinSdk);
            this.f175n = new C0478i();
            this.f173l = new C0483n();
            this.f174m = new C0481l();
            this.f170i = new C0484o(this, appLovinSdk);
            if (m268a(context)) {
                this.f171j = m269b();
                viewGroup.setBackgroundColor(0);
                viewGroup.addView(this.f171j);
                m271b(this.f171j, appLovinAdSize);
                m267a(this.f175n);
                m267a(new C0482m());
                this.f181t = true;
                return;
            }
            this.f166e.userError("AppLovinAdView", "Web view database is corrupt, AdView not loaded");
        } else {
            throw new IllegalArgumentException("Specified context is not an activity");
        }
    }

    /* renamed from: a */
    private void m266a(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, Uri uri) {
        if (this.f177p == null) {
            this.f166e.mo2288d("AppLovinAdView", "Creating and rendering click overlay");
            this.f177p = new ClickTrackingOverlayView(appLovinAdView.getContext(), this.f164c);
            this.f177p.setLayoutParams(new LayoutParams(-1, -1));
            appLovinAdView.addView(this.f177p);
            appLovinAdView.bringChildToFront(this.f177p);
            ((AppLovinAdServiceImpl) this.f165d).trackAndLaunchForegroundClick(appLovinAd, this.f168g, appLovinAdView, this, uri);
            return;
        }
        this.f166e.mo2288d("AppLovinAdView", "Skipping click overlay rendering because it already exists");
    }

    /* renamed from: a */
    private void m267a(Runnable runnable) {
        this.f162a.runOnUiThread(runnable);
    }

    /* renamed from: a */
    private static boolean m268a(Context context) {
        try {
            if (VERSION.SDK_INT >= 11) {
                return true;
            }
            WebViewDatabase instance = WebViewDatabase.getInstance(context);
            Method declaredMethod = WebViewDatabase.class.getDeclaredMethod("getCacheTotalSize", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(instance, new Object[0]);
            return true;
        } catch (Throwable e) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e);
            return true;
        } catch (Throwable e2) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e2);
            return true;
        } catch (Throwable e22) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e22);
            return true;
        } catch (Throwable e3) {
            Log.e("AppLovinAdView", "getCacheTotalSize() reported exception", e3);
            return false;
        } catch (Throwable e32) {
            Log.e("AppLovinAdView", "Unexpected error while checking DB state", e32);
            return false;
        }
    }

    /* renamed from: b */
    private C0487r m269b() {
        C0487r c0487r = new C0487r(this.f169h, this.f164c, this.f162a);
        c0487r.setBackgroundColor(0);
        c0487r.setWillNotCacheDrawing(false);
        if (new dn(this.f164c).m829E() && VERSION.SDK_INT >= 19) {
            c0487r.setLayerType(2, null);
        }
        return c0487r;
    }

    /* renamed from: b */
    private static void m271b(View view, AppLovinAdSize appLovinAdSize) {
        if (view != null) {
            DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
            int applyDimension = appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel()) ? -1 : appLovinAdSize.getWidth() == -1 ? displayMetrics.widthPixels : (int) TypedValue.applyDimension(1, (float) appLovinAdSize.getWidth(), displayMetrics);
            int applyDimension2 = appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel()) ? -1 : appLovinAdSize.getHeight() == -1 ? displayMetrics.heightPixels : (int) TypedValue.applyDimension(1, (float) appLovinAdSize.getHeight(), displayMetrics);
            ViewGroup.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(-2, -2);
            }
            layoutParams.width = applyDimension;
            layoutParams.height = applyDimension2;
            if (layoutParams instanceof LayoutParams) {
                layoutParams.addRule(10);
                layoutParams.addRule(9);
            }
            view.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: a */
    void m282a() {
        if (this.f179r != null) {
            contractAd();
            return;
        }
        this.f166e.mo2288d("AppLovinAdView", "Ad: " + this.f176o + " with placement = \"" + this.f168g + "\" closed.");
        m267a(this.f175n);
        m267a(new C0480k(this, this.f176o));
        this.f176o = null;
        this.f168g = null;
    }

    /* renamed from: a */
    void m283a(int i) {
        if (!this.f183v) {
            this.f165d.addAdUpdateListener(this.f170i, this.f167f);
            m267a(this.f175n);
        }
        m267a(new C0474e(this, i));
    }

    /* renamed from: a */
    void m284a(AppLovinAd appLovinAd) {
        if (appLovinAd != null) {
            this.f184w = true;
            if (this.f183v) {
                this.f180s.set(appLovinAd);
                this.f166e.mo2288d("AppLovinAdView", "Ad view has paused when an ad was received, ad saved for later");
            } else {
                this.f165d.addAdUpdateListener(this.f170i, this.f167f);
                renderAd(appLovinAd);
            }
            m267a(new C0473d(this, appLovinAd));
            return;
        }
        this.f166e.mo2289e("AppLovinAdView", "No provided when to the view controller");
        m283a(-1);
    }

    /* renamed from: a */
    void m285a(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        AppLovinAdServiceImpl appLovinAdServiceImpl = (AppLovinAdServiceImpl) this.f165d;
        if (!new dn(this.f164c).m833I() || uri == null) {
            appLovinAdServiceImpl.trackAndLaunchClick(appLovinAd, this.f168g, appLovinAdView, this, uri);
        } else {
            m266a(appLovinAd, appLovinAdView, uri);
        }
        m267a(new C0479j(this, appLovinAd));
    }

    public void contractAd() {
        m267a(new C0472c(this));
    }

    public void destroy() {
        if (this.f165d != null) {
            this.f165d.removeAdUpdateListener(this.f170i, getSize());
        }
        if (this.f171j != null) {
            try {
                if (this.f179r != null) {
                    this.f179r.dismiss();
                }
                ViewParent parent = this.f171j.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.f171j);
                }
                this.f171j.removeAllViews();
                this.f171j.destroy();
                this.f171j = null;
            } catch (Throwable th) {
                this.f166e.mo2295w("AppLovinAdView", "Unable to destroy ad view", th);
            }
        }
        this.f183v = true;
    }

    public void dismissInterstitialIfRequired() {
        Object obj = ((ae) this.f176o).m130G() == af.DISMISS ? 1 : null;
        if (this.f162a != null && (this.f162a instanceof AppLovinInterstitialActivity)) {
            AppLovinInterstitialActivity appLovinInterstitialActivity = (AppLovinInterstitialActivity) this.f162a;
            if (obj != null && appLovinInterstitialActivity.getPoststitialWasDisplayed()) {
                appLovinInterstitialActivity.dismiss();
            }
        } else if (this.f178q != null && obj != null) {
            bj bjVar = (bj) this.f178q.get();
            if (bjVar != null) {
                bjVar.dismiss();
            }
        }
    }

    public void expandAd() {
        m267a(new C0469a(this));
    }

    public AppLovinAd getCurrentAd() {
        return this.f176o;
    }

    public AppLovinSdk getSdk() {
        return this.f164c;
    }

    public AppLovinAdSize getSize() {
        return this.f167f;
    }

    public void initializeAdView(ViewGroup viewGroup, Context context, AppLovinAdSize appLovinAdSize, AppLovinSdk appLovinSdk, AttributeSet attributeSet) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("No parent view specified");
        } else if (context == null) {
            Log.e(AppLovinLogger.SDK_TAG, "Unable to build AppLovinAdView: no context provided. Please use a different constructor for this view.");
        } else {
            if (appLovinAdSize == null) {
                appLovinAdSize = C0485p.m399a(attributeSet);
                if (appLovinAdSize == null) {
                    appLovinAdSize = AppLovinAdSize.BANNER;
                }
            }
            if (appLovinSdk == null) {
                appLovinSdk = AppLovinSdk.getInstance(context);
            }
            if (appLovinSdk != null && !appLovinSdk.hasCriticalErrors()) {
                m265a(viewGroup, appLovinSdk, appLovinAdSize, context);
                if (C0485p.m400b(attributeSet)) {
                    loadNextAd();
                }
            }
        }
    }

    public boolean isAdReadyToDisplay() {
        return this.f164c.getAdService().hasPreloadedAd(this.f167f);
    }

    public boolean isAutoDestroy() {
        return this.f182u;
    }

    public boolean isForegroundClickInvalidated() {
        return this.f161B;
    }

    public void loadNextAd() {
        if (this.f164c == null || this.f170i == null || this.f162a == null || !this.f181t) {
            Log.i(AppLovinLogger.SDK_TAG, "Unable to load next ad: AppLovinAdView is not initialized.");
        } else {
            this.f165d.loadNextAd(this.f167f, this.f170i);
        }
    }

    public void onAdHtmlLoaded(WebView webView) {
        if (this.f176o != null) {
            webView.setVisibility(0);
            try {
                if (this.f186y != null) {
                    this.f186y.adDisplayed(this.f176o);
                }
            } catch (Throwable th) {
                this.f166e.userError("AppLovinAdView", "Exception while notifying ad display listener", th);
            }
        }
    }

    public void onDetachedFromWindow() {
        if (this.f181t) {
            m267a(new C0480k(this, this.f176o));
            if (this.f182u) {
                destroy();
            }
        }
    }

    public void onVisibilityChanged(int i) {
        if (!this.f181t || !this.f182u) {
            return;
        }
        if (i == 8 || i == 4) {
            pause();
        } else if (i == 0) {
            resume();
        }
    }

    public void pause() {
        if (this.f181t) {
            this.f165d.removeAdUpdateListener(this.f170i, getSize());
            AppLovinAd appLovinAd = this.f176o;
            renderAd(this.f172k);
            if (appLovinAd != null) {
                this.f180s.set(appLovinAd);
            }
            this.f183v = true;
        }
    }

    public void removeClickTrackingOverlay() {
        if (this.f177p != null) {
            ViewParent parent = this.f177p.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(this.f177p);
                this.f177p = null;
                return;
            }
            return;
        }
        this.f166e.mo2288d("AppLovinAdView", "Asked to remove an overlay when none existed. Skipping...");
    }

    public void renderAd(AppLovinAd appLovinAd) {
        renderAd(appLovinAd, null);
    }

    public void renderAd(AppLovinAd appLovinAd, String str) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (!this.f181t) {
            Log.i(AppLovinLogger.SDK_TAG, "Unable to render ad: AppLovinAdView is not initialized.");
        } else if (appLovinAd != this.f176o) {
            this.f166e.mo2288d("AppLovinAdView", "Rendering ad #" + appLovinAd.getAdIdNumber() + " (" + appLovinAd.getSize() + ") over placement: " + str);
            m267a(new C0480k(this, this.f176o));
            this.f180s.set(null);
            this.f176o = appLovinAd;
            this.f168g = str;
            if (appLovinAd.getSize() == this.f167f) {
                if (this.f179r != null) {
                    this.f179r.dismiss();
                }
                m267a(this.f173l);
            } else if (appLovinAd.getSize() == AppLovinAdSize.INTERSTITIAL) {
                m267a(this.f175n);
                m267a(this.f174m);
            }
            if (new dn(this.f164c).ao() || !(appLovinAd instanceof ah)) {
                new dp(this.f164c).m886a();
            }
        } else {
            this.f166e.mo2294w("AppLovinAdView", "Ad #" + appLovinAd.getAdIdNumber() + " is already showing, ignoring");
        }
    }

    public void resume() {
        if (this.f181t) {
            if (this.f184w) {
                this.f165d.addAdUpdateListener(this.f170i, this.f167f);
            }
            AppLovinAd appLovinAd = (AppLovinAd) this.f180s.getAndSet(null);
            if (appLovinAd != null) {
                renderAd(appLovinAd);
            }
            this.f183v = false;
        }
    }

    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        this.f160A = appLovinAdClickListener;
    }

    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.f186y = appLovinAdDisplayListener;
    }

    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.f185x = appLovinAdLoadListener;
    }

    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.f187z = appLovinAdVideoPlaybackListener;
    }

    public void setAutoDestroy(boolean z) {
        this.f182u = z;
    }

    public void setIsForegroundClickInvalidated(boolean z) {
        this.f161B = z;
    }

    public void setParentDialog(WeakReference weakReference) {
        this.f178q = weakReference;
    }
}
