package com.twitter.sdk.android.tweetui;

import android.net.Uri;
import android.text.TextUtils;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.models.Tweet;
import io.fabric.sdk.android.Fabric;
import java.util.List;
import java.util.Locale;

public final class TweetUtils {
    static final String LOAD_TWEET_DEBUG = "loadTweet failure for Tweet Id %d.";
    private static final String PERMALINK_FORMAT = "https://twitter.com/%s/status/%d?ref_src=twsrc%%5Etwitterkit";
    private static final String UNKNOWN_SCREEN_NAME = "twitter_unknown";

    private TweetUtils() {
    }

    public static void loadTweet(long tweetId, final Callback<Tweet> cb) {
        TweetUi.getInstance().getTweetRepository().loadTweet(tweetId, new LoggingCallback<Tweet>(Fabric.getLogger(), cb) {
            public void success(Result<Tweet> result) {
                if (cb != null) {
                    cb.success(result);
                }
            }
        });
    }

    public static void loadTweets(List<Long> tweetIds, final Callback<List<Tweet>> cb) {
        TweetUi.getInstance().getTweetRepository().loadTweets(tweetIds, new LoggingCallback<List<Tweet>>(Fabric.getLogger(), cb) {
            public void success(Result<List<Tweet>> result) {
                if (cb != null) {
                    cb.success(result);
                }
            }
        });
    }

    static boolean isTweetResolvable(Tweet tweet) {
        return (tweet == null || tweet.id <= 0 || tweet.user == null || TextUtils.isEmpty(tweet.user.screenName)) ? false : true;
    }

    static Tweet getDisplayTweet(Tweet tweet) {
        return (tweet == null || tweet.retweetedStatus == null) ? tweet : tweet.retweetedStatus;
    }

    static Uri getPermalink(String screenName, long tweetId) {
        if (tweetId <= 0) {
            return null;
        }
        String permalink;
        if (TextUtils.isEmpty(screenName)) {
            permalink = String.format(Locale.US, PERMALINK_FORMAT, new Object[]{UNKNOWN_SCREEN_NAME, Long.valueOf(tweetId)});
        } else {
            permalink = String.format(Locale.US, PERMALINK_FORMAT, new Object[]{screenName, Long.valueOf(tweetId)});
        }
        return Uri.parse(permalink);
    }
}
