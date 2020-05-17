package com.google.android.gms.internal;

import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class zzbx implements Runnable {
    private zzbx() {
    }

    public final void run() {
        try {
            zzbv.zzlQ = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE);
        } catch (NoSuchAlgorithmException e) {
        } finally {
            zzbv.zzlT.countDown();
        }
    }
}
