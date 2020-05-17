package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.internal.scribe.EventNamespace;
import com.twitter.sdk.android.core.internal.scribe.EventNamespace.Builder;
import com.twitter.sdk.android.core.internal.scribe.ScribeItem;
import com.twitter.sdk.android.core.internal.scribe.SyndicationClientEvent;
import java.util.ArrayList;
import java.util.List;

public class GalleryScribeClientImpl implements GalleryScribeClient {
    static final String SCRIBE_DISMISS_ACTION = "dismiss";
    static final String SCRIBE_IMPRESSION_ACTION = "impression";
    static final String SCRIBE_NAVIGATE_ACTION = "navigate";
    static final String SCRIBE_SHOW_ACTION = "show";
    static final String TFW_CLIENT_EVENT_PAGE = "android";
    static final String TFW_CLIENT_EVENT_SECTION = "gallery";
    final TweetUi tweetUi;

    public GalleryScribeClientImpl(TweetUi tweetUi) {
        this.tweetUi = tweetUi;
    }

    public void show() {
        this.tweetUi.scribe(getTfwShowNamespace());
    }

    public void impression(ScribeItem item) {
        List<ScribeItem> items = new ArrayList();
        items.add(item);
        this.tweetUi.scribe(getTfwImpressionNamespace(), items);
    }

    public void navigate() {
        this.tweetUi.scribe(getTfwNavigateNamespace());
    }

    public void dismiss() {
        this.tweetUi.scribe(getTfwDimissNamespace());
    }

    static EventNamespace getTfwImpressionNamespace() {
        return new Builder().setClient(SyndicationClientEvent.CLIENT_NAME).setPage("android").setSection(TFW_CLIENT_EVENT_SECTION).setAction(SCRIBE_IMPRESSION_ACTION).builder();
    }

    static EventNamespace getTfwShowNamespace() {
        return new Builder().setClient(SyndicationClientEvent.CLIENT_NAME).setPage("android").setSection(TFW_CLIENT_EVENT_SECTION).setAction(SCRIBE_SHOW_ACTION).builder();
    }

    static EventNamespace getTfwNavigateNamespace() {
        return new Builder().setClient(SyndicationClientEvent.CLIENT_NAME).setPage("android").setSection(TFW_CLIENT_EVENT_SECTION).setAction(SCRIBE_NAVIGATE_ACTION).builder();
    }

    static EventNamespace getTfwDimissNamespace() {
        return new Builder().setClient(SyndicationClientEvent.CLIENT_NAME).setPage("android").setSection(TFW_CLIENT_EVENT_SECTION).setAction(SCRIBE_DISMISS_ACTION).builder();
    }
}
