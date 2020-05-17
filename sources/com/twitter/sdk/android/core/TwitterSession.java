package com.twitter.sdk.android.core;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.persistence.SerializationStrategy;

public class TwitterSession extends Session<TwitterAuthToken> {
    public static final long UNKNOWN_USER_ID = -1;
    public static final String UNKNOWN_USER_NAME = "";
    @SerializedName("user_name")
    private final String userName;

    static class Serializer implements SerializationStrategy<TwitterSession> {
        private final Gson gson = new Gson();

        public String serialize(TwitterSession session) {
            if (!(session == null || session.getAuthToken() == null)) {
                try {
                    return this.gson.toJson((Object) session);
                } catch (Exception e) {
                    Fabric.getLogger().mo4333d(TwitterCore.TAG, e.getMessage());
                }
            }
            return "";
        }

        public TwitterSession deserialize(String serializedSession) {
            if (!TextUtils.isEmpty(serializedSession)) {
                try {
                    return (TwitterSession) this.gson.fromJson(serializedSession, TwitterSession.class);
                } catch (Exception e) {
                    Fabric.getLogger().mo4333d(TwitterCore.TAG, e.getMessage());
                }
            }
            return null;
        }
    }

    public TwitterSession(TwitterAuthToken authToken, long userId, String userName) {
        super(authToken, userId);
        this.userName = userName;
    }

    public long getUserId() {
        return getId();
    }

    public String getUserName() {
        return this.userName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TwitterSession that = (TwitterSession) o;
        if (this.userName != null) {
            return this.userName.equals(that.userName);
        }
        if (that.userName != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + (this.userName != null ? this.userName.hashCode() : 0);
    }
}
