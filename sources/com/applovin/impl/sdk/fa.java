package com.applovin.impl.sdk;

import org.json.JSONObject;

class fa implements C0499w {
    /* renamed from: a */
    final /* synthetic */ ez f892a;

    fa(ez ezVar) {
        this.f892a = ezVar;
    }

    /* renamed from: a */
    public void mo2274a(int i) {
        this.f892a.e.mo2288d("TaskReportReward", "Failed to report reward for ad: " + this.f892a.f891a.getAdIdNumber() + " - error code: " + i);
    }

    /* renamed from: a */
    public void m1043a(JSONObject jSONObject, int i) {
        this.f892a.e.mo2288d("TaskReportReward", "Reported reward successfully for ad: " + this.f892a.f891a.getAdIdNumber());
    }
}
