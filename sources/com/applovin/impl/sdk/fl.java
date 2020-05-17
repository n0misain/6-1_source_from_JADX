package com.applovin.impl.sdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class fl {
    /* renamed from: a */
    public static final fl f910a = new fl();
    /* renamed from: b */
    protected String f911b;
    /* renamed from: c */
    protected final List f912c;
    /* renamed from: d */
    private final fl f913d;
    /* renamed from: e */
    private final String f914e;
    /* renamed from: f */
    private final Map f915f;

    private fl() {
        this.f913d = null;
        this.f914e = "";
        this.f915f = Collections.emptyMap();
        this.f911b = "";
        this.f912c = Collections.emptyList();
    }

    public fl(String str, Map map, fl flVar) {
        this.f913d = flVar;
        this.f914e = str;
        this.f915f = Collections.unmodifiableMap(map);
        this.f912c = new ArrayList();
    }

    /* renamed from: a */
    public String m1096a() {
        return this.f914e;
    }

    /* renamed from: a */
    public List m1097a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Unable to get all direct children nodes. No name specified.");
        }
        List arrayList = new ArrayList(this.f912c.size());
        for (fl flVar : this.f912c) {
            if (str.equalsIgnoreCase(flVar.m1096a())) {
                arrayList.add(flVar);
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public fl m1098b(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Unable to get the first direct child node. No name specified.");
        }
        for (fl flVar : this.f912c) {
            if (str.equalsIgnoreCase(flVar.m1096a())) {
                return flVar;
            }
        }
        return null;
    }

    /* renamed from: b */
    public Map m1099b() {
        return this.f915f;
    }

    /* renamed from: c */
    public fl m1100c(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Unable to get the first child node. No name specified.");
        }
        if (this.f912c.size() > 0) {
            List arrayList = new ArrayList();
            arrayList.add(this);
            while (!arrayList.isEmpty()) {
                fl flVar = (fl) arrayList.get(0);
                arrayList.remove(0);
                if (str.equalsIgnoreCase(flVar.m1096a())) {
                    return flVar;
                }
                arrayList.addAll(flVar.m1102d());
            }
        }
        return null;
    }

    /* renamed from: c */
    public String m1101c() {
        return this.f911b;
    }

    /* renamed from: d */
    public List m1102d() {
        return Collections.unmodifiableList(this.f912c);
    }

    public String toString() {
        return "XmlNode{, elementName='" + this.f914e + '\'' + ", text='" + this.f911b + '\'' + ", attributes=" + this.f915f + '}';
    }
}
