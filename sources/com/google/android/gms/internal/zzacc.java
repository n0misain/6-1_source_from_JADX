package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.common.util.zzj;
import java.util.Locale;

public final class zzacc {
    private int zzSI;
    private int zzSJ;
    private int zzVF;
    private boolean zzVG;
    private boolean zzVH;
    private String zzVI;
    private String zzVJ;
    private boolean zzVK;
    private boolean zzVL;
    private boolean zzVM;
    private boolean zzVN;
    private String zzVO;
    private String zzVP;
    private String zzVQ;
    private int zzVR;
    private int zzVS;
    private int zzVT;
    private int zzVU;
    private int zzVV;
    private int zzVW;
    private double zzVX;
    private boolean zzVY;
    private boolean zzVZ;
    private int zzWa;
    private String zzWb;
    private String zzWc;
    private boolean zzWd;
    private float zzxR;

    public zzacc(Context context) {
        boolean z = true;
        PackageManager packageManager = context.getPackageManager();
        zzk(context);
        zzl(context);
        zzm(context);
        Locale locale = Locale.getDefault();
        this.zzVG = zza(packageManager, "geo:0,0?q=donuts") != null;
        if (zza(packageManager, "http://www.google.com") == null) {
            z = false;
        }
        this.zzVH = z;
        this.zzVJ = locale.getCountry();
        zzji.zzds();
        this.zzVK = zzaiy.zzik();
        this.zzVL = zzj.zzaI(context);
        this.zzVO = locale.getLanguage();
        this.zzVP = zzb(context, packageManager);
        this.zzVQ = zza(context, packageManager);
        Resources resources = context.getResources();
        if (resources != null) {
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            if (displayMetrics != null) {
                this.zzxR = displayMetrics.density;
                this.zzSI = displayMetrics.widthPixels;
                this.zzSJ = displayMetrics.heightPixels;
            }
        }
    }

    public zzacc(Context context, zzacb zzacb) {
        context.getPackageManager();
        zzk(context);
        zzl(context);
        zzm(context);
        this.zzWb = Build.FINGERPRINT;
        this.zzWc = Build.DEVICE;
        this.zzWd = zznl.zzi(context);
        this.zzVG = zzacb.zzVG;
        this.zzVH = zzacb.zzVH;
        this.zzVJ = zzacb.zzVJ;
        this.zzVK = zzacb.zzVK;
        this.zzVL = zzacb.zzVL;
        this.zzVO = zzacb.zzVO;
        this.zzVP = zzacb.zzVP;
        this.zzVQ = zzacb.zzVQ;
        this.zzxR = zzacb.zzxR;
        this.zzSI = zzacb.zzSI;
        this.zzSJ = zzacb.zzSJ;
    }

    private static ResolveInfo zza(PackageManager packageManager, String str) {
        return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
    }

    private static String zza(Context context, PackageManager packageManager) {
        String str = null;
        try {
            PackageInfo packageInfo = zzbha.zzaP(context).getPackageInfo("com.android.vending", 128);
            if (packageInfo != null) {
                int i = packageInfo.versionCode;
                String valueOf = String.valueOf(packageInfo.packageName);
                str = new StringBuilder(String.valueOf(valueOf).length() + 12).append(i).append(".").append(valueOf).toString();
            }
        } catch (Exception e) {
        }
        return str;
    }

    private static String zzb(Context context, PackageManager packageManager) {
        String str = null;
        ResolveInfo zza = zza(packageManager, "market://details?id=com.google.android.gms.ads");
        if (zza != null) {
            ActivityInfo activityInfo = zza.activityInfo;
            if (activityInfo != null) {
                try {
                    PackageInfo packageInfo = zzbha.zzaP(context).getPackageInfo(activityInfo.packageName, 0);
                    if (packageInfo != null) {
                        int i = packageInfo.versionCode;
                        String valueOf = String.valueOf(activityInfo.packageName);
                        str = new StringBuilder(String.valueOf(valueOf).length() + 12).append(i).append(".").append(valueOf).toString();
                    }
                } catch (NameNotFoundException e) {
                }
            }
        }
        return str;
    }

    private final void zzk(Context context) {
        zzbs.zzbz();
        AudioManager zzL = zzagz.zzL(context);
        if (zzL != null) {
            try {
                this.zzVF = zzL.getMode();
                this.zzVM = zzL.isMusicActive();
                this.zzVN = zzL.isSpeakerphoneOn();
                this.zzVR = zzL.getStreamVolume(3);
                this.zzVV = zzL.getRingerMode();
                this.zzVW = zzL.getStreamVolume(2);
                return;
            } catch (Throwable th) {
                zzbs.zzbD().zza(th, "DeviceInfo.gatherAudioInfo");
            }
        }
        this.zzVF = -2;
        this.zzVM = false;
        this.zzVN = false;
        this.zzVR = 0;
        this.zzVV = 0;
        this.zzVW = 0;
    }

    @TargetApi(16)
    private final void zzl(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.zzVI = telephonyManager.getNetworkOperator();
        this.zzVT = telephonyManager.getNetworkType();
        this.zzVU = telephonyManager.getPhoneType();
        this.zzVS = -2;
        this.zzVZ = false;
        this.zzWa = -1;
        zzbs.zzbz();
        if (zzagz.zzc(context, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                this.zzVS = activeNetworkInfo.getType();
                this.zzWa = activeNetworkInfo.getDetailedState().ordinal();
            } else {
                this.zzVS = -1;
            }
            if (VERSION.SDK_INT >= 16) {
                this.zzVZ = connectivityManager.isActiveNetworkMetered();
            }
        }
    }

    private final void zzm(Context context) {
        boolean z = false;
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("status", -1);
            this.zzVX = (double) (((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1)));
            if (intExtra == 2 || intExtra == 5) {
                z = true;
            }
            this.zzVY = z;
            return;
        }
        this.zzVX = -1.0d;
        this.zzVY = false;
    }

    public final zzacb zzgM() {
        return new zzacb(this.zzVF, this.zzVG, this.zzVH, this.zzVI, this.zzVJ, this.zzVK, this.zzVL, this.zzVM, this.zzVN, this.zzVO, this.zzVP, this.zzVQ, this.zzVR, this.zzVS, this.zzVT, this.zzVU, this.zzVV, this.zzVW, this.zzxR, this.zzSI, this.zzSJ, this.zzVX, this.zzVY, this.zzVZ, this.zzWa, this.zzWb, this.zzWd, this.zzWc);
    }
}
