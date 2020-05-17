package com.applovin.impl.sdk;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.p000a.C0450a;
import com.applovin.impl.p000a.C0455f;
import com.applovin.impl.p000a.C0458i;
import com.applovin.impl.p000a.C0459j;
import com.applovin.impl.p000a.C0467r;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.concurrent.atomic.AtomicReference;

class dx extends ds {
    /* renamed from: a */
    private final C0450a f821a;

    public dx(C0450a c0450a, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskCacheVastAd", c0450a, appLovinAdLoadListener, appLovinSdkImpl);
        this.f821a = c0450a;
    }

    /* renamed from: d */
    private String m926d(String str) {
        AtomicReference atomicReference = new AtomicReference(null);
        this.d.getConnectionManager().m1174a(str, new dy(this, atomicReference, str));
        return (String) atomicReference.get();
    }

    /* renamed from: e */
    private void m927e() {
        if (this.f821a.m169a(this.d)) {
            C0455f e = this.f821a.m174e();
            if (e != null) {
                C0458i b = e.m202b();
                if (b != null) {
                    try {
                        Uri b2 = b.m217b();
                        String uri = b2 != null ? b2.toString() : "";
                        String c = b.m218c();
                        if (!URLUtil.isValidUrl(uri) && !AppLovinSdkUtils.isValidString(c)) {
                            this.e.mo2294w(this.c, "Companion ad does not have any resources attached. Skipping...");
                            return;
                        } else if (b.m214a() == C0459j.STATIC) {
                            this.e.mo2288d(this.c, "Caching static companion ad at " + uri + "...");
                            b2 = m905b(uri, false);
                            if (b2 != null) {
                                b.m215a(b2);
                                return;
                            } else {
                                this.e.mo2289e(this.c, "Failed to cache static companion ad");
                                return;
                            }
                        } else if (b.m214a() == C0459j.HTML) {
                            if (URLUtil.isValidUrl(uri)) {
                                this.e.mo2288d(this.c, "Begin caching HTML companion ad. Fetching from " + uri + "...");
                                c = m926d(uri);
                                if (AppLovinSdkUtils.isValidString(c)) {
                                    this.e.mo2288d(this.c, "HTML fetched. Caching HTML now...");
                                    b.m216a(m906c(c));
                                    return;
                                }
                                this.e.mo2289e(this.c, "Unable to load companion ad resources from " + uri);
                                return;
                            }
                            this.e.mo2288d(this.c, "Caching provided HTML for companion ad. No fetch required. HTML: " + c);
                            b.m216a(m906c(c));
                            return;
                        } else if (b.m214a() == C0459j.IFRAME) {
                            this.e.mo2288d(this.c, "Skip caching of iFrame resource...");
                            return;
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        this.e.mo2290e(this.c, "Failed to cache companion ad", th);
                        return;
                    }
                }
                this.e.mo2289e(this.c, "Failed to retrieve non-video resources from companion ad. Skipping...");
                return;
            }
            this.e.mo2288d(this.c, "No companion ad provided. Skipping...");
            return;
        }
        this.e.mo2288d(this.c, "Companion ad caching disabled. Skipping...");
    }

    /* renamed from: f */
    private void m928f() {
        if (!this.f821a.m171b(this.d)) {
            this.e.mo2288d(this.c, "Video caching disabled. Skipping...");
        } else if (this.f821a.mo2133a() != null) {
            C0467r c = this.f821a.m172c();
            if (c != null) {
                Uri b = c.m258b();
                if (b != null) {
                    b = m903a(b.toString(), false);
                    if (b != null) {
                        this.e.mo2288d(this.c, "Video file successfully cached into: " + b);
                        c.m257a(b);
                        return;
                    }
                    this.e.mo2289e(this.c, "Failed to cache video file: " + c);
                }
            }
        }
    }

    public void run() {
        this.e.mo2288d(this.c, "Begin caching for VAST ad #" + this.f821a.getAdIdNumber() + "...");
        m907c();
        m927e();
        m928f();
        m908d();
        this.e.mo2288d(this.c, "Finished caching VAST ad #" + this.f821a.getAdIdNumber());
    }
}
