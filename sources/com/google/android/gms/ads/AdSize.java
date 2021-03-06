package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.internal.zzaiy;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzji;
import com.twitter.sdk.android.core.TwitterApiErrorConstants;

public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER = new AdSize(320, 50, "320x50_mb");
    public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
    public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
    public static final int FULL_WIDTH = -1;
    public static final AdSize LARGE_BANNER = new AdSize(320, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(TwitterApiErrorConstants.REGISTRATION_INVALID_INPUT, 250, "300x250_as");
    public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
    public static final AdSize zzrV = new AdSize(50, 50, "50x50_mb");
    private final int zzrW;
    private final int zzrX;
    private final String zzrY;

    public AdSize(int i, int i2) {
        String valueOf = i == -1 ? "FULL" : String.valueOf(i);
        String valueOf2 = i2 == -2 ? "AUTO" : String.valueOf(i2);
        String valueOf3 = String.valueOf("_as");
        this(i, i2, new StringBuilder(((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()).append(valueOf).append("x").append(valueOf2).append(valueOf3).toString());
    }

    AdSize(int i, int i2, String str) {
        if (i < 0 && i != -1 && i != -3) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + i);
        } else if (i2 >= 0 || i2 == -2 || i2 == -4) {
            this.zzrW = i;
            this.zzrX = i2;
            this.zzrY = str;
        } else {
            throw new IllegalArgumentException("Invalid height for AdSize: " + i2);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.zzrW == adSize.zzrW && this.zzrX == adSize.zzrX && this.zzrY.equals(adSize.zzrY);
    }

    public final int getHeight() {
        return this.zzrX;
    }

    public final int getHeightInPixels(Context context) {
        switch (this.zzrX) {
            case -4:
            case -3:
                return -1;
            case -2:
                return zziv.zzb(context.getResources().getDisplayMetrics());
            default:
                zzji.zzds();
                return zzaiy.zzc(context, this.zzrX);
        }
    }

    public final int getWidth() {
        return this.zzrW;
    }

    public final int getWidthInPixels(Context context) {
        switch (this.zzrW) {
            case -4:
            case -3:
                return -1;
            case -1:
                return zziv.zza(context.getResources().getDisplayMetrics());
            default:
                zzji.zzds();
                return zzaiy.zzc(context, this.zzrW);
        }
    }

    public final int hashCode() {
        return this.zzrY.hashCode();
    }

    public final boolean isAutoHeight() {
        return this.zzrX == -2;
    }

    public final boolean isFluid() {
        return this.zzrW == -3 && this.zzrX == -4;
    }

    public final boolean isFullWidth() {
        return this.zzrW == -1;
    }

    public final String toString() {
        return this.zzrY;
    }
}
