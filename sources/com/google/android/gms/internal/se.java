package com.google.android.gms.internal;

import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class se {
    public static pz zza(pz pzVar, Map<String, Object> map) {
        pz zzGI = pz.zzGI();
        Iterator it = pzVar.iterator();
        pz pzVar2 = zzGI;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            pzVar2 = pzVar2.zze((qr) entry.getKey(), zza((xm) entry.getValue(), (Map) map));
        }
        return pzVar2;
    }

    public static xm zza(xm xmVar, Map<String, Object> map) {
        Map map2;
        Object value = xmVar.zzIR().getValue();
        if (value instanceof Map) {
            map2 = (Map) value;
            if (map2.containsKey(".sv")) {
                value = map.get((String) map2.get(".sv"));
            }
        }
        xm zzc = xs.zzc(null, value);
        if (xmVar.zzIQ()) {
            value = xmVar.getValue();
            if (value instanceof Map) {
                map2 = (Map) value;
                if (map2.containsKey(".sv")) {
                    String str = (String) map2.get(".sv");
                    if (map.containsKey(str)) {
                        value = map.get(str);
                    }
                }
            }
            return (value.equals(xmVar.getValue()) && zzc.equals(xmVar.zzIR())) ? xmVar : xp.zza(value, zzc);
        } else if (xmVar.isEmpty()) {
            return xmVar;
        } else {
            wr wrVar = (wr) xmVar;
            sh shVar = new sh(wrVar);
            wrVar.zza(new sg(map, shVar), false);
            return !shVar.zzHm().zzIR().equals(zzc) ? shVar.zzHm().zzf(zzc) : shVar.zzHm();
        }
    }

    public static Map<String, Object> zza(ys ysVar) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(Param.TIMESTAMP, Long.valueOf(ysVar.zzJF()));
        return hashMap;
    }
}
