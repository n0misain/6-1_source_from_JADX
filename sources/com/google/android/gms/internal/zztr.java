package com.google.android.gms.internal;

import android.os.Parcel;
import android.util.Base64;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@zzzn
final class zztr {
    final int zzKt;
    final String zztV;
    final zzir zzuT;

    private zztr(zzir zzir, String str, int i) {
        this.zzuT = zzir;
        this.zztV = str;
        this.zzKt = i;
    }

    zztr(zztn zztn) {
        this(zztn.zzeI(), zztn.getAdUnitId(), zztn.getNetworkType());
    }

    static zztr zzab(String str) throws IOException {
        String[] split = str.split("\u0000");
        if (split.length != 3) {
            throw new IOException("Incorrect field count for QueueSeed.");
        }
        Parcel obtain = Parcel.obtain();
        try {
            String str2 = new String(Base64.decode(split[0], 0), HttpRequest.CHARSET_UTF8);
            int parseInt = Integer.parseInt(split[1]);
            byte[] decode = Base64.decode(split[2], 0);
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            zztr zztr = new zztr((zzir) zzir.CREATOR.createFromParcel(obtain), str2, parseInt);
            obtain.recycle();
            return zztr;
        } catch (Throwable th) {
            obtain.recycle();
        }
    }

    final String zzeW() {
        Parcel obtain = Parcel.obtain();
        String encodeToString;
        try {
            encodeToString = Base64.encodeToString(this.zztV.getBytes(HttpRequest.CHARSET_UTF8), 0);
            String num = Integer.toString(this.zzKt);
            this.zzuT.writeToParcel(obtain, 0);
            String encodeToString2 = Base64.encodeToString(obtain.marshall(), 0);
            encodeToString = new StringBuilder(((String.valueOf(encodeToString).length() + 2) + String.valueOf(num).length()) + String.valueOf(encodeToString2).length()).append(encodeToString).append("\u0000").append(num).append("\u0000").append(encodeToString2).toString();
            return encodeToString;
        } catch (UnsupportedEncodingException e) {
            encodeToString = "QueueSeed encode failed because UTF-8 is not available.";
            zzajc.m1216e(encodeToString);
            return "";
        } finally {
            obtain.recycle();
        }
    }
}
