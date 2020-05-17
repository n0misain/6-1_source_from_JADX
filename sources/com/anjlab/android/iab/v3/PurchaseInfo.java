package com.anjlab.android.iab.v3;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class PurchaseInfo implements Parcelable {
    public static final Creator<PurchaseInfo> CREATOR = new C04431();
    private static final String LOG_TAG = "iabv3.purchaseInfo";
    public final PurchaseData purchaseData = parseResponseData();
    public final String responseData;
    public final String signature;

    /* renamed from: com.anjlab.android.iab.v3.PurchaseInfo$1 */
    static class C04431 implements Creator<PurchaseInfo> {
        C04431() {
        }

        public PurchaseInfo createFromParcel(Parcel source) {
            return new PurchaseInfo(source);
        }

        public PurchaseInfo[] newArray(int size) {
            return new PurchaseInfo[size];
        }
    }

    public PurchaseInfo(String responseData, String signature) {
        this.responseData = responseData;
        this.signature = signature;
    }

    @Deprecated
    public PurchaseData parseResponseData() {
        try {
            Date date;
            JSONObject json = new JSONObject(this.responseData);
            PurchaseData data = new PurchaseData();
            data.orderId = json.optString(Constants.RESPONSE_ORDER_ID);
            data.packageName = json.optString("packageName");
            data.productId = json.optString(Constants.RESPONSE_PRODUCT_ID);
            long purchaseTimeMillis = json.optLong(Constants.RESPONSE_PURCHASE_TIME, 0);
            if (purchaseTimeMillis != 0) {
                date = new Date(purchaseTimeMillis);
            } else {
                date = null;
            }
            data.purchaseTime = date;
            data.purchaseState = PurchaseState.values()[json.optInt("purchaseState", 1)];
            data.developerPayload = json.optString(Constants.RESPONSE_PAYLOAD);
            data.purchaseToken = json.getString(Constants.RESPONSE_PURCHASE_TOKEN);
            data.autoRenewing = json.optBoolean("autoRenewing");
            return data;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Failed to parse response data", e);
            return null;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.responseData);
        dest.writeString(this.signature);
    }

    protected PurchaseInfo(Parcel in) {
        this.responseData = in.readString();
        this.signature = in.readString();
    }
}
