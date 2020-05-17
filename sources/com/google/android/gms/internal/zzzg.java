package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzzg implements zzyv<zzns> {
    private final boolean zzSe;
    private final boolean zzSf;

    public zzzg(boolean z, boolean z2) {
        this.zzSe = z;
        this.zzSf = z2;
    }

    public final /* synthetic */ zzoa zza(zzyn zzyn, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        View view = null;
        List<zzajm> zza = zzyn.zza(jSONObject, "images", false, this.zzSe, this.zzSf);
        Future zza2 = zzyn.zza(jSONObject, "secondary_image", false, this.zzSe);
        Future zzd = zzyn.zzd(jSONObject);
        zzajm zzc = zzyn.zzc(jSONObject, "video");
        List arrayList = new ArrayList();
        for (zzajm zzajm : zza) {
            arrayList.add((zznp) zzajm.get());
        }
        zzaka zzb = zzyn.zzb(zzc);
        String string = jSONObject.getString("headline");
        String string2 = jSONObject.getString("body");
        zzos zzos = (zzos) zza2.get();
        String string3 = jSONObject.getString("call_to_action");
        String string4 = jSONObject.getString("advertiser");
        zznn zznn = (zznn) zzd.get();
        Bundle bundle = new Bundle();
        zzks zziH = zzb != null ? zzb.zziH() : null;
        if (zzb != null) {
            view = zzb.getView();
        }
        return new zzns(string, arrayList, string2, zzos, string3, string4, zznn, bundle, zziH, view);
    }
}
