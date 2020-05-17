package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@zzzn
public abstract class zzhf {
    @Nullable
    private static MessageDigest zzyW = null;
    protected Object mLock = new Object();

    @Nullable
    protected final MessageDigest zzcW() {
        MessageDigest messageDigest;
        synchronized (this.mLock) {
            if (zzyW != null) {
                messageDigest = zzyW;
            } else {
                for (int i = 0; i < 2; i++) {
                    try {
                        zzyW = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE);
                    } catch (NoSuchAlgorithmException e) {
                    }
                }
                messageDigest = zzyW;
            }
        }
        return messageDigest;
    }

    abstract byte[] zzy(String str);
}
