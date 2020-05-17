package com.applovin.impl.sdk;

public class dl implements Comparable {
    /* renamed from: a */
    private static int f798a = 0;
    /* renamed from: b */
    private final int f799b;
    /* renamed from: c */
    private final String f800c;
    /* renamed from: d */
    private final Object f801d;

    private dl(String str, Object obj) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified");
        } else if (obj == null) {
            throw new IllegalArgumentException("No default value specified");
        } else {
            this.f800c = str;
            this.f801d = obj;
            this.f799b = f798a;
            f798a++;
        }
    }

    /* renamed from: a */
    public int m810a() {
        return this.f799b;
    }

    /* renamed from: a */
    Object m811a(Object obj) {
        return this.f801d.getClass().cast(obj);
    }

    /* renamed from: b */
    public String m812b() {
        return this.f800c;
    }

    /* renamed from: c */
    public Object m813c() {
        return this.f801d;
    }

    public int compareTo(Object obj) {
        return (obj == null || !(obj instanceof dl)) ? 0 : this.f800c.compareTo(((dl) obj).m812b());
    }
}
