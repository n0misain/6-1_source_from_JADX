package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;

public class ApiError {
    @SerializedName("code")
    public final int code;
    @SerializedName("message")
    public final String message;

    public ApiError(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
