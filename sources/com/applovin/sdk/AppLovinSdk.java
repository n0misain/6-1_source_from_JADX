package com.applovin.sdk;

import android.content.Context;
import android.util.Log;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.nativeAds.AppLovinNativeAdService;

public abstract class AppLovinSdk {
    public static final String CIS_BUILD_TAG = "{BUILD_NUMBER}";
    public static final String URI_HOST = "com.applovin.sdk";
    public static final String URI_SCHEME = "applovin";
    public static final String VERSION = "7.0.0";
    public static final int VERSION_CODE = 700;
    /* renamed from: a */
    private static AppLovinSdk[] f402a = new AppLovinSdk[0];
    /* renamed from: b */
    private static final Object f403b = new Object();

    public static AppLovinSdk getInstance(Context context) {
        if (context != null) {
            return getInstance(AppLovinSdkUtils.retrieveSdkKey(context), AppLovinSdkUtils.retrieveUserSettings(context), context);
        }
        throw new IllegalArgumentException("No context specified");
    }

    public static AppLovinSdk getInstance(AppLovinSdkSettings appLovinSdkSettings, Context context) {
        if (context != null) {
            return getInstance(AppLovinSdkUtils.retrieveSdkKey(context), appLovinSdkSettings, context);
        }
        throw new IllegalArgumentException("No context specified");
    }

    public static AppLovinSdk getInstance(String str, AppLovinSdkSettings appLovinSdkSettings, Context context) {
        if (appLovinSdkSettings == null) {
            throw new IllegalArgumentException("No userSettings specified");
        } else if (context == null) {
            throw new IllegalArgumentException("No context specified");
        } else {
            AppLovinSdk appLovinSdk;
            synchronized (f403b) {
                if (f402a.length == 1 && f402a[0].getSdkKey().equals(str)) {
                    appLovinSdk = f402a[0];
                } else {
                    for (AppLovinSdk appLovinSdk2 : f402a) {
                        if (appLovinSdk2.getSdkKey().equals(str)) {
                            break;
                        }
                    }
                    try {
                        appLovinSdk2 = new AppLovinSdkImpl();
                        appLovinSdk2.initialize(str, appLovinSdkSettings, context);
                        appLovinSdk2.setInitializedInMainActivity(appLovinSdk2.checkCorrectInitialization(context));
                        Object obj = new AppLovinSdk[(f402a.length + 1)];
                        System.arraycopy(f402a, 0, obj, 0, f402a.length);
                        obj[f402a.length] = appLovinSdk2;
                        f402a = obj;
                    } catch (Throwable th) {
                        Log.e(AppLovinLogger.SDK_TAG, "Failed to build AppLovin SDK. Try cleaning application data and starting the application again.", th);
                        RuntimeException runtimeException = new RuntimeException("Unable to build AppLovin SDK");
                    }
                }
            }
            return appLovinSdk2;
        }
    }

    public static void initializeSdk(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        AppLovinSdk instance = getInstance(context);
        if (instance != null) {
            instance.initializeSdk();
        } else {
            Log.e(AppLovinLogger.SDK_TAG, "Unable to initialize AppLovin SDK: SDK object not created");
        }
    }

    public abstract AppLovinAdService getAdService();

    public abstract Context getApplicationContext();

    public abstract AppLovinEventService getEventService();

    public abstract AppLovinLogger getLogger();

    public abstract AppLovinMediationService getMediationService();

    public abstract AppLovinNativeAdService getNativeAdService();

    public abstract AppLovinPostbackService getPostbackService();

    public abstract String getSdkKey();

    public abstract AppLovinSdkSettings getSettings();

    public abstract AppLovinTargetingData getTargetingData();

    public abstract String getUserIdentifier();

    public abstract boolean hasCriticalErrors();

    public abstract void initialize(String str, AppLovinSdkSettings appLovinSdkSettings, Context context);

    public abstract void initializeSdk();

    public abstract boolean isEnabled();

    public abstract void setPluginVersion(String str);

    public abstract void setUserIdentifier(String str);
}
