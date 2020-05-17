package com.applovin.impl.sdk;

import java.util.Map;

final class db {
    /* renamed from: a */
    private int f719a;
    /* renamed from: b */
    private String f720b;
    /* renamed from: c */
    private String f721c;
    /* renamed from: d */
    private Map f722d;

    db(String str, Map map, int i, String str2) {
        this.f719a = i;
        this.f722d = map;
        this.f720b = str;
        this.f721c = str2;
    }

    /* renamed from: a */
    public int m766a() {
        return this.f719a;
    }

    /* renamed from: a */
    public void m767a(int i) {
        this.f719a = i;
    }

    /* renamed from: b */
    public String m768b() {
        return this.f720b;
    }

    /* renamed from: c */
    public String m769c() {
        return this.f721c;
    }

    /* renamed from: d */
    public Map m770d() {
        return this.f722d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        db dbVar = (db) obj;
        if (this.f719a != dbVar.f719a) {
            return false;
        }
        if (this.f720b == null ? dbVar.f720b != null : !this.f720b.equals(dbVar.f720b)) {
            return false;
        }
        if (this.f721c == null ? dbVar.f721c != null : !this.f721c.equals(dbVar.f721c)) {
            return false;
        }
        if (this.f722d != null) {
            if (this.f722d.equals(dbVar.f722d)) {
                return true;
            }
        } else if (dbVar.f722d == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f721c != null ? this.f721c.hashCode() : 0) + (((this.f720b != null ? this.f720b.hashCode() : 0) + (this.f719a * 31)) * 31)) * 31;
        if (this.f722d != null) {
            i = this.f722d.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "PostbackRequest{attemptNumber=" + this.f719a + ", targetUrl='" + this.f720b + '\'' + ", backupUrl='" + this.f721c + '\'' + ", requestBody=" + this.f722d + '}';
    }
}
