package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Iterator;

public final class xd extends wr implements xm {
    private static final xd zzcij = new xd();

    private xd() {
    }

    public static xd zzJb() {
        return zzcij;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return zzg((xm) obj);
    }

    public final boolean equals(Object obj) {
        return obj instanceof xd ? true : (obj instanceof xm) && ((xm) obj).isEmpty() && equals(((xm) obj).zzIR());
    }

    public final int getChildCount() {
        return 0;
    }

    public final Object getValue() {
        return null;
    }

    public final Object getValue(boolean z) {
        return null;
    }

    public final int hashCode() {
        return 0;
    }

    public final boolean isEmpty() {
        return true;
    }

    public final Iterator<xl> iterator() {
        return Collections.emptyList().iterator();
    }

    public final String toString() {
        return "<Empty Node>";
    }

    public final Iterator<xl> zzFz() {
        return Collections.emptyList().iterator();
    }

    public final String zzIP() {
        return "";
    }

    public final boolean zzIQ() {
        return false;
    }

    public final xm zzIR() {
        return this;
    }

    public final xm zzN(qr qrVar) {
        return this;
    }

    public final String zza(xo xoVar) {
        return "";
    }

    public final xm zze(wp wpVar, xm xmVar) {
        return (xmVar.isEmpty() || wpVar.zzIN()) ? this : new wr().zze(wpVar, xmVar);
    }

    public final /* bridge */ /* synthetic */ xm zzf(xm xmVar) {
        return this;
    }

    public final int zzg(xm xmVar) {
        return xmVar.isEmpty() ? 0 : -1;
    }

    public final boolean zzk(wp wpVar) {
        return false;
    }

    public final wp zzl(wp wpVar) {
        return null;
    }

    public final xm zzl(qr qrVar, xm xmVar) {
        return qrVar.isEmpty() ? xmVar : zze(qrVar.zzHc(), zzl(qrVar.zzHd(), xmVar));
    }

    public final xm zzm(wp wpVar) {
        return this;
    }
}
