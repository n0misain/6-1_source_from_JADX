package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.util.Collections;
import java.util.List;

public class TweetEntities {
    @SerializedName("hashtags")
    public final List<HashtagEntity> hashtags;
    @SerializedName("media")
    public final List<MediaEntity> media;
    @SerializedName("symbols")
    public final List<SymbolEntity> symbols;
    @SerializedName("urls")
    public final List<UrlEntity> urls;
    @SerializedName("user_mentions")
    public final List<MentionEntity> userMentions;

    @Deprecated
    public TweetEntities(List<UrlEntity> urls, List<MentionEntity> userMentions, List<MediaEntity> media, List<HashtagEntity> hashtags) {
        this(urls, userMentions, media, hashtags, null);
    }

    public TweetEntities(List<UrlEntity> urls, List<MentionEntity> userMentions, List<MediaEntity> media, List<HashtagEntity> hashtags, List<SymbolEntity> symbols) {
        this.urls = getSafeList(urls);
        this.userMentions = getSafeList(userMentions);
        this.media = getSafeList(media);
        this.hashtags = getSafeList(hashtags);
        this.symbols = getSafeList(symbols);
    }

    private <T> List<T> getSafeList(List<T> entities) {
        if (entities == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.unmodifiableList(entities);
    }
}
