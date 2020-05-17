package com.twitter.sdk.android.tweetcomposer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.scribe.DefaultScribeClient;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

@DependsOn({TwitterCore.class})
public class TweetComposer extends Kit<Void> {
    private static final String KIT_SCRIBE_NAME = "TweetComposer";
    private static final String MIME_TYPE_JPEG = "image/jpeg";
    private static final String MIME_TYPE_PLAIN_TEXT = "text/plain";
    private static final String TWITTER_PACKAGE_NAME = "com.twitter.android";
    private static final String WEB_INTENT = "https://twitter.com/intent/tweet?text=%s&url=%s";
    String advertisingId;
    private final ConcurrentHashMap<Session, ComposerApiClient> apiClients = new ConcurrentHashMap();
    GuestSessionProvider guestSessionProvider;
    private ScribeClient scribeClient = new ScribeClientImpl(null);
    SessionManager<TwitterSession> sessionManager;

    public static class Builder {
        private final Context context;
        private Uri imageUri;
        private String text;
        private URL url;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context;
        }

        public Builder text(String text) {
            if (text == null) {
                throw new IllegalArgumentException("text must not be null.");
            } else if (this.text != null) {
                throw new IllegalStateException("text already set.");
            } else {
                this.text = text;
                return this;
            }
        }

        public Builder url(URL url) {
            if (url == null) {
                throw new IllegalArgumentException("url must not be null.");
            } else if (this.url != null) {
                throw new IllegalStateException("url already set.");
            } else {
                this.url = url;
                return this;
            }
        }

        public Builder image(Uri imageUri) {
            if (imageUri == null) {
                throw new IllegalArgumentException("imageUri must not be null.");
            } else if (this.imageUri != null) {
                throw new IllegalStateException("imageUri already set.");
            } else {
                this.imageUri = imageUri;
                return this;
            }
        }

        public Intent createIntent() {
            Intent intent = createTwitterIntent();
            if (intent == null) {
                return createWebIntent();
            }
            return intent;
        }

        Intent createTwitterIntent() {
            Intent intent = new Intent("android.intent.action.SEND");
            StringBuilder builder = new StringBuilder();
            if (!TextUtils.isEmpty(this.text)) {
                builder.append(this.text);
            }
            if (this.url != null) {
                if (builder.length() > 0) {
                    builder.append(' ');
                }
                builder.append(this.url.toString());
            }
            intent.putExtra("android.intent.extra.TEXT", builder.toString());
            intent.setType(TweetComposer.MIME_TYPE_PLAIN_TEXT);
            if (this.imageUri != null) {
                intent.putExtra("android.intent.extra.STREAM", this.imageUri);
                intent.setType(TweetComposer.MIME_TYPE_JPEG);
            }
            for (ResolveInfo resolveInfo : this.context.getPackageManager().queryIntentActivities(intent, 65536)) {
                if (resolveInfo.activityInfo.packageName.startsWith(TweetComposer.TWITTER_PACKAGE_NAME)) {
                    intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                    return intent;
                }
            }
            return null;
        }

        Intent createWebIntent() {
            String url = this.url == null ? "" : this.url.toString();
            return new Intent("android.intent.action.VIEW", Uri.parse(String.format(TweetComposer.WEB_INTENT, new Object[]{UrlUtils.urlEncode(this.text), UrlUtils.urlEncode(url)})));
        }

        public void show() {
            this.context.startActivity(createIntent());
        }
    }

    public String getVersion() {
        return "2.3.1.165";
    }

    protected boolean onPreExecute() {
        this.sessionManager = TwitterCore.getInstance().getSessionManager();
        this.guestSessionProvider = TwitterCore.getInstance().getGuestSessionProvider();
        return super.onPreExecute();
    }

    protected Void doInBackground() {
        this.advertisingId = getIdManager().getAdvertisingId();
        this.scribeClient = new ScribeClientImpl(new DefaultScribeClient(this, KIT_SCRIBE_NAME, this.sessionManager, this.guestSessionProvider, getIdManager()));
        return null;
    }

    public String getIdentifier() {
        return "com.twitter.sdk.android:tweet-composer";
    }

    public ComposerApiClient getApiClient(TwitterSession session) {
        checkInitialized();
        if (!this.apiClients.containsKey(session)) {
            this.apiClients.putIfAbsent(session, new ComposerApiClient(session));
        }
        return (ComposerApiClient) this.apiClients.get(session);
    }

    public static TweetComposer getInstance() {
        checkInitialized();
        return (TweetComposer) Fabric.getKit(TweetComposer.class);
    }

    protected ScribeClient getScribeClient() {
        return this.scribeClient;
    }

    private static void checkInitialized() {
        if (Fabric.getKit(TweetComposer.class) == null) {
            throw new IllegalStateException("Must start Twitter Kit with Fabric.with() first");
        }
    }

    String getAdvertisingId() {
        return this.advertisingId;
    }
}
