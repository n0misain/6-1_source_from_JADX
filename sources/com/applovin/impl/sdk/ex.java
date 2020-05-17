package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinErrorCodes;
import io.fabric.sdk.android.services.network.HttpRequest;
import org.json.JSONObject;

abstract class ex extends di implements C0499w {
    /* renamed from: a */
    private final String f732a;
    /* renamed from: b */
    private final Object f733b;
    /* renamed from: g */
    private final C0499w f734g;
    /* renamed from: h */
    private String f735h;
    /* renamed from: i */
    private String f736i;
    /* renamed from: j */
    private JSONObject f737j;
    /* renamed from: k */
    private int f738k;
    /* renamed from: l */
    private boolean f739l = true;
    /* renamed from: m */
    private int f740m = 1;
    /* renamed from: n */
    private long f741n;
    /* renamed from: o */
    private dl f742o = null;
    /* renamed from: p */
    private dl f743p = null;

    ex(String str, Object obj, String str2, AppLovinSdkImpl appLovinSdkImpl) {
        super(str2, appLovinSdkImpl);
        this.f732a = str;
        this.f733b = obj;
        this.f738k = ((Integer) appLovinSdkImpl.get(dj.f791t)).intValue();
        this.f741n = ((Long) appLovinSdkImpl.get(dj.f788q)).longValue();
        this.f734g = new ey(this, str2, appLovinSdkImpl);
    }

    /* renamed from: c */
    private void m789c(dl dlVar) {
        if (dlVar != null) {
            dm settingsManager = this.d.getSettingsManager();
            settingsManager.m819a(dlVar, dlVar.m813c());
            settingsManager.m822b();
        }
    }

    /* renamed from: a */
    public void mo2274a(int i) {
    }

    /* renamed from: a */
    public void m794a(long j) {
        this.f741n = j;
    }

    /* renamed from: a */
    public void m795a(dl dlVar) {
        this.f742o = dlVar;
    }

    /* renamed from: a */
    public void mo2275a(Object obj, int i) {
    }

    /* renamed from: a */
    public void m797a(String str) {
        this.f735h = str;
    }

    /* renamed from: a */
    public void m798a(JSONObject jSONObject) {
        this.f737j = jSONObject;
    }

    /* renamed from: a */
    public void m799a(boolean z) {
        this.f739l = z;
    }

    /* renamed from: b */
    public void m800b(int i) {
        this.f738k = i;
    }

    /* renamed from: b */
    public void m801b(dl dlVar) {
        this.f743p = dlVar;
    }

    /* renamed from: b */
    public void m802b(String str) {
        this.f736i = str;
    }

    /* renamed from: c */
    public void m803c(int i) {
        this.f740m = i;
    }

    public void run() {
        C0517v connectionManager = this.d.getConnectionManager();
        if (TextUtils.isEmpty(this.f735h) || this.f735h.length() < 4) {
            this.e.mo2289e(m676a(), "Task has an invalid or null request endpoint.");
            mo2274a((int) AppLovinErrorCodes.INVALID_URL);
            return;
        }
        String str = this.f732a;
        if (TextUtils.isEmpty(str)) {
            str = this.f737j == null ? HttpRequest.METHOD_GET : HttpRequest.METHOD_POST;
        }
        connectionManager.m1175a(this.f735h, str, this.f738k, this.f737j, this.f733b, this.f739l, this.f734g);
    }
}
