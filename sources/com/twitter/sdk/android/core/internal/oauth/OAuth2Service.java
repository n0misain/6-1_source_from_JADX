package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.TwitterApi;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.network.HttpRequest.Base64;
import io.fabric.sdk.android.services.network.UrlUtils;
import javax.net.ssl.SSLSocketFactory;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class OAuth2Service extends OAuthService {
    OAuth2Api api = ((OAuth2Api) getRetrofit().create(OAuth2Api.class));

    interface OAuth2Api {
        @FormUrlEncoded
        @POST("/oauth2/token")
        @Headers({"Content-Type: application/x-www-form-urlencoded;charset=UTF-8"})
        Call<OAuth2Token> getAppAuthToken(@Header("Authorization") String str, @Field("grant_type") String str2);

        @POST("/1.1/guest/activate.json")
        Call<GuestTokenResponse> getGuestToken(@Header("Authorization") String str);
    }

    public OAuth2Service(TwitterCore twitterCore, SSLSocketFactory sslSocketFactory, TwitterApi api) {
        super(twitterCore, sslSocketFactory, api);
    }

    public void requestGuestAuthToken(final Callback<GuestAuthToken> callback) {
        requestAppAuthToken(new Callback<OAuth2Token>() {
            public void success(Result<OAuth2Token> result) {
                final OAuth2Token appAuthToken = result.data;
                OAuth2Service.this.requestGuestToken(new Callback<GuestTokenResponse>() {
                    public void success(Result<GuestTokenResponse> result) {
                        callback.success(new Result(new GuestAuthToken(appAuthToken.getTokenType(), appAuthToken.getAccessToken(), ((GuestTokenResponse) result.data).guestToken), null));
                    }

                    public void failure(TwitterException error) {
                        Fabric.getLogger().mo4336e(TwitterCore.TAG, "Your app may not allow guest auth. Please talk to us regarding upgrading your consumer key.", error);
                        callback.failure(error);
                    }
                }, appAuthToken);
            }

            public void failure(TwitterException error) {
                Fabric.getLogger().mo4336e(TwitterCore.TAG, "Failed to get app auth token", error);
                if (callback != null) {
                    callback.failure(error);
                }
            }
        });
    }

    void requestAppAuthToken(Callback<OAuth2Token> callback) {
        this.api.getAppAuthToken(getAuthHeader(), OAuthConstants.GRANT_TYPE_CLIENT_CREDENTIALS).enqueue(callback);
    }

    void requestGuestToken(Callback<GuestTokenResponse> callback, OAuth2Token appAuthToken) {
        this.api.getGuestToken(getAuthorizationHeader(appAuthToken)).enqueue(callback);
    }

    private String getAuthorizationHeader(OAuth2Token token) {
        return "Bearer " + token.getAccessToken();
    }

    private String getAuthHeader() {
        TwitterAuthConfig authConfig = getTwitterCore().getAuthConfig();
        return "Basic " + Base64.encode(UrlUtils.percentEncode(authConfig.getConsumerKey()) + ":" + UrlUtils.percentEncode(authConfig.getConsumerSecret()));
    }
}
