package com.applovin.impl.adview;

class ac {
    /* renamed from: a */
    private final String f201a;
    /* renamed from: b */
    private final ab f202b;
    /* renamed from: c */
    private final long f203c;

    private ac(String str, long j, ab abVar) {
        this.f201a = str;
        this.f203c = j;
        this.f202b = abVar;
    }

    /* renamed from: a */
    private String m288a() {
        return this.f201a;
    }

    /* renamed from: b */
    private long m290b() {
        return this.f203c;
    }

    /* renamed from: c */
    private ab m293c() {
        return this.f202b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ac)) {
            return false;
        }
        ac acVar = (ac) obj;
        return this.f201a != null ? this.f201a.equalsIgnoreCase(acVar.f201a) : acVar.f201a == null;
    }

    public int hashCode() {
        return this.f201a != null ? this.f201a.hashCode() : 0;
    }

    public String toString() {
        return "CountdownProxy{identifier='" + this.f201a + '\'' + ", countdownStepMillis=" + this.f203c + '}';
    }
}
