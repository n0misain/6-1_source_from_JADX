package com.orhanobut.logger;

public interface Printer {
    void addAdapter(LogAdapter logAdapter);

    void clearLogAdapters();

    /* renamed from: d */
    void mo4186d(Object obj);

    /* renamed from: d */
    void mo4187d(String str, Object... objArr);

    /* renamed from: e */
    void mo4188e(String str, Object... objArr);

    /* renamed from: e */
    void mo4189e(Throwable th, String str, Object... objArr);

    /* renamed from: i */
    void mo4190i(String str, Object... objArr);

    void json(String str);

    void log(int i, String str, String str2, Throwable th);

    /* renamed from: t */
    Printer mo4193t(String str);

    /* renamed from: v */
    void mo4194v(String str, Object... objArr);

    /* renamed from: w */
    void mo4195w(String str, Object... objArr);

    void wtf(String str, Object... objArr);

    void xml(String str);
}
