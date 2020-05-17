package com.twitter.sdk.android.core.internal;

import android.app.Activity;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import io.fabric.sdk.android.ActivityLifecycleManager;
import io.fabric.sdk.android.ActivityLifecycleManager.Callbacks;
import io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;

public class SessionMonitor<T extends Session> {
    private final ExecutorService executorService;
    protected final MonitorState monitorState;
    private final SessionManager<T> sessionManager;
    private final SessionVerifier sessionVerifier;
    private final SystemCurrentTimeProvider time;

    /* renamed from: com.twitter.sdk.android.core.internal.SessionMonitor$1 */
    class C10061 extends Callbacks {
        C10061() {
        }

        public void onActivityStarted(Activity activity) {
            SessionMonitor.this.triggerVerificationIfNecessary();
        }
    }

    /* renamed from: com.twitter.sdk.android.core.internal.SessionMonitor$2 */
    class C10072 implements Runnable {
        C10072() {
        }

        public void run() {
            SessionMonitor.this.verifyAll();
        }
    }

    protected static class MonitorState {
        private static final long TIME_THRESHOLD_IN_MILLIS = 21600000;
        public long lastVerification;
        private final Calendar utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        public boolean verifying;

        public synchronized boolean beginVerification(long currentTime) {
            boolean z = true;
            synchronized (this) {
                boolean isPastThreshold;
                if (currentTime - this.lastVerification > TIME_THRESHOLD_IN_MILLIS) {
                    isPastThreshold = true;
                } else {
                    isPastThreshold = false;
                }
                boolean dayHasChanged;
                if (isOnSameDate(currentTime, this.lastVerification)) {
                    dayHasChanged = false;
                } else {
                    dayHasChanged = true;
                }
                if (this.verifying || !(isPastThreshold || dayHasChanged)) {
                    z = false;
                } else {
                    this.verifying = true;
                }
            }
            return z;
        }

        public synchronized void endVerification(long currentTime) {
            this.verifying = false;
            this.lastVerification = currentTime;
        }

        private boolean isOnSameDate(long timeA, long timeB) {
            this.utcCalendar.setTimeInMillis(timeA);
            int dayA = this.utcCalendar.get(6);
            int yearA = this.utcCalendar.get(1);
            this.utcCalendar.setTimeInMillis(timeB);
            int dayB = this.utcCalendar.get(6);
            int yearB = this.utcCalendar.get(1);
            if (dayA == dayB && yearA == yearB) {
                return true;
            }
            return false;
        }
    }

    public SessionMonitor(SessionManager<T> sessionManager, ExecutorService executorService, SessionVerifier<T> sessionVerifier) {
        this(sessionManager, new SystemCurrentTimeProvider(), executorService, new MonitorState(), sessionVerifier);
    }

    SessionMonitor(SessionManager<T> sessionManager, SystemCurrentTimeProvider time, ExecutorService executorService, MonitorState monitorState, SessionVerifier sessionVerifier) {
        this.time = time;
        this.sessionManager = sessionManager;
        this.executorService = executorService;
        this.monitorState = monitorState;
        this.sessionVerifier = sessionVerifier;
    }

    public void monitorActivityLifecycle(ActivityLifecycleManager activityLifecycleManager) {
        activityLifecycleManager.registerCallbacks(new C10061());
    }

    public void triggerVerificationIfNecessary() {
        boolean startVerification = this.sessionManager.getActiveSession() != null && this.monitorState.beginVerification(this.time.getCurrentTimeMillis());
        if (startVerification) {
            this.executorService.submit(new C10072());
        }
    }

    protected void verifyAll() {
        for (Session session : this.sessionManager.getSessionMap().values()) {
            this.sessionVerifier.verifySession(session);
        }
        this.monitorState.endVerification(this.time.getCurrentTimeMillis());
    }
}
