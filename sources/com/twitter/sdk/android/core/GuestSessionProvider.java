package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Service;
import io.fabric.sdk.android.Fabric;
import java.util.concurrent.CountDownLatch;

public class GuestSessionProvider {
    private final OAuth2Service oAuth2Service;
    private final SessionManager<GuestSession> sessionManager;

    public GuestSessionProvider(OAuth2Service oAuth2Service, SessionManager<GuestSession> sessionManager) {
        this.oAuth2Service = oAuth2Service;
        this.sessionManager = sessionManager;
    }

    public synchronized GuestSession getCurrentSession() {
        GuestSession session;
        session = (GuestSession) this.sessionManager.getActiveSession();
        if (!isSessionValid(session)) {
            refreshToken();
            session = (GuestSession) this.sessionManager.getActiveSession();
        }
        return session;
    }

    public synchronized GuestSession refreshCurrentSession(GuestSession expiredSession) {
        GuestSession session = (GuestSession) this.sessionManager.getActiveSession();
        if (expiredSession != null && expiredSession.equals(session)) {
            refreshToken();
        }
        return (GuestSession) this.sessionManager.getActiveSession();
    }

    void refreshToken() {
        Fabric.getLogger().mo4333d("GuestSessionProvider", "Refreshing expired guest session.");
        final CountDownLatch latch = new CountDownLatch(1);
        this.oAuth2Service.requestGuestAuthToken(new Callback<GuestAuthToken>() {
            public void success(Result<GuestAuthToken> result) {
                GuestSessionProvider.this.sessionManager.setActiveSession(new GuestSession((GuestAuthToken) result.data));
                latch.countDown();
            }

            public void failure(TwitterException exception) {
                GuestSessionProvider.this.sessionManager.clearSession(0);
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            this.sessionManager.clearSession(0);
        }
    }

    boolean isSessionValid(GuestSession session) {
        return (session == null || session.getAuthToken() == null || ((GuestAuthToken) session.getAuthToken()).isExpired()) ? false : true;
    }
}
