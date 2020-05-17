package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

final class od implements pj {
    private static long zzcaq = 0;
    private final wl zzbZE;
    private ol zzcar;
    private pg zzcas;
    private oe zzcat;
    private int zzcau = og.zzcay;

    public od(oj ojVar, ol olVar, String str, oe oeVar, String str2) {
        long j = zzcaq;
        zzcaq = 1 + j;
        this.zzcar = olVar;
        this.zzcat = oeVar;
        this.zzbZE = new wl(ojVar.zzFT(), "Connection", "conn_" + j);
        this.zzcas = new pg(ojVar, olVar, str, this, str2);
    }

    private final void zza(of ofVar) {
        if (this.zzcau != og.zzcaA) {
            if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb("closing realtime connection", null, new Object[0]);
            }
            this.zzcau = og.zzcaA;
            if (this.zzcas != null) {
                this.zzcas.close();
                this.zzcas = null;
            }
            this.zzcat.zzb(ofVar);
        }
    }

    public final void close() {
        zza(of.OTHER);
    }

    public final void open() {
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb("Opening a connection", null, new Object[0]);
        }
        this.zzcas.open();
    }

    public final void zza(Map<String, Object> map, boolean z) {
        Map hashMap = new HashMap();
        hashMap.put("t", "d");
        hashMap.put("d", map);
        if (this.zzcau != og.zzcaz) {
            this.zzbZE.zzb("Tried to send on an unconnected connection", null, new Object[0]);
            return;
        }
        if (z) {
            this.zzbZE.zzb("Sending data (contents hidden)", null, new Object[0]);
        } else {
            this.zzbZE.zzb("Sending data: %s", null, hashMap);
        }
        this.zzcas.send(hashMap);
    }

    public final void zzaA(boolean z) {
        this.zzcas = null;
        if (z || this.zzcau != og.zzcay) {
            if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb("Realtime connection lost", null, new Object[0]);
            }
        } else if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb("Realtime connection failed", null, new Object[0]);
        }
        zza(of.OTHER);
    }

    public final void zzz(Map<String, Object> map) {
        String str;
        wl wlVar;
        String str2;
        try {
            str = (String) map.get("t");
            if (str == null) {
                if (this.zzbZE.zzIH()) {
                    wlVar = this.zzbZE;
                    str2 = "Failed to parse server message: missing message type:";
                    str = String.valueOf(map.toString());
                    wlVar.zzb(str.length() != 0 ? str2.concat(str) : new String(str2), null, new Object[0]);
                }
                zza(of.OTHER);
            } else if (str.equals("d")) {
                r0 = (Map) map.get("d");
                if (this.zzbZE.zzIH()) {
                    r2 = this.zzbZE;
                    r3 = "received data message: ";
                    r1 = String.valueOf(r0.toString());
                    r2.zzb(r1.length() != 0 ? r3.concat(r1) : new String(r3), null, new Object[0]);
                }
                this.zzcat.zzA(r0);
            } else if (str.equals("c")) {
                r0 = (Map) map.get("d");
                if (this.zzbZE.zzIH()) {
                    r2 = this.zzbZE;
                    r3 = "Got control message: ";
                    r1 = String.valueOf(r0.toString());
                    r2.zzb(r1.length() != 0 ? r3.concat(r1) : new String(r3), null, new Object[0]);
                }
                try {
                    r1 = (String) r0.get("t");
                    if (r1 == null) {
                        if (this.zzbZE.zzIH()) {
                            wlVar = this.zzbZE;
                            str2 = "Got invalid control message: ";
                            str = String.valueOf(r0.toString());
                            wlVar.zzb(str.length() != 0 ? str2.concat(str) : new String(str2), null, new Object[0]);
                        }
                        zza(of.OTHER);
                    } else if (r1.equals("s")) {
                        str = (String) r0.get("d");
                        if (this.zzbZE.zzIH()) {
                            this.zzbZE.zzb("Connection shutdown command received. Shutting down...", null, new Object[0]);
                        }
                        this.zzcat.zzgE(str);
                        zza(of.OTHER);
                    } else if (r1.equals("r")) {
                        str = (String) r0.get("d");
                        if (this.zzbZE.zzIH()) {
                            wlVar = this.zzbZE;
                            str2 = String.valueOf(this.zzcar.getHost());
                            wlVar.zzb(new StringBuilder((String.valueOf(str2).length() + 62) + String.valueOf(str).length()).append("Got a reset; killing connection to ").append(str2).append("; Updating internalHost to ").append(str).toString(), null, new Object[0]);
                        }
                        this.zzcat.zzgD(str);
                        zza(of.SERVER_RESET);
                    } else if (r1.equals("h")) {
                        r0 = (Map) r0.get("d");
                        long longValue = ((Long) r0.get("ts")).longValue();
                        this.zzcat.zzgD((String) r0.get("h"));
                        str = (String) r0.get("s");
                        if (this.zzcau == og.zzcay) {
                            if (this.zzbZE.zzIH()) {
                                this.zzbZE.zzb("realtime connection established", null, new Object[0]);
                            }
                            this.zzcau = og.zzcaz;
                            this.zzcat.zzc(longValue, str);
                        }
                    } else if (this.zzbZE.zzIH()) {
                        r2 = this.zzbZE;
                        r3 = "Ignoring unknown control message: ";
                        str = String.valueOf(r1);
                        r2.zzb(str.length() != 0 ? r3.concat(str) : new String(r3), null, new Object[0]);
                    }
                } catch (ClassCastException e) {
                    if (this.zzbZE.zzIH()) {
                        wlVar = this.zzbZE;
                        str2 = "Failed to parse control message: ";
                        str = String.valueOf(e.toString());
                        wlVar.zzb(str.length() != 0 ? str2.concat(str) : new String(str2), null, new Object[0]);
                    }
                    zza(of.OTHER);
                }
            } else if (this.zzbZE.zzIH()) {
                wlVar = this.zzbZE;
                str2 = "Ignoring unknown server message type: ";
                str = String.valueOf(str);
                wlVar.zzb(str.length() != 0 ? str2.concat(str) : new String(str2), null, new Object[0]);
            }
        } catch (ClassCastException e2) {
            if (this.zzbZE.zzIH()) {
                wlVar = this.zzbZE;
                str2 = "Failed to parse server message: ";
                str = String.valueOf(e2.toString());
                wlVar.zzb(str.length() != 0 ? str2.concat(str) : new String(str2), null, new Object[0]);
            }
            zza(of.OTHER);
        }
    }
}
