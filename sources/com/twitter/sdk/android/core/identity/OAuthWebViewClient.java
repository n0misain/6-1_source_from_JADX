package com.twitter.sdk.android.core.identity;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.net.URI;
import java.util.Map.Entry;
import java.util.TreeMap;

class OAuthWebViewClient extends WebViewClient {
    private final String completeUrl;
    private final Listener listener;

    interface Listener {
        void onError(WebViewException webViewException);

        void onPageFinished(WebView webView, String str);

        void onSuccess(Bundle bundle);
    }

    public OAuthWebViewClient(String completeUrl, Listener listener) {
        this.completeUrl = completeUrl;
        this.listener = listener;
    }

    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        this.listener.onPageFinished(view, url);
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (!url.startsWith(this.completeUrl)) {
            return super.shouldOverrideUrlLoading(view, url);
        }
        TreeMap<String, String> params = UrlUtils.getQueryParams(URI.create(url), false);
        Bundle bundle = new Bundle(params.size());
        for (Entry<String, String> entry : params.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        this.listener.onSuccess(bundle);
        return true;
    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        this.listener.onError(new WebViewException(errorCode, description, failingUrl));
    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        this.listener.onError(new WebViewException(error.getPrimaryError(), null, null));
    }
}
