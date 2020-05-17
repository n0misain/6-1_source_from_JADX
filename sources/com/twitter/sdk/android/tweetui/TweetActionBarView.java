package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;

public class TweetActionBarView extends LinearLayout {
    Callback<Tweet> actionCallback;
    final DependencyProvider dependencyProvider;
    ToggleImageButton likeButton;
    ImageButton shareButton;

    static class DependencyProvider {
        DependencyProvider() {
        }

        TweetUi getTweetUi() {
            return TweetUi.getInstance();
        }
    }

    public TweetActionBarView(Context context) {
        this(context, null, new DependencyProvider());
    }

    public TweetActionBarView(Context context, AttributeSet attrs) {
        this(context, attrs, new DependencyProvider());
    }

    TweetActionBarView(Context context, AttributeSet attrs, DependencyProvider dependencyProvider) {
        super(context, attrs);
        this.dependencyProvider = dependencyProvider;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        findSubviews();
    }

    void setOnActionCallback(Callback<Tweet> actionCallback) {
        this.actionCallback = actionCallback;
    }

    void findSubviews() {
        this.likeButton = (ToggleImageButton) findViewById(C1043R.id.tw__tweet_like_button);
        this.shareButton = (ImageButton) findViewById(C1043R.id.tw__tweet_share_button);
    }

    void setTweet(Tweet tweet) {
        setLike(tweet);
        setShare(tweet);
    }

    void setLike(Tweet tweet) {
        TweetUi tweetUi = this.dependencyProvider.getTweetUi();
        if (tweet != null) {
            this.likeButton.setToggledOn(tweet.favorited);
            this.likeButton.setOnClickListener(new LikeTweetAction(tweet, tweetUi, this.actionCallback));
        }
    }

    void setShare(Tweet tweet) {
        TweetUi tweetUi = this.dependencyProvider.getTweetUi();
        if (tweet != null) {
            this.shareButton.setOnClickListener(new ShareTweetAction(tweet, tweetUi));
        }
    }
}
