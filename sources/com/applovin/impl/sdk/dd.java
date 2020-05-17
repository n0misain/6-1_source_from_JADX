package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class dd implements aj, AppLovinNativeAdLoadListener {
    /* renamed from: a */
    protected final AppLovinSdkImpl f680a;
    /* renamed from: b */
    protected final AppLovinLogger f681b;
    /* renamed from: c */
    protected final Object f682c = new Object();
    /* renamed from: d */
    protected final Map f683d = mo2253a();
    /* renamed from: e */
    protected final Map f684e = new HashMap();
    /* renamed from: f */
    protected final Set f685f = new HashSet();

    dd(AppLovinSdkImpl appLovinSdkImpl) {
        this.f680a = appLovinSdkImpl;
        this.f681b = appLovinSdkImpl.getLogger();
    }

    /* renamed from: h */
    private de m698h(C0505i c0505i) {
        return (de) this.f683d.get(c0505i);
    }

    /* renamed from: a */
    abstract di mo2251a(C0505i c0505i);

    /* renamed from: a */
    abstract C0505i mo2252a(bu buVar);

    /* renamed from: a */
    abstract Map mo2253a();

    /* renamed from: a */
    abstract void mo2255a(Object obj, bu buVar);

    /* renamed from: a */
    abstract void mo2256a(Object obj, C0505i c0505i, int i);

    /* renamed from: a */
    public boolean mo2257a(C0505i c0505i, Object obj) {
        boolean z;
        synchronized (this.f682c) {
            if (m714g(c0505i)) {
                z = false;
            } else {
                mo2259b(c0505i, obj);
                z = true;
            }
        }
        return z;
    }

    /* renamed from: b */
    public bu mo2258b(C0505i c0505i) {
        bu e;
        synchronized (this.f682c) {
            e = m698h(c0505i).m776e();
        }
        return e;
    }

    /* renamed from: b */
    void m706b(bu buVar) {
        mo2263f(mo2252a(buVar));
    }

    /* renamed from: b */
    protected void m707b(C0505i c0505i, int i) {
        this.f681b.mo2288d("PreloadManager", "Failed to pre-load an ad of spec " + c0505i + ", error code " + i);
        synchronized (this.f682c) {
            Object remove = this.f684e.remove(c0505i);
            this.f685f.add(c0505i);
        }
        if (remove != null) {
            try {
                mo2256a(remove, c0505i, i);
            } catch (Throwable th) {
                this.f680a.getLogger().userError("PreloadManager", "Encountered exception while invoking user callback", th);
            }
        }
    }

    /* renamed from: b */
    public void mo2259b(C0505i c0505i, Object obj) {
        synchronized (this.f682c) {
            if (this.f684e.containsKey(c0505i)) {
                this.f681b.mo2294w("PreloadManager", "Possibly missing prior registered preload callback.");
            }
            this.f684e.put(c0505i, obj);
        }
    }

    /* renamed from: c */
    protected void m709c(bu buVar) {
        synchronized (this.f682c) {
            C0505i a = mo2252a(buVar);
            Object obj = this.f684e.get(a);
            this.f684e.remove(a);
            this.f685f.add(a);
            if (obj == null) {
                m698h(a).m772a(buVar);
                this.f681b.mo2288d("PreloadManager", "Ad enqueued: " + buVar);
            } else {
                this.f681b.mo2288d("PreloadManager", "Additional callback found, skipping enqueue.");
            }
        }
        if (obj != null) {
            this.f681b.mo2288d("PreloadManager", "Called additional callback regarding " + buVar);
            try {
                mo2255a(obj, buVar);
            } catch (Throwable th) {
                this.f680a.getLogger().userError("PreloadManager", "Encountered throwable while notifying user callback", th);
            }
            m706b(buVar);
        }
        this.f681b.mo2288d("PreloadManager", "Pulled ad from network and saved to preload cache: " + buVar);
    }

    /* renamed from: c */
    public boolean mo2260c(C0505i c0505i) {
        boolean c;
        synchronized (this.f682c) {
            c = m698h(c0505i).m774c();
        }
        return c;
    }

    /* renamed from: d */
    public void mo2261d(C0505i c0505i) {
        int i = 0;
        if (c0505i != null) {
            int b;
            synchronized (this.f682c) {
                de h = m698h(c0505i);
                b = h != null ? h.m773b() - h.m771a() : 0;
            }
            if (b > 0) {
                while (i < b) {
                    mo2263f(c0505i);
                    i++;
                }
            }
        }
    }

    /* renamed from: e */
    public boolean mo2262e(C0505i c0505i) {
        boolean z;
        synchronized (this.f682c) {
            z = !m698h(c0505i).m775d();
        }
        return z;
    }

    /* renamed from: f */
    public void mo2263f(C0505i c0505i) {
        if (((Boolean) this.f680a.get(dj.f751F)).booleanValue() && !mo2260c(c0505i)) {
            this.f681b.mo2288d("PreloadManager", "Preloading ad for spec " + c0505i + "...");
            this.f680a.m466a().m993a(mo2251a(c0505i), ej.MAIN, 500);
        }
    }

    /* renamed from: g */
    boolean m714g(C0505i c0505i) {
        boolean contains;
        synchronized (this.f682c) {
            contains = this.f685f.contains(c0505i);
        }
        return contains;
    }
}
