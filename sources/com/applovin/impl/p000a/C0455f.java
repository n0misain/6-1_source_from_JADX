package com.applovin.impl.p000a;

import android.net.Uri;
import com.applovin.impl.sdk.fk;
import com.applovin.impl.sdk.fl;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: com.applovin.impl.a.f */
public class C0455f {
    /* renamed from: a */
    private int f94a;
    /* renamed from: b */
    private int f95b;
    /* renamed from: c */
    private Uri f96c;
    /* renamed from: d */
    private C0458i f97d;
    /* renamed from: e */
    private Set f98e = new HashSet();
    /* renamed from: f */
    private Map f99f = new HashMap();

    private C0455f() {
    }

    /* renamed from: a */
    public static C0455f m200a(fl flVar, C0455f c0455f, AppLovinSdk appLovinSdk) {
        if (flVar == null) {
            throw new IllegalArgumentException("Unable to create companion ad. No node specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to create companion ad. No sdk specified.");
        } else {
            C0455f c0455f2 = c0455f != null ? c0455f : new C0455f();
            try {
                if (c0455f2.f94a == 0 && c0455f2.f95b == 0) {
                    int e = fk.m1095e((String) flVar.m1099b().get(SettingsJsonConstants.ICON_WIDTH_KEY));
                    int e2 = fk.m1095e((String) flVar.m1099b().get(SettingsJsonConstants.ICON_HEIGHT_KEY));
                    if (e > 0 && e2 > 0) {
                        c0455f2.f94a = e;
                        c0455f2.f95b = e2;
                    }
                }
                c0455f2.f97d = C0458i.m212a(flVar, c0455f2.f97d, appLovinSdk);
                if (c0455f2.f96c == null) {
                    fl b = flVar.m1098b("CompanionClickThrough");
                    if (b != null) {
                        String c = b.m1101c();
                        if (AppLovinSdkUtils.isValidString(c)) {
                            c0455f2.f96c = Uri.parse(c);
                        }
                    }
                }
                C0463n.m235a(flVar.m1097a("CompanionClickTracking"), c0455f2.f98e, appLovinSdk);
                C0463n.m234a(flVar, c0455f2.f99f, appLovinSdk);
                return c0455f2;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo2290e("VastCompanionAd", "Error occurred while initializing", th);
                return null;
            }
        }
    }

    /* renamed from: a */
    public Uri m201a() {
        return this.f96c;
    }

    /* renamed from: b */
    public C0458i m202b() {
        return this.f97d;
    }

    /* renamed from: c */
    public Set m203c() {
        return this.f98e;
    }

    /* renamed from: d */
    public Map m204d() {
        return this.f99f;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0455f)) {
            return false;
        }
        C0455f c0455f = (C0455f) obj;
        if (this.f94a != c0455f.f94a || this.f95b != c0455f.f95b) {
            return false;
        }
        if (this.f96c != null) {
            if (!this.f96c.equals(c0455f.f96c)) {
                return false;
            }
        } else if (c0455f.f96c != null) {
            return false;
        }
        if (this.f97d != null) {
            if (!this.f97d.equals(c0455f.f97d)) {
                return false;
            }
        } else if (c0455f.f97d != null) {
            return false;
        }
        if (this.f98e != null) {
            if (!this.f98e.equals(c0455f.f98e)) {
                return false;
            }
        } else if (c0455f.f98e != null) {
            return false;
        }
        if (this.f99f != null) {
            z = this.f99f.equals(c0455f.f99f);
        } else if (c0455f.f99f != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f98e != null ? this.f98e.hashCode() : 0) + (((this.f97d != null ? this.f97d.hashCode() : 0) + (((this.f96c != null ? this.f96c.hashCode() : 0) + (((this.f94a * 31) + this.f95b) * 31)) * 31)) * 31)) * 31;
        if (this.f99f != null) {
            i = this.f99f.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "VastCompanionAd{width=" + this.f94a + ", height=" + this.f95b + ", destinationUri=" + this.f96c + ", nonVideoResource=" + this.f97d + ", clickTrackers=" + this.f98e + ", eventTrackers=" + this.f99f + '}';
    }
}
