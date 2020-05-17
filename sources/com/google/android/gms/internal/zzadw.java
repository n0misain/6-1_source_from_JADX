package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbs;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Future;
import org.json.JSONObject;

@zzzn
public final class zzadw extends zzafp implements zzadv {
    private final Context mContext;
    private final Object mLock;
    private final zzafg zzQQ;
    private final long zzWC;
    private final ArrayList<Future> zzWN;
    private final ArrayList<String> zzWO;
    private final HashMap<String, zzadm> zzWP;
    private final List<zzadp> zzWQ;
    private final HashSet<String> zzWR;
    private final zzacs zzWS;

    public zzadw(Context context, zzafg zzafg, zzacs zzacs) {
        this(context, zzafg, zzacs, ((Long) zzbs.zzbL().zzd(zzmo.zzDI)).longValue());
    }

    private zzadw(Context context, zzafg zzafg, zzacs zzacs, long j) {
        this.zzWN = new ArrayList();
        this.zzWO = new ArrayList();
        this.zzWP = new HashMap();
        this.zzWQ = new ArrayList();
        this.zzWR = new HashSet();
        this.mLock = new Object();
        this.mContext = context;
        this.zzQQ = zzafg;
        this.zzWS = zzacs;
        this.zzWC = j;
    }

    private final zzaff zza(int i, @Nullable String str, @Nullable zzua zzua) {
        return new zzaff(this.zzQQ.zzUj.zzSz, null, this.zzQQ.zzXY.zzMa, i, this.zzQQ.zzXY.zzMb, this.zzQQ.zzXY.zzTq, this.zzQQ.zzXY.orientation, this.zzQQ.zzXY.zzMg, this.zzQQ.zzUj.zzSC, this.zzQQ.zzXY.zzTo, zzua, null, str, this.zzQQ.zzXN, null, this.zzQQ.zzXY.zzTp, this.zzQQ.zzvX, this.zzQQ.zzXY.zzTn, this.zzQQ.zzXR, this.zzQQ.zzXY.zzTs, this.zzQQ.zzXY.zzTt, this.zzQQ.zzXL, null, this.zzQQ.zzXY.zzTD, this.zzQQ.zzXY.zzTE, this.zzQQ.zzXY.zzTF, this.zzQQ.zzXY.zzTG, this.zzQQ.zzXY.zzTH, zzgV(), this.zzQQ.zzXY.zzMd, this.zzQQ.zzXY.zzTK, this.zzQQ.zzXX);
    }

    private final String zzgV() {
        StringBuilder stringBuilder = new StringBuilder("");
        if (this.zzWQ == null) {
            return stringBuilder.toString();
        }
        for (zzadp zzadp : this.zzWQ) {
            if (!(zzadp == null || TextUtils.isEmpty(zzadp.zzLK))) {
                int i;
                String str = zzadp.zzLK;
                switch (zzadp.errorCode) {
                    case 3:
                        i = 1;
                        break;
                    case 4:
                        i = 2;
                        break;
                    case 5:
                        i = 4;
                        break;
                    case 6:
                        i = 0;
                        break;
                    case 7:
                        i = 3;
                        break;
                    default:
                        i = 6;
                        break;
                }
                stringBuilder.append(String.valueOf(new StringBuilder(String.valueOf(str).length() + 33).append(str).append(".").append(i).append(".").append(zzadp.zzML).toString()).concat(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR));
            }
        }
        return stringBuilder.substring(0, Math.max(0, stringBuilder.length() - 1));
    }

    public final void onStop() {
    }

    public final void zza(String str, int i) {
    }

    public final void zzaw(String str) {
        synchronized (this.mLock) {
            this.zzWR.add(str);
        }
    }

    public final void zzbd() {
        zzadm zzadm;
        for (zzua zzua : this.zzQQ.zzXN.zzLY) {
            String str = zzua.zzLP;
            for (String str2 : zzua.zzLJ) {
                String string;
                String str22;
                if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str22) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str22)) {
                    try {
                        string = new JSONObject(str).getString("class_name");
                    } catch (Throwable e) {
                        zzajc.zzb("Unable to determine custom event class name, skipping...", e);
                    }
                } else {
                    string = str22;
                }
                synchronized (this.mLock) {
                    zzadz zzav = this.zzWS.zzav(string);
                    if (zzav == null || zzav.zzgX() == null || zzav.zzgW() == null) {
                        this.zzWQ.add(new zzadr().zzay(zzua.zzLK).zzax(string).zzg(0).zzw(7).zzgU());
                    } else {
                        zzafp zzadm2 = new zzadm(this.mContext, string, str, zzua, this.zzQQ, zzav, this, this.zzWC);
                        this.zzWN.add((Future) zzadm2.zzgp());
                        this.zzWO.add(string);
                        this.zzWP.put(string, zzadm2);
                    }
                }
            }
        }
        int i = 0;
        while (i < this.zzWN.size()) {
            try {
                ((Future) this.zzWN.get(i)).get();
                synchronized (this.mLock) {
                    str22 = (String) this.zzWO.get(i);
                    if (!TextUtils.isEmpty(str22)) {
                        zzadm = (zzadm) this.zzWP.get(str22);
                        if (zzadm != null) {
                            this.zzWQ.add(zzadm.zzgR());
                        }
                    }
                }
                synchronized (this.mLock) {
                    if (this.zzWR.contains(this.zzWO.get(i))) {
                        str22 = (String) this.zzWO.get(i);
                        zzaiy.zzaaH.post(new zzadx(this, zza(-2, str22, this.zzWP.get(str22) != null ? ((zzadm) this.zzWP.get(str22)).zzgS() : null)));
                        return;
                    }
                }
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                synchronized (this.mLock) {
                    str22 = (String) this.zzWO.get(i);
                    if (!TextUtils.isEmpty(str22)) {
                        zzadm = (zzadm) this.zzWP.get(str22);
                        if (zzadm != null) {
                            this.zzWQ.add(zzadm.zzgR());
                        }
                    }
                }
            } catch (Throwable e3) {
                zzajc.zzc("Unable to resolve rewarded adapter.", e3);
                synchronized (this.mLock) {
                    str22 = (String) this.zzWO.get(i);
                    if (!TextUtils.isEmpty(str22)) {
                        zzadm = (zzadm) this.zzWP.get(str22);
                        if (zzadm != null) {
                            this.zzWQ.add(zzadm.zzgR());
                        }
                    }
                }
            } catch (Throwable e32) {
                Throwable th = e32;
                synchronized (this.mLock) {
                    str22 = (String) this.zzWO.get(i);
                    if (!TextUtils.isEmpty(str22)) {
                        zzadm = (zzadm) this.zzWP.get(str22);
                        if (zzadm != null) {
                            this.zzWQ.add(zzadm.zzgR());
                        }
                    }
                }
            }
        }
        zzaiy.zzaaH.post(new zzady(this, zza(3, null, null)));
        return;
        i++;
    }
}
