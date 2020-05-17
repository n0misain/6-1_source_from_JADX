package com.twitter.sdk.android.core.internal.oauth;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.AuthToken;

public class OAuth2Token extends AuthToken implements Parcelable {
    public static final Creator<OAuth2Token> CREATOR = new C10121();
    public static final String TOKEN_TYPE_BEARER = "bearer";
    @SerializedName("access_token")
    private final String accessToken;
    @SerializedName("token_type")
    private final String tokenType;

    /* renamed from: com.twitter.sdk.android.core.internal.oauth.OAuth2Token$1 */
    static class C10121 implements Creator<OAuth2Token> {
        C10121() {
        }

        public OAuth2Token createFromParcel(Parcel in) {
            return new OAuth2Token(in);
        }

        public OAuth2Token[] newArray(int size) {
            return new OAuth2Token[size];
        }
    }

    public OAuth2Token(String tokenType, String accessToken) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
    }

    public OAuth2Token(String tokenType, String accessToken, long createdAt) {
        super(createdAt);
        this.tokenType = tokenType;
        this.accessToken = accessToken;
    }

    private OAuth2Token(Parcel in) {
        this.tokenType = in.readString();
        this.accessToken = in.readString();
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public boolean isExpired() {
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.tokenType);
        out.writeString(this.accessToken);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OAuth2Token that = (OAuth2Token) o;
        if (this.accessToken == null ? that.accessToken != null : !this.accessToken.equals(that.accessToken)) {
            return false;
        }
        if (this.tokenType != null) {
            if (this.tokenType.equals(that.tokenType)) {
                return true;
            }
        } else if (that.tokenType == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result;
        int i = 0;
        if (this.tokenType != null) {
            result = this.tokenType.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.accessToken != null) {
            i = this.accessToken.hashCode();
        }
        return i2 + i;
    }
}
