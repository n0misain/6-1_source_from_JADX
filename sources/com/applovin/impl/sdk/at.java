package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

class at implements AppLovinAdClickListener, AppLovinAdDisplayListener, AppLovinAdRewardListener, AppLovinAdVideoPlaybackListener {
    /* renamed from: a */
    final /* synthetic */ am f528a;
    /* renamed from: b */
    private final Activity f529b;
    /* renamed from: c */
    private final AppLovinAdDisplayListener f530c;
    /* renamed from: d */
    private final AppLovinAdClickListener f531d;
    /* renamed from: e */
    private final AppLovinAdVideoPlaybackListener f532e;
    /* renamed from: f */
    private final AppLovinAdRewardListener f533f;

    private at(am amVar, Activity activity, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        this.f528a = amVar;
        this.f530c = appLovinAdDisplayListener;
        this.f531d = appLovinAdClickListener;
        this.f532e = appLovinAdVideoPlaybackListener;
        this.f533f = appLovinAdRewardListener;
        this.f529b = activity;
    }

    public void adClicked(AppLovinAd appLovinAd) {
        if (this.f531d != null) {
            this.f528a.f504f.post(new aw(this, appLovinAd));
        }
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        if (this.f530c != null) {
            this.f530c.adDisplayed(appLovinAd);
        }
    }

    public void adHidden(AppLovinAd appLovinAd) {
        ae aeVar = (ae) appLovinAd;
        String b = this.f528a.m560b();
        if (AppLovinSdkUtils.isValidString(b) && this.f528a.f508j) {
            this.f528a.m558a(b, this.f529b);
        } else {
            String str;
            int i;
            this.f528a.f507i.m1062a(true);
            if (this.f528a.f508j) {
                str = "network_timeout";
                i = AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT;
            } else {
                str = "user_closed_video";
                i = AppLovinErrorCodes.INCENTIVIZED_USER_CLOSED_VIDEO;
            }
            cy.m743a().m745a(aeVar, str);
            if (this.f528a.f508j) {
                this.f528a.m558a(b, this.f529b);
            }
            this.f528a.f504f.post(new au(this, appLovinAd, i));
        }
        if (this.f530c != null) {
            this.f528a.f504f.post(new av(this, appLovinAd));
        }
        this.f528a.f499a.m466a().m992a(new ez(aeVar, this.f528a.f499a), ej.BACKGROUND);
        this.f528a.m551e();
    }

    public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
    }

    public void userOverQuota(AppLovinAd appLovinAd, Map map) {
        this.f528a.m557a("quota_exceeded");
        if (this.f533f != null) {
            this.f528a.f504f.post(new ba(this, appLovinAd, map));
        }
    }

    public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
        this.f528a.m557a("rejected");
        if (this.f533f != null) {
            this.f528a.f504f.post(new bb(this, appLovinAd, map));
        }
    }

    public void userRewardVerified(AppLovinAd appLovinAd, Map map) {
        this.f528a.m557a("accepted");
        if (this.f533f != null) {
            this.f528a.f504f.post(new az(this, appLovinAd, map));
        }
    }

    public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
        this.f528a.m557a("network_timeout");
        if (this.f533f != null) {
            this.f528a.f504f.post(new bc(this, appLovinAd, i));
        }
    }

    public void videoPlaybackBegan(AppLovinAd appLovinAd) {
        if (this.f532e != null) {
            this.f528a.f504f.post(new ax(this, appLovinAd));
        }
    }

    public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
        if (this.f532e != null) {
            this.f528a.f504f.post(new ay(this, appLovinAd, d, z));
        }
        this.f528a.f508j = z;
    }
}
