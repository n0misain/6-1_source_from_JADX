package com.applovin.impl.p000a;

import com.applovin.impl.sdk.fk;
import com.applovin.impl.sdk.fl;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.concurrent.TimeUnit;

/* renamed from: com.applovin.impl.a.l */
public class C0461l {
    /* renamed from: a */
    private String f130a;
    /* renamed from: b */
    private String f131b;
    /* renamed from: c */
    private String f132c;
    /* renamed from: d */
    private long f133d = -1;
    /* renamed from: e */
    private int f134e = -1;

    private C0461l() {
    }

    /* renamed from: a */
    private static int m220a(String str) {
        return "start".equalsIgnoreCase(str) ? 0 : "firstQuartile".equalsIgnoreCase(str) ? 25 : "midpoint".equalsIgnoreCase(str) ? 50 : "thirdQuartile".equalsIgnoreCase(str) ? 75 : "complete".equalsIgnoreCase(str) ? 95 : -1;
    }

    /* renamed from: a */
    public static C0461l m221a(fl flVar, AppLovinSdk appLovinSdk) {
        if (flVar == null) {
            throw new IllegalArgumentException("Unable to create tracker. No node specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to create tracker. No sdk specified.");
        } else {
            try {
                String c = flVar.m1101c();
                if (AppLovinSdkUtils.isValidString(c)) {
                    C0461l c0461l = new C0461l();
                    c0461l.f132c = c;
                    c0461l.f130a = (String) flVar.m1099b().get("id");
                    c0461l.f131b = (String) flVar.m1099b().get("event");
                    c0461l.f134e = C0461l.m220a(c0461l.m222a());
                    c = (String) flVar.m1099b().get("offset");
                    if (AppLovinSdkUtils.isValidString(c)) {
                        c = c.trim();
                        if (c.contains("%")) {
                            c0461l.f134e = fk.m1095e(c.substring(0, c.length() - 1));
                        } else if (c.contains(":")) {
                            String[] split = c.split(":");
                            int length = split.length;
                            if (length > 0) {
                                long j = 0;
                                for (int i = length - 1; i >= 0; i--) {
                                    String str = split[i];
                                    if (fk.m1094d(str)) {
                                        int parseInt = Integer.parseInt(str);
                                        if (i == length - 1) {
                                            j += (long) parseInt;
                                        } else if (i == length - 2) {
                                            j += TimeUnit.MINUTES.toSeconds((long) parseInt);
                                        } else if (i == length - 3) {
                                            j += TimeUnit.HOURS.toSeconds((long) parseInt);
                                        }
                                    }
                                }
                                c0461l.f133d = j;
                                c0461l.f134e = -1;
                            }
                        } else {
                            appLovinSdk.getLogger().mo2289e("VastTracker", "Unable to parse time offset from rawOffsetString = " + c);
                        }
                    }
                    return c0461l;
                }
                appLovinSdk.getLogger().mo2289e("VastTracker", "Unable to create tracker. Could not find URL.");
                return null;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo2290e("VastTracker", "Error occurred while initializing", th);
            }
        }
    }

    /* renamed from: a */
    public String m222a() {
        return this.f131b;
    }

    /* renamed from: a */
    public boolean m223a(long j, int i) {
        return (((this.f133d > 0 ? 1 : (this.f133d == 0 ? 0 : -1)) >= 0) && (j >= this.f133d)) ? true : (this.f134e >= 0) && (i >= this.f134e);
    }

    /* renamed from: b */
    public String m224b() {
        return this.f132c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0461l)) {
            return false;
        }
        C0461l c0461l = (C0461l) obj;
        if (this.f133d != c0461l.f133d || this.f134e != c0461l.f134e) {
            return false;
        }
        if (this.f130a != null) {
            if (!this.f130a.equals(c0461l.f130a)) {
                return false;
            }
        } else if (c0461l.f130a != null) {
            return false;
        }
        if (this.f131b != null) {
            if (!this.f131b.equals(c0461l.f131b)) {
                return false;
            }
        } else if (c0461l.f131b != null) {
            return false;
        }
        return this.f132c.equals(c0461l.f132c);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f130a != null ? this.f130a.hashCode() : 0) * 31;
        if (this.f131b != null) {
            i = this.f131b.hashCode();
        }
        return ((((((hashCode + i) * 31) + this.f132c.hashCode()) * 31) + ((int) (this.f133d ^ (this.f133d >>> 32)))) * 31) + this.f134e;
    }

    public String toString() {
        return "VastTracker{identifier='" + this.f130a + '\'' + ", event='" + this.f131b + '\'' + ", uriString='" + this.f132c + '\'' + ", offsetSeconds=" + this.f133d + ", offsetPercent=" + this.f134e + '}';
    }
}
