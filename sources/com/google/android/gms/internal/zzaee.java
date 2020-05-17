package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;

@zzzn
public final class zzaee extends zza {
    public static final Creator<zzaee> CREATOR = new zzaef();
    public final String type;
    public final int zzWW;

    public zzaee(RewardItem rewardItem) {
        this(rewardItem.getType(), rewardItem.getAmount());
    }

    public zzaee(String str, int i) {
        this.type = str;
        this.zzWW = i;
    }

    @Nullable
    public static zzaee zza(JSONArray jSONArray) throws JSONException {
        return (jSONArray == null || jSONArray.length() == 0) ? null : new zzaee(jSONArray.getJSONObject(0).optString("rb_type"), jSONArray.getJSONObject(0).optInt("rb_amount"));
    }

    @Nullable
    public static zzaee zzaz(@Nullable String str) {
        zzaee zzaee = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                zzaee = zza(new JSONArray(str));
            } catch (JSONException e) {
            }
        }
        return zzaee;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzaee)) {
            return false;
        }
        zzaee zzaee = (zzaee) obj;
        return zzbe.equal(this.type, zzaee.type) && zzbe.equal(Integer.valueOf(this.zzWW), Integer.valueOf(zzaee.zzWW));
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.type, Integer.valueOf(this.zzWW)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzd.zze(parcel);
        zzd.zza(parcel, 2, this.type, false);
        zzd.zzc(parcel, 3, this.zzWW);
        zzd.zzI(parcel, zze);
    }
}
