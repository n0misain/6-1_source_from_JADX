package com.applovin.impl.p000a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.fk;
import com.applovin.impl.sdk.fl;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

/* renamed from: com.applovin.impl.a.r */
public class C0467r {
    /* renamed from: a */
    private Uri f150a;
    /* renamed from: b */
    private Uri f151b;
    /* renamed from: c */
    private C0468s f152c;
    /* renamed from: d */
    private String f153d;
    /* renamed from: e */
    private int f154e;
    /* renamed from: f */
    private int f155f;
    /* renamed from: g */
    private int f156g;

    private C0467r() {
    }

    /* renamed from: a */
    public static C0467r m254a(fl flVar, AppLovinSdk appLovinSdk) {
        if (flVar == null) {
            throw new IllegalArgumentException("Unable to create video file. No node specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to create video file. No sdk specified.");
        } else {
            try {
                String c = flVar.m1101c();
                if (URLUtil.isValidUrl(c)) {
                    Uri parse = Uri.parse(c);
                    C0467r c0467r = new C0467r();
                    c0467r.f150a = parse;
                    c0467r.f151b = parse;
                    c0467r.f156g = fk.m1095e((String) flVar.m1099b().get("bitrate"));
                    c0467r.f152c = C0467r.m255a((String) flVar.m1099b().get("delivery"));
                    c0467r.f155f = fk.m1095e((String) flVar.m1099b().get(SettingsJsonConstants.ICON_HEIGHT_KEY));
                    c0467r.f154e = fk.m1095e((String) flVar.m1099b().get(SettingsJsonConstants.ICON_WIDTH_KEY));
                    c0467r.f153d = ((String) flVar.m1099b().get("type")).toLowerCase();
                    return c0467r;
                }
                appLovinSdk.getLogger().mo2289e("VastVideoFile", "Unable to create video file. Could not find URL.");
                return null;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo2290e("VastVideoFile", "Error occurred while initializing", th);
            }
        }
    }

    /* renamed from: a */
    private static C0468s m255a(String str) {
        if (AppLovinSdkUtils.isValidString(str)) {
            if ("progressive".equalsIgnoreCase(str)) {
                return C0468s.Progressive;
            }
            if ("streaming".equalsIgnoreCase(str)) {
                return C0468s.Streaming;
            }
        }
        return C0468s.Progressive;
    }

    /* renamed from: a */
    public Uri m256a() {
        return this.f150a;
    }

    /* renamed from: a */
    public void m257a(Uri uri) {
        this.f151b = uri;
    }

    /* renamed from: b */
    public Uri m258b() {
        return this.f151b;
    }

    /* renamed from: c */
    public boolean m259c() {
        return this.f152c == C0468s.Streaming;
    }

    /* renamed from: d */
    public String m260d() {
        return this.f153d;
    }

    /* renamed from: e */
    public int m261e() {
        return this.f156g;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0467r)) {
            return false;
        }
        C0467r c0467r = (C0467r) obj;
        if (this.f154e != c0467r.f154e || this.f155f != c0467r.f155f || this.f156g != c0467r.f156g) {
            return false;
        }
        if (this.f150a != null) {
            if (!this.f150a.equals(c0467r.f150a)) {
                return false;
            }
        } else if (c0467r.f150a != null) {
            return false;
        }
        if (this.f151b != null) {
            if (!this.f151b.equals(c0467r.f151b)) {
                return false;
            }
        } else if (c0467r.f151b != null) {
            return false;
        }
        if (this.f152c != c0467r.f152c) {
            return false;
        }
        if (this.f153d != null) {
            z = this.f153d.equals(c0467r.f153d);
        } else if (c0467r.f153d != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f152c != null ? this.f152c.hashCode() : 0) + (((this.f151b != null ? this.f151b.hashCode() : 0) + ((this.f150a != null ? this.f150a.hashCode() : 0) * 31)) * 31)) * 31;
        if (this.f153d != null) {
            i = this.f153d.hashCode();
        }
        return ((((((hashCode + i) * 31) + this.f154e) * 31) + this.f155f) * 31) + this.f156g;
    }

    public String toString() {
        return "VastVideoFile{sourceVideoUri=" + this.f150a + ", videoUri=" + this.f151b + ", deliveryType=" + this.f152c + ", fileType='" + this.f153d + '\'' + ", width=" + this.f154e + ", height=" + this.f155f + ", bitrate=" + this.f156g + '}';
    }
}
