package com.google.android.gms.internal;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.Logger.Level;

public final class qg extends qd {
    public final synchronized void setLogLevel(Level level) {
        zzGP();
        switch (level) {
            case DEBUG:
                this.zzccS = wn.DEBUG;
                break;
            case INFO:
                this.zzccS = wn.INFO;
                break;
            case WARN:
                this.zzccS = wn.WARN;
                break;
            case ERROR:
                this.zzccS = wn.ERROR;
                break;
            case NONE:
                this.zzccS = wn.NONE;
                break;
            default:
                String valueOf = String.valueOf(level);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 19).append("Unknown log level: ").append(valueOf).toString());
        }
    }

    public final synchronized void setPersistenceCacheSizeBytes(long j) {
        zzGP();
        if (j < 1048576) {
            throw new DatabaseException("The minimum cache size must be at least 1MB");
        } else if (j > 104857600) {
            throw new DatabaseException("Firebase Database currently doesn't support a cache size larger than 100MB");
        } else {
            this.cacheSize = j;
        }
    }

    public final synchronized void setPersistenceEnabled(boolean z) {
        zzGP();
        this.zzcaE = z;
    }

    public final synchronized void zzd(FirebaseApp firebaseApp) {
        this.zzbZt = firebaseApp;
    }

    public final synchronized void zzgR(String str) {
        zzGP();
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Session identifier is not allowed to be empty or null!");
        }
        this.zzccR = str;
    }
}
