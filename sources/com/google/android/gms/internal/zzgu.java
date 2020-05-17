package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbs;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzzn
public final class zzgu {
    private final Object mLock = new Object();
    private int zzyl;
    private List<zzgt> zzym = new LinkedList();

    public final boolean zza(zzgt zzgt) {
        boolean z;
        synchronized (this.mLock) {
            if (this.zzym.contains(zzgt)) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public final boolean zzb(zzgt zzgt) {
        synchronized (this.mLock) {
            Iterator it = this.zzym.iterator();
            while (it.hasNext()) {
                zzgt zzgt2 = (zzgt) it.next();
                if (!((Boolean) zzbs.zzbL().zzd(zzmo.zzCZ)).booleanValue() || zzbs.zzbD().zzhn()) {
                    if (((Boolean) zzbs.zzbL().zzd(zzmo.zzDb)).booleanValue() && !zzbs.zzbD().zzho() && zzgt != zzgt2 && zzgt2.zzcF().equals(zzgt.zzcF())) {
                        it.remove();
                        return true;
                    }
                } else if (zzgt != zzgt2 && zzgt2.zzcD().equals(zzgt.zzcD())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public final void zzc(zzgt zzgt) {
        synchronized (this.mLock) {
            if (this.zzym.size() >= 10) {
                zzajc.zzaC("Queue is full, current size = " + this.zzym.size());
                this.zzym.remove(0);
            }
            int i = this.zzyl;
            this.zzyl = i + 1;
            zzgt.zzj(i);
            this.zzym.add(zzgt);
        }
    }

    @Nullable
    public final zzgt zzcL() {
        zzgt zzgt = null;
        int i = 0;
        synchronized (this.mLock) {
            if (this.zzym.size() == 0) {
                zzajc.zzaC("Queue empty");
                return null;
            } else if (this.zzym.size() >= 2) {
                int i2 = Integer.MIN_VALUE;
                int i3 = 0;
                for (zzgt zzgt2 : this.zzym) {
                    zzgt zzgt3;
                    int i4;
                    int score = zzgt2.getScore();
                    if (score > i2) {
                        i = score;
                        zzgt3 = zzgt2;
                        i4 = i3;
                    } else {
                        i4 = i;
                        zzgt3 = zzgt;
                        i = i2;
                    }
                    i3++;
                    i2 = i;
                    zzgt = zzgt3;
                    i = i4;
                }
                this.zzym.remove(i);
                return zzgt;
            } else {
                zzgt2 = (zzgt) this.zzym.get(0);
                zzgt2.zzcG();
                return zzgt2;
            }
        }
    }
}
