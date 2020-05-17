package com.twitter.sdk.android.tweetcomposer;

import com.twitter.sdk.android.core.internal.scribe.EventNamespace.Builder;
import com.twitter.sdk.android.core.internal.scribe.ScribeItem;
import com.twitter.sdk.android.core.internal.scribe.ScribeItem.CardEvent;

final class ScribeConstants {
    static final Builder ComposerEventBuilder = new Builder().setClient("tfw").setPage("android").setSection(SCRIBE_SECTION);
    static final String SCRIBE_CANCEL_ELEMENT = "cancel";
    static final String SCRIBE_CLICK_ACTION = "click";
    static final String SCRIBE_COMPONENT = "";
    static final String SCRIBE_IMPRESSION_ACTION = "impression";
    static final String SCRIBE_IMPRESSION_ELEMENT = "";
    static final String SCRIBE_PAGE = "android";
    static final int SCRIBE_PROMO_APP_CARD_TYPE = 8;
    static final String SCRIBE_SECTION = "composer";
    static final String SCRIBE_TFW_CLIENT = "tfw";
    static final String SCRIBE_TWEET_ELEMENT = "tweet";

    private ScribeConstants() {
    }

    static ScribeItem newCardScribeItem(Card card) {
        return new ScribeItem.Builder().setItemType(0).setCardEvent(new CardEvent(8)).build();
    }
}
