package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class VideoInfo implements Serializable {
    @SerializedName("aspect_ratio")
    public final List<Integer> aspectRatio;
    @SerializedName("duration_millis")
    public final long durationMillis;
    @SerializedName("variants")
    public final List<Variant> variants;

    public static class Variant implements Serializable {
        @SerializedName("bitrate")
        public final long bitrate;
        @SerializedName("content_type")
        public final String contentType;
        @SerializedName("url")
        public final String url;

        public Variant(long bitrate, String contentType, String url) {
            this.bitrate = bitrate;
            this.contentType = contentType;
            this.url = url;
        }
    }

    public VideoInfo(List<Integer> aspectRatio, long durationMillis, List<Variant> variants) {
        this.aspectRatio = aspectRatio;
        this.durationMillis = durationMillis;
        this.variants = variants;
    }
}
