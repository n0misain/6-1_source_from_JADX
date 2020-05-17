package com.google.android.gms.internal;

import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

@zzzn
public final class zzop extends zzox implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener {
    private static String[] zzIs = new String[]{"2011", "1009"};
    private final Object mLock = new Object();
    @Nullable
    private zzny zzHM;
    private final FrameLayout zzIt;
    private Map<String, WeakReference<View>> zzIu = new HashMap();
    @Nullable
    private View zzIv;
    private boolean zzIw = false;
    private Point zzIx = new Point();
    private Point zzIy = new Point();
    private WeakReference<zzge> zzIz = new WeakReference(null);
    @Nullable
    FrameLayout zzss;

    public zzop(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzIt = frameLayout;
        this.zzss = frameLayout2;
        zzbs.zzbX();
        zzajv.zza(this.zzIt, (OnGlobalLayoutListener) this);
        zzbs.zzbX();
        zzajv.zza(this.zzIt, (OnScrollChangedListener) this);
        this.zzIt.setOnTouchListener(this);
        this.zzIt.setOnClickListener(this);
        zzmo.initialize(this.zzIt.getContext());
    }

    private final void zza(zzoc zzoc) {
        synchronized (this.mLock) {
            View view;
            zzoc.zzd(this.zzIu);
            if (this.zzIu != null) {
                for (Object obj : zzIs) {
                    WeakReference weakReference = (WeakReference) this.zzIu.get(obj);
                    if (weakReference != null) {
                        view = (View) weakReference.get();
                        break;
                    }
                }
            }
            view = null;
            if (view instanceof FrameLayout) {
                zzoc.zza(view, new zzor(this, view));
                return;
            }
            zzoc.zzev();
        }
    }

    private final void zzg(@Nullable View view) {
        if (this.zzHM != null) {
            zzny zzer = this.zzHM instanceof zznx ? ((zznx) this.zzHM).zzer() : this.zzHM;
            if (zzer != null) {
                zzer.zzg(view);
            }
        }
    }

    private final int zzm(int i) {
        zzji.zzds();
        return zzaiy.zzd(this.zzHM.getContext(), i);
    }

    public final void destroy() {
        synchronized (this.mLock) {
            if (this.zzss != null) {
                this.zzss.removeAllViews();
            }
            this.zzss = null;
            this.zzIu = null;
            this.zzIv = null;
            this.zzHM = null;
            this.zzIx = null;
            this.zzIy = null;
            this.zzIz = null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r8) {
        /*
        r7 = this;
        r6 = r7.mLock;
        monitor-enter(r6);
        r0 = r7.zzHM;	 Catch:{ all -> 0x0078 }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r6);	 Catch:{ all -> 0x0078 }
    L_0x0008:
        return;
    L_0x0009:
        r3 = new android.os.Bundle;	 Catch:{ all -> 0x0078 }
        r3.<init>();	 Catch:{ all -> 0x0078 }
        r0 = "x";
        r1 = r7.zzIx;	 Catch:{ all -> 0x0078 }
        r1 = r1.x;	 Catch:{ all -> 0x0078 }
        r1 = r7.zzm(r1);	 Catch:{ all -> 0x0078 }
        r1 = (float) r1;	 Catch:{ all -> 0x0078 }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x0078 }
        r0 = "y";
        r1 = r7.zzIx;	 Catch:{ all -> 0x0078 }
        r1 = r1.y;	 Catch:{ all -> 0x0078 }
        r1 = r7.zzm(r1);	 Catch:{ all -> 0x0078 }
        r1 = (float) r1;	 Catch:{ all -> 0x0078 }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x0078 }
        r0 = "start_x";
        r1 = r7.zzIy;	 Catch:{ all -> 0x0078 }
        r1 = r1.x;	 Catch:{ all -> 0x0078 }
        r1 = r7.zzm(r1);	 Catch:{ all -> 0x0078 }
        r1 = (float) r1;	 Catch:{ all -> 0x0078 }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x0078 }
        r0 = "start_y";
        r1 = r7.zzIy;	 Catch:{ all -> 0x0078 }
        r1 = r1.y;	 Catch:{ all -> 0x0078 }
        r1 = r7.zzm(r1);	 Catch:{ all -> 0x0078 }
        r1 = (float) r1;	 Catch:{ all -> 0x0078 }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x0078 }
        r0 = r7.zzIv;	 Catch:{ all -> 0x0078 }
        if (r0 == 0) goto L_0x0088;
    L_0x004c:
        r0 = r7.zzIv;	 Catch:{ all -> 0x0078 }
        r0 = r0.equals(r8);	 Catch:{ all -> 0x0078 }
        if (r0 == 0) goto L_0x0088;
    L_0x0054:
        r0 = r7.zzHM;	 Catch:{ all -> 0x0078 }
        r0 = r0 instanceof com.google.android.gms.internal.zznx;	 Catch:{ all -> 0x0078 }
        if (r0 == 0) goto L_0x007b;
    L_0x005a:
        r0 = r7.zzHM;	 Catch:{ all -> 0x0078 }
        r0 = (com.google.android.gms.internal.zznx) r0;	 Catch:{ all -> 0x0078 }
        r0 = r0.zzer();	 Catch:{ all -> 0x0078 }
        if (r0 == 0) goto L_0x0076;
    L_0x0064:
        r0 = r7.zzHM;	 Catch:{ all -> 0x0078 }
        r0 = (com.google.android.gms.internal.zznx) r0;	 Catch:{ all -> 0x0078 }
        r0 = r0.zzer();	 Catch:{ all -> 0x0078 }
        r2 = "1007";
        r4 = r7.zzIu;	 Catch:{ all -> 0x0078 }
        r5 = r7.zzIt;	 Catch:{ all -> 0x0078 }
        r1 = r8;
        r0.zza(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0078 }
    L_0x0076:
        monitor-exit(r6);	 Catch:{ all -> 0x0078 }
        goto L_0x0008;
    L_0x0078:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0078 }
        throw r0;
    L_0x007b:
        r0 = r7.zzHM;	 Catch:{ all -> 0x0078 }
        r2 = "1007";
        r4 = r7.zzIu;	 Catch:{ all -> 0x0078 }
        r5 = r7.zzIt;	 Catch:{ all -> 0x0078 }
        r1 = r8;
        r0.zza(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0078 }
        goto L_0x0076;
    L_0x0088:
        r0 = r7.zzHM;	 Catch:{ all -> 0x0078 }
        r1 = r7.zzIu;	 Catch:{ all -> 0x0078 }
        r2 = r7.zzIt;	 Catch:{ all -> 0x0078 }
        r0.zza(r8, r1, r3, r2);	 Catch:{ all -> 0x0078 }
        goto L_0x0076;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzop.onClick(android.view.View):void");
    }

    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            if (this.zzIw) {
                int measuredWidth = this.zzIt.getMeasuredWidth();
                int measuredHeight = this.zzIt.getMeasuredHeight();
                if (!(measuredWidth == 0 || measuredHeight == 0 || this.zzss == null)) {
                    this.zzss.setLayoutParams(new LayoutParams(measuredWidth, measuredHeight));
                    this.zzIw = false;
                }
            }
            if (this.zzHM != null) {
                this.zzHM.zzc(this.zzIt, this.zzIu);
            }
        }
    }

    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzHM != null) {
                this.zzHM.zzc(this.zzIt, this.zzIu);
            }
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzHM == null) {
            } else {
                int[] iArr = new int[2];
                this.zzIt.getLocationOnScreen(iArr);
                Point point = new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
                this.zzIx = point;
                if (motionEvent.getAction() == 0) {
                    this.zzIy = point;
                }
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setLocation((float) point.x, (float) point.y);
                this.zzHM.zzd(obtain);
                obtain.recycle();
            }
        }
        return false;
    }

    public final IObjectWrapper zzL(String str) {
        Object obj = null;
        synchronized (this.mLock) {
            if (this.zzIu == null) {
                return null;
            }
            WeakReference weakReference = (WeakReference) this.zzIu.get(str);
            if (weakReference != null) {
                View view = (View) weakReference.get();
            }
            IObjectWrapper zzw = zzn.zzw(obj);
            return zzw;
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, int i) {
        if (zzbs.zzbY().zzr(this.zzIt.getContext()) && this.zzIz != null) {
            zzge zzge = (zzge) this.zzIz.get();
            if (zzge != null) {
                zzge.zzcB();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(java.lang.String r5, com.google.android.gms.dynamic.IObjectWrapper r6) {
        /*
        r4 = this;
        r0 = com.google.android.gms.dynamic.zzn.zzE(r6);
        r0 = (android.view.View) r0;
        r1 = r4.mLock;
        monitor-enter(r1);
        r2 = r4.zzIu;	 Catch:{ all -> 0x0018 }
        if (r2 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r1);	 Catch:{ all -> 0x0018 }
    L_0x000e:
        return;
    L_0x000f:
        if (r0 != 0) goto L_0x001b;
    L_0x0011:
        r0 = r4.zzIu;	 Catch:{ all -> 0x0018 }
        r0.remove(r5);	 Catch:{ all -> 0x0018 }
    L_0x0016:
        monitor-exit(r1);	 Catch:{ all -> 0x0018 }
        goto L_0x000e;
    L_0x0018:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0018 }
        throw r0;
    L_0x001b:
        r2 = r4.zzIu;	 Catch:{ all -> 0x0018 }
        r3 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x0018 }
        r3.<init>(r0);	 Catch:{ all -> 0x0018 }
        r2.put(r5, r3);	 Catch:{ all -> 0x0018 }
        r2 = "1098";
        r2 = r2.equals(r5);	 Catch:{ all -> 0x0018 }
        if (r2 == 0) goto L_0x002f;
    L_0x002d:
        monitor-exit(r1);	 Catch:{ all -> 0x0018 }
        goto L_0x000e;
    L_0x002f:
        r0.setOnTouchListener(r4);	 Catch:{ all -> 0x0018 }
        r2 = 1;
        r0.setClickable(r2);	 Catch:{ all -> 0x0018 }
        r0.setOnClickListener(r4);	 Catch:{ all -> 0x0018 }
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzop.zzd(java.lang.String, com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zze(com.google.android.gms.dynamic.IObjectWrapper r11) {
        /*
        r10 = this;
        r3 = 1;
        r5 = 0;
        r4 = 0;
        r6 = r10.mLock;
        monitor-enter(r6);
        r1 = 0;
        r10.zzg(r1);	 Catch:{ all -> 0x016f }
        r1 = com.google.android.gms.dynamic.zzn.zzE(r11);	 Catch:{ all -> 0x016f }
        r2 = r1 instanceof com.google.android.gms.internal.zzoc;	 Catch:{ all -> 0x016f }
        if (r2 != 0) goto L_0x0019;
    L_0x0012:
        r1 = "Not an instance of native engine. This is most likely a transient error";
        com.google.android.gms.internal.zzajc.zzaT(r1);	 Catch:{ all -> 0x016f }
        monitor-exit(r6);	 Catch:{ all -> 0x016f }
    L_0x0018:
        return;
    L_0x0019:
        r2 = r10.zzss;	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x002e;
    L_0x001d:
        r2 = r10.zzss;	 Catch:{ all -> 0x016f }
        r7 = new android.widget.FrameLayout$LayoutParams;	 Catch:{ all -> 0x016f }
        r8 = 0;
        r9 = 0;
        r7.<init>(r8, r9);	 Catch:{ all -> 0x016f }
        r2.setLayoutParams(r7);	 Catch:{ all -> 0x016f }
        r2 = r10.zzIt;	 Catch:{ all -> 0x016f }
        r2.requestLayout();	 Catch:{ all -> 0x016f }
    L_0x002e:
        r2 = 1;
        r10.zzIw = r2;	 Catch:{ all -> 0x016f }
        r1 = (com.google.android.gms.internal.zzoc) r1;	 Catch:{ all -> 0x016f }
        r2 = r10.zzHM;	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x0052;
    L_0x0037:
        r2 = com.google.android.gms.internal.zzmo.zzFw;	 Catch:{ all -> 0x016f }
        r7 = com.google.android.gms.ads.internal.zzbs.zzbL();	 Catch:{ all -> 0x016f }
        r2 = r7.zzd(r2);	 Catch:{ all -> 0x016f }
        r2 = (java.lang.Boolean) r2;	 Catch:{ all -> 0x016f }
        r2 = r2.booleanValue();	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x0052;
    L_0x0049:
        r2 = r10.zzHM;	 Catch:{ all -> 0x016f }
        r7 = r10.zzIt;	 Catch:{ all -> 0x016f }
        r8 = r10.zzIu;	 Catch:{ all -> 0x016f }
        r2.zzb(r7, r8);	 Catch:{ all -> 0x016f }
    L_0x0052:
        r2 = r10.zzHM;	 Catch:{ all -> 0x016f }
        r2 = r2 instanceof com.google.android.gms.internal.zzoc;	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x008d;
    L_0x0058:
        r2 = r10.zzHM;	 Catch:{ all -> 0x016f }
        r2 = (com.google.android.gms.internal.zzoc) r2;	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x008d;
    L_0x005e:
        r7 = r2.getContext();	 Catch:{ all -> 0x016f }
        if (r7 == 0) goto L_0x008d;
    L_0x0064:
        r7 = com.google.android.gms.ads.internal.zzbs.zzbY();	 Catch:{ all -> 0x016f }
        r8 = r10.zzIt;	 Catch:{ all -> 0x016f }
        r8 = r8.getContext();	 Catch:{ all -> 0x016f }
        r7 = r7.zzr(r8);	 Catch:{ all -> 0x016f }
        if (r7 == 0) goto L_0x008d;
    L_0x0074:
        r7 = r2.zzew();	 Catch:{ all -> 0x016f }
        if (r7 == 0) goto L_0x007e;
    L_0x007a:
        r2 = 0;
        r7.zzu(r2);	 Catch:{ all -> 0x016f }
    L_0x007e:
        r2 = r10.zzIz;	 Catch:{ all -> 0x016f }
        r2 = r2.get();	 Catch:{ all -> 0x016f }
        r2 = (com.google.android.gms.internal.zzge) r2;	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x008d;
    L_0x0088:
        if (r7 == 0) goto L_0x008d;
    L_0x008a:
        r2.zzb(r7);	 Catch:{ all -> 0x016f }
    L_0x008d:
        r2 = r10.zzHM;	 Catch:{ all -> 0x016f }
        r2 = r2 instanceof com.google.android.gms.internal.zznx;	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x0172;
    L_0x0093:
        r2 = r10.zzHM;	 Catch:{ all -> 0x016f }
        r2 = (com.google.android.gms.internal.zznx) r2;	 Catch:{ all -> 0x016f }
        r2 = r2.zzeq();	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x0172;
    L_0x009d:
        r2 = r10.zzHM;	 Catch:{ all -> 0x016f }
        r2 = (com.google.android.gms.internal.zznx) r2;	 Catch:{ all -> 0x016f }
        r2.zzc(r1);	 Catch:{ all -> 0x016f }
    L_0x00a4:
        r2 = com.google.android.gms.internal.zzmo.zzFw;	 Catch:{ all -> 0x016f }
        r7 = com.google.android.gms.ads.internal.zzbs.zzbL();	 Catch:{ all -> 0x016f }
        r2 = r7.zzd(r2);	 Catch:{ all -> 0x016f }
        r2 = (java.lang.Boolean) r2;	 Catch:{ all -> 0x016f }
        r2 = r2.booleanValue();	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x00bc;
    L_0x00b6:
        r2 = r10.zzss;	 Catch:{ all -> 0x016f }
        r7 = 0;
        r2.setClickable(r7);	 Catch:{ all -> 0x016f }
    L_0x00bc:
        r2 = r10.zzss;	 Catch:{ all -> 0x016f }
        r2.removeAllViews();	 Catch:{ all -> 0x016f }
        r7 = r1.zzep();	 Catch:{ all -> 0x016f }
        if (r7 == 0) goto L_0x00e4;
    L_0x00c7:
        r2 = r10.zzIu;	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x00e4;
    L_0x00cb:
        r2 = r10.zzIu;	 Catch:{ all -> 0x016f }
        r8 = "1098";
        r2 = r2.get(r8);	 Catch:{ all -> 0x016f }
        r2 = (java.lang.ref.WeakReference) r2;	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x0182;
    L_0x00d7:
        r2 = r2.get();	 Catch:{ all -> 0x016f }
        r2 = (android.view.View) r2;	 Catch:{ all -> 0x016f }
    L_0x00dd:
        r8 = r2 instanceof android.view.ViewGroup;	 Catch:{ all -> 0x016f }
        if (r8 == 0) goto L_0x00e4;
    L_0x00e1:
        r2 = (android.view.ViewGroup) r2;	 Catch:{ all -> 0x016f }
        r5 = r2;
    L_0x00e4:
        if (r7 == 0) goto L_0x0185;
    L_0x00e6:
        if (r5 == 0) goto L_0x0185;
    L_0x00e8:
        r2 = r3;
    L_0x00e9:
        r3 = r1.zza(r10, r2);	 Catch:{ all -> 0x016f }
        r10.zzIv = r3;	 Catch:{ all -> 0x016f }
        r3 = r10.zzIv;	 Catch:{ all -> 0x016f }
        if (r3 == 0) goto L_0x010f;
    L_0x00f3:
        r3 = r10.zzIu;	 Catch:{ all -> 0x016f }
        if (r3 == 0) goto L_0x0105;
    L_0x00f7:
        r3 = r10.zzIu;	 Catch:{ all -> 0x016f }
        r4 = "1007";
        r7 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x016f }
        r8 = r10.zzIv;	 Catch:{ all -> 0x016f }
        r7.<init>(r8);	 Catch:{ all -> 0x016f }
        r3.put(r4, r7);	 Catch:{ all -> 0x016f }
    L_0x0105:
        if (r2 == 0) goto L_0x0188;
    L_0x0107:
        r5.removeAllViews();	 Catch:{ all -> 0x016f }
        r2 = r10.zzIv;	 Catch:{ all -> 0x016f }
        r5.addView(r2);	 Catch:{ all -> 0x016f }
    L_0x010f:
        r2 = r10.zzIt;	 Catch:{ all -> 0x016f }
        r3 = r10.zzIu;	 Catch:{ all -> 0x016f }
        r1.zza(r2, r3, r10, r10);	 Catch:{ all -> 0x016f }
        r2 = com.google.android.gms.internal.zzagz.zzZr;	 Catch:{ all -> 0x016f }
        r3 = new com.google.android.gms.internal.zzoq;	 Catch:{ all -> 0x016f }
        r3.<init>(r10, r1);	 Catch:{ all -> 0x016f }
        r2.post(r3);	 Catch:{ all -> 0x016f }
        r1 = r10.zzIt;	 Catch:{ all -> 0x016f }
        r10.zzg(r1);	 Catch:{ all -> 0x016f }
        r1 = r10.zzHM;	 Catch:{ all -> 0x016f }
        r1 = r1 instanceof com.google.android.gms.internal.zzoc;	 Catch:{ all -> 0x016f }
        if (r1 == 0) goto L_0x016c;
    L_0x012b:
        r1 = r10.zzHM;	 Catch:{ all -> 0x016f }
        r1 = (com.google.android.gms.internal.zzoc) r1;	 Catch:{ all -> 0x016f }
        if (r1 == 0) goto L_0x016c;
    L_0x0131:
        r2 = r1.getContext();	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x016c;
    L_0x0137:
        r2 = com.google.android.gms.ads.internal.zzbs.zzbY();	 Catch:{ all -> 0x016f }
        r3 = r10.zzIt;	 Catch:{ all -> 0x016f }
        r3 = r3.getContext();	 Catch:{ all -> 0x016f }
        r2 = r2.zzr(r3);	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x016c;
    L_0x0147:
        r2 = r10.zzIz;	 Catch:{ all -> 0x016f }
        r2 = r2.get();	 Catch:{ all -> 0x016f }
        r2 = (com.google.android.gms.internal.zzge) r2;	 Catch:{ all -> 0x016f }
        if (r2 != 0) goto L_0x0165;
    L_0x0151:
        r2 = new com.google.android.gms.internal.zzge;	 Catch:{ all -> 0x016f }
        r3 = r10.zzIt;	 Catch:{ all -> 0x016f }
        r3 = r3.getContext();	 Catch:{ all -> 0x016f }
        r4 = r10.zzIt;	 Catch:{ all -> 0x016f }
        r2.<init>(r3, r4);	 Catch:{ all -> 0x016f }
        r3 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x016f }
        r3.<init>(r2);	 Catch:{ all -> 0x016f }
        r10.zzIz = r3;	 Catch:{ all -> 0x016f }
    L_0x0165:
        r1 = r1.zzew();	 Catch:{ all -> 0x016f }
        r2.zza(r1);	 Catch:{ all -> 0x016f }
    L_0x016c:
        monitor-exit(r6);	 Catch:{ all -> 0x016f }
        goto L_0x0018;
    L_0x016f:
        r1 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x016f }
        throw r1;
    L_0x0172:
        r10.zzHM = r1;	 Catch:{ all -> 0x016f }
        r2 = r1 instanceof com.google.android.gms.internal.zznx;	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x00a4;
    L_0x0178:
        r0 = r1;
        r0 = (com.google.android.gms.internal.zznx) r0;	 Catch:{ all -> 0x016f }
        r2 = r0;
        r7 = 0;
        r2.zzc(r7);	 Catch:{ all -> 0x016f }
        goto L_0x00a4;
    L_0x0182:
        r2 = r5;
        goto L_0x00dd;
    L_0x0185:
        r2 = r4;
        goto L_0x00e9;
    L_0x0188:
        r2 = r1.getContext();	 Catch:{ all -> 0x016f }
        r3 = new com.google.android.gms.ads.formats.AdChoicesView;	 Catch:{ all -> 0x016f }
        r3.<init>(r2);	 Catch:{ all -> 0x016f }
        r2 = new android.widget.FrameLayout$LayoutParams;	 Catch:{ all -> 0x016f }
        r4 = -1;
        r5 = -1;
        r2.<init>(r4, r5);	 Catch:{ all -> 0x016f }
        r3.setLayoutParams(r2);	 Catch:{ all -> 0x016f }
        r2 = r10.zzIv;	 Catch:{ all -> 0x016f }
        r3.addView(r2);	 Catch:{ all -> 0x016f }
        r2 = r10.zzss;	 Catch:{ all -> 0x016f }
        if (r2 == 0) goto L_0x010f;
    L_0x01a4:
        r2 = r10.zzss;	 Catch:{ all -> 0x016f }
        r2.addView(r3);	 Catch:{ all -> 0x016f }
        goto L_0x010f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzop.zze(com.google.android.gms.dynamic.IObjectWrapper):void");
    }
}
