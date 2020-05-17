package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;
import java.util.List;
import retrofit2.Call;

public class TwitterListTimeline extends BaseTimeline implements Timeline<Tweet> {
    private static final String SCRIBE_SECTION = "list";
    final Boolean includeRetweets;
    final Long listId;
    final Integer maxItemsPerRequest;
    final Long ownerId;
    final String ownerScreenName;
    final String slug;

    public static class Builder {
        private Boolean includeRetweets;
        private Long listId;
        private Integer maxItemsPerRequest = Integer.valueOf(30);
        private Long ownerId;
        private String ownerScreenName;
        private String slug;

        @Deprecated
        public Builder(TweetUi tweetUi) {
        }

        public Builder id(Long id) {
            this.listId = id;
            return this;
        }

        public Builder slugWithOwnerId(String slug, Long ownerId) {
            this.slug = slug;
            this.ownerId = ownerId;
            return this;
        }

        public Builder slugWithOwnerScreenName(String slug, String ownerScreenName) {
            this.slug = slug;
            this.ownerScreenName = ownerScreenName;
            return this;
        }

        public Builder maxItemsPerRequest(Integer maxItemsPerRequest) {
            this.maxItemsPerRequest = maxItemsPerRequest;
            return this;
        }

        public Builder includeRetweets(Boolean includeRetweets) {
            this.includeRetweets = includeRetweets;
            return this;
        }

        public TwitterListTimeline build() {
            int i;
            int i2 = 1;
            if (this.listId == null) {
                i = 1;
            } else {
                i = 0;
            }
            if (this.slug != null) {
                i2 = 0;
            }
            if ((i ^ i2) == 0) {
                throw new IllegalStateException("must specify either a list id or slug/owner pair");
            } else if (this.slug == null || this.ownerId != null || this.ownerScreenName != null) {
                return new TwitterListTimeline(this.listId, this.slug, this.ownerId, this.ownerScreenName, this.maxItemsPerRequest, this.includeRetweets);
            } else {
                throw new IllegalStateException("slug/owner pair must set owner via ownerId or ownerScreenName");
            }
        }
    }

    TwitterListTimeline(Long listId, String slug, Long ownerId, String ownerScreenName, Integer maxItemsPerRequest, Boolean includeRetweets) {
        this.listId = listId;
        this.slug = slug;
        this.ownerId = ownerId;
        this.ownerScreenName = ownerScreenName;
        this.maxItemsPerRequest = maxItemsPerRequest;
        this.includeRetweets = includeRetweets;
    }

    public void next(Long sinceId, Callback<TimelineResult<Tweet>> cb) {
        createListTimelineRequest(sinceId, null).enqueue(new TweetsCallback(cb));
    }

    public void previous(Long maxId, Callback<TimelineResult<Tweet>> cb) {
        createListTimelineRequest(null, BaseTimeline.decrementMaxId(maxId)).enqueue(new TweetsCallback(cb));
    }

    Call<List<Tweet>> createListTimelineRequest(Long sinceId, Long maxId) {
        return TwitterCore.getInstance().getApiClient().getListService().statuses(this.listId, this.slug, this.ownerScreenName, this.ownerId, sinceId, maxId, this.maxItemsPerRequest, Boolean.valueOf(true), this.includeRetweets);
    }

    String getTimelineType() {
        return SCRIBE_SECTION;
    }
}
