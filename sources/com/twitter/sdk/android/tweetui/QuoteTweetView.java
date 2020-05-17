package com.twitter.sdk.android.tweetui;

import android.content.Context;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

public class QuoteTweetView extends AbstractTweetView {
    private static final double DEFAULT_ASPECT_RATIO_MEDIA_CONTAINER = 1.6d;
    private static final double MAX_LANDSCAPE_ASPECT_RATIO = 3.0d;
    private static final double MIN_LANDSCAPE_ASPECT_RATIO = 1.3333333333333333d;
    private static final double SQUARE_ASPECT_RATIO = 1.0d;
    private static final String VIEW_TYPE_NAME = "quote";

    public /* bridge */ /* synthetic */ Tweet getTweet() {
        return super.getTweet();
    }

    public /* bridge */ /* synthetic */ long getTweetId() {
        return super.getTweetId();
    }

    public /* bridge */ /* synthetic */ void setTweet(Tweet x0) {
        super.setTweet(x0);
    }

    public /* bridge */ /* synthetic */ void setTweetLinkClickListener(TweetLinkClickListener x0) {
        super.setTweetLinkClickListener(x0);
    }

    public /* bridge */ /* synthetic */ void setTweetMediaClickListener(TweetMediaClickListener x0) {
        super.setTweetMediaClickListener(x0);
    }

    public QuoteTweetView(Context context) {
        this(context, new DependencyProvider());
    }

    QuoteTweetView(Context context, DependencyProvider dependencyProvider) {
        super(context, null, 0, dependencyProvider);
    }

    public void setStyle(int primaryTextColor, int secondaryTextColor, int actionColor, int actionHighlightColor, int mediaBgColor, int photoErrorResId) {
        this.primaryTextColor = primaryTextColor;
        this.secondaryTextColor = secondaryTextColor;
        this.actionColor = actionColor;
        this.actionHighlightColor = actionHighlightColor;
        this.mediaBgColor = mediaBgColor;
        this.photoErrorResId = photoErrorResId;
        applyStyles();
    }

    protected int getLayout() {
        return C1043R.layout.tw__tweet_quote;
    }

    void render() {
        super.render();
        this.screenNameView.requestLayout();
    }

    protected void applyStyles() {
        int mediaViewRadius = getResources().getDimensionPixelSize(C1043R.dimen.tw__media_view_radius);
        this.tweetMediaView.setRoundedCornersRadii(0, 0, mediaViewRadius, mediaViewRadius);
        setBackgroundResource(C1043R.drawable.tw__quote_tweet_border);
        this.fullNameView.setTextColor(this.primaryTextColor);
        this.screenNameView.setTextColor(this.secondaryTextColor);
        this.contentView.setTextColor(this.primaryTextColor);
        this.tweetMediaView.setMediaBgColor(this.mediaBgColor);
        this.tweetMediaView.setPhotoErrorResId(this.photoErrorResId);
    }

    protected double getAspectRatio(MediaEntity photoEntity) {
        double ratio = super.getAspectRatio(photoEntity);
        return ratio <= SQUARE_ASPECT_RATIO ? SQUARE_ASPECT_RATIO : ratio > MAX_LANDSCAPE_ASPECT_RATIO ? MAX_LANDSCAPE_ASPECT_RATIO : ratio < MIN_LANDSCAPE_ASPECT_RATIO ? MIN_LANDSCAPE_ASPECT_RATIO : ratio;
    }

    protected double getAspectRatioForPhotoEntity(int photoCount) {
        return DEFAULT_ASPECT_RATIO_MEDIA_CONTAINER;
    }

    String getViewTypeName() {
        return "quote";
    }
}
