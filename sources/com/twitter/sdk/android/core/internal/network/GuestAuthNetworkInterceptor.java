package com.twitter.sdk.android.core.internal.network;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;

public class GuestAuthNetworkInterceptor implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.code() == 403) {
            return response.newBuilder().code(401).build();
        }
        return response;
    }
}
