package com.cranevalley.dontendword.features.shared;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirebaseClient {
    private static final String BASE_URL = "https://us-central1-dont-end-a-word.cloudfunctions.net/";
    private static Retrofit sRetrofit;

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            sRetrofit = new Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return sRetrofit;
    }
}
