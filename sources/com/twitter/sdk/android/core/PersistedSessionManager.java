package com.twitter.sdk.android.core;

import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreStrategy;
import io.fabric.sdk.android.services.persistence.SerializationStrategy;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class PersistedSessionManager<T extends Session> implements SessionManager<T> {
    private static final int NUM_SESSIONS = 1;
    private final AtomicReference<T> activeSessionRef;
    private final PreferenceStoreStrategy<T> activeSessionStorage;
    private final String prefKeySession;
    private final PreferenceStore preferenceStore;
    private volatile boolean restorePending;
    private final SerializationStrategy<T> serializer;
    private final ConcurrentHashMap<Long, T> sessionMap;
    private final ConcurrentHashMap<Long, PreferenceStoreStrategy<T>> storageMap;

    public PersistedSessionManager(PreferenceStore preferenceStore, SerializationStrategy<T> serializer, String prefKeyActiveSession, String prefKeySession) {
        this(preferenceStore, serializer, new ConcurrentHashMap(1), new ConcurrentHashMap(1), new PreferenceStoreStrategy(preferenceStore, serializer, prefKeyActiveSession), prefKeySession);
    }

    PersistedSessionManager(PreferenceStore preferenceStore, SerializationStrategy<T> serializer, ConcurrentHashMap<Long, T> sessionMap, ConcurrentHashMap<Long, PreferenceStoreStrategy<T>> storageMap, PreferenceStoreStrategy<T> activesSessionStorage, String prefKeySession) {
        this.restorePending = true;
        this.preferenceStore = preferenceStore;
        this.serializer = serializer;
        this.sessionMap = sessionMap;
        this.storageMap = storageMap;
        this.activeSessionStorage = activesSessionStorage;
        this.activeSessionRef = new AtomicReference();
        this.prefKeySession = prefKeySession;
    }

    void restoreAllSessionsIfNecessary() {
        if (this.restorePending) {
            restoreAllSessions();
        }
    }

    private synchronized void restoreAllSessions() {
        if (this.restorePending) {
            restoreActiveSession();
            restoreSessions();
            this.restorePending = false;
        }
    }

    private void restoreSessions() {
        for (Entry<String, ?> entry : this.preferenceStore.get().getAll().entrySet()) {
            if (isSessionPreferenceKey((String) entry.getKey())) {
                Session session = (Session) this.serializer.deserialize((String) entry.getValue());
                if (session != null) {
                    internalSetSession(session.getId(), session, false);
                }
            }
        }
    }

    private void restoreActiveSession() {
        Session session = (Session) this.activeSessionStorage.restore();
        if (session != null) {
            internalSetSession(session.getId(), session, false);
        }
    }

    boolean isSessionPreferenceKey(String preferenceKey) {
        return preferenceKey.startsWith(this.prefKeySession);
    }

    public T getActiveSession() {
        restoreAllSessionsIfNecessary();
        return (Session) this.activeSessionRef.get();
    }

    public void setActiveSession(T session) {
        if (session == null) {
            throw new IllegalArgumentException("Session must not be null!");
        }
        restoreAllSessionsIfNecessary();
        internalSetSession(session.getId(), session, true);
    }

    public void clearActiveSession() {
        restoreAllSessionsIfNecessary();
        if (this.activeSessionRef.get() != null) {
            clearSession(((Session) this.activeSessionRef.get()).getId());
        }
    }

    public T getSession(long id) {
        restoreAllSessionsIfNecessary();
        return (Session) this.sessionMap.get(Long.valueOf(id));
    }

    public void setSession(long id, T session) {
        if (session == null) {
            throw new IllegalArgumentException("Session must not be null!");
        }
        restoreAllSessionsIfNecessary();
        internalSetSession(id, session, false);
    }

    public Map<Long, T> getSessionMap() {
        restoreAllSessionsIfNecessary();
        return Collections.unmodifiableMap(this.sessionMap);
    }

    private void internalSetSession(long id, T session, boolean forceUpdate) {
        this.sessionMap.put(Long.valueOf(id), session);
        PreferenceStoreStrategy<T> storage = (PreferenceStoreStrategy) this.storageMap.get(Long.valueOf(id));
        if (storage == null) {
            storage = new PreferenceStoreStrategy(this.preferenceStore, this.serializer, getPrefKey(id));
            this.storageMap.putIfAbsent(Long.valueOf(id), storage);
        }
        storage.save(session);
        Session activeSession = (Session) this.activeSessionRef.get();
        if (activeSession == null || activeSession.getId() == id || forceUpdate) {
            synchronized (this) {
                this.activeSessionRef.compareAndSet(activeSession, session);
                this.activeSessionStorage.save(session);
            }
        }
    }

    String getPrefKey(long id) {
        return this.prefKeySession + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + id;
    }

    public void clearSession(long id) {
        restoreAllSessionsIfNecessary();
        if (this.activeSessionRef.get() != null && ((Session) this.activeSessionRef.get()).getId() == id) {
            synchronized (this) {
                this.activeSessionRef.set(null);
                this.activeSessionStorage.clear();
            }
        }
        this.sessionMap.remove(Long.valueOf(id));
        PreferenceStoreStrategy<T> storage = (PreferenceStoreStrategy) this.storageMap.remove(Long.valueOf(id));
        if (storage != null) {
            storage.clear();
        }
    }
}
