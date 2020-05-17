package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.util.AttributeSet;
import com.twitter.sdk.android.core.models.Tweet;

public class TweetView extends BaseTweetView {
    private static final double DEFAULT_ASPECT_RATIO_MEDIA_CONTAINER = 1.5d;
    private static final double SQUARE_ASPECT_RATIO = 1.0d;
    private static final String VIEW_TYPE_NAME = "default";

    public TweetView(Context context, Tweet tweet) {
        super(context, tweet);
    }

    public TweetView(Context context, Tweet tweet, int styleResId) {
        super(context, tweet, styleResId);
    }

    TweetView(Context context, Tweet tweet, int styleResId, DependencyProvider dependencyProvider) {
        super(context, tweet, styleResId, dependencyProvider);
    }

    public TweetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TweetView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected int getLayout() {
        return C1043R.layout.tw__tweet;
    }

    void render() {
        super.render();
        setVerifiedCheck(this.tweet);
    }

    protected double getAspectRatioForPhotoEntity(int photoCount) {
        if (photoCount == 4) {
            return SQUARE_ASPECT_RATIO;
        }
        return DEFAULT_ASPECT_RATIO_MEDIA_CONTAINER;
    }

    private void setVerifiedCheck(Tweet tweet) {
        if (tweet == null || tweet.user == null || !tweet.user.verified) {
            this.fullNameView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else {
            this.fullNameView.setCompoundDrawablesWithIntrinsicBounds(0, 0, C1043R.drawable.tw__ic_tweet_verified, 0);
        }
    }

    String getViewTypeName() {
        return VIEW_TYPE_NAME;
    }
}
