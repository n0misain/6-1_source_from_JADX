package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;

public class UrlEntity extends Entity {
    @SerializedName("display_url")
    public final String displayUrl;
    @SerializedName("expanded_url")
    public final String expandedUrl;
    @SerializedName("url")
    public final String url;

    public /* bridge */ /* synthetic */ int getEnd() {
        return super.getEnd();
    }

    public /* bridge */ /* synthetic */ int getStart() {
        return super.getStart();
    }

    public UrlEntity(String url, String expandedUrl, String displayUrl, int start, int end) {
        super(start, end);
        this.url = url;
        this.expandedUrl = expandedUrl;
        this.displayUrl = displayUrl;
    }
}
