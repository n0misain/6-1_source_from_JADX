package com.applovin.impl.adview;

import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;

/* renamed from: com.applovin.impl.adview.q */
class C0486q extends WebChromeClient {
    /* renamed from: a */
    private final AppLovinLogger f354a;

    public C0486q(AppLovinSdk appLovinSdk) {
        this.f354a = appLovinSdk.getLogger();
    }

    public void onConsoleMessage(String str, int i, String str2) {
        this.f354a.mo2294w("AdWebView", "console.log[" + i + "] :" + str);
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        this.f354a.mo2288d("AdWebView", consoleMessage.sourceId() + ": " + consoleMessage.lineNumber() + ": " + consoleMessage.message());
        return true;
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        this.f354a.mo2294w("AdWebView", "Alert attempted: " + str2);
        return true;
    }

    public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        this.f354a.mo2294w("AdWebView", "JS onBeforeUnload attempted: " + str2);
        return true;
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        this.f354a.mo2294w("AdWebView", "JS confirm attempted: " + str2);
        return true;
    }
}
