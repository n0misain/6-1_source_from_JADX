package com.twitter.sdk.android.tweetui;

import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.scribe.DefaultScribeClient;
import com.twitter.sdk.android.core.internal.scribe.EventNamespace;
import com.twitter.sdk.android.core.internal.scribe.ScribeItem;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import java.util.List;

@DependsOn({TwitterCore.class})
public class TweetUi extends Kit<Boolean> {
    private static final String KIT_SCRIBE_NAME = "TweetUi";
    static final String LOGTAG = "TweetUi";
    static final String NOT_STARTED_ERROR = "Must start TweetUi Kit in Fabric.with().";
    GuestSessionProvider guestSessionProvider;
    private Picasso imageLoader;
    DefaultScribeClient scribeClient;
    SessionManager<TwitterSession> sessionManager;
    private TweetRepository tweetRepository;

    public static TweetUi getInstance() {
        checkInitialized();
        return (TweetUi) Fabric.getKit(TweetUi.class);
    }

    public String getIdentifier() {
        return "com.twitter.sdk.android:tweet-ui";
    }

    public String getVersion() {
        return "2.3.1.165";
    }

    protected boolean onPreExecute() {
        super.onPreExecute();
        TwitterCore twitterCore = TwitterCore.getInstance();
        this.sessionManager = twitterCore.getSessionManager();
        this.guestSessionProvider = twitterCore.getGuestSessionProvider();
        this.tweetRepository = new TweetRepository(getFabric().getMainHandler(), twitterCore.getSessionManager());
        return true;
    }

    protected Boolean doInBackground() {
        this.imageLoader = Picasso.with(getContext());
        setUpScribeClient();
        return Boolean.valueOf(true);
    }

    private static void checkInitialized() {
        if (Fabric.getKit(TweetUi.class) == null) {
            throw new IllegalStateException(NOT_STARTED_ERROR);
        }
    }

    private void setUpScribeClient() {
        this.scribeClient = new DefaultScribeClient(this, "TweetUi", this.sessionManager, this.guestSessionProvider, getIdManager());
    }

    void scribe(EventNamespace... namespaces) {
        if (this.scribeClient != null) {
            for (EventNamespace ns : namespaces) {
                this.scribeClient.scribe(ns);
            }
        }
    }

    void scribe(EventNamespace ns, List<ScribeItem> items) {
        if (this.scribeClient != null) {
            this.scribeClient.scribe(ns, (List) items);
        }
    }

    TweetRepository getTweetRepository() {
        return this.tweetRepository;
    }

    void setTweetRepository(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public Picasso getImageLoader() {
        return this.imageLoader;
    }

    void setImageLoader(Picasso imageLoader) {
        this.imageLoader = imageLoader;
    }
}
