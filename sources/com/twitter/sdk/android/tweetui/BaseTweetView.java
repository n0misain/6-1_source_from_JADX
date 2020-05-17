package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.UserUtils;
import com.twitter.sdk.android.core.internal.UserUtils.AvatarSize;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.TweetBuilder;
import io.fabric.sdk.android.Fabric;
import java.util.Locale;

public abstract class BaseTweetView extends AbstractTweetView {
    ColorDrawable avatarMediaBg;
    ImageView avatarView;
    int birdLogoResId;
    View bottomSeparator;
    int containerBgColor;
    ViewGroup quoteTweetHolder;
    QuoteTweetView quoteTweetView;
    int retweetIconResId;
    TextView retweetedByView;
    TextView timestampView;
    TweetActionBarView tweetActionBarView;
    ImageView twitterLogoView;

    public /* bridge */ /* synthetic */ Tweet getTweet() {
        return super.getTweet();
    }

    public /* bridge */ /* synthetic */ long getTweetId() {
        return super.getTweetId();
    }

    public /* bridge */ /* synthetic */ void setTweet(Tweet x0) {
        super.setTweet(x0);
    }

    BaseTweetView(Context context, Tweet tweet) {
        this(context, tweet, DEFAULT_STYLE);
    }

    BaseTweetView(Context context, Tweet tweet, int styleResId) {
        this(context, tweet, styleResId, new DependencyProvider());
    }

    BaseTweetView(Context context, Tweet tweet, int styleResId, DependencyProvider dependencyProvider) {
        super(context, null, styleResId, dependencyProvider);
        initAttributes(styleResId);
        applyStyles();
        if (isTweetUiEnabled()) {
            initTweetActions();
            setTweet(tweet);
        }
    }

    public BaseTweetView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseTweetView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle, new DependencyProvider());
        initXmlAttributes(context, attrs);
        applyStyles();
    }

    private void initAttributes(int styleResId) {
        this.styleResId = styleResId;
        TypedArray a = getContext().getTheme().obtainStyledAttributes(styleResId, C1043R.styleable.tw__TweetView);
        try {
            setStyleAttributes(a);
        } finally {
            a.recycle();
        }
    }

    private void initXmlAttributes(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, C1043R.styleable.tw__TweetView, 0, 0);
            try {
                setXmlDataAttributes(a);
                setStyleAttributes(a);
            } finally {
                a.recycle();
            }
        }
    }

    private void setXmlDataAttributes(TypedArray a) {
        long tweetId = Utils.numberOrDefault(a.getString(C1043R.styleable.tw__TweetView_tw__tweet_id), -1).longValue();
        if (tweetId <= 0) {
            throw new IllegalArgumentException("Invalid tw__tweet_id");
        }
        setPermalinkUri(null, Long.valueOf(tweetId));
        this.tweet = new TweetBuilder().setId(tweetId).build();
    }

    private void setStyleAttributes(TypedArray a) {
        int i = -16777216;
        this.containerBgColor = a.getColor(C1043R.styleable.tw__TweetView_tw__container_bg_color, getResources().getColor(C1043R.color.tw__tweet_light_container_bg_color));
        this.primaryTextColor = a.getColor(C1043R.styleable.tw__TweetView_tw__primary_text_color, getResources().getColor(C1043R.color.tw__tweet_light_primary_text_color));
        this.actionColor = a.getColor(C1043R.styleable.tw__TweetView_tw__action_color, getResources().getColor(C1043R.color.tw__tweet_action_color));
        this.actionHighlightColor = a.getColor(C1043R.styleable.tw__TweetView_tw__action_highlight_color, getResources().getColor(C1043R.color.tw__tweet_action_light_highlight_color));
        this.tweetActionsEnabled = a.getBoolean(C1043R.styleable.tw__TweetView_tw__tweet_actions_enabled, false);
        boolean isLightBg = ColorUtils.isLightColor(this.containerBgColor);
        if (isLightBg) {
            this.photoErrorResId = C1043R.drawable.tw__ic_tweet_photo_error_light;
            this.birdLogoResId = C1043R.drawable.tw__ic_logo_blue;
            this.retweetIconResId = C1043R.drawable.tw__ic_retweet_light;
        } else {
            this.photoErrorResId = C1043R.drawable.tw__ic_tweet_photo_error_dark;
            this.birdLogoResId = C1043R.drawable.tw__ic_logo_white;
            this.retweetIconResId = C1043R.drawable.tw__ic_retweet_dark;
        }
        this.secondaryTextColor = ColorUtils.calculateOpacityTransform(isLightBg ? 0.4d : 0.35d, isLightBg ? -1 : -16777216, this.primaryTextColor);
        double d = isLightBg ? 0.08d : 0.12d;
        if (!isLightBg) {
            i = -1;
        }
        this.mediaBgColor = ColorUtils.calculateOpacityTransform(d, i, this.containerBgColor);
        this.avatarMediaBg = new ColorDrawable(this.mediaBgColor);
    }

    private void loadTweet() {
        final long tweetId = getTweetId();
        this.dependencyProvider.getTweetUi().getTweetRepository().loadTweet(getTweetId(), new Callback<Tweet>() {
            public void success(Result<Tweet> result) {
                BaseTweetView.this.setTweet((Tweet) result.data);
            }

            public void failure(TwitterException exception) {
                Fabric.getLogger().mo4333d("TweetUi", String.format(Locale.ENGLISH, "loadTweet failure for Tweet Id %d.", new Object[]{Long.valueOf(tweetId)}));
            }
        });
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isTweetUiEnabled()) {
            initTweetActions();
            loadTweet();
        }
    }

    private void initTweetActions() {
        setTweetActionsEnabled(this.tweetActionsEnabled);
        this.tweetActionBarView.setOnActionCallback(new ResetTweetCallback(this, this.dependencyProvider.getTweetUi().getTweetRepository(), null));
    }

    void findSubviews() {
        super.findSubviews();
        this.avatarView = (ImageView) findViewById(C1043R.id.tw__tweet_author_avatar);
        this.timestampView = (TextView) findViewById(C1043R.id.tw__tweet_timestamp);
        this.twitterLogoView = (ImageView) findViewById(C1043R.id.tw__twitter_logo);
        this.retweetedByView = (TextView) findViewById(C1043R.id.tw__tweet_retweeted_by);
        this.tweetActionBarView = (TweetActionBarView) findViewById(C1043R.id.tw__tweet_action_bar);
        this.quoteTweetHolder = (ViewGroup) findViewById(C1043R.id.quote_tweet_holder);
        this.bottomSeparator = findViewById(C1043R.id.bottom_separator);
    }

    public void setOnActionCallback(Callback<Tweet> actionCallback) {
        this.tweetActionBarView.setOnActionCallback(new ResetTweetCallback(this, this.dependencyProvider.getTweetUi().getTweetRepository(), actionCallback));
        this.tweetActionBarView.setTweet(this.tweet);
    }

    void render() {
        super.render();
        Tweet displayTweet = TweetUtils.getDisplayTweet(this.tweet);
        setProfilePhotoView(displayTweet);
        setTimestamp(displayTweet);
        setTweetActions(this.tweet);
        showRetweetedBy(this.tweet);
        setQuoteTweet(this.tweet);
    }

    void setQuoteTweet(Tweet tweet) {
        this.quoteTweetView = null;
        this.quoteTweetHolder.removeAllViews();
        if (tweet == null || tweet.quotedStatus == null) {
            this.quoteTweetHolder.setVisibility(8);
            return;
        }
        this.quoteTweetView = new QuoteTweetView(getContext());
        this.quoteTweetView.setStyle(this.primaryTextColor, this.secondaryTextColor, this.actionColor, this.actionHighlightColor, this.mediaBgColor, this.photoErrorResId);
        this.quoteTweetView.setTweet(tweet.quotedStatus);
        this.quoteTweetView.setTweetLinkClickListener(this.tweetLinkClickListener);
        this.quoteTweetView.setTweetMediaClickListener(this.tweetMediaClickListener);
        this.quoteTweetHolder.setVisibility(0);
        this.quoteTweetHolder.addView(this.quoteTweetView);
    }

    void showRetweetedBy(Tweet tweet) {
        if (tweet == null || tweet.retweetedStatus == null) {
            this.retweetedByView.setVisibility(8);
            return;
        }
        this.retweetedByView.setText(getResources().getString(C1043R.string.tw__retweeted_by_format, new Object[]{tweet.user.name}));
        this.retweetedByView.setVisibility(0);
    }

    protected void applyStyles() {
        setBackgroundColor(this.containerBgColor);
        this.fullNameView.setTextColor(this.primaryTextColor);
        this.screenNameView.setTextColor(this.secondaryTextColor);
        this.contentView.setTextColor(this.primaryTextColor);
        this.tweetMediaView.setMediaBgColor(this.mediaBgColor);
        this.tweetMediaView.setPhotoErrorResId(this.photoErrorResId);
        this.avatarView.setImageDrawable(this.avatarMediaBg);
        this.timestampView.setTextColor(this.secondaryTextColor);
        this.twitterLogoView.setImageResource(this.birdLogoResId);
        this.retweetedByView.setTextColor(this.secondaryTextColor);
    }

    private void setTimestamp(Tweet displayTweet) {
        String formattedTimestamp;
        if (displayTweet == null || displayTweet.createdAt == null || !TweetDateUtils.isValidTimestamp(displayTweet.createdAt)) {
            formattedTimestamp = "";
        } else {
            formattedTimestamp = TweetDateUtils.dotPrefix(TweetDateUtils.getRelativeTimeString(getResources(), System.currentTimeMillis(), Long.valueOf(TweetDateUtils.apiTimeToLong(displayTweet.createdAt)).longValue()));
        }
        this.timestampView.setText(formattedTimestamp);
    }

    void setProfilePhotoView(Tweet displayTweet) {
        Picasso imageLoader = this.dependencyProvider.getImageLoader();
        if (imageLoader != null) {
            String url;
            if (displayTweet == null || displayTweet.user == null) {
                url = null;
            } else {
                url = UserUtils.getProfileImageUrlHttps(displayTweet.user, AvatarSize.REASONABLY_SMALL);
            }
            imageLoader.load(url).placeholder(this.avatarMediaBg).into(this.avatarView);
        }
    }

    void setTweetActions(Tweet tweet) {
        this.tweetActionBarView.setTweet(tweet);
    }

    public void setTweetMediaClickListener(TweetMediaClickListener tweetMediaClickListener) {
        super.setTweetMediaClickListener(tweetMediaClickListener);
        if (this.quoteTweetView != null) {
            this.quoteTweetView.setTweetMediaClickListener(tweetMediaClickListener);
        }
    }

    public void setTweetLinkClickListener(TweetLinkClickListener tweetLinkClickListener) {
        super.setTweetLinkClickListener(tweetLinkClickListener);
        if (this.quoteTweetView != null) {
            this.quoteTweetView.setTweetLinkClickListener(tweetLinkClickListener);
        }
    }

    public void setTweetActionsEnabled(boolean enabled) {
        this.tweetActionsEnabled = enabled;
        if (this.tweetActionsEnabled) {
            this.tweetActionBarView.setVisibility(0);
            this.bottomSeparator.setVisibility(8);
            return;
        }
        this.tweetActionBarView.setVisibility(8);
        this.bottomSeparator.setVisibility(0);
    }
}
