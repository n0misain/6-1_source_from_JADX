package com.applovin.impl.sdk;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.facebook.internal.NativeProtocol;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class dq extends di {
    dq(AppLovinSdkImpl appLovinSdkImpl) {
        super("SubmitData", appLovinSdkImpl);
    }

    /* renamed from: a */
    void m892a(JSONObject jSONObject) {
        try {
            JSONObject a = C0518x.m1180a(jSONObject);
            dm settingsManager = this.d.getSettingsManager();
            settingsManager.m819a(dj.f774c, a.getString("device_id"));
            settingsManager.m819a(dj.f776e, a.getString("device_token"));
            settingsManager.m819a(dj.f775d, a.getString("publisher_id"));
            settingsManager.m822b();
            C0518x.m1182a(a, this.d);
            if (a.has("adserver_parameters")) {
                settingsManager.m819a(dj.f795x, a.getJSONObject("adserver_parameters").toString());
            }
        } catch (Throwable e) {
            this.e.mo2290e(this.c, "Unable to parse API response", e);
        }
    }

    /* renamed from: b */
    void m893b(JSONObject jSONObject) throws JSONException {
        C0519y dataCollector = this.d.getDataCollector();
        ab c = dataCollector.m1206c();
        ad a = dataCollector.m1203a();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("model", a.f468a);
        jSONObject2.put("os", a.f469b);
        jSONObject2.put("brand", a.f470c);
        jSONObject2.put("sdk_version", a.f472e);
        jSONObject2.put("revision", a.f471d);
        jSONObject2.put("adns", (double) a.f477j);
        jSONObject2.put("adnsd", a.f478k);
        jSONObject2.put("country_code", a.f473f);
        jSONObject2.put("carrier", a.f474g);
        jSONObject2.put("orientation_lock", a.f476i);
        jSONObject2.put("tz_offset", a.f479l);
        jSONObject2.put("adr", a.f481n ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        jSONObject2.put("wvvc", a.f480m);
        jSONObject2.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, a.f483p);
        jSONObject2.put("type", "android");
        aa d = dataCollector.m1207d();
        String str = d.f461b;
        boolean z = d.f460a;
        if ((!z || ((Boolean) this.d.getSettingsManager().m818a(dj.bp)).booleanValue()) && AppLovinSdkUtils.isValidString(str)) {
            jSONObject2.put("idfa", str);
        }
        ac acVar = a.f482o;
        if (acVar != null) {
            jSONObject2.put("act", acVar.f466a);
            jSONObject2.put("acm", acVar.f467b);
        }
        String str2 = a.f484q;
        if (AppLovinSdkUtils.isValidString(str2)) {
            jSONObject2.put("ua", fk.m1093c(str2));
        }
        jSONObject2.put("dnt", z);
        Locale locale = a.f475h;
        if (locale != null) {
            jSONObject2.put("locale", locale.toString());
        }
        jSONObject.put(DeviceRequestsHelper.DEVICE_INFO_PARAM, jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("package_name", c.f464c);
        jSONObject3.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, c.f462a);
        jSONObject3.put("app_version", c.f463b);
        jSONObject3.put("installed_at", c.f465d);
        jSONObject3.put("applovin_sdk_version", AppLovinSdk.VERSION);
        jSONObject3.put("ic", this.d.isInitializedInMainActivity());
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f);
        String string = defaultSharedPreferences.getString("com.applovin.sdk.impl.isFirstRun", null);
        if (AppLovinSdkUtils.isValidString(string)) {
            jSONObject3.put("first_install", string);
            if (string.equalsIgnoreCase(Boolean.toString(true))) {
                defaultSharedPreferences.edit().putString("com.applovin.sdk.impl.isFirstRun", Boolean.toString(false)).apply();
            }
        }
        str2 = (String) this.d.get(dj.f750E);
        if (str2 != null && str2.length() > 0) {
            jSONObject3.put("plugin_version", str2);
        }
        jSONObject.put("app_info", jSONObject3);
        if (((Boolean) this.d.get(dj.f759N)).booleanValue()) {
            Map a2 = ((C0515t) this.d.getTargetingData()).m1156a();
            if (!(a2 == null || a2.isEmpty())) {
                jSONObject.put("targeting", bt.m618a(a2));
            }
            jSONObject.put("stats", this.d.m468b().m881b());
        }
    }

    /* renamed from: c */
    void m894c(JSONObject jSONObject) {
        ex drVar = new dr(this, HttpRequest.METHOD_POST, new JSONObject(), "Repeat" + this.c, this.d);
        drVar.m797a(C0518x.m1179a("device", null, this.d));
        drVar.m802b(C0518x.m1189c("device", null, this.d));
        drVar.m798a(jSONObject);
        drVar.m803c(((Integer) this.d.get(dj.f777f)).intValue());
        drVar.m795a(dj.f781j);
        drVar.m801b(dj.f785n);
        drVar.run();
    }

    public void run() {
        try {
            this.e.mo2291i(this.c, "Submitting user data...");
            JSONObject jSONObject = new JSONObject();
            m893b(jSONObject);
            m894c(jSONObject);
        } catch (Throwable e) {
            this.e.mo2290e(this.c, "Unable to build JSON message with collected data", e);
        }
    }
}
