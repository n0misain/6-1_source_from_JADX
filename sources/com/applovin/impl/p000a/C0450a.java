package com.applovin.impl.p000a;

import android.net.Uri;
import com.applovin.impl.sdk.ae;
import com.applovin.impl.sdk.bt;
import com.applovin.impl.sdk.dn;
import com.applovin.impl.sdk.fk;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.applovin.impl.a.a */
public class C0450a extends ae {
    /* renamed from: e */
    private final String f66e;
    /* renamed from: f */
    private final String f67f;
    /* renamed from: g */
    private final C0460k f68g;
    /* renamed from: h */
    private final C0464o f69h;
    /* renamed from: i */
    private final C0455f f70i;
    /* renamed from: j */
    private final Set f71j;
    /* renamed from: k */
    private final Set f72k;

    private C0450a(C0452c c0452c) {
        super(c0452c.f73a, c0452c.f74b, c0452c.f75c, c0452c.f76d);
        this.f66e = c0452c.f77e;
        this.f68g = c0452c.f79g;
        this.f67f = c0452c.f78f;
        this.f69h = c0452c.f80h;
        this.f70i = c0452c.f81i;
        this.f71j = c0452c.f82j;
        this.f72k = c0452c.f83k;
    }

    /* renamed from: a */
    private Set m160a(C0453d c0453d, String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return Collections.emptySet();
        }
        Map d = (c0453d != C0453d.VIDEO || this.f69h == null) ? (c0453d != C0453d.COMPANION_AD || this.f70i == null) ? null : this.f70i.m204d() : this.f69h.m251d();
        Set hashSet = new HashSet();
        if (!(d == null || d.isEmpty())) {
            for (Object obj : strArr) {
                if (d.containsKey(obj)) {
                    hashSet.addAll((Collection) d.get(obj));
                }
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    /* renamed from: g */
    public static C0452c m161g() {
        return new C0452c();
    }

    /* renamed from: h */
    private C0466q m162h() {
        C0466q[] a = C0466q.m253a();
        int af = new dn(this.d).af();
        return (af < 0 || af >= a.length) ? C0466q.UNSPECIFIED : a[af];
    }

    /* renamed from: i */
    private Set m163i() {
        return this.f69h != null ? this.f69h.m250c() : Collections.emptySet();
    }

    /* renamed from: j */
    private Set m164j() {
        return this.f70i != null ? this.f70i.m203c() : Collections.emptySet();
    }

    /* renamed from: a */
    public C0464o mo2133a() {
        return this.f69h;
    }

    /* renamed from: a */
    public String m166a(String str) {
        try {
            String a = bt.m615a(this.b, "vimp_url", "", this.d);
            if (AppLovinSdkUtils.isValidString(a)) {
                a = a.replace("{CLCODE}", fk.m1093c(mo2130l()));
                if (AppLovinSdkUtils.isValidString(str)) {
                    a = a.replace("{PLACEMENT}", fk.m1093c(str));
                } else {
                    a = a.replace("{PLACEMENT}", "");
                }
                return a.toString();
            }
        } catch (Throwable th) {
            this.d.getLogger().mo2290e("VastAd", "Unable to create VAST impression URL", th);
        }
        return "";
    }

    /* renamed from: a */
    public Set m167a(C0454e c0454e, String str) {
        return m168a(c0454e, new String[]{str});
    }

    /* renamed from: a */
    public Set m168a(C0454e c0454e, String[] strArr) {
        this.d.getLogger().mo2288d("VastAd", "Retrieving trackers of type '" + c0454e + "' and events '" + strArr + "'...");
        if (c0454e == C0454e.IMPRESSION) {
            return this.f71j;
        }
        if (c0454e == C0454e.VIDEO_CLICK) {
            return m163i();
        }
        if (c0454e == C0454e.COMPANION_CLICK) {
            return m164j();
        }
        if (c0454e == C0454e.VIDEO) {
            return m160a(C0453d.VIDEO, strArr);
        }
        if (c0454e == C0454e.COMPANION) {
            return m160a(C0453d.COMPANION_AD, strArr);
        }
        if (c0454e == C0454e.ERROR) {
            return this.f72k;
        }
        this.d.getLogger().mo2289e("VastAd", "Failed to retrieve trackers of invalid type '" + c0454e + "' and events '" + strArr + "'");
        return Collections.emptySet();
    }

    /* renamed from: a */
    public boolean m169a(AppLovinSdk appLovinSdk) {
        return bt.m619a(this.b, "cache_companion_ad", true, appLovinSdk);
    }

    /* renamed from: b */
    public boolean mo2134b() {
        C0467r c = m172c();
        return c != null ? c.m259c() : false;
    }

    /* renamed from: b */
    public boolean m171b(AppLovinSdk appLovinSdk) {
        return bt.m619a(this.b, "cache_video", true, appLovinSdk);
    }

    /* renamed from: c */
    public C0467r m172c() {
        return this.f69h != null ? this.f69h.m247a(m162h()) : null;
    }

    /* renamed from: d */
    public Uri mo2135d() {
        C0467r c = m172c();
        return c != null ? c.m258b() : null;
    }

    /* renamed from: e */
    public C0455f m174e() {
        return this.f70i;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0450a) || !super.equals(obj)) {
            return false;
        }
        C0450a c0450a = (C0450a) obj;
        if (this.f66e != null) {
            if (!this.f66e.equals(c0450a.f66e)) {
                return false;
            }
        } else if (c0450a.f66e != null) {
            return false;
        }
        if (this.f67f != null) {
            if (!this.f67f.equals(c0450a.f67f)) {
                return false;
            }
        } else if (c0450a.f67f != null) {
            return false;
        }
        if (this.f68g != null) {
            if (!this.f68g.equals(c0450a.f68g)) {
                return false;
            }
        } else if (c0450a.f68g != null) {
            return false;
        }
        if (this.f69h != null) {
            if (!this.f69h.equals(c0450a.f69h)) {
                return false;
            }
        } else if (c0450a.f69h != null) {
            return false;
        }
        if (this.f70i != null) {
            if (!this.f70i.equals(c0450a.f70i)) {
                return false;
            }
        } else if (c0450a.f70i != null) {
            return false;
        }
        if (this.f71j != null) {
            if (!this.f71j.equals(c0450a.f71j)) {
                return false;
            }
        } else if (c0450a.f71j != null) {
            return false;
        }
        if (this.f72k != null) {
            z = this.f72k.equals(c0450a.f72k);
        } else if (c0450a.f72k != null) {
            z = false;
        }
        return z;
    }

    /* renamed from: f */
    public Uri mo2136f() {
        return this.f69h != null ? this.f69h.m249b() : null;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f71j != null ? this.f71j.hashCode() : 0) + (((this.f70i != null ? this.f70i.hashCode() : 0) + (((this.f69h != null ? this.f69h.hashCode() : 0) + (((this.f68g != null ? this.f68g.hashCode() : 0) + (((this.f67f != null ? this.f67f.hashCode() : 0) + (((this.f66e != null ? this.f66e.hashCode() : 0) + (super.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f72k != null) {
            i = this.f72k.hashCode();
        }
        return hashCode + i;
    }

    public boolean isVideoAd() {
        if (this.f69h == null) {
            return false;
        }
        List a = this.f69h.m248a();
        return a != null && a.size() > 0;
    }

    public String toString() {
        return "VastAd{title='" + this.f66e + '\'' + ", adDescription='" + this.f67f + '\'' + ", systemInfo=" + this.f68g + ", videoCreative=" + this.f69h + ", companionAd=" + this.f70i + ", impressionTrackers=" + this.f71j + ", errorTrackers=" + this.f72k + '}';
    }

    /* renamed from: x */
    public boolean mo2137x() {
        return mo2136f() != null;
    }
}
