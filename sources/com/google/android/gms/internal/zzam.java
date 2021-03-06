package com.google.android.gms.internal;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public final class zzam {
    public static String zza(Map<String, String> map) {
        String str = "ISO-8859-1";
        String str2 = (String) map.get(HttpRequest.HEADER_CONTENT_TYPE);
        if (str2 != null) {
            String[] split = str2.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals(HttpRequest.PARAM_CHARSET)) {
                    return split2[1];
                }
            }
        }
        return str;
    }

    public static zzc zzb(zzn zzn) {
        Object obj;
        long j;
        Object obj2;
        long currentTimeMillis = System.currentTimeMillis();
        Map map = zzn.zzy;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        String str = (String) map.get(HttpRequest.HEADER_DATE);
        if (str != null) {
            j2 = zzf(str);
        }
        str = (String) map.get(HttpRequest.HEADER_CACHE_CONTROL);
        if (str != null) {
            String[] split = str.split(",");
            obj = null;
            j = 0;
            j4 = 0;
            for (String trim : split) {
                String trim2 = trim2.trim();
                if (trim2.equals("no-cache") || trim2.equals("no-store")) {
                    return null;
                }
                if (trim2.startsWith("max-age=")) {
                    try {
                        j4 = Long.parseLong(trim2.substring(8));
                    } catch (Exception e) {
                    }
                } else if (trim2.startsWith("stale-while-revalidate=")) {
                    try {
                        j = Long.parseLong(trim2.substring(23));
                    } catch (Exception e2) {
                    }
                } else if (trim2.equals("must-revalidate") || trim2.equals("proxy-revalidate")) {
                    obj = 1;
                }
            }
            j3 = j4;
            j4 = j;
            obj2 = 1;
        } else {
            obj = null;
            obj2 = null;
        }
        str = (String) map.get(HttpRequest.HEADER_EXPIRES);
        long zzf = str != null ? zzf(str) : 0;
        str = (String) map.get(HttpRequest.HEADER_LAST_MODIFIED);
        long zzf2 = str != null ? zzf(str) : 0;
        str = (String) map.get(HttpRequest.HEADER_ETAG);
        if (obj2 != null) {
            j3 = currentTimeMillis + (1000 * j3);
            j = obj != null ? j3 : (1000 * j4) + j3;
        } else if (j2 <= 0 || zzf < j2) {
            j = 0;
            j3 = 0;
        } else {
            j = (zzf - j2) + currentTimeMillis;
            j3 = j;
        }
        zzc zzc = new zzc();
        zzc.data = zzn.data;
        zzc.zza = str;
        zzc.zze = j3;
        zzc.zzd = j;
        zzc.zzb = j2;
        zzc.zzc = zzf2;
        zzc.zzf = map;
        return zzc;
    }

    private static long zzf(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException e) {
            return 0;
        }
    }
}
