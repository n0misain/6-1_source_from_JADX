package com.twitter.sdk.android.tweetcomposer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.twitter.Regex;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterSession;

public class ComposerActivity extends Activity {
    static final String EXTRA_CARD = "EXTRA_CARD";
    static final String EXTRA_HASHTAGS = "EXTRA_HASHTAGS";
    static final String EXTRA_THEME = "EXTRA_THEME";
    static final String EXTRA_USER_TOKEN = "EXTRA_USER_TOKEN";
    private static final int PLACEHOLDER_ID = -1;
    private static final String PLACEHOLDER_SCREEN_NAME = "";

    public static class Builder {
        private Card card;
        private final Context context;
        private String hashtags;
        private int themeResId = C1025R.style.ComposerLight;
        private TwitterAuthToken token;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null");
            }
            this.context = context;
        }

        public Builder session(TwitterSession session) {
            if (session == null) {
                throw new IllegalArgumentException("TwitterSession must not be null");
            }
            TwitterAuthToken token = (TwitterAuthToken) session.getAuthToken();
            if (token == null) {
                throw new IllegalArgumentException("TwitterSession token must not be null");
            }
            this.token = token;
            return this;
        }

        public Builder card(Card card) {
            this.card = card;
            return this;
        }

        public Builder hashtags(String... hashtags) {
            if (hashtags != null) {
                StringBuilder sb = new StringBuilder();
                for (String hashtag : hashtags) {
                    if (Regex.VALID_HASHTAG.matcher(hashtag).find()) {
                        sb.append(" ").append(hashtag);
                    }
                }
                this.hashtags = sb.length() == 0 ? null : sb.toString();
            }
            return this;
        }

        public Builder darkTheme() {
            this.themeResId = C1025R.style.ComposerDark;
            return this;
        }

        public Intent createIntent() {
            if (this.token == null) {
                throw new IllegalStateException("Must set a TwitterSession");
            }
            Intent intent = new Intent(this.context, ComposerActivity.class);
            intent.putExtra(ComposerActivity.EXTRA_USER_TOKEN, this.token);
            intent.putExtra(ComposerActivity.EXTRA_CARD, this.card);
            intent.putExtra(ComposerActivity.EXTRA_THEME, this.themeResId);
            intent.putExtra(ComposerActivity.EXTRA_HASHTAGS, this.hashtags);
            return intent;
        }
    }

    interface Finisher {
        void finish();
    }

    class FinisherImpl implements Finisher {
        FinisherImpl() {
        }

        public void finish() {
            ComposerActivity.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        TwitterSession session = new TwitterSession((TwitterAuthToken) intent.getParcelableExtra(EXTRA_USER_TOKEN), -1, "");
        Card card = (Card) intent.getSerializableExtra(EXTRA_CARD);
        String hashtags = intent.getStringExtra(EXTRA_HASHTAGS);
        setTheme(intent.getIntExtra(EXTRA_THEME, C1025R.style.ComposerLight));
        setContentView(C1025R.layout.tw__activity_composer);
        ComposerController composerController = new ComposerController((ComposerView) findViewById(C1025R.id.tw__composer_view), session, card, hashtags, new FinisherImpl());
    }
}
