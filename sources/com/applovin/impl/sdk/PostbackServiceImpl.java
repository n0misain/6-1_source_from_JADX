package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinPostbackService;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

public class PostbackServiceImpl implements AppLovinPostbackService {
    /* renamed from: a */
    private final AppLovinSdkImpl f457a;

    PostbackServiceImpl(AppLovinSdkImpl appLovinSdkImpl) {
        this.f457a = appLovinSdkImpl;
    }

    public void dispatchPostbackAsync(String str, AppLovinPostbackListener appLovinPostbackListener) {
        dispatchPostbackAsync(str, null, null, appLovinPostbackListener);
    }

    public void dispatchPostbackAsync(String str, Map map, String str2, int i, long j, int i2, AppLovinPostbackListener appLovinPostbackListener) {
        if (AppLovinSdkUtils.isValidString(str)) {
            di ebVar = new eb(this.f457a, str, map, new dc(this, appLovinPostbackListener));
            ebVar.m934a(i);
            ebVar.m935a(j);
            ebVar.m937b(i2);
            ebVar.m936a(str2);
            this.f457a.m466a().m992a(ebVar, ej.POSTBACKS);
            return;
        }
        this.f457a.getLogger().mo2289e("PostbackService", "Requested a postback dispatch for an empty URL; nothing to do...");
        if (appLovinPostbackListener != null) {
            appLovinPostbackListener.onPostbackFailure(str, AppLovinErrorCodes.INVALID_URL);
        }
    }

    public void dispatchPostbackAsync(String str, Map map, String str2, AppLovinPostbackListener appLovinPostbackListener) {
        dispatchPostbackAsync(str, map, str2, ((Integer) this.f457a.get(dj.aR)).intValue(), ((Long) this.f457a.get(dj.f788q)).longValue(), ((Integer) this.f457a.get(dj.aQ)).intValue(), appLovinPostbackListener);
    }
}
