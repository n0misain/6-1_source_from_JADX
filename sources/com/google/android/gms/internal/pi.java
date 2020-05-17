package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;

final class pi implements Runnable {
    private /* synthetic */ pg zzcbQ;

    pi(pg pgVar) {
        this.zzcbQ = pgVar;
    }

    public final void run() {
        if (this.zzcbQ.zzcbH != null) {
            this.zzcbQ.zzcbH.zzgM(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            this.zzcbQ.zzGv();
        }
    }
}
