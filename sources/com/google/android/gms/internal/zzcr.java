package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class zzcr extends zzcs {
    private static final String TAG = zzcr.class.getSimpleName();
    private Info zzqi;

    private zzcr(Context context) {
        super(context, "");
    }

    public static String zza(String str, String str2) {
        return zzbv.zza(str, str2, true);
    }

    public static zzcr zzb(Context context) {
        zzcs.zza(context, true);
        return new zzcr(context);
    }

    protected final zzax zza(Context context, View view) {
        return null;
    }

    public final void zza(Info info) {
        this.zzqi = info;
    }

    protected final void zza(zzdb zzdb, zzax zzax, zzau zzau) {
        if (!zzdb.zzqP) {
            zzcs.zza(zzb(zzdb, zzax, zzau));
        } else if (this.zzqi != null) {
            Object id = this.zzqi.getId();
            if (!TextUtils.isEmpty(id)) {
                zzax.zzbU = zzdg.zzn(id);
                zzax.zzbV = Integer.valueOf(5);
                zzax.zzbW = Boolean.valueOf(this.zzqi.isLimitAdTrackingEnabled());
            }
            this.zzqi = null;
        }
    }

    protected final List<Callable<Void>> zzb(zzdb zzdb, zzax zzax, zzau zzau) {
        List<Callable<Void>> arrayList = new ArrayList();
        if (zzdb.zzC() == null) {
            return arrayList;
        }
        zzdb zzdb2 = zzdb;
        arrayList.add(new zzdp(zzdb2, "Ob9vkrYwqwLnJveTtaSWm/WWJCjo/9DRtOCY3btkKa6pJtjMu6sI0iK41HSh10io", "UrT94Dq3ubetC7rQ64nVjqMQ53po9X61geGgrP+ILCU=", zzax, zzdb.zzy(), 24));
        return arrayList;
    }
}
