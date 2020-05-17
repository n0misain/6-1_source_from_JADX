package com.applovin.impl.sdk;

import android.graphics.Color;
import android.net.Uri;
import com.applovin.impl.adview.C0493y;
import com.applovin.impl.adview.ce;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Locale;
import org.json.JSONObject;

public abstract class ae extends C0449k {
    public ae(C0505i c0505i, JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdk appLovinSdk) {
        super(c0505i, jSONObject, jSONObject2, appLovinSdk);
    }

    /* renamed from: a */
    private float m122a(AppLovinAdType appLovinAdType, float f, boolean z) {
        return appLovinAdType.equals(AppLovinAdType.INCENTIVIZED) ? 0.5f : (appLovinAdType.equals(AppLovinAdType.REGULAR) && z && f == -1.0f) ? 0.5f : 0.0f;
    }

    /* renamed from: a */
    private C0493y m123a(boolean z) {
        return z ? C0493y.WhiteXOnTransparentGrey : C0493y.WhiteXOnOpaqueBlack;
    }

    /* renamed from: A */
    public boolean m124A() {
        return bt.m619a(this.b, "hide_close_on_exit", false, this.d);
    }

    /* renamed from: B */
    public boolean m125B() {
        return bt.m619a(this.b, "lock_current_orientation", false, this.d);
    }

    /* renamed from: C */
    public int m126C() {
        return bt.m613a(this.b, "countdown_length", 0, this.d);
    }

    /* renamed from: D */
    public int m127D() {
        int parseColor = Color.parseColor("#C8FFFFFF");
        String a = bt.m615a(this.b, "countdown_color", null, this.d);
        if (AppLovinSdkUtils.isValidString(a)) {
            try {
                parseColor = Color.parseColor(a);
            } catch (Throwable th) {
                this.d.getLogger().mo2290e("DirectAd", "Unable to parse countdown color", th);
            }
        }
        return parseColor;
    }

    /* renamed from: E */
    public int m128E() {
        int i = -16777216;
        String a = bt.m615a(this.b, "video_background_color", null, this.d);
        if (AppLovinSdkUtils.isValidString(a)) {
            try {
                i = Color.parseColor(a);
            } catch (Throwable th) {
            }
        }
        return i;
    }

    /* renamed from: F */
    public int m129F() {
        int i = isVideoAd() ? -16777216 : -1157627904;
        String a = bt.m615a(this.b, "graphic_background_color", null, this.d);
        if (AppLovinSdkUtils.isValidString(a)) {
            try {
                i = Color.parseColor(a);
            } catch (Throwable th) {
            }
        }
        return i;
    }

    /* renamed from: G */
    public af m130G() {
        String a = bt.m615a(this.b, "poststitial_dismiss_type", null, this.d);
        if (AppLovinSdkUtils.isValidString(a)) {
            if ("dismiss".equalsIgnoreCase(a)) {
                return af.DISMISS;
            }
            if ("no_dismiss".equalsIgnoreCase(a)) {
                return af.DO_NOT_DISMISS;
            }
        }
        return af.UNSPECIFIED;
    }

    /* renamed from: H */
    public String m131H() {
        return bt.m615a(this.b, "cache_prefix", null, this.d);
    }

    /* renamed from: I */
    public boolean m132I() {
        return bt.m619a(this.b, "progress_bar_enabled", false, this.d);
    }

    /* renamed from: J */
    public int m133J() {
        String a = bt.m615a(this.b, "progress_bar_color", "#C8FFFFFF", this.d);
        int i = 0;
        if (AppLovinSdkUtils.isValidString(a)) {
            try {
                i = Color.parseColor(a);
            } catch (Throwable th) {
            }
        }
        return i;
    }

    /* renamed from: K */
    public boolean m134K() {
        return bt.m619a(this.b, "sanitize_webview", false, this.d);
    }

    /* renamed from: L */
    public Uri m135L() {
        Uri uri = null;
        String a = bt.m615a(this.b, "mute_image", (String) uri, this.d);
        if (AppLovinSdkUtils.isValidString(a)) {
            try {
                uri = Uri.parse(a);
            } catch (Throwable th) {
            }
        }
        return uri;
    }

    /* renamed from: M */
    public Uri m136M() {
        Uri uri = null;
        String a = bt.m615a(this.b, "unmute_image", "", this.d);
        if (AppLovinSdkUtils.isValidString(a)) {
            try {
                uri = Uri.parse(a);
            } catch (Throwable th) {
            }
        }
        return uri;
    }

    /* renamed from: a */
    protected C0493y m137a(int i) {
        return i == 1 ? C0493y.WhiteXOnTransparentGrey : i == 2 ? C0493y.Invisible : C0493y.WhiteXOnOpaqueBlack;
    }

    /* renamed from: a */
    public String m138a(int i, String str, boolean z) {
        String s = m152s();
        return AppLovinSdkUtils.isValidString(s) ? fk.m1084a(str, Uri.parse(s.replace("{CLCODE}", mo2130l())).buildUpon().appendQueryParameter(NativeAdImpl.QUERY_PARAM_VIDEO_PERCENT_VIEWED, Integer.toString(i)).appendQueryParameter("vid_ts", Long.toString(System.currentTimeMillis())).appendQueryParameter("uvs", Boolean.toString(z)).build().toString()) : "";
    }

    /* renamed from: b */
    public String m139b(String str) {
        String a = bt.m615a(this.b, "click_tracking_url", "", this.d);
        return AppLovinSdkUtils.isValidString(a) ? fk.m1084a(str, a.replace("{CLCODE}", mo2130l())) : "";
    }

    /* renamed from: b */
    public void m140b(Uri uri) {
        try {
            this.b.put("mute_image", uri);
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public boolean mo2134b() {
        this.d.getLogger().mo2289e("DirectAd", "Attempting to invoke isVideoStream() from base ad class");
        return false;
    }

    /* renamed from: c */
    public void m142c(Uri uri) {
        try {
            this.b.put("unmute_image", uri);
        } catch (Throwable th) {
        }
    }

    /* renamed from: d */
    public Uri mo2135d() {
        this.d.getLogger().mo2289e("DirectAd", "Attempting to invoke getVideoUri() from base ad class");
        return null;
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    /* renamed from: f */
    public Uri mo2136f() {
        this.d.getLogger().mo2289e("DirectAd", "Attempting to invoke getClickDestinationUri() from base ad class");
        return null;
    }

    public /* bridge */ /* synthetic */ long getAdIdNumber() {
        return super.getAdIdNumber();
    }

    public /* bridge */ /* synthetic */ AppLovinAdSize getSize() {
        return super.getSize();
    }

    public /* bridge */ /* synthetic */ AppLovinAdType getType() {
        return super.getType();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ boolean isVideoAd() {
        return super.isVideoAd();
    }

    /* renamed from: l */
    public /* bridge */ /* synthetic */ String mo2130l() {
        return super.mo2130l();
    }

    /* renamed from: m */
    public /* bridge */ /* synthetic */ C0505i mo2131m() {
        return super.mo2131m();
    }

    /* renamed from: n */
    public ag m147n() {
        String toUpperCase = bt.m615a(this.b, "ad_target", ag.DEFAULT.toString(), this.d).toUpperCase(Locale.ENGLISH);
        return "ACTIVITY_PORTRAIT".equalsIgnoreCase(toUpperCase) ? ag.ACTIVITY_PORTRAIT : "ACTIVITY_LANDSCAPE".equalsIgnoreCase(toUpperCase) ? ag.ACTIVITY_LANDSCAPE : ag.DEFAULT;
    }

    /* renamed from: o */
    public float m148o() {
        return bt.m612a(this.b, "close_delay", 0.0f, this.d);
    }

    /* renamed from: p */
    public float m149p() {
        return bt.m612a(this.b, "close_delay_graphic", m122a(getType(), m148o(), isVideoAd()), this.d);
    }

    /* renamed from: q */
    public C0493y m150q() {
        int a = bt.m613a(this.b, "close_style", -1, this.d);
        return a == -1 ? m123a(isVideoAd()) : m137a(a);
    }

    /* renamed from: r */
    public C0493y m151r() {
        int a = bt.m613a(this.b, "skip_style", -1, this.d);
        return a == -1 ? m150q() : m137a(a);
    }

    /* renamed from: s */
    public String m152s() {
        return bt.m615a(this.b, "video_end_url", "", this.d);
    }

    /* renamed from: t */
    public boolean m153t() {
        return bt.m619a(this.b, "dismiss_on_skip", false, this.d);
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    /* renamed from: u */
    public String m154u() {
        if (this.b.has("video_button_properties")) {
            try {
                return bt.m615a(this.b.getJSONObject("video_button_properties"), "video_button_html", "", this.d);
            } catch (Exception e) {
            }
        }
        return "";
    }

    /* renamed from: v */
    public ce m155v() {
        if (!this.b.has("video_button_properties")) {
            return null;
        }
        try {
            return new ce(this.b.getJSONObject("video_button_properties"), this.d);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: w */
    public boolean m156w() {
        return bt.m619a(this.b, "playback_requires_user_action", true, this.d);
    }

    /* renamed from: x */
    public boolean mo2137x() {
        return bt.m619a(this.b, "video_clickable", false, this.d);
    }

    /* renamed from: y */
    public boolean m158y() {
        return bt.m619a(this.b, "accelerate_hardware", false, this.d);
    }

    /* renamed from: z */
    public boolean m159z() {
        return bt.m619a(this.b, "hide_close_on_exit_graphic", false, this.d);
    }
}
