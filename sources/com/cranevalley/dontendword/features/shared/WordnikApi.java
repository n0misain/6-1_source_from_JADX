package com.cranevalley.dontendword.features.shared;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WordnikApi {
    @GET("word.json/{letters}/definitions")
    Call<List<WordInfo>> requestWordInfosList(@Path("letters") String str, @Query("api_key") String str2, @Query("limit") int i);
}
