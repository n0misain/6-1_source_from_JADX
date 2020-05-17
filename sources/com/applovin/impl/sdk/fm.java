package com.applovin.impl.sdk;

import android.util.Xml;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class fm {
    /* renamed from: a */
    private final AppLovinLogger f916a;
    /* renamed from: b */
    private Stack f917b;
    /* renamed from: c */
    private StringBuilder f918c;
    /* renamed from: d */
    private long f919d;
    /* renamed from: e */
    private fo f920e;

    fm(AppLovinSdk appLovinSdk) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to create xml parser. No sdk specified.");
        }
        this.f916a = appLovinSdk.getLogger();
    }

    /* renamed from: a */
    static fl m1104a(String str, AppLovinSdk appLovinSdk) throws SAXException {
        return new fm(appLovinSdk).m1113a(str);
    }

    /* renamed from: a */
    private Map m1108a(Attributes attributes) {
        if (attributes == null) {
            return Collections.emptyMap();
        }
        int length = attributes.getLength();
        Map hashMap = new HashMap(length);
        for (int i = 0; i < length; i++) {
            hashMap.put(attributes.getQName(i), attributes.getValue(i));
        }
        return hashMap;
    }

    /* renamed from: a */
    public fl m1113a(String str) throws SAXException {
        if (str == null) {
            throw new IllegalArgumentException("Unable to parse. No XML specified.");
        }
        this.f918c = new StringBuilder();
        this.f917b = new Stack();
        this.f920e = null;
        Xml.parse(str, new fn(this));
        if (this.f920e != null) {
            return this.f920e;
        }
        throw new SAXException("Unable to parse XML into node");
    }
}
