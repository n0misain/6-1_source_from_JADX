package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.models.Tweet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FixedTweetTimeline extends BaseTimeline implements Timeline<Tweet> {
    private static final String SCRIBE_SECTION = "fixed";
    List<Tweet> tweets;

    public static class Builder {
        private List<Tweet> tweets;

        @Deprecated
        public Builder(TweetUi tweetUi) {
        }

        public Builder setTweets(List<Tweet> tweets) {
            this.tweets = tweets;
            return this;
        }

        public FixedTweetTimeline build() {
            return new FixedTweetTimeline(this.tweets);
        }
    }

    FixedTweetTimeline(List<Tweet> list) {
        List arrayList;
        if (list == null) {
            arrayList = new ArrayList();
        }
        this.tweets = arrayList;
    }

    public void next(Long minPosition, Callback<TimelineResult<Tweet>> cb) {
        cb.success(new Result(new TimelineResult(new TimelineCursor(this.tweets), this.tweets), null));
    }

    public void previous(Long maxPosition, Callback<TimelineResult<Tweet>> cb) {
        List<Tweet> empty = Collections.emptyList();
        cb.success(new Result(new TimelineResult(new TimelineCursor(empty), empty), null));
    }

    String getTimelineType() {
        return SCRIBE_SECTION;
    }
}
