package com.google.android.gms.internal;

import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzzh implements zzyv<zznu> {
    private final boolean zzSe;

    public zzzh(boolean z) {
        this.zzSe = z;
    }

    private static <K, V> SimpleArrayMap<K, V> zza(SimpleArrayMap<K, Future<V>> simpleArrayMap) throws InterruptedException, ExecutionException {
        SimpleArrayMap<K, V> simpleArrayMap2 = new SimpleArrayMap();
        for (int i = 0; i < simpleArrayMap.size(); i++) {
            simpleArrayMap2.put(simpleArrayMap.keyAt(i), ((Future) simpleArrayMap.valueAt(i)).get());
        }
        return simpleArrayMap2;
    }

    public final /* synthetic */ zzoa zza(zzyn zzyn, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        String valueOf;
        View view = null;
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        Future zzd = zzyn.zzd(jSONObject);
        zzajm zzc = zzyn.zzc(jSONObject, "video");
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString("type");
            if ("string".equals(string)) {
                simpleArrayMap2.put(jSONObject2.getString("name"), jSONObject2.getString("string_value"));
            } else if ("image".equals(string)) {
                simpleArrayMap.put(jSONObject2.getString("name"), zzyn.zza(jSONObject2, "image_value", this.zzSe));
            } else {
                String str = "Unknown custom asset type: ";
                valueOf = String.valueOf(string);
                zzajc.zzaT(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
        zzaka zzb = zzyn.zzb(zzc);
        valueOf = jSONObject.getString("custom_template_id");
        simpleArrayMap = zza(simpleArrayMap);
        zznn zznn = (zznn) zzd.get();
        zzks zziH = zzb != null ? zzb.zziH() : null;
        if (zzb != null) {
            view = zzb.getView();
        }
        return new zznu(valueOf, simpleArrayMap, simpleArrayMap2, zznn, zziH, view);
    }
}
