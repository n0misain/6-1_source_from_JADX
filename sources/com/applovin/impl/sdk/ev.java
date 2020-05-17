package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.share.internal.ShareConstants;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class ev extends di {
    /* renamed from: a */
    private final AppLovinNativeAdLoadListener f884a;
    /* renamed from: b */
    private final JSONObject f885b;

    ev(JSONObject jSONObject, AppLovinSdkImpl appLovinSdkImpl, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskRenderNativeAd", appLovinSdkImpl);
        this.f884a = appLovinNativeAdLoadListener;
        this.f885b = jSONObject;
    }

    /* renamed from: a */
    private String m1016a(Map map, String str) {
        String str2 = (String) map.get("simp_url");
        if (AppLovinSdkUtils.isValidString(str2)) {
            return str2.replace("{CLCODE}", str);
        }
        throw new IllegalArgumentException("No impression URL available");
    }

    /* renamed from: a */
    private String m1017a(Map map, String str, String str2) {
        String str3 = (String) map.get("click_url");
        if (AppLovinSdkUtils.isValidString(str3)) {
            CharSequence charSequence;
            if (str2 == null) {
                charSequence = "";
            }
            return str3.replace("{CLCODE}", str).replace("{EVENT_ID}", charSequence);
        }
        throw new IllegalArgumentException("No impression URL available");
    }

    /* renamed from: a */
    private void m1018a(JSONObject jSONObject) throws JSONException, MalformedURLException {
        List<Map> a = bt.m616a(jSONObject.getJSONArray("native_ads"));
        Map a2 = bt.m617a(jSONObject.getJSONObject("native_settings"));
        List arrayList = new ArrayList(a.size());
        for (Map map : a) {
            String str = (String) map.get("clcode");
            NativeAdImpl a3 = new cn().m686e((String) map.get("title")).m687f((String) map.get("description")).m688g((String) map.get(ShareConstants.FEED_CAPTION_PARAM)).m697p((String) map.get("cta")).m682a((String) map.get("icon_url")).m683b((String) map.get("image_url")).m685d((String) map.get("video_url")).m684c((String) map.get("star_rating_url")).m689h((String) map.get("icon_url")).m690i((String) map.get("image_url")).m691j((String) map.get("video_url")).m679a(Float.parseFloat((String) map.get("star_rating"))).m696o(str).m692k(m1016a(a2, str)).m693l(m1017a(a2, str, (String) map.get("event_id"))).m694m(m1019b(a2, str)).m695n(m1020c(a2, str)).m680a(Long.parseLong((String) map.get("ad_id"))).m681a(this.d).m678a();
            arrayList.add(a3);
            this.d.getLogger().mo2288d("TaskRenderNativeAd", "Prepared slot: " + a3.getAdId());
        }
        if (this.f884a != null) {
            this.f884a.onNativeAdsLoaded(arrayList);
        }
    }

    /* renamed from: b */
    private String m1019b(Map map, String str) {
        String str2 = (String) map.get("video_start_url");
        return str2 != null ? str2.replace("{CLCODE}", str) : null;
    }

    /* renamed from: c */
    private String m1020c(Map map, String str) {
        String str2 = (String) map.get("video_end_url");
        return str2 != null ? str2.replace("{CLCODE}", str) : null;
    }

    /* renamed from: a */
    void m1021a(int i) {
        try {
            if (this.f884a != null) {
                this.f884a.onNativeAdsFailedToLoad(i);
            }
        } catch (Throwable e) {
            this.d.getLogger().mo2290e("TaskRenderNativeAd", "Unable to notify listener about failure.", e);
        }
    }

    public void run() {
        try {
            if (this.f885b == null || this.f885b.length() == 0) {
                m1021a((int) AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD);
            } else {
                m1018a(this.f885b);
            }
        } catch (Throwable e) {
            this.d.getLogger().mo2290e("TaskRenderNativeAd", "Unable to render widget.", e);
            m1021a((int) AppLovinErrorCodes.UNABLE_TO_PRECACHE_RESOURCES);
        }
    }
}
