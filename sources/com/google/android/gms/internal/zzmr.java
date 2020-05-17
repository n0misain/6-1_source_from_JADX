package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.zzbs;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@zzzn
public final class zzmr {
    private Context mContext;
    private String zzGJ;
    private BlockingQueue<zznb> zzGL;
    private ExecutorService zzGM;
    private LinkedHashMap<String, String> zzGN = new LinkedHashMap();
    private Map<String, zzmv> zzGO = new HashMap();
    private AtomicBoolean zzGP;
    private File zzGQ;
    private String zzwH;

    public zzmr(Context context, String str, String str2, Map<String, String> map) {
        this.mContext = context;
        this.zzwH = str;
        this.zzGJ = str2;
        this.zzGP = new AtomicBoolean(false);
        this.zzGP.set(((Boolean) zzbs.zzbL().zzd(zzmo.zzCS)).booleanValue());
        if (this.zzGP.get()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null) {
                this.zzGQ = new File(externalStorageDirectory, "sdk_csi_data.txt");
            }
        }
        for (Entry entry : map.entrySet()) {
            this.zzGN.put((String) entry.getKey(), (String) entry.getValue());
        }
        this.zzGL = new ArrayBlockingQueue(30);
        this.zzGM = Executors.newSingleThreadExecutor();
        this.zzGM.execute(new zzms(this));
        this.zzGO.put(NativeProtocol.WEB_DIALOG_ACTION, zzmv.zzGT);
        this.zzGO.put("ad_format", zzmv.zzGT);
        this.zzGO.put("e", zzmv.zzGU);
    }

    private final void zzdO() {
        FileOutputStream fileOutputStream;
        Throwable e;
        while (true) {
            try {
                zznb zznb = (zznb) this.zzGL.take();
                Object zzdU = zznb.zzdU();
                if (!TextUtils.isEmpty(zzdU)) {
                    Map zza = zza(this.zzGN, zznb.zzdV());
                    Builder buildUpon = Uri.parse(this.zzGJ).buildUpon();
                    for (Entry entry : zza.entrySet()) {
                        buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
                    }
                    StringBuilder stringBuilder = new StringBuilder(buildUpon.build().toString());
                    stringBuilder.append("&it=").append(zzdU);
                    String stringBuilder2 = stringBuilder.toString();
                    if (this.zzGP.get()) {
                        File file = this.zzGQ;
                        if (file != null) {
                            try {
                                fileOutputStream = new FileOutputStream(file, true);
                                try {
                                    fileOutputStream.write(stringBuilder2.getBytes());
                                    fileOutputStream.write(10);
                                    try {
                                        fileOutputStream.close();
                                    } catch (Throwable e2) {
                                        zzajc.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", e2);
                                    }
                                } catch (IOException e3) {
                                    e2 = e3;
                                    try {
                                        zzajc.zzc("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e2);
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.close();
                                            } catch (Throwable e22) {
                                                zzajc.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", e22);
                                            }
                                        }
                                    } catch (Throwable th) {
                                        e22 = th;
                                    }
                                }
                            } catch (IOException e4) {
                                e22 = e4;
                                fileOutputStream = null;
                                zzajc.zzc("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e22);
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                            } catch (Throwable th2) {
                                e22 = th2;
                                fileOutputStream = null;
                            }
                        } else {
                            zzajc.zzaT("CsiReporter: File doesn't exists. Cannot write CSI data to file.");
                        }
                    } else {
                        zzbs.zzbz();
                        zzagz.zzd(this.mContext, this.zzwH, stringBuilder2);
                    }
                }
            } catch (Throwable e222) {
                zzajc.zzc("CsiReporter:reporter interrupted", e222);
                return;
            }
        }
        throw e222;
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Throwable e5) {
                zzajc.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", e5);
            }
        }
        throw e222;
    }

    public final zzmv zzM(String str) {
        zzmv zzmv = (zzmv) this.zzGO.get(str);
        return zzmv != null ? zzmv : zzmv.zzGS;
    }

    final Map<String, String> zza(Map<String, String> map, @Nullable Map<String, String> map2) {
        Map<String, String> linkedHashMap = new LinkedHashMap(map);
        if (map2 == null) {
            return linkedHashMap;
        }
        for (Entry entry : map2.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) linkedHashMap.get(str);
            linkedHashMap.put(str, zzM(str).zzg(str2, (String) entry.getValue()));
        }
        return linkedHashMap;
    }

    public final boolean zza(zznb zznb) {
        return this.zzGL.offer(zznb);
    }

    public final void zze(@Nullable List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.zzGN.put("e", TextUtils.join(",", list));
        }
    }
}
