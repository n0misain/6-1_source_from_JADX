package com.twitter.sdk.android.tweetcomposer;

import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import com.twitter.Validator;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

class ComposerController {
    final Card card;
    final ComposerView composerView;
    final DependencyProvider dependencyProvider;
    final Finisher finisher;
    final TwitterSession session;

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerController$1 */
    class C10191 extends Callback<User> {
        C10191() {
        }

        public void success(Result<User> result) {
            ComposerController.this.composerView.setProfilePhotoView((User) result.data);
        }

        public void failure(TwitterException exception) {
            ComposerController.this.composerView.setProfilePhotoView(null);
        }
    }

    public interface ComposerCallbacks {
        void onCloseClick();

        void onTextChanged(String str);

        void onTweetPost(String str);
    }

    class ComposerCallbacksImpl implements ComposerCallbacks {
        ComposerCallbacksImpl() {
        }

        public void onTextChanged(String text) {
            int charCount = ComposerController.this.tweetTextLength(text);
            ComposerController.this.composerView.setCharCount(ComposerController.remainingCharCount(charCount));
            if (ComposerController.isTweetTextOverflow(charCount)) {
                ComposerController.this.composerView.setCharCountTextStyle(C1025R.style.tw__ComposerCharCountOverflow);
            } else {
                ComposerController.this.composerView.setCharCountTextStyle(C1025R.style.tw__ComposerCharCount);
            }
            ComposerController.this.composerView.postTweetEnabled(ComposerController.isPostEnabled(charCount));
        }

        public void onTweetPost(String text) {
            ComposerController.this.dependencyProvider.getScribeClient().click(ComposerController.this.card, "tweet");
            Intent intent = new Intent(ComposerController.this.composerView.getContext(), TweetUploadService.class);
            intent.putExtra("EXTRA_USER_TOKEN", (Parcelable) ComposerController.this.session.getAuthToken());
            intent.putExtra("EXTRA_TWEET_TEXT", text);
            intent.putExtra("EXTRA_TWEET_CARD", ComposerController.this.card);
            ComposerController.this.composerView.getContext().startService(intent);
            ComposerController.this.finisher.finish();
        }

        public void onCloseClick() {
            ComposerController.this.dependencyProvider.getScribeClient().click(ComposerController.this.card, "cancel");
            ComposerController.this.finisher.finish();
        }
    }

    static class DependencyProvider {
        final CardViewFactory cardViewFactory = new CardViewFactory();
        final Validator tweetValidator = new Validator();

        DependencyProvider() {
        }

        TwitterApiClient getApiClient(TwitterSession session) {
            return TwitterCore.getInstance().getApiClient(session);
        }

        CardViewFactory getCardViewFactory() {
            return this.cardViewFactory;
        }

        Validator getTweetValidator() {
            return this.tweetValidator;
        }

        ComposerScribeClient getScribeClient() {
            return new ComposerScribeClientImpl(TweetComposer.getInstance().getScribeClient());
        }
    }

    ComposerController(ComposerView composerView, TwitterSession session, Card card, String hashtags, Finisher finisher) {
        this(composerView, session, card, hashtags, finisher, new DependencyProvider());
    }

    ComposerController(ComposerView composerView, TwitterSession session, Card card, String hashtags, Finisher finisher, DependencyProvider dependencyProvider) {
        this.composerView = composerView;
        this.session = session;
        this.card = card;
        this.finisher = finisher;
        this.dependencyProvider = dependencyProvider;
        composerView.setCallbacks(new ComposerCallbacksImpl());
        composerView.setTweetText(hashtags);
        setProfilePhoto();
        setCardView(card);
        dependencyProvider.getScribeClient().impression(card);
    }

    void setProfilePhoto() {
        this.dependencyProvider.getApiClient(this.session).getAccountService().verifyCredentials(Boolean.valueOf(false), Boolean.valueOf(true)).enqueue(new C10191());
    }

    void setCardView(Card card) {
        if (card != null) {
            this.composerView.setCardView(this.dependencyProvider.getCardViewFactory().createCard(this.composerView.getContext(), card));
        }
    }

    int tweetTextLength(String text) {
        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        return this.dependencyProvider.getTweetValidator().getTweetLength(text);
    }

    static int remainingCharCount(int charCount) {
        return 140 - charCount;
    }

    static boolean isPostEnabled(int charCount) {
        return charCount > 0 && charCount <= 140;
    }

    static boolean isTweetTextOverflow(int charCount) {
        return charCount > 140;
    }
}
