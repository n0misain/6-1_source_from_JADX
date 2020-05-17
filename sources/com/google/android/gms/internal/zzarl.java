package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzarl extends zzarn<Status> {
    zzarl(zzarg zzarg, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    protected final void zza(Context context, zzart zzart) throws RemoteException {
        zzart.zza(new zzarm(this));
    }

    protected final /* synthetic */ Result zzb(Status status) {
        return status;
    }
}
