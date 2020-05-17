package com.cranevalley.dontendword.features.shared;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class UserInfo implements Parcelable {
    public static final Creator<UserInfo> CREATOR = new C06351();
    public String displayName;
    public String photoUrl;
    public String userId;

    /* renamed from: com.cranevalley.dontendword.features.shared.UserInfo$1 */
    static class C06351 implements Creator<UserInfo> {
        C06351() {
        }

        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    }

    protected UserInfo(Parcel source) {
        this.userId = source.readString();
        this.displayName = source.readString();
        this.photoUrl = source.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.displayName);
        dest.writeString(this.photoUrl);
    }
}
