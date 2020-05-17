package com.applovin.impl.adview;

import android.content.Context;
import com.applovin.sdk.AppLovinSdk;

public class bz extends C0471x {
    /* renamed from: c */
    private float f308c = 30.0f;
    /* renamed from: d */
    private float f309d = 1.0f;

    public bz(AppLovinSdk appLovinSdk, Context context) {
        super(appLovinSdk, context);
    }

    /* renamed from: a */
    public void m367a(float f) {
        this.f309d = f;
    }

    /* renamed from: a */
    public void mo2181a(int i) {
        m367a(((float) i) / this.f308c);
    }
}
