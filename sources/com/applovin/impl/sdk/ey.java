package com.applovin.impl.sdk;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinErrorCodes;

class ey implements C0499w {
    /* renamed from: a */
    final /* synthetic */ String f888a;
    /* renamed from: b */
    final /* synthetic */ AppLovinSdkImpl f889b;
    /* renamed from: c */
    final /* synthetic */ ex f890c;

    ey(ex exVar, String str, AppLovinSdkImpl appLovinSdkImpl) {
        this.f890c = exVar;
        this.f888a = str;
        this.f889b = appLovinSdkImpl;
    }

    /* renamed from: a */
    public void mo2274a(int i) {
        Object obj = 1;
        Object obj2 = (i < Callback.DEFAULT_DRAG_ANIMATION_DURATION || i >= 500) ? 1 : null;
        if (i == AppLovinErrorCodes.NO_NETWORK) {
            obj = null;
        }
        if (obj2 == null || r0 == null) {
            this.f890c.mo2274a(i);
        } else if (this.f890c.f740m > 0) {
            this.f890c.e.mo2294w(this.f888a, "Unable to send request due to server failure (code " + i + "). " + this.f890c.f740m + " attempts left, retrying in " + (((double) this.f890c.f741n) / 1000.0d) + " seconds...");
            this.f890c.f740m = this.f890c.f740m - 1;
            if (this.f890c.f740m == 0) {
                this.f890c.m789c(this.f890c.f742o);
                if (!TextUtils.isEmpty(this.f890c.f736i) && this.f890c.f736i.length() >= 4) {
                    this.f890c.f735h = this.f890c.f736i;
                    this.f890c.e.mo2291i(this.f890c.m676a(), "Switching to backup endpoint " + this.f890c.f736i);
                }
            }
            this.f889b.m466a().m993a(this.f890c, ej.BACKGROUND, this.f890c.f741n);
        } else {
            if (this.f890c.f736i == null || !this.f890c.f736i.equals(this.f890c.f735h)) {
                this.f890c.m789c(this.f890c.f742o);
            } else {
                this.f890c.m789c(this.f890c.f743p);
            }
            this.f890c.mo2274a(i);
        }
    }

    /* renamed from: a */
    public void mo2275a(Object obj, int i) {
        this.f890c.f740m = 0;
        this.f890c.mo2275a(obj, i);
    }
}
