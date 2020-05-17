package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Pattern;

public final class zf {
    private static final Pattern zzcjD = Pattern.compile("[\\[\\]\\.#$]");
    private static final Pattern zzcjE = Pattern.compile("[\\[\\]\\.#\\$\\/\\u0000-\\u001F\\u007F]");

    public static void zzO(qr qrVar) throws DatabaseException {
        wp zzHc = qrVar.zzHc();
        Object obj = (zzHc == null || !zzHc.asString().startsWith(".")) ? 1 : null;
        if (obj == null) {
            String str = "Invalid write location: ";
            String valueOf = String.valueOf(qrVar.toString());
            throw new DatabaseException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    public static void zzam(Object obj) {
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (!map.containsKey(".sv")) {
                for (Entry entry : map.entrySet()) {
                    String str = (String) entry.getKey();
                    Object obj2 = (str == null || str.length() <= 0 || !(str.equals(".value") || str.equals(".priority") || (!str.startsWith(".") && !zzcjE.matcher(str).find()))) ? null : 1;
                    if (obj2 == null) {
                        throw new DatabaseException(new StringBuilder(String.valueOf(str).length() + 68).append("Invalid key: ").append(str).append(". Keys must not contain '/', '.', '#', '$', '[', or ']'").toString());
                    }
                    zzam(entry.getValue());
                }
            }
        } else if (obj instanceof List) {
            for (Object zzam : (List) obj) {
                zzam(zzam);
            }
        } else if ((obj instanceof Double) || (obj instanceof Float)) {
            double doubleValue = ((Double) obj).doubleValue();
            if (Double.isInfinite(doubleValue) || Double.isNaN(doubleValue)) {
                throw new DatabaseException("Invalid value: Value cannot be NaN, Inf or -Inf.");
            }
        }
    }

    public static Map<qr, xm> zzb(qr qrVar, Map<String, Object> map) throws DatabaseException {
        Map treeMap = new TreeMap();
        for (Entry entry : map.entrySet()) {
            qr qrVar2 = new qr((String) entry.getKey());
            Object value = entry.getValue();
            tn.zza(qrVar.zzh(qrVar2), value);
            String asString = !qrVar2.isEmpty() ? qrVar2.zzHf().asString() : "";
            if (asString.equals(".sv") || asString.equals(".value")) {
                String valueOf = String.valueOf(qrVar2);
                throw new DatabaseException(new StringBuilder((String.valueOf(valueOf).length() + 40) + String.valueOf(asString).length()).append("Path '").append(valueOf).append("' contains disallowed child name: ").append(asString).toString());
            }
            Object zzc = asString.equals(".priority") ? xs.zzc(qrVar2, value) : xp.zza(value, xd.zzJb());
            zzam(value);
            treeMap.put(qrVar2, zzc);
        }
        qr qrVar3 = null;
        for (qr qrVar4 : treeMap.keySet()) {
            boolean z = qrVar3 == null || qrVar3.zzj(qrVar4) < 0;
            zd.zzaF(z);
            if (qrVar3 == null || !qrVar3.zzi(qrVar4)) {
                qrVar3 = qrVar4;
            } else {
                String valueOf2 = String.valueOf(qrVar3);
                asString = String.valueOf(qrVar4);
                throw new DatabaseException(new StringBuilder((String.valueOf(valueOf2).length() + 42) + String.valueOf(asString).length()).append("Path '").append(valueOf2).append("' is an ancestor of '").append(asString).append("' in an update.").toString());
            }
        }
        return treeMap;
    }

    public static void zzhb(String str) throws DatabaseException {
        if ((!zzcjD.matcher(str).find() ? 1 : null) == null) {
            throw new DatabaseException(new StringBuilder(String.valueOf(str).length() + 101).append("Invalid Firebase Database path: ").append(str).append(". Firebase Database paths must not contain '.', '#', '$', '[', or ']'").toString());
        }
    }

    public static void zzhc(String str) throws DatabaseException {
        if (str.startsWith(".info")) {
            zzhb(str.substring(5));
        } else if (str.startsWith("/.info")) {
            zzhb(str.substring(6));
        } else {
            zzhb(str);
        }
    }

    public static void zzhd(String str) throws DatabaseException {
        if (str != null) {
            Object obj = (str.equals(".info") || !zzcjE.matcher(str).find()) ? 1 : null;
            if (obj == null) {
                throw new DatabaseException(new StringBuilder(String.valueOf(str).length() + 68).append("Invalid key: ").append(str).append(". Keys must not contain '/', '.', '#', '$', '[', or ']'").toString());
            }
        }
    }
}
