package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@zzzn
public final class zziu {
    public static final zziu zzAr = new zziu();

    protected zziu() {
    }

    public static zzir zza(Context context, zzla zzla) {
        Date birthday = zzla.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1;
        String contentUrl = zzla.getContentUrl();
        int gender = zzla.getGender();
        Collection keywords = zzla.getKeywords();
        List unmodifiableList = !keywords.isEmpty() ? Collections.unmodifiableList(new ArrayList(keywords)) : null;
        boolean isTestDevice = zzla.isTestDevice(context);
        int zzdB = zzla.zzdB();
        Location location = zzla.getLocation();
        Bundle networkExtrasBundle = zzla.getNetworkExtrasBundle(AdMobAdapter.class);
        boolean manualImpressionsEnabled = zzla.getManualImpressionsEnabled();
        String publisherProvidedId = zzla.getPublisherProvidedId();
        SearchAdRequest zzdy = zzla.zzdy();
        zzlt zzlt = zzdy != null ? new zzlt(zzdy) : null;
        String str = null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            String packageName = applicationContext.getPackageName();
            zzji.zzds();
            str = zzaiy.zza(Thread.currentThread().getStackTrace(), packageName);
        }
        return new zzir(7, time, networkExtrasBundle, gender, unmodifiableList, isTestDevice, zzdB, manualImpressionsEnabled, publisherProvidedId, zzlt, location, contentUrl, zzla.zzdA(), zzla.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(zzla.zzdC())), zzla.zzdx(), str, zzla.isDesignedForFamilies());
    }
}
