package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.ads.internal.zzbs;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zzcq implements zzcp {
    protected MotionEvent zzpP;
    protected LinkedList<MotionEvent> zzpQ = new LinkedList();
    protected long zzpR = 0;
    protected long zzpS = 0;
    protected long zzpT = 0;
    protected long zzpU = 0;
    protected long zzpV = 0;
    protected long zzpW = 0;
    protected long zzpX = 0;
    protected double zzpY;
    private double zzpZ;
    private double zzqa;
    protected float zzqb;
    protected float zzqc;
    protected float zzqd;
    protected float zzqe;
    private boolean zzqf = false;
    protected boolean zzqg = false;
    protected DisplayMetrics zzqh;

    protected zzcq(Context context) {
        try {
            zzbv.zzw();
            this.zzqh = context.getResources().getDisplayMetrics();
        } catch (Throwable th) {
        }
    }

    private final String zza(Context context, String str, boolean z, View view, byte[] bArr) {
        zzau zzau;
        zzax zza;
        if (bArr != null && bArr.length > 0) {
            try {
                zzau = (zzau) adp.zza(new zzau(), bArr);
            } catch (ado e) {
            }
            if (z) {
                zza = zza(context, zzau);
            } else {
                try {
                    zzax zza2 = zza(context, view);
                    this.zzqf = true;
                    zza = zza2;
                } catch (NoSuchAlgorithmException e2) {
                    return Integer.toString(7);
                } catch (UnsupportedEncodingException e3) {
                    return Integer.toString(7);
                } catch (Throwable th) {
                    return Integer.toString(3);
                }
            }
            if (zza != null || zza.zzLV() == 0) {
                return Integer.toString(5);
            }
            Object obj = (!((Boolean) zzbs.zzbL().zzd(zzmo.zzEO)).booleanValue() || z) ? 1 : null;
            return zzbv.zza(zza, str, obj == null);
        }
        zzau = null;
        if (z) {
            zza = zza(context, zzau);
        } else {
            zzax zza22 = zza(context, view);
            this.zzqf = true;
            zza = zza22;
        }
        if (zza != null) {
        }
        return Integer.toString(5);
    }

    protected abstract long zza(StackTraceElement[] stackTraceElementArr) throws zzcy;

    protected abstract zzax zza(Context context, View view);

    protected abstract zzax zza(Context context, zzau zzau);

    public final String zza(Context context) {
        if (zzdg.zzS()) {
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzFb)).booleanValue()) {
                throw new IllegalStateException("The caller must not be called from the UI thread.");
            }
        }
        return zza(context, null, false, null, null);
    }

    public final String zza(Context context, String str) {
        return zza(context, str, null);
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, true, view, null);
    }

    public final String zza(Context context, byte[] bArr) {
        if (zzdg.zzS()) {
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzFb)).booleanValue()) {
                throw new IllegalStateException("The caller must not be called from the UI thread.");
            }
        }
        return zza(context, null, false, null, bArr);
    }

    public final void zza(int i, int i2, int i3) {
        if (this.zzpP != null) {
            this.zzpP.recycle();
        }
        if (this.zzqh != null) {
            this.zzpP = MotionEvent.obtain(0, (long) i3, 1, ((float) i) * this.zzqh.density, ((float) i2) * this.zzqh.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            this.zzpP = null;
        }
        this.zzqg = false;
    }

    public final void zza(MotionEvent motionEvent) {
        if (this.zzqf) {
            this.zzpU = 0;
            this.zzpT = 0;
            this.zzpS = 0;
            this.zzpR = 0;
            this.zzpV = 0;
            this.zzpX = 0;
            this.zzpW = 0;
            Iterator it = this.zzpQ.iterator();
            while (it.hasNext()) {
                ((MotionEvent) it.next()).recycle();
            }
            this.zzpQ.clear();
            this.zzpP = null;
            this.zzqf = false;
        }
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzEU)).booleanValue()) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.zzpY = 0.0d;
                    this.zzpZ = (double) motionEvent.getRawX();
                    this.zzqa = (double) motionEvent.getRawY();
                    break;
                case 1:
                case 2:
                    double rawX = (double) motionEvent.getRawX();
                    double rawY = (double) motionEvent.getRawY();
                    double d = rawX - this.zzpZ;
                    double d2 = rawY - this.zzqa;
                    this.zzpY = Math.sqrt((d * d) + (d2 * d2)) + this.zzpY;
                    this.zzpZ = rawX;
                    this.zzqa = rawY;
                    break;
            }
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (((Boolean) zzbs.zzbL().zzd(zzmo.zzEV)).booleanValue()) {
                    this.zzqb = motionEvent.getX();
                    this.zzqc = motionEvent.getY();
                    this.zzqd = motionEvent.getRawX();
                    this.zzqe = motionEvent.getRawY();
                }
                this.zzpR++;
                break;
            case 1:
                this.zzpP = MotionEvent.obtain(motionEvent);
                this.zzpQ.add(this.zzpP);
                if (this.zzpQ.size() > 6) {
                    ((MotionEvent) this.zzpQ.remove()).recycle();
                }
                this.zzpT++;
                try {
                    this.zzpV = zza(new Throwable().getStackTrace());
                    break;
                } catch (zzcy e) {
                    break;
                }
            case 2:
                this.zzpS += (long) (motionEvent.getHistorySize() + 1);
                try {
                    zzdf zzb = zzb(motionEvent);
                    Object obj = (zzb == null || zzb.zzcd == null || zzb.zzrd == null) ? null : 1;
                    if (obj != null) {
                        this.zzpW += zzb.zzcd.longValue() + zzb.zzrd.longValue();
                    }
                    obj = (this.zzqh == null || zzb == null || zzb.zzcb == null || zzb.zzre == null) ? null : 1;
                    if (obj != null) {
                        this.zzpX = (zzb.zzre.longValue() + zzb.zzcb.longValue()) + this.zzpX;
                        break;
                    }
                } catch (zzcy e2) {
                    break;
                }
                break;
            case 3:
                this.zzpU++;
                break;
        }
        this.zzqg = true;
    }

    protected abstract zzdf zzb(MotionEvent motionEvent) throws zzcy;
}
