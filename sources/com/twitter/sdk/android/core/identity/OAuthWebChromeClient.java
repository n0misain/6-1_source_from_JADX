package com.twitter.sdk.android.core.identity;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

class OAuthWebChromeClient extends WebChromeClient {
    OAuthWebChromeClient() {
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return true;
    }
}
