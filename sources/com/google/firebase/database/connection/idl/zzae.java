package com.google.firebase.database.connection.idl;

import com.google.android.gms.internal.oi;

final class zzae implements oi {
    private /* synthetic */ zzn zzccj;

    zzae(zzad zzad, zzn zzn) {
        this.zzccj = zzn;
    }

    public final void onError(String str) {
        try {
            this.zzccj.onError(str);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public final void zzgF(String str) {
        try {
            this.zzccj.zzgF(str);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
