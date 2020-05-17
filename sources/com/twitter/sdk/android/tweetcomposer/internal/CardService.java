package com.twitter.sdk.android.tweetcomposer.internal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CardService {
    @FormUrlEncoded
    @POST("https://caps.twitter.com/v2/cards/create.json")
    Call<CardCreate> create(@Field("card_data") CardData cardData);
}
