package com.anjlab.android.iab.v3;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Date;

public class PurchaseData implements Parcelable {
    public static final Creator<PurchaseData> CREATOR = new C04421();
    public boolean autoRenewing;
    public String developerPayload;
    public String orderId;
    public String packageName;
    public String productId;
    public PurchaseState purchaseState;
    public Date purchaseTime;
    public String purchaseToken;

    /* renamed from: com.anjlab.android.iab.v3.PurchaseData$1 */
    static class C04421 implements Creator<PurchaseData> {
        C04421() {
        }

        public PurchaseData createFromParcel(Parcel source) {
            return new PurchaseData(source);
        }

        public PurchaseData[] newArray(int size) {
            return new PurchaseData[size];
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderId);
        dest.writeString(this.packageName);
        dest.writeString(this.productId);
        dest.writeLong(this.purchaseTime != null ? this.purchaseTime.getTime() : -1);
        dest.writeInt(this.purchaseState == null ? -1 : this.purchaseState.ordinal());
        dest.writeString(this.developerPayload);
        dest.writeString(this.purchaseToken);
        dest.writeByte(this.autoRenewing ? (byte) 1 : (byte) 0);
    }

    protected PurchaseData(Parcel in) {
        PurchaseState purchaseState = null;
        this.orderId = in.readString();
        this.packageName = in.readString();
        this.productId = in.readString();
        long tmpPurchaseTime = in.readLong();
        this.purchaseTime = tmpPurchaseTime == -1 ? null : new Date(tmpPurchaseTime);
        int tmpPurchaseState = in.readInt();
        if (tmpPurchaseState != -1) {
            purchaseState = PurchaseState.values()[tmpPurchaseState];
        }
        this.purchaseState = purchaseState;
        this.developerPayload = in.readString();
        this.purchaseToken = in.readString();
        this.autoRenewing = in.readByte() != (byte) 0;
    }
}
