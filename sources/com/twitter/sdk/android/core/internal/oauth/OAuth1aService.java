package com.twitter.sdk.android.core.internal.oauth;

import android.net.Uri;
import com.facebook.internal.ServerProtocol;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.TwitterApi;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.UrlUtils;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class OAuth1aService extends OAuthService {
    private static final String CALLBACK_URL = "twittersdk://callback";
    private static final String PARAM_SCREEN_NAME = "screen_name";
    private static final String PARAM_USER_ID = "user_id";
    private static final String RESOURCE_OAUTH = "oauth";
    OAuthApi api = ((OAuthApi) getRetrofit().create(OAuthApi.class));

    interface OAuthApi {
        @POST("/oauth/access_token")
        Call<ResponseBody> getAccessToken(@Header("Authorization") String str, @Query("oauth_verifier") String str2);

        @POST("/oauth/request_token")
        Call<ResponseBody> getTempToken(@Header("Authorization") String str);
    }

    public OAuth1aService(TwitterCore twitterCore, SSLSocketFactory sslSocketFactory, TwitterApi api) {
        super(twitterCore, sslSocketFactory, api);
    }

    public void requestTempToken(Callback<OAuthResponse> callback) {
        TwitterAuthConfig config = getTwitterCore().getAuthConfig();
        this.api.getTempToken(new OAuth1aHeaders().getAuthorizationHeader(config, null, buildCallbackUrl(config), HttpRequest.METHOD_POST, getTempTokenUrl(), null)).enqueue(getCallbackWrapper(callback));
    }

    String getTempTokenUrl() {
        return getApi().getBaseHostUrl() + "/oauth/request_token";
    }

    public String buildCallbackUrl(TwitterAuthConfig authConfig) {
        return Uri.parse(CALLBACK_URL).buildUpon().appendQueryParameter(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, getTwitterCore().getVersion()).appendQueryParameter(SettingsJsonConstants.APP_KEY, authConfig.getConsumerKey()).build().toString();
    }

    public void requestAccessToken(Callback<OAuthResponse> callback, TwitterAuthToken requestToken, String verifier) {
        TwitterAuthToken twitterAuthToken = requestToken;
        this.api.getAccessToken(new OAuth1aHeaders().getAuthorizationHeader(getTwitterCore().getAuthConfig(), twitterAuthToken, null, HttpRequest.METHOD_POST, getAccessTokenUrl(), null), verifier).enqueue(getCallbackWrapper(callback));
    }

    String getAccessTokenUrl() {
        return getApi().getBaseHostUrl() + "/oauth/access_token";
    }

    public String getAuthorizeUrl(TwitterAuthToken requestToken) {
        return getApi().buildUponBaseHostUrl(RESOURCE_OAUTH, "authorize").appendQueryParameter(OAuthConstants.PARAM_TOKEN, requestToken.token).build().toString();
    }

    public static OAuthResponse parseAuthResponse(String response) {
        long userId;
        TreeMap<String, String> params = UrlUtils.getQueryParams(response, false);
        String token = (String) params.get(OAuthConstants.PARAM_TOKEN);
        String secret = (String) params.get(OAuthConstants.PARAM_TOKEN_SECRET);
        String userName = (String) params.get(PARAM_SCREEN_NAME);
        if (params.containsKey("user_id")) {
            userId = Long.parseLong((String) params.get("user_id"));
        } else {
            userId = 0;
        }
        if (token == null || secret == null) {
            return null;
        }
        return new OAuthResponse(new TwitterAuthToken(token, secret), userName, userId);
    }

    Callback<ResponseBody> getCallbackWrapper(final Callback<OAuthResponse> callback) {
        return new Callback<ResponseBody>() {
            public void success(Result<ResponseBody> result) {
                Throwable th;
                IOException e;
                BufferedReader reader = null;
                StringBuilder sb = new StringBuilder();
                try {
                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(((ResponseBody) result.data).byteStream()));
                    while (true) {
                        try {
                            String line = reader2.readLine();
                            if (line == null) {
                                break;
                            }
                            sb.append(line);
                        } catch (Throwable th2) {
                            th = th2;
                            reader = reader2;
                        }
                    }
                    if (reader2 != null) {
                        try {
                            reader2.close();
                        } catch (IOException e2) {
                            e = e2;
                            reader = reader2;
                            callback.failure(new TwitterAuthException(e.getMessage(), e));
                            return;
                        }
                    }
                    String responseAsStr = sb.toString();
                    OAuthResponse authResponse = OAuth1aService.parseAuthResponse(responseAsStr);
                    if (authResponse == null) {
                        callback.failure(new TwitterAuthException("Failed to parse auth response: " + responseAsStr));
                    } else {
                        callback.success(new Result(authResponse, null));
                    }
                    reader = reader2;
                } catch (Throwable th3) {
                    th = th3;
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e3) {
                            e = e3;
                            callback.failure(new TwitterAuthException(e.getMessage(), e));
                            return;
                        }
                    }
                    throw th;
                }
            }

            public void failure(TwitterException exception) {
                callback.failure(exception);
            }
        };
    }
}
