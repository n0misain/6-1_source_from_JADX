package com.cranevalley.dontendword.features.main.chat;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

class MessageInfo implements Parcelable {
    public static final Creator<MessageInfo> CREATOR = new C05501();
    Boolean receiver;
    String text;

    /* renamed from: com.cranevalley.dontendword.features.main.chat.MessageInfo$1 */
    static class C05501 implements Creator<MessageInfo> {
        C05501() {
        }

        public MessageInfo createFromParcel(Parcel source) {
            return new MessageInfo(source);
        }

        public MessageInfo[] newArray(int size) {
            return new MessageInfo[size];
        }
    }

    MessageInfo() {
    }

    protected MessageInfo(Parcel source) {
        this.text = source.readString();
        this.receiver = Boolean.valueOf(source.readByte() != (byte) 0);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeByte((byte) (this.receiver.booleanValue() ? 1 : 0));
    }
}
