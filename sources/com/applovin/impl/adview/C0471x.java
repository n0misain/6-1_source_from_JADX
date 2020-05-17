package com.applovin.impl.adview;

import android.content.Context;
import android.view.View;
import com.applovin.sdk.AppLovinSdk;

/* renamed from: com.applovin.impl.adview.x */
public abstract class C0471x extends View {
    /* renamed from: a */
    protected final AppLovinSdk f306a;
    /* renamed from: b */
    protected final Context f307b;

    C0471x(AppLovinSdk appLovinSdk, Context context) {
        super(context);
        this.f307b = context;
        this.f306a = appLovinSdk;
    }

    /* renamed from: a */
    public static C0471x m365a(AppLovinSdk appLovinSdk, Context context, C0493y c0493y) {
        return c0493y.equals(C0493y.Invisible) ? new bz(appLovinSdk, context) : c0493y.equals(C0493y.WhiteXOnTransparentGrey) ? new cb(appLovinSdk, context) : new ci(appLovinSdk, context);
    }

    /* renamed from: a */
    public abstract void mo2181a(int i);
}
