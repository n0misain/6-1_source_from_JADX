package com.twitter.sdk.android.tweetcomposer;

interface ComposerScribeClient {
    void click(Card card, String str);

    void impression(Card card);
}
