package com.applovin.impl.sdk;

import android.content.pm.ApplicationInfo;
import com.applovin.mediation.AppLovinMediationAdapter;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class by {
    /* renamed from: a */
    private static final Map f611a = new HashMap();
    /* renamed from: b */
    private final AppLovinSdkImpl f612b;
    /* renamed from: c */
    private final AppLovinLogger f613c;
    /* renamed from: d */
    private final Object f614d = new Object();
    /* renamed from: e */
    private final Map f615e = new HashMap();
    /* renamed from: f */
    private final Collection f616f = new HashSet();
    /* renamed from: g */
    private final Collection f617g = new HashSet();

    static {
        f611a.put("chartboost", "com.applovin.mediation.impl.AppLovinChartboostMediationAdapter");
        f611a.put("facebook", "com.applovin.mediation.impl.AppLovinFacebookMediationAdapter");
        f611a.put("google", "com.applovin.mediation.impl.AppLovinGoogleMediationAdapter");
        f611a.put("heyzap", "com.applovin.mediation.impl.AppLovinHeyzapMediationAdapter");
        f611a.put("inmobi", "com.applovin.mediation.impl.AppLovinInMobiMediationAdapter");
        f611a.put("mopub", "com.applovin.mediation.impl.AppLovinMoPubMediationAdapter");
        f611a.put("ironsource", "com.applovin.mediation.impl.AppLovinIronSourceMediationAdapter");
        f611a.put("vungle", "com.applovin.mediation.impl.AppLovinVungleMediationAdapter");
        f611a.put("unity", "com.applovin.mediation.impl.AppLovinUnityMediationAdapter");
    }

    by(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f612b = appLovinSdkImpl;
        this.f613c = appLovinSdkImpl.getLogger();
    }

    /* renamed from: a */
    private ca m632a(bz bzVar, Map map) {
        if (bzVar == null) {
            throw new IllegalArgumentException("No adapter spec specified");
        }
        synchronized (this.f614d) {
            String a = bzVar.m648a();
            if (this.f616f.contains(a)) {
                this.f613c.mo2288d("MediationAdapterManager", "Not attempting to load " + bzVar + " due to prior errors");
                return null;
            } else if (this.f615e.containsKey(a)) {
                r0 = (ca) this.f615e.get(a);
                return r0;
            } else {
                r0 = m635b(bzVar, map);
                if (r0 != null) {
                    this.f613c.mo2288d("MediationAdapterManager", "Loaded " + bzVar);
                    this.f615e.put(a, r0);
                    return r0;
                }
                this.f613c.mo2289e("MediationAdapterManager", "Failed to load " + bzVar);
                this.f616f.add(a);
                return null;
            }
        }
    }

    /* renamed from: a */
    private String m633a(String str) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = this.f612b.getApplicationContext().getPackageManager().getApplicationInfo(this.f612b.getApplicationContext().getPackageName(), 128);
            Collection<bz> b = bz.m647b(applicationInfo.metaData.getString("applovin.mediation:load"), this.f613c);
            if (!b.isEmpty()) {
                for (bz bzVar : b) {
                    if (bzVar.m649b().equalsIgnoreCase(str) && AppLovinSdkUtils.isValidString(bzVar.m648a())) {
                        return bzVar.m648a();
                    }
                }
            }
            return applicationInfo.metaData.getString("applovin.mediation." + str + ":" + "class");
        } catch (Throwable th) {
            this.f613c.mo2290e("MediationAdapterManager", "Unable to retrieve classname from Android Manifest for adapter name: " + str, th);
            return null;
        }
    }

    /* renamed from: a */
    private void m634a(Collection collection, String str) {
        for (bz bzVar : collection) {
            ca a = m640a(bzVar.m649b(), bzVar.m648a(), null);
            if (a != null) {
                this.f613c.mo2291i("MediationAdapterManager", "Loaded " + str + " adapter: " + a);
            }
        }
    }

    /* renamed from: b */
    private ca m635b(bz bzVar, Map map) {
        try {
            Class cls = Class.forName(bzVar.m648a());
            if (cls != null) {
                Object newInstance = cls.newInstance();
                if (newInstance instanceof AppLovinMediationAdapter) {
                    ca caVar = new ca(bzVar.m649b(), (AppLovinMediationAdapter) newInstance, this.f612b);
                    caVar.m667a(map);
                    if (caVar.m668b()) {
                        return caVar;
                    }
                    this.f613c.userError("MediationAdapterManager", "Failed to initialize " + bzVar);
                    return null;
                }
                this.f613c.userError("MediationAdapterManager", bzVar + " error: not an instance of '" + AppLovinMediationAdapter.class.getName() + "'.");
                return null;
            }
            this.f613c.userError("MediationAdapterManager", "No class found for " + bzVar);
            return null;
        } catch (Throwable th) {
            this.f613c.userError("MediationAdapterManager", "Failed to load: " + bzVar, th);
            return null;
        }
    }

    /* renamed from: e */
    private Collection m637e() {
        try {
            ApplicationInfo applicationInfo = this.f612b.getApplicationContext().getPackageManager().getApplicationInfo(this.f612b.getApplicationContext().getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                return Collections.emptyList();
            }
            String string = applicationInfo.metaData.getString("applovin.mediation:load");
            Collection<bz> b = bz.m647b(string, this.f613c);
            if (b == null || b.isEmpty()) {
                this.f613c.mo2288d("MediationAdapterManager", "No adapter specs found in: '" + string + "'");
                return Collections.emptyList();
            }
            Collection arrayList = new ArrayList(b.size());
            for (bz bzVar : b) {
                if (!AppLovinSdkUtils.isValidString(bzVar.m649b())) {
                    this.f613c.userError("MediationAdapterManager", "Ignored loading of adapter with class " + bzVar.m648a() + ": no name specified");
                } else if (AppLovinSdkUtils.isValidString(bzVar.m648a())) {
                    arrayList.add(bzVar);
                } else {
                    String string2 = applicationInfo.metaData.getString("applovin.mediation." + bzVar.m649b() + ":" + "class");
                    if (AppLovinSdkUtils.isValidString(string2)) {
                        arrayList.add(new bz(bzVar.m649b(), string2));
                    } else {
                        string2 = (String) f611a.get(bzVar.m649b());
                        if (AppLovinSdkUtils.isValidString(string2)) {
                            arrayList.add(new bz(bzVar.m649b(), string2));
                        } else {
                            this.f613c.userError("MediationAdapterManager", "Ignored loading of " + bzVar.m649b() + ": no default adapter class found");
                        }
                    }
                }
            }
            return arrayList;
        } catch (Throwable th) {
            this.f613c.userError("MediationAdapterManager", "Unable to load applovin.mediation:loadfrom AndroidManifest.xml", th);
            return Collections.emptyList();
        }
    }

    /* renamed from: f */
    private Collection m638f() {
        return bz.m647b(this.f612b.getSettingsManager().m817a().getString("applovin.mediation:load", ""), this.f613c);
    }

    /* renamed from: g */
    private void m639g() {
        String a;
        synchronized (this.f614d) {
            a = bz.m646a(this.f617g);
        }
        this.f612b.getSettingsManager().m817a().edit().putString("applovin.mediation:load", a).apply();
    }

    /* renamed from: a */
    ca m640a(String str, String str2, Map map) {
        if (AppLovinSdkUtils.isValidString(str)) {
            String str3;
            if (AppLovinSdkUtils.isValidString(str2)) {
                this.f613c.mo2288d("MediationAdapterManager", "Loading adapter using explicit classname: " + str2);
                str3 = str2;
            } else if (map == null || !map.containsKey("class")) {
                str3 = m633a(str);
                if (!AppLovinSdkUtils.isValidString(str3)) {
                    str3 = (String) f611a.get(str.toLowerCase());
                    if (AppLovinSdkUtils.isValidString(str3)) {
                        this.f613c.mo2288d("MediationAdapterManager", "Loading '" + str + "' adapter using resolved default classname: " + str3);
                    } else {
                        this.f613c.mo2294w("MediationAdapterManager", "Unable to find default classname for '" + str + "'");
                        return null;
                    }
                }
            } else {
                str3 = (String) map.get("class");
                if (AppLovinSdkUtils.isValidString(str3)) {
                    this.f613c.mo2288d("MediationAdapterManager", "Loading '" + str + "' adapter using configured classname: " + str3);
                } else {
                    this.f613c.mo2294w("MediationAdapterManager", "Invalid configured classname for '" + str + "'");
                    return null;
                }
            }
            return m632a(new bz(str, str3), map);
        }
        this.f613c.mo2289e("MediationAdapterManager", "No adapter name provided for " + str2 + ", not loading the adapter ");
        return null;
    }

    /* renamed from: a */
    void m641a() {
        synchronized (this.f614d) {
            if (((Boolean) this.f612b.get(dj.cx)).booleanValue()) {
                m634a(m638f(), "last used");
            }
            if (((Boolean) this.f612b.get(dj.cy)).booleanValue()) {
                m634a(m637e(), "AndroidManifest");
            }
        }
    }

    /* renamed from: a */
    void m642a(ca caVar) {
        if (caVar != null) {
            bz bzVar = new bz(caVar.m662a(), caVar.m671e());
            synchronized (this.f614d) {
                if (!this.f617g.contains(bzVar)) {
                    this.f617g.add(bzVar);
                    m639g();
                }
            }
        }
    }

    /* renamed from: b */
    Collection m643b() {
        Collection arrayList;
        synchronized (this.f614d) {
            arrayList = new ArrayList(this.f616f);
        }
        return arrayList;
    }

    /* renamed from: c */
    Collection m644c() {
        Collection arrayList;
        synchronized (this.f614d) {
            arrayList = new ArrayList(this.f615e.values());
        }
        return arrayList;
    }
}
