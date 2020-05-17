package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzal;

@zzzn
final class zzti {
    @Nullable
    zzke zzKi;
    @Nullable
    zznh zzKj;
    @Nullable
    zzjl zzKk;
    @Nullable
    zzadd zzKl;
    @Nullable
    zzjo zztK;

    zzti() {
    }

    final void zzd(zzal zzal) {
        if (this.zztK != null) {
            zzal.zza(new zztj(this.zztK));
        }
        if (this.zzKi != null) {
            zzal.zza(this.zzKi);
        }
        if (this.zzKj != null) {
            zzal.zza(this.zzKj);
        }
        if (this.zzKk != null) {
            zzal.zza(this.zzKk);
        }
        if (this.zzKl != null) {
            zzal.zza(this.zzKl);
        }
    }
}
