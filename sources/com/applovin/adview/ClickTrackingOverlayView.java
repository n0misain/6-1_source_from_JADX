package com.applovin.adview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.applovin.impl.sdk.dn;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

public class ClickTrackingOverlayView extends RelativeLayout {
    public ClickTrackingOverlayView(Context context, AppLovinSdk appLovinSdk) {
        super(context, null, new dn(appLovinSdk).m836L());
        m118a(context, appLovinSdk);
    }

    /* renamed from: a */
    private void m118a(Context context, AppLovinSdk appLovinSdk) {
        LayoutParams layoutParams;
        dn dnVar = new dn(appLovinSdk);
        View progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        int K = dnVar.m835K();
        if (K == -2 || K == -1) {
            layoutParams = new RelativeLayout.LayoutParams(K, K);
        } else {
            K = AppLovinSdkUtils.dpToPx(context, K);
            layoutParams = new RelativeLayout.LayoutParams(K, K);
        }
        layoutParams.addRule(13);
        progressBar.setLayoutParams(layoutParams);
        setBackgroundColor(Color.parseColor(dnVar.m834J()));
        addView(progressBar);
    }
}
