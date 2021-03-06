package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.account.zzd;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzaqo extends zzbay<Result, zzaqx> {
    private /* synthetic */ boolean val$enabled;

    zzaqo(zzaqn zzaqn, Api api, GoogleApiClient googleApiClient, boolean z) {
        this.val$enabled = z;
        super(api, googleApiClient);
    }

    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }

    protected final /* synthetic */ void zza(zzb zzb) throws RemoteException {
        ((zzd) ((zzaqx) zzb).zzrf()).zzO(this.val$enabled);
    }

    protected final Result zzb(Status status) {
        return new zzaqp(this, status);
    }
}
