package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.Map;

public class cy {
    /* renamed from: d */
    private static cy f704d;
    /* renamed from: a */
    private final Map f705a = new HashMap(1);
    /* renamed from: b */
    private final Map f706b = new HashMap(1);
    /* renamed from: c */
    private final Object f707c = new Object();

    private cy() {
    }

    /* renamed from: a */
    public static synchronized cy m743a() {
        cy cyVar;
        synchronized (cy.class) {
            if (f704d == null) {
                f704d = new cy();
            }
            cyVar = f704d;
        }
        return cyVar;
    }

    /* renamed from: a */
    public Map m744a(ae aeVar) {
        Map map;
        synchronized (this.f707c) {
            map = (Map) this.f706b.remove(aeVar);
        }
        return map;
    }

    /* renamed from: a */
    public void m745a(ae aeVar, String str) {
        synchronized (this.f707c) {
            this.f705a.put(aeVar, str);
        }
    }

    /* renamed from: a */
    public void m746a(ae aeVar, Map map) {
        synchronized (this.f707c) {
            this.f706b.put(aeVar, map);
        }
    }

    /* renamed from: b */
    public String m747b(ae aeVar) {
        String str;
        synchronized (this.f707c) {
            str = (String) this.f705a.remove(aeVar);
        }
        return str;
    }
}
