package com.applovin.impl.p000a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.C0505i;
import com.applovin.impl.sdk.fk;
import com.applovin.impl.sdk.fl;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* renamed from: com.applovin.impl.a.n */
public class C0463n {
    /* renamed from: a */
    private static DateFormat f136a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    /* renamed from: b */
    private static Random f137b = new Random(System.currentTimeMillis());

    /* renamed from: a */
    public static Uri m225a(String str, long j, Uri uri, C0457h c0457h, AppLovinSdk appLovinSdk) {
        Uri uri2 = null;
        if (URLUtil.isValidUrl(str)) {
            try {
                String replace = str.replace("[ERRORCODE]", Integer.toString(c0457h.m211a()));
                if (j >= 0) {
                    replace = replace.replace("[CONTENTPLAYHEAD]", C0463n.m228a(j));
                }
                if (uri != null) {
                    replace = replace.replace("[ASSETURI]", uri.toString());
                }
                uri2 = Uri.parse(replace.replace("[CACHEBUSTING]", C0463n.m227a()).replace("[TIMESTAMP]", C0463n.m240b()));
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo2290e("VastUtils", "Unable to replace macros in URL string " + str, th);
            }
        } else {
            appLovinSdk.getLogger().mo2289e("VastUtils", "Unable to replace macros in invalid URL string.");
        }
        return uri2;
    }

    /* renamed from: a */
    public static C0457h m226a(C0450a c0450a) {
        return (C0463n.m241b(c0450a) || C0463n.m243c(c0450a)) ? null : C0457h.GENERAL_WRAPPER_ERROR;
    }

    /* renamed from: a */
    private static String m227a() {
        return Integer.toString(10000000 + f137b.nextInt(89999999));
    }

    /* renamed from: a */
    private static String m228a(long j) {
        if (j <= 0) {
            return "00:00:00.000";
        }
        long toHours = TimeUnit.SECONDS.toHours(j);
        long toMinutes = TimeUnit.SECONDS.toMinutes(j) % TimeUnit.MINUTES.toSeconds(1);
        long toSeconds = j % TimeUnit.MINUTES.toSeconds(1);
        return String.format("%02d:%02d:%02d.000", new Object[]{Long.valueOf(toHours), Long.valueOf(toMinutes), Long.valueOf(toSeconds)});
    }

    /* renamed from: a */
    public static String m229a(C0456g c0456g) {
        if (c0456g == null) {
            throw new IllegalArgumentException("Unable to get resolution uri string for fetching the next wrapper or inline response in the chain");
        }
        List b = c0456g.m206b();
        int size = c0456g.m206b().size();
        if (size > 0) {
            fl c = ((fl) b.get(size - 1)).m1100c("VASTAdTagURI");
            if (c != null) {
                return c.m1101c();
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m230a(fl flVar, String str, String str2) {
        fl b = flVar.m1098b(str);
        if (b == null) {
            return str2;
        }
        String c = b.m1101c();
        return AppLovinSdkUtils.isValidString(c) ? c : str2;
    }

    /* renamed from: a */
    private static Set m231a(C0456g c0456g, AppLovinSdk appLovinSdk) {
        if (c0456g == null) {
            return null;
        }
        List<fl> b = c0456g.m206b();
        HashSet hashSet = new HashSet(b.size());
        Set set = hashSet;
        for (fl flVar : b) {
            fl c = flVar.m1100c("Wrapper");
            if (c == null) {
                c = flVar.m1100c("InLine");
            }
            set = c != null ? C0463n.m232a(set, c.m1097a("Error"), appLovinSdk) : C0463n.m232a(set, flVar.m1097a("Error"), appLovinSdk);
        }
        appLovinSdk.getLogger().mo2288d("VastUtils", "Retrieved " + set.size() + " top level error trackers: " + set);
        return set;
    }

    /* renamed from: a */
    private static Set m232a(Set set, List list, AppLovinSdk appLovinSdk) {
        if (list != null) {
            for (fl a : list) {
                C0461l a2 = C0461l.m221a(a, appLovinSdk);
                if (a2 != null) {
                    set.add(a2);
                }
            }
        }
        return set;
    }

    /* renamed from: a */
    public static void m233a(C0456g c0456g, C0505i c0505i, AppLovinAdLoadListener appLovinAdLoadListener, C0457h c0457h, int i, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("Unable to handle failure. No sdk specified.");
        }
        if (c0456g != null) {
            c0505i = c0456g.m209e();
        }
        fk.m1087a(appLovinAdLoadListener, c0505i, i, appLovinSdkImpl);
        C0463n.m237a(C0463n.m231a(c0456g, (AppLovinSdk) appLovinSdkImpl), c0457h, appLovinSdkImpl);
    }

    /* renamed from: a */
    public static void m234a(fl flVar, Map map, AppLovinSdk appLovinSdk) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to render event trackers. No sdk specified.");
        } else if (flVar == null) {
            appLovinSdk.getLogger().mo2289e("VastUtils", "Unable to render event trackers; null node provided");
        } else if (map == null) {
            appLovinSdk.getLogger().mo2289e("VastUtils", "Unable to render event trackers; null event trackers provided");
        } else {
            fl b = flVar.m1098b("TrackingEvents");
            if (b != null) {
                List<fl> a = b.m1097a("Tracking");
                if (a != null) {
                    for (fl b2 : a) {
                        String str = (String) b2.m1099b().get("event");
                        if (AppLovinSdkUtils.isValidString(str)) {
                            C0461l a2 = C0461l.m221a(b2, appLovinSdk);
                            if (a2 != null) {
                                Set set = (Set) map.get(str);
                                if (set != null) {
                                    set.add(a2);
                                } else {
                                    set = new HashSet();
                                    set.add(a2);
                                    map.put(str, set);
                                }
                            }
                        } else {
                            appLovinSdk.getLogger().mo2289e("VastUtils", "Could not find event for tracking node = " + b2);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m235a(List list, Set set, AppLovinSdk appLovinSdk) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to render trackers. No sdk specified.");
        } else if (list == null) {
            appLovinSdk.getLogger().mo2289e("VastUtils", "Unable to render trackers; null nodes provided");
        } else if (set == null) {
            appLovinSdk.getLogger().mo2289e("VastUtils", "Unable to render trackers; null trackers provided");
        } else {
            for (fl a : list) {
                C0461l a2 = C0461l.m221a(a, appLovinSdk);
                if (a2 != null) {
                    set.add(a2);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m236a(Set set, long j, Uri uri, C0457h c0457h, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("Unable to fire trackers. No sdk specified.");
        } else if (set != null && !set.isEmpty()) {
            for (C0461l b : set) {
                Uri a = C0463n.m225a(b.m224b(), j, uri, c0457h, (AppLovinSdk) appLovinSdkImpl);
                if (a != null) {
                    appLovinSdkImpl.getPersistentPostbackManager().m763a(a.toString(), null, false);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m237a(Set set, C0457h c0457h, AppLovinSdkImpl appLovinSdkImpl) {
        C0463n.m236a(set, -1, null, c0457h, appLovinSdkImpl);
    }

    /* renamed from: a */
    public static void m238a(Set set, AppLovinSdkImpl appLovinSdkImpl) {
        C0463n.m236a(set, -1, null, C0457h.UNSPECIFIED, appLovinSdkImpl);
    }

    /* renamed from: a */
    public static boolean m239a(fl flVar) {
        if (flVar != null) {
            return flVar.m1100c("Wrapper") != null;
        } else {
            throw new IllegalArgumentException("Unable to check if a given XmlNode contains a wrapper response");
        }
    }

    /* renamed from: b */
    private static String m240b() {
        f136a.setTimeZone(TimeZone.getDefault());
        return f136a.format(new Date());
    }

    /* renamed from: b */
    public static boolean m241b(C0450a c0450a) {
        if (c0450a == null) {
            return false;
        }
        C0464o a = c0450a.mo2133a();
        if (a == null) {
            return false;
        }
        List a2 = a.m248a();
        return (a2 == null || a2.isEmpty()) ? false : true;
    }

    /* renamed from: b */
    public static boolean m242b(fl flVar) {
        if (flVar != null) {
            return flVar.m1100c("InLine") != null;
        } else {
            throw new IllegalArgumentException("Unable to check if a given XmlNode contains an inline response");
        }
    }

    /* renamed from: c */
    public static boolean m243c(C0450a c0450a) {
        if (c0450a == null) {
            return false;
        }
        C0455f e = c0450a.m174e();
        if (e == null) {
            return false;
        }
        C0458i b = e.m202b();
        return b != null ? b.m217b() != null || AppLovinSdkUtils.isValidString(b.m218c()) : false;
    }
}
