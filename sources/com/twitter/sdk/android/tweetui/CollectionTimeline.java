package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.TweetBuilder;
import com.twitter.sdk.android.core.models.TwitterCollection;
import com.twitter.sdk.android.core.models.TwitterCollection.TimelineItem;
import com.twitter.sdk.android.core.models.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import retrofit2.Call;

public class CollectionTimeline extends BaseTimeline implements Timeline<Tweet> {
    static final String COLLECTION_PREFIX = "custom-";
    private static final String SCRIBE_SECTION = "collection";
    final String collectionIdentifier;
    final Integer maxItemsPerRequest;

    public static class Builder {
        private Long collectionId;
        private Integer maxItemsPerRequest = Integer.valueOf(30);

        @Deprecated
        public Builder(TweetUi tweetUi) {
        }

        public Builder id(Long collectionId) {
            this.collectionId = collectionId;
            return this;
        }

        public Builder maxItemsPerRequest(Integer maxItemsPerRequest) {
            this.maxItemsPerRequest = maxItemsPerRequest;
            return this;
        }

        public CollectionTimeline build() {
            if (this.collectionId != null) {
                return new CollectionTimeline(this.collectionId, this.maxItemsPerRequest);
            }
            throw new IllegalStateException("collection id must not be null");
        }
    }

    class CollectionCallback extends Callback<TwitterCollection> {
        final Callback<TimelineResult<Tweet>> cb;

        CollectionCallback(Callback<TimelineResult<Tweet>> cb) {
            this.cb = cb;
        }

        public void success(Result<TwitterCollection> result) {
            TimelineResult<Tweet> timelineResult;
            TimelineCursor timelineCursor = CollectionTimeline.getTimelineCursor((TwitterCollection) result.data);
            List<Tweet> tweets = CollectionTimeline.getOrderedTweets((TwitterCollection) result.data);
            if (timelineCursor != null) {
                timelineResult = new TimelineResult(timelineCursor, tweets);
            } else {
                timelineResult = new TimelineResult(null, Collections.emptyList());
            }
            if (this.cb != null) {
                this.cb.success(new Result(timelineResult, result.response));
            }
        }

        public void failure(TwitterException exception) {
            if (this.cb != null) {
                this.cb.failure(exception);
            }
        }
    }

    CollectionTimeline(Long collectionId, Integer maxItemsPerRequest) {
        if (collectionId == null) {
            this.collectionIdentifier = null;
        } else {
            this.collectionIdentifier = COLLECTION_PREFIX + Long.toString(collectionId.longValue());
        }
        this.maxItemsPerRequest = maxItemsPerRequest;
    }

    public void next(Long minPosition, Callback<TimelineResult<Tweet>> cb) {
        createCollectionRequest(minPosition, null).enqueue(new CollectionCallback(cb));
    }

    public void previous(Long maxPosition, Callback<TimelineResult<Tweet>> cb) {
        createCollectionRequest(null, maxPosition).enqueue(new CollectionCallback(cb));
    }

    String getTimelineType() {
        return SCRIBE_SECTION;
    }

    Call<TwitterCollection> createCollectionRequest(Long minPosition, Long maxPosition) {
        return TwitterCore.getInstance().getApiClient().getCollectionService().collection(this.collectionIdentifier, this.maxItemsPerRequest, maxPosition, minPosition);
    }

    static List<Tweet> getOrderedTweets(TwitterCollection collection) {
        if (collection == null || collection.contents == null || collection.contents.tweetMap == null || collection.contents.userMap == null || collection.metadata == null || collection.metadata.timelineItems == null || collection.metadata.position == null) {
            return Collections.emptyList();
        }
        List<Tweet> tweets = new ArrayList();
        for (TimelineItem item : collection.metadata.timelineItems) {
            tweets.add(mapTweetToUsers((Tweet) collection.contents.tweetMap.get(item.tweetItem.id), collection.contents.userMap));
        }
        return tweets;
    }

    static Tweet mapTweetToUsers(Tweet trimmedTweet, Map<Long, User> userMap) {
        TweetBuilder builder = new TweetBuilder().copy(trimmedTweet).setUser((User) userMap.get(Long.valueOf(trimmedTweet.user.id)));
        if (trimmedTweet.quotedStatus != null) {
            builder.setQuotedStatus(mapTweetToUsers(trimmedTweet.quotedStatus, userMap));
        }
        return builder.build();
    }

    static TimelineCursor getTimelineCursor(TwitterCollection twitterCollection) {
        if (twitterCollection == null || twitterCollection.metadata == null || twitterCollection.metadata.position == null) {
            return null;
        }
        return new TimelineCursor(twitterCollection.metadata.position.minPosition, twitterCollection.metadata.position.maxPosition);
    }
}
