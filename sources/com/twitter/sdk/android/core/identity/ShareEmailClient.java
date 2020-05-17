package com.twitter.sdk.android.core.identity;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

class ShareEmailClient extends TwitterApiClient {
    static final int RESULT_CODE_CANCELED = 0;
    static final int RESULT_CODE_ERROR = 1;
    static final int RESULT_CODE_OK = -1;
    static final String RESULT_DATA_EMAIL = "email";
    static final String RESULT_DATA_ERROR = "error";
    static final String RESULT_DATA_MSG = "msg";

    interface EmailService {
        @GET("/1.1/account/verify_credentials.json?include_email=true")
        Call<User> verifyCredentials(@Query("include_entities") Boolean bool, @Query("skip_status") Boolean bool2);
    }

    ShareEmailClient(TwitterSession session) {
        super(session);
    }

    protected void getEmail(Callback<User> callback) {
        ((EmailService) getService(EmailService.class)).verifyCredentials(Boolean.valueOf(true), Boolean.valueOf(true)).enqueue(callback);
    }
}
