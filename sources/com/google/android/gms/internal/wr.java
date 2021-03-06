package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class wr implements xm {
    public static Comparator<wp> zzchR = new ws();
    private final nh<wp, xm> zzcgs;
    private final xm zzchS;
    private String zzchT;

    protected wr() {
        this.zzchT = null;
        this.zzcgs = ni.zza(zzchR);
        this.zzchS = xd.zzJb();
    }

    protected wr(nh<wp, xm> nhVar, xm xmVar) {
        this.zzchT = null;
        if (!nhVar.isEmpty() || xmVar.isEmpty()) {
            this.zzchS = xmVar;
            this.zzcgs = nhVar;
            return;
        }
        throw new IllegalArgumentException("Can't create empty ChildrenNode with priority!");
    }

    private static void zzb(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append(" ");
        }
    }

    private final void zzc(StringBuilder stringBuilder, int i) {
        if (this.zzcgs.isEmpty() && this.zzchS.isEmpty()) {
            stringBuilder.append("{ }");
            return;
        }
        stringBuilder.append("{\n");
        Iterator it = this.zzcgs.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzb(stringBuilder, i + 2);
            stringBuilder.append(((wp) entry.getKey()).asString());
            stringBuilder.append("=");
            if (entry.getValue() instanceof wr) {
                ((wr) entry.getValue()).zzc(stringBuilder, i + 2);
            } else {
                stringBuilder.append(((xm) entry.getValue()).toString());
            }
            stringBuilder.append("\n");
        }
        if (!this.zzchS.isEmpty()) {
            zzb(stringBuilder, i + 2);
            stringBuilder.append(".priority=");
            stringBuilder.append(this.zzchS.toString());
            stringBuilder.append("\n");
        }
        zzb(stringBuilder, i);
        stringBuilder.append("}");
    }

    public /* synthetic */ int compareTo(Object obj) {
        return zzg((xm) obj);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof wr)) {
            return false;
        }
        wr wrVar = (wr) obj;
        if (!zzIR().equals(wrVar.zzIR())) {
            return false;
        }
        if (this.zzcgs.size() != wrVar.zzcgs.size()) {
            return false;
        }
        Iterator it = this.zzcgs.iterator();
        Iterator it2 = wrVar.zzcgs.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Entry entry = (Entry) it.next();
            Entry entry2 = (Entry) it2.next();
            if (((wp) entry.getKey()).equals(entry2.getKey())) {
                if (!((xm) entry.getValue()).equals(entry2.getValue())) {
                }
            }
            return false;
        }
        if (!it.hasNext() && !it2.hasNext()) {
            return true;
        }
        throw new IllegalStateException("Something went wrong internally.");
    }

    public int getChildCount() {
        return this.zzcgs.size();
    }

    public Object getValue() {
        return getValue(false);
    }

    public Object getValue(boolean z) {
        int i = 0;
        if (isEmpty()) {
            return null;
        }
        Map hashMap = new HashMap();
        Iterator it = this.zzcgs.iterator();
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (it.hasNext()) {
            int i5;
            Entry entry = (Entry) it.next();
            String asString = ((wp) entry.getKey()).asString();
            hashMap.put(asString, ((xm) entry.getValue()).getValue(z));
            int i6 = i4 + 1;
            if (i2 != 0) {
                if (asString.length() <= 1 || asString.charAt(0) != '0') {
                    Integer zzha = zd.zzha(asString);
                    if (zzha != null && zzha.intValue() >= 0) {
                        if (zzha.intValue() > i3) {
                            i3 = zzha.intValue();
                            i4 = i6;
                        }
                    }
                }
                i5 = 0;
                i2 = i5;
                i4 = i6;
            }
            i5 = i2;
            i2 = i5;
            i4 = i6;
        }
        if (z || i2 == 0 || i3 >= i4 * 2) {
            if (z && !this.zzchS.isEmpty()) {
                hashMap.put(".priority", this.zzchS.getValue());
            }
            return hashMap;
        }
        List arrayList = new ArrayList(i3 + 1);
        while (i <= i3) {
            arrayList.add(hashMap.get(i));
            i++;
        }
        return arrayList;
    }

    public int hashCode() {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            xl xlVar = (xl) it.next();
            i = xlVar.zzFn().hashCode() + (((i * 31) + xlVar.zzJk().hashCode()) * 17);
        }
        return i;
    }

    public boolean isEmpty() {
        return this.zzcgs.isEmpty();
    }

    public Iterator<xl> iterator() {
        return new wv(this.zzcgs.iterator());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        zzc(stringBuilder, 0);
        return stringBuilder.toString();
    }

    public Iterator<xl> zzFz() {
        return new wv(this.zzcgs.zzFz());
    }

    public String zzIP() {
        if (this.zzchT == null) {
            String zza = zza(xo.V1);
            this.zzchT = zza.isEmpty() ? "" : zd.zzgY(zza);
        }
        return this.zzchT;
    }

    public boolean zzIQ() {
        return false;
    }

    public xm zzIR() {
        return this.zzchS;
    }

    public final wp zzIS() {
        return (wp) this.zzcgs.zzFx();
    }

    public final wp zzIT() {
        return (wp) this.zzcgs.zzFy();
    }

    public xm zzN(qr qrVar) {
        wp zzHc = qrVar.zzHc();
        return zzHc == null ? this : zzm(zzHc).zzN(qrVar.zzHd());
    }

    public String zza(xo xoVar) {
        int i = 0;
        if (xoVar != xo.V1) {
            throw new IllegalArgumentException("Hashes on children nodes only supported for V1");
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!this.zzchS.isEmpty()) {
            stringBuilder.append("priority:");
            stringBuilder.append(this.zzchS.zza(xo.V1));
            stringBuilder.append(":");
        }
        List arrayList = new ArrayList();
        Iterator it = iterator();
        int i2 = 0;
        while (it.hasNext()) {
            xl xlVar = (xl) it.next();
            arrayList.add(xlVar);
            int i3 = (i2 == 0 && xlVar.zzFn().zzIR().isEmpty()) ? 0 : 1;
            i2 = i3;
        }
        if (i2 != 0) {
            Collections.sort(arrayList, xr.zzJl());
        }
        ArrayList arrayList2 = (ArrayList) arrayList;
        i2 = arrayList2.size();
        while (i < i2) {
            Object obj = arrayList2.get(i);
            i++;
            xl xlVar2 = (xl) obj;
            String zzIP = xlVar2.zzFn().zzIP();
            if (!zzIP.equals("")) {
                stringBuilder.append(":");
                stringBuilder.append(xlVar2.zzJk().asString());
                stringBuilder.append(":");
                stringBuilder.append(zzIP);
            }
        }
        return stringBuilder.toString();
    }

    public final void zza(wu wuVar, boolean z) {
        if (!z || zzIR().isEmpty()) {
            this.zzcgs.zza(wuVar);
        } else {
            this.zzcgs.zza(new wt(this, wuVar));
        }
    }

    public xm zze(wp wpVar, xm xmVar) {
        if (wpVar.zzIN()) {
            return zzf(xmVar);
        }
        nh nhVar = this.zzcgs;
        if (nhVar.containsKey(wpVar)) {
            nhVar = nhVar.zzX(wpVar);
        }
        if (!xmVar.isEmpty()) {
            nhVar = nhVar.zzg(wpVar, xmVar);
        }
        return nhVar.isEmpty() ? xd.zzJb() : new wr(nhVar, this.zzchS);
    }

    public xm zzf(xm xmVar) {
        return this.zzcgs.isEmpty() ? xd.zzJb() : new wr(this.zzcgs, xmVar);
    }

    public int zzg(xm xmVar) {
        return isEmpty() ? xmVar.isEmpty() ? 0 : -1 : xmVar.zzIQ() ? 1 : xmVar.isEmpty() ? 1 : xmVar == xm.zzciw ? -1 : 0;
    }

    public boolean zzk(wp wpVar) {
        return !zzm(wpVar).isEmpty();
    }

    public wp zzl(wp wpVar) {
        return (wp) this.zzcgs.zzY(wpVar);
    }

    public xm zzl(qr qrVar, xm xmVar) {
        wp zzHc = qrVar.zzHc();
        return zzHc == null ? xmVar : zzHc.zzIN() ? zzf(xmVar) : zze(zzHc, zzm(zzHc).zzl(qrVar.zzHd(), xmVar));
    }

    public xm zzm(wp wpVar) {
        return (!wpVar.zzIN() || this.zzchS.isEmpty()) ? this.zzcgs.containsKey(wpVar) ? (xm) this.zzcgs.get(wpVar) : xd.zzJb() : this.zzchS;
    }
}
