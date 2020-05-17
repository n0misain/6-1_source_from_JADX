package com.twitter.sdk.android.core;

import com.google.gson.annotations.SerializedName;

public class Session<T extends AuthToken> {
    @SerializedName("auth_token")
    private final T authToken;
    @SerializedName("id")
    private final long id;

    public Session(T authToken, long id) {
        if (authToken == null) {
            throw new IllegalArgumentException("AuthToken must not be null.");
        }
        this.authToken = authToken;
        this.id = id;
    }

    public T getAuthToken() {
        return this.authToken;
    }

    public long getId() {
        return this.id;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        if (this.id != session.id) {
            return false;
        }
        if (this.authToken != null) {
            return this.authToken.equals(session.authToken);
        }
        if (session.authToken != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.authToken != null ? this.authToken.hashCode() : 0) * 31) + ((int) (this.id ^ (this.id >>> 32)));
    }
}
