package com.google.android.gms.internal;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzzn
public final class zzsh extends zzsb {
    private static final Set<String> zzJX = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzJY = new DecimalFormat("#,###");
    private File zzJZ;
    private boolean zzKa;

    public zzsh(zzaka zzaka) {
        super(zzaka);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzajc.zzaT("Context.getCacheDir() returned null");
            return;
        }
        this.zzJZ = new File(cacheDir, "admobVideoStreams");
        String str;
        String valueOf;
        if (!this.zzJZ.isDirectory() && !this.zzJZ.mkdirs()) {
            str = "Could not create preload cache directory at ";
            valueOf = String.valueOf(this.zzJZ.getAbsolutePath());
            zzajc.zzaT(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzJZ = null;
        } else if (!this.zzJZ.setReadable(true, false) || !this.zzJZ.setExecutable(true, false)) {
            str = "Could not set cache file permissions at ";
            valueOf = String.valueOf(this.zzJZ.getAbsolutePath());
            zzajc.zzaT(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzJZ = null;
        }
    }

    private final File zzb(File file) {
        return new File(this.zzJZ, String.valueOf(file.getName()).concat(".done"));
    }

    public final void abort() {
        this.zzKa = true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzU(java.lang.String r28) {
        /*
        r27 = this;
        r0 = r27;
        r2 = r0.zzJZ;
        if (r2 != 0) goto L_0x0013;
    L_0x0006:
        r2 = 0;
        r3 = "noCacheDir";
        r4 = 0;
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r3, r4);
        r2 = 0;
    L_0x0012:
        return r2;
    L_0x0013:
        r0 = r27;
        r2 = r0.zzJZ;
        if (r2 != 0) goto L_0x0048;
    L_0x0019:
        r2 = 0;
        r3 = r2;
    L_0x001b:
        r2 = com.google.android.gms.internal.zzmo.zzCm;
        r4 = com.google.android.gms.ads.internal.zzbs.zzbL();
        r2 = r4.zzd(r2);
        r2 = (java.lang.Integer) r2;
        r2 = r2.intValue();
        if (r3 <= r2) goto L_0x00b9;
    L_0x002d:
        r0 = r27;
        r2 = r0.zzJZ;
        if (r2 != 0) goto L_0x006f;
    L_0x0033:
        r2 = 0;
    L_0x0034:
        if (r2 != 0) goto L_0x0013;
    L_0x0036:
        r2 = "Unable to expire stream cache";
        com.google.android.gms.internal.zzajc.zzaT(r2);
        r2 = 0;
        r3 = "expireFailed";
        r4 = 0;
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r3, r4);
        r2 = 0;
        goto L_0x0012;
    L_0x0048:
        r3 = 0;
        r0 = r27;
        r2 = r0.zzJZ;
        r4 = r2.listFiles();
        r5 = r4.length;
        r2 = 0;
        r26 = r2;
        r2 = r3;
        r3 = r26;
    L_0x0058:
        if (r3 >= r5) goto L_0x006d;
    L_0x005a:
        r6 = r4[r3];
        r6 = r6.getName();
        r7 = ".done";
        r6 = r6.endsWith(r7);
        if (r6 != 0) goto L_0x006a;
    L_0x0068:
        r2 = r2 + 1;
    L_0x006a:
        r3 = r3 + 1;
        goto L_0x0058;
    L_0x006d:
        r3 = r2;
        goto L_0x001b;
    L_0x006f:
        r7 = 0;
        r4 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r0 = r27;
        r2 = r0.zzJZ;
        r9 = r2.listFiles();
        r10 = r9.length;
        r2 = 0;
        r8 = r2;
    L_0x0080:
        if (r8 >= r10) goto L_0x009f;
    L_0x0082:
        r6 = r9[r8];
        r2 = r6.getName();
        r3 = ".done";
        r2 = r2.endsWith(r3);
        if (r2 != 0) goto L_0x050b;
    L_0x0090:
        r2 = r6.lastModified();
        r11 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r11 >= 0) goto L_0x050b;
    L_0x0098:
        r4 = r6;
    L_0x0099:
        r5 = r8 + 1;
        r8 = r5;
        r7 = r4;
        r4 = r2;
        goto L_0x0080;
    L_0x009f:
        r2 = 0;
        if (r7 == 0) goto L_0x0034;
    L_0x00a2:
        r2 = r7.delete();
        r0 = r27;
        r3 = r0.zzb(r7);
        r4 = r3.isFile();
        if (r4 == 0) goto L_0x0034;
    L_0x00b2:
        r3 = r3.delete();
        r2 = r2 & r3;
        goto L_0x0034;
    L_0x00b9:
        com.google.android.gms.internal.zzji.zzds();
        r2 = com.google.android.gms.internal.zzaiy.zzaR(r28);
        r13 = new java.io.File;
        r0 = r27;
        r3 = r0.zzJZ;
        r13.<init>(r3, r2);
        r0 = r27;
        r14 = r0.zzb(r13);
        r2 = r13.isFile();
        if (r2 == 0) goto L_0x0107;
    L_0x00d5:
        r2 = r14.isFile();
        if (r2 == 0) goto L_0x0107;
    L_0x00db:
        r2 = r13.length();
        r3 = (int) r2;
        r4 = "Stream cache hit at ";
        r2 = java.lang.String.valueOf(r28);
        r5 = r2.length();
        if (r5 == 0) goto L_0x0101;
    L_0x00ec:
        r2 = r4.concat(r2);
    L_0x00f0:
        com.google.android.gms.internal.zzajc.zzaC(r2);
        r2 = r13.getAbsolutePath();
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r3);
        r2 = 1;
        goto L_0x0012;
    L_0x0101:
        r2 = new java.lang.String;
        r2.<init>(r4);
        goto L_0x00f0;
    L_0x0107:
        r0 = r27;
        r2 = r0.zzJZ;
        r2 = r2.getAbsolutePath();
        r3 = java.lang.String.valueOf(r2);
        r2 = java.lang.String.valueOf(r28);
        r4 = r2.length();
        if (r4 == 0) goto L_0x0155;
    L_0x011d:
        r2 = r3.concat(r2);
        r9 = r2;
    L_0x0122:
        r3 = zzJX;
        monitor-enter(r3);
        r2 = zzJX;	 Catch:{ all -> 0x0152 }
        r2 = r2.contains(r9);	 Catch:{ all -> 0x0152 }
        if (r2 == 0) goto L_0x0162;
    L_0x012d:
        r4 = "Stream cache already in progress at ";
        r2 = java.lang.String.valueOf(r28);	 Catch:{ all -> 0x0152 }
        r5 = r2.length();	 Catch:{ all -> 0x0152 }
        if (r5 == 0) goto L_0x015c;
    L_0x0139:
        r2 = r4.concat(r2);	 Catch:{ all -> 0x0152 }
    L_0x013d:
        com.google.android.gms.internal.zzajc.zzaT(r2);	 Catch:{ all -> 0x0152 }
        r2 = r13.getAbsolutePath();	 Catch:{ all -> 0x0152 }
        r4 = "inProgress";
        r5 = 0;
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r4, r5);	 Catch:{ all -> 0x0152 }
        r2 = 0;
        monitor-exit(r3);	 Catch:{ all -> 0x0152 }
        goto L_0x0012;
    L_0x0152:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0152 }
        throw r2;
    L_0x0155:
        r2 = new java.lang.String;
        r2.<init>(r3);
        r9 = r2;
        goto L_0x0122;
    L_0x015c:
        r2 = new java.lang.String;	 Catch:{ all -> 0x0152 }
        r2.<init>(r4);	 Catch:{ all -> 0x0152 }
        goto L_0x013d;
    L_0x0162:
        r2 = zzJX;	 Catch:{ all -> 0x0152 }
        r2.add(r9);	 Catch:{ all -> 0x0152 }
        monitor-exit(r3);	 Catch:{ all -> 0x0152 }
        r5 = 0;
        r11 = "error";
        r10 = 0;
        com.google.android.gms.ads.internal.zzbs.zzbM();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = com.google.android.gms.internal.zzmo.zzCs;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r3 = com.google.android.gms.ads.internal.zzbs.zzbL();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r3.zzd(r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = (java.lang.Integer) r2;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r2.intValue();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r0 = r28;
        r3 = com.google.android.gms.internal.zzajo.zzb(r0, r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r3 instanceof java.net.HttpURLConnection;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        if (r2 == 0) goto L_0x0259;
    L_0x0189:
        r0 = r3;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r0;
        r2 = r2.getResponseCode();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r4 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r2 < r4) goto L_0x0259;
    L_0x0195:
        r4 = "badUrl";
        r6 = "HTTP request failed. Code: ";
        r3 = java.lang.Integer.toString(r2);	 Catch:{ IOException -> 0x0256, RuntimeException -> 0x04fe }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x0256, RuntimeException -> 0x04fe }
        r7 = r3.length();	 Catch:{ IOException -> 0x0256, RuntimeException -> 0x04fe }
        if (r7 == 0) goto L_0x024f;
    L_0x01a7:
        r3 = r6.concat(r3);	 Catch:{ IOException -> 0x0256, RuntimeException -> 0x04fe }
    L_0x01ab:
        r6 = new java.io.IOException;	 Catch:{ IOException -> 0x01da, RuntimeException -> 0x0502 }
        r7 = java.lang.String.valueOf(r28);	 Catch:{ IOException -> 0x01da, RuntimeException -> 0x0502 }
        r7 = r7.length();	 Catch:{ IOException -> 0x01da, RuntimeException -> 0x0502 }
        r7 = r7 + 32;
        r8 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01da, RuntimeException -> 0x0502 }
        r8.<init>(r7);	 Catch:{ IOException -> 0x01da, RuntimeException -> 0x0502 }
        r7 = "HTTP status code ";
        r7 = r8.append(r7);	 Catch:{ IOException -> 0x01da, RuntimeException -> 0x0502 }
        r2 = r7.append(r2);	 Catch:{ IOException -> 0x01da, RuntimeException -> 0x0502 }
        r7 = " at ";
        r2 = r2.append(r7);	 Catch:{ IOException -> 0x01da, RuntimeException -> 0x0502 }
        r0 = r28;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x01da, RuntimeException -> 0x0502 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x01da, RuntimeException -> 0x0502 }
        r6.<init>(r2);	 Catch:{ IOException -> 0x01da, RuntimeException -> 0x0502 }
        throw r6;	 Catch:{ IOException -> 0x01da, RuntimeException -> 0x0502 }
    L_0x01da:
        r2 = move-exception;
    L_0x01db:
        r6 = r2 instanceof java.lang.RuntimeException;
        if (r6 == 0) goto L_0x01e8;
    L_0x01df:
        r6 = com.google.android.gms.ads.internal.zzbs.zzbD();
        r7 = "VideoStreamFullFileCache.preload";
        r6.zza(r2, r7);
    L_0x01e8:
        r5.close();	 Catch:{ IOException -> 0x04f8, NullPointerException -> 0x04fb }
    L_0x01eb:
        r0 = r27;
        r5 = r0.zzKa;
        if (r5 == 0) goto L_0x04c7;
    L_0x01f1:
        r2 = java.lang.String.valueOf(r28);
        r2 = r2.length();
        r2 = r2 + 26;
        r5 = new java.lang.StringBuilder;
        r5.<init>(r2);
        r2 = "Preload aborted for URL \"";
        r2 = r5.append(r2);
        r0 = r28;
        r2 = r2.append(r0);
        r5 = "\"";
        r2 = r2.append(r5);
        r2 = r2.toString();
        com.google.android.gms.internal.zzajc.zzaS(r2);
    L_0x0219:
        r2 = r13.exists();
        if (r2 == 0) goto L_0x023c;
    L_0x021f:
        r2 = r13.delete();
        if (r2 != 0) goto L_0x023c;
    L_0x0225:
        r5 = "Could not delete partial cache file at ";
        r2 = r13.getAbsolutePath();
        r2 = java.lang.String.valueOf(r2);
        r6 = r2.length();
        if (r6 == 0) goto L_0x04f1;
    L_0x0235:
        r2 = r5.concat(r2);
    L_0x0239:
        com.google.android.gms.internal.zzajc.zzaT(r2);
    L_0x023c:
        r2 = r13.getAbsolutePath();
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r4, r3);
        r2 = zzJX;
        r2.remove(r9);
        r2 = 0;
        goto L_0x0012;
    L_0x024f:
        r3 = new java.lang.String;	 Catch:{ IOException -> 0x0256, RuntimeException -> 0x04fe }
        r3.<init>(r6);	 Catch:{ IOException -> 0x0256, RuntimeException -> 0x04fe }
        goto L_0x01ab;
    L_0x0256:
        r2 = move-exception;
        r3 = r10;
        goto L_0x01db;
    L_0x0259:
        r7 = r3.getContentLength();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        if (r7 >= 0) goto L_0x0293;
    L_0x025f:
        r3 = "Stream cache aborted, missing content-length header at ";
        r2 = java.lang.String.valueOf(r28);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r4 = r2.length();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        if (r4 == 0) goto L_0x0288;
    L_0x026b:
        r2 = r3.concat(r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
    L_0x026f:
        com.google.android.gms.internal.zzajc.zzaT(r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r13.getAbsolutePath();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r3 = "contentLengthMissing";
        r4 = 0;
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r3, r4);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = zzJX;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2.remove(r9);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = 0;
        goto L_0x0012;
    L_0x0288:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        goto L_0x026f;
    L_0x028e:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        goto L_0x01db;
    L_0x0293:
        r2 = zzJY;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r0 = (long) r7;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r16 = r0;
        r0 = r16;
        r4 = r2.format(r0);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = com.google.android.gms.internal.zzmo.zzCn;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r6 = com.google.android.gms.ads.internal.zzbs.zzbL();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r6.zzd(r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = (java.lang.Integer) r2;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r15 = r2.intValue();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        if (r7 <= r15) goto L_0x0315;
    L_0x02b0:
        r2 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r2.length();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r2 + 33;
        r3 = java.lang.String.valueOf(r28);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r3 = r3.length();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r2 + r3;
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r3.<init>(r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = "Content length ";
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r2.append(r4);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r3 = " exceeds limit at ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r0 = r28;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        com.google.android.gms.internal.zzajc.zzaT(r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r3 = "File too big for full file cache. Size: ";
        r2 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r4 = r2.length();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        if (r4 == 0) goto L_0x030a;
    L_0x02f1:
        r2 = r3.concat(r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
    L_0x02f5:
        r3 = r13.getAbsolutePath();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r4 = "sizeExceeded";
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r3, r4, r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = zzJX;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2.remove(r9);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = 0;
        goto L_0x0012;
    L_0x030a:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        goto L_0x02f5;
    L_0x0310:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        goto L_0x01db;
    L_0x0315:
        r2 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r2.length();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r2 + 20;
        r6 = java.lang.String.valueOf(r28);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r6 = r6.length();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r2 + r6;
        r6 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r6.<init>(r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = "Caching ";
        r2 = r6.append(r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r2.append(r4);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r4 = " bytes from ";
        r2 = r2.append(r4);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r0 = r28;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        com.google.android.gms.internal.zzajc.zzaC(r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r2 = r3.getInputStream();	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r16 = java.nio.channels.Channels.newChannel(r2);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r12 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r12.<init>(r13);	 Catch:{ IOException -> 0x028e, RuntimeException -> 0x0310 }
        r17 = r12.getChannel();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r18 = java.nio.ByteBuffer.allocate(r2);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r19 = com.google.android.gms.ads.internal.zzbs.zzbF();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r6 = 0;
        r20 = r19.currentTimeMillis();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = com.google.android.gms.internal.zzmo.zzCr;	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r3 = com.google.android.gms.ads.internal.zzbs.zzbL();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = r3.zzd(r2);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = (java.lang.Long) r2;	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = r2.longValue();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r22 = new com.google.android.gms.internal.zzair;	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r0 = r22;
        r0.<init>(r2);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = com.google.android.gms.internal.zzmo.zzCq;	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r3 = com.google.android.gms.ads.internal.zzbs.zzbL();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = r3.zzd(r2);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = (java.lang.Long) r2;	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r24 = r2.longValue();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
    L_0x0391:
        r0 = r16;
        r1 = r18;
        r2 = r0.read(r1);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        if (r2 < 0) goto L_0x0456;
    L_0x039b:
        r6 = r6 + r2;
        if (r6 <= r15) goto L_0x03cb;
    L_0x039e:
        r4 = "sizeExceeded";
        r2 = "File too big for full file cache. Size: ";
        r3 = java.lang.Integer.toString(r6);	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r5 = r3.length();	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        if (r5 == 0) goto L_0x03c0;
    L_0x03b0:
        r3 = r2.concat(r3);	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
    L_0x03b4:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x03bc, RuntimeException -> 0x041a }
        r5 = "stream cache file size limit exceeded";
        r2.<init>(r5);	 Catch:{ IOException -> 0x03bc, RuntimeException -> 0x041a }
        throw r2;	 Catch:{ IOException -> 0x03bc, RuntimeException -> 0x041a }
    L_0x03bc:
        r2 = move-exception;
        r5 = r12;
        goto L_0x01db;
    L_0x03c0:
        r3 = new java.lang.String;	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r3.<init>(r2);	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        goto L_0x03b4;
    L_0x03c6:
        r2 = move-exception;
        r3 = r10;
        r5 = r12;
        goto L_0x01db;
    L_0x03cb:
        r18.flip();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
    L_0x03ce:
        r2 = r17.write(r18);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        if (r2 > 0) goto L_0x03ce;
    L_0x03d4:
        r18.clear();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = r19.currentTimeMillis();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = r2 - r20;
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = r4 * r24;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x041e;
    L_0x03e5:
        r4 = "downloadTimeout";
        r2 = java.lang.Long.toString(r24);	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r3 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r3 = r3.length();	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r3 = r3 + 29;
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r5.<init>(r3);	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r3 = "Timeout exceeded. Limit: ";
        r3 = r5.append(r3);	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r3 = " sec";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r3 = r2.toString();	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x03bc, RuntimeException -> 0x041a }
        r5 = "stream cache time limit exceeded";
        r2.<init>(r5);	 Catch:{ IOException -> 0x03bc, RuntimeException -> 0x041a }
        throw r2;	 Catch:{ IOException -> 0x03bc, RuntimeException -> 0x041a }
    L_0x041a:
        r2 = move-exception;
        r5 = r12;
        goto L_0x01db;
    L_0x041e:
        r0 = r27;
        r2 = r0.zzKa;	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        if (r2 == 0) goto L_0x0433;
    L_0x0424:
        r4 = "externalAbort";
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        r3 = "abort requested";
        r2.<init>(r3);	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
        throw r2;	 Catch:{ IOException -> 0x03c6, RuntimeException -> 0x042e }
    L_0x042e:
        r2 = move-exception;
        r3 = r10;
        r5 = r12;
        goto L_0x01db;
    L_0x0433:
        r2 = r22.tryAcquire();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        if (r2 == 0) goto L_0x0391;
    L_0x0439:
        r5 = r13.getAbsolutePath();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r23 = com.google.android.gms.internal.zzaiy.zzaaH;	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = new com.google.android.gms.internal.zzsc;	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r8 = 0;
        r3 = r27;
        r4 = r28;
        r2.<init>(r3, r4, r5, r6, r7, r8);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r0 = r23;
        r0.post(r2);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        goto L_0x0391;
    L_0x0450:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        r5 = r12;
        goto L_0x01db;
    L_0x0456:
        r12.close();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = 3;
        r2 = com.google.android.gms.internal.zzajc.zzz(r2);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        if (r2 == 0) goto L_0x049c;
    L_0x0460:
        r2 = zzJY;	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r4 = (long) r6;	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = r2.format(r4);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r3 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r3 = r3.length();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r3 = r3 + 22;
        r4 = java.lang.String.valueOf(r28);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r4 = r4.length();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r3 = r3 + r4;
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r4.<init>(r3);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r3 = "Preloaded ";
        r3 = r4.append(r3);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r3 = " bytes from ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r0 = r28;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        com.google.android.gms.internal.zzajc.zzaC(r2);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
    L_0x049c:
        r2 = 1;
        r3 = 0;
        r13.setReadable(r2, r3);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = r14.isFile();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        if (r2 == 0) goto L_0x04c1;
    L_0x04a7:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r14.setLastModified(r2);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
    L_0x04ae:
        r2 = r13.getAbsolutePath();	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r6);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = zzJX;	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2.remove(r9);	 Catch:{ IOException -> 0x0450, RuntimeException -> 0x0505 }
        r2 = 1;
        goto L_0x0012;
    L_0x04c1:
        r14.createNewFile();	 Catch:{ IOException -> 0x04c5, RuntimeException -> 0x0505 }
        goto L_0x04ae;
    L_0x04c5:
        r2 = move-exception;
        goto L_0x04ae;
    L_0x04c7:
        r5 = java.lang.String.valueOf(r28);
        r5 = r5.length();
        r5 = r5 + 25;
        r6 = new java.lang.StringBuilder;
        r6.<init>(r5);
        r5 = "Preload failed for URL \"";
        r5 = r6.append(r5);
        r0 = r28;
        r5 = r5.append(r0);
        r6 = "\"";
        r5 = r5.append(r6);
        r5 = r5.toString();
        com.google.android.gms.internal.zzajc.zzc(r5, r2);
        goto L_0x0219;
    L_0x04f1:
        r2 = new java.lang.String;
        r2.<init>(r5);
        goto L_0x0239;
    L_0x04f8:
        r5 = move-exception;
        goto L_0x01eb;
    L_0x04fb:
        r5 = move-exception;
        goto L_0x01eb;
    L_0x04fe:
        r2 = move-exception;
        r3 = r10;
        goto L_0x01db;
    L_0x0502:
        r2 = move-exception;
        goto L_0x01db;
    L_0x0505:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        r5 = r12;
        goto L_0x01db;
    L_0x050b:
        r2 = r4;
        r4 = r7;
        goto L_0x0099;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsh.zzU(java.lang.String):boolean");
    }
}
