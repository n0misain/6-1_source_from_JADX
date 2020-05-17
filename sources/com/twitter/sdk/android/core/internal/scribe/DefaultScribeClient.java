package com.twitter.sdk.android.core.internal.scribe;

import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.internal.scribe.ScribeEvent.Transform;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.ExecutorUtils;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class DefaultScribeClient extends ScribeClient {
    private static final String DEBUG_BUILD = "debug";
    private static final String SCRIBE_PATH_TYPE = "sdk";
    private static final String SCRIBE_PATH_VERSION = "i";
    private static final String SCRIBE_URL = "https://syndication.twitter.com";
    private static volatile ScheduledExecutorService executor;
    private final String advertisingId;
    private final Kit kit;
    private final SessionManager<? extends Session<TwitterAuthToken>> sessionManager;

    public DefaultScribeClient(Kit kit, String kitName, SessionManager<? extends Session<TwitterAuthToken>> sessionManager, GuestSessionProvider guestSessionProvider, IdManager idManager) {
        this(kit, kitName, getGson(), sessionManager, guestSessionProvider, idManager);
    }

    DefaultScribeClient(Kit kit, String kitName, Gson gson, SessionManager<? extends Session<TwitterAuthToken>> sessionManager, GuestSessionProvider guestSessionProvider, IdManager idManager) {
        super(kit, getExecutor(), getScribeConfig(Settings.getInstance().awaitSettingsData(), getUserAgent(kitName, kit)), new Transform(gson), TwitterCore.getInstance().getAuthConfig(), sessionManager, guestSessionProvider, TwitterCore.getInstance().getSSLSocketFactory(), idManager);
        this.sessionManager = sessionManager;
        this.kit = kit;
        this.advertisingId = idManager.getAdvertisingId();
    }

    public void scribe(EventNamespace... namespaces) {
        for (EventNamespace ns : namespaces) {
            scribe(ns, Collections.emptyList());
        }
    }

    public void scribe(EventNamespace namespace, List<ScribeItem> items) {
        String str = "";
        EventNamespace eventNamespace = namespace;
        scribe(ScribeEventFactory.newScribeEvent(eventNamespace, str, System.currentTimeMillis(), getLanguageFromKit(), this.advertisingId, items));
    }

    public void scribe(ScribeEvent event) {
        super.scribe(event, getScribeSessionId(getActiveSession()));
    }

    public void scribe(EventNamespace namespace, String eventInfo) {
        EventNamespace eventNamespace = namespace;
        String str = eventInfo;
        scribe(ScribeEventFactory.newScribeEvent(eventNamespace, str, System.currentTimeMillis(), getLanguageFromKit(), this.advertisingId, Collections.emptyList()));
    }

    Session getActiveSession() {
        return this.sessionManager.getActiveSession();
    }

    long getScribeSessionId(Session activeSession) {
        if (activeSession != null) {
            return activeSession.getId();
        }
        return 0;
    }

    private String getLanguageFromKit() {
        if (this.kit.getContext() != null) {
            return this.kit.getContext().getResources().getConfiguration().locale.getLanguage();
        }
        return "";
    }

    private static Gson getGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    private static ScheduledExecutorService getExecutor() {
        if (executor == null) {
            synchronized (DefaultScribeClient.class) {
                if (executor == null) {
                    executor = ExecutorUtils.buildSingleThreadScheduledExecutorService("scribe");
                }
            }
        }
        return executor;
    }

    static ScribeConfig getScribeConfig(SettingsData settingsData, String userAgent) {
        int maxFilesToKeep;
        int sendIntervalSeconds;
        if (settingsData == null || settingsData.analyticsSettingsData == null) {
            maxFilesToKeep = 100;
            sendIntervalSeconds = 600;
        } else {
            maxFilesToKeep = settingsData.analyticsSettingsData.maxPendingSendFileCount;
            sendIntervalSeconds = settingsData.analyticsSettingsData.flushIntervalSeconds;
        }
        return new ScribeConfig(isEnabled(), getScribeUrl(SCRIBE_URL, ""), SCRIBE_PATH_VERSION, "sdk", "", userAgent, maxFilesToKeep, sendIntervalSeconds);
    }

    private static boolean isEnabled() {
        return !"release".equals(DEBUG_BUILD);
    }

    static String getUserAgent(String kitName, Kit kit) {
        return "Fabric/" + kit.getFabric().getVersion() + " (Android " + VERSION.SDK_INT + ") " + kitName + "/" + kit.getVersion();
    }

    static String getScribeUrl(String defaultUrl, String overrideUrl) {
        return !TextUtils.isEmpty(overrideUrl) ? overrideUrl : defaultUrl;
    }
}
