package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.applovin.impl.p000a.C0450a;
import com.applovin.impl.p000a.C0455f;
import com.applovin.impl.p000a.C0458i;
import com.applovin.impl.p000a.C0459j;
import com.applovin.impl.sdk.C0503g;
import com.applovin.impl.sdk.C0516u;
import com.applovin.impl.sdk.ae;
import com.applovin.impl.sdk.ah;
import com.applovin.impl.sdk.dn;
import com.applovin.impl.sdk.fk;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

/* renamed from: com.applovin.impl.adview.r */
class C0487r extends WebView {
    /* renamed from: a */
    private final AppLovinLogger f355a;
    /* renamed from: b */
    private final AppLovinSdk f356b;
    /* renamed from: c */
    private AppLovinAd f357c = null;
    /* renamed from: d */
    private boolean f358d = false;

    C0487r(C0490u c0490u, AppLovinSdk appLovinSdk, Context context) {
        super(context);
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to create AdWebView. No sdk specified.");
        }
        this.f356b = appLovinSdk;
        this.f355a = appLovinSdk.getLogger();
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptEnabled(true);
        setWebViewClient(c0490u);
        setWebChromeClient(new C0486q(appLovinSdk));
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setScrollBarStyle(33554432);
        setOnTouchListener(new C0488s(this));
        setOnLongClickListener(new C0489t(this));
    }

    /* renamed from: a */
    AppLovinAd m401a() {
        return this.f357c;
    }

    /* renamed from: a */
    public void m402a(AppLovinAd appLovinAd, String str) {
        if (this.f358d) {
            this.f355a.userError("AdWebView", "Ad can not be loaded in a destroyed webview");
            return;
        }
        this.f357c = appLovinAd;
        try {
            dn dnVar = new dn(this.f356b);
            if (dnVar.m840P() || ((appLovinAd instanceof ae) && ((ae) appLovinAd).m134K())) {
                loadUrl("about:blank");
            }
            if (C0516u.m1164c() && (appLovinAd instanceof ae)) {
                getSettings().setMediaPlaybackRequiresUserGesture(((ae) appLovinAd).m156w());
            }
            if (appLovinAd instanceof ah) {
                loadDataWithBaseURL("/", ((ah) appLovinAd).m513a(), "text/html", null, "");
                this.f355a.mo2288d("AdWebView", "Empty ad rendered");
            } else if (appLovinAd instanceof C0503g) {
                loadDataWithBaseURL("/", fk.m1084a(str, ((C0503g) appLovinAd).mo2133a()), "text/html", null, "");
                this.f355a.mo2288d("AdWebView", "AppLovinAd rendered");
            } else if (appLovinAd instanceof C0450a) {
                C0455f e = ((C0450a) appLovinAd).m174e();
                if (e != null) {
                    C0458i b = e.m202b();
                    Uri b2 = b.m217b();
                    String uri = b2 != null ? b2.toString() : "";
                    String c = b.m218c();
                    if (!URLUtil.isValidUrl(uri) && !AppLovinSdkUtils.isValidString(c)) {
                        this.f355a.mo2289e("AdWebView", "Unable to load companion ad. No resources provided.");
                        return;
                    } else if (b.m214a() == C0459j.STATIC) {
                        this.f355a.mo2288d("AdWebView", "Rendering WebView for static VAST ad");
                        loadDataWithBaseURL("/", dnVar.ae().replace("{SOURCE}", uri), "text/html", null, "");
                        return;
                    } else if (b.m214a() == C0459j.HTML) {
                        if (AppLovinSdkUtils.isValidString(c)) {
                            this.f355a.mo2288d("AdWebView", "Rendering WebView for HTML VAST ad with resourceContents: " + c);
                            loadDataWithBaseURL("/", c, "text/html", null, "");
                            return;
                        }
                        this.f355a.mo2288d("AdWebView", "Rendering WebView for HTML VAST ad with resourceUri: " + uri);
                        loadUrl(uri);
                        return;
                    } else if (b.m214a() != C0459j.IFRAME) {
                        this.f355a.mo2289e("AdWebView", "Failed to render VAST companion ad of invalid type");
                        return;
                    } else if (AppLovinSdkUtils.isValidString(uri)) {
                        this.f355a.mo2288d("AdWebView", "Rendering WebView for iFrame VAST ad with resourceUri: " + uri);
                        loadUrl(uri);
                        return;
                    } else {
                        this.f355a.mo2288d("AdWebView", "Rendering WebView for iFrame VAST ad with resourceContents: " + c);
                        loadDataWithBaseURL("/", c, "text/html", null, "");
                        return;
                    }
                }
                this.f355a.mo2288d("AdWebView", "No companion ad provided.");
            }
        } catch (Throwable th) {
            this.f355a.mo2290e("AdWebView", "Unable to render AppLovinAd with placement = \"" + str + "\"", th);
        }
    }

    public void computeScroll() {
    }

    public void destroy() {
        this.f358d = true;
        try {
            super.destroy();
            this.f355a.mo2288d("AdWebView", "Web view destroyed");
        } catch (Throwable th) {
            if (this.f355a != null) {
                this.f355a.mo2290e("AdWebView", "destroy() threw exception", th);
            }
        }
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        try {
            super.onFocusChanged(z, i, rect);
        } catch (Throwable e) {
            this.f355a.mo2290e("AdWebView", "onFocusChanged() threw exception", e);
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
    }

    public void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
        } catch (Throwable e) {
            this.f355a.mo2290e("AdWebView", "onWindowFocusChanged() threw exception", e);
        }
    }

    protected void onWindowVisibilityChanged(int i) {
        try {
            super.onWindowVisibilityChanged(i);
        } catch (Throwable e) {
            this.f355a.mo2290e("AdWebView", "onWindowVisibilityChanged() threw exception", e);
        }
    }

    public boolean requestFocus(int i, Rect rect) {
        try {
            return super.requestFocus(i, rect);
        } catch (Throwable e) {
            this.f355a.mo2290e("AdWebView", "requestFocus() threw exception", e);
            return false;
        }
    }

    public void scrollTo(int i, int i2) {
    }
}
