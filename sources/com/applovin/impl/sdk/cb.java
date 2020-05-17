package com.applovin.impl.sdk;

import java.util.Map;

class cb implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Map f629a;
    /* renamed from: b */
    final /* synthetic */ ca f630b;

    cb(ca caVar, Map map) {
        this.f630b = caVar;
        this.f629a = map;
    }

    public void run() {
        try {
            this.f630b.f627e.m630a(this.f629a);
            this.f630b.f624b.initialize(this.f630b.f627e, this.f630b.f625c, this.f630b.f625c.getInitializationActivity());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
