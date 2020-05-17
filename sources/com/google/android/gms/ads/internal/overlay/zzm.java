package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.InputDeviceCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzakb;
import com.google.android.gms.internal.zzig;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzwy;
import com.google.android.gms.internal.zzzn;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Collections;

@zzzn
public final class zzm extends zzwy implements zzaj {
    private static int zzOF = Color.argb(0, 0, 0, 0);
    private final Activity mActivity;
    private zzaka zzJH;
    AdOverlayInfoParcel zzOG;
    private zzr zzOH;
    private zzae zzOI;
    private boolean zzOJ = false;
    private FrameLayout zzOK;
    private CustomViewCallback zzOL;
    private boolean zzOM = false;
    private boolean zzON = false;
    private zzq zzOO;
    private boolean zzOP = false;
    private int zzOQ = 0;
    private final Object zzOR = new Object();
    private Runnable zzOS;
    private boolean zzOT;
    private boolean zzOU;
    private boolean zzOV = false;
    private boolean zzOW = false;
    private boolean zzOX = true;

    public zzm(Activity activity) {
        this.mActivity = activity;
    }

    private final void zzfM() {
        if (this.mActivity.isFinishing() && !this.zzOV) {
            this.zzOV = true;
            if (this.zzJH != null) {
                this.zzJH.zzA(this.zzOQ);
                synchronized (this.zzOR) {
                    if (!this.zzOT && this.zzJH.zziI()) {
                        this.zzOS = new zzo(this);
                        zzagz.zzZr.postDelayed(this.zzOS, ((Long) zzbs.zzbL().zzd(zzmo.zzDS)).longValue());
                        return;
                    }
                }
            }
            zzfN();
        }
    }

    private final void zzfP() {
        this.zzJH.zzfP();
    }

    private final void zzr(boolean z) {
        int intValue = ((Integer) zzbs.zzbL().zzd(zzmo.zzGB)).intValue();
        zzaf zzaf = new zzaf();
        zzaf.size = 50;
        zzaf.paddingLeft = z ? intValue : 0;
        zzaf.paddingRight = z ? 0 : intValue;
        zzaf.paddingTop = 0;
        zzaf.paddingBottom = intValue;
        this.zzOI = new zzae(this.mActivity, zzaf, this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        this.zzOI.zza(z, this.zzOG.zzPj);
        this.zzOO.addView(this.zzOI, layoutParams);
    }

    private final void zzs(boolean z) throws zzp {
        if (!this.zzOU) {
            this.mActivity.requestWindowFeature(1);
        }
        Window window = this.mActivity.getWindow();
        if (window == null) {
            throw new zzp("Invalid activity, no window available.");
        }
        boolean zza;
        zzakb zziw;
        boolean zzcn;
        ViewParent parent;
        if (zzq.isAtLeastN()) {
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzGz)).booleanValue()) {
                zzbs.zzbz();
                zza = zzagz.zza(this.mActivity, this.mActivity.getResources().getConfiguration());
                Object obj = (this.zzOG.zzPo == null && this.zzOG.zzPo.zzus) ? 1 : null;
                if (!(this.zzON && obj == null) && r0) {
                    window.setFlags(1024, 1024);
                    if (((Boolean) zzbs.zzbL().zzd(zzmo.zzDT)).booleanValue() && zzq.zzsc() && this.zzOG.zzPo != null && this.zzOG.zzPo.zzux) {
                        window.getDecorView().setSystemUiVisibility(InputDeviceCompat.SOURCE_TOUCHSCREEN);
                    }
                }
                zziw = this.zzOG.zzPg.zziw();
                zzcn = zziw == null ? zziw.zzcn() : false;
                this.zzOP = false;
                if (zzcn) {
                    if (this.zzOG.orientation == zzbs.zzbB().zzhT()) {
                        this.zzOP = this.mActivity.getResources().getConfiguration().orientation != 1;
                    } else if (this.zzOG.orientation == zzbs.zzbB().zzhU()) {
                        this.zzOP = this.mActivity.getResources().getConfiguration().orientation != 2;
                    }
                }
                zzajc.zzaC("Delay onShow to next orientation change: " + this.zzOP);
                setRequestedOrientation(this.zzOG.orientation);
                if (zzbs.zzbB().zza(window)) {
                    zzajc.zzaC("Hardware acceleration on the AdActivity window enabled.");
                }
                if (this.zzON) {
                    this.zzOO.setBackgroundColor(-16777216);
                } else {
                    this.zzOO.setBackgroundColor(zzOF);
                }
                this.mActivity.setContentView(this.zzOO);
                this.zzOU = true;
                if (z) {
                    this.zzJH = this.zzOG.zzPg;
                    this.zzJH.setContext(this.mActivity);
                } else {
                    try {
                        this.zzJH = zzbs.zzbA().zza(this.mActivity, this.zzOG.zzPg.zzam(), true, zzcn, null, this.zzOG.zzvT, null, null, this.zzOG.zzPg.zzak(), zzig.zzde());
                        this.zzJH.zziw().zza(null, null, this.zzOG.zzPh, this.zzOG.zzPl, true, null, this.zzOG.zzPg.zziw().zziO(), null, null);
                        this.zzJH.zziw().zza(new zzn(this));
                        if (this.zzOG.url != null) {
                            this.zzJH.loadUrl(this.zzOG.url);
                        } else if (this.zzOG.zzPk == null) {
                            this.zzJH.loadDataWithBaseURL(this.zzOG.zzPi, this.zzOG.zzPk, "text/html", HttpRequest.CHARSET_UTF8, null);
                        } else {
                            throw new zzp("No URL or HTML to display in ad overlay.");
                        }
                        if (this.zzOG.zzPg != null) {
                            this.zzOG.zzPg.zzc(this);
                        }
                    } catch (Exception e) {
                        throw new zzp("Could not obtain webview for the overlay.");
                    }
                }
                this.zzJH.zzb(this);
                parent = this.zzJH.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.zzJH.getView());
                }
                if (this.zzON) {
                    this.zzJH.zziN();
                }
                this.zzOO.addView(this.zzJH.getView(), -1, -1);
                if (!(z || this.zzOP)) {
                    zzfP();
                }
                zzr(zzcn);
                if (this.zzJH.zzix()) {
                    zza(zzcn, true);
                }
            }
        }
        zza = true;
        if (this.zzOG.zzPo == null) {
        }
        window.setFlags(1024, 1024);
        window.getDecorView().setSystemUiVisibility(InputDeviceCompat.SOURCE_TOUCHSCREEN);
        zziw = this.zzOG.zzPg.zziw();
        if (zziw == null) {
        }
        this.zzOP = false;
        if (zzcn) {
            if (this.zzOG.orientation == zzbs.zzbB().zzhT()) {
                if (this.mActivity.getResources().getConfiguration().orientation != 1) {
                }
                this.zzOP = this.mActivity.getResources().getConfiguration().orientation != 1;
            } else if (this.zzOG.orientation == zzbs.zzbB().zzhU()) {
                if (this.mActivity.getResources().getConfiguration().orientation != 2) {
                }
                this.zzOP = this.mActivity.getResources().getConfiguration().orientation != 2;
            }
        }
        zzajc.zzaC("Delay onShow to next orientation change: " + this.zzOP);
        setRequestedOrientation(this.zzOG.orientation);
        if (zzbs.zzbB().zza(window)) {
            zzajc.zzaC("Hardware acceleration on the AdActivity window enabled.");
        }
        if (this.zzON) {
            this.zzOO.setBackgroundColor(zzOF);
        } else {
            this.zzOO.setBackgroundColor(-16777216);
        }
        this.mActivity.setContentView(this.zzOO);
        this.zzOU = true;
        if (z) {
            this.zzJH = this.zzOG.zzPg;
            this.zzJH.setContext(this.mActivity);
        } else {
            this.zzJH = zzbs.zzbA().zza(this.mActivity, this.zzOG.zzPg.zzam(), true, zzcn, null, this.zzOG.zzvT, null, null, this.zzOG.zzPg.zzak(), zzig.zzde());
            this.zzJH.zziw().zza(null, null, this.zzOG.zzPh, this.zzOG.zzPl, true, null, this.zzOG.zzPg.zziw().zziO(), null, null);
            this.zzJH.zziw().zza(new zzn(this));
            if (this.zzOG.url != null) {
                this.zzJH.loadUrl(this.zzOG.url);
            } else if (this.zzOG.zzPk == null) {
                throw new zzp("No URL or HTML to display in ad overlay.");
            } else {
                this.zzJH.loadDataWithBaseURL(this.zzOG.zzPi, this.zzOG.zzPk, "text/html", HttpRequest.CHARSET_UTF8, null);
            }
            if (this.zzOG.zzPg != null) {
                this.zzOG.zzPg.zzc(this);
            }
        }
        this.zzJH.zzb(this);
        parent = this.zzJH.getParent();
        ((ViewGroup) parent).removeView(this.zzJH.getView());
        if (this.zzON) {
            this.zzJH.zziN();
        }
        this.zzOO.addView(this.zzJH.getView(), -1, -1);
        zzfP();
        zzr(zzcn);
        if (this.zzJH.zzix()) {
            zza(zzcn, true);
        }
    }

    public final void close() {
        this.zzOQ = 2;
        this.mActivity.finish();
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
    }

    public final void onBackPressed() {
        this.zzOQ = 0;
    }

    public final void onCreate(Bundle bundle) {
        boolean z = false;
        this.mActivity.requestWindowFeature(1);
        if (bundle != null) {
            z = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.zzOM = z;
        try {
            this.zzOG = AdOverlayInfoParcel.zzb(this.mActivity.getIntent());
            if (this.zzOG == null) {
                throw new zzp("Could not get info for ad overlay.");
            }
            if (this.zzOG.zzvT.zzaaP > 7500000) {
                this.zzOQ = 3;
            }
            if (this.mActivity.getIntent() != null) {
                this.zzOX = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
            }
            if (this.zzOG.zzPo != null) {
                this.zzON = this.zzOG.zzPo.zzur;
            } else {
                this.zzON = false;
            }
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzFh)).booleanValue() && this.zzON && this.zzOG.zzPo.zzuw != -1) {
                new zzs().zzhL();
            }
            if (bundle == null) {
                if (this.zzOG.zzPf != null && this.zzOX) {
                    this.zzOG.zzPf.zzaB();
                }
                if (!(this.zzOG.zzPm == 1 || this.zzOG.zzPe == null)) {
                    this.zzOG.zzPe.onAdClicked();
                }
            }
            this.zzOO = new zzq(this.mActivity, this.zzOG.zzPn, this.zzOG.zzvT.zzaP);
            this.zzOO.setId(1000);
            switch (this.zzOG.zzPm) {
                case 1:
                    zzs(false);
                    return;
                case 2:
                    this.zzOH = new zzr(this.zzOG.zzPg);
                    zzs(false);
                    return;
                case 3:
                    zzs(true);
                    return;
                case 4:
                    if (this.zzOM) {
                        this.zzOQ = 3;
                        this.mActivity.finish();
                        return;
                    }
                    zzbs.zzbw();
                    if (!zza.zza(this.mActivity, this.zzOG.zzPd, this.zzOG.zzPl)) {
                        this.zzOQ = 3;
                        this.mActivity.finish();
                        return;
                    }
                    return;
                default:
                    throw new zzp("Could not determine ad overlay type.");
            }
        } catch (zzp e) {
            zzajc.zzaT(e.getMessage());
            this.zzOQ = 3;
            this.mActivity.finish();
        }
    }

    public final void onDestroy() {
        if (this.zzJH != null) {
            this.zzOO.removeView(this.zzJH.getView());
        }
        zzfM();
    }

    public final void onPause() {
        zzfI();
        if (this.zzOG.zzPf != null) {
            this.zzOG.zzPf.onPause();
        }
        if (!(((Boolean) zzbs.zzbL().zzd(zzmo.zzGA)).booleanValue() || this.zzJH == null || (this.mActivity.isFinishing() && this.zzOH != null))) {
            zzbs.zzbB();
            zzahe.zzk(this.zzJH);
        }
        zzfM();
    }

    public final void onRestart() {
    }

    public final void onResume() {
        if (this.zzOG != null && this.zzOG.zzPm == 4) {
            if (this.zzOM) {
                this.zzOQ = 3;
                this.mActivity.finish();
            } else {
                this.zzOM = true;
            }
        }
        if (this.zzOG.zzPf != null) {
            this.zzOG.zzPf.onResume();
        }
        if (!((Boolean) zzbs.zzbL().zzd(zzmo.zzGA)).booleanValue()) {
            if (this.zzJH == null || this.zzJH.isDestroyed()) {
                zzajc.zzaT("The webview does not exist. Ignoring action.");
                return;
            }
            zzbs.zzbB();
            zzahe.zzl(this.zzJH);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzOM);
    }

    public final void onStart() {
        if (!((Boolean) zzbs.zzbL().zzd(zzmo.zzGA)).booleanValue()) {
            return;
        }
        if (this.zzJH == null || this.zzJH.isDestroyed()) {
            zzajc.zzaT("The webview does not exist. Ignoring action.");
            return;
        }
        zzbs.zzbB();
        zzahe.zzl(this.zzJH);
    }

    public final void onStop() {
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzGA)).booleanValue() && this.zzJH != null && (!this.mActivity.isFinishing() || this.zzOH == null)) {
            zzbs.zzbB();
            zzahe.zzk(this.zzJH);
        }
        zzfM();
    }

    public final void setRequestedOrientation(int i) {
        this.mActivity.setRequestedOrientation(i);
    }

    public final void zza(View view, CustomViewCallback customViewCallback) {
        this.zzOK = new FrameLayout(this.mActivity);
        this.zzOK.setBackgroundColor(-16777216);
        this.zzOK.addView(view, -1, -1);
        this.mActivity.setContentView(this.zzOK);
        this.zzOU = true;
        this.zzOL = customViewCallback;
        this.zzOJ = true;
    }

    public final void zza(boolean z, boolean z2) {
        if (this.zzOI != null) {
            this.zzOI.zza(z, z2);
        }
    }

    public final void zzaa() {
        this.zzOU = true;
    }

    public final void zzfI() {
        if (this.zzOG != null && this.zzOJ) {
            setRequestedOrientation(this.zzOG.orientation);
        }
        if (this.zzOK != null) {
            this.mActivity.setContentView(this.zzOO);
            this.zzOU = true;
            this.zzOK.removeAllViews();
            this.zzOK = null;
        }
        if (this.zzOL != null) {
            this.zzOL.onCustomViewHidden();
            this.zzOL = null;
        }
        this.zzOJ = false;
    }

    public final void zzfJ() {
        this.zzOQ = 1;
        this.mActivity.finish();
    }

    public final boolean zzfK() {
        this.zzOQ = 0;
        if (this.zzJH == null) {
            return true;
        }
        boolean zziC = this.zzJH.zziC();
        if (zziC) {
            return zziC;
        }
        this.zzJH.zza("onbackblocked", Collections.emptyMap());
        return zziC;
    }

    public final void zzfL() {
        this.zzOO.removeView(this.zzOI);
        zzr(true);
    }

    final void zzfN() {
        if (!this.zzOW) {
            this.zzOW = true;
            if (this.zzJH != null) {
                this.zzOO.removeView(this.zzJH.getView());
                if (this.zzOH != null) {
                    this.zzJH.setContext(this.zzOH.zzqD);
                    this.zzJH.zzA(false);
                    this.zzOH.parent.addView(this.zzJH.getView(), this.zzOH.index, this.zzOH.zzPa);
                    this.zzOH = null;
                } else if (this.mActivity.getApplicationContext() != null) {
                    this.zzJH.setContext(this.mActivity.getApplicationContext());
                }
                this.zzJH = null;
            }
            if (this.zzOG != null && this.zzOG.zzPf != null) {
                this.zzOG.zzPf.zzaA();
            }
        }
    }

    public final void zzfO() {
        if (this.zzOP) {
            this.zzOP = false;
            zzfP();
        }
    }

    public final void zzfQ() {
        this.zzOO.zzOZ = true;
    }

    public final void zzfR() {
        synchronized (this.zzOR) {
            this.zzOT = true;
            if (this.zzOS != null) {
                zzagz.zzZr.removeCallbacks(this.zzOS);
                zzagz.zzZr.post(this.zzOS);
            }
        }
    }

    public final void zzo(IObjectWrapper iObjectWrapper) {
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzGz)).booleanValue() && zzq.isAtLeastN()) {
            Configuration configuration = (Configuration) zzn.zzE(iObjectWrapper);
            zzbs.zzbz();
            if (zzagz.zza(this.mActivity, configuration)) {
                this.mActivity.getWindow().addFlags(1024);
                this.mActivity.getWindow().clearFlags(2048);
                return;
            }
            this.mActivity.getWindow().addFlags(2048);
            this.mActivity.getWindow().clearFlags(1024);
        }
    }
}
