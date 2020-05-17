package com.applovin.impl.adview;

import com.applovin.impl.sdk.bt;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import org.json.JSONObject;

public class ce {
    /* renamed from: a */
    private final AppLovinLogger f317a;
    /* renamed from: b */
    private int f318b;
    /* renamed from: c */
    private int f319c;
    /* renamed from: d */
    private int f320d;
    /* renamed from: e */
    private int f321e;
    /* renamed from: f */
    private boolean f322f;
    /* renamed from: g */
    private int f323g;
    /* renamed from: h */
    private int f324h;
    /* renamed from: i */
    private int f325i;
    /* renamed from: j */
    private float f326j;
    /* renamed from: k */
    private float f327k;

    public ce(JSONObject jSONObject, AppLovinSdk appLovinSdk) {
        this.f317a = appLovinSdk.getLogger();
        this.f317a.mo2291i("VideoButtonProperties", "Updating video button properties with JSON = " + jSONObject);
        this.f318b = bt.m613a(jSONObject, SettingsJsonConstants.ICON_WIDTH_KEY, 64, appLovinSdk);
        this.f319c = bt.m613a(jSONObject, SettingsJsonConstants.ICON_HEIGHT_KEY, 7, appLovinSdk);
        this.f320d = bt.m613a(jSONObject, "margin", 20, appLovinSdk);
        this.f321e = bt.m613a(jSONObject, "gravity", 85, appLovinSdk);
        this.f322f = bt.m619a(jSONObject, "tap_to_fade", false, appLovinSdk);
        this.f323g = bt.m613a(jSONObject, "tap_to_fade_duration_milliseconds", 500, appLovinSdk);
        this.f324h = bt.m613a(jSONObject, "fade_in_duration_milliseconds", 500, appLovinSdk);
        this.f325i = bt.m613a(jSONObject, "fade_out_duration_milliseconds", 500, appLovinSdk);
        this.f326j = bt.m612a(jSONObject, "fade_in_delay_seconds", 1.0f, appLovinSdk);
        this.f327k = bt.m612a(jSONObject, "fade_out_delay_seconds", 6.0f, appLovinSdk);
    }

    /* renamed from: a */
    public int m378a() {
        return this.f318b;
    }

    /* renamed from: b */
    public int m379b() {
        return this.f319c;
    }

    /* renamed from: c */
    public int m380c() {
        return this.f320d;
    }

    /* renamed from: d */
    public int m381d() {
        return this.f321e;
    }

    /* renamed from: e */
    public boolean m382e() {
        return this.f322f;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ce ceVar = (ce) obj;
        if (this.f318b != ceVar.f318b || this.f319c != ceVar.f319c || this.f320d != ceVar.f320d || this.f321e != ceVar.f321e || this.f322f != ceVar.f322f || this.f323g != ceVar.f323g || this.f324h != ceVar.f324h || this.f325i != ceVar.f325i || Float.compare(ceVar.f326j, this.f326j) != 0) {
            return false;
        }
        if (Float.compare(ceVar.f327k, this.f327k) != 0) {
            z = false;
        }
        return z;
    }

    /* renamed from: f */
    public long m383f() {
        return (long) this.f323g;
    }

    /* renamed from: g */
    public long m384g() {
        return (long) this.f324h;
    }

    /* renamed from: h */
    public long m385h() {
        return (long) this.f325i;
    }

    public int hashCode() {
        int i = 0;
        int floatToIntBits = ((this.f326j != 0.0f ? Float.floatToIntBits(this.f326j) : 0) + (((((((((this.f322f ? 1 : 0) + (((((((this.f318b * 31) + this.f319c) * 31) + this.f320d) * 31) + this.f321e) * 31)) * 31) + this.f323g) * 31) + this.f324h) * 31) + this.f325i) * 31)) * 31;
        if (this.f327k != 0.0f) {
            i = Float.floatToIntBits(this.f327k);
        }
        return floatToIntBits + i;
    }

    /* renamed from: i */
    public float m386i() {
        return this.f326j;
    }

    /* renamed from: j */
    public float m387j() {
        return this.f327k;
    }

    public String toString() {
        return "VideoButtonProperties{widthPercentOfScreen=" + this.f318b + ", heightPercentOfScreen=" + this.f319c + ", margin=" + this.f320d + ", gravity=" + this.f321e + ", tapToFade=" + this.f322f + ", tapToFadeDurationMillis=" + this.f323g + ", fadeInDurationMillis=" + this.f324h + ", fadeOutDurationMillis=" + this.f325i + ", fadeInDelay=" + this.f326j + ", fadeOutDelay=" + this.f327k + '}';
    }
}
