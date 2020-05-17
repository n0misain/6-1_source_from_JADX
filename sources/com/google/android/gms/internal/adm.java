package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class adm implements Cloneable {
    private Object value;
    private adk<?, ?> zzcsu;
    private List<adr> zzcsv = new ArrayList();

    adm() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzn()];
        zza(adh.zzI(bArr));
        return bArr;
    }

    private adm zzLP() {
        adm adm = new adm();
        try {
            adm.zzcsu = this.zzcsu;
            if (this.zzcsv == null) {
                adm.zzcsv = null;
            } else {
                adm.zzcsv.addAll(this.zzcsv);
            }
            if (this.value != null) {
                if (this.value instanceof adp) {
                    adm.value = (adp) ((adp) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    adm.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    r4 = new byte[bArr.length][];
                    adm.value = r4;
                    for (r2 = 0; r2 < bArr.length; r2++) {
                        r4[r2] = (byte[]) bArr[r2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    adm.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    adm.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    adm.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    adm.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    adm.value = ((double[]) this.value).clone();
                } else if (this.value instanceof adp[]) {
                    adp[] adpArr = (adp[]) this.value;
                    r4 = new adp[adpArr.length];
                    adm.value = r4;
                    for (r2 = 0; r2 < adpArr.length; r2++) {
                        r4[r2] = (adp) adpArr[r2].clone();
                    }
                }
            }
            return adm;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzLP();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof adm)) {
            return false;
        }
        adm adm = (adm) obj;
        if (this.value != null && adm.value != null) {
            return this.zzcsu == adm.zzcsu ? !this.zzcsu.zzcjG.isArray() ? this.value.equals(adm.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) adm.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) adm.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) adm.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) adm.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) adm.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) adm.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) adm.value) : false;
        } else {
            if (this.zzcsv != null && adm.zzcsv != null) {
                return this.zzcsv.equals(adm.zzcsv);
            }
            try {
                return Arrays.equals(toByteArray(), adm.toByteArray());
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    final void zza(adh adh) throws IOException {
        if (this.value != null) {
            this.zzcsu.zza(this.value, adh);
            return;
        }
        for (adr adr : this.zzcsv) {
            adh.zzcu(adr.tag);
            adh.zzK(adr.zzbws);
        }
    }

    final void zza(adr adr) {
        this.zzcsv.add(adr);
    }

    final <T> T zzb(adk<?, T> adk) {
        if (this.value == null) {
            this.zzcsu = adk;
            this.value = adk.zzX(this.zzcsv);
            this.zzcsv = null;
        } else if (!this.zzcsu.equals(adk)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.value;
    }

    final int zzn() {
        if (this.value != null) {
            return this.zzcsu.zzav(this.value);
        }
        int i = 0;
        for (adr adr : this.zzcsv) {
            i = (adr.zzbws.length + (adh.zzcv(adr.tag) + 0)) + i;
        }
        return i;
    }
}
