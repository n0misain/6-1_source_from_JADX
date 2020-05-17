package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbs;

@zzzn
final class zzts {
    private static final zztp zzKJ = zztp.zzeN();
    private static final float zzKK = ((Float) zzbs.zzbL().zzd(zzmo.zzEk)).floatValue();
    private static final long zzKL = ((Long) zzbs.zzbL().zzd(zzmo.zzEi)).longValue();
    private static final float zzKM = ((Float) zzbs.zzbL().zzd(zzmo.zzEl)).floatValue();
    private static final long zzKN = ((Long) zzbs.zzbL().zzd(zzmo.zzEj)).longValue();

    private static int zzb(long j, int i) {
        return (int) ((j >>> ((i % 16) * 4)) & 15);
    }

    static boolean zzeX() {
        int i = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int zzeU = zzKJ.zzeU();
        int zzeV = zzKJ.zzeV();
        int zzeS = zzKJ.zzeS() + zzKJ.zzeT();
        int zzb = (zzeU >= 16 || zzKN == 0) ? zzKM != 0.0f ? ((int) (zzKM * ((float) zzeU))) + 1 : ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : zzb(zzKN, zzeU);
        if (zzeV <= zzb) {
            if (zzeU < 16 && zzKL != 0) {
                i = zzb(zzKL, zzeU);
            } else if (zzKK != 0.0f) {
                i = (int) (zzKK * ((float) zzeU));
            }
            if (zzeS <= i) {
                return true;
            }
        }
        return false;
    }
}
