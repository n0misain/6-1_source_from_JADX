package com.google.android.gms.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class zzdp extends zzec {
    public zzdp(zzdb zzdb, String str, String str2, zzax zzax, int i, int i2) {
        super(zzdb, str, str2, zzax, i, 24);
    }

    private final void zzW() {
        AdvertisingIdClient zzO = this.zzpJ.zzO();
        if (zzO != null) {
            try {
                Info info = zzO.getInfo();
                String zzn = zzdg.zzn(info.getId());
                if (zzn != null) {
                    synchronized (this.zzro) {
                        this.zzro.zzbU = zzn;
                        this.zzro.zzbW = Boolean.valueOf(info.isLimitAdTrackingEnabled());
                        this.zzro.zzbV = Integer.valueOf(5);
                    }
                }
            } catch (IOException e) {
            }
        }
    }

    public final /* synthetic */ Object call() throws Exception {
        return zzV();
    }

    protected final void zzT() throws IllegalAccessException, InvocationTargetException {
        if (this.zzpJ.zzH()) {
            zzW();
            return;
        }
        synchronized (this.zzro) {
            this.zzro.zzbU = (String) this.zzrx.invoke(null, new Object[]{this.zzpJ.getApplicationContext()});
        }
    }

    public final Void zzV() throws Exception {
        if (this.zzpJ.isInitialized()) {
            return super.zzV();
        }
        if (this.zzpJ.zzH()) {
            zzW();
        }
        return null;
    }
}
