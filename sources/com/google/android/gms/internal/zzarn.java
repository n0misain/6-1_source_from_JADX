package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

abstract class zzarn<R extends Result> extends zzbay<R, zzaro> {
    zzarn(GoogleApiClient googleApiClient) {
        super(Auth.CREDENTIALS_API, googleApiClient);
    }

    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }

    protected abstract void zza(Context context, zzart zzart) throws DeadObjectException, RemoteException;

    protected final /* synthetic */ void zza(zzb zzb) throws RemoteException {
        zzaro zzaro = (zzaro) zzb;
        zza(zzaro.getContext(), (zzart) zzaro.zzrf());
    }
}
