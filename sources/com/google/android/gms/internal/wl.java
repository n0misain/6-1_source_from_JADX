package com.google.android.gms.internal;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public final class wl {
    private final String prefix;
    private final wm zzcaD;
    private final String zzchG;

    public wl(wm wmVar, String str) {
        this(wmVar, str, null);
    }

    public wl(wm wmVar, String str, String str2) {
        this.zzcaD = wmVar;
        this.zzchG = str;
        this.prefix = str2;
    }

    private static String zzg(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private final String zzh(String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        if (this.prefix == null) {
            return str;
        }
        String str2 = this.prefix;
        return new StringBuilder((String.valueOf(str2).length() + 3) + String.valueOf(str).length()).append(str2).append(" - ").append(str).toString();
    }

    public final void info(String str) {
        this.zzcaD.zzb(wn.INFO, this.zzchG, zzh(str, new Object[0]), System.currentTimeMillis());
    }

    public final boolean zzIH() {
        return this.zzcaD.zzGQ().ordinal() <= wn.DEBUG.ordinal();
    }

    public final void zzb(String str, Throwable th, Object... objArr) {
        if (zzIH()) {
            String zzh = zzh(str, objArr);
            if (th != null) {
                String valueOf = String.valueOf(zzg(th));
                zzh = new StringBuilder((String.valueOf(zzh).length() + 1) + String.valueOf(valueOf).length()).append(zzh).append("\n").append(valueOf).toString();
            }
            this.zzcaD.zzb(wn.DEBUG, this.zzchG, zzh, System.currentTimeMillis());
        }
    }

    public final void zzd(String str, Throwable th) {
        String valueOf = String.valueOf(zzh(str, new Object[0]));
        String valueOf2 = String.valueOf(zzg(th));
        this.zzcaD.zzb(wn.ERROR, this.zzchG, new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append("\n").append(valueOf2).toString(), System.currentTimeMillis());
    }

    public final void zze(String str, Throwable th) {
        this.zzcaD.zzb(wn.WARN, this.zzchG, zzh(str, new Object[0]), System.currentTimeMillis());
    }
}
