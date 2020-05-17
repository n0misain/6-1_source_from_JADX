package com.applovin.impl.sdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.applovin.sdk.AppLovinSdkUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.x */
public class C0518x {
    /* renamed from: a */
    private static final int[] f967a = new int[]{7, 4, 2, 1, 11};
    /* renamed from: b */
    private static final int[] f968b = new int[]{5, 6, 10, 3, 9, 8, 14};
    /* renamed from: c */
    private static final int[] f969c = new int[]{15, 12, 13};
    /* renamed from: d */
    private static final String f970d = C0518x.class.getSimpleName();

    /* renamed from: a */
    static String m1176a(AppLovinSdkImpl appLovinSdkImpl) {
        NetworkInfo b = C0518x.m1185b(appLovinSdkImpl.getApplicationContext());
        if (b == null) {
            return "unknown";
        }
        int type = b.getType();
        int subtype = b.getSubtype();
        String str = type == 1 ? "wifi" : type == 0 ? C0518x.m1183a(subtype, f967a) ? "2g" : C0518x.m1183a(subtype, f968b) ? "3g" : C0518x.m1183a(subtype, f969c) ? "4g" : "mobile" : "unknown";
        appLovinSdkImpl.getLogger().mo2288d(f970d, "Network " + type + "/" + subtype + " resolved to " + str);
        return str;
    }

    /* renamed from: a */
    static String m1177a(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        Scanner scanner = new Scanner(inputStream, HttpRequest.CHARSET_UTF8);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    static String m1178a(String str, String str2, Map map, AppLovinSdkImpl appLovinSdkImpl) {
        if (str == null || str.length() < 4) {
            throw new IllegalArgumentException("Invalid domain specified");
        } else if (str2 == null) {
            throw new IllegalArgumentException("No endpoint specified");
        } else if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            StringBuilder stringBuilder = new StringBuilder(str + str2);
            if (map != null && map.size() > 0) {
                stringBuilder.append("?" + fk.m1085a(map));
            }
            return stringBuilder.toString();
        }
    }

    /* renamed from: a */
    static String m1179a(String str, Map map, AppLovinSdkImpl appLovinSdkImpl) {
        String str2 = (String) appLovinSdkImpl.get(dj.f781j);
        String str3 = (String) appLovinSdkImpl.get(dj.f776e);
        if (map == null) {
            map = C0518x.m1187b(appLovinSdkImpl);
        } else {
            map.putAll(C0518x.m1187b(appLovinSdkImpl));
        }
        return C0518x.m1178a(str2, str, map, appLovinSdkImpl);
    }

    /* renamed from: a */
    static JSONObject m1180a(JSONObject jSONObject) throws JSONException {
        return (JSONObject) jSONObject.getJSONArray("results").get(0);
    }

    /* renamed from: a */
    static void m1181a(int i, AppLovinSdkImpl appLovinSdkImpl) {
        dm settingsManager = appLovinSdkImpl.getSettingsManager();
        if (i == 401) {
            settingsManager.m819a(dj.f774c, "");
            settingsManager.m819a(dj.f776e, "");
            settingsManager.m822b();
        } else if (i == 418) {
            settingsManager.m819a(dj.f772a, Boolean.valueOf(true));
            settingsManager.m822b();
        } else if (i >= 400 && i < 500) {
            appLovinSdkImpl.m474h();
        } else if (i == -1) {
            appLovinSdkImpl.m474h();
        }
    }

    /* renamed from: a */
    static void m1182a(JSONObject jSONObject, AppLovinSdkImpl appLovinSdkImpl) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No response specified");
        } else if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            try {
                if (jSONObject.has("settings")) {
                    dm settingsManager = appLovinSdkImpl.getSettingsManager();
                    if (!jSONObject.isNull("settings")) {
                        settingsManager.m821a(jSONObject.getJSONObject("settings"));
                        settingsManager.m822b();
                        appLovinSdkImpl.getLogger().mo2288d(f970d, "New settings processed");
                    }
                }
            } catch (Throwable e) {
                appLovinSdkImpl.getLogger().mo2290e(f970d, "Unable to parse settings out of API response", e);
            }
        }
    }

    /* renamed from: a */
    private static boolean m1183a(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m1184a(Context context) {
        NetworkInfo b = C0518x.m1185b(context);
        return b == null || b.isConnected();
    }

    /* renamed from: b */
    private static NetworkInfo m1185b(Context context) {
        if (C0519y.m1193a("android.permission.ACCESS_NETWORK_STATE", context)) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                return connectivityManager.getActiveNetworkInfo();
            }
        }
        return null;
    }

    /* renamed from: b */
    static String m1186b(String str, Map map, AppLovinSdkImpl appLovinSdkImpl) {
        return C0518x.m1178a((String) appLovinSdkImpl.get(dj.f782k), str, map, appLovinSdkImpl);
    }

    /* renamed from: b */
    private static Map m1187b(AppLovinSdkImpl appLovinSdkImpl) {
        Map hashMap = new HashMap();
        String str = (String) appLovinSdkImpl.get(dj.f776e);
        if (AppLovinSdkUtils.isValidString(str)) {
            hashMap.put("device_token", str);
        } else {
            hashMap.put("api_key", appLovinSdkImpl.getSdkKey());
        }
        return hashMap;
    }

    /* renamed from: b */
    static void m1188b(int i, AppLovinSdkImpl appLovinSdkImpl) {
        if (i == 418) {
            dm settingsManager = appLovinSdkImpl.getSettingsManager();
            settingsManager.m819a(dj.f772a, Boolean.valueOf(true));
            settingsManager.m822b();
        }
    }

    /* renamed from: c */
    static String m1189c(String str, Map map, AppLovinSdkImpl appLovinSdkImpl) {
        String str2 = (String) appLovinSdkImpl.get(dj.f785n);
        if (map == null) {
            map = C0518x.m1187b(appLovinSdkImpl);
        } else {
            map.putAll(C0518x.m1187b(appLovinSdkImpl));
        }
        return C0518x.m1178a(str2, str, map, appLovinSdkImpl);
    }

    /* renamed from: d */
    static String m1190d(String str, Map map, AppLovinSdkImpl appLovinSdkImpl) {
        return C0518x.m1178a((String) appLovinSdkImpl.get(dj.f786o), str, map, appLovinSdkImpl);
    }
}
