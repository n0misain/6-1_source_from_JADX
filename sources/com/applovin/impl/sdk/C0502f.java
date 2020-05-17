package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.applovin.impl.sdk.f */
public class C0502f extends dd {
    C0502f(AppLovinSdkImpl appLovinSdkImpl) {
        super(appLovinSdkImpl);
    }

    /* renamed from: h */
    private static dl m1027h(C0505i c0505i) {
        AppLovinAdSize a = c0505i.m1133a();
        if (c0505i.m1134b() == AppLovinAdType.REGULAR) {
            if (a.equals(AppLovinAdSize.BANNER)) {
                if (c0505i.m1135c() == C0506j.DIRECT) {
                    return dj.an;
                }
                if (c0505i.m1135c() == C0506j.INDIRECT) {
                    return dj.ao;
                }
            } else if (a.equals(AppLovinAdSize.MREC)) {
                if (c0505i.m1135c() == C0506j.DIRECT) {
                    return dj.ap;
                }
                if (c0505i.m1135c() == C0506j.INDIRECT) {
                    return dj.aq;
                }
            } else if (a.equals(AppLovinAdSize.INTERSTITIAL)) {
                if (c0505i.m1135c() == C0506j.DIRECT) {
                    return dj.ar;
                }
                if (c0505i.m1135c() == C0506j.INDIRECT) {
                    return dj.as;
                }
            } else if (a.equals(AppLovinAdSize.LEADER)) {
                if (c0505i.m1135c() == C0506j.DIRECT) {
                    return dj.at;
                }
                if (c0505i.m1135c() == C0506j.INDIRECT) {
                    return dj.au;
                }
            }
        } else if (c0505i.m1134b() == AppLovinAdType.INCENTIVIZED) {
            if (c0505i.m1135c() == C0506j.DIRECT) {
                return dj.av;
            }
            if (c0505i.m1135c() == C0506j.INDIRECT) {
                return dj.aw;
            }
        }
        return dj.an;
    }

    /* renamed from: a */
    di mo2251a(C0505i c0505i) {
        di edVar = new ed(c0505i, this, this.a);
        edVar.m961a(true);
        return edVar;
    }

    /* renamed from: a */
    C0505i mo2252a(bu buVar) {
        return ((C0449k) buVar).mo2131m();
    }

    /* renamed from: a */
    Map mo2253a() {
        Collection<C0505i> d = C0505i.m1132d();
        Map hashMap = new HashMap(d.size());
        for (C0505i c0505i : d) {
            if (!c0505i.equals(C0505i.f933h)) {
                hashMap.put(c0505i, new de(((Integer) this.a.get(C0502f.m1027h(c0505i))).intValue()));
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public void mo2254a(C0505i c0505i, int i) {
        m707b(c0505i, i);
    }

    /* renamed from: a */
    void mo2255a(Object obj, bu buVar) {
        ((AppLovinAdLoadListener) obj).adReceived((AppLovinAd) buVar);
    }

    /* renamed from: a */
    void mo2256a(Object obj, C0505i c0505i, int i) {
        if (obj instanceof aj) {
            ((aj) obj).mo2254a(c0505i, i);
        }
        if (obj instanceof AppLovinAdLoadListener) {
            ((AppLovinAdLoadListener) obj).failedToReceiveAd(i);
        }
    }

    public void adReceived(AppLovinAd appLovinAd) {
        m709c((bu) appLovinAd);
    }

    /* renamed from: b */
    public /* bridge */ /* synthetic */ bu mo2258b(C0505i c0505i) {
        return super.mo2258b(c0505i);
    }

    /* renamed from: b */
    public /* bridge */ /* synthetic */ void mo2259b(C0505i c0505i, Object obj) {
        super.mo2259b(c0505i, obj);
    }

    /* renamed from: c */
    public /* bridge */ /* synthetic */ boolean mo2260c(C0505i c0505i) {
        return super.mo2260c(c0505i);
    }

    /* renamed from: d */
    public /* bridge */ /* synthetic */ void mo2261d(C0505i c0505i) {
        super.mo2261d(c0505i);
    }

    /* renamed from: e */
    public /* bridge */ /* synthetic */ boolean mo2262e(C0505i c0505i) {
        return super.mo2262e(c0505i);
    }

    /* renamed from: f */
    public /* bridge */ /* synthetic */ void mo2263f(C0505i c0505i) {
        super.mo2263f(c0505i);
    }

    public void failedToReceiveAd(int i) {
    }

    public void onNativeAdsFailedToLoad(int i) {
    }

    public void onNativeAdsLoaded(List list) {
    }
}
