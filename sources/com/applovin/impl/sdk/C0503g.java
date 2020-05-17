package com.applovin.impl.sdk;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.adview.C0493y;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdkUtils;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.g */
public final class C0503g extends ae {
    public C0503g(C0505i c0505i, JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdkImpl appLovinSdkImpl) {
        super(c0505i, jSONObject, jSONObject2, appLovinSdkImpl);
    }

    /* renamed from: N */
    private String m1116N() {
        return bt.m615a(this.b, "stream_url", "", this.d);
    }

    /* renamed from: a */
    private C0504h m1117a(String str, AppLovinAdType appLovinAdType, boolean z) {
        if (AppLovinSdkUtils.isValidString(str)) {
            if (str.contains("activity") || str.contains("view_controller")) {
                return C0504h.ACTIVITY;
            }
            if (str.contains("dialog")) {
                return C0504h.DIALOG;
            }
        }
        return (z || appLovinAdType.equals(AppLovinAdType.INCENTIVIZED)) ? C0504h.ACTIVITY : C0504h.DIALOG;
    }

    /* renamed from: a */
    public String mo2133a() {
        return bt.m615a(this.b, "html", null, this.d);
    }

    /* renamed from: a */
    public void m1119a(Uri uri) {
        try {
            this.b.put("video", uri.toString());
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void m1120a(String str) {
        try {
            this.b.put("html", str);
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public boolean mo2134b() {
        return this.b.has("stream_url");
    }

    /* renamed from: c */
    public void m1122c() {
        this.b.remove("stream_url");
    }

    /* renamed from: d */
    public Uri mo2135d() {
        String N = m1116N();
        if (AppLovinSdkUtils.isValidString(N)) {
            return Uri.parse(N);
        }
        N = m1124e();
        return AppLovinSdkUtils.isValidString(N) ? Uri.parse(N) : null;
    }

    /* renamed from: e */
    public String m1124e() {
        return bt.m615a(this.b, "video", "", this.d);
    }

    /* renamed from: f */
    public Uri mo2136f() {
        String a = bt.m615a(this.b, "click_url", "", this.d);
        return URLUtil.isValidUrl(a) ? Uri.parse(a) : null;
    }

    /* renamed from: g */
    public float m1126g() {
        return bt.m612a(this.b, "mraid_close_delay_graphic", 0.0f, this.d);
    }

    /* renamed from: h */
    public boolean m1127h() {
        return bt.m619a(this.b, "close_button_graphic_hidden", false, this.d);
    }

    /* renamed from: i */
    public C0504h m1128i() {
        return m1117a(bt.m615a(this.b, "presentation_mode", "", this.d), getType(), isVideoAd());
    }

    public boolean isVideoAd() {
        return mo2135d() != null;
    }

    /* renamed from: j */
    public boolean m1129j() {
        return this.b.has("close_button_expandable_hidden") ? bt.m619a(this.b, "close_button_expandable_hidden", false, this.d) : true;
    }

    /* renamed from: k */
    public C0493y m1130k() {
        return m137a(bt.m613a(this.b, "expandable_style", C0493y.Invisible.ordinal(), this.d));
    }
}
