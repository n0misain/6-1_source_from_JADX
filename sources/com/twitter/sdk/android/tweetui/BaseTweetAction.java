package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;

class BaseTweetAction {
    protected Callback<Tweet> actionCallback;

    BaseTweetAction(Callback<Tweet> actionCallback) {
        this.actionCallback = actionCallback;
    }

    Callback<Tweet> getActionCallback() {
        return this.actionCallback;
    }
}
