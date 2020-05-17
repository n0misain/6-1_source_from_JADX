package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.internal.scribe.EventNamespace;
import com.twitter.sdk.android.core.internal.scribe.EventNamespace.Builder;
import com.twitter.sdk.android.core.internal.scribe.ScribeItem;
import com.twitter.sdk.android.core.internal.scribe.SyndicationClientEvent;
import com.twitter.sdk.android.core.models.Tweet;
import java.util.ArrayList;
import java.util.List;

class TweetScribeClientImpl implements TweetScribeClient {
    static final String SCRIBE_ACTIONS_ELEMENT = "actions";
    static final String SCRIBE_CLICK_ACTION = "click";
    static final String SCRIBE_FAVORITE_ACTION = "favorite";
    static final String SCRIBE_IMPRESSION_ACTION = "impression";
    static final String SCRIBE_SHARE_ACTION = "share";
    static final String SCRIBE_UNFAVORITE_ACTION = "unfavorite";
    static final String SYNDICATED_SDK_IMPRESSION_COMPONENT = "";
    static final String SYNDICATED_SDK_IMPRESSION_ELEMENT = "";
    static final String SYNDICATED_SDK_IMPRESSION_PAGE = "tweet";
    static final String TFW_CLIENT_EVENT_ELEMENT = "";
    static final String TFW_CLIENT_EVENT_PAGE = "android";
    static final String TFW_CLIENT_EVENT_SECTION = "tweet";
    final TweetUi tweetUi;

    TweetScribeClientImpl(TweetUi tweetUi) {
        this.tweetUi = tweetUi;
    }

    public void impression(Tweet tweet, String viewName, boolean actionEnabled) {
        List<ScribeItem> items = new ArrayList();
        items.add(ScribeItem.fromTweet(tweet));
        this.tweetUi.scribe(getTfwImpressionNamespace(viewName, actionEnabled), items);
        this.tweetUi.scribe(getSyndicatedImpressionNamespace(viewName), items);
    }

    public void share(Tweet tweet) {
        List<ScribeItem> items = new ArrayList();
        items.add(ScribeItem.fromTweet(tweet));
        this.tweetUi.scribe(getTfwShareNamespace(), items);
    }

    public void favorite(Tweet tweet) {
        List<ScribeItem> items = new ArrayList();
        items.add(ScribeItem.fromTweet(tweet));
        this.tweetUi.scribe(getTfwFavoriteNamespace(), items);
    }

    public void unfavorite(Tweet tweet) {
        List<ScribeItem> items = new ArrayList();
        items.add(ScribeItem.fromTweet(tweet));
        this.tweetUi.scribe(getTfwUnfavoriteNamespace(), items);
    }

    public void click(Tweet tweet, String viewName) {
        List<ScribeItem> items = new ArrayList();
        items.add(ScribeItem.fromTweet(tweet));
        this.tweetUi.scribe(getTfwClickNamespace(viewName), items);
    }

    static EventNamespace getTfwImpressionNamespace(String viewName, boolean actionEnabled) {
        return new Builder().setClient(SyndicationClientEvent.CLIENT_NAME).setPage("android").setSection("tweet").setComponent(viewName).setElement(actionEnabled ? SCRIBE_ACTIONS_ELEMENT : "").setAction(SCRIBE_IMPRESSION_ACTION).builder();
    }

    static EventNamespace getTfwUnfavoriteNamespace() {
        return new Builder().setClient(SyndicationClientEvent.CLIENT_NAME).setPage("android").setSection("tweet").setElement(SCRIBE_ACTIONS_ELEMENT).setAction(SCRIBE_UNFAVORITE_ACTION).builder();
    }

    static EventNamespace getTfwFavoriteNamespace() {
        return new Builder().setClient(SyndicationClientEvent.CLIENT_NAME).setPage("android").setSection("tweet").setElement(SCRIBE_ACTIONS_ELEMENT).setAction(SCRIBE_FAVORITE_ACTION).builder();
    }

    static EventNamespace getTfwShareNamespace() {
        return new Builder().setClient(SyndicationClientEvent.CLIENT_NAME).setPage("android").setSection("tweet").setElement(SCRIBE_ACTIONS_ELEMENT).setAction("share").builder();
    }

    static EventNamespace getTfwClickNamespace(String viewName) {
        return new Builder().setClient(SyndicationClientEvent.CLIENT_NAME).setPage("android").setSection("tweet").setComponent(viewName).setElement("").setAction(SCRIBE_CLICK_ACTION).builder();
    }

    static EventNamespace getSyndicatedImpressionNamespace(String viewName) {
        return new Builder().setClient("android").setPage("tweet").setSection(viewName).setComponent("").setElement("").setAction(SCRIBE_IMPRESSION_ACTION).builder();
    }
}
