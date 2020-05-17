package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzbah implements zzazq {
    private static final Charset UTF_8 = Charset.forName(HttpRequest.CHARSET_UTF8);
    static Boolean zzazO = null;
    private zzbai zzazP;

    public zzbah(Context context) {
        this(new zzbai(context));
    }

    private zzbah(zzbai zzbai) {
        this.zzazP = (zzbai) zzbo.zzu(zzbai);
    }

    private static zzbaj zzcr(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        String str2 = "";
        int indexOf = str.indexOf(44);
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            i = indexOf + 1;
        }
        int indexOf2 = str.indexOf(47, i);
        if (indexOf2 <= 0) {
            str2 = "LogSamplerImpl";
            String str3 = "Failed to parse the rule: ";
            String valueOf = String.valueOf(str);
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        }
        try {
            long parseLong = Long.parseLong(str.substring(i, indexOf2));
            long parseLong2 = Long.parseLong(str.substring(indexOf2 + 1));
            if (parseLong >= 0 && parseLong2 >= 0) {
                return new zzbaj(str2, parseLong, parseLong2);
            }
            Log.e("LogSamplerImpl", "negative values not supported: " + parseLong + "/" + parseLong2);
            return null;
        } catch (Throwable e) {
            Throwable th = e;
            str3 = "LogSamplerImpl";
            String str4 = "parseLong() failed while parsing: ";
            valueOf = String.valueOf(str);
            Log.e(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4), th);
            return null;
        }
    }

    public final boolean zzg(String str, int i) {
        if (str == null || str.isEmpty()) {
            Object valueOf = i >= 0 ? String.valueOf(i) : null;
        }
        if (valueOf == null) {
            return true;
        }
        String str2;
        zzbai zzbai = this.zzazP;
        long j = zzbai.zzazQ == null ? 0 : hi.getLong(zzbai.zzazQ, "android_id", 0);
        zzbai zzbai2 = this.zzazP;
        if (zzbai2.zzazQ == null) {
            str2 = null;
        } else {
            ContentResolver contentResolver = zzbai2.zzazQ;
            String valueOf2 = String.valueOf("gms:playlog:service:sampling_");
            str2 = String.valueOf(valueOf);
            str2 = hi.zza(contentResolver, str2.length() != 0 ? valueOf2.concat(str2) : new String(valueOf2), null);
        }
        zzbaj zzcr = zzcr(str2);
        if (zzcr == null) {
            return true;
        }
        String str3 = zzcr.zzazR;
        if (str3 == null || str3.isEmpty()) {
            j = zzbac.zzf(ByteBuffer.allocate(8).putLong(j).array());
        } else {
            byte[] bytes = str3.getBytes(UTF_8);
            ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 8);
            allocate.put(bytes);
            allocate.putLong(j);
            j = zzbac.zzf(allocate.array());
        }
        long j2 = zzcr.zzazS;
        long j3 = zzcr.zzazT;
        if (j2 < 0 || j3 < 0) {
            throw new IllegalArgumentException("negative values not supported: " + j2 + "/" + j3);
        }
        if (j3 > 0) {
            if ((j >= 0 ? j % j3 : (((j & Long.MAX_VALUE) % j3) + ((Long.MAX_VALUE % j3) + 1)) % j3) < j2) {
                return true;
            }
        }
        return false;
    }
}
