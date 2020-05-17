package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbei;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public final class zzb extends AsyncTaskLoader<Void> implements zzbei {
    private Semaphore zzami = new Semaphore(0);
    private Set<GoogleApiClient> zzamj;

    public zzb(Context context, Set<GoogleApiClient> set) {
        super(context);
        this.zzamj = set;
    }

    private final Void zzmE() {
        int i = 0;
        for (GoogleApiClient zza : this.zzamj) {
            i = zza.zza((zzbei) this) ? i + 1 : i;
        }
        try {
            this.zzami.tryAcquire(i, 5, TimeUnit.SECONDS);
        } catch (Throwable e) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", e);
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public final /* synthetic */ Object loadInBackground() {
        return zzmE();
    }

    protected final void onStartLoading() {
        this.zzami.drainPermits();
        forceLoad();
    }

    public final void zzmF() {
        this.zzami.release();
    }
}
