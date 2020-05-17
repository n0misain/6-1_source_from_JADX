package com.google.android.gms.internal;

import com.facebook.internal.ServerProtocol;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public final class yr {
    private static void zza(Object obj, JSONStringer jSONStringer) throws IOException, JSONException {
        if (obj instanceof Map) {
            jSONStringer.object();
            for (Entry entry : ((Map) obj).entrySet()) {
                jSONStringer.key((String) entry.getKey());
                zza(entry.getValue(), jSONStringer);
            }
            jSONStringer.endObject();
        } else if (obj instanceof Collection) {
            Collection<Object> collection = (Collection) obj;
            jSONStringer.array();
            for (Object zza : collection) {
                zza(zza, jSONStringer);
            }
            jSONStringer.endArray();
        } else {
            jSONStringer.value(obj);
        }
    }

    public static String zzak(Object obj) throws IOException {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof String) {
            return JSONObject.quote((String) obj);
        }
        if (obj instanceof Number) {
            try {
                return JSONObject.numberToString((Number) obj);
            } catch (Throwable e) {
                throw new IOException("Could not serialize number", e);
            }
        } else if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue() ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
        } else {
            try {
                JSONStringer jSONStringer = new JSONStringer();
                zza(obj, jSONStringer);
                return jSONStringer.toString();
            } catch (Throwable e2) {
                throw new IOException("Failed to serialize JSON", e2);
            }
        }
    }

    private static Object zzal(Object obj) throws JSONException {
        if (obj instanceof JSONObject) {
            return zzs((JSONObject) obj);
        }
        if (!(obj instanceof JSONArray)) {
            return obj.equals(JSONObject.NULL) ? null : obj;
        } else {
            JSONArray jSONArray = (JSONArray) obj;
            List arrayList = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(zzal(jSONArray.get(i)));
            }
            return arrayList;
        }
    }

    public static Map<String, Object> zzgV(String str) throws IOException {
        try {
            return zzs(new JSONObject(str));
        } catch (Throwable e) {
            throw new IOException(e);
        }
    }

    public static Object zzgW(String str) throws IOException {
        try {
            return zzal(new JSONTokener(str).nextValue());
        } catch (Throwable e) {
            throw new IOException(e);
        }
    }

    private static Map<String, Object> zzs(JSONObject jSONObject) throws JSONException {
        Map<String, Object> hashMap = new HashMap(jSONObject.length());
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, zzal(jSONObject.get(str)));
        }
        return hashMap;
    }
}
