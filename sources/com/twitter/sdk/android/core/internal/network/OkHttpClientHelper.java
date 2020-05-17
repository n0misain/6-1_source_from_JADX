package com.twitter.sdk.android.core.internal.network;

import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;

public class OkHttpClientHelper {
    public static OkHttpClient getOkHttpClient(GuestSessionProvider guestSessionProvider, SSLSocketFactory sslSocketFactory) {
        return getOkHttpClientBuilder(guestSessionProvider, sslSocketFactory).build();
    }

    public static Builder getOkHttpClientBuilder(GuestSessionProvider guestSessionProvider, SSLSocketFactory sslSocketFactory) {
        return addGuestAuth(new Builder(), guestSessionProvider, sslSocketFactory);
    }

    public static OkHttpClient getOkHttpClient(Session<? extends TwitterAuthToken> session, TwitterAuthConfig authConfig, SSLSocketFactory sslSocketFactory) {
        return getOkHttpClientBuilder(session, authConfig, sslSocketFactory).build();
    }

    public static Builder getOkHttpClientBuilder(Session<? extends TwitterAuthToken> session, TwitterAuthConfig authConfig, SSLSocketFactory sslSocketFactory) {
        if (session != null) {
            return addSessionAuth(new Builder(), session, authConfig, sslSocketFactory);
        }
        throw new IllegalArgumentException("Session must not be null.");
    }

    public static OkHttpClient getCustomOkHttpClient(OkHttpClient httpClient, GuestSessionProvider guestSessionProvider, SSLSocketFactory sslSocketFactory) {
        if (httpClient != null) {
            return addGuestAuth(httpClient.newBuilder(), guestSessionProvider, sslSocketFactory).build();
        }
        throw new IllegalArgumentException("HttpClient must not be null.");
    }

    public static OkHttpClient getCustomOkHttpClient(OkHttpClient httpClient, Session<? extends TwitterAuthToken> session, TwitterAuthConfig authConfig, SSLSocketFactory sslSocketFactory) {
        if (session == null) {
            throw new IllegalArgumentException("Session must not be null.");
        } else if (httpClient != null) {
            return addSessionAuth(httpClient.newBuilder(), session, authConfig, sslSocketFactory).build();
        } else {
            throw new IllegalArgumentException("HttpClient must not be null.");
        }
    }

    static Builder addGuestAuth(Builder builder, GuestSessionProvider guestSessionProvider, SSLSocketFactory sslSocketFactory) {
        return builder.sslSocketFactory(sslSocketFactory).authenticator(new GuestAuthenticator(guestSessionProvider)).addInterceptor(new GuestAuthInterceptor(guestSessionProvider)).addNetworkInterceptor(new GuestAuthNetworkInterceptor());
    }

    static Builder addSessionAuth(Builder builder, Session<? extends TwitterAuthToken> session, TwitterAuthConfig authConfig, SSLSocketFactory sslSocketFactory) {
        return builder.sslSocketFactory(sslSocketFactory).addInterceptor(new OAuth1aInterceptor(session, authConfig));
    }
}
