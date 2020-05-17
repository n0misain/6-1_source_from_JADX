package com.applovin.impl.sdk;

import android.content.SharedPreferences;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class cz {
    /* renamed from: a */
    private final AppLovinSdkImpl f708a;
    /* renamed from: b */
    private final AppLovinLogger f709b;
    /* renamed from: c */
    private ArrayList f710c;
    /* renamed from: d */
    private ArrayList f711d;
    /* renamed from: e */
    private final Object f712e;
    /* renamed from: f */
    private final SharedPreferences f713f;

    cz(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f708a = appLovinSdkImpl;
        this.f709b = appLovinSdkImpl.getLogger();
        this.f713f = appLovinSdkImpl.getApplicationContext().getSharedPreferences("com.applovin.sdk.impl.postbackQueue.domain", 0);
        this.f712e = new Object();
        this.f710c = m754c();
        this.f711d = new ArrayList();
    }

    /* renamed from: a */
    private void m750a(db dbVar) {
        synchronized (this.f712e) {
            m753b(dbVar);
            m755c(dbVar);
        }
    }

    /* renamed from: b */
    private db m751b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("attemptNumber");
            return new db(jSONObject.getString("targetUrl"), bt.m617a(jSONObject.getJSONObject("requestBody")), i, jSONObject.getString("backupUrl"));
        } catch (Throwable e) {
            this.f709b.mo2295w("PersistentPostbackManager", "Unable to inflate postback request from JSON.", e);
            return null;
        }
    }

    /* renamed from: b */
    private void m753b(db dbVar) {
        synchronized (this.f712e) {
            if (this.f710c.size() < ((Integer) this.f708a.get(dj.bx)).intValue()) {
                this.f710c.add(dbVar);
                m756d();
                this.f709b.mo2288d("PersistentPostbackManager", "Enqueued postback: " + dbVar);
            } else {
                this.f709b.mo2294w("PersistentPostbackManager", "Persistent queue has reached maximum size; postback retried in memory only." + dbVar);
            }
        }
    }

    /* renamed from: c */
    private ArrayList m754c() {
        if (C0516u.m1161b()) {
            Set<String> stringSet = this.f713f.getStringSet("com.applovin.sdk.impl.postbackQueue.key", new LinkedHashSet(0));
            ArrayList arrayList = new ArrayList(Math.max(1, stringSet.size()));
            int intValue = ((Integer) this.f708a.get(dj.by)).intValue();
            this.f709b.mo2288d("PersistentPostbackManager", "Deserializing " + stringSet.size() + " postback(s).");
            for (String str : stringSet) {
                db b = m751b(str);
                if (b == null) {
                    this.f709b.mo2289e("PersistentPostbackManager", "Unable to deserialize postback json: " + str);
                } else if (b.m766a() > intValue) {
                    arrayList.add(b);
                } else {
                    this.f709b.mo2288d("PersistentPostbackManager", "Skipping deserialization because maximum attempt count exceeded for postback: " + b);
                }
            }
            this.f709b.mo2288d("PersistentPostbackManager", "Successfully loaded postback queue with " + arrayList.size() + " postback(s).");
            return arrayList;
        }
        this.f709b.mo2288d("PersistentPostbackManager", "Loading new postback queue due to old Android version...");
        return new ArrayList();
    }

    /* renamed from: c */
    private void m755c(db dbVar) {
        this.f709b.mo2288d("PersistentPostbackManager", "Preparing to submit postback..." + dbVar);
        synchronized (this.f712e) {
            dbVar.m767a(dbVar.m766a() + 1);
            m756d();
        }
        int intValue = ((Integer) this.f708a.get(dj.by)).intValue();
        if (dbVar.m766a() > intValue) {
            this.f709b.mo2294w("PersistentPostbackManager", "Exceeded maximum persisted attempt count of " + intValue + ". Dequeuing postback: " + dbVar);
            m757d(dbVar);
            return;
        }
        this.f708a.getPostbackService().dispatchPostbackAsync(dbVar.m768b(), dbVar.m770d(), dbVar.m769c(), new da(this, dbVar));
    }

    /* renamed from: d */
    private void m756d() {
        if (C0516u.m1161b()) {
            Set linkedHashSet = new LinkedHashSet(this.f710c.size());
            Iterator it = this.f710c.iterator();
            while (it.hasNext()) {
                String f = m759f((db) it.next());
                if (f != null) {
                    linkedHashSet.add(f);
                }
            }
            this.f713f.edit().putStringSet("com.applovin.sdk.impl.postbackQueue.key", linkedHashSet).commit();
            this.f709b.mo2288d("PersistentPostbackManager", "Wrote updated postback queue to disk.");
            return;
        }
        this.f709b.mo2288d("PersistentPostbackManager", "Skipping writing postback queue to disk due to old Android version...");
    }

    /* renamed from: d */
    private void m757d(db dbVar) {
        synchronized (this.f712e) {
            this.f710c.remove(dbVar);
            m756d();
        }
        this.f709b.mo2288d("PersistentPostbackManager", "Dequeued successfully transmitted postback: " + dbVar);
    }

    /* renamed from: e */
    private void m758e(db dbVar) {
        synchronized (this.f712e) {
            this.f711d.add(dbVar);
        }
    }

    /* renamed from: f */
    private String m759f(db dbVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("attemptNumber", dbVar.m766a());
            jSONObject.put("targetUrl", dbVar.m768b());
            String c = dbVar.m769c();
            if (AppLovinSdkUtils.isValidString(c)) {
                jSONObject.put("backupUrl", c);
            }
            Map d = dbVar.m770d();
            if (d != null) {
                jSONObject.put("requestBody", new JSONObject(d));
            }
            return jSONObject.toString();
        } catch (Throwable e) {
            this.f709b.mo2295w("PersistentPostbackManager", "Unable to serialize postback request to JSON.", e);
            return null;
        }
    }

    /* renamed from: a */
    public void m760a() {
        synchronized (this.f712e) {
            if (this.f710c != null) {
                for (db c : new ArrayList(this.f710c)) {
                    m755c(c);
                }
            }
        }
    }

    /* renamed from: a */
    public void m761a(String str) {
        m762a(str, null);
    }

    /* renamed from: a */
    public void m762a(String str, Map map) {
        m763a(str, map, true);
    }

    /* renamed from: a */
    public void m763a(String str, Map map, boolean z) {
        m764a(str, map, z, null);
    }

    /* renamed from: a */
    public void m764a(String str, Map map, boolean z, String str2) {
        if (AppLovinSdkUtils.isValidString(str)) {
            if (z) {
                String str3 = "&postback_ts=" + System.currentTimeMillis();
                str = str + str3;
                if (AppLovinSdkUtils.isValidString(str2)) {
                    str2 = str2 + str3;
                }
            }
            m750a(new db(str, map, 0, str2));
        }
    }

    /* renamed from: b */
    public void m765b() {
        synchronized (this.f712e) {
            Iterator it = this.f711d.iterator();
            while (it.hasNext()) {
                m755c((db) it.next());
            }
            this.f711d.clear();
        }
    }
}
