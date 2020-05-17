package com.twitter.sdk.android.tweetcomposer;

import com.twitter.sdk.android.core.models.Tweet;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StatusesService {
    @FormUrlEncoded
    @POST("/1.1/statuses/update.json")
    Call<Tweet> update(@Field("status") String str, @Field("card_uri") String str2);
}
