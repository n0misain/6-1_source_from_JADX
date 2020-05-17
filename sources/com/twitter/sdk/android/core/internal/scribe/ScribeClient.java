package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.internal.scribe.ScribeEvent.Transform;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import io.fabric.sdk.android.services.events.DisabledEventsStrategy;
import io.fabric.sdk.android.services.events.EventsStrategy;
import io.fabric.sdk.android.services.events.QueueFileEventStorage;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import javax.net.ssl.SSLSocketFactory;

public class ScribeClient {
    private static final String STORAGE_DIR_BASE = "_se_to_send";
    private static final String WORKING_FILENAME_BASE = "_se.tap";
    private final TwitterAuthConfig authConfig;
    private final ScheduledExecutorService executor;
    private final GuestSessionProvider guestSessionProvider;
    private final IdManager idManager;
    private final Kit kit;
    private final ScribeConfig scribeConfig;
    final ConcurrentHashMap<Long, ScribeHandler> scribeHandlers = new ConcurrentHashMap(2);
    private final SessionManager<? extends Session<TwitterAuthToken>> sessionManager;
    private final SSLSocketFactory sslSocketFactory;
    private final Transform transform;

    public ScribeClient(Kit kit, ScheduledExecutorService executor, ScribeConfig scribeConfig, Transform transform, TwitterAuthConfig authConfig, SessionManager<? extends Session<TwitterAuthToken>> sessionManager, GuestSessionProvider guestSessionProvider, SSLSocketFactory sslSocketFactory, IdManager idManager) {
        this.kit = kit;
        this.executor = executor;
        this.scribeConfig = scribeConfig;
        this.transform = transform;
        this.authConfig = authConfig;
        this.sessionManager = sessionManager;
        this.guestSessionProvider = guestSessionProvider;
        this.sslSocketFactory = sslSocketFactory;
        this.idManager = idManager;
    }

    public boolean scribe(ScribeEvent event, long ownerId) {
        try {
            getScribeHandler(ownerId).scribe(event);
            return true;
        } catch (IOException e) {
            CommonUtils.logControlledError(this.kit.getContext(), "Failed to scribe event", e);
            return false;
        }
    }

    public boolean scribeAndFlush(ScribeEvent event, long ownerId) {
        try {
            getScribeHandler(ownerId).scribeAndFlush(event);
            return true;
        } catch (IOException e) {
            CommonUtils.logControlledError(this.kit.getContext(), "Failed to scribe event", e);
            return false;
        }
    }

    ScribeHandler getScribeHandler(long ownerId) throws IOException {
        if (!this.scribeHandlers.containsKey(Long.valueOf(ownerId))) {
            this.scribeHandlers.putIfAbsent(Long.valueOf(ownerId), newScribeHandler(ownerId));
        }
        return (ScribeHandler) this.scribeHandlers.get(Long.valueOf(ownerId));
    }

    private ScribeHandler newScribeHandler(long ownerId) throws IOException {
        Context context = this.kit.getContext();
        ScribeFilesManager filesManager = new ScribeFilesManager(context, this.transform, new SystemCurrentTimeProvider(), new QueueFileEventStorage(context, new FileStoreImpl(this.kit).getFilesDir(), getWorkingFileNameForOwner(ownerId), getStorageDirForOwner(ownerId)), this.scribeConfig.maxFilesToKeep);
        return new ScribeHandler(context, getScribeStrategy(ownerId, filesManager), filesManager, this.executor);
    }

    EventsStrategy<ScribeEvent> getScribeStrategy(long ownerId, ScribeFilesManager filesManager) {
        Context context = this.kit.getContext();
        if (this.scribeConfig.isEnabled) {
            CommonUtils.logControlled(context, "Scribe enabled");
            return new EnabledScribeStrategy(context, this.executor, filesManager, this.scribeConfig, new ScribeFilesSender(context, this.scribeConfig, ownerId, this.authConfig, this.sessionManager, this.guestSessionProvider, this.sslSocketFactory, this.executor, this.idManager));
        }
        CommonUtils.logControlled(context, "Scribe disabled");
        return new DisabledEventsStrategy();
    }

    String getWorkingFileNameForOwner(long ownerId) {
        return ownerId + WORKING_FILENAME_BASE;
    }

    String getStorageDirForOwner(long ownerId) {
        return ownerId + STORAGE_DIR_BASE;
    }
}
