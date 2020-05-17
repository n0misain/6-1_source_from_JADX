package com.google.android.gms.internal;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzzf implements zzyv<zznq> {
    private final boolean zzSe;
    private final boolean zzSf;

    public zzzf(boolean z, boolean z2) {
        this.zzSe = z;
        this.zzSf = z2;
    }

    public final /* synthetic */ zzoa zza(zzyn zzyn, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        List<zzajm> zza = zzyn.zza(jSONObject, "images", false, this.zzSe, this.zzSf);
        Future zza2 = zzyn.zza(jSONObject, "app_icon", true, this.zzSe);
        zzajm zzc = zzyn.zzc(jSONObject, "video");
        Future zzd = zzyn.zzd(jSONObject);
        List arrayList = new ArrayList();
        for (zzajm zzajm : zza) {
            arrayList.add((zznp) zzajm.get());
        }
        zzaka zzb = zzyn.zzb(zzc);
        return new zznq(jSONObject.getString("headline"), arrayList, jSONObject.getString("body"), (zzos) zza2.get(), jSONObject.getString("call_to_action"), jSONObject.optDouble("rating", -1.0d), jSONObject.optString("store"), jSONObject.optString("price"), (zznn) zzd.get(), new Bundle(), zzb != null ? zzb.zziH() : null, zzb != null ? zzb.getView() : null);
    }
}
