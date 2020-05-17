package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.scribe.ScribeItem;
import com.twitter.sdk.android.core.models.Identifiable;
import com.twitter.sdk.android.core.models.Tweet;
import java.util.ArrayList;
import java.util.List;

public class TweetTimelineListAdapter extends TimelineListAdapter<Tweet> {
    static final String DEFAULT_FILTERS_JSON_MSG = "{\"total_filters\":0}";
    static final String TOTAL_FILTERS_JSON_PROP = "total_filters";
    protected Callback<Tweet> actionCallback;
    final Gson gson;
    protected final int styleResId;
    protected TweetUi tweetUi;

    public static class Builder {
        private Callback<Tweet> actionCallback;
        private Context context;
        private int styleResId = C1043R.style.tw__TweetLightStyle;
        private Timeline<Tweet> timeline;
        private TimelineFilter timelineFilter;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTimeline(Timeline<Tweet> timeline) {
            this.timeline = timeline;
            return this;
        }

        public Builder setViewStyle(int styleResId) {
            this.styleResId = styleResId;
            return this;
        }

        public Builder setOnActionCallback(Callback<Tweet> actionCallback) {
            this.actionCallback = actionCallback;
            return this;
        }

        public Builder setTimelineFilter(TimelineFilter timelineFilter) {
            this.timelineFilter = timelineFilter;
            return this;
        }

        public TweetTimelineListAdapter build() {
            if (this.timelineFilter == null) {
                return new TweetTimelineListAdapter(this.context, this.timeline, this.styleResId, this.actionCallback);
            }
            return new TweetTimelineListAdapter(this.context, new FilterTimelineDelegate(this.timeline, this.timelineFilter), this.styleResId, this.actionCallback, TweetUi.getInstance());
        }
    }

    static class ReplaceTweetCallback extends Callback<Tweet> {
        Callback<Tweet> cb;
        TimelineDelegate<Tweet> delegate;

        ReplaceTweetCallback(TimelineDelegate<Tweet> delegate, Callback<Tweet> cb) {
            this.delegate = delegate;
            this.cb = cb;
        }

        public void success(Result<Tweet> result) {
            this.delegate.setItemById((Identifiable) result.data);
            if (this.cb != null) {
                this.cb.success(result);
            }
        }

        public void failure(TwitterException exception) {
            if (this.cb != null) {
                this.cb.failure(exception);
            }
        }
    }

    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public /* bridge */ /* synthetic */ long getItemId(int x0) {
        return super.getItemId(x0);
    }

    public /* bridge */ /* synthetic */ void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public /* bridge */ /* synthetic */ void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }

    public /* bridge */ /* synthetic */ void refresh(Callback x0) {
        super.refresh(x0);
    }

    public /* bridge */ /* synthetic */ void registerDataSetObserver(DataSetObserver x0) {
        super.registerDataSetObserver(x0);
    }

    public /* bridge */ /* synthetic */ void unregisterDataSetObserver(DataSetObserver x0) {
        super.unregisterDataSetObserver(x0);
    }

    public TweetTimelineListAdapter(Context context, Timeline<Tweet> timeline) {
        this(context, timeline, C1043R.style.tw__TweetLightStyle, null);
    }

    TweetTimelineListAdapter(Context context, Timeline<Tweet> timeline, int styleResId, Callback<Tweet> cb) {
        this(context, new TimelineDelegate(timeline), styleResId, cb, TweetUi.getInstance());
    }

    TweetTimelineListAdapter(Context context, TimelineDelegate<Tweet> delegate, int styleResId, Callback<Tweet> cb, TweetUi tweetUi) {
        super(context, (TimelineDelegate) delegate);
        this.gson = new Gson();
        this.styleResId = styleResId;
        this.actionCallback = new ReplaceTweetCallback(delegate, cb);
        this.tweetUi = tweetUi;
        scribeTimelineImpression();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        Tweet tweet = (Tweet) getItem(position);
        if (rowView == null) {
            View tv = new CompactTweetView(this.context, tweet, this.styleResId);
            tv.setOnActionCallback(this.actionCallback);
            return tv;
        }
        ((BaseTweetView) rowView).setTweet(tweet);
        return rowView;
    }

    private void scribeTimelineImpression() {
        String jsonMessage;
        if (this.delegate instanceof FilterTimelineDelegate) {
            jsonMessage = getJsonMessage(this.delegate.timelineFilter.totalFilters());
        } else {
            jsonMessage = DEFAULT_FILTERS_JSON_MSG;
        }
        ScribeItem scribeItem = ScribeItem.fromMessage(jsonMessage);
        List<ScribeItem> items = new ArrayList();
        items.add(scribeItem);
        String timelineType = getTimelineType(this.delegate.getTimeline());
        this.tweetUi.scribe(ScribeConstants.getSyndicatedSdkTimelineNamespace(timelineType));
        this.tweetUi.scribe(ScribeConstants.getTfwClientTimelineNamespace(timelineType), items);
    }

    private String getJsonMessage(int totalFilters) {
        JsonElement message = new JsonObject();
        message.addProperty(TOTAL_FILTERS_JSON_PROP, Integer.valueOf(totalFilters));
        return this.gson.toJson(message);
    }

    static String getTimelineType(Timeline timeline) {
        if (timeline instanceof BaseTimeline) {
            return ((BaseTimeline) timeline).getTimelineType();
        }
        return FacebookRequestErrorClassification.KEY_OTHER;
    }
}
