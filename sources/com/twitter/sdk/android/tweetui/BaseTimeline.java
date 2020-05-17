package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import java.util.List;

abstract class BaseTimeline {

    static class TweetsCallback extends Callback<List<Tweet>> {
        final Callback<TimelineResult<Tweet>> cb;

        TweetsCallback(Callback<TimelineResult<Tweet>> cb) {
            this.cb = cb;
        }

        public void success(Result<List<Tweet>> result) {
            List<Tweet> tweets = result.data;
            TimelineResult<Tweet> timelineResult = new TimelineResult(new TimelineCursor(tweets), tweets);
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

    abstract String getTimelineType();

    BaseTimeline() {
    }

    static Long decrementMaxId(Long maxId) {
        return maxId == null ? null : Long.valueOf(maxId.longValue() - 1);
    }
}
