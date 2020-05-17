package com.google.android.gms.internal;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.common.util.zzf;
import java.util.Set;

@zzzn
public final class zzwk extends zzwu {
    private static Set<String> zzNy = zzf.zzb("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center");
    private final Object mLock = new Object();
    private final zzaka zzJH;
    private boolean zzNA = true;
    private int zzNB = 0;
    private int zzNC = 0;
    private int zzND = 0;
    private int zzNE = 0;
    private ImageView zzNF;
    private LinearLayout zzNG;
    private zzwv zzNH;
    private PopupWindow zzNI;
    private RelativeLayout zzNJ;
    private ViewGroup zzNK;
    private final Activity zzNo;
    private String zzNz = "top-right";
    private int zzrW = -1;
    private int zzrX = -1;
    private zziv zzuZ;

    public zzwk(zzaka zzaka, zzwv zzwv) {
        super(zzaka, "resize");
        this.zzJH = zzaka;
        this.zzNo = zzaka.zzis();
        this.zzNH = zzwv;
    }

    private final void zza(int i, int i2) {
        zzb(i, i2 - zzbs.zzbz().zzh(this.zzNo)[0], this.zzrW, this.zzrX);
    }

    private final int[] zzfB() {
        int i;
        int[] zzg = zzbs.zzbz().zzg(this.zzNo);
        int[] zzh = zzbs.zzbz().zzh(this.zzNo);
        int i2 = zzg[0];
        int i3 = zzg[1];
        if (this.zzrW < 50 || this.zzrW > i2) {
            zzajc.zzaT("Width is too small or too large.");
            i3 = 0;
        } else if (this.zzrX < 50 || this.zzrX > i3) {
            zzajc.zzaT("Height is too small or too large.");
            i3 = 0;
        } else if (this.zzrX == i3 && this.zzrW == i2) {
            zzajc.zzaT("Cannot resize to a full-screen ad.");
            i3 = 0;
        } else {
            if (this.zzNA) {
                String str = this.zzNz;
                i3 = -1;
                switch (str.hashCode()) {
                    case -1364013995:
                        if (str.equals("center")) {
                            i3 = 2;
                            break;
                        }
                        break;
                    case -1012429441:
                        if (str.equals("top-left")) {
                            i3 = 0;
                            break;
                        }
                        break;
                    case -655373719:
                        if (str.equals("bottom-left")) {
                            i3 = 3;
                            break;
                        }
                        break;
                    case 1163912186:
                        if (str.equals("bottom-right")) {
                            i3 = 5;
                            break;
                        }
                        break;
                    case 1288627767:
                        if (str.equals("bottom-center")) {
                            i3 = 4;
                            break;
                        }
                        break;
                    case 1755462605:
                        if (str.equals("top-center")) {
                            i3 = 1;
                            break;
                        }
                        break;
                }
                switch (i3) {
                    case 0:
                        i = this.zzND + this.zzNB;
                        i3 = this.zzNC + this.zzNE;
                        break;
                    case 1:
                        i = ((this.zzNB + this.zzND) + (this.zzrW / 2)) - 25;
                        i3 = this.zzNC + this.zzNE;
                        break;
                    case 2:
                        i = ((this.zzNB + this.zzND) + (this.zzrW / 2)) - 25;
                        i3 = ((this.zzNC + this.zzNE) + (this.zzrX / 2)) - 25;
                        break;
                    case 3:
                        i = this.zzND + this.zzNB;
                        i3 = ((this.zzNC + this.zzNE) + this.zzrX) - 50;
                        break;
                    case 4:
                        i = ((this.zzNB + this.zzND) + (this.zzrW / 2)) - 25;
                        i3 = ((this.zzNC + this.zzNE) + this.zzrX) - 50;
                        break;
                    case 5:
                        i = ((this.zzNB + this.zzND) + this.zzrW) - 50;
                        i3 = ((this.zzNC + this.zzNE) + this.zzrX) - 50;
                        break;
                    default:
                        i = ((this.zzNB + this.zzND) + this.zzrW) - 50;
                        i3 = this.zzNC + this.zzNE;
                        break;
                }
                if (i < 0 || i + 50 > i2 || r0 < zzh[0] || r0 + 50 > zzh[1]) {
                    i3 = 0;
                }
            }
            i3 = 1;
        }
        if (i3 == 0) {
            return null;
        }
        if (this.zzNA) {
            return new int[]{this.zzNB + this.zzND, this.zzNC + this.zzNE};
        }
        zzg = zzbs.zzbz().zzg(this.zzNo);
        zzh = zzbs.zzbz().zzh(this.zzNo);
        i2 = zzg[0];
        i3 = this.zzNB + this.zzND;
        i = this.zzNC + this.zzNE;
        if (i3 < 0) {
            i3 = 0;
        } else if (this.zzrW + i3 > i2) {
            i3 = i2 - this.zzrW;
        }
        if (i < zzh[0]) {
            i = zzh[0];
        } else if (this.zzrX + i > zzh[1]) {
            i = zzh[1] - this.zzrX;
        }
        return new int[]{i3, i};
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void execute(java.util.Map<java.lang.String, java.lang.String> r13) {
        /*
        r12 = this;
        r4 = -1;
        r5 = 1;
        r3 = 0;
        r6 = r12.mLock;
        monitor-enter(r6);
        r1 = r12.zzNo;	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0011;
    L_0x000a:
        r1 = "Not an activity context. Cannot resize.";
        r12.zzan(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
    L_0x0010:
        return;
    L_0x0011:
        r1 = r12.zzJH;	 Catch:{ all -> 0x0020 }
        r1 = r1.zzam();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0023;
    L_0x0019:
        r1 = "Webview is not yet available, size is not set.";
        r12.zzan(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0020:
        r1 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        throw r1;
    L_0x0023:
        r1 = r12.zzJH;	 Catch:{ all -> 0x0020 }
        r1 = r1.zzam();	 Catch:{ all -> 0x0020 }
        r1 = r1.zzAt;	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0034;
    L_0x002d:
        r1 = "Is interstitial. Cannot resize an interstitial.";
        r12.zzan(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0034:
        r1 = r12.zzJH;	 Catch:{ all -> 0x0020 }
        r1 = r1.zziA();	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0043;
    L_0x003c:
        r1 = "Cannot resize an expanded banner.";
        r12.zzan(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0043:
        r1 = "width";
        r1 = r13.get(r1);	 Catch:{ all -> 0x0020 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0020 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0064;
    L_0x0052:
        com.google.android.gms.ads.internal.zzbs.zzbz();	 Catch:{ all -> 0x0020 }
        r1 = "width";
        r1 = r13.get(r1);	 Catch:{ all -> 0x0020 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.internal.zzagz.zzaJ(r1);	 Catch:{ all -> 0x0020 }
        r12.zzrW = r1;	 Catch:{ all -> 0x0020 }
    L_0x0064:
        r1 = "height";
        r1 = r13.get(r1);	 Catch:{ all -> 0x0020 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0020 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0083;
    L_0x0072:
        com.google.android.gms.ads.internal.zzbs.zzbz();	 Catch:{ all -> 0x0020 }
        r1 = "height";
        r1 = r13.get(r1);	 Catch:{ all -> 0x0020 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.internal.zzagz.zzaJ(r1);	 Catch:{ all -> 0x0020 }
        r12.zzrX = r1;	 Catch:{ all -> 0x0020 }
    L_0x0083:
        r1 = "offsetX";
        r1 = r13.get(r1);	 Catch:{ all -> 0x0020 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0020 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x00a2;
    L_0x0091:
        com.google.android.gms.ads.internal.zzbs.zzbz();	 Catch:{ all -> 0x0020 }
        r1 = "offsetX";
        r1 = r13.get(r1);	 Catch:{ all -> 0x0020 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.internal.zzagz.zzaJ(r1);	 Catch:{ all -> 0x0020 }
        r12.zzND = r1;	 Catch:{ all -> 0x0020 }
    L_0x00a2:
        r1 = "offsetY";
        r1 = r13.get(r1);	 Catch:{ all -> 0x0020 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0020 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x00c1;
    L_0x00b0:
        com.google.android.gms.ads.internal.zzbs.zzbz();	 Catch:{ all -> 0x0020 }
        r1 = "offsetY";
        r1 = r13.get(r1);	 Catch:{ all -> 0x0020 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.internal.zzagz.zzaJ(r1);	 Catch:{ all -> 0x0020 }
        r12.zzNE = r1;	 Catch:{ all -> 0x0020 }
    L_0x00c1:
        r1 = "allowOffscreen";
        r1 = r13.get(r1);	 Catch:{ all -> 0x0020 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0020 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x00dd;
    L_0x00cf:
        r1 = "allowOffscreen";
        r1 = r13.get(r1);	 Catch:{ all -> 0x0020 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0020 }
        r1 = java.lang.Boolean.parseBoolean(r1);	 Catch:{ all -> 0x0020 }
        r12.zzNA = r1;	 Catch:{ all -> 0x0020 }
    L_0x00dd:
        r1 = "customClosePosition";
        r1 = r13.get(r1);	 Catch:{ all -> 0x0020 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0020 }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0020 }
        if (r2 != 0) goto L_0x00ed;
    L_0x00eb:
        r12.zzNz = r1;	 Catch:{ all -> 0x0020 }
    L_0x00ed:
        r1 = r12.zzrW;	 Catch:{ all -> 0x0020 }
        if (r1 < 0) goto L_0x0100;
    L_0x00f1:
        r1 = r12.zzrX;	 Catch:{ all -> 0x0020 }
        if (r1 < 0) goto L_0x0100;
    L_0x00f5:
        r1 = r5;
    L_0x00f6:
        if (r1 != 0) goto L_0x0102;
    L_0x00f8:
        r1 = "Invalid width and height options. Cannot resize.";
        r12.zzan(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0100:
        r1 = r3;
        goto L_0x00f6;
    L_0x0102:
        r1 = r12.zzNo;	 Catch:{ all -> 0x0020 }
        r7 = r1.getWindow();	 Catch:{ all -> 0x0020 }
        if (r7 == 0) goto L_0x0110;
    L_0x010a:
        r1 = r7.getDecorView();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0118;
    L_0x0110:
        r1 = "Activity context is not ready, cannot get window or decor view.";
        r12.zzan(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0118:
        r8 = r12.zzfB();	 Catch:{ all -> 0x0020 }
        if (r8 != 0) goto L_0x0126;
    L_0x011e:
        r1 = "Resize location out of screen or close button is not visible.";
        r12.zzan(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0126:
        com.google.android.gms.internal.zzji.zzds();	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNo;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzrW;	 Catch:{ all -> 0x0020 }
        r9 = com.google.android.gms.internal.zzaiy.zzc(r1, r2);	 Catch:{ all -> 0x0020 }
        com.google.android.gms.internal.zzji.zzds();	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNo;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzrX;	 Catch:{ all -> 0x0020 }
        r10 = com.google.android.gms.internal.zzaiy.zzc(r1, r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzJH;	 Catch:{ all -> 0x0020 }
        r1 = r1.getView();	 Catch:{ all -> 0x0020 }
        r2 = r1.getParent();	 Catch:{ all -> 0x0020 }
        if (r2 == 0) goto L_0x0288;
    L_0x0148:
        r1 = r2 instanceof android.view.ViewGroup;	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0288;
    L_0x014c:
        r0 = r2;
        r0 = (android.view.ViewGroup) r0;	 Catch:{ all -> 0x0020 }
        r1 = r0;
        r11 = r12.zzJH;	 Catch:{ all -> 0x0020 }
        r11 = r11.getView();	 Catch:{ all -> 0x0020 }
        r1.removeView(r11);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNI;	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0281;
    L_0x015d:
        r2 = (android.view.ViewGroup) r2;	 Catch:{ all -> 0x0020 }
        r12.zzNK = r2;	 Catch:{ all -> 0x0020 }
        com.google.android.gms.ads.internal.zzbs.zzbz();	 Catch:{ all -> 0x0020 }
        r1 = r12.zzJH;	 Catch:{ all -> 0x0020 }
        r1 = r1.getView();	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.internal.zzagz.zzl(r1);	 Catch:{ all -> 0x0020 }
        r2 = new android.widget.ImageView;	 Catch:{ all -> 0x0020 }
        r11 = r12.zzNo;	 Catch:{ all -> 0x0020 }
        r2.<init>(r11);	 Catch:{ all -> 0x0020 }
        r12.zzNF = r2;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzNF;	 Catch:{ all -> 0x0020 }
        r2.setImageBitmap(r1);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzJH;	 Catch:{ all -> 0x0020 }
        r1 = r1.zzam();	 Catch:{ all -> 0x0020 }
        r12.zzuZ = r1;	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNK;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzNF;	 Catch:{ all -> 0x0020 }
        r1.addView(r2);	 Catch:{ all -> 0x0020 }
    L_0x018b:
        r1 = new android.widget.RelativeLayout;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzNo;	 Catch:{ all -> 0x0020 }
        r1.<init>(r2);	 Catch:{ all -> 0x0020 }
        r12.zzNJ = r1;	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNJ;	 Catch:{ all -> 0x0020 }
        r2 = 0;
        r1.setBackgroundColor(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNJ;	 Catch:{ all -> 0x0020 }
        r2 = new android.view.ViewGroup$LayoutParams;	 Catch:{ all -> 0x0020 }
        r2.<init>(r9, r10);	 Catch:{ all -> 0x0020 }
        r1.setLayoutParams(r2);	 Catch:{ all -> 0x0020 }
        com.google.android.gms.ads.internal.zzbs.zzbz();	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNJ;	 Catch:{ all -> 0x0020 }
        r2 = 0;
        r1 = com.google.android.gms.internal.zzagz.zza(r1, r9, r10, r2);	 Catch:{ all -> 0x0020 }
        r12.zzNI = r1;	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNI;	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r1.setOutsideTouchable(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNI;	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r1.setTouchable(r2);	 Catch:{ all -> 0x0020 }
        r2 = r12.zzNI;	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNA;	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0290;
    L_0x01c2:
        r1 = r5;
    L_0x01c3:
        r2.setClippingEnabled(r1);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNJ;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzJH;	 Catch:{ all -> 0x0020 }
        r2 = r2.getView();	 Catch:{ all -> 0x0020 }
        r9 = -1;
        r10 = -1;
        r1.addView(r2, r9, r10);	 Catch:{ all -> 0x0020 }
        r1 = new android.widget.LinearLayout;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzNo;	 Catch:{ all -> 0x0020 }
        r1.<init>(r2);	 Catch:{ all -> 0x0020 }
        r12.zzNG = r1;	 Catch:{ all -> 0x0020 }
        r2 = new android.widget.RelativeLayout$LayoutParams;	 Catch:{ all -> 0x0020 }
        com.google.android.gms.internal.zzji.zzds();	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNo;	 Catch:{ all -> 0x0020 }
        r9 = 50;
        r1 = com.google.android.gms.internal.zzaiy.zzc(r1, r9);	 Catch:{ all -> 0x0020 }
        com.google.android.gms.internal.zzji.zzds();	 Catch:{ all -> 0x0020 }
        r9 = r12.zzNo;	 Catch:{ all -> 0x0020 }
        r10 = 50;
        r9 = com.google.android.gms.internal.zzaiy.zzc(r9, r10);	 Catch:{ all -> 0x0020 }
        r2.<init>(r1, r9);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNz;	 Catch:{ all -> 0x0020 }
        r9 = r1.hashCode();	 Catch:{ all -> 0x0020 }
        switch(r9) {
            case -1364013995: goto L_0x02ab;
            case -1012429441: goto L_0x0293;
            case -655373719: goto L_0x02b6;
            case 1163912186: goto L_0x02cc;
            case 1288627767: goto L_0x02c1;
            case 1755462605: goto L_0x029f;
            default: goto L_0x0200;
        };	 Catch:{ all -> 0x0020 }
    L_0x0200:
        r1 = r4;
    L_0x0201:
        switch(r1) {
            case 0: goto L_0x02d7;
            case 1: goto L_0x02e3;
            case 2: goto L_0x02ef;
            case 3: goto L_0x02f6;
            case 4: goto L_0x0302;
            case 5: goto L_0x030e;
            default: goto L_0x0204;
        };	 Catch:{ all -> 0x0020 }
    L_0x0204:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 11;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
    L_0x020e:
        r1 = r12.zzNG;	 Catch:{ all -> 0x0020 }
        r3 = new com.google.android.gms.internal.zzwl;	 Catch:{ all -> 0x0020 }
        r3.<init>(r12);	 Catch:{ all -> 0x0020 }
        r1.setOnClickListener(r3);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNG;	 Catch:{ all -> 0x0020 }
        r3 = "Close button";
        r1.setContentDescription(r3);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNJ;	 Catch:{ all -> 0x0020 }
        r3 = r12.zzNG;	 Catch:{ all -> 0x0020 }
        r1.addView(r3, r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNI;	 Catch:{ RuntimeException -> 0x031a }
        r2 = r7.getDecorView();	 Catch:{ RuntimeException -> 0x031a }
        r3 = 0;
        com.google.android.gms.internal.zzji.zzds();	 Catch:{ RuntimeException -> 0x031a }
        r4 = r12.zzNo;	 Catch:{ RuntimeException -> 0x031a }
        r5 = 0;
        r5 = r8[r5];	 Catch:{ RuntimeException -> 0x031a }
        r4 = com.google.android.gms.internal.zzaiy.zzc(r4, r5);	 Catch:{ RuntimeException -> 0x031a }
        com.google.android.gms.internal.zzji.zzds();	 Catch:{ RuntimeException -> 0x031a }
        r5 = r12.zzNo;	 Catch:{ RuntimeException -> 0x031a }
        r7 = 1;
        r7 = r8[r7];	 Catch:{ RuntimeException -> 0x031a }
        r5 = com.google.android.gms.internal.zzaiy.zzc(r5, r7);	 Catch:{ RuntimeException -> 0x031a }
        r1.showAtLocation(r2, r3, r4, r5);	 Catch:{ RuntimeException -> 0x031a }
        r1 = 0;
        r1 = r8[r1];	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r2 = r8[r2];	 Catch:{ all -> 0x0020 }
        r3 = r12.zzNH;	 Catch:{ all -> 0x0020 }
        if (r3 == 0) goto L_0x025b;
    L_0x0252:
        r3 = r12.zzNH;	 Catch:{ all -> 0x0020 }
        r4 = r12.zzrW;	 Catch:{ all -> 0x0020 }
        r5 = r12.zzrX;	 Catch:{ all -> 0x0020 }
        r3.zza(r1, r2, r4, r5);	 Catch:{ all -> 0x0020 }
    L_0x025b:
        r1 = r12.zzJH;	 Catch:{ all -> 0x0020 }
        r2 = new com.google.android.gms.internal.zziv;	 Catch:{ all -> 0x0020 }
        r3 = r12.zzNo;	 Catch:{ all -> 0x0020 }
        r4 = new com.google.android.gms.ads.AdSize;	 Catch:{ all -> 0x0020 }
        r5 = r12.zzrW;	 Catch:{ all -> 0x0020 }
        r7 = r12.zzrX;	 Catch:{ all -> 0x0020 }
        r4.<init>(r5, r7);	 Catch:{ all -> 0x0020 }
        r2.<init>(r3, r4);	 Catch:{ all -> 0x0020 }
        r1.zza(r2);	 Catch:{ all -> 0x0020 }
        r1 = 0;
        r1 = r8[r1];	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r2 = r8[r2];	 Catch:{ all -> 0x0020 }
        r12.zza(r1, r2);	 Catch:{ all -> 0x0020 }
        r1 = "resized";
        r12.zzap(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0281:
        r1 = r12.zzNI;	 Catch:{ all -> 0x0020 }
        r1.dismiss();	 Catch:{ all -> 0x0020 }
        goto L_0x018b;
    L_0x0288:
        r1 = "Webview is detached, probably in the middle of a resize or expand.";
        r12.zzan(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0290:
        r1 = r3;
        goto L_0x01c3;
    L_0x0293:
        r5 = "top-left";
        r1 = r1.equals(r5);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0200;
    L_0x029c:
        r1 = r3;
        goto L_0x0201;
    L_0x029f:
        r3 = "top-center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0200;
    L_0x02a8:
        r1 = r5;
        goto L_0x0201;
    L_0x02ab:
        r3 = "center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0200;
    L_0x02b3:
        r1 = 2;
        goto L_0x0201;
    L_0x02b6:
        r3 = "bottom-left";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0200;
    L_0x02be:
        r1 = 3;
        goto L_0x0201;
    L_0x02c1:
        r3 = "bottom-center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0200;
    L_0x02c9:
        r1 = 4;
        goto L_0x0201;
    L_0x02cc:
        r3 = "bottom-right";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0200;
    L_0x02d4:
        r1 = 5;
        goto L_0x0201;
    L_0x02d7:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 9;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x020e;
    L_0x02e3:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 14;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x020e;
    L_0x02ef:
        r1 = 13;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x020e;
    L_0x02f6:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 9;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x020e;
    L_0x0302:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 14;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x020e;
    L_0x030e:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 11;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x020e;
    L_0x031a:
        r1 = move-exception;
        r2 = "Cannot show popup window: ";
        r1 = r1.getMessage();	 Catch:{ all -> 0x0020 }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x0020 }
        r3 = r1.length();	 Catch:{ all -> 0x0020 }
        if (r3 == 0) goto L_0x035d;
    L_0x032b:
        r1 = r2.concat(r1);	 Catch:{ all -> 0x0020 }
    L_0x032f:
        r12.zzan(r1);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNJ;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzJH;	 Catch:{ all -> 0x0020 }
        r2 = r2.getView();	 Catch:{ all -> 0x0020 }
        r1.removeView(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNK;	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x035a;
    L_0x0341:
        r1 = r12.zzNK;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzNF;	 Catch:{ all -> 0x0020 }
        r1.removeView(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzNK;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzJH;	 Catch:{ all -> 0x0020 }
        r2 = r2.getView();	 Catch:{ all -> 0x0020 }
        r1.addView(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzJH;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzuZ;	 Catch:{ all -> 0x0020 }
        r1.zza(r2);	 Catch:{ all -> 0x0020 }
    L_0x035a:
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x035d:
        r1 = new java.lang.String;	 Catch:{ all -> 0x0020 }
        r1.<init>(r2);	 Catch:{ all -> 0x0020 }
        goto L_0x032f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzwk.execute(java.util.Map):void");
    }

    public final void zza(int i, int i2, boolean z) {
        synchronized (this.mLock) {
            this.zzNB = i;
            this.zzNC = i2;
            if (this.zzNI != null && z) {
                int[] zzfB = zzfB();
                if (zzfB != null) {
                    PopupWindow popupWindow = this.zzNI;
                    zzji.zzds();
                    int zzc = zzaiy.zzc(this.zzNo, zzfB[0]);
                    zzji.zzds();
                    popupWindow.update(zzc, zzaiy.zzc(this.zzNo, zzfB[1]), this.zzNI.getWidth(), this.zzNI.getHeight());
                    zza(zzfB[0], zzfB[1]);
                } else {
                    zzk(true);
                }
            }
        }
    }

    public final void zzb(int i, int i2) {
        this.zzNB = i;
        this.zzNC = i2;
    }

    public final boolean zzfC() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzNI != null;
        }
        return z;
    }

    public final void zzk(boolean z) {
        synchronized (this.mLock) {
            if (this.zzNI != null) {
                this.zzNI.dismiss();
                this.zzNJ.removeView(this.zzJH.getView());
                if (this.zzNK != null) {
                    this.zzNK.removeView(this.zzNF);
                    this.zzNK.addView(this.zzJH.getView());
                    this.zzJH.zza(this.zzuZ);
                }
                if (z) {
                    zzap("default");
                    if (this.zzNH != null) {
                        this.zzNH.zzaN();
                    }
                }
                this.zzNI = null;
                this.zzNJ = null;
                this.zzNK = null;
                this.zzNG = null;
            }
        }
    }
}
