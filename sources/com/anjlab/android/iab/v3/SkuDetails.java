package com.anjlab.android.iab.v3;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.json.JSONException;
import org.json.JSONObject;

public class SkuDetails implements Parcelable {
    public static final Creator<SkuDetails> CREATOR = new C04441();
    public final String currency;
    public final String description;
    public final boolean isSubscription;
    public final long priceLong;
    public final String priceText;
    public final Double priceValue;
    public final String productId;
    public final String title;

    /* renamed from: com.anjlab.android.iab.v3.SkuDetails$1 */
    static class C04441 implements Creator<SkuDetails> {
        C04441() {
        }

        public SkuDetails createFromParcel(Parcel source) {
            return new SkuDetails(source);
        }

        public SkuDetails[] newArray(int size) {
            return new SkuDetails[size];
        }
    }

    public SkuDetails(JSONObject source) throws JSONException {
        String responseType = source.optString("type");
        if (responseType == null) {
            responseType = Constants.PRODUCT_TYPE_MANAGED;
        }
        this.productId = source.optString(Constants.RESPONSE_PRODUCT_ID);
        this.title = source.optString("title");
        this.description = source.optString("description");
        this.isSubscription = responseType.equalsIgnoreCase(Constants.PRODUCT_TYPE_SUBSCRIPTION);
        this.currency = source.optString(Constants.RESPONSE_PRICE_CURRENCY);
        this.priceLong = source.optLong(Constants.RESPONSE_PRICE_MICROS);
        this.priceValue = Double.valueOf(((double) this.priceLong) / 1000000.0d);
        this.priceText = source.optString("price");
    }

    public String toString() {
        return String.format("%s: %s(%s) %f in %s (%s)", new Object[]{this.productId, this.title, this.description, this.priceValue, this.currency, this.priceText});
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SkuDetails that = (SkuDetails) o;
        if (this.isSubscription != that.isSubscription) {
            return false;
        }
        if (this.productId != null) {
            if (this.productId.equals(that.productId)) {
                return true;
            }
        } else if (that.productId == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result;
        int i = 0;
        if (this.productId != null) {
            result = this.productId.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.isSubscription) {
            i = 1;
        }
        return i2 + i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productId);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeByte(this.isSubscription ? (byte) 1 : (byte) 0);
        dest.writeString(this.currency);
        dest.writeDouble(this.priceValue.doubleValue());
        dest.writeLong(this.priceLong);
        dest.writeString(this.priceText);
    }

    protected SkuDetails(Parcel in) {
        this.productId = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.isSubscription = in.readByte() != (byte) 0;
        this.currency = in.readString();
        this.priceValue = Double.valueOf(in.readDouble());
        this.priceLong = in.readLong();
        this.priceText = in.readString();
    }
}
