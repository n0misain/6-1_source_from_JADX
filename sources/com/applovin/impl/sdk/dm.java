package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkSettings;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

class dm {
    /* renamed from: a */
    private final AppLovinSdkImpl f802a;
    /* renamed from: b */
    private final AppLovinLogger f803b;
    /* renamed from: c */
    private final Context f804c;
    /* renamed from: d */
    private final Object[] f805d = new Object[dj.m809b()];

    dm(AppLovinSdkImpl appLovinSdkImpl) {
        this.f802a = appLovinSdkImpl;
        this.f803b = appLovinSdkImpl.getLogger();
        this.f804c = appLovinSdkImpl.getApplicationContext();
    }

    /* renamed from: a */
    private static dl m814a(String str) {
        for (dl dlVar : dj.m808a()) {
            if (dlVar.m812b().equals(str)) {
                return dlVar;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static Object m815a(String str, JSONObject jSONObject, Object obj) throws JSONException {
        if (obj instanceof Boolean) {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        }
        if (obj instanceof Float) {
            return Float.valueOf((float) jSONObject.getDouble(str));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(jSONObject.getInt(str));
        }
        if (obj instanceof Long) {
            return Long.valueOf(jSONObject.getLong(str));
        }
        if (obj instanceof String) {
            return jSONObject.getString(str);
        }
        throw new RuntimeException("SDK Error: unknown value type: " + obj.getClass());
    }

    /* renamed from: e */
    private String m816e() {
        return "com.applovin.sdk." + fk.m1081a(this.f802a.getSdkKey()) + ".";
    }

    /* renamed from: a */
    public SharedPreferences m817a() {
        if (this.f804c != null) {
            return this.f804c.getSharedPreferences("com.applovin.sdk.1", 0);
        }
        throw new IllegalArgumentException("No context specified");
    }

    /* renamed from: a */
    public Object m818a(dl dlVar) {
        if (dlVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        }
        Object obj;
        synchronized (this.f805d) {
            try {
                obj = this.f805d[dlVar.m810a()];
                if (obj != null) {
                    obj = dlVar.m811a(obj);
                } else {
                    obj = dlVar.m813c();
                }
            } catch (Throwable th) {
                this.f802a.getLogger().mo2289e("SettingsManager", "Unable to retrieve value for setting " + dlVar.m812b() + "; using default...");
                obj = dlVar.m813c();
            }
        }
        return obj;
    }

    /* renamed from: a */
    public void m819a(dl dlVar, Object obj) {
        if (dlVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        } else if (obj == null) {
            throw new IllegalArgumentException("No new value specified");
        } else {
            synchronized (this.f805d) {
                this.f805d[dlVar.m810a()] = obj;
            }
            this.f803b.mo2288d("SettingsManager", "Setting update: " + dlVar.m812b() + " set to \"" + obj + "\"");
        }
    }

    /* renamed from: a */
    void m820a(AppLovinSdkSettings appLovinSdkSettings) {
        long j = 0;
        boolean z = false;
        this.f803b.mo2291i("SettingsManager", "Loading user-defined settings...");
        if (appLovinSdkSettings != null) {
            synchronized (this.f805d) {
                boolean z2;
                boolean z3;
                this.f805d[dj.f780i.m810a()] = Boolean.valueOf(appLovinSdkSettings.isVerboseLoggingEnabled());
                long bannerAdRefreshSeconds = appLovinSdkSettings.getBannerAdRefreshSeconds();
                if (bannerAdRefreshSeconds >= 0) {
                    if (bannerAdRefreshSeconds > 0) {
                        j = Math.max(30, bannerAdRefreshSeconds);
                    }
                    this.f805d[dj.f797z.m810a()] = Long.valueOf(j);
                    this.f805d[dj.f796y.m810a()] = Boolean.valueOf(true);
                } else if (bannerAdRefreshSeconds == -1) {
                    this.f805d[dj.f796y.m810a()] = Boolean.valueOf(false);
                }
                String autoPreloadSizes = appLovinSdkSettings.getAutoPreloadSizes();
                if (!AppLovinSdkUtils.isValidString(autoPreloadSizes)) {
                    autoPreloadSizes = "NONE";
                }
                if (autoPreloadSizes.equals("NONE")) {
                    this.f805d[dj.f755J.m810a()] = "";
                    this.f805d[dj.f756K.m810a()] = "";
                } else {
                    this.f805d[dj.f755J.m810a()] = autoPreloadSizes;
                    this.f805d[dj.f756K.m810a()] = autoPreloadSizes;
                }
                autoPreloadSizes = appLovinSdkSettings.getAutoPreloadTypes();
                if (!AppLovinSdkUtils.isValidString(autoPreloadSizes)) {
                    autoPreloadSizes = "NONE";
                }
                if ("NONE".equals(autoPreloadSizes)) {
                    z2 = false;
                    z3 = false;
                } else {
                    z2 = false;
                    z3 = false;
                    for (String str : autoPreloadSizes.split(",")) {
                        if (str.equals(AppLovinAdType.REGULAR.getLabel())) {
                            z3 = true;
                        } else if (str.equals(AppLovinAdType.INCENTIVIZED.getLabel()) || str.contains("INCENT") || str.contains("REWARD")) {
                            z2 = true;
                        } else if (str.equals(AppLovinAdType.NATIVE.getLabel())) {
                            z = true;
                        }
                    }
                }
                if (!z3) {
                    this.f805d[dj.f755J.m810a()] = "";
                    this.f805d[dj.f756K.m810a()] = "";
                }
                this.f805d[dj.f757L.m810a()] = Boolean.valueOf(z2);
                this.f805d[dj.f758M.m810a()] = Boolean.valueOf(z2);
                this.f805d[dj.aX.m810a()] = Boolean.valueOf(z);
                if (appLovinSdkSettings instanceof bs) {
                    for (Entry entry : ((bs) appLovinSdkSettings).m611b().entrySet()) {
                        this.f805d[((dl) entry.getKey()).m810a()] = entry.getValue();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    void m821a(JSONObject jSONObject) {
        this.f803b.mo2288d("SettingsManager", "Loading settings from JSON array...");
        synchronized (this.f805d) {
            String str = "";
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                if (str != null && str.length() > 0) {
                    try {
                        dl a = m814a(str);
                        if (a != null) {
                            Object a2 = m815a(str, jSONObject, a.m813c());
                            this.f805d[a.m810a()] = a2;
                            this.f803b.mo2288d("SettingsManager", "Setting update: " + a.m812b() + " set to \"" + a2 + "\"");
                        } else {
                            this.f803b.mo2294w("SettingsManager", "Unknown setting recieved: " + str);
                        }
                    } catch (Throwable e) {
                        this.f803b.mo2290e("SettingsManager", "Unable to parse JSON settings array", e);
                    } catch (Throwable e2) {
                        this.f803b.mo2290e("SettingsManager", "Unable to convert setting object ", e2);
                    }
                }
            }
        }
    }

    /* renamed from: b */
    void m822b() {
        if (this.f804c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.f803b.mo2291i("SettingsManager", "Saving settings with the application...");
        String e = m816e();
        Editor edit = m817a().edit();
        synchronized (this.f805d) {
            for (dl dlVar : dj.m808a()) {
                Object obj = this.f805d[dlVar.m810a()];
                if (obj != null) {
                    String str = e + dlVar.m812b();
                    if (obj instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Float) {
                        edit.putFloat(str, ((Float) obj).floatValue());
                    } else if (obj instanceof Integer) {
                        edit.putInt(str, ((Integer) obj).intValue());
                    } else if (obj instanceof Long) {
                        edit.putLong(str, ((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        edit.putString(str, (String) obj);
                    } else {
                        throw new RuntimeException("SDK Error: unknown value: " + obj.getClass());
                    }
                }
            }
        }
        edit.commit();
        this.f803b.mo2288d("SettingsManager", "Settings saved with the application.");
    }

    /* renamed from: c */
    void m823c() {
        if (this.f804c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.f803b.mo2291i("SettingsManager", "Loading settings saved with the application...");
        String e = m816e();
        SharedPreferences a = m817a();
        synchronized (this.f805d) {
            for (dl dlVar : dj.m808a()) {
                try {
                    Boolean valueOf;
                    String str = e + dlVar.m812b();
                    Object c = dlVar.m813c();
                    if (c instanceof Boolean) {
                        valueOf = Boolean.valueOf(a.getBoolean(str, ((Boolean) c).booleanValue()));
                    } else if (c instanceof Float) {
                        valueOf = Float.valueOf(a.getFloat(str, ((Float) c).floatValue()));
                    } else if (c instanceof Integer) {
                        valueOf = Integer.valueOf(a.getInt(str, ((Integer) c).intValue()));
                    } else if (c instanceof Long) {
                        valueOf = Long.valueOf(a.getLong(str, ((Long) c).longValue()));
                    } else if (c instanceof String) {
                        valueOf = a.getString(str, (String) c);
                    } else {
                        throw new RuntimeException("SDK Error: unknown value: " + c.getClass());
                    }
                    this.f805d[dlVar.m810a()] = valueOf;
                } catch (Throwable e2) {
                    this.f803b.mo2290e("SettingsManager", "Unable to load \"" + dlVar.m812b() + "\"", e2);
                }
            }
        }
    }

    /* renamed from: d */
    void m824d() {
        synchronized (this.f805d) {
            Arrays.fill(this.f805d, null);
        }
        Editor edit = m817a().edit();
        edit.clear();
        edit.commit();
    }
}
