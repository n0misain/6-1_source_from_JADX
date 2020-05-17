package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbs;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.WeakHashMap;

@zzzn
public final class zzzi implements zzzl {
    private static zzzl zzSg = null;
    private static final Object zzuF = new Object();
    private final String mPackageName;
    private final Object zzSh = new Object();
    private final WeakHashMap<Thread, Boolean> zzSi = new WeakHashMap();
    private final zzaje zzuK;

    private zzzi(String str, zzaje zzaje) {
        this.mPackageName = str;
        this.zzuK = zzaje;
        Thread thread = Looper.getMainLooper().getThread();
        if (thread != null) {
            synchronized (this.zzSh) {
                this.zzSi.put(thread, Boolean.valueOf(true));
            }
            thread.setUncaughtExceptionHandler(new zzzk(this, thread.getUncaughtExceptionHandler()));
        }
        Thread.setDefaultUncaughtExceptionHandler(new zzzj(this, Thread.getDefaultUncaughtExceptionHandler()));
    }

    private static boolean zzat(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith((String) zzbs.zzbL().zzd(zzmo.zzCe))) {
            return true;
        }
        try {
            return Class.forName(str).isAnnotationPresent(zzzn.class);
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = "Fail to check class type for class ";
            String valueOf = String.valueOf(str);
            zzajc.zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            return false;
        }
    }

    public static zzzl zzc(Context context, zzaje zzaje) {
        synchronized (zzuF) {
            if (zzSg == null) {
                if (((Boolean) zzbs.zzbL().zzd(zzmo.zzCc)).booleanValue()) {
                    String str = "unknown";
                    try {
                        str = context.getApplicationContext().getPackageName();
                    } catch (Throwable th) {
                        zzajc.zzaT("Cannot obtain package name, proceeding.");
                    }
                    zzSg = new zzzi(str, zzaje);
                } else {
                    zzSg = new zzzm();
                }
            }
        }
        return zzSg;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final void zza(java.lang.Thread r11, java.lang.Throwable r12) {
        /*
        r10 = this;
        r1 = 1;
        r3 = 0;
        if (r12 == 0) goto L_0x0046;
    L_0x0004:
        r2 = r3;
        r0 = r3;
        r5 = r12;
    L_0x0007:
        if (r5 == 0) goto L_0x003a;
    L_0x0009:
        r6 = r5.getStackTrace();
        r7 = r6.length;
        r4 = r3;
    L_0x000f:
        if (r4 >= r7) goto L_0x0034;
    L_0x0011:
        r8 = r6[r4];
        r9 = r8.getClassName();
        r9 = zzat(r9);
        if (r9 == 0) goto L_0x001e;
    L_0x001d:
        r0 = r1;
    L_0x001e:
        r9 = r10.getClass();
        r9 = r9.getName();
        r8 = r8.getClassName();
        r8 = r9.equals(r8);
        if (r8 == 0) goto L_0x0031;
    L_0x0030:
        r2 = r1;
    L_0x0031:
        r4 = r4 + 1;
        goto L_0x000f;
    L_0x0034:
        r4 = r5.getCause();
        r5 = r4;
        goto L_0x0007;
    L_0x003a:
        if (r0 == 0) goto L_0x0046;
    L_0x003c:
        if (r2 != 0) goto L_0x0046;
    L_0x003e:
        if (r1 == 0) goto L_0x0045;
    L_0x0040:
        r0 = "";
        r10.zza(r12, r0);
    L_0x0045:
        return;
    L_0x0046:
        r1 = r3;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzzi.zza(java.lang.Thread, java.lang.Throwable):void");
    }

    public final void zza(Throwable th, String str) {
        Throwable th2;
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzCd)).booleanValue()) {
            th2 = th;
        } else {
            Throwable th3;
            LinkedList linkedList = new LinkedList();
            for (th3 = th; th3 != null; th3 = th3.getCause()) {
                linkedList.push(th3);
            }
            th2 = null;
            while (!linkedList.isEmpty()) {
                Throwable th4;
                th3 = (Throwable) linkedList.pop();
                StackTraceElement[] stackTrace = th3.getStackTrace();
                ArrayList arrayList = new ArrayList();
                arrayList.add(new StackTraceElement(th3.getClass().getName(), "<filtered>", "<filtered>", 1));
                Object obj = null;
                for (StackTraceElement stackTraceElement : stackTrace) {
                    if (zzat(stackTraceElement.getClassName())) {
                        obj = 1;
                        arrayList.add(stackTraceElement);
                    } else {
                        String className = stackTraceElement.getClassName();
                        Object obj2 = (TextUtils.isEmpty(className) || !(className.startsWith("android.") || className.startsWith("java."))) ? null : 1;
                        if (obj2 != null) {
                            arrayList.add(stackTraceElement);
                        } else {
                            arrayList.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                        }
                    }
                }
                if (obj != null) {
                    th4 = th2 == null ? new Throwable(th3.getMessage()) : new Throwable(th3.getMessage(), th2);
                    th4.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
                } else {
                    th4 = th2;
                }
                th2 = th4;
            }
        }
        if (th2 != null) {
            Class cls = th.getClass();
            List arrayList2 = new ArrayList();
            Writer stringWriter = new StringWriter();
            th2.printStackTrace(new PrintWriter(stringWriter));
            zzbs.zzbz();
            arrayList2.add(new Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", VERSION.RELEASE).appendQueryParameter("api", String.valueOf(VERSION.SDK_INT)).appendQueryParameter("device", zzagz.zzhQ()).appendQueryParameter("js", this.zzuK.zzaP).appendQueryParameter("appid", this.mPackageName).appendQueryParameter("exceptiontype", cls.getName()).appendQueryParameter("stacktrace", stringWriter.toString()).appendQueryParameter("eids", TextUtils.join(",", zzmo.zzdJ())).appendQueryParameter("exceptionkey", str).appendQueryParameter("cl", "162978962").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", zzbs.zzbD().getSessionId()).toString());
            zzbs.zzbz();
            zzagz.zza(arrayList2, zzbs.zzbD().zzht());
        }
    }
}
