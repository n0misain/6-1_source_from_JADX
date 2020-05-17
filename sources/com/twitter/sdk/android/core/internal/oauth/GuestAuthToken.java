package com.twitter.sdk.android.core.internal.oauth;

import com.google.gson.annotations.SerializedName;

public class GuestAuthToken extends OAuth2Token {
    private static final long EXPIRES_IN_MS = 10800000;
    public static final String HEADER_GUEST_TOKEN = "x-guest-token";
    @SerializedName("guest_token")
    private final String guestToken;

    public GuestAuthToken(String tokenType, String accessToken, String guestToken) {
        super(tokenType, accessToken);
        this.guestToken = guestToken;
    }

    public GuestAuthToken(String tokenType, String accessToken, String guestToken, long createdAt) {
        super(tokenType, accessToken, createdAt);
        this.guestToken = guestToken;
    }

    public String getGuestToken() {
        return this.guestToken;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() >= this.createdAt + EXPIRES_IN_MS;
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
        GuestAuthToken that = (GuestAuthToken) o;
        if (this.guestToken != null) {
            if (this.guestToken.equals(that.guestToken)) {
                return true;
            }
        } else if (that.guestToken == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + (this.guestToken != null ? this.guestToken.hashCode() : 0);
    }
}
