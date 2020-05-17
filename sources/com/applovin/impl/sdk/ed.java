package com.applovin.impl.sdk;

import android.graphics.Point;
import android.text.TextUtils;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinMediationService.AppLovinMediationAdapterInfo;
import com.applovin.sdk.AppLovinMediationService.AppLovinMediationAdapterStats;
import com.applovin.sdk.AppLovinMediationService.AppLovinMediationAdapterStatus;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.appevents.AppEventsConstants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

class ed extends di implements fi {
    /* renamed from: a */
    private final C0505i f837a;
    /* renamed from: b */
    private final AppLovinAdLoadListener f838b;
    /* renamed from: g */
    private boolean f839g = false;

    ed(C0505i c0505i, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("FetchNextAd", appLovinSdkImpl);
        this.f837a = c0505i;
        this.f838b = appLovinAdLoadListener;
    }

    /* renamed from: a */
    private String m942a(AppLovinMediationAdapterInfo appLovinMediationAdapterInfo) {
        if (appLovinMediationAdapterInfo != null) {
            try {
                if (appLovinMediationAdapterInfo.getAdapter() != null) {
                    return appLovinMediationAdapterInfo.getAdapter().getVersion();
                }
            } catch (Throwable th) {
                this.e.mo2295w(this.c, "Unable to get adapter version", th);
            }
        }
        return null;
    }

    /* renamed from: a */
    private void m943a(C0500do c0500do) {
        if (System.currentTimeMillis() - c0500do.m880b("ad_session_start") > TimeUnit.MINUTES.toMillis((long) ((Integer) this.d.get(dj.f794w)).intValue())) {
            c0500do.m882b("ad_session_start", System.currentTimeMillis());
            c0500do.m884c("ad_imp_session");
        }
    }

    /* renamed from: b */
    private void m946b(int i) {
        this.e.mo2289e(this.c, "Unable to fetch " + this.f837a + " ad: server returned " + i);
        try {
            mo2284a(i);
        } catch (Throwable th) {
            this.e.userError(this.c, "Unable process a failure to recieve an ad", th);
        }
        C0518x.m1188b(i, this.d);
    }

    /* renamed from: b */
    private void m947b(JSONObject jSONObject) {
        C0518x.m1182a(jSONObject, this.d);
        di a = mo2282a(jSONObject);
        if (((Boolean) this.d.get(dj.bX)).booleanValue()) {
            this.d.m466a().m991a(a);
        } else {
            this.d.m466a().m992a(a, ej.MAIN);
        }
    }

    /* renamed from: d */
    private String m948d() {
        String str = "custom_size,launch_app";
        return (C0516u.m1161b() && C0516u.m1159a(AppLovinInterstitialActivity.class, this.f)) ? str + ",video" : str;
    }

    /* renamed from: f */
    private void m949f(Map map) {
        if (this.d.getSettings().isTestAdsEnabled()) {
            map.put("test_ads", Boolean.toString(true));
        }
        map.put("api_did", this.d.get(dj.f774c));
        map.put("sdk_key", this.d.getSdkKey());
        map.put("sdk_version", AppLovinSdk.VERSION);
        map.put("app_version", fk.m1093c(this.d.getDataCollector().m1206c().f463b));
        if (!AppLovinSdk.CIS_BUILD_TAG.equals(AppLovinSdk.CIS_BUILD_TAG)) {
            map.put("build_tag", AppLovinSdk.CIS_BUILD_TAG);
        }
        String str = (String) this.d.get(dj.f750E);
        if (str != null && str.length() > 0) {
            map.put("plugin_version", str);
        }
        map.put("accept", m948d());
        map.put("v1", Boolean.toString(C0516u.m1160a("android.permission.WRITE_EXTERNAL_STORAGE", this.f)));
        map.put("v2", Boolean.toString(C0516u.m1159a(AppLovinInterstitialActivity.class, this.f)));
        map.put("v3", Boolean.toString(C0516u.m1158a(this.f)));
        map.put("v4", Boolean.toString(C0516u.m1162b(this.f)));
        map.put("m", this.f837a.m1135c().toString());
        map.put("preloading", String.valueOf(this.f839g));
        map.put("size", this.f837a.m1133a().getLabel());
        map.put("format", "json");
        map.put("ia", Long.toString(this.d.getDataCollector().m1206c().f465d));
    }

    /* renamed from: g */
    private void m950g(Map map) {
        if (((Boolean) this.d.get(dj.f759N)).booleanValue()) {
            C0500do b = this.d.m468b();
            map.put("li", String.valueOf(b.m880b("ad_imp")));
            map.put("si", String.valueOf(b.m880b("ad_imp_session")));
            map.put("ld", String.valueOf(b.m880b("last_displayed_ad_id_number")));
            map.put("dt", String.valueOf(b.m880b("ad_dismiss_duration")));
            map.put("ct", String.valueOf(b.m880b("ad_time_to_click_through")));
            map.put("pd", String.valueOf(b.m880b("ad_paused_duration")));
        }
    }

    /* renamed from: h */
    private void m951h(Map map) {
        if (((Boolean) this.d.get(dj.f759N)).booleanValue()) {
            Map a = ((C0515t) this.d.getTargetingData()).m1156a();
            if (a != null && !a.isEmpty()) {
                map.putAll(a);
            }
        }
    }

    /* renamed from: i */
    private void m952i(Map map) {
        Map a = C0495a.m507a(this.d);
        if (a.isEmpty()) {
            try {
                m953j(a);
                C0495a.m509a(a, this.d);
            } catch (Throwable e) {
                this.e.mo2290e(this.c, "Unable to populate device information", e);
            }
        }
        try {
            m954k(a);
        } catch (Throwable e2) {
            this.e.mo2290e(this.c, "Unable to populate ephemeral device information", e2);
        }
        map.putAll(a);
        map.put("network", C0518x.m1176a(this.d));
        m956m(map);
        map.put("vz", fk.m1082a(this.d.getApplicationContext().getPackageName(), this.d));
    }

    /* renamed from: j */
    private void m953j(Map map) {
        ad a = this.d.getDataCollector().m1203a();
        map.put("brand", fk.m1093c(a.f470c));
        map.put("carrier", fk.m1093c(a.f474g));
        map.put("country_code", fk.m1093c(a.f473f));
        map.put("locale", a.f475h.toString());
        map.put("model", fk.m1093c(a.f468a));
        map.put("os", fk.m1093c(a.f469b));
        map.put("platform", "android");
        map.put("revision", fk.m1093c(a.f471d));
        map.put("orientation_lock", a.f476i);
        map.put("tz_offset", String.valueOf(a.f479l));
        map.put("wvvc", String.valueOf(a.f480m));
        map.put("adns", String.valueOf(a.f477j));
        map.put("adnsd", String.valueOf(a.f478k));
        m955l(map);
    }

    /* renamed from: k */
    private void m954k(Map map) {
        ad b = this.d.getDataCollector().m1205b();
        ac acVar = b.f482o;
        if (acVar != null) {
            map.put("act", String.valueOf(acVar.f466a));
            map.put("acm", String.valueOf(acVar.f467b));
        }
        map.put("adr", b.f481n ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        map.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, String.valueOf(b.f483p));
        String str = b.f484q;
        if (AppLovinSdkUtils.isValidString(str)) {
            map.put("ua", fk.m1093c(str));
        }
        m955l(map);
        m957n(map);
    }

    /* renamed from: l */
    private void m955l(Map map) {
        Point c = C0516u.m1163c(this.d.getApplicationContext());
        map.put("dx", Integer.toString(c.x));
        map.put("dy", Integer.toString(c.y));
    }

    /* renamed from: m */
    private void m956m(Map map) {
        aa d = this.d.getDataCollector().m1207d();
        String str = d.f461b;
        boolean z = d.f460a;
        if ((!z || ((Boolean) this.d.getSettingsManager().m818a(dj.bp)).booleanValue()) && AppLovinSdkUtils.isValidString(str)) {
            map.put("idfa", str);
        }
        map.put("dnt", Boolean.toString(z));
    }

    /* renamed from: n */
    private void m957n(Map map) {
        Collection<AppLovinMediationAdapterInfo> adapterInfo = this.d.getMediationService().getAdapterInfo();
        if (!(adapterInfo == null || adapterInfo.isEmpty())) {
            StringBuilder stringBuilder = new StringBuilder();
            for (AppLovinMediationAdapterInfo appLovinMediationAdapterInfo : adapterInfo) {
                if (appLovinMediationAdapterInfo.getStatus() == AppLovinMediationAdapterStatus.READY) {
                    stringBuilder.append(appLovinMediationAdapterInfo.getName());
                    Object a = m942a(appLovinMediationAdapterInfo);
                    if (!TextUtils.isEmpty(a)) {
                        stringBuilder.append(":");
                        stringBuilder.append(a);
                    }
                    stringBuilder.append(",");
                }
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.setLength(stringBuilder.length() - 1);
            }
            if (stringBuilder.length() > 0) {
                map.put("aa", stringBuilder.toString());
            }
        }
        AppLovinMediationAdapterStats lastAdapterStats = this.d.getMediationService().getLastAdapterStats();
        if (lastAdapterStats != null) {
            map.put("lman", lastAdapterStats.getAdapterName());
            map.put("lmat", String.valueOf(lastAdapterStats.getLastAdLoadMillis()));
        }
    }

    /* renamed from: a */
    protected di mo2282a(JSONObject jSONObject) {
        return new eo(jSONObject, this.f837a, this.f838b, this.d);
    }

    /* renamed from: a */
    protected String mo2283a(Map map) {
        return C0518x.m1186b("3.0/ad", map, this.d);
    }

    /* renamed from: a */
    protected void mo2284a(int i) {
        if (this.f838b == null) {
            return;
        }
        if (this.f838b instanceof aj) {
            ((aj) this.f838b).mo2254a(this.f837a, i);
        } else {
            this.f838b.failedToReceiveAd(i);
        }
    }

    /* renamed from: a */
    void m961a(boolean z) {
        this.f839g = z;
    }

    /* renamed from: b */
    protected String mo2285b(Map map) {
        return C0518x.m1190d("3.0/ad", map, this.d);
    }

    /* renamed from: b */
    void mo2280b() {
        super.mo2280b();
        m946b(-410);
    }

    /* renamed from: c */
    public String mo2281c() {
        return "tFNA";
    }

    /* renamed from: c */
    protected void m965c(Map map) {
        m951h(map);
        m952i(map);
        m950g(map);
        m949f(map);
        mo2286d(map);
        mo2287e(map);
    }

    /* renamed from: d */
    protected void mo2286d(Map map) {
        map.put("require", this.f837a.m1134b().getLabel());
    }

    /* renamed from: e */
    protected void mo2287e(Map map) {
        ff a = fd.m1052a().m1053a("tFNA");
        if (a != null) {
            map.put("etf", Long.toString(a.m1056b()));
            map.put("ntf", a.m1055a());
        }
        a = fd.m1052a().m1053a("tRA");
        if (a != null) {
            map.put("etr", Long.toString(a.m1056b()));
            map.put("ntr", a.m1055a());
        }
    }

    public void run() {
        if (this.f839g) {
            this.e.mo2288d(this.c, "Preloading next ad of spec: " + this.f837a);
        } else {
            this.e.mo2288d(this.c, "Fetching next ad of spec: " + this.f837a);
        }
        C0500do b = this.d.m468b();
        b.m878a("ad_req");
        m943a(b);
        try {
            ex eeVar = new ee(this, HttpRequest.METHOD_GET, new JSONObject(), "RepeatFetchNextAd", this.d);
            Map hashMap = new HashMap();
            m965c(hashMap);
            eeVar.m797a(mo2283a(hashMap));
            eeVar.m802b(mo2285b(hashMap));
            eeVar.m800b(((Integer) this.d.get(dj.f792u)).intValue());
            eeVar.m803c(((Integer) this.d.get(dj.f779h)).intValue());
            eeVar.m795a(dj.f782k);
            eeVar.m801b(dj.f786o);
            eeVar.run();
        } catch (Throwable th) {
            this.e.mo2290e(this.c, "Unable to fetch ad " + this.f837a, th);
            m946b(0);
        }
    }
}
