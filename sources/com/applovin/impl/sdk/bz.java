package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Collection;

class bz {
    /* renamed from: a */
    private final String f618a;
    /* renamed from: b */
    private final String f619b;

    bz(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified");
        } else if (str2 == null) {
            throw new IllegalArgumentException("No classname specified");
        } else {
            this.f618a = str.toLowerCase();
            this.f619b = str2;
        }
    }

    /* renamed from: a */
    static bz m645a(String str, AppLovinLogger appLovinLogger) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            return null;
        }
        if (str.contains(":")) {
            try {
                int indexOf = str.indexOf(58);
                if (indexOf > 0 && indexOf < str.length() - 1) {
                    return new bz(str.substring(0, indexOf).toLowerCase(), str.substring(indexOf + 1, str.length()));
                }
                appLovinLogger.userError("MediationAdapterManager", "Unable to parse config '" + str + "': malformed string");
                return null;
            } catch (Throwable th) {
                appLovinLogger.userError("MediationAdapterManager", "Unable to parse config '" + str + "'", th);
                return null;
            }
        }
        String toLowerCase = str.toLowerCase();
        if (by.f611a.containsKey(toLowerCase)) {
            return new bz(toLowerCase, (String) by.f611a.get(toLowerCase));
        }
        appLovinLogger.userError("MediationAdapterManager", "Unable to create config '" + str + "': unknown name");
        return null;
    }

    /* renamed from: a */
    static String m646a(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (bz c : collection) {
            stringBuilder.append(c.m650c());
            stringBuilder.append(',');
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /* renamed from: b */
    static Collection m647b(String str, AppLovinLogger appLovinLogger) {
        if (appLovinLogger == null) {
            throw new IllegalArgumentException("No logger specified");
        }
        Collection arrayList = new ArrayList();
        if (AppLovinSdkUtils.isValidString(str)) {
            for (String a : str.split(",")) {
                bz a2 = m645a(a, appLovinLogger);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    String m648a() {
        return this.f619b;
    }

    /* renamed from: b */
    String m649b() {
        return this.f618a;
    }

    /* renamed from: c */
    String m650c() {
        return this.f618a + ":" + this.f619b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bz bzVar = (bz) obj;
        return (this.f618a == null ? bzVar.f618a != null : !this.f618a.equals(bzVar.f618a)) ? false : this.f619b != null ? this.f619b.equals(bzVar.f619b) : bzVar.f619b == null;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f618a != null ? this.f618a.hashCode() : 0) * 31;
        if (this.f619b != null) {
            i = this.f619b.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "[Adapter Spec: " + m650c() + "]";
    }
}
