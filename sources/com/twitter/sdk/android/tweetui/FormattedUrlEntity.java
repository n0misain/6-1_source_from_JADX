package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.models.UrlEntity;

class FormattedUrlEntity {
    final String displayUrl;
    int end;
    final String expandedUrl;
    int start;
    final String url;

    FormattedUrlEntity(UrlEntity entity) {
        this.start = entity.getStart();
        this.end = entity.getEnd();
        this.displayUrl = entity.displayUrl;
        this.url = entity.url;
        this.expandedUrl = entity.expandedUrl;
    }
}
