package com.twitter.sdk.android.tweetui;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.scribe.ScribeItem;
import com.twitter.sdk.android.core.models.Tweet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

class FilterTimelineDelegate extends TimelineDelegate<Tweet> {
    static final String TOTAL_APPLIED_FILTERS_JSON_PROP = "total_filters";
    static final String TWEETS_COUNT_JSON_PROP = "tweet_count";
    static final String TWEETS_FILTERED_JSON_PROP = "tweets_filtered";
    final Gson gson = new Gson();
    final TimelineFilter timelineFilter;
    final TweetUi tweetUi;

    class TimelineFilterCallback extends Callback<TimelineResult<Tweet>> {
        final DefaultCallback callback;
        final ExecutorService executorService = TwitterCore.getInstance().getFabric().getExecutorService();
        final Handler handler = new Handler(Looper.getMainLooper());
        final TimelineFilter timelineFilter;

        TimelineFilterCallback(DefaultCallback callback, TimelineFilter timelineFilter) {
            this.callback = callback;
            this.timelineFilter = timelineFilter;
        }

        public void success(final Result<TimelineResult<Tweet>> result) {
            this.executorService.execute(new Runnable() {
                public void run() {
                    List<Tweet> filteredTweets = TimelineFilterCallback.this.timelineFilter.filter(((TimelineResult) result.data).items);
                    final TimelineResult<Tweet> filteredTimelineResult = TimelineFilterCallback.this.buildTimelineResult(((TimelineResult) result.data).timelineCursor, filteredTweets);
                    TimelineFilterCallback.this.handler.post(new Runnable() {
                        public void run() {
                            TimelineFilterCallback.this.callback.success(new Result(filteredTimelineResult, result.response));
                        }
                    });
                    FilterTimelineDelegate.this.scribeFilteredTimeline(((TimelineResult) result.data).items, filteredTweets);
                }
            });
        }

        public void failure(TwitterException ex) {
            if (this.callback != null) {
                this.callback.failure(ex);
            }
        }

        TimelineResult<Tweet> buildTimelineResult(TimelineCursor timelineCursor, List<Tweet> filteredTweets) {
            return new TimelineResult(timelineCursor, filteredTweets);
        }
    }

    public FilterTimelineDelegate(Timeline<Tweet> timeline, TimelineFilter timelineFilter) {
        super(timeline);
        this.timelineFilter = timelineFilter;
        this.tweetUi = TweetUi.getInstance();
    }

    public void refresh(Callback<TimelineResult<Tweet>> developerCb) {
        this.timelineStateHolder.resetCursors();
        loadNext(this.timelineStateHolder.positionForNext(), new TimelineFilterCallback(new RefreshCallback(developerCb, this.timelineStateHolder), this.timelineFilter));
    }

    public void next(Callback<TimelineResult<Tweet>> developerCb) {
        loadNext(this.timelineStateHolder.positionForNext(), new TimelineFilterCallback(new NextCallback(developerCb, this.timelineStateHolder), this.timelineFilter));
    }

    public void previous() {
        loadPrevious(this.timelineStateHolder.positionForPrevious(), new TimelineFilterCallback(new PreviousCallback(this.timelineStateHolder), this.timelineFilter));
    }

    void scribeFilteredTimeline(List<Tweet> tweets, List<Tweet> filteredTweets) {
        int tweetCount = tweets.size();
        ScribeItem scribeItem = ScribeItem.fromMessage(getJsonMessage(tweetCount, tweetCount - filteredTweets.size(), this.timelineFilter.totalFilters()));
        List<ScribeItem> items = new ArrayList();
        items.add(scribeItem);
        this.tweetUi.scribe(ScribeConstants.getTfwClientFilterTimelineNamespace(TweetTimelineListAdapter.getTimelineType(this.timeline)), items);
    }

    private String getJsonMessage(int totalTweetsSize, int filteredTweetsSize, int totalFilters) {
        JsonElement message = new JsonObject();
        message.addProperty(TWEETS_COUNT_JSON_PROP, Integer.valueOf(totalTweetsSize));
        message.addProperty(TWEETS_FILTERED_JSON_PROP, Integer.valueOf(totalTweetsSize - filteredTweetsSize));
        message.addProperty(TOTAL_APPLIED_FILTERS_JSON_PROP, Integer.valueOf(totalFilters));
        return this.gson.toJson(message);
    }
}
