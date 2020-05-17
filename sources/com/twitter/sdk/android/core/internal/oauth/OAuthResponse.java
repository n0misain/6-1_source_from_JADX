package com.twitter.sdk.android.core.internal.oauth;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.twitter.sdk.android.core.TwitterAuthToken;

public class OAuthResponse implements Parcelable {
    public static final Creator<OAuthResponse> CREATOR = new C10131();
    public final TwitterAuthToken authToken;
    public final long userId;
    public final String userName;

    /* renamed from: com.twitter.sdk.android.core.internal.oauth.OAuthResponse$1 */
    static class C10131 implements Creator<OAuthResponse> {
        C10131() {
        }

        public OAuthResponse createFromParcel(Parcel in) {
            return new OAuthResponse(in);
        }

        public OAuthResponse[] newArray(int size) {
            return new OAuthResponse[size];
        }
    }

    public OAuthResponse(TwitterAuthToken authToken, String userName, long userId) {
        this.authToken = authToken;
        this.userName = userName;
        this.userId = userId;
    }

    private OAuthResponse(Parcel in) {
        this.authToken = (TwitterAuthToken) in.readParcelable(TwitterAuthToken.class.getClassLoader());
        this.userName = in.readString();
        this.userId = in.readLong();
    }

    public String toString() {
        return "authToken=" + this.authToken + ",userName=" + this.userName + ",userId=" + this.userId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.authToken, flags);
        out.writeString(this.userName);
        out.writeLong(this.userId);
    }
}
