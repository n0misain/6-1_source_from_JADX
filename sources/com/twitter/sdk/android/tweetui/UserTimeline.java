package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import java.util.List;
import retrofit2.Call;

public class UserTimeline extends BaseTimeline implements Timeline<Tweet> {
    private static final String SCRIBE_SECTION = "user";
    final Boolean includeReplies;
    final Boolean includeRetweets;
    final Integer maxItemsPerRequest;
    final String screenName;
    final Long userId;

    public static class Builder {
        private Boolean includeReplies;
        private Boolean includeRetweets;
        private Integer maxItemsPerRequest = Integer.valueOf(30);
        private String screenName;
        private Long userId;

        @Deprecated
        public Builder(TweetUi tweetUi) {
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder screenName(String screenName) {
            this.screenName = screenName;
            return this;
        }

        public Builder maxItemsPerRequest(Integer maxItemsPerRequest) {
            this.maxItemsPerRequest = maxItemsPerRequest;
            return this;
        }

        public Builder includeReplies(Boolean includeReplies) {
            this.includeReplies = includeReplies;
            return this;
        }

        public Builder includeRetweets(Boolean includeRetweets) {
            this.includeRetweets = includeRetweets;
            return this;
        }

        public UserTimeline build() {
            return new UserTimeline(this.userId, this.screenName, this.maxItemsPerRequest, this.includeReplies, this.includeRetweets);
        }
    }

    UserTimeline(Long userId, String screenName, Integer maxItemsPerRequest, Boolean includeReplies, Boolean includeRetweets) {
        this.userId = userId;
        this.screenName = screenName;
        this.maxItemsPerRequest = maxItemsPerRequest;
        this.includeReplies = Boolean.valueOf(includeReplies == null ? false : includeReplies.booleanValue());
        this.includeRetweets = includeRetweets;
    }

    public void next(Long sinceId, Callback<TimelineResult<Tweet>> cb) {
        createUserTimelineRequest(sinceId, null).enqueue(new TweetsCallback(cb));
    }

    public void previous(Long maxId, Callback<TimelineResult<Tweet>> cb) {
        createUserTimelineRequest(null, BaseTimeline.decrementMaxId(maxId)).enqueue(new TweetsCallback(cb));
    }

    String getTimelineType() {
        return SCRIBE_SECTION;
    }

    Call<List<Tweet>> createUserTimelineRequest(Long sinceId, Long maxId) {
        boolean z = false;
        StatusesService statusesService = TwitterCore.getInstance().getApiClient().getStatusesService();
        Long l = this.userId;
        String str = this.screenName;
        Integer num = this.maxItemsPerRequest;
        Boolean valueOf = Boolean.valueOf(false);
        if (!this.includeReplies.booleanValue()) {
            z = true;
        }
        return statusesService.userTimeline(l, str, num, sinceId, maxId, valueOf, Boolean.valueOf(z), null, this.includeRetweets);
    }
}
