package com.twitter.sdk.android.tweetui;

import com.google.gson.annotations.SerializedName;
import java.util.Collections;
import java.util.List;

public class FilterValues {
    @SerializedName("handles")
    public final List<String> handles;
    @SerializedName("hashtags")
    public final List<String> hashtags;
    @SerializedName("keywords")
    public final List<String> keywords;
    @SerializedName("urls")
    public final List<String> urls;

    public FilterValues(List<String> keywords, List<String> hashtags, List<String> handles, List<String> urls) {
        this.keywords = getSafeList(keywords);
        this.hashtags = getSafeList(hashtags);
        this.handles = getSafeList(handles);
        this.urls = getSafeList(urls);
    }

    private <T> List<T> getSafeList(List<T> filters) {
        if (filters == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.unmodifiableList(filters);
    }
}
