package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzfi implements OnGlobalLayoutListener, OnScrollChangedListener {
    private final Context mApplicationContext;
    private Object mLock = new Object();
    private boolean zzuV = false;
    private final WeakReference<zzaff> zzwN;
    private WeakReference<ViewTreeObserver> zzwO;
    private final zzgs zzwP;
    protected final zzfg zzwQ;
    private final WindowManager zzwR;
    private final PowerManager zzwS;
    private final KeyguardManager zzwT;
    private final DisplayMetrics zzwU;
    @Nullable
    private zzfp zzwV;
    private boolean zzwW;
    private boolean zzwX = false;
    private boolean zzwY;
    private boolean zzwZ;
    private zzair zzwx;
    private boolean zzxa;
    @Nullable
    private BroadcastReceiver zzxb;
    private final HashSet<Object> zzxc = new HashSet();
    private final HashSet<zzgd> zzxd = new HashSet();
    private final Rect zzxe = new Rect();
    private final zzfl zzxf;
    private float zzxg;

    public zzfi(Context context, zziv zziv, zzaff zzaff, zzaje zzaje, zzgs zzgs) {
        this.zzwN = new WeakReference(zzaff);
        this.zzwP = zzgs;
        this.zzwO = new WeakReference(null);
        this.zzwY = true;
        this.zzxa = false;
        this.zzwx = new zzair(200);
        this.zzwQ = new zzfg(UUID.randomUUID().toString(), zzaje, zziv.zzAs, zzaff.zzXL, zzaff.zzcn(), zziv.zzAv);
        this.zzwR = (WindowManager) context.getSystemService("window");
        this.zzwS = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzwT = (KeyguardManager) context.getSystemService("keyguard");
        this.mApplicationContext = context;
        this.zzxf = new zzfl(this, new Handler());
        this.mApplicationContext.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.zzxf);
        this.zzwU = context.getResources().getDisplayMetrics();
        Display defaultDisplay = this.zzwR.getDefaultDisplay();
        this.zzxe.right = defaultDisplay.getWidth();
        this.zzxe.bottom = defaultDisplay.getHeight();
        zzcp();
    }

    private final boolean isScreenOn() {
        return VERSION.SDK_INT >= 20 ? this.zzwS.isInteractive() : this.zzwS.isScreenOn();
    }

    private static int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    private final JSONObject zza(@Nullable View view, @Nullable Boolean bool) throws JSONException {
        if (view == null) {
            return zzcu().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
        }
        boolean isAttachedToWindow = zzbs.zzbB().isAttachedToWindow(view);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view.getLocationOnScreen(iArr);
            view.getLocationInWindow(iArr2);
        } catch (Throwable e) {
            zzajc.zzb("Failure getting view location.", e);
        }
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect2, null);
        Rect rect3 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect3);
        Rect rect4 = new Rect();
        view.getHitRect(rect4);
        JSONObject zzcu = zzcu();
        zzcu.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", isAttachedToWindow).put("viewBox", new JSONObject().put("top", zza(this.zzxe.top, this.zzwU)).put("bottom", zza(this.zzxe.bottom, this.zzwU)).put("left", zza(this.zzxe.left, this.zzwU)).put("right", zza(this.zzxe.right, this.zzwU))).put("adBox", new JSONObject().put("top", zza(rect.top, this.zzwU)).put("bottom", zza(rect.bottom, this.zzwU)).put("left", zza(rect.left, this.zzwU)).put("right", zza(rect.right, this.zzwU))).put("globalVisibleBox", new JSONObject().put("top", zza(rect2.top, this.zzwU)).put("bottom", zza(rect2.bottom, this.zzwU)).put("left", zza(rect2.left, this.zzwU)).put("right", zza(rect2.right, this.zzwU))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", zza(rect3.top, this.zzwU)).put("bottom", zza(rect3.bottom, this.zzwU)).put("left", zza(rect3.left, this.zzwU)).put("right", zza(rect3.right, this.zzwU))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put("top", zza(rect4.top, this.zzwU)).put("bottom", zza(rect4.bottom, this.zzwU)).put("left", zza(rect4.left, this.zzwU)).put("right", zza(rect4.right, this.zzwU))).put("screenDensity", (double) this.zzwU.density);
        if (bool == null) {
            bool = Boolean.valueOf(zzbs.zzbz().zza(view, this.zzwS, this.zzwT));
        }
        zzcu.put("isVisible", bool.booleanValue());
        return zzcu;
    }

    private static JSONObject zza(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }

    private final void zza(JSONObject jSONObject, boolean z) {
        try {
            JSONObject zza = zza(jSONObject);
            ArrayList arrayList = new ArrayList(this.zzxd);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((zzgd) obj).zzb(zza, z);
            }
        } catch (Throwable th) {
            zzajc.zzb("Skipping active view message.", th);
        }
    }

    private final void zzcr() {
        if (this.zzwV != null) {
            this.zzwV.zza(this);
        }
    }

    private final void zzct() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzwO.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    private final JSONObject zzcu() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        zzbs.zzbz();
        zzbs.zzbz();
        jSONObject.put("afmaVersion", this.zzwQ.zzck()).put("activeViewJSON", this.zzwQ.zzcl()).put(Param.TIMESTAMP, zzbs.zzbF().elapsedRealtime()).put("adFormat", this.zzwQ.zzcj()).put("hashCode", this.zzwQ.zzcm()).put("isMraid", this.zzwQ.zzcn()).put("isStopped", this.zzwX).put("isPaused", this.zzuV).put("isNative", this.zzwQ.zzco()).put("isScreenOn", isScreenOn()).put("appMuted", zzagz.zzbh()).put("appVolume", (double) zzagz.zzbf()).put("deviceVolume", (double) this.zzxg);
        return jSONObject;
    }

    public final void onGlobalLayout() {
        zzg(2);
    }

    public final void onScrollChanged() {
        zzg(1);
    }

    public final void pause() {
        synchronized (this.mLock) {
            this.zzuV = true;
            zzg(3);
        }
    }

    public final void resume() {
        synchronized (this.mLock) {
            this.zzuV = false;
            zzg(3);
        }
    }

    public final void stop() {
        synchronized (this.mLock) {
            this.zzwX = true;
            zzg(3);
        }
    }

    public final void zza(zzfp zzfp) {
        synchronized (this.mLock) {
            this.zzwV = zzfp;
        }
    }

    public final void zza(zzgd zzgd) {
        if (this.zzxd.isEmpty()) {
            synchronized (this.mLock) {
                if (this.zzxb != null) {
                } else {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.SCREEN_ON");
                    intentFilter.addAction("android.intent.action.SCREEN_OFF");
                    this.zzxb = new zzfj(this);
                    this.mApplicationContext.registerReceiver(this.zzxb, intentFilter);
                }
            }
            zzg(3);
        }
        this.zzxd.add(zzgd);
        try {
            zzgd.zzb(zza(zza(this.zzwP.zzcv(), null)), false);
        } catch (Throwable e) {
            zzajc.zzb("Skipping measurement update for new client.", e);
        }
    }

    final void zza(zzgd zzgd, Map<String, String> map) {
        String str = "Received request to untrack: ";
        String valueOf = String.valueOf(this.zzwQ.zzcm());
        zzajc.zzaC(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        zzb(zzgd);
    }

    final void zzb(zzaka zzaka, Map<String, String> map) {
        zzg(3);
    }

    public final void zzb(zzgd zzgd) {
        this.zzxd.remove(zzgd);
        zzgd.zzcz();
        if (this.zzxd.isEmpty()) {
            synchronized (this.mLock) {
                zzct();
                synchronized (this.mLock) {
                    if (this.zzxb != null) {
                        try {
                            this.mApplicationContext.unregisterReceiver(this.zzxb);
                        } catch (Throwable e) {
                            zzajc.zzb("Failed trying to unregister the receiver", e);
                        } catch (Throwable e2) {
                            zzbs.zzbD().zza(e2, "ActiveViewUnit.stopScreenStatusMonitoring");
                        }
                        this.zzxb = null;
                    }
                }
                this.mApplicationContext.getContentResolver().unregisterContentObserver(this.zzxf);
                this.zzwY = false;
                zzcr();
                ArrayList arrayList = new ArrayList(this.zzxd);
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    zzb((zzgd) obj);
                }
            }
        }
    }

    final boolean zzb(@Nullable Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = (String) map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(this.zzwQ.zzcm());
    }

    final void zzc(Map<String, String> map) {
        if (map.containsKey("isVisible")) {
            if (!AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("isVisible"))) {
                ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(map.get("isVisible"));
            }
            Iterator it = this.zzxc.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    public final void zzcp() {
        zzbs.zzbz();
        this.zzxg = zzagz.zzM(this.mApplicationContext);
    }

    public final void zzcq() {
        synchronized (this.mLock) {
            if (this.zzwY) {
                this.zzwZ = true;
                try {
                    JSONObject zzcu = zzcu();
                    zzcu.put("doneReasonCode", "u");
                    zza(zzcu, true);
                } catch (Throwable e) {
                    zzajc.zzb("JSON failure while processing active view data.", e);
                } catch (Throwable e2) {
                    zzajc.zzb("Failure while processing active view data.", e2);
                }
                String str = "Untracking ad unit: ";
                String valueOf = String.valueOf(this.zzwQ.zzcm());
                zzajc.zzaC(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
    }

    public final boolean zzcs() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzwY;
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final void zzg(int r8) {
        /*
        r7 = this;
        r2 = 0;
        r1 = 1;
        r4 = r7.mLock;
        monitor-enter(r4);
        r0 = r7.zzxd;	 Catch:{ all -> 0x005d }
        r3 = r0.iterator();	 Catch:{ all -> 0x005d }
    L_0x000b:
        r0 = r3.hasNext();	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0026;
    L_0x0011:
        r0 = r3.next();	 Catch:{ all -> 0x005d }
        r0 = (com.google.android.gms.internal.zzgd) r0;	 Catch:{ all -> 0x005d }
        r0 = r0.zzcy();	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x000b;
    L_0x001d:
        r0 = r1;
    L_0x001e:
        if (r0 == 0) goto L_0x0024;
    L_0x0020:
        r0 = r7.zzwY;	 Catch:{ all -> 0x005d }
        if (r0 != 0) goto L_0x0028;
    L_0x0024:
        monitor-exit(r4);	 Catch:{ all -> 0x005d }
    L_0x0025:
        return;
    L_0x0026:
        r0 = r2;
        goto L_0x001e;
    L_0x0028:
        r0 = r7.zzwP;	 Catch:{ all -> 0x005d }
        r5 = r0.zzcv();	 Catch:{ all -> 0x005d }
        if (r5 == 0) goto L_0x0060;
    L_0x0030:
        r0 = com.google.android.gms.ads.internal.zzbs.zzbz();	 Catch:{ all -> 0x005d }
        r3 = r7.zzwS;	 Catch:{ all -> 0x005d }
        r6 = r7.zzwT;	 Catch:{ all -> 0x005d }
        r0 = r0.zza(r5, r3, r6);	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0060;
    L_0x003e:
        r3 = r1;
    L_0x003f:
        if (r5 == 0) goto L_0x0062;
    L_0x0041:
        if (r3 == 0) goto L_0x0062;
    L_0x0043:
        r0 = new android.graphics.Rect;	 Catch:{ all -> 0x005d }
        r0.<init>();	 Catch:{ all -> 0x005d }
        r6 = 0;
        r0 = r5.getGlobalVisibleRect(r0, r6);	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0062;
    L_0x004f:
        r0 = r1;
    L_0x0050:
        r2 = r7.zzwP;	 Catch:{ all -> 0x005d }
        r2 = r2.zzcw();	 Catch:{ all -> 0x005d }
        if (r2 == 0) goto L_0x0064;
    L_0x0058:
        r7.zzcq();	 Catch:{ all -> 0x005d }
        monitor-exit(r4);	 Catch:{ all -> 0x005d }
        goto L_0x0025;
    L_0x005d:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x005d }
        throw r0;
    L_0x0060:
        r3 = r2;
        goto L_0x003f;
    L_0x0062:
        r0 = r2;
        goto L_0x0050;
    L_0x0064:
        if (r8 != r1) goto L_0x0074;
    L_0x0066:
        r2 = r7.zzwx;	 Catch:{ all -> 0x005d }
        r2 = r2.tryAcquire();	 Catch:{ all -> 0x005d }
        if (r2 != 0) goto L_0x0074;
    L_0x006e:
        r2 = r7.zzxa;	 Catch:{ all -> 0x005d }
        if (r0 != r2) goto L_0x0074;
    L_0x0072:
        monitor-exit(r4);	 Catch:{ all -> 0x005d }
        goto L_0x0025;
    L_0x0074:
        if (r0 != 0) goto L_0x007e;
    L_0x0076:
        r2 = r7.zzxa;	 Catch:{ all -> 0x005d }
        if (r2 != 0) goto L_0x007e;
    L_0x007a:
        if (r8 != r1) goto L_0x007e;
    L_0x007c:
        monitor-exit(r4);	 Catch:{ all -> 0x005d }
        goto L_0x0025;
    L_0x007e:
        r1 = java.lang.Boolean.valueOf(r3);	 Catch:{ JSONException -> 0x00d2, RuntimeException -> 0x00cb }
        r1 = r7.zza(r5, r1);	 Catch:{ JSONException -> 0x00d2, RuntimeException -> 0x00cb }
        r2 = 0;
        r7.zza(r1, r2);	 Catch:{ JSONException -> 0x00d2, RuntimeException -> 0x00cb }
        r7.zzxa = r0;	 Catch:{ JSONException -> 0x00d2, RuntimeException -> 0x00cb }
    L_0x008c:
        r0 = r7.zzwP;	 Catch:{ all -> 0x005d }
        r0 = r0.zzcx();	 Catch:{ all -> 0x005d }
        r1 = r0.zzcv();	 Catch:{ all -> 0x005d }
        if (r1 == 0) goto L_0x00c5;
    L_0x0098:
        r0 = r7.zzwO;	 Catch:{ all -> 0x005d }
        r0 = r0.get();	 Catch:{ all -> 0x005d }
        r0 = (android.view.ViewTreeObserver) r0;	 Catch:{ all -> 0x005d }
        r1 = r1.getViewTreeObserver();	 Catch:{ all -> 0x005d }
        if (r1 == r0) goto L_0x00c5;
    L_0x00a6:
        r7.zzct();	 Catch:{ all -> 0x005d }
        r2 = r7.zzwW;	 Catch:{ all -> 0x005d }
        if (r2 == 0) goto L_0x00b5;
    L_0x00ad:
        if (r0 == 0) goto L_0x00be;
    L_0x00af:
        r0 = r0.isAlive();	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x00be;
    L_0x00b5:
        r0 = 1;
        r7.zzwW = r0;	 Catch:{ all -> 0x005d }
        r1.addOnScrollChangedListener(r7);	 Catch:{ all -> 0x005d }
        r1.addOnGlobalLayoutListener(r7);	 Catch:{ all -> 0x005d }
    L_0x00be:
        r0 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x005d }
        r0.<init>(r1);	 Catch:{ all -> 0x005d }
        r7.zzwO = r0;	 Catch:{ all -> 0x005d }
    L_0x00c5:
        r7.zzcr();	 Catch:{ all -> 0x005d }
        monitor-exit(r4);	 Catch:{ all -> 0x005d }
        goto L_0x0025;
    L_0x00cb:
        r0 = move-exception;
    L_0x00cc:
        r1 = "Active view update failed.";
        com.google.android.gms.internal.zzajc.zza(r1, r0);	 Catch:{ all -> 0x005d }
        goto L_0x008c;
    L_0x00d2:
        r0 = move-exception;
        goto L_0x00cc;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfi.zzg(int):void");
    }
}
