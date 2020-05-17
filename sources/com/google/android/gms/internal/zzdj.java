package com.google.android.gms.internal;

import android.provider.Settings.SettingNotFoundException;
import java.lang.reflect.InvocationTargetException;

public final class zzdj extends zzec {
    public zzdj(zzdb zzdb, String str, String str2, zzax zzax, int i, int i2) {
        super(zzdb, str, str2, zzax, i, 49);
    }

    protected final void zzT() throws IllegalAccessException, InvocationTargetException {
        this.zzro.zzbI = Integer.valueOf(2);
        try {
            this.zzro.zzbI = Integer.valueOf(((Boolean) this.zzrx.invoke(null, new Object[]{this.zzpJ.getApplicationContext()})).booleanValue() ? 1 : 0);
        } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof SettingNotFoundException)) {
                throw e;
            }
        }
    }
}
