package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.data.DataHolder.zza;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzd<T extends SafeParcelable> extends AbstractDataBuffer<T> {
    private static final String[] zzaFz = new String[]{ShareConstants.WEB_DIALOG_PARAM_DATA};
    private final Creator<T> zzaFA;

    public zzd(DataHolder dataHolder, Creator<T> creator) {
        super(dataHolder);
        this.zzaFA = creator;
    }

    public static <T extends SafeParcelable> void zza(zza zza, T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ShareConstants.WEB_DIALOG_PARAM_DATA, obtain.marshall());
        zza.zza(contentValues);
        obtain.recycle();
    }

    public static zza zzqQ() {
        return DataHolder.zza(zzaFz);
    }

    public /* synthetic */ Object get(int i) {
        return zzas(i);
    }

    public T zzas(int i) {
        byte[] zzg = this.zzaCX.zzg(ShareConstants.WEB_DIALOG_PARAM_DATA, i, this.zzaCX.zzat(i));
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        SafeParcelable safeParcelable = (SafeParcelable) this.zzaFA.createFromParcel(obtain);
        obtain.recycle();
        return safeParcelable;
    }
}
