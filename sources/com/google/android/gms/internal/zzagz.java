package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.js.zzl;
import com.google.android.gms.ads.internal.zzax;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.common.util.zzn;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzagz {
    public static final Handler zzZr = new zzafs(Looper.getMainLooper());
    private static AtomicReference<List<String>> zzZs = new AtomicReference(null);
    private static AtomicReference<List<String>> zzZt = new AtomicReference(null);
    private final Object mLock = new Object();
    private String zzJP;
    private zzl zzLG;
    private boolean zzZu = true;
    private boolean zzZv = false;

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            zzZr.post(runnable);
        }
    }

    public static boolean zzD(Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            zzajc.zzaT("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
            return false;
        }
        boolean z;
        String str = "com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".";
        if ((resolveActivity.activityInfo.configChanges & 16) == 0) {
            zzajc.zzaT(String.format(str, new Object[]{"keyboard"}));
            z = false;
        } else {
            z = true;
        }
        if ((resolveActivity.activityInfo.configChanges & 32) == 0) {
            zzajc.zzaT(String.format(str, new Object[]{"keyboardHidden"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 128) == 0) {
            zzajc.zzaT(String.format(str, new Object[]{"orientation"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 256) == 0) {
            zzajc.zzaT(String.format(str, new Object[]{"screenLayout"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 512) == 0) {
            zzajc.zzaT(String.format(str, new Object[]{"uiMode"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 1024) == 0) {
            zzajc.zzaT(String.format(str, new Object[]{"screenSize"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 2048) != 0) {
            return z;
        }
        zzajc.zzaT(String.format(str, new Object[]{"smallestScreenSize"}));
        return false;
    }

    protected static String zzF(Context context) {
        try {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Exception e) {
            return zzhN();
        }
    }

    public static Builder zzG(Context context) {
        return new Builder(context);
    }

    public static zzlz zzH(Context context) {
        return new zzlz(context);
    }

    private static String zzI(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return null;
            }
            List runningTasks = activityManager.getRunningTasks(1);
            if (!(runningTasks == null || runningTasks.isEmpty())) {
                RunningTaskInfo runningTaskInfo = (RunningTaskInfo) runningTasks.get(0);
                if (!(runningTaskInfo == null || runningTaskInfo.topActivity == null)) {
                    return runningTaskInfo.topActivity.getClassName();
                }
            }
            return null;
        } catch (Exception e) {
        }
    }

    public static boolean zzJ(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null) {
                return false;
            }
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode()) {
                        PowerManager powerManager = (PowerManager) context.getSystemService("power");
                        if (powerManager == null ? false : powerManager.isScreenOn()) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public static Bitmap zzK(Context context) {
        if (!(context instanceof Activity)) {
            return null;
        }
        Bitmap zzo;
        try {
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzFi)).booleanValue()) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    zzo = zzo(window.getDecorView().getRootView());
                }
                zzo = null;
            } else {
                zzo = zzn(((Activity) context).getWindow().getDecorView());
            }
        } catch (Throwable e) {
            zzajc.zzb("Fail to capture screen shot", e);
        }
        return zzo;
    }

    public static AudioManager zzL(Context context) {
        return (AudioManager) context.getSystemService("audio");
    }

    public static float zzM(Context context) {
        AudioManager zzL = zzL(context);
        if (zzL == null) {
            return 0.0f;
        }
        int streamMaxVolume = zzL.getStreamMaxVolume(3);
        return streamMaxVolume != 0 ? ((float) zzL.getStreamVolume(3)) / ((float) streamMaxVolume) : 0.0f;
    }

    public static int zzN(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return applicationInfo == null ? 0 : applicationInfo.targetSdkVersion;
    }

    public static boolean zzO(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        } catch (ClassNotFoundException e) {
            return true;
        } catch (Throwable th) {
            zzajc.zzb("Error loading class.", th);
            zzbs.zzbD().zza(th, "AdUtil.isLiteSdk");
            return false;
        }
    }

    public static int zzP(Context context) {
        return DynamiteModule.zzF(context, ModuleDescriptor.MODULE_ID);
    }

    public static int zzQ(Context context) {
        return DynamiteModule.zzE(context, ModuleDescriptor.MODULE_ID);
    }

    public static Bundle zza(zzgz zzgz) {
        if (zzgz == null) {
            return null;
        }
        if (!((Boolean) zzbs.zzbL().zzd(zzmo.zzCZ)).booleanValue()) {
            if (!((Boolean) zzbs.zzbL().zzd(zzmo.zzDb)).booleanValue()) {
                return null;
            }
        }
        if (zzbs.zzbD().zzhn() && zzbs.zzbD().zzho()) {
            return null;
        }
        String zzcD;
        String zzcE;
        String str;
        if (zzgz.zzcQ()) {
            zzgz.wakeup();
        }
        zzgt zzcO = zzgz.zzcO();
        if (zzcO != null) {
            zzcD = zzcO.zzcD();
            zzcE = zzcO.zzcE();
            String zzcF = zzcO.zzcF();
            if (zzcD != null) {
                zzbs.zzbD().zzaF(zzcD);
            }
            if (zzcF != null) {
                zzbs.zzbD().zzaG(zzcF);
                str = zzcD;
                zzcD = zzcE;
                zzcE = zzcF;
            } else {
                str = zzcD;
                zzcD = zzcE;
                zzcE = zzcF;
            }
        } else {
            zzcD = null;
            str = zzbs.zzbD().zzhu();
            zzcE = zzbs.zzbD().zzhv();
        }
        Bundle bundle = new Bundle(1);
        if (zzcE != null) {
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzDb)).booleanValue() && !zzbs.zzbD().zzho()) {
                bundle.putString("v_fp_vertical", zzcE);
            }
        }
        if (str != null) {
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzCZ)).booleanValue() && !zzbs.zzbD().zzhn()) {
                bundle.putString("fingerprint", str);
                if (!str.equals(zzcD)) {
                    bundle.putString("v_fp", zzcD);
                }
            }
        }
        return !bundle.isEmpty() ? bundle : null;
    }

    public static DisplayMetrics zza(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static PopupWindow zza(View view, int i, int i2, boolean z) {
        return new PopupWindow(view, i, i2, false);
    }

    public static String zza(Context context, View view, zziv zziv) {
        if (!((Boolean) zzbs.zzbL().zzd(zzmo.zzDr)).booleanValue()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(SettingsJsonConstants.ICON_WIDTH_KEY, zziv.width);
            jSONObject2.put(SettingsJsonConstants.ICON_HEIGHT_KEY, zziv.height);
            jSONObject.put("size", jSONObject2);
            jSONObject.put("activity", zzI(context));
            if (!zziv.zzAt) {
                JSONArray jSONArray = new JSONArray();
                while (view != null) {
                    ViewParent parent = view.getParent();
                    if (parent != null) {
                        int i = -1;
                        if (parent instanceof ViewGroup) {
                            i = ((ViewGroup) parent).indexOfChild(view);
                        }
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("type", parent.getClass().getName());
                        jSONObject3.put("index_of_child", i);
                        jSONArray.put(jSONObject3);
                    }
                    view = (parent == null || !(parent instanceof View)) ? null : (View) parent;
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("parents", jSONArray);
                }
            }
            return jSONObject.toString();
        } catch (Throwable e) {
            zzajc.zzc("Fail to get view hierarchy json", e);
            return null;
        }
    }

    private static String zza(Context context, zzcu zzcu, String str, View view) {
        if (zzcu != null) {
            try {
                Uri parse = Uri.parse(str);
                if (zzcu.zzd(parse)) {
                    parse = zzcu.zza(parse, context, view);
                }
                str = parse.toString();
            } catch (Exception e) {
            }
        }
        return str;
    }

    public static String zza(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(8192);
        char[] cArr = new char[2048];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                return stringBuilder.toString();
            }
            stringBuilder.append(cArr, 0, read);
        }
    }

    private final JSONArray zza(Collection<?> collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object zza : collection) {
            zza(jSONArray, zza);
        }
        return jSONArray;
    }

    public static void zza(Activity activity, OnGlobalLayoutListener onGlobalLayoutListener) {
        Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static void zza(Activity activity, OnScrollChangedListener onScrollChangedListener) {
        Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().addOnScrollChangedListener(onScrollChangedListener);
        }
    }

    @TargetApi(18)
    public static void zza(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzGo)).booleanValue()) {
                zzc(context, intent);
            }
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            String valueOf = String.valueOf(uri.toString());
            zzajc.zzaC(new StringBuilder(String.valueOf(valueOf).length() + 26).append("Opening ").append(valueOf).append(" in a new browser.").toString());
        } catch (Throwable e) {
            zzajc.zzb("No browser is found.", e);
        }
    }

    public static void zza(Context context, String str, List<String> list) {
        for (String zzaiq : list) {
            new zzaiq(context, str, zzaiq).zzgp();
        }
    }

    public static void zza(List<String> list, String str) {
        for (String zzaiq : list) {
            new zzaiq(zzaiq, str).zzgp();
        }
    }

    private final void zza(JSONArray jSONArray, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONArray.put(zzg((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONArray.put(zzj((Map) obj));
        } else if (obj instanceof Collection) {
            jSONArray.put(zza((Collection) obj));
        } else if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            JSONArray jSONArray2 = new JSONArray();
            for (Object zza : objArr) {
                zza(jSONArray2, zza);
            }
            jSONArray.put(jSONArray2);
        } else {
            jSONArray.put(obj);
        }
    }

    private final void zza(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONObject.put(str, zzg((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONObject.put(str, zzj((Map) obj));
        } else if (obj instanceof Collection) {
            if (str == null) {
                str = "null";
            }
            jSONObject.put(str, zza((Collection) obj));
        } else if (obj instanceof Object[]) {
            jSONObject.put(str, zza(Arrays.asList((Object[]) obj)));
        } else {
            jSONObject.put(str, obj);
        }
    }

    @TargetApi(24)
    public static boolean zza(Activity activity, Configuration configuration) {
        zzji.zzds();
        int zzc = zzaiy.zzc(activity, configuration.screenHeightDp);
        int zzc2 = zzaiy.zzc(activity, configuration.screenWidthDp);
        DisplayMetrics zza = zza((WindowManager) activity.getApplicationContext().getSystemService("window"));
        int i = zza.heightPixels;
        int i2 = zza.widthPixels;
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
        identifier = ((Integer) zzbs.zzbL().zzd(zzmo.zzGy)).intValue() * ((int) Math.round(((double) activity.getResources().getDisplayMetrics().density) + 0.5d));
        return zzb(i, dimensionPixelSize + zzc, identifier) && zzb(i2, zzc2, identifier);
    }

    public static boolean zza(ClassLoader classLoader, Class<?> cls, String str) {
        boolean z = false;
        try {
            z = cls.isAssignableFrom(Class.forName(str, false, classLoader));
        } catch (Throwable th) {
        }
        return z;
    }

    public static String zzaI(String str) {
        return Uri.parse(str).buildUpon().query(null).build().toString();
    }

    public static int zzaJ(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            String valueOf = String.valueOf(e);
            zzajc.zzaT(new StringBuilder(String.valueOf(valueOf).length() + 22).append("Could not parse value:").append(valueOf).toString());
            return 0;
        }
    }

    public static boolean zzaK(String str) {
        return TextUtils.isEmpty(str) ? false : str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public static boolean zzaL(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (zzZs.get() == null) {
            try {
                JSONArray jSONArray = new JSONArray((String) zzbs.zzbL().zzd(zzmo.zzDw));
                List arrayList = new ArrayList(jSONArray.length());
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
                zzZs.compareAndSet(null, arrayList);
            } catch (JSONException e) {
                zzajc.zzaT("Could not parse click ping schema");
                return false;
            }
        }
        for (String contains : (List) zzZs.get()) {
            if (str.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    public static boolean zzaM(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (zzZt.get() == null) {
            try {
                JSONArray jSONArray = new JSONArray((String) zzbs.zzbL().zzd(zzmo.zzDx));
                List arrayList = new ArrayList(jSONArray.length());
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
                zzZt.compareAndSet(null, arrayList);
            } catch (JSONException e) {
                zzajc.zzaT("Could not parse impression ping schema");
                return false;
            }
        }
        for (String contains : (List) zzZt.get()) {
            if (str.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    public static Uri zzb(String str, String str2, String str3) throws UnsupportedOperationException {
        int indexOf = str.indexOf("&adurl");
        if (indexOf == -1) {
            indexOf = str.indexOf("?adurl");
        }
        return indexOf != -1 ? Uri.parse(new StringBuilder(str.substring(0, indexOf + 1)).append(str2).append("=").append(str3).append("&").append(str.substring(indexOf + 1)).toString()) : Uri.parse(str).buildUpon().appendQueryParameter(str2, str3).build();
    }

    public static String zzb(zzaka zzaka, String str) {
        return zza(zzaka.getContext(), zzaka.zziy(), str, zzaka.getView());
    }

    public static void zzb(Activity activity, OnScrollChangedListener onScrollChangedListener) {
        Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().removeOnScrollChangedListener(onScrollChangedListener);
        }
    }

    public static void zzb(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable th) {
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }

    public static void zzb(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
        } else {
            zzagt.zza(runnable);
        }
    }

    private static boolean zzb(int i, int i2, int i3) {
        return Math.abs(i - i2) <= i3;
    }

    public static float zzbf() {
        zzbs.zzbT();
        zzax zzbe = zzax.zzbe();
        return (zzbe == null || !zzbe.zzbg()) ? 1.0f : zzbe.zzbf();
    }

    public static boolean zzbh() {
        zzbs.zzbT();
        zzax zzbe = zzax.zzbe();
        return zzbe != null ? zzbe.zzbh() : false;
    }

    @TargetApi(18)
    public static void zzc(Context context, Intent intent) {
        if (intent != null && zzq.zzsb()) {
            Bundle extras = intent.getExtras() != null ? intent.getExtras() : new Bundle();
            extras.putBinder(CustomTabsIntent.EXTRA_SESSION, null);
            extras.putString("com.android.browser.application_id", context.getPackageName());
            intent.putExtras(extras);
        }
    }

    public static boolean zzc(Context context, String str, String str2) {
        return zzbha.zzaP(context).checkPermission(str2, str) == 0;
    }

    public static void zzd(Context context, String str, String str2) {
        List arrayList = new ArrayList();
        arrayList.add(str2);
        zza(context, str, arrayList);
    }

    public static void zze(Context context, String str, String str2) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes(HttpRequest.CHARSET_UTF8));
            openFileOutput.close();
        } catch (Throwable e) {
            zzajc.zzb("Error writing to file in internal storage.", e);
        }
    }

    public static int[] zzf(Activity activity) {
        Window window = activity.getWindow();
        if (window == null || window.findViewById(16908290) == null) {
            return zzhR();
        }
        return new int[]{window.findViewById(16908290).getWidth(), window.findViewById(16908290).getHeight()};
    }

    public static Map<String, String> zzg(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str : zzbs.zzbB().zzh(uri)) {
            hashMap.put(str, uri.getQueryParameter(str));
        }
        return hashMap;
    }

    private final JSONObject zzg(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            zza(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    private static String zzhN() {
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("Mozilla/5.0 (Linux; U; Android");
        if (VERSION.RELEASE != null) {
            stringBuffer.append(" ").append(VERSION.RELEASE);
        }
        stringBuffer.append("; ").append(Locale.getDefault());
        if (Build.DEVICE != null) {
            stringBuffer.append("; ").append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                stringBuffer.append(" Build/").append(Build.DISPLAY);
            }
        }
        stringBuffer.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return stringBuffer.toString();
    }

    public static String zzhO() {
        return UUID.randomUUID().toString();
    }

    public static String zzhP() {
        UUID randomUUID = UUID.randomUUID();
        byte[] toByteArray = BigInteger.valueOf(randomUUID.getLeastSignificantBits()).toByteArray();
        byte[] toByteArray2 = BigInteger.valueOf(randomUUID.getMostSignificantBits()).toByteArray();
        String bigInteger = new BigInteger(1, toByteArray).toString();
        for (int i = 0; i < 2; i++) {
            try {
                MessageDigest instance = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE);
                instance.update(toByteArray);
                instance.update(toByteArray2);
                Object obj = new byte[8];
                System.arraycopy(instance.digest(), 0, obj, 0, 8);
                bigInteger = new BigInteger(1, obj).toString();
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return bigInteger;
    }

    public static String zzhQ() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        return str2.startsWith(str) ? str2 : new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(str2).length()).append(str).append(" ").append(str2).toString();
    }

    private static int[] zzhR() {
        return new int[]{0, 0};
    }

    public static Bundle zzhS() {
        Bundle bundle = new Bundle();
        try {
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzCC)).booleanValue()) {
                Parcelable memoryInfo = new MemoryInfo();
                Debug.getMemoryInfo(memoryInfo);
                bundle.putParcelable("debug_memory_info", memoryInfo);
            }
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzCD)).booleanValue()) {
                Runtime runtime = Runtime.getRuntime();
                bundle.putLong("runtime_free_memory", runtime.freeMemory());
                bundle.putLong("runtime_max_memory", runtime.maxMemory());
                bundle.putLong("runtime_total_memory", runtime.totalMemory());
            }
            bundle.putInt("web_view_count", zzbs.zzbD().zzhJ());
        } catch (Throwable e) {
            zzajc.zzc("Unable to gather memory stats", e);
        }
        return bundle;
    }

    public static Bitmap zzl(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public static Bitmap zzm(View view) {
        if (view == null) {
            return null;
        }
        Bitmap zzo = zzo(view);
        return zzo == null ? zzn(view) : zzo;
    }

    private static Bitmap zzn(@NonNull View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width == 0 || height == 0) {
                zzajc.zzaT("Width or height of view is zero");
                return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            view.layout(0, 0, width, height);
            view.draw(canvas);
            return createBitmap;
        } catch (Throwable e) {
            zzajc.zzb("Fail to capture the webview", e);
            return null;
        }
    }

    private static Bitmap zzo(@NonNull View view) {
        Bitmap drawingCache;
        Throwable e;
        try {
            boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
            view.setDrawingCacheEnabled(true);
            drawingCache = view.getDrawingCache();
            drawingCache = drawingCache != null ? Bitmap.createBitmap(drawingCache) : null;
            try {
                view.setDrawingCacheEnabled(isDrawingCacheEnabled);
            } catch (RuntimeException e2) {
                e = e2;
                zzajc.zzb("Fail to capture the web view", e);
                return drawingCache;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            drawingCache = null;
            e = th;
            zzajc.zzb("Fail to capture the web view", e);
            return drawingCache;
        }
        return drawingCache;
    }

    public static int zzp(@Nullable View view) {
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof AdapterView)) {
            parent = parent.getParent();
        }
        return parent == null ? -1 : ((AdapterView) parent).getPositionForView(view);
    }

    public static String zzt(Context context, String str) {
        try {
            return new String(zzn.zza(context.openFileInput(str), true), HttpRequest.CHARSET_UTF8);
        } catch (IOException e) {
            zzajc.zzaC("Error reading from internal storage.");
            return "";
        }
    }

    public final boolean zzE(Context context) {
        if (this.zzZv) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new zzahd(), intentFilter);
        this.zzZv = true;
        return true;
    }

    public final JSONObject zza(Bundle bundle, JSONObject jSONObject) {
        try {
            return zzg(bundle);
        } catch (Throwable e) {
            zzajc.zzb("Error converting Bundle to JSON", e);
            return null;
        }
    }

    public final void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        if (z) {
            zzbs.zzbz();
            bundle.putString("device", zzhQ());
            bundle.putString("eids", TextUtils.join(",", zzmo.zzdJ()));
        }
        zzji.zzds();
        zzaiy.zza(context, str, str2, bundle, z, new zzahc(this, context, str));
    }

    public final void zza(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(z);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", zzs(context, str));
        httpURLConnection.setUseCaches(false);
    }

    public final void zza(Context context, List<String> list) {
        if (!(context instanceof Activity) || TextUtils.isEmpty(aeo.zzbU((Activity) context))) {
            return;
        }
        if (list == null) {
            zzafr.m1217v("Cannot ping urls: empty list.");
        } else if (zznl.zzi(context)) {
            zznl zznl = new zznl();
            zznl.zza(new zzaha(this, list, zznl, context));
            zznl.zzd((Activity) context);
        } else {
            zzafr.m1217v("Cannot ping url because custom tabs is not supported");
        }
    }

    public final boolean zza(View view, Context context) {
        KeyguardManager keyguardManager = null;
        Context applicationContext = context.getApplicationContext();
        PowerManager powerManager = applicationContext != null ? (PowerManager) applicationContext.getSystemService("power") : null;
        Object systemService = context.getSystemService("keyguard");
        if (systemService != null && (systemService instanceof KeyguardManager)) {
            keyguardManager = (KeyguardManager) systemService;
        }
        return zza(view, powerManager, keyguardManager);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(android.view.View r6, android.os.PowerManager r7, android.app.KeyguardManager r8) {
        /*
        r5 = this;
        r3 = 0;
        r2 = 1;
        r1 = 0;
        r0 = com.google.android.gms.ads.internal.zzbs.zzbz();
        r0 = r0.zzZu;
        if (r0 != 0) goto L_0x0047;
    L_0x000b:
        if (r8 != 0) goto L_0x008a;
    L_0x000d:
        r0 = r1;
    L_0x000e:
        if (r0 == 0) goto L_0x0047;
    L_0x0010:
        r0 = com.google.android.gms.internal.zzmo.zzEs;
        r4 = com.google.android.gms.ads.internal.zzbs.zzbL();
        r0 = r4.zzd(r0);
        r0 = (java.lang.Boolean) r0;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x0098;
    L_0x0022:
        r0 = r6.getRootView();
        if (r0 == 0) goto L_0x008f;
    L_0x0028:
        r0 = r0.getContext();
        r4 = r0 instanceof android.app.Activity;
        if (r4 == 0) goto L_0x008f;
    L_0x0030:
        r0 = (android.app.Activity) r0;
    L_0x0032:
        if (r0 == 0) goto L_0x0096;
    L_0x0034:
        r0 = r0.getWindow();
        if (r0 != 0) goto L_0x0091;
    L_0x003a:
        r0 = r3;
    L_0x003b:
        if (r0 == 0) goto L_0x0096;
    L_0x003d:
        r0 = r0.flags;
        r3 = 524288; // 0x80000 float:7.34684E-40 double:2.590327E-318;
        r0 = r0 & r3;
        if (r0 == 0) goto L_0x0096;
    L_0x0044:
        r0 = r2;
    L_0x0045:
        if (r0 == 0) goto L_0x0098;
    L_0x0047:
        r0 = r2;
    L_0x0048:
        r3 = r6.getVisibility();
        if (r3 != 0) goto L_0x009c;
    L_0x004e:
        r3 = r6.isShown();
        if (r3 == 0) goto L_0x009c;
    L_0x0054:
        if (r7 == 0) goto L_0x005c;
    L_0x0056:
        r3 = r7.isScreenOn();
        if (r3 == 0) goto L_0x009a;
    L_0x005c:
        r3 = r2;
    L_0x005d:
        if (r3 == 0) goto L_0x009c;
    L_0x005f:
        if (r0 == 0) goto L_0x009c;
    L_0x0061:
        r0 = com.google.android.gms.internal.zzmo.zzEq;
        r3 = com.google.android.gms.ads.internal.zzbs.zzbL();
        r0 = r3.zzd(r0);
        r0 = (java.lang.Boolean) r0;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x0089;
    L_0x0073:
        r0 = new android.graphics.Rect;
        r0.<init>();
        r0 = r6.getLocalVisibleRect(r0);
        if (r0 != 0) goto L_0x0089;
    L_0x007e:
        r0 = new android.graphics.Rect;
        r0.<init>();
        r0 = r6.getGlobalVisibleRect(r0);
        if (r0 == 0) goto L_0x009c;
    L_0x0089:
        return r2;
    L_0x008a:
        r0 = r8.inKeyguardRestrictedInputMode();
        goto L_0x000e;
    L_0x008f:
        r0 = r3;
        goto L_0x0032;
    L_0x0091:
        r0 = r0.getAttributes();
        goto L_0x003b;
    L_0x0096:
        r0 = r1;
        goto L_0x0045;
    L_0x0098:
        r0 = r1;
        goto L_0x0048;
    L_0x009a:
        r3 = r1;
        goto L_0x005d;
    L_0x009c:
        r2 = r1;
        goto L_0x0089;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzagz.zza(android.view.View, android.os.PowerManager, android.app.KeyguardManager):boolean");
    }

    public final void zzb(Context context, String str, String str2, Bundle bundle, boolean z) {
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzEv)).booleanValue()) {
            zza(context, str, str2, bundle, z);
        }
    }

    public final zzl zze(Context context, zzaje zzaje) {
        zzl zzl;
        synchronized (this.mLock) {
            if (this.zzLG == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                this.zzLG = new zzl(context, zzaje, (String) zzbs.zzbL().zzd(zzmo.zzBX));
            }
            zzl = this.zzLG;
        }
        return zzl;
    }

    public final int[] zzg(Activity activity) {
        int[] zzf = zzf(activity);
        r1 = new int[2];
        zzji.zzds();
        r1[0] = zzaiy.zzd(activity, zzf[0]);
        zzji.zzds();
        r1[1] = zzaiy.zzd(activity, zzf[1]);
        return r1;
    }

    public final int[] zzh(Activity activity) {
        Window window = activity.getWindow();
        int[] zzhR = (window == null || window.findViewById(16908290) == null) ? zzhR() : new int[]{window.findViewById(16908290).getTop(), window.findViewById(16908290).getBottom()};
        r1 = new int[2];
        zzji.zzds();
        r1[0] = zzaiy.zzd(activity, zzhR[0]);
        zzji.zzds();
        r1[1] = zzaiy.zzd(activity, zzhR[1]);
        return r1;
    }

    public final JSONObject zzj(Map<String, ?> map) throws JSONException {
        String valueOf;
        try {
            JSONObject jSONObject = new JSONObject();
            for (String valueOf2 : map.keySet()) {
                zza(jSONObject, valueOf2, map.get(valueOf2));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            String str = "Could not convert map to JSON: ";
            valueOf2 = String.valueOf(e.getMessage());
            throw new JSONException(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
        }
    }

    public final String zzs(Context context, String str) {
        String str2;
        synchronized (this.mLock) {
            if (this.zzJP != null) {
                str2 = this.zzJP;
            } else if (str == null) {
                str2 = zzhN();
            } else {
                try {
                    this.zzJP = zzbs.zzbB().getDefaultUserAgent(context);
                } catch (Exception e) {
                }
                if (TextUtils.isEmpty(this.zzJP)) {
                    zzji.zzds();
                    if (zzaiy.zzil()) {
                        this.zzJP = zzF(context);
                    } else {
                        this.zzJP = null;
                        zzZr.post(new zzahb(this, context));
                        while (this.zzJP == null) {
                            try {
                                this.mLock.wait();
                            } catch (InterruptedException e2) {
                                this.zzJP = zzhN();
                                String str3 = "Interrupted, use default user agent: ";
                                str2 = String.valueOf(this.zzJP);
                                zzajc.zzaT(str2.length() != 0 ? str3.concat(str2) : new String(str3));
                            }
                        }
                    }
                }
                str2 = String.valueOf(this.zzJP);
                this.zzJP = new StringBuilder((String.valueOf(str2).length() + 11) + String.valueOf(str).length()).append(str2).append(" (Mobile; ").append(str).append(")").toString();
                str2 = this.zzJP;
            }
        }
        return str2;
    }
}
