package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class op implements oe, on {
    private static long zzcaq = 0;
    private final wl zzbZE;
    private final ScheduledExecutorService zzbZs;
    private final oh zzcaC;
    private final oo zzcaH;
    private String zzcaI;
    private HashSet<String> zzcaJ = new HashSet();
    private boolean zzcaK = true;
    private long zzcaL;
    private od zzcaM;
    private oz zzcaN = oz.Disconnected;
    private long zzcaO = 0;
    private long zzcaP = 0;
    private Map<Long, oy> zzcaQ;
    private List<pb> zzcaR;
    private Map<Long, pd> zzcaS;
    private Map<pa, pc> zzcaT;
    private String zzcaU;
    private boolean zzcaV;
    private final oj zzcaW;
    private final pq zzcaX;
    private String zzcaY;
    private long zzcaZ = 0;
    private final ol zzcar;
    private int zzcba = 0;
    private ScheduledFuture<?> zzcbb = null;
    private long zzcbc;
    private boolean zzcbd;

    public op(oj ojVar, ol olVar, oo ooVar) {
        this.zzcaH = ooVar;
        this.zzcaW = ojVar;
        this.zzbZs = ojVar.zzFV();
        this.zzcaC = ojVar.zzFU();
        this.zzcar = olVar;
        this.zzcaT = new HashMap();
        this.zzcaQ = new HashMap();
        this.zzcaS = new HashMap();
        this.zzcaR = new ArrayList();
        this.zzcaX = new ps(this.zzbZs, ojVar.zzFT(), "ConnectionRetryHelper").zzas(1000).zzh(1.3d).zzat(30000).zzi(0.7d).zzGC();
        long j = zzcaq;
        zzcaq = 1 + j;
        this.zzbZE = new wl(ojVar.zzFT(), "PersistentConnection", "pc_" + j);
        this.zzcaY = null;
        zzGi();
    }

    private final boolean isIdle() {
        return this.zzcaT.isEmpty() && this.zzcaQ.isEmpty() && !this.zzcbd && this.zzcaS.isEmpty();
    }

    private final boolean zzGc() {
        return this.zzcaN == oz.Authenticating || this.zzcaN == oz.Connected;
    }

    private final boolean zzGd() {
        return this.zzcaN == oz.Connected;
    }

    private final boolean zzGe() {
        return this.zzcaJ.size() == 0;
    }

    private final void zzGf() {
        if (zzGe()) {
            ok.zzc(this.zzcaN == oz.Disconnected, "Not in disconnected state: %s", this.zzcaN);
            boolean z = this.zzcaV;
            this.zzbZE.zzb("Scheduling connection attempt", null, new Object[0]);
            this.zzcaV = false;
            this.zzcaX.zzp(new oq(this, z));
        }
    }

    private final void zzGg() {
        List arrayList = new ArrayList();
        Iterator it = this.zzcaS.entrySet().iterator();
        while (it.hasNext()) {
            pd pdVar = (pd) ((Entry) it.next()).getValue();
            if (pdVar.zzGp().containsKey("h") && pdVar.zzGr()) {
                arrayList.add(pdVar);
                it.remove();
            }
        }
        ArrayList arrayList2 = (ArrayList) arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            ((pd) obj).zzGl().zzaa("disconnected", null);
        }
    }

    private final void zzGh() {
        int i = 0;
        ok.zzc(this.zzcaN == oz.Connected, "Should be connected if we're restoring state, but we are: %s", this.zzcaN);
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb("Restoring outstanding listens", null, new Object[0]);
        }
        for (pc pcVar : this.zzcaT.values()) {
            if (this.zzbZE.zzIH()) {
                wl wlVar = this.zzbZE;
                String valueOf = String.valueOf(pcVar.zzGm());
                wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 17).append("Restoring listen ").append(valueOf).toString(), null, new Object[0]);
            }
            zza(pcVar);
        }
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb("Restoring writes.", null, new Object[0]);
        }
        List arrayList = new ArrayList(this.zzcaS.keySet());
        Collections.sort(arrayList);
        ArrayList arrayList2 = (ArrayList) arrayList;
        int size = arrayList2.size();
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            zzap(((Long) obj).longValue());
        }
        for (pb pbVar : this.zzcaR) {
            zza(pbVar.getAction(), pbVar.zzGk(), pbVar.getData(), pbVar.zzGl());
        }
        this.zzcaR.clear();
    }

    private final void zzGi() {
        if (isIdle()) {
            if (this.zzcbb != null) {
                this.zzcbb.cancel(false);
            }
            this.zzcbb = this.zzbZs.schedule(new ox(this), 60000, TimeUnit.MILLISECONDS);
        } else if (isInterrupted("connection_idle")) {
            ok.zzc(!isIdle(), "", new Object[0]);
            resume("connection_idle");
        }
    }

    private final boolean zzGj() {
        return isIdle() && System.currentTimeMillis() > this.zzcbc + 60000;
    }

    private final void zzS(List<String> list) {
        if (this.zzbZE.zzIH()) {
            wl wlVar = this.zzbZE;
            String valueOf = String.valueOf(list);
            wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 29).append("removing all listens at path ").append(valueOf).toString(), null, new Object[0]);
        }
        List arrayList = new ArrayList();
        for (Entry entry : this.zzcaT.entrySet()) {
            pa paVar = (pa) entry.getKey();
            pc pcVar = (pc) entry.getValue();
            if (paVar.zzcbt.equals(list)) {
                arrayList.add(pcVar);
            }
        }
        ArrayList arrayList2 = (ArrayList) arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            this.zzcaT.remove(((pc) obj).zzGm());
        }
        zzGi();
        ArrayList arrayList3 = (ArrayList) arrayList;
        i = arrayList3.size();
        int i2 = 0;
        while (i2 < i) {
            Object obj2 = arrayList3.get(i2);
            i2++;
            ((pc) obj2).zzcbx.zzaa("permission_denied", null);
        }
    }

    private final pc zza(pa paVar) {
        if (this.zzbZE.zzIH()) {
            wl wlVar = this.zzbZE;
            String valueOf = String.valueOf(paVar);
            wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 15).append("removing query ").append(valueOf).toString(), null, new Object[0]);
        }
        if (this.zzcaT.containsKey(paVar)) {
            pc pcVar = (pc) this.zzcaT.get(paVar);
            this.zzcaT.remove(paVar);
            zzGi();
            return pcVar;
        } else if (!this.zzbZE.zzIH()) {
            return null;
        } else {
            wlVar = this.zzbZE;
            valueOf = String.valueOf(paVar);
            wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 64).append("Trying to remove listener for QuerySpec ").append(valueOf).append(" but no listener exists.").toString(), null, new Object[0]);
            return null;
        }
    }

    private final void zza(pc pcVar) {
        Map hashMap = new HashMap();
        hashMap.put("p", ok.zzR(pcVar.zzGm().zzcbt));
        Long zzGn = pcVar.zzGn();
        if (zzGn != null) {
            hashMap.put("q", pcVar.zzcby.zzcbu);
            hashMap.put("t", zzGn);
        }
        om zzGo = pcVar.zzGo();
        hashMap.put("h", zzGo.zzFY());
        if (zzGo.zzFZ()) {
            oc zzGa = zzGo.zzGa();
            List arrayList = new ArrayList();
            for (List zzR : zzGa.zzFR()) {
                arrayList.add(ok.zzR(zzR));
            }
            Map hashMap2 = new HashMap();
            hashMap2.put("hs", zzGa.zzFS());
            hashMap2.put("ps", arrayList);
            hashMap.put("ch", hashMap2);
        }
        zza("q", hashMap, new ov(this, pcVar));
    }

    private final void zza(String str, List<String> list, Object obj, pf pfVar) {
        Map hashMap = new HashMap();
        hashMap.put("p", ok.zzR(list));
        hashMap.put("d", obj);
        zza(str, hashMap, new os(this, pfVar));
    }

    private final void zza(String str, List<String> list, Object obj, String str2, pf pfVar) {
        Map hashMap = new HashMap();
        hashMap.put("p", ok.zzR(list));
        hashMap.put("d", obj);
        if (str2 != null) {
            hashMap.put("h", str2);
        }
        long j = this.zzcaO;
        this.zzcaO = 1 + j;
        this.zzcaS.put(Long.valueOf(j), new pd(str, hashMap, pfVar));
        if (zzGd()) {
            zzap(j);
        }
        this.zzcbc = System.currentTimeMillis();
        zzGi();
    }

    private final void zza(String str, Map<String, Object> map, oy oyVar) {
        zza(str, false, (Map) map, oyVar);
    }

    private final void zza(String str, boolean z, Map<String, Object> map, oy oyVar) {
        long j = this.zzcaP;
        this.zzcaP = 1 + j;
        Map hashMap = new HashMap();
        hashMap.put("r", Long.valueOf(j));
        hashMap.put("a", str);
        hashMap.put("b", map);
        this.zzcaM.zza(hashMap, z);
        this.zzcaQ.put(Long.valueOf(j), oyVar);
    }

    private final void zza(List<String> list, pa paVar) {
        if (list.contains("no_index")) {
            String valueOf = String.valueOf(paVar.zzcbu.get("i"));
            valueOf = new StringBuilder(String.valueOf(valueOf).length() + 14).append("\".indexOn\": \"").append(valueOf).append("\"").toString();
            wl wlVar = this.zzbZE;
            String valueOf2 = String.valueOf(ok.zzR(paVar.zzcbt));
            wlVar.zze(new StringBuilder((String.valueOf(valueOf).length() + 118) + String.valueOf(valueOf2).length()).append("Using an unspecified index. Consider adding '").append(valueOf).append("' at ").append(valueOf2).append(" to your security and Firebase Database rules for better performance").toString(), null);
        }
    }

    private final void zzaC(boolean z) {
        ok.zzc(zzGc(), "Must be connected to send auth, but was: %s", this.zzcaN);
        ok.zzc(this.zzcaU != null, "Auth token must be set to authenticate!", new Object[0]);
        oy otVar = new ot(this, z);
        Map hashMap = new HashMap();
        yq zzgU = yq.zzgU(this.zzcaU);
        if (zzgU != null) {
            hashMap.put("cred", zzgU.getToken());
            if (zzgU.zzJE() != null) {
                hashMap.put("authvar", zzgU.zzJE());
            }
            zza("gauth", true, hashMap, otVar);
            return;
        }
        hashMap.put("cred", this.zzcaU);
        zza("auth", true, hashMap, otVar);
    }

    private final void zzap(long j) {
        pd pdVar = (pd) this.zzcaS.get(Long.valueOf(j));
        pf zzGl = pdVar.zzGl();
        String action = pdVar.getAction();
        pdVar.zzGq();
        zza(action, pdVar.zzGp(), new ou(this, action, j, pdVar, zzGl));
    }

    public final void initialize() {
        zzGf();
    }

    public final void interrupt(String str) {
        if (this.zzbZE.zzIH()) {
            wl wlVar = this.zzbZE;
            String str2 = "Connection interrupted for: ";
            String valueOf = String.valueOf(str);
            wlVar.zzb(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), null, new Object[0]);
        }
        this.zzcaJ.add(str);
        if (this.zzcaM != null) {
            this.zzcaM.close();
            this.zzcaM = null;
        } else {
            this.zzcaX.cancel();
            this.zzcaN = oz.Disconnected;
        }
        this.zzcaX.zzGA();
    }

    public final boolean isInterrupted(String str) {
        return this.zzcaJ.contains(str);
    }

    public final void purgeOutstandingWrites() {
        for (pd pdVar : this.zzcaS.values()) {
            if (pdVar.zzcbw != null) {
                pdVar.zzcbw.zzaa("write_canceled", null);
            }
        }
        for (pb pbVar : this.zzcaR) {
            if (pbVar.zzcbw != null) {
                pbVar.zzcbw.zzaa("write_canceled", null);
            }
        }
        this.zzcaS.clear();
        this.zzcaR.clear();
        if (!zzGc()) {
            this.zzcbd = false;
        }
        zzGi();
    }

    public final void refreshAuthToken() {
        this.zzbZE.zzb("Auth token refresh requested", null, new Object[0]);
        interrupt("token_refresh");
        resume("token_refresh");
    }

    public final void resume(String str) {
        if (this.zzbZE.zzIH()) {
            wl wlVar = this.zzbZE;
            String str2 = "Connection no longer interrupted for: ";
            String valueOf = String.valueOf(str);
            wlVar.zzb(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), null, new Object[0]);
        }
        this.zzcaJ.remove(str);
        if (zzGe() && this.zzcaN == oz.Disconnected) {
            zzGf();
        }
    }

    public final void shutdown() {
        interrupt("shutdown");
    }

    public final void zzA(Map<String, Object> map) {
        if (map.containsKey("r")) {
            oy oyVar = (oy) this.zzcaQ.remove(Long.valueOf((long) ((Integer) map.get("r")).intValue()));
            if (oyVar != null) {
                oyVar.zzC((Map) map.get("b"));
            }
        } else if (!map.containsKey("error")) {
            String str;
            if (map.containsKey("a")) {
                String valueOf;
                String str2 = (String) map.get("a");
                Map map2 = (Map) map.get("b");
                if (this.zzbZE.zzIH()) {
                    wl wlVar = this.zzbZE;
                    valueOf = String.valueOf(map2);
                    wlVar.zzb(new StringBuilder((String.valueOf(str2).length() + 22) + String.valueOf(valueOf).length()).append("handleServerMessage: ").append(str2).append(" ").append(valueOf).toString(), null, new Object[0]);
                }
                Object obj;
                wl wlVar2;
                String str3;
                if (str2.equals("d") || str2.equals("m")) {
                    boolean equals = str2.equals("m");
                    str2 = (String) map2.get("p");
                    obj = map2.get("d");
                    Long zzah = ok.zzah(map2.get("t"));
                    if (!equals || !(obj instanceof Map) || ((Map) obj).size() != 0) {
                        this.zzcaH.zza(ok.zzgG(str2), obj, equals, zzah);
                    } else if (this.zzbZE.zzIH()) {
                        wlVar2 = this.zzbZE;
                        str3 = "ignoring empty merge for path ";
                        str2 = String.valueOf(str2);
                        wlVar2.zzb(str2.length() != 0 ? str3.concat(str2) : new String(str3), null, new Object[0]);
                    }
                } else if (str2.equals("rm")) {
                    str2 = (String) map2.get("p");
                    List zzgG = ok.zzgG(str2);
                    obj = map2.get("d");
                    Long zzah2 = ok.zzah(map2.get("t"));
                    List<Map> list = (List) obj;
                    List arrayList = new ArrayList();
                    for (Map map22 : list) {
                        str3 = (String) map22.get("s");
                        valueOf = (String) map22.get("e");
                        arrayList.add(new pe(str3 != null ? ok.zzgG(str3) : null, valueOf != null ? ok.zzgG(valueOf) : null, map22.get("m")));
                    }
                    if (!arrayList.isEmpty()) {
                        this.zzcaH.zza(zzgG, arrayList, zzah2);
                    } else if (this.zzbZE.zzIH()) {
                        wlVar2 = this.zzbZE;
                        str3 = "Ignoring empty range merge for path ";
                        str2 = String.valueOf(str2);
                        wlVar2.zzb(str2.length() != 0 ? str3.concat(str2) : new String(str3), null, new Object[0]);
                    }
                } else if (str2.equals("c")) {
                    zzS(ok.zzgG((String) map22.get("p")));
                } else if (str2.equals("ac")) {
                    str2 = (String) map22.get("s");
                    str = (String) map22.get("d");
                    this.zzbZE.zzb(new StringBuilder((String.valueOf(str2).length() + 23) + String.valueOf(str).length()).append("Auth token revoked: ").append(str2).append(" (").append(str).append(")").toString(), null, new Object[0]);
                    this.zzcaU = null;
                    this.zzcaV = true;
                    this.zzcaH.zzaB(false);
                    this.zzcaM.close();
                } else if (str2.equals("sd")) {
                    this.zzbZE.info((String) map22.get("msg"));
                } else if (this.zzbZE.zzIH()) {
                    wlVar2 = this.zzbZE;
                    str3 = "Unrecognized action from server: ";
                    str2 = String.valueOf(str2);
                    wlVar2.zzb(str2.length() != 0 ? str3.concat(str2) : new String(str3), null, new Object[0]);
                }
            } else if (this.zzbZE.zzIH()) {
                wl wlVar3 = this.zzbZE;
                str = String.valueOf(map);
                wlVar3.zzb(new StringBuilder(String.valueOf(str).length() + 26).append("Ignoring unknown message: ").append(str).toString(), null, new Object[0]);
            }
        }
    }

    public final void zza(List<String> list, pf pfVar) {
        if (zzGd()) {
            zza("oc", (List) list, null, pfVar);
        } else {
            this.zzcaR.add(new pb("oc", list, null, pfVar));
        }
        zzGi();
    }

    public final void zza(List<String> list, Object obj, pf pfVar) {
        zza("p", (List) list, obj, null, pfVar);
    }

    public final void zza(List<String> list, Object obj, String str, pf pfVar) {
        zza("p", (List) list, obj, str, pfVar);
    }

    public final void zza(List<String> list, Map<String, Object> map) {
        pa paVar = new pa(list, map);
        if (this.zzbZE.zzIH()) {
            wl wlVar = this.zzbZE;
            String valueOf = String.valueOf(paVar);
            wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 15).append("unlistening on ").append(valueOf).toString(), null, new Object[0]);
        }
        pc zza = zza(paVar);
        if (zza != null && zzGc()) {
            Map hashMap = new HashMap();
            hashMap.put("p", ok.zzR(zza.zzcby.zzcbt));
            Long zzGn = zza.zzGn();
            if (zzGn != null) {
                hashMap.put("q", zza.zzGm().zzcbu);
                hashMap.put("t", zzGn);
            }
            zza("n", hashMap, null);
        }
        zzGi();
    }

    public final void zza(List<String> list, Map<String, Object> map, om omVar, Long l, pf pfVar) {
        pa paVar = new pa(list, map);
        if (this.zzbZE.zzIH()) {
            wl wlVar = this.zzbZE;
            String valueOf = String.valueOf(paVar);
            wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 13).append("Listening on ").append(valueOf).toString(), null, new Object[0]);
        }
        ok.zzc(!this.zzcaT.containsKey(paVar), "listen() called twice for same QuerySpec.", new Object[0]);
        if (this.zzbZE.zzIH()) {
            wlVar = this.zzbZE;
            valueOf = String.valueOf(paVar);
            wlVar.zzb(new StringBuilder(String.valueOf(valueOf).length() + 21).append("Adding listen query: ").append(valueOf).toString(), null, new Object[0]);
        }
        pc pcVar = new pc(pfVar, paVar, l, omVar);
        this.zzcaT.put(paVar, pcVar);
        if (zzGc()) {
            zza(pcVar);
        }
        zzGi();
    }

    public final void zza(List<String> list, Map<String, Object> map, pf pfVar) {
        zza("m", (List) list, (Object) map, null, pfVar);
    }

    public final void zzb(of ofVar) {
        if (this.zzbZE.zzIH()) {
            wl wlVar = this.zzbZE;
            String str = "Got on disconnect due to ";
            String valueOf = String.valueOf(ofVar.name());
            wlVar.zzb(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), null, new Object[0]);
        }
        this.zzcaN = oz.Disconnected;
        this.zzcaM = null;
        this.zzcbd = false;
        this.zzcaQ.clear();
        zzGg();
        if (zzGe()) {
            boolean z = this.zzcaL > 0 ? System.currentTimeMillis() - this.zzcaL > 30000 : false;
            if (ofVar == of.SERVER_RESET || r0) {
                this.zzcaX.zzGA();
            }
            zzGf();
        }
        this.zzcaL = 0;
        this.zzcaH.onDisconnect();
    }

    public final void zzb(List<String> list, Object obj, pf pfVar) {
        this.zzcbd = true;
        if (zzGd()) {
            zza("o", (List) list, obj, pfVar);
        } else {
            this.zzcaR.add(new pb("o", list, obj, pfVar));
        }
        zzGi();
    }

    public final void zzb(List<String> list, Map<String, Object> map, pf pfVar) {
        this.zzcbd = true;
        if (zzGd()) {
            zza("om", (List) list, (Object) map, pfVar);
        } else {
            this.zzcaR.add(new pb("om", list, map, pfVar));
        }
        zzGi();
    }

    public final void zzc(long j, String str) {
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb("onReady", null, new Object[0]);
        }
        this.zzcaL = System.currentTimeMillis();
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb("handling timestamp", null, new Object[0]);
        }
        long currentTimeMillis = j - System.currentTimeMillis();
        Map hashMap = new HashMap();
        hashMap.put("serverTimeOffset", Long.valueOf(currentTimeMillis));
        this.zzcaH.zzB(hashMap);
        if (this.zzcaK) {
            Map hashMap2 = new HashMap();
            String str2;
            String valueOf;
            if (yp.zzJD()) {
                if (this.zzcaW.zzFW()) {
                    hashMap2.put("persistence.android.enabled", Integer.valueOf(1));
                }
                str2 = "sdk.android.";
                valueOf = String.valueOf(this.zzcaW.zzFX().replace('.', '-'));
                hashMap2.put(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), Integer.valueOf(1));
            } else {
                str2 = "sdk.java.";
                valueOf = String.valueOf(this.zzcaW.zzFX().replace('.', '-'));
                hashMap2.put(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), Integer.valueOf(1));
            }
            if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb("Sending first connection stats", null, new Object[0]);
            }
            if (!hashMap2.isEmpty()) {
                hashMap = new HashMap();
                hashMap.put("c", hashMap2);
                zza("s", hashMap, new ow(this));
            } else if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb("Not sending stats because stats are empty", null, new Object[0]);
            }
        }
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb("calling restore state", null, new Object[0]);
        }
        ok.zzc(this.zzcaN == oz.Connecting, "Wanted to restore auth, but was in wrong state: %s", this.zzcaN);
        if (this.zzcaU == null) {
            if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb("Not restoring auth because token is null.", null, new Object[0]);
            }
            this.zzcaN = oz.Connected;
            zzGh();
        } else {
            if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb("Restoring auth.", null, new Object[0]);
            }
            this.zzcaN = oz.Authenticating;
            zzaC(true);
        }
        this.zzcaK = false;
        this.zzcaY = str;
        this.zzcaH.zzGb();
    }

    public final void zzgD(String str) {
        this.zzcaI = str;
    }

    public final void zzgE(String str) {
        if (this.zzbZE.zzIH()) {
            wl wlVar = this.zzbZE;
            String str2 = "Firebase Database connection was forcefully killed by the server. Will not attempt reconnect. Reason: ";
            String valueOf = String.valueOf(str);
            wlVar.zzb(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), null, new Object[0]);
        }
        interrupt("server_kill");
    }

    public final void zzgH(String str) {
        this.zzbZE.zzb("Auth token refreshed.", null, new Object[0]);
        this.zzcaU = str;
        if (!zzGc()) {
            return;
        }
        if (str != null) {
            zzaC(false);
            return;
        }
        ok.zzc(zzGc(), "Must be connected to send unauth.", new Object[0]);
        ok.zzc(this.zzcaU == null, "Auth token must not be set.", new Object[0]);
        zza("unauth", Collections.emptyMap(), null);
    }

    public final void zzgI(String str) {
        ok.zzc(this.zzcaN == oz.GettingToken, "Trying to open network connection while in the wrong state: %s", this.zzcaN);
        if (str == null) {
            this.zzcaH.zzaB(false);
        }
        this.zzcaU = str;
        this.zzcaN = oz.Connecting;
        this.zzcaM = new od(this.zzcaW, this.zzcar, this.zzcaI, this, this.zzcaY);
        this.zzcaM.open();
    }
}
