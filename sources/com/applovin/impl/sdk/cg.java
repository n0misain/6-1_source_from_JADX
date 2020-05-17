package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediatedAdInfo;
import com.applovin.mediation.AppLovinMediationDisplayListener;
import com.applovin.mediation.AppLovinMediationErrorCode;
import java.util.Map;

class cg implements AppLovinMediationDisplayListener {
    /* renamed from: a */
    final /* synthetic */ cf f642a;

    cg(cf cfVar) {
        this.f642a = cfVar;
    }

    public void adClicked(AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        this.f642a.f637a.m577c(this.f642a.f638b);
    }

    public void adDisplayed(AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        this.f642a.f637a.m568a(this.f642a.f638b);
    }

    public void adHidden(AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        this.f642a.f637a.m573b(this.f642a.f638b);
    }

    public void failedToDisplayAd(AppLovinMediatedAdInfo appLovinMediatedAdInfo, AppLovinMediationErrorCode appLovinMediationErrorCode) {
        this.f642a.f637a.m573b(this.f642a.f638b);
    }

    public void rewardRejected(AppLovinMediatedAdInfo appLovinMediatedAdInfo, Map map) {
        this.f642a.f637a.m576b(map, this.f642a.f638b);
    }

    public void rewardVerified(AppLovinMediatedAdInfo appLovinMediatedAdInfo, Map map) {
        this.f642a.f637a.m572a(map, this.f642a.f638b);
    }
}
