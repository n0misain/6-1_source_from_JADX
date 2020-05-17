package io.fabric.sdk.android;

import android.util.Log;

public class DefaultLogger implements Logger {
    private int logLevel;

    public DefaultLogger(int logLevel) {
        this.logLevel = logLevel;
    }

    public DefaultLogger() {
        this.logLevel = 4;
    }

    public boolean isLoggable(String tag, int level) {
        return this.logLevel <= level;
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    /* renamed from: d */
    public void mo4334d(String tag, String text, Throwable throwable) {
        if (isLoggable(tag, 3)) {
            Log.d(tag, text, throwable);
        }
    }

    /* renamed from: v */
    public void mo4345v(String tag, String text, Throwable throwable) {
        if (isLoggable(tag, 2)) {
            Log.v(tag, text, throwable);
        }
    }

    /* renamed from: i */
    public void mo4339i(String tag, String text, Throwable throwable) {
        if (isLoggable(tag, 4)) {
            Log.i(tag, text, throwable);
        }
    }

    /* renamed from: w */
    public void mo4347w(String tag, String text, Throwable throwable) {
        if (isLoggable(tag, 5)) {
            Log.w(tag, text, throwable);
        }
    }

    /* renamed from: e */
    public void mo4336e(String tag, String text, Throwable throwable) {
        if (isLoggable(tag, 6)) {
            Log.e(tag, text, throwable);
        }
    }

    /* renamed from: d */
    public void mo4333d(String tag, String text) {
        mo4334d(tag, text, null);
    }

    /* renamed from: v */
    public void mo4344v(String tag, String text) {
        mo4345v(tag, text, null);
    }

    /* renamed from: i */
    public void mo4338i(String tag, String text) {
        mo4339i(tag, text, null);
    }

    /* renamed from: w */
    public void mo4346w(String tag, String text) {
        mo4347w(tag, text, null);
    }

    /* renamed from: e */
    public void mo4335e(String tag, String text) {
        mo4336e(tag, text, null);
    }

    public void log(int priority, String tag, String msg) {
        log(priority, tag, msg, false);
    }

    public void log(int priority, String tag, String msg, boolean forceLog) {
        if (forceLog || isLoggable(tag, priority)) {
            Log.println(priority, tag, msg);
        }
    }
}
