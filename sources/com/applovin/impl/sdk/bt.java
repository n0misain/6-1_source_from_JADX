package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class bt {
    /* renamed from: a */
    public static float m612a(JSONObject jSONObject, String str, float f, AppLovinSdk appLovinSdk) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return f;
        }
        try {
            double d = jSONObject.getDouble(str);
            return (-3.4028234663852886E38d >= d || d >= 3.4028234663852886E38d) ? f : (float) d;
        } catch (JSONException e) {
            if (appLovinSdk == null) {
                return f;
            }
            appLovinSdk.getLogger().mo2289e("JsonUtils", "Failed to retrieve float property for key = " + str);
            return f;
        }
    }

    /* renamed from: a */
    public static int m613a(JSONObject jSONObject, String str, int i, AppLovinSdk appLovinSdk) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                i = jSONObject.getInt(str);
            } catch (JSONException e) {
                appLovinSdk.getLogger().mo2289e("JsonUtils", "Failed to retrieve int property for key = " + str);
            }
        }
        return i;
    }

    /* renamed from: a */
    private static Object m614a(Object obj) throws JSONException {
        return obj == JSONObject.NULL ? null : obj instanceof JSONObject ? m617a((JSONObject) obj) : obj instanceof JSONArray ? m616a((JSONArray) obj) : obj;
    }

    /* renamed from: a */
    public static String m615a(JSONObject jSONObject, String str, String str2, AppLovinSdk appLovinSdk) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                str2 = jSONObject.getString(str);
            } catch (JSONException e) {
                if (appLovinSdk != null) {
                    appLovinSdk.getLogger().mo2289e("JsonUtils", "Failed to retrieve string property for key = " + str);
                }
            }
        }
        return str2;
    }

    /* renamed from: a */
    public static List m616a(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(m614a(jSONArray.get(i)));
        }
        return arrayList;
    }

    /* renamed from: a */
    public static Map m617a(JSONObject jSONObject) throws JSONException {
        Map hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, m614a(jSONObject.get(str)).toString());
        }
        return hashMap;
    }

    /* renamed from: a */
    static JSONObject m618a(Map map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            jSONObject.put((String) entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static boolean m619a(JSONObject jSONObject, String str, boolean z, AppLovinSdk appLovinSdk) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                z = jSONObject.getBoolean(str);
            } catch (JSONException e) {
                if (appLovinSdk != null) {
                    appLovinSdk.getLogger().mo2294w("JsonUtils", "Unable to parse boolean for key = " + str + "... Attempting to parse it as an int");
                }
                return m613a(jSONObject, str, z ? 1 : 0, appLovinSdk) > 0;
            }
        }
        return z;
    }
}
