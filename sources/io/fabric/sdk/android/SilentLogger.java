package io.fabric.sdk.android;

public class SilentLogger implements Logger {
    private int logLevel = 7;

    public boolean isLoggable(String tag, int level) {
        return false;
    }

    /* renamed from: d */
    public void mo4334d(String tag, String text, Throwable throwable) {
    }

    /* renamed from: v */
    public void mo4345v(String tag, String text, Throwable throwable) {
    }

    /* renamed from: i */
    public void mo4339i(String tag, String text, Throwable throwable) {
    }

    /* renamed from: w */
    public void mo4347w(String tag, String text, Throwable throwable) {
    }

    /* renamed from: e */
    public void mo4336e(String tag, String text, Throwable throwable) {
    }

    /* renamed from: d */
    public void mo4333d(String tag, String text) {
    }

    /* renamed from: v */
    public void mo4344v(String tag, String text) {
    }

    /* renamed from: i */
    public void mo4338i(String tag, String text) {
    }

    /* renamed from: w */
    public void mo4346w(String tag, String text) {
    }

    /* renamed from: e */
    public void mo4335e(String tag, String text) {
    }

    public void log(int priority, String tag, String msg) {
    }

    public void log(int priority, String tag, String msg, boolean forceLog) {
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(int logLevel) {
    }
}
