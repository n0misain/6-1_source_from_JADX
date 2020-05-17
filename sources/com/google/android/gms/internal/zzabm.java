package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.js.zzl;
import com.google.android.gms.ads.internal.zzbs;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzabm extends zzaan {
    private static zzabm zzUx;
    private static final Object zzuF = new Object();
    private final Context mContext;
    private final zzl zzLG;
    private final zzabl zzUy;
    private final zzmb zzUz;

    private zzabm(Context context, zzmb zzmb, zzabl zzabl) {
        this(context, zzmb, zzabl, zzbs.zzbO().zzb(context, new zzaje(11020208, 11020208, true)).zzff());
    }

    private zzabm(Context context, zzmb zzmb, zzabl zzabl, zzl zzl) {
        this.mContext = context;
        this.zzUy = zzabl;
        this.zzUz = zzmb;
        this.zzLG = zzl;
    }

    private static zzaai zza(Context context, zzl zzl, zzmb zzmb, zzabl zzabl, zzaae zzaae) {
        Future zza;
        Future zzz;
        zzajc.zzaC("Starting ad request from service using: AFMA_getAd");
        zznb zznb = new zznb(((Boolean) zzbs.zzbL().zzd(zzmo.zzCQ)).booleanValue(), "load_ad", zzaae.zzvX.zzAs);
        if (zzaae.versionCode > 10 && zzaae.zzSP != -1) {
            zznb.zza(zznb.zzc(zzaae.zzSP), "cts");
        }
        zzmz zzdS = zznb.zzdS();
        Future zzj = zzabl.zzUv.zzj(context);
        Future zzo = zzabl.zzUu.zzo(context);
        Future zzaD = zzabl.zzUp.zzaD(zzaae.zzSA.packageName);
        Future zze = zzabl.zzUw.zze(zzaae);
        Future zzn = zzbs.zzbI().zzn(context);
        zzajh zzajh = new zzajh(null);
        Bundle bundle = zzaae.zzSz.extras;
        Object obj = (bundle == null || bundle.getString("_ad") == null) ? null : 1;
        if (zzaae.zzSV && obj == null) {
            zza = zzabl.zzUs.zza(zzaae.applicationInfo);
        } else {
            Object obj2 = zzajh;
        }
        zzajh = new zzajh(null);
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzDM)).booleanValue()) {
            zzz = zzabl.zzUw.zzz(context);
        } else {
            Object obj3 = zzajh;
        }
        Bundle bundle2 = (zzaae.versionCode < 4 || zzaae.zzSG == null) ? null : zzaae.zzSG;
        ((Boolean) zzbs.zzbL().zzd(zzmo.zzDg)).booleanValue();
        zzbs.zzbz();
        if (zzagz.zzc(context, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE")) {
            if (((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
                zzajc.zzaC("Device is offline.");
            }
        }
        String uuid = zzaae.versionCode >= 7 ? zzaae.zzSM : UUID.randomUUID().toString();
        zzabu zzabu = new zzabu(uuid, zzaae.applicationInfo.packageName);
        if (zzaae.zzSz.extras != null) {
            String string = zzaae.zzSz.extras.getString("_ad");
            if (string != null) {
                return zzabt.zza(context, zzaae, string);
            }
        }
        List zza2 = zzabl.zzUq.zza(zzaae);
        bundle = (Bundle) zzaji.zza(zzj, null, ((Long) zzbs.zzbL().zzd(zzmo.zzGb)).longValue(), TimeUnit.MILLISECONDS);
        zzacn zzacn = (zzacn) zzaji.zza(zzo, null, ((Long) zzbs.zzbL().zzd(zzmo.zzEF)).longValue(), TimeUnit.MILLISECONDS);
        Location location = (Location) zzaji.zza(zza, null, ((Long) zzbs.zzbL().zzd(zzmo.zzFK)).longValue(), TimeUnit.MILLISECONDS);
        Info info = (Info) zzaji.zza(zzz, null, ((Long) zzbs.zzbL().zzd(zzmo.zzDN)).longValue(), TimeUnit.MILLISECONDS);
        String str = (String) zzaji.zza(zze, null);
        String str2 = (String) zzaji.zza(zzaD, null);
        zzacb zzacb = (zzacb) zzaji.zza(zzn, null);
        if (zzacb == null) {
            zzajc.zzaT("Error fetching device info. This is not recoverable.");
            return new zzaai(0);
        }
        zzabk zzabk = new zzabk();
        zzabk.zzUj = zzaae;
        zzabk.zzUk = zzacb;
        zzabk.zzUh = zzacn;
        zzabk.zzzV = location;
        zzabk.zzUg = bundle;
        zzabk.zzSB = str;
        zzabk.zzqi = info;
        if (zza2 == null) {
            zzabk.zzSN.clear();
        }
        zzabk.zzSN = zza2;
        zzabk.zzSG = bundle2;
        zzabk.zzUi = str2;
        zzabk.zzUl = zzabl.zzUo.zzf(context);
        zzabk.zzUm = zzabl.zzUm;
        JSONObject zza3 = zzabt.zza(context, zzabk);
        if (zza3 == null) {
            return new zzaai(0);
        }
        if (zzaae.versionCode < 7) {
            try {
                zza3.put("request_id", uuid);
            } catch (JSONException e) {
            }
        }
        String jSONObject = zza3.toString();
        zznb.zza(zzdS, "arc");
        zzagz.zzZr.post(new zzabn(zzl, zzabu, zznb, zznb.zzdS(), jSONObject));
        zzaai zzaai;
        try {
            zzaca zzaca = (zzaca) zzabu.zzgG().get(10, TimeUnit.SECONDS);
            if (zzaca == null) {
                zzaai = new zzaai(0);
                return zzaai;
            } else if (zzaca.getErrorCode() != -2) {
                zzaai = new zzaai(zzaca.getErrorCode());
                zzagz.zzZr.post(new zzabq(zzabl, context, zzabu, zzaae));
                return zzaai;
            } else {
                if (zznb.zzdW() != null) {
                    zznb.zza(zznb.zzdW(), "rur");
                }
                zzaai = null;
                if (!TextUtils.isEmpty(zzaca.zzgL())) {
                    zzaai = zzabt.zza(context, zzaae, zzaca.zzgL());
                }
                if (zzaai == null && !TextUtils.isEmpty(zzaca.getUrl())) {
                    zzaai = zza(zzaae, context, zzaae.zzvT.zzaP, zzaca.getUrl(), str2, zzaca, zznb, zzabl);
                }
                if (zzaai == null) {
                    zzaai = new zzaai(0);
                }
                zznb.zza(zzdS, "tts");
                zzaai.zzTB = zznb.zzdU();
                zzagz.zzZr.post(new zzabq(zzabl, context, zzabu, zzaae));
                return zzaai;
            }
        } catch (Exception e2) {
            zzaai = new zzaai(0);
            return zzaai;
        } finally {
            zzagz.zzZr.post(new zzabq(zzabl, context, zzabu, zzaae));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzaai zza(com.google.android.gms.internal.zzaae r13, android.content.Context r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, com.google.android.gms.internal.zzaca r18, com.google.android.gms.internal.zznb r19, com.google.android.gms.internal.zzabl r20) {
        /*
        if (r19 == 0) goto L_0x00dc;
    L_0x0002:
        r2 = r19.zzdS();
        r4 = r2;
    L_0x0007:
        r8 = new com.google.android.gms.internal.zzaby;	 Catch:{ IOException -> 0x00e7 }
        r2 = r18.zzgI();	 Catch:{ IOException -> 0x00e7 }
        r8.<init>(r13, r2);	 Catch:{ IOException -> 0x00e7 }
        r3 = "AdRequestServiceImpl: Sending request: ";
        r2 = java.lang.String.valueOf(r16);	 Catch:{ IOException -> 0x00e7 }
        r5 = r2.length();	 Catch:{ IOException -> 0x00e7 }
        if (r5 == 0) goto L_0x00e0;
    L_0x001c:
        r2 = r3.concat(r2);	 Catch:{ IOException -> 0x00e7 }
    L_0x0020:
        com.google.android.gms.internal.zzajc.zzaC(r2);	 Catch:{ IOException -> 0x00e7 }
        r3 = new java.net.URL;	 Catch:{ IOException -> 0x00e7 }
        r0 = r16;
        r3.<init>(r0);	 Catch:{ IOException -> 0x00e7 }
        r2 = 0;
        r5 = com.google.android.gms.ads.internal.zzbs.zzbF();	 Catch:{ IOException -> 0x00e7 }
        r10 = r5.elapsedRealtime();	 Catch:{ IOException -> 0x00e7 }
        r7 = r3;
        r3 = r2;
    L_0x0035:
        r2 = r7.openConnection();	 Catch:{ IOException -> 0x00e7 }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ IOException -> 0x00e7 }
        r5 = com.google.android.gms.ads.internal.zzbs.zzbz();	 Catch:{ all -> 0x010c }
        r6 = 0;
        r5.zza(r14, r15, r6, r2);	 Catch:{ all -> 0x010c }
        r5 = android.text.TextUtils.isEmpty(r17);	 Catch:{ all -> 0x010c }
        if (r5 != 0) goto L_0x0057;
    L_0x0049:
        r5 = r18.zzgK();	 Catch:{ all -> 0x010c }
        if (r5 == 0) goto L_0x0057;
    L_0x004f:
        r5 = "x-afma-drt-cookie";
        r0 = r17;
        r2.addRequestProperty(r5, r0);	 Catch:{ all -> 0x010c }
    L_0x0057:
        r5 = r13.zzSW;	 Catch:{ all -> 0x010c }
        r6 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x010c }
        if (r6 != 0) goto L_0x0069;
    L_0x005f:
        r6 = "Sending webview cookie in ad request header.";
        com.google.android.gms.internal.zzajc.zzaC(r6);	 Catch:{ all -> 0x010c }
        r6 = "Cookie";
        r2.addRequestProperty(r6, r5);	 Catch:{ all -> 0x010c }
    L_0x0069:
        if (r18 == 0) goto L_0x0095;
    L_0x006b:
        r5 = r18.zzgJ();	 Catch:{ all -> 0x010c }
        r5 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x010c }
        if (r5 != 0) goto L_0x0095;
    L_0x0075:
        r5 = 1;
        r2.setDoOutput(r5);	 Catch:{ all -> 0x010c }
        r5 = r18.zzgJ();	 Catch:{ all -> 0x010c }
        r9 = r5.getBytes();	 Catch:{ all -> 0x010c }
        r5 = r9.length;	 Catch:{ all -> 0x010c }
        r2.setFixedLengthStreamingMode(r5);	 Catch:{ all -> 0x010c }
        r6 = 0;
        r5 = new java.io.BufferedOutputStream;	 Catch:{ all -> 0x0106 }
        r12 = r2.getOutputStream();	 Catch:{ all -> 0x0106 }
        r5.<init>(r12);	 Catch:{ all -> 0x0106 }
        r5.write(r9);	 Catch:{ all -> 0x01ac }
        com.google.android.gms.common.util.zzn.closeQuietly(r5);	 Catch:{ all -> 0x010c }
    L_0x0095:
        r9 = r2.getResponseCode();	 Catch:{ all -> 0x010c }
        r12 = r2.getHeaderFields();	 Catch:{ all -> 0x010c }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r9 < r5) goto L_0x0117;
    L_0x00a1:
        r5 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r9 >= r5) goto L_0x0117;
    L_0x00a5:
        r3 = r7.toString();	 Catch:{ all -> 0x010c }
        r6 = 0;
        r5 = new java.io.InputStreamReader;	 Catch:{ all -> 0x0111 }
        r7 = r2.getInputStream();	 Catch:{ all -> 0x0111 }
        r5.<init>(r7);	 Catch:{ all -> 0x0111 }
        com.google.android.gms.ads.internal.zzbs.zzbz();	 Catch:{ all -> 0x01a8 }
        r6 = com.google.android.gms.internal.zzagz.zza(r5);	 Catch:{ all -> 0x01a8 }
        com.google.android.gms.common.util.zzn.closeQuietly(r5);	 Catch:{ all -> 0x010c }
        zza(r3, r12, r6, r9);	 Catch:{ all -> 0x010c }
        r8.zza(r3, r12, r6);	 Catch:{ all -> 0x010c }
        if (r19 == 0) goto L_0x00d3;
    L_0x00c5:
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ all -> 0x010c }
        r5 = 0;
        r6 = "ufe";
        r3[r5] = r6;	 Catch:{ all -> 0x010c }
        r0 = r19;
        r0.zza(r4, r3);	 Catch:{ all -> 0x010c }
    L_0x00d3:
        r3 = r8.zze(r10);	 Catch:{ all -> 0x010c }
        r2.disconnect();	 Catch:{ IOException -> 0x00e7 }
        r2 = r3;
    L_0x00db:
        return r2;
    L_0x00dc:
        r2 = 0;
        r4 = r2;
        goto L_0x0007;
    L_0x00e0:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x00e7 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x00e7 }
        goto L_0x0020;
    L_0x00e7:
        r2 = move-exception;
        r3 = "Error while connecting to ad server: ";
        r2 = r2.getMessage();
        r2 = java.lang.String.valueOf(r2);
        r4 = r2.length();
        if (r4 == 0) goto L_0x01a1;
    L_0x00f8:
        r2 = r3.concat(r2);
    L_0x00fc:
        com.google.android.gms.internal.zzajc.zzaT(r2);
        r2 = new com.google.android.gms.internal.zzaai;
        r3 = 2;
        r2.<init>(r3);
        goto L_0x00db;
    L_0x0106:
        r3 = move-exception;
        r4 = r6;
    L_0x0108:
        com.google.android.gms.common.util.zzn.closeQuietly(r4);	 Catch:{ all -> 0x010c }
        throw r3;	 Catch:{ all -> 0x010c }
    L_0x010c:
        r3 = move-exception;
        r2.disconnect();	 Catch:{ IOException -> 0x00e7 }
        throw r3;	 Catch:{ IOException -> 0x00e7 }
    L_0x0111:
        r3 = move-exception;
        r4 = r6;
    L_0x0113:
        com.google.android.gms.common.util.zzn.closeQuietly(r4);	 Catch:{ all -> 0x010c }
        throw r3;	 Catch:{ all -> 0x010c }
    L_0x0117:
        r5 = r7.toString();	 Catch:{ all -> 0x010c }
        r6 = 0;
        zza(r5, r12, r6, r9);	 Catch:{ all -> 0x010c }
        r5 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r9 < r5) goto L_0x016d;
    L_0x0123:
        r5 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r9 >= r5) goto L_0x016d;
    L_0x0127:
        r5 = "Location";
        r5 = r2.getHeaderField(r5);	 Catch:{ all -> 0x010c }
        r6 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x010c }
        if (r6 == 0) goto L_0x0143;
    L_0x0133:
        r3 = "No location header to follow redirect.";
        com.google.android.gms.internal.zzajc.zzaT(r3);	 Catch:{ all -> 0x010c }
        r3 = new com.google.android.gms.internal.zzaai;	 Catch:{ all -> 0x010c }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x010c }
        r2.disconnect();	 Catch:{ IOException -> 0x00e7 }
        r2 = r3;
        goto L_0x00db;
    L_0x0143:
        r6 = new java.net.URL;	 Catch:{ all -> 0x010c }
        r6.<init>(r5);	 Catch:{ all -> 0x010c }
        r5 = r3 + 1;
        r3 = com.google.android.gms.internal.zzmo.zzGG;	 Catch:{ all -> 0x010c }
        r7 = com.google.android.gms.ads.internal.zzbs.zzbL();	 Catch:{ all -> 0x010c }
        r3 = r7.zzd(r3);	 Catch:{ all -> 0x010c }
        r3 = (java.lang.Integer) r3;	 Catch:{ all -> 0x010c }
        r3 = r3.intValue();	 Catch:{ all -> 0x010c }
        if (r5 <= r3) goto L_0x0191;
    L_0x015c:
        r3 = "Too many redirects.";
        com.google.android.gms.internal.zzajc.zzaT(r3);	 Catch:{ all -> 0x010c }
        r3 = new com.google.android.gms.internal.zzaai;	 Catch:{ all -> 0x010c }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x010c }
        r2.disconnect();	 Catch:{ IOException -> 0x00e7 }
        r2 = r3;
        goto L_0x00db;
    L_0x016d:
        r3 = 46;
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x010c }
        r4.<init>(r3);	 Catch:{ all -> 0x010c }
        r3 = "Received error HTTP response code: ";
        r3 = r4.append(r3);	 Catch:{ all -> 0x010c }
        r3 = r3.append(r9);	 Catch:{ all -> 0x010c }
        r3 = r3.toString();	 Catch:{ all -> 0x010c }
        com.google.android.gms.internal.zzajc.zzaT(r3);	 Catch:{ all -> 0x010c }
        r3 = new com.google.android.gms.internal.zzaai;	 Catch:{ all -> 0x010c }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x010c }
        r2.disconnect();	 Catch:{ IOException -> 0x00e7 }
        r2 = r3;
        goto L_0x00db;
    L_0x0191:
        r8.zzh(r12);	 Catch:{ all -> 0x010c }
        r2.disconnect();	 Catch:{ IOException -> 0x00e7 }
        if (r20 == 0) goto L_0x019d;
    L_0x0199:
        r3 = r5;
        r7 = r6;
        goto L_0x0035;
    L_0x019d:
        r3 = r5;
        r7 = r6;
        goto L_0x0035;
    L_0x01a1:
        r2 = new java.lang.String;
        r2.<init>(r3);
        goto L_0x00fc;
    L_0x01a8:
        r3 = move-exception;
        r4 = r5;
        goto L_0x0113;
    L_0x01ac:
        r3 = move-exception;
        r4 = r5;
        goto L_0x0108;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzabm.zza(com.google.android.gms.internal.zzaae, android.content.Context, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.zzaca, com.google.android.gms.internal.zznb, com.google.android.gms.internal.zzabl):com.google.android.gms.internal.zzaai");
    }

    public static zzabm zza(Context context, zzmb zzmb, zzabl zzabl) {
        zzabm zzabm;
        synchronized (zzuF) {
            if (zzUx == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zzmo.initialize(context);
                zzUx = new zzabm(context, zzmb, zzabl);
            }
            zzabm = zzUx;
        }
        return zzabm;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzajc.zzz(2)) {
            zzafr.m1217v(new StringBuilder(String.valueOf(str).length() + 39).append("Http Response: {\n  URL:\n    ").append(str).append("\n  Headers:").toString());
            if (map != null) {
                for (String str3 : map.keySet()) {
                    String str32;
                    zzafr.m1217v(new StringBuilder(String.valueOf(str32).length() + 5).append("    ").append(str32).append(":").toString());
                    for (String str322 : (List) map.get(str322)) {
                        String str4 = "      ";
                        str322 = String.valueOf(str322);
                        zzafr.m1217v(str322.length() != 0 ? str4.concat(str322) : new String(str4));
                    }
                }
            }
            zzafr.m1217v("  Body:");
            if (str2 != null) {
                for (int i2 = 0; i2 < Math.min(str2.length(), 100000); i2 += 1000) {
                    zzafr.m1217v(str2.substring(i2, Math.min(str2.length(), i2 + 1000)));
                }
            } else {
                zzafr.m1217v("    null");
            }
            zzafr.m1217v("  Response Code:\n    " + i + "\n}");
        }
    }

    public final void zza(zzaae zzaae, zzaap zzaap) {
        zzbs.zzbD().zzd(this.mContext, zzaae.zzvT);
        Future zza = zzagt.zza(new zzabr(this, zzaae, zzaap));
        zzbs.zzbP().zzie();
        zzbs.zzbP().getHandler().postDelayed(new zzabs(this, zza), 60000);
    }

    public final void zza(zzaax zzaax, zzaas zzaas) {
        zzafr.m1217v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }

    public final zzaai zzc(zzaae zzaae) {
        return zza(this.mContext, this.zzLG, this.zzUz, this.zzUy, zzaae);
    }
}
