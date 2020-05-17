package com.applovin.impl.sdk;

import java.lang.Thread.UncaughtExceptionHandler;

class el implements UncaughtExceptionHandler {
    /* renamed from: a */
    final /* synthetic */ ek f862a;

    el(ek ekVar) {
        this.f862a = ekVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        this.f862a.f860a.f852c.mo2290e("TaskManager", "Caught unhandled exception", th);
    }
}
