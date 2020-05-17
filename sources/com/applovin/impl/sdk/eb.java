package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinSdkUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Map;
import org.json.JSONObject;

public class eb extends di {
    /* renamed from: a */
    private final String f829a;
    /* renamed from: b */
    private final Map f830b;
    /* renamed from: g */
    private final AppLovinPostbackListener f831g;
    /* renamed from: h */
    private String f832h;
    /* renamed from: i */
    private int f833i;
    /* renamed from: j */
    private long f834j;
    /* renamed from: k */
    private int f835k = -1;

    public eb(AppLovinSdkImpl appLovinSdkImpl, String str, Map map, AppLovinPostbackListener appLovinPostbackListener) {
        super("TaskDispatchPostback", appLovinSdkImpl);
        this.f829a = str;
        this.f831g = appLovinPostbackListener;
        this.f830b = map;
    }

    /* renamed from: a */
    public void m934a(int i) {
        this.f833i = i;
    }

    /* renamed from: a */
    public void m935a(long j) {
        this.f834j = j;
    }

    /* renamed from: a */
    public void m936a(String str) {
        this.f832h = str;
    }

    /* renamed from: b */
    public void m937b(int i) {
        this.f835k = i;
    }

    public void run() {
        if (AppLovinSdkUtils.isValidString(this.f829a)) {
            ex ecVar = new ec(this, this.f830b == null ? HttpRequest.METHOD_GET : HttpRequest.METHOD_POST, new JSONObject(), "RepeatTaskDispatchPostback", this.d);
            ecVar.m797a(this.f829a);
            ecVar.m802b(this.f832h);
            ecVar.m798a(this.f830b == null ? null : new JSONObject(this.f830b));
            ecVar.m794a(this.f834j);
            ecVar.m803c(this.f833i < 0 ? ((Integer) this.d.get(dj.aR)).intValue() : this.f833i);
            ecVar.m800b(this.f835k < 0 ? ((Integer) this.d.get(dj.aQ)).intValue() : this.f835k);
            ecVar.m799a(false);
            ecVar.run();
            return;
        }
        this.d.getLogger().mo2291i("TaskDispatchPostback", "Requested URL is not valid; nothing to do...");
        this.f831g.onPostbackFailure(this.f829a, AppLovinErrorCodes.INVALID_URL);
    }
}
