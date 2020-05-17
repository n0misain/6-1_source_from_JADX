package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.AdActivity;

@zzzn
public final class zzafl {
    private final Object mLock = new Object();
    private String mSessionId;
    int zzYI = -1;
    private long zzYM = -1;
    private long zzYN = -1;
    private int zzYO = -1;
    private long zzYP = 0;
    private int zzYQ = 0;
    private int zzYR = 0;

    public zzafl(String str) {
        this.mSessionId = str;
    }

    private static boolean zzB(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "android");
        if (identifier == 0) {
            zzajc.zzaS("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            zzajc.zzaS("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (NameNotFoundException e) {
            zzajc.zzaT("Fail to fetch AdActivity theme");
            zzajc.zzaS("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.zzir r11, long r12) {
        /*
        r10 = this;
        r1 = r10.mLock;
        monitor-enter(r1);
        r0 = com.google.android.gms.ads.internal.zzbs.zzbD();	 Catch:{ all -> 0x0057 }
        r2 = r0.zzhy();	 Catch:{ all -> 0x0057 }
        r0 = com.google.android.gms.ads.internal.zzbs.zzbF();	 Catch:{ all -> 0x0057 }
        r4 = r0.currentTimeMillis();	 Catch:{ all -> 0x0057 }
        r6 = r10.zzYN;	 Catch:{ all -> 0x0057 }
        r8 = -1;
        r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r0 != 0) goto L_0x005a;
    L_0x001b:
        r2 = r4 - r2;
        r0 = com.google.android.gms.internal.zzmo.zzDL;	 Catch:{ all -> 0x0057 }
        r6 = com.google.android.gms.ads.internal.zzbs.zzbL();	 Catch:{ all -> 0x0057 }
        r0 = r6.zzd(r0);	 Catch:{ all -> 0x0057 }
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x0057 }
        r6 = r0.longValue();	 Catch:{ all -> 0x0057 }
        r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x004c;
    L_0x0031:
        r0 = -1;
        r10.zzYI = r0;	 Catch:{ all -> 0x0057 }
    L_0x0034:
        r10.zzYN = r12;	 Catch:{ all -> 0x0057 }
        r2 = r10.zzYN;	 Catch:{ all -> 0x0057 }
        r10.zzYM = r2;	 Catch:{ all -> 0x0057 }
    L_0x003a:
        r0 = r11.extras;	 Catch:{ all -> 0x0057 }
        if (r0 == 0) goto L_0x005d;
    L_0x003e:
        r0 = r11.extras;	 Catch:{ all -> 0x0057 }
        r2 = "gw";
        r3 = 2;
        r0 = r0.getInt(r2, r3);	 Catch:{ all -> 0x0057 }
        r2 = 1;
        if (r0 != r2) goto L_0x005d;
    L_0x004a:
        monitor-exit(r1);	 Catch:{ all -> 0x0057 }
    L_0x004b:
        return;
    L_0x004c:
        r0 = com.google.android.gms.ads.internal.zzbs.zzbD();	 Catch:{ all -> 0x0057 }
        r0 = r0.zzhB();	 Catch:{ all -> 0x0057 }
        r10.zzYI = r0;	 Catch:{ all -> 0x0057 }
        goto L_0x0034;
    L_0x0057:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0057 }
        throw r0;
    L_0x005a:
        r10.zzYM = r12;	 Catch:{ all -> 0x0057 }
        goto L_0x003a;
    L_0x005d:
        r0 = r10.zzYO;	 Catch:{ all -> 0x0057 }
        r0 = r0 + 1;
        r10.zzYO = r0;	 Catch:{ all -> 0x0057 }
        r0 = r10.zzYI;	 Catch:{ all -> 0x0057 }
        r0 = r0 + 1;
        r10.zzYI = r0;	 Catch:{ all -> 0x0057 }
        r0 = r10.zzYI;	 Catch:{ all -> 0x0057 }
        if (r0 != 0) goto L_0x007a;
    L_0x006d:
        r2 = 0;
        r10.zzYP = r2;	 Catch:{ all -> 0x0057 }
        r0 = com.google.android.gms.ads.internal.zzbs.zzbD();	 Catch:{ all -> 0x0057 }
        r0.zzk(r4);	 Catch:{ all -> 0x0057 }
    L_0x0078:
        monitor-exit(r1);	 Catch:{ all -> 0x0057 }
        goto L_0x004b;
    L_0x007a:
        r0 = com.google.android.gms.ads.internal.zzbs.zzbD();	 Catch:{ all -> 0x0057 }
        r2 = r0.zzhz();	 Catch:{ all -> 0x0057 }
        r2 = r4 - r2;
        r10.zzYP = r2;	 Catch:{ all -> 0x0057 }
        goto L_0x0078;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzafl.zzb(com.google.android.gms.internal.zzir, long):void");
    }

    public final void zzhc() {
        synchronized (this.mLock) {
            this.zzYR++;
        }
    }

    public final void zzhd() {
        synchronized (this.mLock) {
            this.zzYQ++;
        }
    }

    public final Bundle zzo(Context context, String str) {
        Bundle bundle;
        synchronized (this.mLock) {
            bundle = new Bundle();
            bundle.putString("session_id", this.mSessionId);
            bundle.putLong("basets", this.zzYN);
            bundle.putLong("currts", this.zzYM);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.zzYO);
            bundle.putInt("preqs_in_session", this.zzYI);
            bundle.putLong("time_in_session", this.zzYP);
            bundle.putInt("pclick", this.zzYQ);
            bundle.putInt("pimp", this.zzYR);
            bundle.putBoolean("support_transparent_background", zzB(context));
        }
        return bundle;
    }
}
