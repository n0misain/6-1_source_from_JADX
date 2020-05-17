package com.applovin.impl.sdk;

import java.util.Map;

public class df {
    /* renamed from: a */
    private final String f728a;
    /* renamed from: b */
    private final Map f729b;
    /* renamed from: c */
    private final long f730c;
    /* renamed from: d */
    private final String f731d;

    public df(String str, Map map, long j, String str2) {
        this.f728a = str;
        this.f729b = map;
        this.f730c = j;
        this.f731d = str2;
    }

    /* renamed from: a */
    public String m777a() {
        return this.f728a;
    }

    /* renamed from: b */
    public Map m778b() {
        return this.f729b;
    }

    /* renamed from: c */
    public long m779c() {
        return this.f730c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r7) {
        /*
        r6 = this;
        r0 = 1;
        r1 = 0;
        if (r6 != r7) goto L_0x0006;
    L_0x0004:
        r1 = r0;
    L_0x0005:
        return r1;
    L_0x0006:
        if (r7 == 0) goto L_0x0005;
    L_0x0008:
        r2 = r6.getClass();
        r3 = r7.getClass();
        if (r2 != r3) goto L_0x0005;
    L_0x0012:
        r7 = (com.applovin.impl.sdk.df) r7;
        r2 = r6.f730c;
        r4 = r7.f730c;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 != 0) goto L_0x0005;
    L_0x001c:
        r2 = r6.f728a;
        if (r2 == 0) goto L_0x0049;
    L_0x0020:
        r2 = r6.f728a;
        r3 = r7.f728a;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x002a:
        r2 = r6.f729b;
        if (r2 == 0) goto L_0x004e;
    L_0x002e:
        r2 = r6.f729b;
        r3 = r7.f729b;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0038:
        r2 = r6.f731d;
        if (r2 == 0) goto L_0x0053;
    L_0x003c:
        r2 = r6.f731d;
        r3 = r7.f731d;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0047;
    L_0x0046:
        r0 = r1;
    L_0x0047:
        r1 = r0;
        goto L_0x0005;
    L_0x0049:
        r2 = r7.f728a;
        if (r2 == 0) goto L_0x002a;
    L_0x004d:
        goto L_0x0005;
    L_0x004e:
        r2 = r7.f729b;
        if (r2 == 0) goto L_0x0038;
    L_0x0052:
        goto L_0x0005;
    L_0x0053:
        r2 = r7.f731d;
        if (r2 != 0) goto L_0x0046;
    L_0x0057:
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.df.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.f729b != null ? this.f729b.hashCode() : 0) + ((this.f728a != null ? this.f728a.hashCode() : 0) * 31)) * 31) + ((int) (this.f730c ^ (this.f730c >>> 32)))) * 31;
        if (this.f731d != null) {
            i = this.f731d.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "SdkEvent{eventType='" + this.f728a + '\'' + ", parameters=" + this.f729b + ", creationTsMillis=" + this.f730c + ", uniqueIdentifier='" + this.f731d + '\'' + '}';
    }
}
