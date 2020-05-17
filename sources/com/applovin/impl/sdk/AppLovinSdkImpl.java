package com.applovin.impl.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.util.Log;
import com.applovin.nativeAds.AppLovinNativeAdService;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.applovin.sdk.AppLovinTargetingData;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.List;

public class AppLovinSdkImpl extends AppLovinSdk {
    /* renamed from: a */
    private String f404a;
    /* renamed from: b */
    private AppLovinSdkSettings f405b;
    /* renamed from: c */
    private Context f406c;
    /* renamed from: d */
    private WeakReference f407d;
    /* renamed from: e */
    private AppLovinLogger f408e;
    /* renamed from: f */
    private ei f409f;
    /* renamed from: g */
    private dm f410g;
    /* renamed from: h */
    private C0517v f411h;
    /* renamed from: i */
    private C0500do f412i;
    /* renamed from: j */
    private al f413j;
    /* renamed from: k */
    private C0502f f414k;
    /* renamed from: l */
    private co f415l;
    /* renamed from: m */
    private C0519y f416m;
    /* renamed from: n */
    private C0515t f417n;
    /* renamed from: o */
    private AppLovinAdServiceImpl f418o;
    /* renamed from: p */
    private cq f419p;
    /* renamed from: q */
    private PostbackServiceImpl f420q;
    /* renamed from: r */
    private EventServiceImpl f421r;
    /* renamed from: s */
    private MediationServiceImpl f422s;
    /* renamed from: t */
    private cz f423t;
    /* renamed from: u */
    private boolean f424u = true;
    /* renamed from: v */
    private boolean f425v = false;
    /* renamed from: w */
    private boolean f426w = false;
    /* renamed from: x */
    private boolean f427x = false;
    /* renamed from: y */
    private boolean f428y = false;
    /* renamed from: z */
    private boolean f429z = false;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    private void m464a(android.content.Context r7) {
        /*
        r6 = this;
        r5 = 700; // 0x2bc float:9.81E-43 double:3.46E-321;
        r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r7);
        r0 = "com.applovin.sdk.impl.lastKnownVersionCode";
        r2 = 0;
        r0 = r1.getInt(r0, r2);	 Catch:{ Exception -> 0x003a }
        if (r0 >= r5) goto L_0x0032;
    L_0x000f:
        r0 = "AppLovinSdkImpl";
        r2 = "SDK has been updated since last run. Continuing...";
        android.util.Log.i(r0, r2);	 Catch:{ Exception -> 0x003a }
        r0 = r6.getSettingsManager();	 Catch:{ Exception -> 0x003a }
        r0.m824d();	 Catch:{ Exception -> 0x003a }
        r0 = r6.getSettingsManager();	 Catch:{ Exception -> 0x003a }
        r0.m822b();	 Catch:{ Exception -> 0x003a }
    L_0x0024:
        r0 = r1.edit();
        r1 = "com.applovin.sdk.impl.lastKnownVersionCode";
        r0 = r0.putInt(r1, r5);
        r0.apply();
    L_0x0031:
        return;
    L_0x0032:
        r0 = "AppLovinSdkImpl";
        r2 = "SDK has not been updated since last run. Continuing...";
        android.util.Log.d(r0, r2);	 Catch:{ Exception -> 0x003a }
        goto L_0x0024;
    L_0x003a:
        r0 = move-exception;
        r2 = r6.getLogger();	 Catch:{ all -> 0x0054 }
        r3 = "AppLovinSdkImpl";
        r4 = "Unable to check for SDK update";
        r2.mo2290e(r3, r4, r0);	 Catch:{ all -> 0x0054 }
        r0 = r1.edit();
        r1 = "com.applovin.sdk.impl.lastKnownVersionCode";
        r0 = r0.putInt(r1, r5);
        r0.apply();
        goto L_0x0031;
    L_0x0054:
        r0 = move-exception;
        r1 = r1.edit();
        r2 = "com.applovin.sdk.impl.lastKnownVersionCode";
        r1 = r1.putInt(r2, r5);
        r1.apply();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.AppLovinSdkImpl.a(android.content.Context):void");
    }

    /* renamed from: i */
    private static boolean m465i() {
        return (VERSION.RELEASE.startsWith("1.") || VERSION.RELEASE.startsWith("2.0") || VERSION.RELEASE.startsWith("2.1")) ? false : true;
    }

    /* renamed from: a */
    ei m466a() {
        return this.f409f;
    }

    /* renamed from: a */
    void m467a(boolean z) {
        this.f424u = false;
        this.f425v = z;
        this.f426w = true;
    }

    /* renamed from: b */
    C0500do m468b() {
        return this.f412i;
    }

    /* renamed from: c */
    C0502f m469c() {
        return this.f414k;
    }

    public boolean checkCorrectInitialization(Context context) {
        try {
            getLogger().mo2288d(AppLovinLogger.SDK_TAG, "Checking if sdk is initialized in main activity...");
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(context.getPackageName());
            String stackTraceString = Log.getStackTraceString(new Throwable());
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (queryIntentActivities != null) {
                getLogger().mo2288d(AppLovinLogger.SDK_TAG, "Found " + queryIntentActivities.size() + " main activities for this application");
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    if (stackTraceString.contains(resolveInfo.activityInfo.name)) {
                        return true;
                    }
                }
            }
            getLogger().mo2294w(AppLovinLogger.SDK_TAG, "AppLovin SDK was initialized too late in session; SDK should always be initialized within main activity and/or any relevant entry points");
            getLogger().mo2294w(AppLovinLogger.SDK_TAG, "Initialization instead happened from: " + stackTraceString);
        } catch (Throwable th) {
            getLogger().mo2290e(AppLovinLogger.SDK_TAG, "Error checking if sdk is initialized in main activity...", th);
        }
        return false;
    }

    /* renamed from: d */
    co m470d() {
        return this.f415l;
    }

    /* renamed from: e */
    boolean m471e() {
        return this.f424u;
    }

    /* renamed from: f */
    boolean m472f() {
        return this.f426w;
    }

    /* renamed from: g */
    void m473g() {
        this.f424u = true;
        this.f409f.m994a(new eg(this), 0);
    }

    public Object get(dl dlVar) {
        return this.f410g.m818a(dlVar);
    }

    public AppLovinAdService getAdService() {
        return this.f418o;
    }

    public Context getApplicationContext() {
        return this.f406c;
    }

    public C0517v getConnectionManager() {
        return this.f411h;
    }

    public C0519y getDataCollector() {
        return this.f416m;
    }

    public AppLovinEventService getEventService() {
        return this.f421r;
    }

    public al getFileManager() {
        return this.f413j;
    }

    public Activity getInitializationActivity() {
        return this.f407d != null ? (Activity) this.f407d.get() : null;
    }

    public AppLovinLogger getLogger() {
        return this.f408e;
    }

    public MediationServiceImpl getMediationService() {
        return this.f422s;
    }

    public AppLovinNativeAdService getNativeAdService() {
        return this.f419p;
    }

    public cz getPersistentPostbackManager() {
        return this.f423t;
    }

    public PostbackServiceImpl getPostbackService() {
        return this.f420q;
    }

    public String getSdkKey() {
        return this.f404a;
    }

    public AppLovinSdkSettings getSettings() {
        return this.f405b;
    }

    public dm getSettingsManager() {
        return this.f410g;
    }

    public AppLovinTargetingData getTargetingData() {
        return this.f417n;
    }

    public String getUserIdentifier() {
        return fj.m1067a();
    }

    /* renamed from: h */
    void m474h() {
        this.f410g.m824d();
        this.f410g.m822b();
        this.f412i.m877a();
    }

    public boolean hasCriticalErrors() {
        return this.f427x || this.f428y;
    }

    public void initialize(String str, AppLovinSdkSettings appLovinSdkSettings, Context context) {
        this.f404a = str;
        this.f405b = appLovinSdkSettings;
        if (context instanceof Activity) {
            this.f407d = new WeakReference((Activity) context);
        }
        this.f406c = context.getApplicationContext();
        try {
            C0513r c0513r = new C0513r();
            this.f408e = c0513r;
            this.f410g = new dm(this);
            this.f409f = new ei(this);
            this.f411h = new C0517v(this);
            this.f412i = new C0500do(this);
            this.f413j = new al(this);
            this.f416m = new C0519y(this);
            this.f418o = new AppLovinAdServiceImpl(this);
            this.f419p = new cq(this);
            this.f420q = new PostbackServiceImpl(this);
            this.f421r = new EventServiceImpl(this);
            this.f422s = new MediationServiceImpl(this);
            this.f423t = new cz(this);
            this.f414k = new C0502f(this);
            this.f415l = new co(this);
            this.f417n = new C0515t(this);
            if (!m465i()) {
                this.f427x = true;
                Log.e(AppLovinLogger.SDK_TAG, "Unable to initalize AppLovin SDK: Android SDK version has to be at least level 8");
            }
            if (str == null || str.length() < 1) {
                this.f428y = true;
                Log.e(AppLovinLogger.SDK_TAG, "Unable to find AppLovin SDK key. Please add     meta-data android:name=\"applovin.sdk.key\" android:value=\"YOUR_SDK_KEY_HERE\" into AndroidManifest.xml.");
                Writer stringWriter = new StringWriter();
                new Throwable("").printStackTrace(new PrintWriter(stringWriter));
                Log.e(AppLovinLogger.SDK_TAG, "Called with an invalid SDK key from: " + stringWriter.toString());
            }
            if (hasCriticalErrors()) {
                m467a(false);
                return;
            }
            c0513r.m1144a(this.f410g);
            if (appLovinSdkSettings instanceof bs) {
                c0513r.m1145a(((bs) appLovinSdkSettings).m610a());
            }
            m464a(this.f406c);
            this.f410g.m823c();
            if (((Boolean) this.f410g.m818a(dj.f773b)).booleanValue()) {
                this.f410g.m820a(appLovinSdkSettings);
                this.f410g.m822b();
            }
            m473g();
        } catch (Throwable th) {
            Log.e(AppLovinLogger.SDK_TAG, "Failed to load AppLovin SDK, ad serving will be disabled", th);
            m467a(false);
        }
    }

    public void initializeSdk() {
    }

    public boolean isEnabled() {
        return this.f425v;
    }

    public boolean isInitializedInMainActivity() {
        return this.f429z;
    }

    public void setInitializedInMainActivity(boolean z) {
        this.f429z = z;
    }

    public void setPluginVersion(String str) {
        if (str == null) {
            throw new IllegalArgumentException("No version specified");
        }
        this.f410g.m819a(dj.f750E, str);
        this.f410g.m822b();
    }

    public void setUserIdentifier(String str) {
        fj.m1068a(str);
    }
}
