package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzaey implements ThreadFactory {
    private final AtomicInteger zzXJ = new AtomicInteger(1);

    zzaey(zzaew zzaew) {
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "AdWorker(SCION_TASK_EXECUTOR) #" + this.zzXJ.getAndIncrement());
    }
}
