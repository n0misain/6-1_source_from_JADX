package com.applovin.impl.sdk;

import java.util.Map;

class ai implements ea {
    /* renamed from: a */
    final /* synthetic */ df f493a;
    /* renamed from: b */
    final /* synthetic */ EventServiceImpl f494b;

    ai(EventServiceImpl eventServiceImpl, df dfVar) {
        this.f494b = eventServiceImpl;
        this.f493a = dfVar;
    }

    /* renamed from: a */
    public void mo2231a(aa aaVar) {
        try {
            Map a = this.f494b.m480a(this.f493a, aaVar);
            this.f494b.f430a.getPersistentPostbackManager().m764a(this.f494b.m476a(this.f494b.m477a(), a).toString(), this.f493a.m778b(), true, this.f494b.m476a(this.f494b.m484b(), a).toString());
        } catch (Throwable e) {
            this.f494b.f430a.getLogger().mo2290e("EventServiceImpl", "Unable to track event due to failure to convert event parameters into JSONObject for event: " + this.f493a, e);
        }
    }
}
