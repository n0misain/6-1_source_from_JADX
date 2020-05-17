package com.google.android.gms.internal;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.nio.charset.Charset;
import java.security.MessageDigest;

@zzzn
public final class zzho extends zzhf {
    private MessageDigest zzzd;
    private final int zzzg;
    private final int zzzh;

    public zzho(int i) {
        int i2 = i / 8;
        if (i % 8 > 0) {
            i2++;
        }
        this.zzzg = i2;
        this.zzzh = i;
    }

    public final byte[] zzy(String str) {
        byte[] bArr;
        synchronized (this.mLock) {
            this.zzzd = zzcW();
            if (this.zzzd == null) {
                bArr = new byte[0];
            } else {
                this.zzzd.reset();
                this.zzzd.update(str.getBytes(Charset.forName(HttpRequest.CHARSET_UTF8)));
                Object digest = this.zzzd.digest();
                bArr = new byte[(digest.length > this.zzzg ? this.zzzg : digest.length)];
                System.arraycopy(digest, 0, bArr, 0, bArr.length);
                if (this.zzzh % 8 > 0) {
                    int i;
                    long j = 0;
                    for (i = 0; i < bArr.length; i++) {
                        if (i > 0) {
                            j <<= 8;
                        }
                        j += (long) (bArr[i] & 255);
                    }
                    j >>>= 8 - (this.zzzh % 8);
                    for (i = this.zzzg - 1; i >= 0; i--) {
                        bArr[i] = (byte) ((int) (255 & j));
                        j >>>= 8;
                    }
                }
            }
        }
        return bArr;
    }
}
