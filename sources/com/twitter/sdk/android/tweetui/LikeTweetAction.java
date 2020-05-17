package com.twitter.sdk.android.tweetui;

import android.view.View;
import android.view.View.OnClickListener;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiException;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.TwitterApiConstants.Errors;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.TweetBuilder;

class LikeTweetAction extends BaseTweetAction implements OnClickListener {
    final Tweet tweet;
    final TweetRepository tweetRepository;
    final TweetScribeClient tweetScribeClient;
    final TweetUi tweetUi;

    static class LikeCallback extends Callback<Tweet> {
        ToggleImageButton button;
        Callback<Tweet> cb;
        Tweet tweet;

        LikeCallback(ToggleImageButton button, Tweet tweet, Callback<Tweet> cb) {
            this.button = button;
            this.tweet = tweet;
            this.cb = cb;
        }

        public void success(Result<Tweet> result) {
            this.cb.success(result);
        }

        public void failure(TwitterException exception) {
            if (exception instanceof TwitterApiException) {
                switch (((TwitterApiException) exception).getErrorCode()) {
                    case Errors.ALREADY_FAVORITED /*139*/:
                        this.cb.success(new Result(new TweetBuilder().copy(this.tweet).setFavorited(true).build(), null));
                        return;
                    case Errors.ALREADY_UNFAVORITED /*144*/:
                        this.cb.success(new Result(new TweetBuilder().copy(this.tweet).setFavorited(false).build(), null));
                        return;
                    default:
                        this.button.setToggledOn(this.tweet.favorited);
                        this.cb.failure(exception);
                        return;
                }
            }
            this.button.setToggledOn(this.tweet.favorited);
            this.cb.failure(exception);
        }
    }

    LikeTweetAction(Tweet tweet, TweetUi tweetUi, Callback<Tweet> cb) {
        this(tweet, tweetUi, cb, new TweetScribeClientImpl(tweetUi));
    }

    LikeTweetAction(Tweet tweet, TweetUi tweetUi, Callback<Tweet> cb, TweetScribeClient tweetScribeClient) {
        super(cb);
        this.tweet = tweet;
        this.tweetUi = tweetUi;
        this.tweetScribeClient = tweetScribeClient;
        this.tweetRepository = tweetUi.getTweetRepository();
    }

    public void onClick(View view) {
        if (view instanceof ToggleImageButton) {
            ToggleImageButton toggleImageButton = (ToggleImageButton) view;
            if (this.tweet.favorited) {
                scribeUnFavoriteAction();
                this.tweetRepository.unfavorite(this.tweet.id, new LikeCallback(toggleImageButton, this.tweet, getActionCallback()));
                return;
            }
            scribeFavoriteAction();
            this.tweetRepository.favorite(this.tweet.id, new LikeCallback(toggleImageButton, this.tweet, getActionCallback()));
        }
    }

    void scribeFavoriteAction() {
        this.tweetScribeClient.favorite(this.tweet);
    }

    void scribeUnFavoriteAction() {
        this.tweetScribeClient.unfavorite(this.tweet);
    }
}
