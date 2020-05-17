package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;

class dc implements AppLovinPostbackListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinPostbackListener f723a;
    /* renamed from: b */
    final /* synthetic */ PostbackServiceImpl f724b;

    dc(PostbackServiceImpl postbackServiceImpl, AppLovinPostbackListener appLovinPostbackListener) {
        this.f724b = postbackServiceImpl;
        this.f723a = appLovinPostbackListener;
    }

    public void onPostbackFailure(String str, int i) {
        this.f724b.f457a.getLogger().mo2289e("PostbackService", "Failed to dispatch postback. Error code: " + i + " URL: " + str);
        if (this.f723a != null) {
            this.f723a.onPostbackFailure(str, i);
        }
    }

    public void onPostbackSuccess(String str) {
        this.f724b.f457a.getLogger().mo2288d("PostbackService", "Successfully dispatched postback to URL: " + str);
        if (this.f723a != null) {
            this.f723a.onPostbackSuccess(str);
        }
    }
}
