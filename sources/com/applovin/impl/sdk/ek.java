package com.applovin.impl.sdk;

import java.util.concurrent.ThreadFactory;

class ek implements ThreadFactory {
    /* renamed from: a */
    final /* synthetic */ ei f860a;
    /* renamed from: b */
    private final String f861b;

    public ek(ei eiVar, String str) {
        this.f860a = eiVar;
        this.f861b = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "AppLovinSdk:" + this.f861b + ":" + fk.m1081a(this.f860a.f851b.getSdkKey()));
        thread.setDaemon(true);
        thread.setPriority(10);
        thread.setUncaughtExceptionHandler(new el(this));
        return thread;
    }
}
