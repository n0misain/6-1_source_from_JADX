package com.google.android.gms.internal;

import com.google.android.gms.ads.reward.RewardItem;

@zzzn
public final class zzadg implements RewardItem {
    private final zzacv zzWw;

    public zzadg(zzacv zzacv) {
        this.zzWw = zzacv;
    }

    public final int getAmount() {
        int i = 0;
        if (this.zzWw != null) {
            try {
                i = this.zzWw.getAmount();
            } catch (Throwable e) {
                zzajc.zzc("Could not forward getAmount to RewardItem", e);
            }
        }
        return i;
    }

    public final String getType() {
        String str = null;
        if (this.zzWw != null) {
            try {
                str = this.zzWw.getType();
            } catch (Throwable e) {
                zzajc.zzc("Could not forward getType to RewardItem", e);
            }
        }
        return str;
    }
}
