package com.applovin.impl.adview;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

public class cg extends WebViewClient {
    /* renamed from: a */
    private final AppLovinSdk f328a;
    /* renamed from: b */
    private final AppLovinLogger f329b;
    /* renamed from: c */
    private WeakReference f330c;

    public cg(AppLovinSdk appLovinSdk) {
        this.f328a = appLovinSdk;
        this.f329b = appLovinSdk.getLogger();
    }

    /* renamed from: a */
    void m389a(WebView webView, String str) {
        this.f329b.mo2291i("WebViewButtonClient", "Processing click on ad URL \"" + str + "\"");
        if (str != null && (webView instanceof cf)) {
            cf cfVar = (cf) webView;
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            String host = parse.getHost();
            String path = parse.getPath();
            ch chVar = (ch) this.f330c.get();
            if (!AppLovinSdk.URI_SCHEME.equalsIgnoreCase(scheme) || !AppLovinSdk.URI_HOST.equalsIgnoreCase(host) || chVar == null) {
                return;
            }
            if ("/track_click".equals(path)) {
                chVar.mo2160a(cfVar);
            } else if ("/close_ad".equals(path)) {
                chVar.mo2161b(cfVar);
            } else if ("/skip_ad".equals(path)) {
                chVar.mo2162c(cfVar);
            } else {
                this.f329b.mo2294w("WebViewButtonClient", "Unknown URL: " + str);
                this.f329b.mo2294w("WebViewButtonClient", "Path: " + path);
            }
        }
    }

    /* renamed from: a */
    public void m390a(WeakReference weakReference) {
        this.f330c = weakReference;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        m389a(webView, str);
        return true;
    }
}
