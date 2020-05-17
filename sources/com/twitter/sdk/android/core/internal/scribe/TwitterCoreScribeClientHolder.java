package com.twitter.sdk.android.core.internal.scribe;

import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.services.common.IdManager;

public class TwitterCoreScribeClientHolder {
    private static final String KIT_NAME = "TwitterCore";
    private static DefaultScribeClient instance;

    public static DefaultScribeClient getScribeClient() {
        return instance;
    }

    public static void initialize(TwitterCore kit, SessionManager<? extends Session<TwitterAuthToken>> sessionManagers, GuestSessionProvider guestSessionProvider, IdManager idManager) {
        instance = new DefaultScribeClient(kit, KIT_NAME, sessionManagers, guestSessionProvider, idManager);
    }
}
