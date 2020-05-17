package com.google.android.gms.internal;

final class wt extends ns<wp, xm> {
    private boolean zzchU = false;
    private /* synthetic */ wu zzchV;
    private /* synthetic */ wr zzchW;

    wt(wr wrVar, wu wuVar) {
        this.zzchW = wrVar;
        this.zzchV = wuVar;
    }

    public final /* synthetic */ void zzh(Object obj, Object obj2) {
        wp wpVar = (wp) obj;
        xm xmVar = (xm) obj2;
        if (!this.zzchU && wpVar.zzi(wp.zzIL()) > 0) {
            this.zzchU = true;
            this.zzchV.zzb(wp.zzIL(), this.zzchW.zzIR());
        }
        this.zzchV.zzb(wpVar, xmVar);
    }
}
