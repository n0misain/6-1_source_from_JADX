package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbo;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

final class jm extends kj<AuthResult, ll> {
    @NonNull
    private final String zzakv;

    public jm(@NonNull String str) {
        super(2);
        this.zzakv = zzbo.zzh(str, "token cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzbWM.zzb(this.zzakv, this.zzbWK);
    }

    public final void zzEL() {
        FirebaseUser zzb = iq.zza(this.zzbVZ, this.zzbWU);
        ((ll) this.zzbWN).zza(this.zzbWT, zzb);
        zzV(new lp(zzb));
    }
}
