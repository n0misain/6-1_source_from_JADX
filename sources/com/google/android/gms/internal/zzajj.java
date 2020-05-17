package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

final class zzajj implements Runnable {
    private /* synthetic */ zzajg zzaaV;
    private /* synthetic */ zzajl zzaaW;
    private /* synthetic */ zzajm zzaaX;

    zzajj(zzajg zzajg, zzajl zzajl, zzajm zzajm) {
        this.zzaaV = zzajg;
        this.zzaaW = zzajl;
        this.zzaaX = zzajm;
    }

    public final void run() {
        try {
            this.zzaaV.zzg(this.zzaaW.apply(this.zzaaX.get()));
            return;
        } catch (CancellationException e) {
        } catch (InterruptedException e2) {
        } catch (ExecutionException e3) {
        }
        this.zzaaV.cancel(true);
    }
}
