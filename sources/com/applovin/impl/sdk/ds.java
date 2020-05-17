package com.applovin.impl.sdk;

import android.net.Uri;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;

abstract class ds extends di {
    /* renamed from: a */
    private final ae f811a;
    /* renamed from: b */
    private AppLovinAdLoadListener f812b;
    /* renamed from: g */
    private final al f813g;
    /* renamed from: h */
    private final Collection f814h;

    ds(String str, ae aeVar, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super(str, appLovinSdkImpl);
        if (aeVar == null) {
            throw new IllegalArgumentException("Unable to create TaskCacheAd. No ad specified.");
        }
        this.f811a = aeVar;
        this.f812b = appLovinAdLoadListener;
        this.f813g = appLovinSdkImpl.getFileManager();
        this.f814h = m901e();
    }

    /* renamed from: a */
    private Uri m898a(Uri uri, String str) {
        if (uri != null) {
            String uri2 = uri.toString();
            if (AppLovinSdkUtils.isValidString(uri2)) {
                this.e.mo2288d(this.c, "Caching " + str + " image...");
                return m904b(uri2);
            }
            this.e.mo2288d(this.c, "Failed to cache " + str + " image");
        } else {
            this.e.mo2288d(this.c, "No " + str + " image to cache");
        }
        return null;
    }

    /* renamed from: a */
    private String m899a(String str, String str2) {
        String replace = str2.replace("/", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
        String H = this.f811a.m131H();
        if (AppLovinSdkUtils.isValidString(H)) {
            replace = H + replace;
        }
        File a = this.f813g.m518a(replace, this.d.getApplicationContext(), true);
        if (a == null) {
            return null;
        }
        if (a.exists()) {
            this.e.mo2288d(this.c, "Loaded " + replace + " from cache: file://" + a.getAbsolutePath());
            return "file://" + a.getAbsolutePath();
        }
        return this.f813g.m526a(a, new StringBuilder().append(str).append(str2).toString()) ? "file://" + a.getAbsolutePath() : null;
    }

    /* renamed from: d */
    private String m900d(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        for (String str2 : ((String) this.d.get(dj.f754I)).split(",")) {
            int i = 0;
            int i2 = 0;
            while (i2 < stringBuilder.length()) {
                i2 = stringBuilder.indexOf(str2, i);
                if (i2 == -1) {
                    break;
                }
                int length = stringBuilder.length();
                i = i2;
                while (!this.f814h.contains(Character.valueOf(stringBuilder.charAt(i))) && i < length) {
                    i++;
                }
                if (i <= i2 || i == length) {
                    this.e.mo2289e(this.c, "Unable to cache resource; ad HTML is invalid.");
                } else {
                    String a = m899a(str2, stringBuilder.substring(str2.length() + i2, i));
                    if (a != null) {
                        stringBuilder.replace(i2, i, a);
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: e */
    private Collection m901e() {
        Collection hashSet = new HashSet();
        for (char valueOf : ((String) this.d.get(dj.ay)).toCharArray()) {
            hashSet.add(Character.valueOf(valueOf));
        }
        hashSet.add(Character.valueOf('\"'));
        return hashSet;
    }

    /* renamed from: a */
    Uri m902a(String str) {
        return m903a(str, true);
    }

    /* renamed from: a */
    Uri m903a(String str, boolean z) {
        try {
            if (AppLovinSdkUtils.isValidString(str)) {
                this.e.mo2288d(this.c, "Caching video " + str + "...");
                String a = this.f813g.m519a(this.f, str, this.f811a.m131H(), z);
                if (AppLovinSdkUtils.isValidString(a)) {
                    File a2 = this.d.getFileManager().m518a(a, this.f, false);
                    if (a2 != null) {
                        Uri fromFile = Uri.fromFile(a2);
                        if (fromFile != null) {
                            this.e.mo2288d(this.c, "Finish caching video for ad #" + this.f811a.getAdIdNumber() + ". Updating ad with cachedVideoFilename = " + a);
                            return fromFile;
                        }
                        this.e.mo2289e(this.c, "Unable to create URI from cached video file = " + a2);
                    } else {
                        this.e.mo2289e(this.c, "Unable to cache video = " + str + "Video file was missing or null - please make sure your app has the WRITE_EXTERNAL_STORAGE permission!");
                    }
                } else if (((Boolean) this.d.get(dj.f753H)).booleanValue()) {
                    this.e.mo2289e(this.c, "Failed to cache video");
                    fk.m1087a(this.f812b, this.f811a.mo2131m(), AppLovinErrorCodes.UNABLE_TO_PRECACHE_VIDEO_RESOURCES, this.d);
                    this.f812b = null;
                } else {
                    this.e.mo2289e(this.c, "Failed to cache video, but not failing ad load");
                }
            }
        } catch (Throwable e) {
            this.e.mo2290e(this.c, "Encountered exception while attempting to cache video.", e);
        }
        return null;
    }

    /* renamed from: b */
    Uri m904b(String str) {
        return m905b(str, true);
    }

    /* renamed from: b */
    Uri m905b(String str, boolean z) {
        try {
            String a = this.d.getFileManager().m519a(this.f, str, this.f811a.m131H(), z);
            if (AppLovinSdkUtils.isValidString(a)) {
                File a2 = this.d.getFileManager().m518a(a, this.f, false);
                if (a2 != null) {
                    Uri fromFile = Uri.fromFile(a2);
                    if (fromFile != null) {
                        return fromFile;
                    }
                    this.e.mo2289e(this.c, "Unable to extract Uri from image file");
                } else {
                    this.e.mo2289e(this.c, "Unable to retrieve File from cached image filename = " + a);
                }
            }
        } catch (Throwable e) {
            this.e.mo2290e(this.c, "Failed to cache image at url = " + str, e);
        }
        return null;
    }

    /* renamed from: c */
    String m906c(String str) {
        return ((Boolean) this.d.get(dj.f752G)).booleanValue() ? m900d(str) : str;
    }

    /* renamed from: c */
    void m907c() {
        this.e.mo2288d(this.c, "Caching mute images...");
        Uri a = m898a(this.f811a.m135L(), "mute");
        if (a != null) {
            this.f811a.m140b(a);
        }
        a = m898a(this.f811a.m136M(), "unmute");
        if (a != null) {
            this.f811a.m142c(a);
        }
        this.e.mo2288d(this.c, "Ad updated with muteImageFilename = " + this.f811a.m135L() + ", unmuteImageFilename = " + this.f811a.m136M());
    }

    /* renamed from: d */
    void m908d() {
        if (this.f812b != null) {
            this.d.getLogger().mo2288d(m676a(), "Rendered new ad:" + this.f811a);
            this.f812b.adReceived(this.f811a);
            this.f812b = null;
        }
    }
}
