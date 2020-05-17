package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;

public class SearchTimeline extends BaseTimeline implements Timeline<Tweet> {
    static final String FILTER_RETWEETS = " -filter:retweets";
    private static final SimpleDateFormat QUERY_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private static final String SCRIBE_SECTION = "search";
    final String languageCode;
    final Integer maxItemsPerRequest;
    final String query;
    final String resultType;
    final String untilDate;

    public static class Builder {
        private String lang;
        private Integer maxItemsPerRequest = Integer.valueOf(30);
        private String query;
        private String resultType = ResultType.FILTERED.type;
        private String untilDate;

        @Deprecated
        public Builder(TweetUi tweetUi) {
        }

        public Builder query(String query) {
            this.query = query;
            return this;
        }

        public Builder resultType(ResultType resultType) {
            this.resultType = resultType.type;
            return this;
        }

        public Builder languageCode(String languageCode) {
            this.lang = languageCode;
            return this;
        }

        public Builder maxItemsPerRequest(Integer maxItemsPerRequest) {
            this.maxItemsPerRequest = maxItemsPerRequest;
            return this;
        }

        public Builder untilDate(Date date) {
            this.untilDate = SearchTimeline.QUERY_DATE.format(date);
            return this;
        }

        public SearchTimeline build() {
            if (this.query != null) {
                return new SearchTimeline(this.query, this.resultType, this.lang, this.maxItemsPerRequest, this.untilDate);
            }
            throw new IllegalStateException("query must not be null");
        }
    }

    public enum ResultType {
        RECENT("recent"),
        POPULAR("popular"),
        MIXED("mixed"),
        FILTERED("filtered");
        
        final String type;

        private ResultType(String type) {
            this.type = type;
        }
    }

    class SearchCallback extends Callback<Search> {
        final Callback<TimelineResult<Tweet>> cb;

        SearchCallback(Callback<TimelineResult<Tweet>> cb) {
            this.cb = cb;
        }

        public void success(Result<Search> result) {
            List<Tweet> tweets = ((Search) result.data).tweets;
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

    SearchTimeline(String query, String resultType, String languageCode, Integer maxItemsPerRequest, String untilDate) {
        this.languageCode = languageCode;
        this.maxItemsPerRequest = maxItemsPerRequest;
        this.untilDate = untilDate;
        this.resultType = resultType;
        this.query = query == null ? null : query + FILTER_RETWEETS;
    }

    public void next(Long sinceId, Callback<TimelineResult<Tweet>> cb) {
        createSearchRequest(sinceId, null).enqueue(new SearchCallback(cb));
    }

    public void previous(Long maxId, Callback<TimelineResult<Tweet>> cb) {
        createSearchRequest(null, BaseTimeline.decrementMaxId(maxId)).enqueue(new SearchCallback(cb));
    }

    String getTimelineType() {
        return "search";
    }

    Call<Search> createSearchRequest(Long sinceId, Long maxId) {
        return TwitterCore.getInstance().getApiClient().getSearchService().tweets(this.query, null, this.languageCode, null, this.resultType, this.maxItemsPerRequest, this.untilDate, sinceId, maxId, Boolean.valueOf(true));
    }
}
