package com.applovin.impl.sdk;

import android.content.Intent;
import android.net.Uri;
import com.anjlab.android.iab.v3.Constants;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinEventTypes;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.appevents.AppEventsConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class EventServiceImpl implements AppLovinEventService {
    /* renamed from: a */
    private final AppLovinSdkImpl f430a;
    /* renamed from: b */
    private final List f431b;

    public EventServiceImpl(AppLovinSdk appLovinSdk) {
        this.f430a = (AppLovinSdkImpl) appLovinSdk;
        this.f431b = Arrays.asList(((String) ((AppLovinSdkImpl) appLovinSdk).get(dj.bu)).split(","));
    }

    /* renamed from: a */
    private Uri m476a(String str, Map map) {
        try {
            return Uri.parse(str).buildUpon().encodedQuery(fk.m1085a(map)).build();
        } catch (Throwable th) {
            this.f430a.getLogger().mo2290e("EventServiceImpl", "Unable to create postback uri due to invalid endpoint", th);
            return null;
        }
    }

    /* renamed from: a */
    private String m477a() {
        return (String) this.f430a.get(dj.f784m);
    }

    /* renamed from: a */
    private HashMap m480a(df dfVar, aa aaVar) {
        C0519y dataCollector = this.f430a.getDataCollector();
        ad a = dataCollector.m1203a();
        ab c = dataCollector.m1206c();
        boolean contains = this.f431b.contains(dfVar.m777a());
        HashMap hashMap = new HashMap();
        hashMap.put("event", contains ? dfVar.m777a() : "postinstall");
        hashMap.put("ts", Long.toString(dfVar.m779c()));
        hashMap.put("platform", "Android");
        hashMap.put("model", a.f468a);
        hashMap.put("package_name", c.f464c);
        hashMap.put("sdk_key", this.f430a.getSdkKey());
        hashMap.put("idfa", aaVar.f461b);
        hashMap.put("dnt", Boolean.toString(aaVar.f460a));
        hashMap.put("ia", Long.toString(c.f465d));
        hashMap.put("api_did", this.f430a.get(dj.f774c));
        hashMap.put("brand", a.f470c);
        hashMap.put("model", a.f468a);
        hashMap.put("revision", a.f471d);
        hashMap.put("sdk_version", AppLovinSdk.VERSION);
        hashMap.put("os", a.f469b);
        hashMap.put("orientation_lock", a.f476i);
        hashMap.put("app_version", this.f430a.getDataCollector().m1206c().f463b);
        hashMap.put("country_code", a.f473f);
        hashMap.put("carrier", a.f474g);
        hashMap.put("tz_offset", String.valueOf(a.f479l));
        hashMap.put("adr", a.f481n ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        hashMap.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, String.valueOf(a.f483p));
        ac acVar = a.f482o;
        if (acVar != null) {
            hashMap.put("act", String.valueOf(acVar.f466a));
            hashMap.put("acm", String.valueOf(acVar.f467b));
        }
        String str = a.f484q;
        if (AppLovinSdkUtils.isValidString(str)) {
            hashMap.put("ua", fk.m1093c(str));
        }
        if (!contains) {
            hashMap.put("sub_event", dfVar.m777a());
        }
        return hashMap;
    }

    /* renamed from: a */
    private Map m481a(Map map) {
        Map hashMap = new HashMap();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if ((key instanceof String) && (value instanceof String)) {
                    hashMap.put((String) key, (String) value);
                } else {
                    this.f430a.getLogger().mo2294w("EventServiceImpl", "Unexpected class type in trackEvent(); all keys and values passed as parameters must be String. Encountered " + key.getClass().getCanonicalName() + "/" + value.getClass().getCanonicalName() + "; will use toString() value instead, which may be unexpected...");
                    hashMap.put(key.toString(), value.toString());
                }
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    private void m482a(df dfVar) {
        if (((Boolean) this.f430a.get(dj.bv)).booleanValue()) {
            this.f430a.getLogger().mo2288d("EventServiceImpl", "Tracking event: " + dfVar);
            m483a(new ai(this, dfVar));
        }
    }

    /* renamed from: a */
    private void m483a(ea eaVar) {
        this.f430a.m466a().m992a(new dz(this.f430a, eaVar), ej.BACKGROUND);
    }

    /* renamed from: b */
    private String m484b() {
        return (String) this.f430a.get(dj.f787p);
    }

    public void trackCheckout(String str, Map map) {
        Map hashMap = map != null ? new HashMap(map) : new HashMap(1);
        hashMap.put("transaction_id", str);
        trackEvent(AppLovinEventTypes.USER_COMPLETED_CHECKOUT, hashMap);
    }

    public void trackEvent(String str) {
        trackEvent(str, new HashMap());
    }

    public void trackEvent(String str, Map map) {
        m482a(new df(str, m481a(map), System.currentTimeMillis(), fk.m1091b(UUID.randomUUID().toString())));
    }

    public void trackInAppPurchase(Intent intent, Map map) {
        Map hashMap = map != null ? new HashMap(map) : new HashMap();
        try {
            hashMap.put(AppLovinEventParameters.IN_APP_PURCHASE_DATA, intent.getStringExtra(Constants.INAPP_PURCHASE_DATA));
            hashMap.put(AppLovinEventParameters.IN_APP_DATA_SIGNATURE, intent.getStringExtra(Constants.RESPONSE_INAPP_SIGNATURE));
        } catch (Throwable e) {
            this.f430a.getLogger().userError("EventServiceImpl", "Unable to track in app purchase; invalid purchanse intent", e);
        }
        trackEvent(AppLovinEventTypes.USER_COMPLETED_IN_APP_PURCHASE, hashMap);
    }
}
