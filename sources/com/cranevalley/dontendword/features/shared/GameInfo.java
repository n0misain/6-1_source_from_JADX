package com.cranevalley.dontendword.features.shared;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GameInfo implements Parcelable {
    public static final Creator<GameInfo> CREATOR = new C06341();
    public Boolean challenged;
    public String gameId;
    public String letters;
    public Long nudgeTime;
    public String opponentDisplayName;
    public String opponentPhotoUrl;
    public String opponentUserId;
    public String report;
    public String result;
    public Boolean turn;

    /* renamed from: com.cranevalley.dontendword.features.shared.GameInfo$1 */
    static class C06341 implements Creator<GameInfo> {
        C06341() {
        }

        public GameInfo createFromParcel(Parcel source) {
            return new GameInfo(source);
        }

        public GameInfo[] newArray(int size) {
            return new GameInfo[size];
        }
    }

    protected GameInfo(Parcel source) {
        Long l = null;
        this.gameId = source.readString();
        this.opponentUserId = source.readString();
        this.opponentDisplayName = source.readString();
        this.opponentPhotoUrl = source.readString();
        this.letters = source.readString();
        switch (source.readInt()) {
            case 0:
                this.turn = Boolean.valueOf(false);
                break;
            case 1:
                this.turn = Boolean.valueOf(true);
                break;
            default:
                this.turn = null;
                break;
        }
        switch (source.readInt()) {
            case 0:
                this.challenged = Boolean.valueOf(false);
                break;
            case 1:
                this.challenged = Boolean.valueOf(true);
                break;
            default:
                this.challenged = null;
                break;
        }
        long tempNudgeTime = source.readLong();
        if (tempNudgeTime != Long.MIN_VALUE) {
            l = Long.valueOf(tempNudgeTime);
        }
        this.nudgeTime = l;
        this.result = source.readString();
        this.report = source.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        dest.writeString(this.gameId);
        dest.writeString(this.opponentUserId);
        dest.writeString(this.opponentDisplayName);
        dest.writeString(this.opponentPhotoUrl);
        dest.writeString(this.letters);
        int i2 = this.turn != null ? this.turn.booleanValue() ? 1 : 0 : -1;
        dest.writeInt(i2);
        if (this.challenged == null) {
            i = -1;
        } else if (!this.challenged.booleanValue()) {
            i = 0;
        }
        dest.writeInt(i);
        dest.writeLong(this.nudgeTime != null ? this.nudgeTime.longValue() : Long.MIN_VALUE);
        dest.writeString(this.result);
        dest.writeString(this.report);
    }
}
