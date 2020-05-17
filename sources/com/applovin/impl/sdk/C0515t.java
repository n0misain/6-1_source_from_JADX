package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinTargetingData;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.applovin.impl.sdk.t */
class C0515t implements AppLovinTargetingData {
    /* renamed from: a */
    private final AppLovinSdkImpl f962a;
    /* renamed from: b */
    private final Context f963b;

    C0515t(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f962a = appLovinSdkImpl;
        this.f963b = appLovinSdkImpl.getApplicationContext();
    }

    /* renamed from: a */
    private static String m1154a(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strArr) {
            if (AppLovinSdkUtils.isValidString(str)) {
                stringBuilder.append(fk.m1093c(str));
                stringBuilder.append(",");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    private void m1155a(String str, String str2) {
        if (AppLovinSdkUtils.isValidString(str)) {
            Editor edit = this.f963b.getSharedPreferences("applovin.sdk.targeting", 0).edit();
            edit.putString(str, fk.m1093c(str2));
            edit.commit();
        }
    }

    /* renamed from: a */
    Map m1156a() {
        Map hashMap = new HashMap();
        Map all = this.f963b.getSharedPreferences("applovin.sdk.targeting", 0).getAll();
        if (all != null && all.size() > 0) {
            for (Entry entry : all.entrySet()) {
                hashMap.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return hashMap;
    }

    public void clearData() {
        Editor edit = this.f963b.getSharedPreferences("applovin.sdk.targeting", 0).edit();
        edit.clear();
        edit.commit();
    }

    public void putExtra(String str, String str2) {
        if (AppLovinSdkUtils.isValidString(str) && AppLovinSdkUtils.isValidString(str2)) {
            m1155a("ex_" + str, str2);
        }
    }

    public void setBirthYear(int i) {
        if (i < 9999 && i > 1900) {
            m1155a("yob", Integer.toString(i));
        }
    }

    public void setCarrier(String str) {
        this.f962a.getLogger().userError("AppLovinTargetingDataImpl", "Explicitly setting `carrier` targeting data is deprecated.");
    }

    public void setCountry(String str) {
        this.f962a.getLogger().userError("AppLovinTargetingDataImpl", "Explicitly setting `country code` targeting data is deprecated.");
    }

    public void setGender(char c) {
        String str = c == AppLovinTargetingData.GENDER_MALE ? "m" : c == AppLovinTargetingData.GENDER_FEMALE ? "f" : "u";
        m1155a("gender", str);
    }

    public void setInterests(String... strArr) {
        if (strArr != null && strArr.length > 0) {
            m1155a("interests", C0515t.m1154a(strArr));
        }
    }

    public void setKeywords(String... strArr) {
        if (strArr != null && strArr.length > 0) {
            m1155a("keywords", C0515t.m1154a(strArr));
        }
    }

    public void setLanguage(String str) {
        if (AppLovinSdkUtils.isValidString(str)) {
            m1155a("language", str.toLowerCase(Locale.ENGLISH));
        }
    }

    public void setLocation(Location location) {
        this.f962a.getLogger().userError("AppLovinTargetingDataImpl", "Explicitly setting `location` targeting data is deprecated.");
    }
}
