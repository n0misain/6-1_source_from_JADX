package com.applovin.impl.p000a;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.applovin.impl.sdk.dn;
import com.applovin.impl.sdk.fk;
import com.applovin.impl.sdk.fl;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* renamed from: com.applovin.impl.a.o */
public class C0464o {
    /* renamed from: a */
    private List f138a = Collections.EMPTY_LIST;
    /* renamed from: b */
    private List f139b = Collections.EMPTY_LIST;
    /* renamed from: c */
    private int f140c;
    /* renamed from: d */
    private Uri f141d;
    /* renamed from: e */
    private final Set f142e = new HashSet();
    /* renamed from: f */
    private final Map f143f = new HashMap();

    private C0464o() {
    }

    private C0464o(C0456g c0456g) {
        this.f139b = c0456g.m210f();
    }

    /* renamed from: a */
    private static int m244a(String str, AppLovinSdk appLovinSdk) {
        try {
            if (!AppLovinSdkUtils.isValidString(str)) {
                return 0;
            }
            String[] split = str.split(":");
            if (split.length != 3) {
                return 0;
            }
            int e = fk.m1095e(split[0]);
            int e2 = fk.m1095e(split[1]);
            int e3 = fk.m1095e(split[2]);
            return (int) (((long) e3) + (TimeUnit.MINUTES.toSeconds((long) e2) + TimeUnit.HOURS.toSeconds((long) e)));
        } catch (Throwable th) {
            appLovinSdk.getLogger().mo2289e("VastVideoCreative", "Unable to parse duration from \"" + str + "\"");
            return 0;
        }
    }

    /* renamed from: a */
    public static C0464o m245a(fl flVar, C0464o c0464o, C0456g c0456g, AppLovinSdk appLovinSdk) {
        if (flVar == null) {
            throw new IllegalArgumentException("Unable to create video creative. No node specified.");
        } else if (c0456g == null) {
            throw new IllegalArgumentException("Unable to create video creative. No context specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to create video creative. No sdk specified.");
        } else {
            C0464o c0464o2 = c0464o != null ? c0464o : new C0464o(c0456g);
            try {
                fl b;
                if (c0464o2.f140c == 0) {
                    b = flVar.m1098b("Duration");
                    if (b != null) {
                        int a = C0464o.m244a(b.m1101c(), appLovinSdk);
                        if (a > 0) {
                            c0464o2.f140c = a;
                        }
                    }
                }
                b = flVar.m1098b("MediaFiles");
                if (b != null) {
                    List a2 = C0464o.m246a(b, appLovinSdk);
                    if (a2 != null && a2.size() > 0) {
                        if (c0464o2.f138a != null) {
                            a2.addAll(c0464o2.f138a);
                        }
                        c0464o2.f138a = a2;
                    }
                }
                b = flVar.m1098b("VideoClicks");
                if (b != null) {
                    if (c0464o2.f141d == null) {
                        fl b2 = b.m1098b("ClickThrough");
                        if (b2 != null) {
                            String c = b2.m1101c();
                            if (AppLovinSdkUtils.isValidString(c)) {
                                c0464o2.f141d = Uri.parse(c);
                            }
                        }
                    }
                    C0463n.m235a(b.m1097a("ClickTracking"), c0464o2.f142e, appLovinSdk);
                }
                C0463n.m234a(flVar, c0464o2.f143f, appLovinSdk);
                return c0464o2;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo2290e("VastVideoCreative", "Error occurred while initializing", th);
                return null;
            }
        }
    }

    /* renamed from: a */
    private static List m246a(fl flVar, AppLovinSdk appLovinSdk) {
        List<fl> a = flVar.m1097a("MediaFile");
        List arrayList = new ArrayList(a.size());
        dn dnVar = new dn(appLovinSdk);
        List asList = Arrays.asList(dnVar.ah().split(","));
        List asList2 = Arrays.asList(dnVar.ai().split(","));
        for (fl a2 : a) {
            C0467r a3 = C0467r.m254a(a2, appLovinSdk);
            if (a3 != null) {
                try {
                    String d = a3.m260d();
                    if (!AppLovinSdkUtils.isValidString(d) || asList.contains(d)) {
                        if (dnVar.aj()) {
                            d = MimeTypeMap.getFileExtensionFromUrl(a3.m258b().toString());
                            if (AppLovinSdkUtils.isValidString(d) && !asList2.contains(d)) {
                                arrayList.add(a3);
                            }
                        }
                        appLovinSdk.getLogger().mo2294w("VastVideoCreative", "Video file not supported: " + a3);
                    } else {
                        arrayList.add(a3);
                    }
                } catch (Throwable th) {
                    appLovinSdk.getLogger().mo2290e("VastVideoCreative", "Failed to validate vidoe file: " + a3, th);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public C0467r m247a(C0466q c0466q) {
        if (this.f138a == null || this.f138a.size() == 0) {
            return null;
        }
        List arrayList = new ArrayList(3);
        for (String str : this.f139b) {
            for (C0467r c0467r : this.f138a) {
                String d = c0467r.m260d();
                if (AppLovinSdkUtils.isValidString(d) && str.equalsIgnoreCase(d)) {
                    arrayList.add(c0467r);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        List list = !arrayList.isEmpty() ? arrayList : this.f138a;
        Collections.sort(list, new C0465p(this));
        return c0466q == C0466q.LOW ? (C0467r) list.get(0) : c0466q == C0466q.MEDIUM ? (C0467r) list.get(list.size() / 2) : (C0467r) list.get(list.size() - 1);
    }

    /* renamed from: a */
    public List m248a() {
        return this.f138a;
    }

    /* renamed from: b */
    public Uri m249b() {
        return this.f141d;
    }

    /* renamed from: c */
    public Set m250c() {
        return this.f142e;
    }

    /* renamed from: d */
    public Map m251d() {
        return this.f143f;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0464o)) {
            return false;
        }
        C0464o c0464o = (C0464o) obj;
        if (this.f140c != c0464o.f140c) {
            return false;
        }
        if (this.f138a != null) {
            if (!this.f138a.equals(c0464o.f138a)) {
                return false;
            }
        } else if (c0464o.f138a != null) {
            return false;
        }
        if (this.f141d != null) {
            if (!this.f141d.equals(c0464o.f141d)) {
                return false;
            }
        } else if (c0464o.f141d != null) {
            return false;
        }
        if (this.f142e != null) {
            if (!this.f142e.equals(c0464o.f142e)) {
                return false;
            }
        } else if (c0464o.f142e != null) {
            return false;
        }
        if (this.f143f != null) {
            z = this.f143f.equals(c0464o.f143f);
        } else if (c0464o.f143f != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f142e != null ? this.f142e.hashCode() : 0) + (((this.f141d != null ? this.f141d.hashCode() : 0) + ((((this.f138a != null ? this.f138a.hashCode() : 0) * 31) + this.f140c) * 31)) * 31)) * 31;
        if (this.f143f != null) {
            i = this.f143f.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "VastVideoCreative{videoFiles=" + this.f138a + ", durationSeconds=" + this.f140c + ", destinationUri=" + this.f141d + ", clickTrackers=" + this.f142e + ", eventTrackers=" + this.f143f + '}';
    }
}
