package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.internal.TwitterApi;
import java.io.IOException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

abstract class OAuthService {
    private static final String CLIENT_NAME = "TwitterAndroidSDK";
    private final TwitterApi api;
    private final Retrofit retrofit;
    private final TwitterCore twitterCore;
    private final String userAgent;

    /* renamed from: com.twitter.sdk.android.core.internal.oauth.OAuthService$1 */
    class C10141 implements Interceptor {
        C10141() {
        }

        public Response intercept(Chain chain) throws IOException {
            return chain.proceed(chain.request().newBuilder().header("User-Agent", OAuthService.this.getUserAgent()).build());
        }
    }

    OAuthService(TwitterCore twitterCore, SSLSocketFactory sslSocketFactory, TwitterApi api) {
        this.twitterCore = twitterCore;
        this.api = api;
        this.userAgent = TwitterApi.buildUserAgent(CLIENT_NAME, twitterCore.getVersion());
        if (sslSocketFactory == null) {
            throw new IllegalArgumentException("sslSocketFactory must not be null");
        }
        this.retrofit = new Builder().baseUrl(getApi().getBaseHostUrl()).client(new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory).addInterceptor(new C10141()).build()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    protected TwitterCore getTwitterCore() {
        return this.twitterCore;
    }

    protected TwitterApi getApi() {
        return this.api;
    }

    protected String getUserAgent() {
        return this.userAgent;
    }

    protected Retrofit getRetrofit() {
        return this.retrofit;
    }
}
