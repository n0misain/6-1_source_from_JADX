package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ApiErrors {
    @SerializedName("errors")
    public final List<ApiError> errors;

    public ApiErrors(List<ApiError> errors) {
        this.errors = errors;
    }
}
