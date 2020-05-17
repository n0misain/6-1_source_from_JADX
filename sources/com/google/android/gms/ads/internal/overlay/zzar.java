package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzaia;
import com.google.android.gms.internal.zzaic;
import com.google.android.gms.internal.zzaid;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzmu;
import com.google.android.gms.internal.zzmz;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zzzn;
import com.twitter.sdk.android.core.internal.VineCardUtils;
import java.util.concurrent.TimeUnit;

@zzzn
public final class zzar {
    private final Context mContext;
    @Nullable
    private final zznb zzPs;
    private boolean zzPw;
    private final long[] zzQA;
    private final String[] zzQB;
    private boolean zzQC = false;
    private boolean zzQD = false;
    private boolean zzQE = false;
    private boolean zzQF = false;
    private zzy zzQG;
    private boolean zzQH;
    private boolean zzQI;
    private long zzQJ = -1;
    private final String zzQx;
    @Nullable
    private final zzmz zzQy;
    private final zzaia zzQz = new zzaid().zza("min_1", Double.MIN_VALUE, 1.0d).zza("1_5", 1.0d, 5.0d).zza("5_10", 5.0d, 10.0d).zza("10_20", 10.0d, 20.0d).zza("20_30", 20.0d, 30.0d).zza("30_max", 30.0d, Double.MAX_VALUE).zzid();
    private final zzaje zzuK;

    public zzar(Context context, zzaje zzaje, String str, @Nullable zznb zznb, @Nullable zzmz zzmz) {
        this.mContext = context;
        this.zzuK = zzaje;
        this.zzQx = str;
        this.zzPs = zznb;
        this.zzQy = zzmz;
        String str2 = (String) zzbs.zzbL().zzd(zzmo.zzCu);
        if (str2 == null) {
            this.zzQB = new String[0];
            this.zzQA = new long[0];
            return;
        }
        String[] split = TextUtils.split(str2, ",");
        this.zzQB = new String[split.length];
        this.zzQA = new long[split.length];
        for (int i = 0; i < split.length; i++) {
            try {
                this.zzQA[i] = Long.parseLong(split[i]);
            } catch (Throwable e) {
                zzajc.zzc("Unable to parse frame hash target time number.", e);
                this.zzQA[i] = -1;
            }
        }
    }

    public final void onStop() {
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzCt)).booleanValue() && !this.zzQH) {
            String valueOf;
            String valueOf2;
            Bundle bundle = new Bundle();
            bundle.putString("type", "native-player-metrics");
            bundle.putString(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, this.zzQx);
            bundle.putString(VineCardUtils.PLAYER_CARD, this.zzQG.zzfD());
            for (zzaic zzaic : this.zzQz.getBuckets()) {
                valueOf = String.valueOf("fps_c_");
                valueOf2 = String.valueOf(zzaic.name);
                bundle.putString(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), Integer.toString(zzaic.count));
                valueOf = String.valueOf("fps_p_");
                valueOf2 = String.valueOf(zzaic.name);
                bundle.putString(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), Double.toString(zzaic.zzZZ));
            }
            for (int i = 0; i < this.zzQA.length; i++) {
                valueOf2 = this.zzQB[i];
                if (valueOf2 != null) {
                    String valueOf3 = String.valueOf("fh_");
                    valueOf = String.valueOf(Long.valueOf(this.zzQA[i]));
                    bundle.putString(new StringBuilder(String.valueOf(valueOf3).length() + String.valueOf(valueOf).length()).append(valueOf3).append(valueOf).toString(), valueOf2);
                }
            }
            zzbs.zzbz().zza(this.mContext, this.zzuK.zzaP, "gmob-apps", bundle, true);
            this.zzQH = true;
        }
    }

    public final void zza(zzy zzy) {
        zzmu.zza(this.zzPs, this.zzQy, "vpc2");
        this.zzQC = true;
        if (this.zzPs != null) {
            this.zzPs.zzh("vpn", zzy.zzfD());
        }
        this.zzQG = zzy;
    }

    public final void zzb(zzy zzy) {
        if (this.zzQE && !this.zzQF) {
            zzmu.zza(this.zzPs, this.zzQy, "vff2");
            this.zzQF = true;
        }
        long nanoTime = zzbs.zzbF().nanoTime();
        if (this.zzPw && this.zzQI && this.zzQJ != -1) {
            this.zzQz.zza(((double) TimeUnit.SECONDS.toNanos(1)) / ((double) (nanoTime - this.zzQJ)));
        }
        this.zzQI = this.zzPw;
        this.zzQJ = nanoTime;
        long longValue = ((Long) zzbs.zzbL().zzd(zzmo.zzCv)).longValue();
        long currentPosition = (long) zzy.getCurrentPosition();
        int i = 0;
        while (i < this.zzQB.length) {
            if (this.zzQB[i] != null || longValue <= Math.abs(currentPosition - this.zzQA[i])) {
                i++;
            } else {
                String[] strArr = this.zzQB;
                Bitmap bitmap = zzy.getBitmap(8, 8);
                currentPosition = 0;
                longValue = 63;
                int i2 = 0;
                while (i2 < 8) {
                    int i3 = 0;
                    long j = currentPosition;
                    currentPosition = longValue;
                    while (i3 < 8) {
                        int pixel = bitmap.getPixel(i3, i2);
                        j |= (Color.green(pixel) + (Color.blue(pixel) + Color.red(pixel)) > 128 ? 1 : 0) << ((int) currentPosition);
                        i3++;
                        currentPosition--;
                    }
                    i2++;
                    longValue = currentPosition;
                    currentPosition = j;
                }
                strArr[i] = String.format("%016X", new Object[]{Long.valueOf(currentPosition)});
                return;
            }
        }
    }

    public final void zzfT() {
        if (this.zzQC && !this.zzQD) {
            zzmu.zza(this.zzPs, this.zzQy, "vfr2");
            this.zzQD = true;
        }
    }

    public final void zzgj() {
        this.zzPw = true;
        if (this.zzQD && !this.zzQE) {
            zzmu.zza(this.zzPs, this.zzQy, "vfp2");
            this.zzQE = true;
        }
    }

    public final void zzgk() {
        this.zzPw = false;
    }
}
