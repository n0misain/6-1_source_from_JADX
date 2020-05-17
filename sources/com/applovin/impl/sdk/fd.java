package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.Map;

public class fd {
    /* renamed from: a */
    private static final fd f896a = new fd();
    /* renamed from: b */
    private final Object f897b = new Object();
    /* renamed from: c */
    private final Map f898c = new HashMap(2);

    private fd() {
    }

    /* renamed from: a */
    static fd m1052a() {
        return f896a;
    }

    /* renamed from: a */
    ff m1053a(String str) {
        ff ffVar;
        synchronized (this.f897b) {
            ffVar = (ff) this.f898c.remove(str);
        }
        return ffVar;
    }

    /* renamed from: a */
    void m1054a(String str, long j, String str2) {
        ff ffVar = new ff(this, str2, j);
        synchronized (this.f897b) {
            this.f898c.put(str, ffVar);
        }
    }
}
