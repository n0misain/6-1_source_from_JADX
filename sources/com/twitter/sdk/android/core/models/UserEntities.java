package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.util.Collections;
import java.util.List;

public class UserEntities {
    @SerializedName("description")
    public final UrlEntities description;
    @SerializedName("url")
    public final UrlEntities url;

    public static class UrlEntities {
        @SerializedName("urls")
        public final List<UrlEntity> urls;

        public UrlEntities(List<UrlEntity> urls) {
            this.urls = getSafeList(urls);
        }

        private <T> List<T> getSafeList(List<T> entities) {
            if (entities == null) {
                return Collections.EMPTY_LIST;
            }
            return Collections.unmodifiableList(entities);
        }
    }

    public UserEntities(UrlEntities url, UrlEntities description) {
        this.url = url;
        this.description = description;
    }
}
