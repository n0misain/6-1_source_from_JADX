package com.twitter.sdk.android.core;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.persistence.SerializationStrategy;

public class GuestSession extends Session<GuestAuthToken> {
    public static final long LOGGED_OUT_USER_ID = 0;

    public static class Serializer implements SerializationStrategy<GuestSession> {
        private final Gson gson = new GsonBuilder().registerTypeAdapter(GuestAuthToken.class, new AuthTokenAdapter()).create();

        public String serialize(GuestSession session) {
            if (!(session == null || session.getAuthToken() == null)) {
                try {
                    return this.gson.toJson((Object) session);
                } catch (Exception e) {
                    Fabric.getLogger().mo4333d(TwitterCore.TAG, "Failed to serialize session " + e.getMessage());
                }
            }
            return "";
        }

        public GuestSession deserialize(String serializedSession) {
            if (!TextUtils.isEmpty(serializedSession)) {
                try {
                    return (GuestSession) this.gson.fromJson(serializedSession, GuestSession.class);
                } catch (Exception e) {
                    Fabric.getLogger().mo4333d(TwitterCore.TAG, "Failed to deserialize session " + e.getMessage());
                }
            }
            return null;
        }
    }

    public GuestSession(GuestAuthToken authToken) {
        super(authToken, 0);
    }
}
