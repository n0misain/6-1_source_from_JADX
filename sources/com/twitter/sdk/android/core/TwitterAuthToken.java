package com.twitter.sdk.android.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;

public class TwitterAuthToken extends AuthToken implements Parcelable {
    public static final Creator<TwitterAuthToken> CREATOR = new C10011();
    @SerializedName("secret")
    public final String secret;
    @SerializedName("token")
    public final String token;

    /* renamed from: com.twitter.sdk.android.core.TwitterAuthToken$1 */
    static class C10011 implements Creator<TwitterAuthToken> {
        C10011() {
        }

        public TwitterAuthToken createFromParcel(Parcel in) {
            return new TwitterAuthToken(in);
        }

        public TwitterAuthToken[] newArray(int size) {
            return new TwitterAuthToken[size];
        }
    }

    public TwitterAuthToken(String token, String secret) {
        this.token = token;
        this.secret = secret;
    }

    TwitterAuthToken(String token, String secret, long createdAt) {
        super(createdAt);
        this.token = token;
        this.secret = secret;
    }

    private TwitterAuthToken(Parcel in) {
        this.token = in.readString();
        this.secret = in.readString();
    }

    public boolean isExpired() {
        return false;
    }

    public String toString() {
        return "token=" + this.token + ",secret=" + this.secret;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.token);
        out.writeString(this.secret);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TwitterAuthToken)) {
            return false;
        }
        TwitterAuthToken that = (TwitterAuthToken) o;
        if (this.secret == null ? that.secret != null : !this.secret.equals(that.secret)) {
            return false;
        }
        if (this.token != null) {
            if (this.token.equals(that.token)) {
                return true;
            }
        } else if (that.token == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result;
        int i = 0;
        if (this.token != null) {
            result = this.token.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.secret != null) {
            i = this.secret.hashCode();
        }
        return i2 + i;
    }
}
