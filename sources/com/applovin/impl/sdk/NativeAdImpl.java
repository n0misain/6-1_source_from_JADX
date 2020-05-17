package com.applovin.impl.sdk;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.applovin.sdk.AppLovinSdkUtils;

public class NativeAdImpl implements ak, bu {
    public static final String QUERY_PARAM_IS_FIRST_PLAY = "fp";
    public static final String QUERY_PARAM_VIDEO_PERCENT_VIEWED = "pv";
    /* renamed from: a */
    private final AppLovinSdkImpl f438a;
    /* renamed from: b */
    private String f439b;
    /* renamed from: c */
    private String f440c;
    /* renamed from: d */
    private String f441d;
    /* renamed from: e */
    private String f442e;
    /* renamed from: f */
    private String f443f;
    /* renamed from: g */
    private String f444g;
    /* renamed from: h */
    private String f445h;
    /* renamed from: i */
    private String f446i;
    /* renamed from: j */
    private String f447j;
    /* renamed from: k */
    private String f448k;
    /* renamed from: l */
    private float f449l;
    /* renamed from: m */
    private String f450m;
    /* renamed from: n */
    private String f451n;
    /* renamed from: o */
    private String f452o;
    /* renamed from: p */
    private String f453p;
    /* renamed from: q */
    private String f454q;
    /* renamed from: r */
    private String f455r;
    /* renamed from: s */
    private long f456s;

    private NativeAdImpl(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, float f, String str10, String str11, String str12, String str13, String str14, String str15, String str16, long j, AppLovinSdkImpl appLovinSdkImpl) {
        this.f439b = str;
        this.f440c = str2;
        this.f441d = str3;
        this.f442e = str4;
        this.f443f = str5;
        this.f444g = str6;
        this.f445h = str7;
        this.f447j = str8;
        this.f448k = str9;
        this.f449l = f;
        this.f450m = str10;
        this.f451n = str11;
        this.f452o = str12;
        this.f453p = str13;
        this.f454q = str14;
        this.f455r = str15;
        this.f446i = str16;
        this.f456s = j;
        this.f438a = appLovinSdkImpl;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NativeAdImpl nativeAdImpl = (NativeAdImpl) obj;
        if (this.f445h == null ? nativeAdImpl.f445h != null : !this.f445h.equals(nativeAdImpl.f445h)) {
            return false;
        }
        if (this.f455r == null ? nativeAdImpl.f455r != null : !this.f455r.equals(nativeAdImpl.f455r)) {
            return false;
        }
        if (this.f452o == null ? nativeAdImpl.f452o != null : !this.f452o.equals(nativeAdImpl.f452o)) {
            return false;
        }
        if (this.f446i == null ? nativeAdImpl.f446i != null : !this.f446i.equals(nativeAdImpl.f446i)) {
            return false;
        }
        if (this.f444g == null ? nativeAdImpl.f444g != null : !this.f444g.equals(nativeAdImpl.f444g)) {
            return false;
        }
        if (this.f451n == null ? nativeAdImpl.f451n != null : !this.f451n.equals(nativeAdImpl.f451n)) {
            return false;
        }
        if (this.f439b == null ? nativeAdImpl.f439b != null : !this.f439b.equals(nativeAdImpl.f439b)) {
            return false;
        }
        if (this.f440c == null ? nativeAdImpl.f440c != null : !this.f440c.equals(nativeAdImpl.f440c)) {
            return false;
        }
        if (this.f441d == null ? nativeAdImpl.f441d != null : !this.f441d.equals(nativeAdImpl.f441d)) {
            return false;
        }
        if (this.f442e == null ? nativeAdImpl.f442e != null : !this.f442e.equals(nativeAdImpl.f442e)) {
            return false;
        }
        if (this.f443f == null ? nativeAdImpl.f443f != null : !this.f443f.equals(nativeAdImpl.f443f)) {
            return false;
        }
        if (this.f454q == null ? nativeAdImpl.f454q != null : !this.f454q.equals(nativeAdImpl.f454q)) {
            return false;
        }
        if (this.f453p != null) {
            if (this.f453p.equals(nativeAdImpl.f453p)) {
                return true;
            }
        } else if (nativeAdImpl.f453p == null) {
            return true;
        }
        return false;
    }

    public long getAdId() {
        return this.f456s;
    }

    public String getCaptionText() {
        return this.f445h;
    }

    public String getClCode() {
        return this.f455r;
    }

    public String getClickUrl() {
        return this.f452o;
    }

    public String getCtaText() {
        return this.f446i;
    }

    public String getDescriptionText() {
        return this.f444g;
    }

    public String getIconUrl() {
        return this.f447j;
    }

    public String getImageUrl() {
        return this.f448k;
    }

    public String getImpressionTrackingUrl() {
        return this.f451n;
    }

    public String getSourceIconUrl() {
        return this.f439b;
    }

    public String getSourceImageUrl() {
        return this.f440c;
    }

    public String getSourceStarRatingImageUrl() {
        return this.f441d;
    }

    public String getSourceVideoUrl() {
        return this.f442e;
    }

    public float getStarRating() {
        return this.f449l;
    }

    public String getTitle() {
        return this.f443f;
    }

    public String getVideoEndTrackingUrl(int i, boolean z) {
        if (this.f454q == null) {
            return Uri.EMPTY.toString();
        }
        if (i < 0 || i > 100) {
            Log.e("AppLovinNativeAd", "Invalid percent viewed supplied.", new IllegalArgumentException("Percent viewed must be an integer between 0 and 100."));
        }
        return Uri.parse(this.f454q).buildUpon().appendQueryParameter(QUERY_PARAM_VIDEO_PERCENT_VIEWED, Integer.toString(i)).appendQueryParameter(QUERY_PARAM_IS_FIRST_PLAY, Boolean.toString(z)).build().toString();
    }

    public String getVideoStartTrackingUrl() {
        return this.f453p;
    }

    public String getVideoUrl() {
        return this.f450m;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f454q != null ? this.f454q.hashCode() : 0) + (((this.f453p != null ? this.f453p.hashCode() : 0) + (((this.f452o != null ? this.f452o.hashCode() : 0) + (((this.f451n != null ? this.f451n.hashCode() : 0) + (((this.f446i != null ? this.f446i.hashCode() : 0) + (((this.f445h != null ? this.f445h.hashCode() : 0) + (((this.f444g != null ? this.f444g.hashCode() : 0) + (((this.f443f != null ? this.f443f.hashCode() : 0) + (((this.f442e != null ? this.f442e.hashCode() : 0) + (((this.f441d != null ? this.f441d.hashCode() : 0) + (((this.f440c != null ? this.f440c.hashCode() : 0) + ((this.f439b != null ? this.f439b.hashCode() : 0) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f455r != null) {
            i = this.f455r.hashCode();
        }
        return hashCode + i;
    }

    public boolean isImagePrecached() {
        boolean z = (this.f447j == null || this.f447j.equals(this.f439b)) ? false : true;
        boolean z2 = (this.f448k == null || this.f448k.equals(this.f440c)) ? false : true;
        return z && z2;
    }

    public boolean isVideoPrecached() {
        return (this.f450m == null || this.f450m.equals(this.f442e)) ? false : true;
    }

    public void launchClickTarget(Context context) {
        this.f438a.getPersistentPostbackManager().m761a(this.f452o);
        AppLovinSdkUtils.openUrl(context, this.f452o, this.f438a);
    }

    public void setIconUrl(String str) {
        this.f447j = str;
    }

    public void setImageUrl(String str) {
        this.f448k = str;
    }

    public void setStarRating(float f) {
        this.f449l = f;
    }

    public void setVideoUrl(String str) {
        this.f450m = str;
    }

    public String toString() {
        return "WidgetSlot{clCode='" + this.f455r + '\'' + ", sourceIconUrl='" + this.f439b + '\'' + ", sourceImageUrl='" + this.f440c + '\'' + ", sourceStarRatingImageUrl='" + this.f441d + '\'' + ", sourceVideoUrl='" + this.f442e + '\'' + ", title='" + this.f443f + '\'' + ", descriptionText='" + this.f444g + '\'' + ", captionText='" + this.f445h + '\'' + ", ctaText='" + this.f446i + '\'' + ", iconUrl='" + this.f447j + '\'' + ", imageUrl='" + this.f448k + '\'' + ", starRating='" + this.f449l + '\'' + ", videoUrl='" + this.f450m + '\'' + ", impressionTrackingUrl='" + this.f451n + '\'' + ", clickUrl='" + this.f452o + '\'' + ", videoStartTrackingUrl='" + this.f453p + '\'' + ", videoEndTrackingUrl='" + this.f454q + '\'' + '}';
    }
}
