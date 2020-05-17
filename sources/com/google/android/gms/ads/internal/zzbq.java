package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzeu;
import com.google.android.gms.internal.zzmo;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class zzbq extends AsyncTask<Void, Void, String> {
    private /* synthetic */ zzbm zzvf;

    private zzbq(zzbm zzbm) {
        this.zzvf = zzbm;
    }

    private final String zza(Void... voidArr) {
        Throwable e;
        try {
            this.zzvf.zzvd = (zzeu) this.zzvf.zzva.get(((Long) zzbs.zzbL().zzd(zzmo.zzFX)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            e = e2;
            zzajc.zzc("Failed to load ad data", e);
        } catch (ExecutionException e3) {
            e = e3;
            zzajc.zzc("Failed to load ad data", e);
        } catch (TimeoutException e4) {
            zzajc.zzaT("Timed out waiting for ad data");
        }
        return this.zzvf.zzbp();
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return zza((Void[]) objArr);
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        if (this.zzvf.zzvc != null && str != null) {
            this.zzvf.zzvc.loadUrl(str);
        }
    }
}
