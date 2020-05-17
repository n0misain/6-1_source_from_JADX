package com.google.android.gms.internal;

import java.util.Arrays;

public class zzcsp {
    private static final String TAG = zzcsp.class.getSimpleName();
    private static final char[] zzbyU = "0123456789abcdef".toCharArray();
    private final byte[] zzbyV;

    public zzcsp(byte[] bArr) {
        this.zzbyV = bArr;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return z;
        }
        if (obj == this) {
            return true;
        }
        try {
            return Arrays.equals(this.zzbyV, ((zzcsp) obj).zzbyV);
        } catch (ClassCastException e) {
            return z;
        }
    }

    public final byte[] getBytes() {
        return this.zzbyV;
    }

    public int hashCode() {
        return Arrays.hashCode(this.zzbyV) + 527;
    }

    public final zzcsp zzbu(int i) {
        return new zzcsp(Arrays.copyOfRange(this.zzbyV, 0, 4));
    }
}
