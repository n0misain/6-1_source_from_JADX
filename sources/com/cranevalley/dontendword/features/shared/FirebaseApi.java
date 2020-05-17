package com.cranevalley.dontendword.features.shared;

import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FirebaseApi {
    @GET("getServerTime")
    Call<ResponseBody> getServerTime(@Header("IdToken") String str);

    @GET("getStarterLetter")
    Call<ResponseBody> getStarterLetter(@Header("IdToken") String str);

    @POST("joinRandomGame")
    Call<ResponseBody> joinRandomGame(@Header("IdToken") String str, @Body Map<String, Object> map);
}
