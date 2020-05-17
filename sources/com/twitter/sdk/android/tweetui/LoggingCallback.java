package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterException;
import io.fabric.sdk.android.Logger;

abstract class LoggingCallback<T> extends Callback<T> {
    private final Callback cb;
    private final Logger logger;

    LoggingCallback(Callback cb, Logger logger) {
        this.cb = cb;
        this.logger = logger;
    }

    public void failure(TwitterException exception) {
        this.logger.mo4336e("TweetUi", exception.getMessage(), exception);
        if (this.cb != null) {
            this.cb.failure(exception);
        }
    }
}
