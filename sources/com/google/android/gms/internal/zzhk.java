package com.google.android.gms.internal;

import android.support.v4.internal.view.SupportMenu;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

@zzzn
public final class zzhk extends zzhf {
    private MessageDigest zzzd;

    public final byte[] zzy(String str) {
        byte[] array;
        int i = 0;
        String[] split = str.split(" ");
        int zzA;
        if (split.length == 1) {
            zzA = zzhj.zzA(split[0]);
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putInt(zzA);
            array = allocate.array();
        } else if (split.length < 5) {
            byte[] bArr = new byte[(split.length << 1)];
            for (zzA = 0; zzA < split.length; zzA++) {
                int zzA2 = zzhj.zzA(split[zzA]);
                zzA2 = (zzA2 >> 16) ^ (SupportMenu.USER_MASK & zzA2);
                byte[] bArr2 = new byte[]{(byte) zzA2, (byte) (zzA2 >> 8)};
                bArr[zzA << 1] = bArr2[0];
                bArr[(zzA << 1) + 1] = bArr2[1];
            }
            array = bArr;
        } else {
            array = new byte[split.length];
            while (i < split.length) {
                int zzA3 = zzhj.zzA(split[i]);
                array[i] = (byte) ((zzA3 >> 24) ^ (((zzA3 & 255) ^ ((zzA3 >> 8) & 255)) ^ ((zzA3 >> 16) & 255)));
                i++;
            }
        }
        this.zzzd = zzcW();
        synchronized (this.mLock) {
            if (this.zzzd == null) {
                array = new byte[0];
            } else {
                this.zzzd.reset();
                this.zzzd.update(array);
                Object digest = this.zzzd.digest();
                array = new byte[(digest.length > 4 ? 4 : digest.length)];
                System.arraycopy(digest, 0, array, 0, array.length);
            }
        }
        return array;
    }
}
