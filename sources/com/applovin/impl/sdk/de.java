package com.applovin.impl.sdk;

import java.util.LinkedList;
import java.util.Queue;

class de {
    /* renamed from: a */
    private final int f725a;
    /* renamed from: b */
    private final Queue f726b;
    /* renamed from: c */
    private final Object f727c;

    de(int i) {
        if (i > 10) {
            i = 10;
        }
        this.f725a = i;
        this.f726b = new LinkedList();
        this.f727c = new Object();
    }

    /* renamed from: a */
    int m771a() {
        int size;
        synchronized (this.f727c) {
            size = this.f726b.size();
        }
        return size;
    }

    /* renamed from: a */
    void m772a(bu buVar) {
        synchronized (this.f727c) {
            if (!m774c()) {
                this.f726b.offer(buVar);
            }
        }
    }

    /* renamed from: b */
    int m773b() {
        return this.f725a;
    }

    /* renamed from: c */
    boolean m774c() {
        boolean z;
        synchronized (this.f727c) {
            z = m771a() >= this.f725a;
        }
        return z;
    }

    /* renamed from: d */
    boolean m775d() {
        boolean z;
        synchronized (this.f727c) {
            z = m771a() == 0;
        }
        return z;
    }

    /* renamed from: e */
    bu m776e() {
        try {
            bu buVar;
            synchronized (this.f727c) {
                buVar = !m775d() ? (bu) this.f726b.poll() : null;
            }
            return buVar;
        } catch (Exception e) {
            return null;
        }
    }
}
