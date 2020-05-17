package com.twitter.sdk.android.core;

import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.twitter.sdk.android.core.models.ApiError;
import com.twitter.sdk.android.core.models.ApiErrors;
import com.twitter.sdk.android.core.models.SafeListAdapter;
import com.twitter.sdk.android.core.models.SafeMapAdapter;
import io.fabric.sdk.android.Fabric;
import retrofit2.Response;

public class TwitterApiException extends TwitterException {
    public static final int DEFAULT_ERROR_CODE = 0;
    private final ApiError apiError;
    private final int code;
    private final Response response;
    private final TwitterRateLimit twitterRateLimit;

    public TwitterApiException(Response response) {
        this(response, readApiError(response), readApiRateLimit(response), response.code());
    }

    TwitterApiException(Response response, ApiError apiError, TwitterRateLimit twitterRateLimit, int code) {
        super(createExceptionMessage(code));
        this.apiError = apiError;
        this.twitterRateLimit = twitterRateLimit;
        this.code = code;
        this.response = response;
    }

    public int getStatusCode() {
        return this.code;
    }

    public int getErrorCode() {
        return this.apiError == null ? 0 : this.apiError.code;
    }

    public String getErrorMessage() {
        return this.apiError == null ? null : this.apiError.message;
    }

    public TwitterRateLimit getTwitterRateLimit() {
        return this.twitterRateLimit;
    }

    public Response getResponse() {
        return this.response;
    }

    public static TwitterRateLimit readApiRateLimit(Response response) {
        return new TwitterRateLimit(response.headers());
    }

    public static ApiError readApiError(Response response) {
        try {
            String body = response.errorBody().source().buffer().clone().readUtf8();
            if (!TextUtils.isEmpty(body)) {
                return parseApiError(body);
            }
        } catch (Exception e) {
            Fabric.getLogger().mo4336e(TwitterCore.TAG, "Unexpected response", e);
        }
        return null;
    }

    static ApiError parseApiError(String body) {
        try {
            ApiErrors apiErrors = (ApiErrors) new GsonBuilder().registerTypeAdapterFactory(new SafeListAdapter()).registerTypeAdapterFactory(new SafeMapAdapter()).create().fromJson(body, ApiErrors.class);
            if (!apiErrors.errors.isEmpty()) {
                return (ApiError) apiErrors.errors.get(0);
            }
        } catch (JsonSyntaxException e) {
            Fabric.getLogger().mo4336e(TwitterCore.TAG, "Invalid json: " + body, e);
        }
        return null;
    }

    static String createExceptionMessage(int code) {
        return "HTTP request failed, Status: " + code;
    }
}
