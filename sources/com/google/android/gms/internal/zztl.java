package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzbs;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@zzzn
public final class zztl {
    private final Map<zztm, zztn> zzKo = new HashMap();
    private final LinkedList<zztm> zzKp = new LinkedList();
    @Nullable
    private zzsi zzKq;

    private static String[] zzY(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), HttpRequest.CHARSET_UTF8);
            }
            return split;
        } catch (UnsupportedEncodingException e) {
            return new String[0];
        }
    }

    private static boolean zzZ(String str) {
        try {
            return Pattern.matches((String) zzbs.zzbL().zzd(zzmo.zzEf), str);
        } catch (Throwable e) {
            zzbs.zzbD().zza(e, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }

    private static void zza(String str, zztm zztm) {
        if (zzajc.zzz(2)) {
            zzafr.m1217v(String.format(str, new Object[]{zztm}));
        }
    }

    private static String zzaa(String str) {
        try {
            Matcher matcher = Pattern.compile("([^/]+/[0-9]+).*").matcher(str);
            if (matcher.matches()) {
                str = matcher.group(1);
            }
        } catch (RuntimeException e) {
        }
        return str;
    }

    private static void zzc(Bundle bundle, String str) {
        while (true) {
            String[] split = str.split("/", 2);
            if (split.length != 0) {
                String str2 = split[0];
                if (split.length == 1) {
                    bundle.remove(str2);
                    return;
                }
                bundle = bundle.getBundle(str2);
                if (bundle != null) {
                    str = split[1];
                } else {
                    return;
                }
            }
            return;
        }
    }

    private final String zzeH() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = this.zzKp.iterator();
            while (it.hasNext()) {
                stringBuilder.append(Base64.encodeToString(((zztm) it.next()).toString().getBytes(HttpRequest.CHARSET_UTF8), 0));
                if (it.hasNext()) {
                    stringBuilder.append("\u0000");
                }
            }
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    static Set<String> zzi(zzir zzir) {
        Set<String> hashSet = new HashSet();
        hashSet.addAll(zzir.extras.keySet());
        Bundle bundle = zzir.zzzX.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            hashSet.addAll(bundle.keySet());
        }
        return hashSet;
    }

    static zzir zzj(zzir zzir) {
        zzir zzl = zzl(zzir);
        String str = "_skipMediation";
        Bundle bundle = zzl.zzzX.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            bundle.putBoolean(str, true);
        }
        zzl.extras.putBoolean(str, true);
        return zzl;
    }

    private static zzir zzk(zzir zzir) {
        zzir zzl = zzl(zzir);
        for (String str : ((String) zzbs.zzbL().zzd(zzmo.zzEb)).split(",")) {
            zzc(zzl.zzzX, str);
            String str2 = "com.google.ads.mediation.admob.AdMobAdapter/";
            if (str.startsWith(str2)) {
                zzc(zzl.extras, str.replaceFirst(str2, ""));
            }
        }
        return zzl;
    }

    private static zzir zzl(zzir zzir) {
        Parcel obtain = Parcel.obtain();
        zzir.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        zzir zzir2 = (zzir) zzir.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzDR)).booleanValue()) {
            zzir.zzh(zzir2);
        }
        return zzir2;
    }

    @Nullable
    final zzto zza(zzir zzir, String str) {
        if (zzZ(str)) {
            return null;
        }
        zztn zztn;
        int i = new zzacc(this.zzKq.getApplicationContext()).zzgM().zzVS;
        zzir zzk = zzk(zzir);
        String zzaa = zzaa(str);
        zztm zztm = new zztm(zzk, zzaa, i);
        zztn zztn2 = (zztn) this.zzKo.get(zztm);
        if (zztn2 == null) {
            zza("Interstitial pool created at %s.", zztm);
            zztn2 = new zztn(zzk, zzaa, i);
            this.zzKo.put(zztm, zztn2);
            zztn = zztn2;
        } else {
            zztn = zztn2;
        }
        this.zzKp.remove(zztm);
        this.zzKp.add(zztm);
        zztn.zzeL();
        while (true) {
            if (this.zzKp.size() <= ((Integer) zzbs.zzbL().zzd(zzmo.zzEc)).intValue()) {
                break;
            }
            zztm zztm2 = (zztm) this.zzKp.remove();
            zztn zztn3 = (zztn) this.zzKo.get(zztm2);
            zza("Evicting interstitial queue for %s.", zztm2);
            while (zztn3.size() > 0) {
                zzto zzm = zztn3.zzm(null);
                if (zzm.zzKz) {
                    zztp.zzeN().zzeP();
                }
                zzm.zzKv.zzbb();
            }
            this.zzKo.remove(zztm2);
        }
        while (zztn.size() > 0) {
            zzto zzm2 = zztn.zzm(zzk);
            if (zzm2.zzKz) {
                if (zzbs.zzbF().currentTimeMillis() - zzm2.zzKy > 1000 * ((long) ((Integer) zzbs.zzbL().zzd(zzmo.zzEe)).intValue())) {
                    zza("Expired interstitial at %s.", zztm);
                    zztp.zzeN().zzeO();
                }
            }
            String str2 = zzm2.zzKw != null ? " (inline) " : " ";
            zza(new StringBuilder(String.valueOf(str2).length() + 34).append("Pooled interstitial").append(str2).append("returned at %s.").toString(), zztm);
            return zzm2;
        }
        return null;
    }

    final void zza(zzsi zzsi) {
        Throwable e;
        if (this.zzKq == null) {
            this.zzKq = zzsi.zzeF();
            if (this.zzKq != null) {
                zztm zztm;
                SharedPreferences sharedPreferences = this.zzKq.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
                while (this.zzKp.size() > 0) {
                    zztm = (zztm) this.zzKp.remove();
                    zztn zztn = (zztn) this.zzKo.get(zztm);
                    zza("Flushing interstitial queue for %s.", zztm);
                    while (zztn.size() > 0) {
                        zztn.zzm(null).zzKv.zzbb();
                    }
                    this.zzKo.remove(zztm);
                }
                try {
                    Map hashMap = new HashMap();
                    for (Entry entry : sharedPreferences.getAll().entrySet()) {
                        if (!((String) entry.getKey()).equals("PoolKeys")) {
                            zztr zzab = zztr.zzab((String) entry.getValue());
                            zztm zztm2 = new zztm(zzab.zzuT, zzab.zztV, zzab.zzKt);
                            if (!this.zzKo.containsKey(zztm2)) {
                                this.zzKo.put(zztm2, new zztn(zzab.zzuT, zzab.zztV, zzab.zzKt));
                                hashMap.put(zztm2.toString(), zztm2);
                                zza("Restored interstitial queue for %s.", zztm2);
                            }
                        }
                    }
                    for (Object obj : zzY(sharedPreferences.getString("PoolKeys", ""))) {
                        zztm = (zztm) hashMap.get(obj);
                        if (this.zzKo.containsKey(zztm)) {
                            this.zzKp.add(zztm);
                        }
                    }
                } catch (RuntimeException e2) {
                    e = e2;
                    zzbs.zzbD().zza(e, "InterstitialAdPool.restore");
                    zzajc.zzc("Malformed preferences value for InterstitialAdPool.", e);
                    this.zzKo.clear();
                    this.zzKp.clear();
                } catch (IOException e3) {
                    e = e3;
                    zzbs.zzbD().zza(e, "InterstitialAdPool.restore");
                    zzajc.zzc("Malformed preferences value for InterstitialAdPool.", e);
                    this.zzKo.clear();
                    this.zzKp.clear();
                }
            }
        }
    }

    final void zzb(zzir zzir, String str) {
        if (this.zzKq != null) {
            int i = new zzacc(this.zzKq.getApplicationContext()).zzgM().zzVS;
            zzir zzk = zzk(zzir);
            String zzaa = zzaa(str);
            zztm zztm = new zztm(zzk, zzaa, i);
            zztn zztn = (zztn) this.zzKo.get(zztm);
            if (zztn == null) {
                zza("Interstitial pool created at %s.", zztm);
                zztn = new zztn(zzk, zzaa, i);
                this.zzKo.put(zztm, zztn);
            }
            zztn.zza(this.zzKq, zzir);
            zztn.zzeL();
            zza("Inline entry added to the queue at %s.", zztm);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void zzeG() {
        /*
        r9 = this;
        r8 = 2;
        r7 = 0;
        r0 = r9.zzKq;
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = r9.zzKo;
        r0 = r0.entrySet();
        r4 = r0.iterator();
    L_0x0011:
        r0 = r4.hasNext();
        if (r0 == 0) goto L_0x008c;
    L_0x0017:
        r0 = r4.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (com.google.android.gms.internal.zztm) r1;
        r0 = r0.getValue();
        r0 = (com.google.android.gms.internal.zztn) r0;
        r2 = com.google.android.gms.internal.zzajc.zzz(r8);
        if (r2 == 0) goto L_0x0056;
    L_0x002f:
        r2 = r0.size();
        r3 = r0.zzeJ();
        if (r3 >= r2) goto L_0x0056;
    L_0x0039:
        r5 = "Loading %s/%s pooled interstitials for %s.";
        r6 = 3;
        r6 = new java.lang.Object[r6];
        r3 = r2 - r3;
        r3 = java.lang.Integer.valueOf(r3);
        r6[r7] = r3;
        r3 = 1;
        r2 = java.lang.Integer.valueOf(r2);
        r6[r3] = r2;
        r6[r8] = r1;
        r2 = java.lang.String.format(r5, r6);
        com.google.android.gms.internal.zzafr.m1217v(r2);
    L_0x0056:
        r2 = r0.zzeK();
        r2 = r2 + 0;
        r3 = r2;
    L_0x005d:
        r5 = r0.size();
        r2 = com.google.android.gms.internal.zzmo.zzEd;
        r6 = com.google.android.gms.ads.internal.zzbs.zzbL();
        r2 = r6.zzd(r2);
        r2 = (java.lang.Integer) r2;
        r2 = r2.intValue();
        if (r5 >= r2) goto L_0x0084;
    L_0x0073:
        r2 = "Pooling and loading one new interstitial for %s.";
        zza(r2, r1);
        r2 = r9.zzKq;
        r2 = r0.zzb(r2);
        if (r2 == 0) goto L_0x005d;
    L_0x0080:
        r2 = r3 + 1;
        r3 = r2;
        goto L_0x005d;
    L_0x0084:
        r0 = com.google.android.gms.internal.zztp.zzeN();
        r0.zzn(r3);
        goto L_0x0011;
    L_0x008c:
        r0 = r9.zzKq;
        if (r0 == 0) goto L_0x0006;
    L_0x0090:
        r0 = r9.zzKq;
        r0 = r0.getApplicationContext();
        r1 = "com.google.android.gms.ads.internal.interstitial.InterstitialAdPool";
        r0 = r0.getSharedPreferences(r1, r7);
        r2 = r0.edit();
        r2.clear();
        r0 = r9.zzKo;
        r0 = r0.entrySet();
        r3 = r0.iterator();
    L_0x00ad:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x00e1;
    L_0x00b3:
        r0 = r3.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (com.google.android.gms.internal.zztm) r1;
        r0 = r0.getValue();
        r0 = (com.google.android.gms.internal.zztn) r0;
        r4 = r0.zzeM();
        if (r4 == 0) goto L_0x00ad;
    L_0x00cb:
        r4 = new com.google.android.gms.internal.zztr;
        r4.<init>(r0);
        r0 = r4.zzeW();
        r4 = r1.toString();
        r2.putString(r4, r0);
        r0 = "Saved interstitial queue for %s.";
        zza(r0, r1);
        goto L_0x00ad;
    L_0x00e1:
        r0 = "PoolKeys";
        r1 = r9.zzeH();
        r2.putString(r0, r1);
        r2.apply();
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zztl.zzeG():void");
    }
}
