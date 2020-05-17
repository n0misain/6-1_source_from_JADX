package com.twitter.sdk.android.core.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class TwitterApi {
    public static final String BASE_HOST = "api.twitter.com";
    public static final String BASE_HOST_URL = "https://api.twitter.com";
    private final String baseHostUrl;

    public TwitterApi() {
        this("https://api.twitter.com");
    }

    public TwitterApi(String baseHostUrl) {
        this.baseHostUrl = baseHostUrl;
    }

    public String getBaseHostUrl() {
        return this.baseHostUrl;
    }

    public Builder buildUponBaseHostUrl(String... paths) {
        Builder builder = Uri.parse(getBaseHostUrl()).buildUpon();
        if (paths != null) {
            for (String p : paths) {
                builder.appendPath(p);
            }
        }
        return builder;
    }

    public static String buildUserAgent(String clientName, String version) {
        return normalizeString('/' + version + ' ' + Build.MODEL + '/' + VERSION.RELEASE + " (" + Build.MANUFACTURER + ';' + Build.MODEL + ';' + Build.BRAND + ';' + Build.PRODUCT + ')');
    }

    static String normalizeString(String str) {
        return stripNonAscii(Normalizer.normalize(str, Form.NFD));
    }

    static String stripNonAscii(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c > '\u001f' && c < '') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
