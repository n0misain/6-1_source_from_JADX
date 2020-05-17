package com.twitter.sdk.android.tweetcomposer;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Media;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetcomposer.internal.CardCreate;
import io.fabric.sdk.android.Fabric;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TweetUploadService extends IntentService {
    public static final String EXTRA_RETRY_INTENT = "EXTRA_RETRY_INTENT";
    static final String EXTRA_TWEET_CARD = "EXTRA_TWEET_CARD";
    public static final String EXTRA_TWEET_ID = "EXTRA_TWEET_ID";
    static final String EXTRA_TWEET_TEXT = "EXTRA_TWEET_TEXT";
    static final String EXTRA_USER_TOKEN = "EXTRA_USER_TOKEN";
    private static final int PLACEHOLDER_ID = -1;
    private static final String PLACEHOLDER_SCREEN_NAME = "";
    static final String TAG = "TweetUploadService";
    public static final String UPLOAD_FAILURE = "com.twitter.sdk.android.tweetcomposer.UPLOAD_FAILURE";
    public static final String UPLOAD_SUCCESS = "com.twitter.sdk.android.tweetcomposer.UPLOAD_SUCCESS";
    DependencyProvider dependencyProvider;
    Intent intent;
    Card tweetCard;
    String tweetText;
    TwitterSession twitterSession;

    /* renamed from: com.twitter.sdk.android.tweetcomposer.TweetUploadService$1 */
    class C10261 extends Callback<Tweet> {
        C10261() {
        }

        public void success(Result<Tweet> result) {
            TweetUploadService.this.sendSuccessBroadcast(((Tweet) result.data).getId());
            TweetUploadService.this.stopSelf();
        }

        public void failure(TwitterException exception) {
            TweetUploadService.this.fail(exception);
        }
    }

    static class DependencyProvider {
        DependencyProvider() {
        }

        ComposerApiClient getComposerApiClient(TwitterSession session) {
            return TweetComposer.getInstance().getApiClient(session);
        }

        String getAdvertisingId() {
            return TweetComposer.getInstance().getAdvertisingId();
        }
    }

    public TweetUploadService() {
        this(new DependencyProvider());
    }

    TweetUploadService(DependencyProvider dependencyProvider) {
        super(TAG);
        this.dependencyProvider = dependencyProvider;
    }

    protected void onHandleIntent(Intent intent) {
        TwitterAuthToken token = (TwitterAuthToken) intent.getParcelableExtra(EXTRA_USER_TOKEN);
        this.intent = intent;
        this.twitterSession = new TwitterSession(token, -1, "");
        this.tweetText = intent.getStringExtra(EXTRA_TWEET_TEXT);
        this.tweetCard = (Card) intent.getSerializableExtra(EXTRA_TWEET_CARD);
        if (Card.isAppCard(this.tweetCard)) {
            uploadAppCardTweet(this.twitterSession, this.tweetText, this.tweetCard);
        } else {
            uploadTweet(this.twitterSession, this.tweetText);
        }
    }

    void uploadTweet(TwitterSession session, String text) {
        this.dependencyProvider.getComposerApiClient(session).getComposerStatusesService().update(text, null).enqueue(new C10261());
    }

    void uploadAppCardTweet(TwitterSession session, final String text, final Card card) {
        final ComposerApiClient client = this.dependencyProvider.getComposerApiClient(session);
        String path = FileUtils.getPath(this, Uri.parse(card.imageUri));
        if (path == null) {
            fail(new TwitterException("Uri file path resolved to null"));
            return;
        }
        File file = new File(path);
        client.getMediaService().upload(RequestBody.create(MediaType.parse(FileUtils.getMimeType(file)), file), null, null).enqueue(new Callback<Media>() {

            /* renamed from: com.twitter.sdk.android.tweetcomposer.TweetUploadService$2$1 */
            class C10281 extends Callback<CardCreate> {

                /* renamed from: com.twitter.sdk.android.tweetcomposer.TweetUploadService$2$1$1 */
                class C10271 extends Callback<Tweet> {
                    C10271() {
                    }

                    public void success(Result<Tweet> result) {
                        TweetUploadService.this.sendSuccessBroadcast(((Tweet) result.data).getId());
                        TweetUploadService.this.stopSelf();
                    }

                    public void failure(TwitterException exception) {
                        TweetUploadService.this.fail(exception);
                    }
                }

                C10281() {
                }

                public void success(Result<CardCreate> result) {
                    client.getComposerStatusesService().update(text, ((CardCreate) result.data).cardUri).enqueue(new C10271());
                }

                public void failure(TwitterException exception) {
                    TweetUploadService.this.fail(exception);
                }
            }

            public void success(Result<Media> result) {
                client.getCardService().create(CardDataFactory.createAppCardData(card, Long.valueOf(((Media) result.data).mediaId), TweetUploadService.this.dependencyProvider.getAdvertisingId())).enqueue(new C10281());
            }

            public void failure(TwitterException exception) {
                TweetUploadService.this.fail(exception);
            }
        });
    }

    void fail(TwitterException e) {
        sendFailureBroadcast(this.intent);
        Fabric.getLogger().mo4336e(TAG, "Post Tweet failed", e);
        stopSelf();
    }

    void sendSuccessBroadcast(long tweetId) {
        Intent intent = new Intent(UPLOAD_SUCCESS);
        intent.putExtra(EXTRA_TWEET_ID, tweetId);
        intent.setPackage(getApplicationContext().getPackageName());
        sendBroadcast(intent);
    }

    void sendFailureBroadcast(Intent original) {
        Intent intent = new Intent(UPLOAD_FAILURE);
        intent.putExtra(EXTRA_RETRY_INTENT, original);
        intent.setPackage(getApplicationContext().getPackageName());
        sendBroadcast(intent);
    }
}
