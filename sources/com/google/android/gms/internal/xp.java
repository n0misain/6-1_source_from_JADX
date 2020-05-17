package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class xp {
    public static xm zza(Object obj, xm xmVar) throws DatabaseException {
        try {
            Map map;
            Object obj2;
            List list;
            Map hashMap;
            int i;
            String str;
            xm zza;
            Map hashMap2;
            if (obj instanceof Map) {
                map = (Map) obj;
                if (map.containsKey(".priority")) {
                    xmVar = xs.zzc(null, map.get(".priority"));
                }
                if (map.containsKey(".value")) {
                    obj2 = map.get(".value");
                    if (obj2 == null) {
                        return xd.zzJb();
                    }
                    if (obj2 instanceof String) {
                        return new xu((String) obj2, xmVar);
                    }
                    if (obj2 instanceof Long) {
                        return new xk((Long) obj2, xmVar);
                    }
                    if (obj2 instanceof Integer) {
                        return new xk(Long.valueOf((long) ((Integer) obj2).intValue()), xmVar);
                    }
                    if (obj2 instanceof Double) {
                        return new xc((Double) obj2, xmVar);
                    }
                    if (obj2 instanceof Boolean) {
                        return new wo((Boolean) obj2, xmVar);
                    }
                    if (!(obj2 instanceof Map) || (obj2 instanceof List)) {
                        if (obj2 instanceof Map) {
                            list = (List) obj2;
                            hashMap = new HashMap(list.size());
                            for (i = 0; i < list.size(); i++) {
                                str = i;
                                zza = zza(list.get(i), xd.zzJb());
                                if (!zza.isEmpty()) {
                                    hashMap.put(wp.zzgT(str), zza);
                                }
                            }
                            map = hashMap;
                        } else {
                            map = (Map) obj2;
                            if (map.containsKey(".sv")) {
                                return new xb(map, xmVar);
                            }
                            hashMap2 = new HashMap(map.size());
                            for (String str2 : map.keySet()) {
                                if (!str2.startsWith(".")) {
                                    zza = zza(map.get(str2), xd.zzJb());
                                    if (!zza.isEmpty()) {
                                        hashMap2.put(wp.zzgT(str2), zza);
                                    }
                                }
                            }
                            map = hashMap2;
                        }
                        return map.isEmpty() ? xd.zzJb() : new wr(ni.zza(map, wr.zzchR), xmVar);
                    } else {
                        String str3 = "Failed to parse node with class ";
                        String valueOf = String.valueOf(obj2.getClass().toString());
                        throw new DatabaseException(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                    }
                }
            }
            obj2 = obj;
            if (obj2 == null) {
                return xd.zzJb();
            }
            if (obj2 instanceof String) {
                return new xu((String) obj2, xmVar);
            }
            if (obj2 instanceof Long) {
                return new xk((Long) obj2, xmVar);
            }
            if (obj2 instanceof Integer) {
                return new xk(Long.valueOf((long) ((Integer) obj2).intValue()), xmVar);
            }
            if (obj2 instanceof Double) {
                return new xc((Double) obj2, xmVar);
            }
            if (obj2 instanceof Boolean) {
                return new wo((Boolean) obj2, xmVar);
            }
            if (obj2 instanceof Map) {
            }
            if (obj2 instanceof Map) {
                list = (List) obj2;
                hashMap = new HashMap(list.size());
                for (i = 0; i < list.size(); i++) {
                    str = i;
                    zza = zza(list.get(i), xd.zzJb());
                    if (!zza.isEmpty()) {
                        hashMap.put(wp.zzgT(str), zza);
                    }
                }
                map = hashMap;
            } else {
                map = (Map) obj2;
                if (map.containsKey(".sv")) {
                    return new xb(map, xmVar);
                }
                hashMap2 = new HashMap(map.size());
                for (String str22 : map.keySet()) {
                    if (!str22.startsWith(".")) {
                        zza = zza(map.get(str22), xd.zzJb());
                        if (!zza.isEmpty()) {
                            hashMap2.put(wp.zzgT(str22), zza);
                        }
                    }
                }
                map = hashMap2;
            }
            if (map.isEmpty()) {
            }
        } catch (Throwable e) {
            throw new DatabaseException("Failed to parse node", e);
        }
    }
}
