package com.google.android.gms.internal;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class pg {
    private static long zzcbG = 0;
    private final wl zzbZE;
    private final ScheduledExecutorService zzbZs;
    private pk zzcbH;
    private boolean zzcbI = false;
    private boolean zzcbJ = false;
    private long zzcbK = 0;
    private pt zzcbL;
    private pj zzcbM;
    private ScheduledFuture<?> zzcbN;
    private ScheduledFuture<?> zzcbO;
    private final oj zzcbP;

    public pg(oj ojVar, ol olVar, String str, pj pjVar, String str2) {
        this.zzcbP = ojVar;
        this.zzbZs = ojVar.zzFV();
        this.zzcbM = pjVar;
        long j = zzcbG;
        zzcbG = 1 + j;
        this.zzbZE = new wl(ojVar.zzFT(), "WebSocket", "ws_" + j);
        if (str == null) {
            str = olVar.getHost();
        }
        boolean isSecure = olVar.isSecure();
        String namespace = olVar.getNamespace();
        String str3 = isSecure ? "wss" : "ws";
        String valueOf = String.valueOf("v");
        String valueOf2 = String.valueOf("5");
        str3 = new StringBuilder(((((String.valueOf(str3).length() + 13) + String.valueOf(str).length()) + String.valueOf(namespace).length()) + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length()).append(str3).append("://").append(str).append("/.ws?ns=").append(namespace).append("&").append(valueOf).append("=").append(valueOf2).toString();
        if (str2 != null) {
            str3 = String.valueOf(str3);
            namespace = String.valueOf("&ls=");
            str3 = new StringBuilder((String.valueOf(str3).length() + String.valueOf(namespace).length()) + String.valueOf(str2).length()).append(str3).append(namespace).append(str2).toString();
        }
        URI create = URI.create(str3);
        Map hashMap = new HashMap();
        hashMap.put("User-Agent", this.zzcbP.zzht());
        this.zzcbH = new pl(this, new yd(create, null, hashMap), null);
    }

    private final void shutdown() {
        this.zzcbJ = true;
        this.zzcbM.zzaA(this.zzcbI);
    }

    private final void zzGv() {
        if (!this.zzcbJ) {
            if (this.zzcbN != null) {
                this.zzcbN.cancel(false);
                if (this.zzbZE.zzIH()) {
                    this.zzbZE.zzb("Reset keepAlive. Remaining: " + this.zzcbN.getDelay(TimeUnit.MILLISECONDS), null, new Object[0]);
                }
            } else if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb("Reset keepAlive", null, new Object[0]);
            }
            this.zzcbN = this.zzbZs.schedule(new pi(this), 45000, TimeUnit.MILLISECONDS);
        }
    }

    private final void zzGw() {
        if (!this.zzcbJ) {
            if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb("closing itself", null, new Object[0]);
            }
            shutdown();
        }
        this.zzcbH = null;
        if (this.zzcbN != null) {
            this.zzcbN.cancel(false);
        }
    }

    private final void zzGx() {
        if (!this.zzcbI && !this.zzcbJ) {
            if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb("timed out on connect", null, new Object[0]);
            }
            this.zzcbH.close();
        }
    }

    private final void zzbV(int i) {
        this.zzcbK = (long) i;
        this.zzcbL = new pt();
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb("HandleNewFrameCount: " + this.zzcbK, null, new Object[0]);
        }
    }

    private final void zzgJ(String str) {
        Throwable th;
        wl wlVar;
        String str2;
        String valueOf;
        this.zzcbL.zzgN(str);
        this.zzcbK--;
        if (this.zzcbK == 0) {
            try {
                this.zzcbL.zzGD();
                Map zzgV = yr.zzgV(this.zzcbL.toString());
                this.zzcbL = null;
                if (this.zzbZE.zzIH()) {
                    wl wlVar2 = this.zzbZE;
                    String valueOf2 = String.valueOf(zzgV);
                    wlVar2.zzb(new StringBuilder(String.valueOf(valueOf2).length() + 36).append("handleIncomingFrame complete frame: ").append(valueOf2).toString(), null, new Object[0]);
                }
                this.zzcbM.zzz(zzgV);
            } catch (Throwable e) {
                th = e;
                wlVar = this.zzbZE;
                str2 = "Error parsing frame: ";
                valueOf = String.valueOf(this.zzcbL.toString());
                wlVar.zzd(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
                close();
                shutdown();
            } catch (Throwable e2) {
                th = e2;
                wlVar = this.zzbZE;
                str2 = "Error parsing frame (cast error): ";
                valueOf = String.valueOf(this.zzcbL.toString());
                wlVar.zzd(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
                close();
                shutdown();
            }
        }
    }

    private final String zzgK(String str) {
        if (str.length() <= 6) {
            try {
                int parseInt = Integer.parseInt(str);
                if (parseInt > 0) {
                    zzbV(parseInt);
                }
                return null;
            } catch (NumberFormatException e) {
            }
        }
        zzbV(1);
        return str;
    }

    private final void zzgL(String str) {
        if (!this.zzcbJ) {
            zzGv();
            if ((this.zzcbL != null ? 1 : null) != null) {
                zzgJ(str);
                return;
            }
            String zzgK = zzgK(str);
            if (zzgK != null) {
                zzgJ(zzgK);
            }
        }
    }

    public final void close() {
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb("websocket is being closed", null, new Object[0]);
        }
        this.zzcbJ = true;
        this.zzcbH.close();
        if (this.zzcbO != null) {
            this.zzcbO.cancel(true);
        }
        if (this.zzcbN != null) {
            this.zzcbN.cancel(true);
        }
    }

    public final void open() {
        this.zzcbH.connect();
        this.zzcbO = this.zzbZs.schedule(new ph(this), 30000, TimeUnit.MILLISECONDS);
    }

    public final void send(Map<String, Object> map) {
        zzGv();
        String str;
        try {
            String[] strArr;
            int i;
            String zzak = yr.zzak(map);
            if (zzak.length() <= 16384) {
                strArr = new String[]{zzak};
            } else {
                ArrayList arrayList = new ArrayList();
                for (i = 0; i < zzak.length(); i += 16384) {
                    arrayList.add(zzak.substring(i, Math.min(i + 16384, zzak.length())));
                }
                strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            if (strArr.length > 1) {
                this.zzcbH.zzgM(strArr.length);
            }
            for (String str2 : strArr) {
                this.zzcbH.zzgM(str2);
            }
        } catch (Throwable e) {
            Throwable th = e;
            wl wlVar = this.zzbZE;
            str2 = "Failed to serialize message: ";
            String valueOf = String.valueOf(map.toString());
            wlVar.zzd(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            shutdown();
        }
    }
}
