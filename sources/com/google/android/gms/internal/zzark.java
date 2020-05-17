package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzark extends zzarn<Status> {
    private /* synthetic */ Credential zzalA;

    zzark(zzarg zzarg, GoogleApiClient googleApiClient, Credential credential) {
        this.zzalA = credential;
        super(googleApiClient);
    }

    protected final void zza(Context context, zzart zzart) throws RemoteException {
        zzart.zza(new zzarm(this), new zzarp(this.zzalA));
    }

    protected final /* synthetic */ Result zzb(Status status) {
        return status;
    }
}
