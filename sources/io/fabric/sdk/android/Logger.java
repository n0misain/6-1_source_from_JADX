package io.fabric.sdk.android;

public interface Logger {
    /* renamed from: d */
    void mo4333d(String str, String str2);

    /* renamed from: d */
    void mo4334d(String str, String str2, Throwable th);

    /* renamed from: e */
    void mo4335e(String str, String str2);

    /* renamed from: e */
    void mo4336e(String str, String str2, Throwable th);

    int getLogLevel();

    /* renamed from: i */
    void mo4338i(String str, String str2);

    /* renamed from: i */
    void mo4339i(String str, String str2, Throwable th);

    boolean isLoggable(String str, int i);

    void log(int i, String str, String str2);

    void log(int i, String str, String str2, boolean z);

    void setLogLevel(int i);

    /* renamed from: v */
    void mo4344v(String str, String str2);

    /* renamed from: v */
    void mo4345v(String str, String str2, Throwable th);

    /* renamed from: w */
    void mo4346w(String str, String str2);

    /* renamed from: w */
    void mo4347w(String str, String str2, Throwable th);
}
