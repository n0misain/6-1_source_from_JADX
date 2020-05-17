package com.applovin.impl.sdk;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.applovin.impl.sdk.a */
class C0495a {
    /* renamed from: a */
    private static final Object f458a = new Object();
    /* renamed from: b */
    private static final Map f459b = new HashMap();

    /* renamed from: a */
    static Map m507a(AppLovinSdkImpl appLovinSdkImpl) {
        Map c;
        synchronized (f458a) {
            appLovinSdkImpl.getLogger().mo2288d("AdDataCache", "Reading cached device data...");
            c = C0495a.m512c(appLovinSdkImpl);
        }
        return c;
    }

    /* renamed from: a */
    private static void m508a(String str, Map map) {
        String[] split = str.split("=");
        if (split.length == 2) {
            map.put(split[0], split[1]);
        }
    }

    /* renamed from: a */
    static void m509a(Map map, AppLovinSdkImpl appLovinSdkImpl) {
        C0495a.m511b(map, appLovinSdkImpl);
    }

    /* renamed from: b */
    static void m510b(AppLovinSdkImpl appLovinSdkImpl) {
        synchronized (f458a) {
            appLovinSdkImpl.getLogger().mo2288d("AdDataCache", "Clearing old device data cache...");
            C0495a.m509a(new HashMap(0), appLovinSdkImpl);
        }
    }

    /* renamed from: b */
    private static void m511b(Map map, AppLovinSdkImpl appLovinSdkImpl) {
        if (map == null) {
            throw new IllegalArgumentException("No ad aata specified");
        } else if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            try {
                synchronized (f459b) {
                    Map map2 = (Map) f459b.get("ad_data_cache");
                    if (map2 == null) {
                        map2 = new HashMap();
                    }
                    map2.clear();
                    map2.putAll(map);
                    f459b.put("ad_data_cache", map2);
                }
                Editor edit = appLovinSdkImpl.getSettingsManager().m817a().edit();
                edit.putString("ad_data_cache", fk.m1085a(map));
                edit.commit();
                appLovinSdkImpl.getLogger().mo2288d("AdDataCache", map.size() + " " + "ad_data_cache" + " entries saved in cache");
            } catch (Throwable e) {
                appLovinSdkImpl.getLogger().mo2290e("AdDataCache", "Unable to save ad data entries", e);
            }
        }
    }

    /* renamed from: c */
    private static Map m512c(AppLovinSdkImpl appLovinSdkImpl) {
        Map map;
        Map hashMap;
        Throwable e;
        synchronized (f459b) {
            map = (Map) f459b.get("ad_data_cache");
        }
        if (map == null) {
            SharedPreferences a = appLovinSdkImpl.getSettingsManager().m817a();
            String string = a.getString("ad_data_cache", "");
            if (string != null && string.length() > 0) {
                try {
                    hashMap = new HashMap();
                    try {
                        for (String a2 : string.split("&")) {
                            C0495a.m508a(a2, hashMap);
                        }
                        synchronized (f459b) {
                            f459b.put("ad_data_cache", hashMap);
                        }
                        appLovinSdkImpl.getLogger().mo2288d("AdDataCache", hashMap.size() + " " + "ad_data_cache" + " entries loaded from cache");
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Throwable e3) {
                    Throwable th = e3;
                    hashMap = map;
                    e = th;
                    appLovinSdkImpl.getLogger().mo2290e("AdDataCache", "Unable to load ad data", e);
                    Editor edit = a.edit();
                    edit.putString("ad_data_cache", "");
                    edit.commit();
                    return hashMap != null ? new HashMap(hashMap) : new HashMap();
                }
                if (hashMap != null) {
                }
            }
        }
        hashMap = map;
        if (hashMap != null) {
        }
    }
}
