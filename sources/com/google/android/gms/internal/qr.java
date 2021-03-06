package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class qr implements Comparable<qr>, Iterable<wp> {
    private static final qr zzcdh = new qr("");
    private final int end;
    private final int start;
    private final wp[] zzcdg;

    public qr(String str) {
        String[] split = str.split("/");
        int i = 0;
        for (String length : split) {
            if (length.length() > 0) {
                i++;
            }
        }
        this.zzcdg = new wp[i];
        int length2 = split.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length2) {
            String str2 = split[i2];
            if (str2.length() > 0) {
                i = i3 + 1;
                this.zzcdg[i3] = wp.zzgT(str2);
            } else {
                i = i3;
            }
            i2++;
            i3 = i;
        }
        this.start = 0;
        this.end = this.zzcdg.length;
    }

    public qr(List<String> list) {
        this.zzcdg = new wp[list.size()];
        int i = 0;
        for (String zzgT : list) {
            int i2 = i + 1;
            this.zzcdg[i] = wp.zzgT(zzgT);
            i = i2;
        }
        this.start = 0;
        this.end = list.size();
    }

    public qr(wp... wpVarArr) {
        this.zzcdg = (wp[]) Arrays.copyOf(wpVarArr, wpVarArr.length);
        this.start = 0;
        this.end = wpVarArr.length;
    }

    private qr(wp[] wpVarArr, int i, int i2) {
        this.zzcdg = wpVarArr;
        this.start = i;
        this.end = i2;
    }

    public static qr zzGZ() {
        return zzcdh;
    }

    public static qr zza(qr qrVar, qr qrVar2) {
        while (true) {
            wp zzHc = qrVar.zzHc();
            wp zzHc2 = qrVar2.zzHc();
            if (zzHc == null) {
                return qrVar2;
            }
            if (zzHc.equals(zzHc2)) {
                qrVar = qrVar.zzHd();
                qrVar2 = qrVar2.zzHd();
            } else {
                String valueOf = String.valueOf(qrVar2);
                String valueOf2 = String.valueOf(qrVar);
                throw new DatabaseException(new StringBuilder((String.valueOf(valueOf).length() + 37) + String.valueOf(valueOf2).length()).append("INTERNAL ERROR: ").append(valueOf).append(" is not contained in ").append(valueOf2).toString());
            }
        }
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return zzj((qr) obj);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof qr)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        qr qrVar = (qr) obj;
        if (size() != qrVar.size()) {
            return false;
        }
        int i = this.start;
        int i2 = qrVar.start;
        while (i < this.end && i2 < qrVar.end) {
            if (!this.zzcdg[i].equals(qrVar.zzcdg[i2])) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public final int hashCode() {
        int i = 0;
        for (int i2 = this.start; i2 < this.end; i2++) {
            i = (i * 37) + this.zzcdg[i2].hashCode();
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.start >= this.end;
    }

    public final Iterator<wp> iterator() {
        return new qs(this);
    }

    public final int size() {
        return this.end - this.start;
    }

    public final String toString() {
        if (isEmpty()) {
            return "/";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = this.start; i < this.end; i++) {
            stringBuilder.append("/");
            stringBuilder.append(this.zzcdg[i].asString());
        }
        return stringBuilder.toString();
    }

    public final String zzHa() {
        if (isEmpty()) {
            return "/";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = this.start; i < this.end; i++) {
            if (i > this.start) {
                stringBuilder.append("/");
            }
            stringBuilder.append(this.zzcdg[i].asString());
        }
        return stringBuilder.toString();
    }

    public final List<String> zzHb() {
        List<String> arrayList = new ArrayList(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            arrayList.add(((wp) it.next()).asString());
        }
        return arrayList;
    }

    public final wp zzHc() {
        return isEmpty() ? null : this.zzcdg[this.start];
    }

    public final qr zzHd() {
        int i = this.start;
        if (!isEmpty()) {
            i++;
        }
        return new qr(this.zzcdg, i, this.end);
    }

    public final qr zzHe() {
        return isEmpty() ? null : new qr(this.zzcdg, this.start, this.end - 1);
    }

    public final wp zzHf() {
        return !isEmpty() ? this.zzcdg[this.end - 1] : null;
    }

    public final qr zza(wp wpVar) {
        int size = size();
        Object obj = new wp[(size + 1)];
        System.arraycopy(this.zzcdg, this.start, obj, 0, size);
        obj[size] = wpVar;
        return new qr(obj, 0, size + 1);
    }

    public final qr zzh(qr qrVar) {
        int size = size() + qrVar.size();
        Object obj = new wp[size];
        System.arraycopy(this.zzcdg, this.start, obj, 0, size());
        System.arraycopy(qrVar.zzcdg, qrVar.start, obj, size(), qrVar.size());
        return new qr(obj, 0, size);
    }

    public final boolean zzi(qr qrVar) {
        if (size() > qrVar.size()) {
            return false;
        }
        int i = this.start;
        int i2 = qrVar.start;
        while (i < this.end) {
            if (!this.zzcdg[i].equals(qrVar.zzcdg[i2])) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public final int zzj(qr qrVar) {
        int i = this.start;
        int i2 = qrVar.start;
        while (i < this.end && i2 < qrVar.end) {
            int zzi = this.zzcdg[i].zzi(qrVar.zzcdg[i2]);
            if (zzi != 0) {
                return zzi;
            }
            i++;
            i2++;
        }
        return (i == this.end && i2 == qrVar.end) ? 0 : i == this.end ? -1 : 1;
    }
}
