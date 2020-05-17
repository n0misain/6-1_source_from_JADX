package com.applovin.impl.sdk;

import com.applovin.impl.p000a.C0450a;
import com.applovin.impl.p000a.C0455f;
import com.applovin.impl.p000a.C0456g;
import com.applovin.impl.p000a.C0457h;
import com.applovin.impl.p000a.C0460k;
import com.applovin.impl.p000a.C0463n;
import com.applovin.impl.p000a.C0464o;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.HashSet;
import java.util.Set;

class ew extends di implements fi {
    /* renamed from: a */
    private C0456g f886a;
    /* renamed from: b */
    private final AppLovinAdLoadListener f887b;

    ew(C0456g c0456g, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskRenderVastAd", appLovinSdkImpl);
        if (c0456g == null) {
            throw new IllegalArgumentException("Unable to create TaskRenderVastAd. No context specified.");
        }
        this.f887b = appLovinAdLoadListener;
        this.f886a = c0456g;
    }

    /* renamed from: a */
    private void m1022a(C0457h c0457h, Throwable th) {
        this.e.mo2290e(this.c, "Failed to render valid VAST ad", th);
        C0463n.m233a(this.f886a, this.f886a.m209e(), this.f887b, c0457h, -6, this.d);
    }

    /* renamed from: c */
    public String mo2281c() {
        return "tRVA";
    }

    public void run() {
        C0455f c0455f = null;
        this.e.mo2288d(this.c, "Rendering VAST ad...");
        String str = "";
        String str2 = "";
        int size = this.f886a.m206b().size();
        Set hashSet = new HashSet(size);
        Set hashSet2 = new HashSet(size);
        C0464o c0464o = null;
        C0460k c0460k = null;
        for (fl flVar : this.f886a.m206b()) {
            fl flVar2;
            C0460k c0460k2;
            String str3;
            fl c = flVar2.m1100c(C0463n.m239a(flVar2) ? "Wrapper" : "InLine");
            if (c != null) {
                flVar2 = c.m1100c("AdSystem");
                if (flVar2 != null) {
                    c0460k = C0460k.m219a(flVar2, c0460k, this.d);
                }
                str = C0463n.m230a(c, "AdTitle", str);
                str2 = C0463n.m230a(c, "Description", str2);
                C0463n.m235a(c.m1097a("Impression"), hashSet, this.d);
                C0463n.m235a(c.m1097a("Error"), hashSet2, this.d);
                flVar2 = c.m1098b("Creatives");
                if (flVar2 != null) {
                    for (fl flVar22 : flVar22.m1102d()) {
                        fl b = flVar22.m1098b("Linear");
                        if (b != null) {
                            c0464o = C0464o.m245a(b, c0464o, this.f886a, this.d);
                        } else {
                            b = flVar22.m1100c("CompanionAds");
                            if (b != null) {
                                flVar22 = b.m1100c("Companion");
                                if (flVar22 != null) {
                                    c0455f = C0455f.m200a(flVar22, c0455f, this.d);
                                }
                            } else {
                                this.e.mo2289e(this.c, "Received and will skip rendering for an unidentified creative: " + flVar22);
                            }
                        }
                    }
                }
                c0460k2 = c0460k;
                str3 = str2;
                str2 = str;
            } else {
                this.e.mo2289e(this.c, "Did not find wrapper or inline response for node: " + flVar22);
                c0460k2 = c0460k;
                str3 = str2;
                str2 = str;
            }
            str = str2;
            str2 = str3;
            c0460k = c0460k2;
        }
        try {
            C0450a a = C0450a.m161g().m193a(this.d).m196a(this.f886a.m207c()).m199b(this.f886a.m208d()).m192a(this.f886a.m209e()).m194a(str).m197b(str2).m190a(c0460k).m191a(c0464o).m189a(c0455f).m195a(hashSet).m198b(hashSet2).m188a();
            C0457h a2 = C0463n.m226a(a);
            if (a2 == null) {
                this.d.m466a().m991a(new dx(a, this.f887b, this.d));
            } else {
                m1022a(a2, null);
            }
        } catch (Throwable th) {
            m1022a(C0457h.GENERAL_WRAPPER_ERROR, th);
        }
    }
}
