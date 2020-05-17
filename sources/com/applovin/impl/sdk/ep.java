package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ep extends di implements AppLovinAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ eo f873a;
    /* renamed from: b */
    private final JSONArray f874b;
    /* renamed from: g */
    private final int f875g;

    ep(eo eoVar, int i, JSONArray jSONArray) {
        this.f873a = eoVar;
        super("TaskProcessNextWaterfallAd", eoVar.d);
        if (jSONArray == null) {
            throw new IllegalArgumentException("No ad objects array specified");
        } else if (i < 0 || i >= jSONArray.length()) {
            throw new IllegalArgumentException("Invalid ad index specified: " + i);
        } else {
            this.f874b = jSONArray;
            this.f875g = i;
        }
    }

    /* renamed from: a */
    private void m1004a(int i) throws JSONException {
        if ("adapter".equals(m1005b(i))) {
            this.d.m466a().m992a(new en(this.f873a.f871b, this.f874b.getJSONObject(i), this.f873a.f870a, this.d), ej.BACKGROUND);
        }
    }

    /* renamed from: b */
    private String m1005b(int i) {
        if (i < 0 || i >= this.f874b.length()) {
            return "undefined";
        }
        try {
            return bt.m615a(this.f874b.getJSONObject(i), "type", "undefined", this.d);
        } catch (JSONException e) {
            this.e.mo2289e(this.c, "Unable to parse next ad from the ad response");
            return "undefined";
        }
    }

    /* renamed from: c */
    private void m1006c() throws JSONException {
        JSONObject jSONObject = this.f874b.getJSONObject(this.f875g);
        String b = m1005b(this.f875g);
        if (AppLovinSdk.URI_SCHEME.equalsIgnoreCase(b)) {
            this.e.mo2288d(this.c, "Starting task for AppLovin ad...");
            this.d.m466a().m991a(new eu(this.f873a.f871b, jSONObject, this.f873a.f870a, this, this.d));
        } else if ("vast".equalsIgnoreCase(b)) {
            this.e.mo2288d(this.c, "Starting task for VAST ad...");
            this.d.m466a().m991a(eq.m1008a(this.f873a.f871b, jSONObject, this.f873a.f870a, this, this.d));
        } else if ("adapter".equalsIgnoreCase(b)) {
            this.e.mo2288d(this.c, "Starting task for adapter ad...");
            this.d.m466a().m991a(new eh(this.f873a.f871b, jSONObject, this.f873a.f870a, this, this.d));
        } else {
            this.e.mo2294w(this.c, "Unable to process ad of unknown type: " + b);
            failedToReceiveAd(AppLovinErrorCodes.INVALID_RESPONSE);
        }
    }

    public void adReceived(AppLovinAd appLovinAd) {
        this.f873a.m999a(appLovinAd);
    }

    public void failedToReceiveAd(int i) {
        if (this.f875g < this.f874b.length() - 1) {
            this.e.mo2291i(this.c, "Attempting to load next ad (" + this.f875g + ") after failure...");
            this.d.m466a().m992a(new ep(this.f873a, this.f875g + 1, this.f874b), ej.BACKGROUND);
            return;
        }
        this.f873a.m1002d();
    }

    public void run() {
        try {
            int i;
            if (this.f875g == 0) {
                int intValue = ((Integer) this.d.get(dj.cw)).intValue();
                i = 1;
                while (i <= intValue && i < this.f874b.length()) {
                    m1004a(i);
                    i++;
                }
            } else {
                i = ((Integer) this.d.get(dj.cw)).intValue() + this.f875g;
                if (i < this.f874b.length()) {
                    m1004a(i);
                }
            }
            m1006c();
        } catch (Throwable th) {
            this.e.mo2290e(this.c, "Encountered error while processing ad number " + this.f875g, th);
            this.f873a.m1002d();
        }
    }
}
