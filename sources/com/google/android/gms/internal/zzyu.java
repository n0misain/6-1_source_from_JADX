package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.common.util.zzq;
import java.io.InputStream;

final class zzyu implements zzaii<zznp> {
    private /* synthetic */ String zzNO;
    private /* synthetic */ zzyn zzRF;
    private /* synthetic */ boolean zzRT;
    private /* synthetic */ double zzRU;
    private /* synthetic */ boolean zzRV;

    zzyu(zzyn zzyn, boolean z, double d, boolean z2, String str) {
        this.zzRF = zzyn;
        this.zzRT = z;
        this.zzRU = d;
        this.zzRV = z2;
        this.zzNO = str;
    }

    @TargetApi(19)
    private final zznp zzg(InputStream inputStream) {
        Bitmap decodeStream;
        Options options = new Options();
        options.inDensity = (int) (160.0d * this.zzRU);
        if (!this.zzRV) {
            options.inPreferredConfig = Config.RGB_565;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
        } catch (Throwable e) {
            zzajc.zzb("Error grabbing image.", e);
            decodeStream = null;
        }
        if (decodeStream == null) {
            this.zzRF.zzc(2, this.zzRT);
            return null;
        }
        long uptimeMillis2 = SystemClock.uptimeMillis();
        if (zzq.zzsc() && zzafr.zzhM()) {
            int width = decodeStream.getWidth();
            int height = decodeStream.getHeight();
            zzafr.m1217v("Decoded image w: " + width + " h:" + height + " bytes: " + decodeStream.getAllocationByteCount() + " time: " + (uptimeMillis2 - uptimeMillis) + " on ui thread: " + (Looper.getMainLooper().getThread() == Thread.currentThread()));
        }
        return new zznp(new BitmapDrawable(Resources.getSystem(), decodeStream), Uri.parse(this.zzNO), this.zzRU);
    }

    public final /* synthetic */ Object zzgy() {
        this.zzRF.zzc(2, this.zzRT);
        return null;
    }

    @TargetApi(19)
    public final /* synthetic */ Object zzh(InputStream inputStream) {
        return zzg(inputStream);
    }
}
