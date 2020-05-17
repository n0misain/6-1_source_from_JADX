package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.common.util.zzn;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class zzaav implements Runnable {
    private /* synthetic */ OutputStream zzTS;
    private /* synthetic */ byte[] zzTT;

    zzaav(zzaau zzaau, OutputStream outputStream, byte[] bArr) {
        this.zzTS = outputStream;
        this.zzTT = bArr;
    }

    public final void run() {
        Throwable e;
        Closeable dataOutputStream;
        try {
            dataOutputStream = new DataOutputStream(this.zzTS);
            try {
                dataOutputStream.writeInt(this.zzTT.length);
                dataOutputStream.write(this.zzTT);
                zzn.closeQuietly(dataOutputStream);
            } catch (IOException e2) {
                e = e2;
                try {
                    zzajc.zzb("Error transporting the ad response", e);
                    zzbs.zzbD().zza(e, "LargeParcelTeleporter.pipeData.1");
                    if (dataOutputStream != null) {
                        zzn.closeQuietly(this.zzTS);
                    } else {
                        zzn.closeQuietly(dataOutputStream);
                    }
                } catch (Throwable th) {
                    e = th;
                    if (dataOutputStream != null) {
                        zzn.closeQuietly(this.zzTS);
                    } else {
                        zzn.closeQuietly(dataOutputStream);
                    }
                    throw e;
                }
            }
        } catch (IOException e3) {
            e = e3;
            dataOutputStream = null;
            zzajc.zzb("Error transporting the ad response", e);
            zzbs.zzbD().zza(e, "LargeParcelTeleporter.pipeData.1");
            if (dataOutputStream != null) {
                zzn.closeQuietly(dataOutputStream);
            } else {
                zzn.closeQuietly(this.zzTS);
            }
        } catch (Throwable th2) {
            e = th2;
            dataOutputStream = null;
            if (dataOutputStream != null) {
                zzn.closeQuietly(dataOutputStream);
            } else {
                zzn.closeQuietly(this.zzTS);
            }
            throw e;
        }
    }
}
