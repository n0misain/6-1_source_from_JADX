package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.twitter.sdk.android.core.TwitterApiErrorConstants;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@zzzn
public final class zzajd implements zzajb {
    @Nullable
    private final String zzJP;

    public zzajd() {
        this(null);
    }

    public zzajd(@Nullable String str) {
        this.zzJP = str;
    }

    @WorkerThread
    public final void zzaN(String str) {
        String valueOf;
        HttpURLConnection httpURLConnection;
        try {
            String str2 = "Pinging URL: ";
            valueOf = String.valueOf(str);
            zzajc.zzaC(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            zzji.zzds();
            zzaiy.zza(true, httpURLConnection, this.zzJP);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < Callback.DEFAULT_DRAG_ANIMATION_DURATION || responseCode >= TwitterApiErrorConstants.REGISTRATION_INVALID_INPUT) {
                zzajc.zzaT(new StringBuilder(String.valueOf(str).length() + 65).append("Received non-success response code ").append(responseCode).append(" from pinging URL: ").append(str).toString());
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            valueOf = String.valueOf(e.getMessage());
            zzajc.zzaT(new StringBuilder((String.valueOf(str).length() + 32) + String.valueOf(valueOf).length()).append("Error while parsing ping URL: ").append(str).append(". ").append(valueOf).toString());
        } catch (IOException e2) {
            valueOf = String.valueOf(e2.getMessage());
            zzajc.zzaT(new StringBuilder((String.valueOf(str).length() + 27) + String.valueOf(valueOf).length()).append("Error while pinging URL: ").append(str).append(". ").append(valueOf).toString());
        } catch (RuntimeException e3) {
            valueOf = String.valueOf(e3.getMessage());
            zzajc.zzaT(new StringBuilder((String.valueOf(str).length() + 27) + String.valueOf(valueOf).length()).append("Error while pinging URL: ").append(str).append(". ").append(valueOf).toString());
        } catch (Throwable th) {
            httpURLConnection.disconnect();
        }
    }
}
