package com.anjlab.android.iab.v3;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Date;
import org.json.JSONException;

public class TransactionDetails implements Parcelable {
    public static final Creator<TransactionDetails> CREATOR = new C04451();
    @Deprecated
    public final String orderId = this.purchaseInfo.purchaseData.orderId;
    @Deprecated
    public final String productId = this.purchaseInfo.purchaseData.productId;
    public final PurchaseInfo purchaseInfo;
    @Deprecated
    public final Date purchaseTime = this.purchaseInfo.purchaseData.purchaseTime;
    @Deprecated
    public final String purchaseToken = this.purchaseInfo.purchaseData.purchaseToken;

    /* renamed from: com.anjlab.android.iab.v3.TransactionDetails$1 */
    static class C04451 implements Creator<TransactionDetails> {
        C04451() {
        }

        public TransactionDetails createFromParcel(Parcel source) {
            return new TransactionDetails(source);
        }

        public TransactionDetails[] newArray(int size) {
            return new TransactionDetails[size];
        }
    }

    public TransactionDetails(PurchaseInfo info) throws JSONException {
        this.purchaseInfo = info;
    }

    public String toString() {
        return String.format("%s purchased at %s(%s). Token: %s, Signature: %s", new Object[]{this.productId, this.purchaseTime, this.orderId, this.purchaseToken, this.purchaseInfo.signature});
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactionDetails details = (TransactionDetails) o;
        if (this.orderId != null) {
            if (this.orderId.equals(details.orderId)) {
                return true;
            }
        } else if (details.orderId == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.orderId != null ? this.orderId.hashCode() : 0;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.purchaseInfo, flags);
    }

    protected TransactionDetails(Parcel in) {
        this.purchaseInfo = (PurchaseInfo) in.readParcelable(PurchaseInfo.class.getClassLoader());
    }
}
