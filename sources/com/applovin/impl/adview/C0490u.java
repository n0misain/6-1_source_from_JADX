package com.applovin.impl.adview;

import android.content.Context;
import android.net.Uri;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.p000a.C0450a;
import com.applovin.impl.p000a.C0455f;
import com.applovin.impl.p000a.C0463n;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.util.List;

/* renamed from: com.applovin.impl.adview.u */
class C0490u extends WebViewClient {
    /* renamed from: a */
    private final AppLovinLogger f361a;
    /* renamed from: b */
    private final AdViewControllerImpl f362b;

    public C0490u(AdViewControllerImpl adViewControllerImpl, AppLovinSdk appLovinSdk) {
        this.f361a = appLovinSdk.getLogger();
        this.f362b = adViewControllerImpl;
    }

    /* renamed from: a */
    private void m403a(C0487r c0487r, Uri uri) {
        AppLovinAd a = c0487r.m401a();
        ViewParent parent = c0487r.getParent();
        if (!(parent instanceof AppLovinAdView) || a == null) {
            this.f361a.mo2289e("AdWebViewClient", "Attempting to track click that is null or not an ApplovinAdView instance for clickedUri = " + uri);
        } else {
            this.f362b.m285a(a, (AppLovinAdView) parent, this.f362b, uri);
        }
    }

    /* renamed from: c */
    private void m404c(C0487r c0487r) {
        this.f362b.expandAd();
    }

    /* renamed from: d */
    private void m405d(C0487r c0487r) {
        this.f362b.contractAd();
    }

    /* renamed from: a */
    void m406a(WebView webView, String str) {
        this.f361a.mo2291i("AdWebViewClient", "Processing click on ad URL \"" + str + "\"");
        if (str != null && (webView instanceof C0487r)) {
            Uri parse = Uri.parse(str);
            C0487r c0487r = (C0487r) webView;
            String scheme = parse.getScheme();
            String host = parse.getHost();
            String path = parse.getPath();
            AppLovinAd currentAd = this.f362b.getCurrentAd();
            if (!AppLovinSdk.URI_SCHEME.equals(scheme) || !AppLovinSdk.URI_HOST.equals(host)) {
                m403a(c0487r, parse);
            } else if (AppLovinAdService.URI_NEXT_AD.equals(path)) {
                m407a(c0487r);
            } else if (AppLovinAdService.URI_CLOSE_AD.equals(path)) {
                m408b(c0487r);
            } else if (AppLovinAdService.URI_EXPAND_AD.equals(path)) {
                m404c(c0487r);
            } else if (AppLovinAdService.URI_CONTRACT_AD.equals(path)) {
                m405d(c0487r);
            } else if (!AppLovinAdServiceImpl.URI_NO_OP.equals(path)) {
                if (AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY.equals(path)) {
                    if (currentAd instanceof C0450a) {
                        C0455f e = ((C0450a) currentAd).m174e();
                        if (e != null) {
                            C0463n.m238a(e.m203c(), (AppLovinSdkImpl) this.f362b.getSdk());
                            m403a(c0487r, e.m201a());
                            return;
                        }
                        return;
                    }
                    m403a(c0487r, Uri.parse(AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY));
                } else if (path == null || !path.startsWith("/launch/")) {
                    this.f361a.mo2294w("AdWebViewClient", "Unknown URL: " + str);
                    this.f361a.mo2294w("AdWebViewClient", "Path: " + path);
                } else {
                    List pathSegments = parse.getPathSegments();
                    if (pathSegments != null && pathSegments.size() > 1) {
                        String str2 = (String) pathSegments.get(pathSegments.size() - 1);
                        try {
                            Context context = webView.getContext();
                            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str2));
                            m403a(c0487r, null);
                        } catch (Throwable e2) {
                            this.f361a.mo2290e("AdWebViewClient", "Threw Exception Trying to Launch App for Package: " + str2, e2);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    void m407a(C0487r c0487r) {
        ViewParent parent = c0487r.getParent();
        if (parent instanceof AppLovinAdView) {
            ((AppLovinAdView) parent).loadNextAd();
        }
    }

    /* renamed from: b */
    void m408b(C0487r c0487r) {
        this.f362b.m282a();
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.f362b.onAdHtmlLoaded(webView);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        m406a(webView, str);
        return true;
    }
}
