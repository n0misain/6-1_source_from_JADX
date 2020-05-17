package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.zzb;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@zzzn
public final class zzwb {
    public static int zza(ErrorCode errorCode) {
        switch (zzwc.zzNn[errorCode.ordinal()]) {
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            default:
                return 0;
        }
    }

    public static AdSize zzb(zziv zziv) {
        int i = 0;
        AdSize[] adSizeArr = new AdSize[]{AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
        while (i < 6) {
            if (adSizeArr[i].getWidth() == zziv.width && adSizeArr[i].getHeight() == zziv.height) {
                return adSizeArr[i];
            }
            i++;
        }
        return new AdSize(zzb.zza(zziv.width, zziv.height, zziv.zzAs));
    }

    public static MediationAdRequest zzn(zzir zzir) {
        Gender gender;
        Set hashSet = zzir.zzzP != null ? new HashSet(zzir.zzzP) : null;
        Date date = new Date(zzir.zzzN);
        switch (zzir.zzzO) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            default:
                gender = Gender.UNKNOWN;
                break;
        }
        return new MediationAdRequest(date, gender, hashSet, zzir.zzzQ, zzir.zzzV);
    }
}
