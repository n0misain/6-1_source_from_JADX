package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class lb extends zza {
    public static final Creator<lb> CREATOR = new lc();
    private List<kz> zzbXt;

    public lb() {
        this.zzbXt = new ArrayList();
    }

    lb(List<kz> list) {
        if (list == null || list.isEmpty()) {
            this.zzbXt = Collections.emptyList();
        } else {
            this.zzbXt = Collections.unmodifiableList(list);
        }
    }

    public static lb zza(lb lbVar) {
        Collection collection = lbVar.zzbXt;
        lb lbVar2 = new lb();
        if (collection != null) {
            lbVar2.zzbXt.addAll(collection);
        }
        return lbVar2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzd.zze(parcel);
        zzd.zzc(parcel, 2, this.zzbXt, false);
        zzd.zzI(parcel, zze);
    }

    public final List<kz> zzER() {
        return this.zzbXt;
    }
}
