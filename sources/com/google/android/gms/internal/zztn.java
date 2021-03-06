package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import java.util.Iterator;
import java.util.LinkedList;

@zzzn
final class zztn {
    private final LinkedList<zzto> zzKr = new LinkedList();
    private zzir zzKs;
    private final int zzKt;
    private boolean zzKu;
    private final String zztV;

    zztn(zzir zzir, String str, int i) {
        zzbo.zzu(zzir);
        zzbo.zzu(str);
        this.zzKs = zzir;
        this.zztV = str;
        this.zzKt = i;
    }

    final String getAdUnitId() {
        return this.zztV;
    }

    final int getNetworkType() {
        return this.zzKt;
    }

    final int size() {
        return this.zzKr.size();
    }

    final void zza(zzsi zzsi, zzir zzir) {
        this.zzKr.add(new zzto(this, zzsi, zzir));
    }

    final boolean zzb(zzsi zzsi) {
        zzto zzto = new zzto(this, zzsi);
        this.zzKr.add(zzto);
        return zzto.load();
    }

    final zzir zzeI() {
        return this.zzKs;
    }

    final int zzeJ() {
        Iterator it = this.zzKr.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((zzto) it.next()).zzKz ? i + 1 : i;
        }
        return i;
    }

    final int zzeK() {
        Iterator it = this.zzKr.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((zzto) it.next()).load() ? i + 1 : i;
        }
        return i;
    }

    final void zzeL() {
        this.zzKu = true;
    }

    final boolean zzeM() {
        return this.zzKu;
    }

    final zzto zzm(@Nullable zzir zzir) {
        if (zzir != null) {
            this.zzKs = zzir;
        }
        return (zzto) this.zzKr.remove();
    }
}
