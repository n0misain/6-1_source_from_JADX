package com.twitter.sdk.android.core.internal.network;

import com.twitter.sdk.android.core.GuestSession;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class GuestAuthInterceptor implements Interceptor {
    final GuestSessionProvider guestSessionProvider;

    public GuestAuthInterceptor(GuestSessionProvider guestSessionProvider) {
        this.guestSessionProvider = guestSessionProvider;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        GuestSession session = this.guestSessionProvider.getCurrentSession();
        GuestAuthToken token = session == null ? null : (GuestAuthToken) session.getAuthToken();
        if (token == null) {
            return chain.proceed(request);
        }
        Builder builder = request.newBuilder();
        addAuthHeaders(builder, token);
        return chain.proceed(builder.build());
    }

    static void addAuthHeaders(Builder builder, GuestAuthToken token) {
        builder.header("Authorization", token.getTokenType() + " " + token.getAccessToken());
        builder.header("x-guest-token", token.getGuestToken());
    }
}
