package com.applovin.impl.adview;

import android.util.AttributeSet;
import com.applovin.adview.AppLovinAdView;
import com.applovin.sdk.AppLovinAdSize;

/* renamed from: com.applovin.impl.adview.p */
class C0485p {
    /* renamed from: a */
    static AppLovinAdSize m399a(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(AppLovinAdView.NAMESPACE, "size");
        return attributeValue != null ? AppLovinAdSize.fromString(attributeValue) : null;
    }

    /* renamed from: b */
    static boolean m400b(AttributeSet attributeSet) {
        return attributeSet != null && attributeSet.getAttributeBooleanValue(AppLovinAdView.NAMESPACE, "loadAdOnCreate", false);
    }
}
