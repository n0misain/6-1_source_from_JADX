package com.applovin.impl.sdk;

import android.content.SharedPreferences.Editor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.do */
class C0500do {
    /* renamed from: a */
    private final AppLovinSdkImpl f807a;
    /* renamed from: b */
    private final Map f808b = new HashMap();

    C0500do(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f807a = appLovinSdkImpl;
    }

    /* renamed from: a */
    void m877a() {
        synchronized (this.f808b) {
            this.f808b.clear();
        }
        m885d();
    }

    /* renamed from: a */
    void m878a(String str) {
        m879a(str, 1);
    }

    /* renamed from: a */
    void m879a(String str, long j) {
        synchronized (this.f808b) {
            Long l = (Long) this.f808b.get(str);
            if (l == null) {
                l = Long.valueOf(0);
            }
            this.f808b.put(str, Long.valueOf(l.longValue() + j));
        }
        m885d();
    }

    /* renamed from: b */
    long m880b(String str) {
        long longValue;
        synchronized (this.f808b) {
            Long l = (Long) this.f808b.get(str);
            if (l == null) {
                l = Long.valueOf(0);
            }
            longValue = l.longValue();
        }
        return longValue;
    }

    /* renamed from: b */
    JSONObject m881b() throws JSONException {
        JSONObject jSONObject;
        synchronized (this.f808b) {
            jSONObject = new JSONObject();
            for (Entry entry : this.f808b.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    /* renamed from: b */
    void m882b(String str, long j) {
        synchronized (this.f808b) {
            this.f808b.put(str, Long.valueOf(j));
        }
        m885d();
    }

    /* renamed from: c */
    void m883c() {
        try {
            JSONObject jSONObject = new JSONObject(this.f807a.getSettingsManager().m817a().getString("stats", "{}"));
            synchronized (this.f808b) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    try {
                        String str = (String) keys.next();
                        this.f808b.put(str, Long.valueOf(jSONObject.getLong(str)));
                    } catch (JSONException e) {
                    }
                }
            }
        } catch (Throwable th) {
            this.f807a.getLogger().mo2290e("StatsManager", "Unable to load stats", th);
        }
    }

    /* renamed from: c */
    void m884c(String str) {
        synchronized (this.f808b) {
            this.f808b.remove(str);
        }
        m885d();
    }

    /* renamed from: d */
    void m885d() {
        try {
            Editor edit = this.f807a.getSettingsManager().m817a().edit();
            edit.putString("stats", m881b().toString());
            edit.commit();
        } catch (Throwable e) {
            this.f807a.getLogger().mo2290e("StatsManager", "Unable to save stats", e);
        }
    }
}
