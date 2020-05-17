package com.applovin.impl.p000a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.fl;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

/* renamed from: com.applovin.impl.a.i */
public class C0458i {
    /* renamed from: a */
    private C0459j f120a;
    /* renamed from: b */
    private Uri f121b;
    /* renamed from: c */
    private String f122c;

    private C0458i() {
    }

    /* renamed from: a */
    static C0458i m212a(fl flVar, C0458i c0458i, AppLovinSdk appLovinSdk) {
        if (flVar == null) {
            throw new IllegalArgumentException("Unable to create non-video resource. No node specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to create non-video resource. No sdk specified.");
        } else {
            C0458i c0458i2 = c0458i != null ? c0458i : new C0458i();
            try {
                if (c0458i2.f121b != null || AppLovinSdkUtils.isValidString(c0458i2.f122c)) {
                    return c0458i2;
                }
                String a = C0458i.m213a(flVar, "StaticResource");
                if (URLUtil.isValidUrl(a)) {
                    c0458i2.f121b = Uri.parse(a);
                    c0458i2.f120a = C0459j.STATIC;
                    return c0458i2;
                }
                a = C0458i.m213a(flVar, "IFrameResource");
                if (AppLovinSdkUtils.isValidString(a)) {
                    c0458i2.f120a = C0459j.IFRAME;
                    if (URLUtil.isValidUrl(a)) {
                        c0458i2.f121b = Uri.parse(a);
                        return c0458i2;
                    }
                    c0458i2.f122c = a;
                    return c0458i2;
                }
                a = C0458i.m213a(flVar, "HTMLResource");
                if (!AppLovinSdkUtils.isValidString(a)) {
                    return c0458i2;
                }
                c0458i2.f120a = C0459j.HTML;
                if (URLUtil.isValidUrl(a)) {
                    c0458i2.f121b = Uri.parse(a);
                    return c0458i2;
                }
                c0458i2.f122c = a;
                return c0458i2;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo2290e("VastNonVideoResource", "Error occurred while initializing", th);
                return null;
            }
        }
    }

    /* renamed from: a */
    private static String m213a(fl flVar, String str) {
        fl b = flVar.m1098b(str);
        return b != null ? b.m1101c() : null;
    }

    /* renamed from: a */
    public C0459j m214a() {
        return this.f120a;
    }

    /* renamed from: a */
    public void m215a(Uri uri) {
        this.f121b = uri;
    }

    /* renamed from: a */
    public void m216a(String str) {
        this.f122c = str;
    }

    /* renamed from: b */
    public Uri m217b() {
        return this.f121b;
    }

    /* renamed from: c */
    public String m218c() {
        return this.f122c;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0458i)) {
            return false;
        }
        C0458i c0458i = (C0458i) obj;
        if (this.f120a != c0458i.f120a) {
            return false;
        }
        if (this.f121b != null) {
            if (!this.f121b.equals(c0458i.f121b)) {
                return false;
            }
        } else if (c0458i.f121b != null) {
            return false;
        }
        if (this.f122c != null) {
            z = this.f122c.equals(c0458i.f122c);
        } else if (c0458i.f122c != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f121b != null ? this.f121b.hashCode() : 0) + ((this.f120a != null ? this.f120a.hashCode() : 0) * 31)) * 31;
        if (this.f122c != null) {
            i = this.f122c.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "VastNonVideoResource{type=" + this.f120a + ", resourceUri=" + this.f121b + ", resourceContents='" + this.f122c + '\'' + '}';
    }
}
