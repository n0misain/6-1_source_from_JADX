package com.twitter.sdk.android.core;

import android.app.Activity;
import com.twitter.sdk.android.core.GuestSession.Serializer;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.internal.MigrationHelper;
import com.twitter.sdk.android.core.internal.SessionMonitor;
import com.twitter.sdk.android.core.internal.TwitterApi;
import com.twitter.sdk.android.core.internal.TwitterSessionVerifier;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Service;
import com.twitter.sdk.android.core.internal.scribe.TwitterCoreScribeClientHolder;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.network.NetworkUtils;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.SSLSocketFactory;

public class TwitterCore extends Kit<Boolean> {
    static final String PREF_KEY_ACTIVE_GUEST_SESSION = "active_guestsession";
    static final String PREF_KEY_ACTIVE_TWITTER_SESSION = "active_twittersession";
    static final String PREF_KEY_GUEST_SESSION = "guestsession";
    static final String PREF_KEY_TWITTER_SESSION = "twittersession";
    static final String SESSION_PREF_FILE_NAME = "session_store";
    public static final String TAG = "Twitter";
    private final ConcurrentHashMap<Session, TwitterApiClient> apiClients;
    private final TwitterAuthConfig authConfig;
    private volatile TwitterApiClient guestClient;
    SessionManager<GuestSession> guestSessionManager;
    private volatile GuestSessionProvider guestSessionProvider;
    SessionMonitor<TwitterSession> sessionMonitor;
    private volatile SSLSocketFactory sslSocketFactory;
    SessionManager<TwitterSession> twitterSessionManager;

    public TwitterCore(TwitterAuthConfig authConfig) {
        this(authConfig, new ConcurrentHashMap(), null);
    }

    TwitterCore(TwitterAuthConfig authConfig, ConcurrentHashMap<Session, TwitterApiClient> apiClients, TwitterApiClient guestClient) {
        this.authConfig = authConfig;
        this.apiClients = apiClients;
        this.guestClient = guestClient;
    }

    public static TwitterCore getInstance() {
        checkInitialized();
        return (TwitterCore) Fabric.getKit(TwitterCore.class);
    }

    public String getVersion() {
        return "2.3.0.163";
    }

    public TwitterAuthConfig getAuthConfig() {
        return this.authConfig;
    }

    public SSLSocketFactory getSSLSocketFactory() {
        checkInitialized();
        if (this.sslSocketFactory == null) {
            createSSLSocketFactory();
        }
        return this.sslSocketFactory;
    }

    private synchronized void createSSLSocketFactory() {
        if (this.sslSocketFactory == null) {
            try {
                this.sslSocketFactory = NetworkUtils.getSSLSocketFactory(new TwitterPinningInfoProvider(getContext()));
                Fabric.getLogger().mo4333d(TAG, "Custom SSL pinning enabled");
            } catch (Exception e) {
                Fabric.getLogger().mo4336e(TAG, "Exception setting up custom SSL pinning", e);
            }
        }
    }

    protected boolean onPreExecute() {
        new MigrationHelper().migrateSessionStore(getContext(), getIdentifier(), getIdentifier() + ":" + SESSION_PREF_FILE_NAME + ".xml");
        this.twitterSessionManager = new PersistedSessionManager(new PreferenceStoreImpl(getContext(), SESSION_PREF_FILE_NAME), new Serializer(), PREF_KEY_ACTIVE_TWITTER_SESSION, PREF_KEY_TWITTER_SESSION);
        this.guestSessionManager = new PersistedSessionManager(new PreferenceStoreImpl(getContext(), SESSION_PREF_FILE_NAME), new Serializer(), PREF_KEY_ACTIVE_GUEST_SESSION, PREF_KEY_GUEST_SESSION);
        this.sessionMonitor = new SessionMonitor(this.twitterSessionManager, getFabric().getExecutorService(), new TwitterSessionVerifier());
        return true;
    }

    protected Boolean doInBackground() {
        this.twitterSessionManager.getActiveSession();
        this.guestSessionManager.getActiveSession();
        getSSLSocketFactory();
        getGuestSessionProvider();
        initializeScribeClient();
        this.sessionMonitor.monitorActivityLifecycle(getFabric().getActivityLifecycleManager());
        return Boolean.valueOf(true);
    }

    public String getIdentifier() {
        return "com.twitter.sdk.android:twitter-core";
    }

    private static void checkInitialized() {
        if (Fabric.getKit(TwitterCore.class) == null) {
            throw new IllegalStateException("Must start Twitter Kit with Fabric.with() first");
        }
    }

    private void initializeScribeClient() {
        TwitterCoreScribeClientHolder.initialize(this, getSessionManager(), getGuestSessionProvider(), getIdManager());
    }

    public void logIn(Activity activity, Callback<TwitterSession> callback) {
        checkInitialized();
        new TwitterAuthClient().authorize(activity, callback);
    }

    public void logOut() {
        checkInitialized();
        SessionManager<TwitterSession> sessionManager = getSessionManager();
        if (sessionManager != null) {
            sessionManager.clearActiveSession();
        }
    }

    public SessionManager<TwitterSession> getSessionManager() {
        checkInitialized();
        return this.twitterSessionManager;
    }

    public GuestSessionProvider getGuestSessionProvider() {
        checkInitialized();
        if (this.guestSessionProvider == null) {
            createGuestSessionProvider();
        }
        return this.guestSessionProvider;
    }

    private synchronized void createGuestSessionProvider() {
        if (this.guestSessionProvider == null) {
            this.guestSessionProvider = new GuestSessionProvider(new OAuth2Service(this, getSSLSocketFactory(), new TwitterApi()), this.guestSessionManager);
        }
    }

    public TwitterApiClient getApiClient() {
        checkInitialized();
        TwitterSession session = (TwitterSession) this.twitterSessionManager.getActiveSession();
        if (session == null) {
            return getGuestApiClient();
        }
        return getApiClient(session);
    }

    public TwitterApiClient getApiClient(TwitterSession session) {
        checkInitialized();
        if (!this.apiClients.containsKey(session)) {
            this.apiClients.putIfAbsent(session, new TwitterApiClient(session));
        }
        return (TwitterApiClient) this.apiClients.get(session);
    }

    public void addGuestApiClient(TwitterApiClient customTwitterApiClient) {
        checkInitialized();
        if (this.guestClient == null) {
            createGuestClient(customTwitterApiClient);
        }
    }

    public void addApiClient(TwitterSession session, TwitterApiClient customTwitterApiClient) {
        checkInitialized();
        if (!this.apiClients.containsKey(session)) {
            this.apiClients.putIfAbsent(session, customTwitterApiClient);
        }
    }

    public TwitterApiClient getGuestApiClient() {
        checkInitialized();
        if (this.guestClient == null) {
            createGuestClient();
        }
        return this.guestClient;
    }

    private synchronized void createGuestClient() {
        if (this.guestClient == null) {
            this.guestClient = new TwitterApiClient();
        }
    }

    private synchronized void createGuestClient(TwitterApiClient twitterApiClient) {
        if (this.guestClient == null) {
            this.guestClient = twitterApiClient;
        }
    }
}
