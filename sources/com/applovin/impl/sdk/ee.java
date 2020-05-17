package com.applovin.impl.sdk;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import org.json.JSONObject;

class ee extends ex {
    /* renamed from: a */
    final /* synthetic */ ed f840a;

    ee(ed edVar, String str, JSONObject jSONObject, String str2, AppLovinSdkImpl appLovinSdkImpl) {
        this.f840a = edVar;
        super(str, jSONObject, str2, appLovinSdkImpl);
    }

    /* renamed from: a */
    public void mo2274a(int i) {
        this.f840a.m946b(i);
    }

    /* renamed from: a */
    public void m970a(JSONObject jSONObject, int i) {
        if (i == Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
            this.f840a.m947b(jSONObject);
        } else {
            this.f840a.m946b(i);
        }
    }
}
