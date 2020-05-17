package com.applovin.impl.sdk;

import android.content.pm.ApplicationInfo;
import com.applovin.mediation.AppLovinMediationAdapterConfig;
import com.applovin.sdk.AppLovinLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

class bx implements AppLovinMediationAdapterConfig {
    /* renamed from: a */
    private final AppLovinSdkImpl f603a;
    /* renamed from: b */
    private final AppLovinLogger f604b;
    /* renamed from: c */
    private final String f605c;
    /* renamed from: d */
    private final String f606d;
    /* renamed from: e */
    private final Object f607e = new Object();
    /* renamed from: f */
    private Map f608f;
    /* renamed from: g */
    private final Set f609g = new HashSet(5);
    /* renamed from: h */
    private final Map f610h = new HashMap(5);

    bx(String str, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (str == null) {
            throw new IllegalArgumentException("No adapter classname specified");
        } else {
            this.f603a = appLovinSdkImpl;
            this.f604b = appLovinSdkImpl.getLogger();
            this.f605c = str.toLowerCase();
            this.f606d = "applovin.mediation." + str + ":config";
        }
    }

    /* renamed from: a */
    private Map m629a() {
        try {
            String string = this.f603a.getSettingsManager().m817a().getString(this.f606d, null);
            if (string == null || string.isEmpty()) {
                this.f604b.mo2288d("MediationAdapterConfigWrapper", "Last known config for '" + this.f605c + "' is missing");
                return null;
            }
            Map a = bt.m617a(new JSONObject(string));
            this.f604b.mo2288d("MediationAdapterConfigWrapper", "Last known config for '" + this.f605c + "' is: " + a);
            return a;
        } catch (Throwable th) {
            this.f604b.mo2290e("MediationAdapterConfigWrapper", "Unable to load the last known configuration for " + this.f605c, th);
            return null;
        }
    }

    /* renamed from: a */
    void m630a(Map map) {
        synchronized (this.f607e) {
            if (map != null) {
                if (!map.isEmpty()) {
                    this.f608f = map;
                    m631b(map);
                }
            }
            this.f608f = m629a();
        }
    }

    /* renamed from: b */
    void m631b(Map map) {
        if (map != null) {
            try {
                this.f603a.getSettingsManager().m817a().edit().putString(this.f606d, bt.m618a(map).toString()).apply();
                synchronized (this.f607e) {
                    this.f608f = map;
                }
            } catch (Throwable th) {
                this.f604b.mo2290e("MediationAdapterConfigWrapper", "Unable to save the last known configuration for " + this.f605c, th);
            }
        }
    }

    public Boolean getBoolean(String str, Boolean bool) {
        if (str == null) {
            throw new IllegalArgumentException("No key specified");
        }
        String string = getString(str, null);
        return string != null ? Boolean.valueOf(Boolean.parseBoolean(string)) : bool;
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, Boolean.valueOf(false)).booleanValue();
    }

    public int getInt(String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("No key specified");
        }
        String string = getString(str, null);
        if (string != null && fk.m1094d(string)) {
            try {
                i = Integer.parseInt(string);
            } catch (Throwable e) {
                this.f604b.mo2295w("MediationAdapterConfigWrapper", "Unable to parse int for " + str, e);
            }
        }
        return i;
    }

    public long getLong(String str, long j) {
        if (str == null) {
            throw new IllegalArgumentException("No key specified");
        }
        String string = getString(str, null);
        if (string != null) {
            try {
                string = string.replace("L", "").trim();
                if (fk.m1094d(string)) {
                    j = Long.parseLong(string);
                }
            } catch (Throwable e) {
                this.f604b.mo2295w("MediationAdapterConfigWrapper", "Unable to parse long for " + str, e);
            }
        }
        return j;
    }

    public String getString(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("No key specified");
        }
        synchronized (this.f607e) {
            String str3;
            if (this.f608f == null || !this.f608f.containsKey(str)) {
                String str4 = "applovin.mediation." + this.f605c + ":" + str;
                if (this.f610h.containsKey(str4)) {
                    str3 = (String) this.f610h.get(str4);
                    return str3;
                } else if (this.f609g.contains(str4)) {
                    return str2;
                } else {
                    try {
                        ApplicationInfo applicationInfo = this.f603a.getApplicationContext().getPackageManager().getApplicationInfo(this.f603a.getApplicationContext().getPackageName(), 128);
                        if (applicationInfo.metaData != null) {
                            str3 = String.valueOf(applicationInfo.metaData.get(str4));
                            if (str3 != null) {
                                this.f610h.put(str4, str3);
                                return str3;
                            }
                            this.f609g.add(str4);
                        }
                    } catch (Throwable th) {
                        this.f604b.mo2290e("MediationAdapterConfigWrapper", "Unable to load " + str + "from AndroidManifest.xml", th);
                    }
                    return str2;
                }
            }
            str3 = (String) this.f608f.get(str);
            return str3;
        }
    }
}
