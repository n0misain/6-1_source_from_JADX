package com.applovin.impl.adview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.applovin.impl.sdk.C0503g;
import com.applovin.impl.sdk.dn;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

class ae extends Dialog implements ad {
    /* renamed from: a */
    private final Activity f204a;
    /* renamed from: b */
    private final AppLovinSdk f205b;
    /* renamed from: c */
    private final AppLovinLogger f206c;
    /* renamed from: d */
    private final View f207d;
    /* renamed from: e */
    private final C0503g f208e;
    /* renamed from: f */
    private RelativeLayout f209f;
    /* renamed from: g */
    private C0471x f210g;

    ae(C0503g c0503g, View view, Activity activity, AppLovinSdk appLovinSdk) {
        super(activity, 16973840);
        if (c0503g == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (view == null) {
            throw new IllegalArgumentException("No main view specified");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else {
            this.f205b = appLovinSdk;
            this.f206c = appLovinSdk.getLogger();
            this.f204a = activity;
            this.f207d = view;
            this.f208e = c0503g;
            requestWindowFeature(1);
            setCancelable(false);
            try {
                getWindow().setFlags(activity.getWindow().getAttributes().flags, activity.getWindow().getAttributes().flags);
            } catch (Throwable th) {
                this.f206c.mo2290e("ExpandedAdDialog", "Setting window flags failed.", th);
            }
        }
    }

    /* renamed from: a */
    private int m294a(int i) {
        return AppLovinSdkUtils.dpToPx(this.f204a, i);
    }

    /* renamed from: a */
    private void m295a() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.f207d.setLayoutParams(layoutParams);
        this.f209f = new RelativeLayout(this.f204a);
        this.f209f.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.f209f.setBackgroundColor(-1157627904);
        this.f209f.addView(this.f207d);
        if (!this.f208e.m1129j()) {
            m297a(this.f208e.m1130k());
            m302c();
        }
        setContentView(this.f209f);
    }

    /* renamed from: a */
    private void m297a(C0493y c0493y) {
        int i = 9;
        if (this.f210g != null) {
            this.f206c.mo2294w("ExpandedAdDialog", "Attempting to create duplicate close button");
            return;
        }
        this.f210g = C0471x.m365a(this.f205b, getContext(), c0493y);
        this.f210g.setVisibility(8);
        this.f210g.setOnClickListener(new af(this));
        this.f210g.setClickable(false);
        dn dnVar = new dn(this.f205b);
        int a = m294a(dnVar.m850Z());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(a, a);
        layoutParams.addRule(10);
        layoutParams.addRule(dnVar.ac() ? 9 : 11);
        this.f210g.mo2181a(a);
        int a2 = m294a(dnVar.ab());
        int a3 = m294a(dnVar.aa());
        layoutParams.setMargins(a3, a2, a3, 0);
        this.f209f.addView(this.f210g, layoutParams);
        this.f210g.bringToFront();
        int a4 = m294a(dnVar.ad());
        View view = new View(this.f204a);
        view.setBackgroundColor(0);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a + a4, a + a4);
        layoutParams2.addRule(10);
        if (!dnVar.ac()) {
            i = 11;
        }
        layoutParams2.addRule(i);
        layoutParams2.setMargins(a3 - m294a(5), a2 - m294a(5), a3 - m294a(5), 0);
        view.setOnClickListener(new ag(this));
        this.f209f.addView(view, layoutParams2);
        view.bringToFront();
    }

    /* renamed from: a */
    private void m298a(String str) {
        if (this.f207d instanceof WebView) {
            try {
                this.f206c.mo2288d("ExpandedAdDialog", "Forwarding close action to the ad template");
                ((WebView) this.f207d).loadUrl(str);
                return;
            } catch (Throwable th) {
                this.f206c.mo2290e("ExpandedAdDialog", "Unable to forward close action to template. Dismissing...", th);
                dismiss();
                return;
            }
        }
        this.f206c.mo2294w("ExpandedAdDialog", "Expanded main view is not a WebView, dismissing the dialog");
        dismiss();
    }

    /* renamed from: b */
    private void m300b() {
        m298a("javascript:al_onCloseTapped();");
    }

    /* renamed from: c */
    private void m302c() {
        this.f204a.runOnUiThread(new ah(this));
    }

    public void dismiss() {
        this.f209f.removeView(this.f207d);
        super.dismiss();
    }

    public void onBackPressed() {
        m298a("javascript:al_onBackPressed();");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m295a();
    }
}
