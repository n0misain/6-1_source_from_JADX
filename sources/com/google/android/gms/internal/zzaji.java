package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbs;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@zzzn
public final class zzaji {
    public static <A, B> zzajm<B> zza(zzajm<A> zzajm, zzajl<A, B> zzajl) {
        zzajm zzajg = new zzajg();
        zzajm.zzc(new zzajj(zzajg, zzajl, zzajm));
        return zzajg;
    }

    public static <T> T zza(Future<T> future, T t) {
        try {
            t = future.get(((Long) zzbs.zzbL().zzd(zzmo.zzEJ)).longValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            future.cancel(true);
            zzajc.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbs.zzbD().zza(e, "Futures.resolveFuture");
        } catch (Throwable e2) {
            future.cancel(true);
            zzajc.zzb("Error waiting for future.", e2);
            zzbs.zzbD().zza(e2, "Futures.resolveFuture");
        }
        return t;
    }

    public static <T> T zza(Future<T> future, T t, long j, TimeUnit timeUnit) {
        try {
            t = future.get(j, timeUnit);
        } catch (Throwable e) {
            future.cancel(true);
            zzajc.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbs.zzbD().zza(e, "Futures.resolveFuture");
        } catch (Throwable e2) {
            future.cancel(true);
            zzajc.zzb("Error waiting for future.", e2);
            zzbs.zzbD().zza(e2, "Futures.resolveFuture");
        }
        return t;
    }

    public static <V> zzajm<List<V>> zzp(List<zzajm<V>> list) {
        zzajm zzajg = new zzajg();
        int size = list.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzajm zzc : list) {
            zzc.zzc(new zzajk(atomicInteger, size, zzajg, list));
        }
        return zzajg;
    }

    private static <V> List<V> zzq(List<zzajm<V>> list) throws ExecutionException, InterruptedException {
        List<V> arrayList = new ArrayList();
        for (zzajm zzajm : list) {
            Object obj = zzajm.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
