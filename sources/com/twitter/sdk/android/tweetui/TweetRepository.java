package com.twitter.sdk.android.tweetui;

import android.os.Handler;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import io.fabric.sdk.android.Fabric;
import java.util.List;

class TweetRepository {
    private static final int DEFAULT_CACHE_SIZE = 20;
    final LruCache<Long, FormattedTweetText> formatCache;
    private final Handler mainHandler;
    final LruCache<Long, Tweet> tweetCache;
    private final TwitterCore twitterCore;
    private final SessionManager<TwitterSession> userSessionManagers;

    class MultiTweetsCallback extends Callback<List<Tweet>> {
        final Callback<List<Tweet>> cb;
        final List<Long> tweetIds;

        MultiTweetsCallback(List<Long> tweetIds, Callback<List<Tweet>> cb) {
            this.cb = cb;
            this.tweetIds = tweetIds;
        }

        public void success(Result<List<Tweet>> result) {
            if (this.cb != null) {
                this.cb.success(new Result(Utils.orderTweets(this.tweetIds, (List) result.data), result.response));
            }
        }

        public void failure(TwitterException exception) {
            this.cb.failure(exception);
        }
    }

    class SingleTweetCallback extends Callback<Tweet> {
        final Callback<Tweet> cb;

        SingleTweetCallback(Callback<Tweet> cb) {
            this.cb = cb;
        }

        public void success(Result<Tweet> result) {
            Tweet tweet = result.data;
            TweetRepository.this.updateCache(tweet);
            if (this.cb != null) {
                this.cb.success(new Result(tweet, result.response));
            }
        }

        public void failure(TwitterException exception) {
            this.cb.failure(exception);
        }
    }

    TweetRepository(Handler mainHandler, SessionManager<TwitterSession> userSessionManagers) {
        this(mainHandler, userSessionManagers, TwitterCore.getInstance());
    }

    TweetRepository(Handler mainHandler, SessionManager<TwitterSession> userSessionManagers, TwitterCore twitterCore) {
        this.twitterCore = twitterCore;
        this.mainHandler = mainHandler;
        this.userSessionManagers = userSessionManagers;
        this.tweetCache = new LruCache(20);
        this.formatCache = new LruCache(20);
    }

    FormattedTweetText formatTweetText(Tweet tweet) {
        if (tweet == null) {
            return null;
        }
        FormattedTweetText cached = (FormattedTweetText) this.formatCache.get(Long.valueOf(tweet.id));
        if (cached != null) {
            return cached;
        }
        FormattedTweetText formattedTweetText = TweetTextUtils.formatTweetText(tweet);
        if (!(formattedTweetText == null || TextUtils.isEmpty(formattedTweetText.text))) {
            this.formatCache.put(Long.valueOf(tweet.id), formattedTweetText);
        }
        return formattedTweetText;
    }

    void updateCache(Tweet tweet) {
        this.tweetCache.put(Long.valueOf(tweet.id), tweet);
    }

    private void deliverTweet(final Tweet tweet, final Callback<Tweet> cb) {
        if (cb != null) {
            this.mainHandler.post(new Runnable() {
                public void run() {
                    cb.success(new Result(tweet, null));
                }
            });
        }
    }

    void favorite(long tweetId, Callback<Tweet> cb) {
        final long j = tweetId;
        final Callback<Tweet> callback = cb;
        getUserSession(new LoggingCallback<TwitterSession>(cb, Fabric.getLogger()) {
            public void success(Result<TwitterSession> result) {
                TweetRepository.this.twitterCore.getApiClient((TwitterSession) result.data).getFavoriteService().create(Long.valueOf(j), Boolean.valueOf(false)).enqueue(callback);
            }
        });
    }

    void unfavorite(long tweetId, Callback<Tweet> cb) {
        final long j = tweetId;
        final Callback<Tweet> callback = cb;
        getUserSession(new LoggingCallback<TwitterSession>(cb, Fabric.getLogger()) {
            public void success(Result<TwitterSession> result) {
                TweetRepository.this.twitterCore.getApiClient((TwitterSession) result.data).getFavoriteService().destroy(Long.valueOf(j), Boolean.valueOf(false)).enqueue(callback);
            }
        });
    }

    void retweet(long tweetId, Callback<Tweet> cb) {
        final long j = tweetId;
        final Callback<Tweet> callback = cb;
        getUserSession(new LoggingCallback<TwitterSession>(cb, Fabric.getLogger()) {
            public void success(Result<TwitterSession> result) {
                TweetRepository.this.twitterCore.getApiClient((TwitterSession) result.data).getStatusesService().retweet(Long.valueOf(j), Boolean.valueOf(false)).enqueue(callback);
            }
        });
    }

    void unretweet(long tweetId, Callback<Tweet> cb) {
        final long j = tweetId;
        final Callback<Tweet> callback = cb;
        getUserSession(new LoggingCallback<TwitterSession>(cb, Fabric.getLogger()) {
            public void success(Result<TwitterSession> result) {
                TweetRepository.this.twitterCore.getApiClient((TwitterSession) result.data).getStatusesService().unretweet(Long.valueOf(j), Boolean.valueOf(false)).enqueue(callback);
            }
        });
    }

    void getUserSession(Callback<TwitterSession> cb) {
        TwitterSession session = (TwitterSession) this.userSessionManagers.getActiveSession();
        if (session == null) {
            cb.failure(new TwitterAuthException("User authorization required"));
        } else {
            cb.success(new Result(session, null));
        }
    }

    void loadTweet(long tweetId, Callback<Tweet> cb) {
        Tweet cachedTweet = (Tweet) this.tweetCache.get(Long.valueOf(tweetId));
        if (cachedTweet != null) {
            deliverTweet(cachedTweet, cb);
        } else {
            this.twitterCore.getApiClient().getStatusesService().show(Long.valueOf(tweetId), null, null, null).enqueue(new SingleTweetCallback(cb));
        }
    }

    void loadTweets(List<Long> tweetIds, Callback<List<Tweet>> cb) {
        this.twitterCore.getApiClient().getStatusesService().lookup(TextUtils.join(",", tweetIds), null, null, null).enqueue(new MultiTweetsCallback(tweetIds, cb));
    }
}
