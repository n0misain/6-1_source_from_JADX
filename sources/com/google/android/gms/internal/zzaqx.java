package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.account.zzd;
import com.google.android.gms.auth.account.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;
import com.twitter.sdk.android.core.TwitterApiErrorConstants;

public final class zzaqx extends zzz<zzd> {
    public zzaqx(Context context, Looper looper, zzq zzq, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, TwitterApiErrorConstants.EMAIL_ALREADY_REGISTERED, zzq, connectionCallbacks, onConnectionFailedListener);
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        return zze.zzz(iBinder);
    }

    protected final String zzdb() {
        return "com.google.android.gms.auth.account.workaccount.START";
    }

    protected final String zzdc() {
        return "com.google.android.gms.auth.account.IWorkAccountService";
    }
}
