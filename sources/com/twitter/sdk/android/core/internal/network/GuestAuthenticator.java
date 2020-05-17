package com.twitter.sdk.android.core.internal.network;

import com.twitter.sdk.android.core.GuestSession;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.Route;

public class GuestAuthenticator implements Authenticator {
    static final int MAX_RETRIES = 2;
    final GuestSessionProvider guestSessionProvider;

    public GuestAuthenticator(GuestSessionProvider guestSessionProvider) {
        this.guestSessionProvider = guestSessionProvider;
    }

    public Request authenticate(Route route, Response response) throws IOException {
        return reauth(response);
    }

    Request reauth(Response response) {
        if (canRetry(response)) {
            GuestSession session = this.guestSessionProvider.refreshCurrentSession(getExpiredSession(response));
            GuestAuthToken token = session == null ? null : (GuestAuthToken) session.getAuthToken();
            if (token != null) {
                return resign(response.request(), token);
            }
        }
        return null;
    }

    GuestSession getExpiredSession(Response response) {
        Headers headers = response.request().headers();
        String auth = headers.get("Authorization");
        String guest = headers.get("x-guest-token");
        if (auth == null || guest == null) {
            return null;
        }
        return new GuestSession(new GuestAuthToken(OAuth2Token.TOKEN_TYPE_BEARER, auth.replace("bearer ", ""), guest));
    }

    Request resign(Request request, GuestAuthToken token) {
        Builder builder = request.newBuilder();
        GuestAuthInterceptor.addAuthHeaders(builder, token);
        return builder.build();
    }

    boolean canRetry(Response response) {
        int responseCount = 1;
        while (true) {
            response = response.priorResponse();
            if (response == null) {
                break;
            }
            responseCount++;
        }
        return responseCount < 2;
    }
}
