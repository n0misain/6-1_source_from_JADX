package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("h")
    /* renamed from: h */
    public final int f1010h;
    @SerializedName("image_type")
    public final String imageType;
    @SerializedName("w")
    /* renamed from: w */
    public final int f1011w;

    public Image(int w, int h, String imageType) {
        this.f1011w = w;
        this.f1010h = h;
        this.imageType = imageType;
    }
}
