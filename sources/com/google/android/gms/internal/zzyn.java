package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzyn implements Callable<zzaff> {
    private static long zzRj = TimeUnit.SECONDS.toMillis(60);
    private final Context mContext;
    private int mErrorCode;
    private final Object mLock = new Object();
    private final zzcu zzIc;
    private final zzafg zzQQ;
    private String zzRA;
    private boolean zzRB;
    private final zzaie zzRv;
    private final zzbb zzRw;
    private boolean zzRx;
    private List<String> zzRy;
    private JSONObject zzRz;
    private final zznb zzsK;
    private zzyh zzuP;

    public zzyn(Context context, zzbb zzbb, zzaie zzaie, zzcu zzcu, zzafg zzafg, zznb zznb) {
        this.mContext = context;
        this.zzRw = zzbb;
        this.zzRv = zzaie;
        this.zzQQ = zzafg;
        this.zzIc = zzcu;
        this.zzsK = zznb;
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzFu)).booleanValue()) {
            this.zzuP = zzbb.zzbi();
        }
        if (this.zzuP == null) {
            this.zzuP = new zzyh(context, zzafg, zzbb, zzcu);
            this.zzuP.zzgs();
            this.zzRB = true;
        }
        this.zzRx = false;
        this.mErrorCode = -2;
        this.zzRy = null;
        this.zzRA = null;
    }

    private final zzaff zza(zzoa zzoa) {
        int i;
        synchronized (this.mLock) {
            i = this.mErrorCode;
            if (zzoa == null && this.mErrorCode == -2) {
                i = 0;
            }
        }
        return new zzaff(this.zzQQ.zzUj.zzSz, null, this.zzQQ.zzXY.zzMa, i, this.zzQQ.zzXY.zzMb, this.zzRy, this.zzQQ.zzXY.orientation, this.zzQQ.zzXY.zzMg, this.zzQQ.zzUj.zzSC, false, null, null, null, null, null, 0, this.zzQQ.zzvX, this.zzQQ.zzXY.zzTn, this.zzQQ.zzXR, this.zzQQ.zzXS, this.zzQQ.zzXY.zzTt, this.zzRz, i != -2 ? null : zzoa, null, null, null, this.zzQQ.zzXY.zzTG, this.zzQQ.zzXY.zzTH, null, this.zzQQ.zzXY.zzMd, this.zzRA, this.zzQQ.zzXX);
    }

    private final zzajm<zznp> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        String string = z ? jSONObject.getString("url") : jSONObject.optString("url");
        double optDouble = jSONObject.optDouble("scale", 1.0d);
        boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        if (!TextUtils.isEmpty(string)) {
            return z2 ? new zzajh(new zznp(null, Uri.parse(string), optDouble)) : this.zzRv.zza(string, new zzyu(this, z, optDouble, optBoolean, string));
        } else {
            zzc(0, z);
            return new zzajh(null);
        }
    }

    static zzaka zzb(zzajm<zzaka> zzajm) {
        Throwable e;
        try {
            return (zzaka) zzajm.get((long) ((Integer) zzbs.zzbL().zzd(zzmo.zzFz)).intValue(), TimeUnit.SECONDS);
        } catch (Throwable e2) {
            zzajc.zzc("InterruptedException occurred while waiting for video to load", e2);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e3) {
            e2 = e3;
            zzajc.zzc("Exception occurred while waiting for video to load", e2);
        } catch (TimeoutException e4) {
            e2 = e4;
            zzajc.zzc("Exception occurred while waiting for video to load", e2);
        } catch (CancellationException e5) {
            e2 = e5;
            zzajc.zzc("Exception occurred while waiting for video to load", e2);
        }
        return null;
    }

    private static Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException e) {
            return null;
        }
    }

    private final void zzc(zzpj zzpj, String str) {
        try {
            zzpt zzs = this.zzRw.zzs(zzpj.getCustomTemplateId());
            if (zzs != null) {
                zzs.zzb(zzpj, str);
            }
        } catch (Throwable e) {
            zzajc.zzc(new StringBuilder(String.valueOf(str).length() + 40).append("Failed to call onCustomClick for asset ").append(str).append(".").toString(), e);
        }
    }

    private static String[] zzd(JSONObject jSONObject, String str) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        String[] strArr = new String[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); i++) {
            strArr[i] = optJSONArray.getString(i);
        }
        return strArr;
    }

    private final zzaff zzgw() {
        try {
            JSONObject jSONObject;
            zzyv zzyv;
            zzoa zzoa;
            if (this.zzRB) {
                this.zzuP.zzgt();
            }
            String uuid = UUID.randomUUID().toString();
            if (zzgx()) {
                jSONObject = null;
            } else {
                zzajg zzajg = new zzajg();
                this.zzuP.zza(new zzyo(this, uuid, new zzyw(this), zzajg));
                jSONObject = (JSONObject) zzajg.get(zzRj, TimeUnit.MILLISECONDS);
            }
            if (zzgx() || jSONObject == null) {
                zzyv = null;
            } else {
                String string = jSONObject.getString("template_id");
                boolean z = this.zzQQ.zzUj.zzwj != null ? this.zzQQ.zzUj.zzwj.zzIn : false;
                boolean z2 = this.zzQQ.zzUj.zzwj != null ? this.zzQQ.zzUj.zzwj.zzIp : false;
                Object zzzf;
                if ("2".equals(string)) {
                    zzzf = new zzzf(z, z2);
                } else if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(string)) {
                    zzzf = new zzzg(z, z2);
                } else {
                    if ("3".equals(string)) {
                        String string2 = jSONObject.getString("custom_template_id");
                        zzajg zzajg2 = new zzajg();
                        zzagz.zzZr.post(new zzyq(this, zzajg2, string2));
                        if (zzajg2.get(zzRj, TimeUnit.MILLISECONDS) != null) {
                            zzzf = new zzzh(z);
                        } else {
                            String str = "No handler for custom template: ";
                            string2 = String.valueOf(jSONObject.getString("custom_template_id"));
                            zzajc.m1216e(string2.length() != 0 ? str.concat(string2) : new String(str));
                        }
                    } else {
                        zzt(0);
                    }
                    zzyv = null;
                }
            }
            if (zzgx() || zzyv == null || jSONObject == null) {
                zzoa = null;
            } else {
                JSONObject jSONObject2 = jSONObject.getJSONObject("tracking_urls_and_actions");
                String[] zzd = zzd(jSONObject2, "impression_tracking_urls");
                this.zzRy = zzd == null ? null : Arrays.asList(zzd);
                this.zzRz = jSONObject2.optJSONObject("active_view");
                this.zzRA = jSONObject.optString("debug_signals");
                zzoa zza = zzyv.zza(this, jSONObject);
                zza.zzb(new zzoc(this.mContext, this.zzRw, this.zzuP, this.zzIc, jSONObject, zza, this.zzQQ.zzUj.zzvT, uuid));
                zzoa = zza;
            }
            if (zzoa instanceof zznu) {
                zznu zznu = (zznu) zzoa;
                zzyw zzyw = new zzyw(this);
                zzrd zzyr = new zzyr(this, zznu);
                zzyw.zzRW = zzyr;
                this.zzuP.zza(new zzys(this, zzyr));
            }
            return zza(zzoa);
        } catch (CancellationException e) {
            if (!this.zzRx) {
                zzt(0);
            }
            return zza(null);
        } catch (ExecutionException e2) {
            if (this.zzRx) {
                zzt(0);
            }
            return zza(null);
        } catch (InterruptedException e3) {
            if (this.zzRx) {
                zzt(0);
            }
            return zza(null);
        } catch (Throwable e4) {
            zzajc.zzc("Malformed native JSON response.", e4);
            if (this.zzRx) {
                zzt(0);
            }
            return zza(null);
        } catch (Throwable e42) {
            zzajc.zzc("Timeout when loading native ad.", e42);
            if (this.zzRx) {
                zzt(0);
            }
            return zza(null);
        } catch (Throwable e422) {
            zzajc.zzc("Error occured while doing native ads initialization.", e422);
            if (this.zzRx) {
                zzt(0);
            }
            return zza(null);
        }
    }

    private static List<Drawable> zzj(List<zznp> list) throws RemoteException {
        List<Drawable> arrayList = new ArrayList();
        for (zznp zzeg : list) {
            arrayList.add((Drawable) zzn.zzE(zzeg.zzeg()));
        }
        return arrayList;
    }

    public final /* synthetic */ Object call() throws Exception {
        return zzgw();
    }

    public final zzajm<zznp> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public final List<zzajm<zznp>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        List<zzajm<zznp>> arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() == 0) {
            zzc(0, false);
            return arrayList;
        }
        int length = z3 ? optJSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, false, z2));
        }
        return arrayList;
    }

    public final Future<zznp> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, optBoolean, z);
    }

    public final zzajm<zzaka> zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return new zzajh(null);
        }
        if (TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
            zzajc.zzaT("Required field 'vast_xml' is missing");
            return new zzajh(null);
        }
        zzyx zzyx = new zzyx(this.mContext, this.zzIc, this.zzQQ, this.zzsK, this.zzRw);
        zzajg zzajg = new zzajg();
        zzbs.zzbz();
        zzagz.runOnUiThread(new zzyy(zzyx, optJSONObject, zzajg));
        return zzajg;
    }

    public final void zzc(int i, boolean z) {
        if (z) {
            zzt(i);
        }
    }

    public final zzajm<zznn> zzd(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return new zzajh(null);
        }
        String optString = optJSONObject.optString("text");
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer zzb = zzb(optJSONObject, "text_color");
        Integer zzb2 = zzb(optJSONObject, "bg_color");
        int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        int i = (this.zzQQ.zzUj.zzwj == null || this.zzQQ.zzUj.zzwj.versionCode < 2) ? 1 : this.zzQQ.zzUj.zzwj.zzIq;
        boolean optBoolean = optJSONObject.optBoolean("allow_pub_rendering");
        List arrayList = new ArrayList();
        if (optJSONObject.optJSONArray("images") != null) {
            arrayList = zza(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, "image", false, false));
        }
        return zzaji.zza(zzaji.zzp(arrayList), new zzyt(this, optString, zzb2, zzb, optInt, optInt3, optInt2, i, optBoolean));
    }

    public final boolean zzgx() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzRx;
        }
        return z;
    }

    public final void zzt(int i) {
        synchronized (this.mLock) {
            this.zzRx = true;
            this.mErrorCode = i;
        }
    }
}
